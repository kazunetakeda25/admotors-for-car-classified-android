package com.panaceasoft.admotors.ui.manufacturer.list;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.material.appbar.AppBarLayout;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.MainActivity;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentManufacturerListBinding;
import com.panaceasoft.admotors.ui.manufacturer.adapter.ManufacturerAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.item.TouchCountViewModel;
import com.panaceasoft.admotors.viewmodel.itemmanufacturer.ItemManufacturerViewModel;
import com.panaceasoft.admotors.viewobject.Manufacturer;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;

import java.util.List;

public class ManufacturerListFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemManufacturerViewModel itemManufacturerViewModel;
    private TouchCountViewModel touchCountViewModel;

    @VisibleForTesting
    private AutoClearedValue<FragmentManufacturerListBinding> binding;
    private AutoClearedValue<ManufacturerAdapter> adapter;

    //endregion


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentManufacturerListBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_manufacturer_list, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());

        
        return binding.get().getRoot();
    }

    @Override
    protected void initUIAndActions() {


        if (getActivity() instanceof MainActivity) {

            Toolbar toolbar = ((MainActivity) getActivity()).binding.toolbar;
            if (toolbar.getParent() instanceof AppBarLayout){
                ((AppBarLayout)toolbar.getParent()).setExpanded(true,true);
            }

            ((MainActivity) getActivity()).setToolbarText(((MainActivity) getActivity()).binding.toolbar, getString(R.string.category__list_title));
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            ((MainActivity) this.getActivity()).binding.toolbar.setBackgroundColor(getResources().getColor(R.color.global__primary));
            ((MainActivity) getActivity()).updateToolbarIconColor(Color.WHITE);
            ((MainActivity) getActivity()).updateMenuIconWhite();
            ((MainActivity) getActivity()).refreshPSCount();

        }

        if (Config.SHOW_ADMOB && connectivity.isConnected()) {
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            binding.get().adView.loadAd(adRequest);
        } else {
            binding.get().adView.setVisibility(View.GONE);
        }

        binding.get().categoryList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager layoutManager = (GridLayoutManager)
                        recyclerView.getLayoutManager();

                if (layoutManager != null) {
                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();
                    if (lastPosition == adapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !itemManufacturerViewModel.forceEndLoading) {

                            if (connectivity.isConnected()) {

                                itemManufacturerViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                                int limit = Config.LIST_MANUFACTURER_COUNT;
                                itemManufacturerViewModel.offset = itemManufacturerViewModel.offset + limit;

                                itemManufacturerViewModel.setNextPageLoadingStateObj(String.valueOf(Config.LIST_MANUFACTURER_COUNT),
                                        String.valueOf(itemManufacturerViewModel.offset));//itemManufacturerViewModel.categoryParameterHolder.cityId);
                            }
                        }
                    }
                }
            }
        });

        binding.get().swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.view__primary_line));
        binding.get().swipeRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.global__primary));
        binding.get().swipeRefresh.setOnRefreshListener(() -> {

            itemManufacturerViewModel.loadingDirection = Utils.LoadingDirection.top;

            // reset itemManufacturerViewModel.offset
            itemManufacturerViewModel.offset = 0;

            // reset itemManufacturerViewModel.forceEndLoading
            itemManufacturerViewModel.forceEndLoading = false;

            // update live data
            itemManufacturerViewModel.setManufacturerListObj(String.valueOf(Config.LIST_MANUFACTURER_COUNT), String.valueOf(itemManufacturerViewModel.offset));

        });
    }

    @Override
    protected void initViewModels() {
        itemManufacturerViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemManufacturerViewModel.class);
        touchCountViewModel = new ViewModelProvider(this, viewModelFactory).get(TouchCountViewModel.class);
    }

    @Override
    protected void initAdapters() {
        ManufacturerAdapter nvAdapter = new ManufacturerAdapter(dataBindingComponent,
                manufacturer -> navigationController.navigateToSubCategoryActivity(ManufacturerListFragment.this.getActivity(), manufacturer.id, manufacturer.name), this);

        this.adapter = new AutoClearedValue<>(this, nvAdapter);
        binding.get().categoryList.setAdapter(nvAdapter);
    }

    @Override
    protected void initData() {
        loadCategory();
    }

    //region Private Methods

    private void loadCategory() {

        // Load Category List

        itemManufacturerViewModel.manufacturerParameterHolder.cityId = selectedCityId;

        itemManufacturerViewModel.setManufacturerListObj(String.valueOf(Config.LIST_MANUFACTURER_COUNT), String.valueOf(itemManufacturerViewModel.offset));

        LiveData<Resource<List<Manufacturer>>> news = itemManufacturerViewModel.getManufacturerListData();

        if (news != null) {

            news.observe(this, listResource -> {
                if (listResource != null) {

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

                            itemManufacturerViewModel.setLoadingState(false);


                            break;

                        case ERROR:
                            // Error State

                            itemManufacturerViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data

                    if (itemManufacturerViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemManufacturerViewModel.forceEndLoading = true;
                    }

                }

            });
        }

        itemManufacturerViewModel.getNextPageLoadingStateData().observe(this, state -> {
            if (state != null) {
                if (state.status == Status.ERROR) {

                    itemManufacturerViewModel.setLoadingState(false);
                    itemManufacturerViewModel.forceEndLoading = true;
                }
            }
        });

        itemManufacturerViewModel.getLoadingState().observe(this, loadingState -> {

            binding.get().setLoadingMore(itemManufacturerViewModel.isLoading);

            if (loadingState != null && !loadingState) {
                binding.get().swipeRefresh.setRefreshing(false);
            }

        });

        //get touch count post method
        touchCountViewModel.getTouchCountPostData().observe(this, result -> {
            if (result != null) {
                if (result.status == Status.SUCCESS) {
                    if (ManufacturerListFragment.this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                    }

                } else if (result.status == Status.ERROR) {
                    if (ManufacturerListFragment.this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                    }
                }
            }
        });

    }

    private void replaceData(List<Manufacturer> categoryList) {

        adapter.get().replace(categoryList);
        binding.get().executePendingBindings();

    }


    @Override
    public void onDispatched() {
        if (itemManufacturerViewModel.loadingDirection == Utils.LoadingDirection.top) {

            if (binding.get().categoryList != null) {

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        binding.get().categoryList.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
    }
}
