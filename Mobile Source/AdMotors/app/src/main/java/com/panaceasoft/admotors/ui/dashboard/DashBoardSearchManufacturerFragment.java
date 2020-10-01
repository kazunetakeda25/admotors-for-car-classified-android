package com.panaceasoft.admotors.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.ads.AdRequest;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentSearchManufacturerAndModelBinding;
import com.panaceasoft.admotors.ui.manufacturer.adapter.SearchManufacturerAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.itemmanufacturer.ItemManufacturerViewModel;
import com.panaceasoft.admotors.viewobject.Manufacturer;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;

import java.util.List;

public class DashBoardSearchManufacturerFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemManufacturerViewModel itemManufacturerViewModel;
    public String manufacturerId;

    @VisibleForTesting
    private AutoClearedValue<FragmentSearchManufacturerAndModelBinding> binding;
    private AutoClearedValue<SearchManufacturerAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentSearchManufacturerAndModelBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_manufacturer_and_model, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);
        
        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.manufacturerId = intent.getStringExtra(Constants.MANUFACTURER_ID);
        }

        if (Config.SHOW_ADMOB && connectivity.isConnected()) {
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            binding.get().adView.loadAd(adRequest);
        } else {
            binding.get().adView.setVisibility(View.GONE);
        }
        return binding.get().getRoot();
    }

    @Override
    protected void initUIAndActions() {

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.clear_button, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.clear) {
            this.manufacturerId = "";

            initAdapters();

            initData();

            navigationController.navigateBackToSearchFragment(this.getActivity(), this.manufacturerId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        itemManufacturerViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemManufacturerViewModel.class);
    }

    @Override
    protected void initAdapters() {

        SearchManufacturerAdapter nvadapter = new SearchManufacturerAdapter(dataBindingComponent,
                (manufacturer, id) -> {
                    navigationController.navigateBackToSearchFragment(DashBoardSearchManufacturerFragment.this.getActivity(), manufacturer.id, manufacturer.name);
                    if (DashBoardSearchManufacturerFragment.this.getActivity() != null) {
                        DashBoardSearchManufacturerFragment.this.getActivity().finish();
                    }
                }, this.manufacturerId);
        this.adapter = new AutoClearedValue<>(this, nvadapter);
        binding.get().searchManufacturerRecyclerView.setAdapter(nvadapter);

    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load Category List
        itemManufacturerViewModel.manufacturerParameterHolder.cityId = selectedCityId;

        itemManufacturerViewModel.setManufacturerListObj("", String.valueOf(itemManufacturerViewModel.offset));

        LiveData<Resource<List<Manufacturer>>> news = itemManufacturerViewModel.getManufacturerListData();

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
                    Utils.psLog("Empty Data");

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
                    Utils.psLog("Next Page State : " + state.data);

                    itemManufacturerViewModel.setLoadingState(false);
                    itemManufacturerViewModel.forceEndLoading = true;
                }
            }
        });

    }

    private void replaceData(List<Manufacturer> manufacturerList) {

        adapter.get().replace(manufacturerList);
        binding.get().executePendingBindings();

    }
}

