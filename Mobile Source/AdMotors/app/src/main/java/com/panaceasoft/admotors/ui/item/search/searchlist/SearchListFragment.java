package com.panaceasoft.admotors.ui.item.search.searchlist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.MainActivity;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentItemListBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.item.adapter.ItemVerticalListAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.item.ItemViewModel;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;
import com.panaceasoft.admotors.viewobject.holder.ItemParameterHolder;

import java.util.ArrayList;
import java.util.List;


public class SearchListFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private boolean typeClicked = false;
    private boolean filterClicked = false;
    private boolean mapFilterClicked = false;
    private List<Item> clearRecyclerView = new ArrayList<>();
    private ItemViewModel itemViewModel;

    @VisibleForTesting
    private AutoClearedValue<FragmentItemListBinding> binding;
    private AutoClearedValue<ItemVerticalListAdapter> adapter;

    //region Override Methods
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentItemListBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_list, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == Constants.REQUEST_CODE__ITEM_LIST_FRAGMENT
                && resultCode == Constants.RESULT_CODE__CATEGORY_FILTER) {

            String manufacturerId = data.getStringExtra(Constants.MANUFACTURER_ID);
            if (manufacturerId != null) {
                itemViewModel.holder.manufacturer_id = manufacturerId;
            }
            String modelId = data.getStringExtra(Constants.MODEL_ID);
            if (modelId != null) {
                itemViewModel.holder.model_id = modelId;
            }

            typeClicked = (itemViewModel.holder.manufacturer_id != null && !itemViewModel.holder.manufacturer_id.equals(""))
                    || (itemViewModel.holder.model_id != null && !itemViewModel.holder.model_id.equals(""));

            typeButtonClicked(typeClicked);

            replaceData(clearRecyclerView);

            loadItemList();

        } else if (requestCode == Constants.REQUEST_CODE__ITEM_LIST_FRAGMENT
                && resultCode == Constants.RESULT_CODE__SPECIAL_FILTER) {

            // Result From Filter
//
            if (data.getSerializableExtra(Constants.FILTERING_HOLDER) != null) {

                itemViewModel.holder = (ItemParameterHolder) data.getSerializableExtra(Constants.FILTERING_HOLDER);

            }

            filterClicked = !itemViewModel.holder.keyword.equals("") || !itemViewModel.holder.order_by.equals(Constants.FILTERING_FEATURE) ||
                    !itemViewModel.holder.order_type.equals(Constants.FILTERING_DESC);

            tuneButtonClicked(filterClicked);

            replaceData(clearRecyclerView);

            loadItemList();

        } else if (requestCode == Constants.REQUEST_CODE__MAP_FILTERING
                && resultCode == Constants.RESULT_CODE__MAP_FILTERING) {

            if (data.getSerializableExtra(Constants.ITEM_HOLDER) != null) {

                itemViewModel.holder = (ItemParameterHolder) data.getSerializableExtra(Constants.ITEM_HOLDER);

            }

            mapFilterClicked = !itemViewModel.holder.lat.equals("") || !itemViewModel.holder.lng.equals("") ||
                    !itemViewModel.holder.mapMiles.equals("");

            mapFilterButtonClicked(mapFilterClicked);

            replaceData(clearRecyclerView);

            loadItemList();

        }

    }


    @Override
    protected void initUIAndActions() {
        if (getActivity() instanceof MainActivity) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            ((MainActivity) this.getActivity()).binding.toolbar.setBackgroundColor(getResources().getColor(R.color.global__primary));
            ((MainActivity) getActivity()).updateToolbarIconColor(Color.WHITE);
            ((MainActivity) getActivity()).updateMenuIconWhite();
            ((MainActivity) getActivity()).refreshPSCount();
        }

        binding.get().typeButton.setOnClickListener(this::ButtonClick);

        binding.get().tuneButton.setOnClickListener(this::ButtonClick);

        binding.get().sortButton.setOnClickListener(this::ButtonClick);

        binding.get().newsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager layoutManager = (GridLayoutManager)
                        recyclerView.getLayoutManager();

                if (layoutManager != null) {

                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();

                    if (lastPosition == adapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !itemViewModel.forceEndLoading) {

                            itemViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                            int limit = Config.ITEM_COUNT;

                            itemViewModel.offset = itemViewModel.offset + limit;

                            loadNextPageItemList(String.valueOf(itemViewModel.offset));

                        }
                    }
                }
            }
        });

        binding.get().swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.view__primary_line));
        binding.get().swipeRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.global__primary));
        binding.get().swipeRefresh.setOnRefreshListener(() -> {

            itemViewModel.forceEndLoading = false;

            itemViewModel.loadingDirection = Utils.LoadingDirection.top;

            loadItemList();

        });
    }


    @Override
    protected void initViewModels() {
        // ViewModel need to get from new ViewModelProviders
        itemViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemViewModel.class);

    }

    @Override
    protected void initAdapters() {

        ItemVerticalListAdapter nvAdapter = new ItemVerticalListAdapter(dataBindingComponent, item -> navigationController.navigateToItemDetailActivity(SearchListFragment.this.getActivity(), item.id, item.title), this);

        this.adapter = new AutoClearedValue<>(this, nvAdapter);
        binding.get().newsList.setAdapter(nvAdapter);
    }

    @Override
    protected void initData() {

        if (getActivity() != null) {
            itemViewModel.holder = (ItemParameterHolder) getActivity().getIntent().getSerializableExtra(Constants.ITEM_PARAM_HOLDER_KEY);

            if (getArguments() != null) {
                itemViewModel.holder = (ItemParameterHolder) getArguments().getSerializable(Constants.ITEM_PARAM_HOLDER_KEY);
            }

            if (itemViewModel.holder != null) {
//                if (!itemViewModel.holder.keyword.isEmpty()) {
//                    binding.get().buttonLayout.setVisibility(View.GONE);
//                }
//                else {
                binding.get().buttonLayout.setVisibility(View.VISIBLE);
//                }
            }

            initItemData();

//            setTouchCount();

            filterClicked = !itemViewModel.holder.keyword.equals(Constants.FILTERING_INACTIVE) ||
                    !itemViewModel.holder.order_by.equals(Constants.FILTERING_INACTIVE) ||
                    !itemViewModel.holder.order_type.equals(Constants.FILTERING_INACTIVE);

            typeClicked = !itemViewModel.holder.manufacturer_id.equals(Constants.FILTERING_INACTIVE) || !itemViewModel.holder.model_id.equals(Constants.FILTERING_INACTIVE);

            mapFilterClicked = !itemViewModel.holder.lat.equals("") || !itemViewModel.holder.lng.equals("") ||
                    !itemViewModel.holder.mapMiles.equals("");

            typeButtonClicked(typeClicked);

            tuneButtonClicked(filterClicked);

            mapFilterButtonClicked(mapFilterClicked);

        }
    }

    //endregion


    //region Private Methods

    private void initItemData() {

        loadItemList();

        LiveData<Resource<List<Item>>> news = itemViewModel.getItemListByKeyData();

        if (news != null) {

            news.observe(this, listResource -> {
                if (listResource != null) {
                    switch (listResource.status) {
                        case SUCCESS:

                            itemViewModel.setLoadingState(false);

                            if (listResource.data != null) {
                                if (listResource.data.size() == 0) {

                                    if (!binding.get().getLoadingMore()) {
                                        binding.get().noItemConstraintLayout.setVisibility(View.VISIBLE);
                                    }

                                } else {

                                    binding.get().noItemConstraintLayout.setVisibility(View.INVISIBLE);

                                }

                                fadeIn(binding.get().getRoot());

                                replaceData(listResource.data);

                                onDispatched();
                            }
                            break;

                        case LOADING:
                            if (listResource.data != null) {

                                binding.get().noItemConstraintLayout.setVisibility(View.INVISIBLE);

                                fadeIn(binding.get().getRoot());

                                replaceData(listResource.data);

                                itemViewModel.setLoadingState(true);

                                if (itemViewModel.forceEndLoading) {
                                    itemViewModel.forceEndLoading = false;
                                }

                                onDispatched();
                            }
                            break;

                        case ERROR:

                            itemViewModel.setLoadingState(false);
                            itemViewModel.forceEndLoading = true;

                            if (itemViewModel.getItemListByKeyData() != null) {
                                if (itemViewModel.getItemListByKeyData().getValue() != null) {
                                    if (itemViewModel.getItemListByKeyData().getValue().data != null) {
                                        if (!binding.get().getLoadingMore() && itemViewModel.getItemListByKeyData().getValue().data.size() == 0) {
                                            binding.get().noItemConstraintLayout.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }

                            break;

                        default:
                            break;
                    }
                }
            });
        }

        itemViewModel.getNextPageItemListByKeyData().observe(this, state -> {

            if (state != null) {
                if (state.status == Status.ERROR) {

                    itemViewModel.setLoadingState(false);
                    itemViewModel.forceEndLoading = true;

                }
            }
        });

        itemViewModel.getLoadingState().observe(this, loadingState -> {

            binding.get().setLoadingMore(itemViewModel.isLoading);

            if (loadingState != null && !loadingState) {
                binding.get().swipeRefresh.setRefreshing(false);
            }
        });

    }

    private void replaceData(List<Item> newsList) {
        adapter.get().replace(newsList);
        binding.get().executePendingBindings();
    }


    private void typeButtonClicked(Boolean b) {
        if (b) {
            binding.get().typeButton.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.baseline_list_with_check_orange_24), null, null);
        } else {
            binding.get().typeButton.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.baseline_list_orange_24), null, null);
        }
    }

    private void tuneButtonClicked(Boolean b) {
        if (b) {

            binding.get().tuneButton.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.baseline_tune_with_check_orange_24), null, null);

        } else {
            binding.get().tuneButton.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.baseline_tune_orange_24), null, null);
        }
    }

    private void mapFilterButtonClicked(Boolean b) {
        if (b) {

            binding.get().sortButton.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.baseline_sort_with_check_orange_24), null, null);

        } else {
            binding.get().sortButton.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.baseline_sort_orange_24), null, null);
        }
    }

    private void ButtonClick(View view) {

        switch (view.getId()) {
            case R.id.typeButton:
//
                navigationController.navigateToTypeFilterFragment(SearchListFragment.this.getActivity(), itemViewModel.holder.manufacturer_id,
                        itemViewModel.holder.model_id, itemViewModel.holder, Constants.FILTERING_TYPE_FILTER);
//                navigationController.navigateToCategoryActivity(getActivity());

                typeButtonClicked(typeClicked);

                break;

            case R.id.tuneButton:

                navigationController.navigateToTypeFilterFragment(SearchListFragment.this.getActivity(), itemViewModel.holder.manufacturer_id,
                        itemViewModel.holder.model_id, itemViewModel.holder, Constants.FILTERING_SPECIAL_FILTER);

                tuneButtonClicked(filterClicked);

                break;

            case R.id.sortButton:

//                mBottomSheetDialog.get().show();
//                ButtonSheetClick();

                itemViewModel.holder.location_id = selected_location_id;
                navigationController.navigateToMapFiltering(this.getActivity(), itemViewModel.holder);

                break;

            default:
                Utils.psLog("No ID for Buttons");
        }
    }

    @Override
    public void onDispatched() {

        if (itemViewModel.loadingDirection == Utils.LoadingDirection.top) {

            if (binding.get() != null) {
                GridLayoutManager layoutManager = (GridLayoutManager)
                        binding.get().newsList.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPositionWithOffset(0, 0);
                }
            }
        }
    }

    private void resetLimitAndOffset() {
        itemViewModel.offset = 0;
    }

    private void loadNextPageItemList(String offset) {

        itemViewModel.setNextPageItemListByKeyObj(String.valueOf(Config.ITEM_COUNT), offset, Utils.checkUserId(loginUserId), itemViewModel.holder);


    }

    private void loadItemList() {

        resetLimitAndOffset();

        itemViewModel.setItemListByKeyObj(Utils.checkUserId(loginUserId), String.valueOf(Config.ITEM_COUNT), Constants.ZERO, itemViewModel.holder);

    }

    @Override
    public void onResume() {
        super.onResume();

        loadLoginUserId();
    }
}

