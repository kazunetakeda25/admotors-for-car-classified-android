package com.panaceasoft.admotors.ui.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentModelBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.model.adapter.ModelAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.itemmodel.ItemModelViewModel;
import com.panaceasoft.admotors.viewobject.Model;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;
import com.panaceasoft.admotors.viewobject.holder.ItemParameterHolder;

import java.util.List;

public class ModelFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemModelViewModel itemModelViewModel;
    private ItemParameterHolder itemParameterHolder = new ItemParameterHolder();

    @VisibleForTesting
    private AutoClearedValue<FragmentModelBinding> binding;
    private AutoClearedValue<ModelAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentModelBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_model, container, false, dataBindingComponent);


        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());

        return binding.get().getRoot();

    }

    @Override
    protected void initUIAndActions() {
        binding.get().subcategoryList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager layoutManager = (GridLayoutManager)
                        recyclerView.getLayoutManager();

                if (layoutManager != null) {

                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();
                    if (lastPosition == adapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !itemModelViewModel.forceEndLoading) {

                            if (connectivity.isConnected()) {

                                itemModelViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                                int limit = Config.LIST_MANUFACTURER_COUNT;
                                itemModelViewModel.offset = itemModelViewModel.offset + limit;

                                itemModelViewModel.setNextPageLoadingStateObj(itemModelViewModel.manufacturerId, String.valueOf(Config.LIST_MANUFACTURER_COUNT), String.valueOf(itemModelViewModel.offset));
                            }
                        }
                    }
                }
            }
        });

        binding.get().swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.view__primary_line));
        binding.get().swipeRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.global__primary));
        binding.get().swipeRefresh.setOnRefreshListener(() -> {

            itemModelViewModel.loadingDirection = Utils.LoadingDirection.top;

            // reset productViewModel.offset
            itemModelViewModel.offset = 0;

            // reset productViewModel.forceEndLoading
            itemModelViewModel.forceEndLoading = false;

            // update live data
            itemModelViewModel.setModelListData(itemModelViewModel.manufacturerId, String.valueOf(Config.LIST_MANUFACTURER_COUNT), String.valueOf(itemModelViewModel.offset));

        });
    }


    @Override
    protected void initViewModels() {
        // ViewModel need to get from new ViewModelProviders
        itemModelViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemModelViewModel.class);
    }

    @Override
    protected void initAdapters() {
        ModelAdapter nvAdapter = new ModelAdapter(dataBindingComponent, new ModelAdapter.SubCategoryClickCallback() {
            @Override
            public void onClick(Model model) {

                itemParameterHolder.model_id = model.id;

                navigationController.navigateToHomeFilteringActivity(ModelFragment.this.getActivity(), itemParameterHolder, model.name, selectedCityLat, selectedCityLng, selectedCityName);

                if (ModelFragment.this.getActivity() != null) {
                    ModelFragment.this.getActivity().finish();
                }

            }
        },this);
        this.adapter = new AutoClearedValue<>(this, nvAdapter);
        binding.get().subcategoryList.setAdapter(nvAdapter);
    }

    @Override
    protected void initData() {
        getIntentData();

        loadNews();
    }

    private void getIntentData() {
        try {
            if (getActivity() != null) {
                if (getActivity().getIntent().getExtras() != null) {
                    itemModelViewModel.manufacturerId = getActivity().getIntent().getExtras().getString(Constants.MANUFACTURER_ID);
                    itemParameterHolder.manufacturer_id = itemModelViewModel.manufacturerId;
//                    itemParameterHolder.city_id = selectedCityId;
                }
            }
        } catch (Exception e) {
            Utils.psErrorLog("", e);
        }
    }
    private void loadNews() {

        // Load Sub Category
        itemModelViewModel.setModelListData(itemModelViewModel.manufacturerId, String.valueOf(Config.LIST_MANUFACTURER_COUNT), String.valueOf(itemModelViewModel.offset));

        LiveData<Resource<List<Model>>> news = itemModelViewModel.getModelListData();

        if (news != null) {

            news.observe(this, listResource -> {
                if (listResource != null) {

                    Utils.psLog("Got Data" + listResource.message + listResource.toString());

                    switch (listResource.status) {
                        case LOADING:
                            // Loading State
                            // Data are from Local DB

                            if (listResource.data != null) {
                                //fadeIn Animation
                                fadeIn(binding.get().getRoot());

                                // Update the data
                                replaceData(listResource.data);

                            }

                            break;

                        case SUCCESS:
                            // Success State
                            // Data are from Server

                            if (listResource.data != null) {
                                // Update the data
                                replaceData(listResource.data);
                            }

                            itemModelViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            itemModelViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (itemModelViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemModelViewModel.forceEndLoading = true;
                    }

                }

            });
        }

        itemModelViewModel.getNextPageLoadingStateData().observe(this, state -> {
            if (state != null) {
                if (state.status == Status.ERROR) {
                    Utils.psLog("Next Page State : " + state.data);

                    itemModelViewModel.setLoadingState(false);
                    itemModelViewModel.forceEndLoading = true;
                }
            }
        });

        itemModelViewModel.getLoadingState().observe(this, loadingState -> {

            binding.get().setLoadingMore(itemModelViewModel.isLoading);

            if (loadingState != null && !loadingState) {
                binding.get().swipeRefresh.setRefreshing(false);
            }

        });

    }

    private void replaceData(List<Model> newsList) {

        adapter.get().replace(newsList);
        binding.get().executePendingBindings();

    }

    @Override
    public void onDispatched() {

        if (itemModelViewModel.loadingDirection == Utils.LoadingDirection.top) {

            if (binding.get().subcategoryList != null) {

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        binding.get().subcategoryList.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
    }

    //endregion

}

