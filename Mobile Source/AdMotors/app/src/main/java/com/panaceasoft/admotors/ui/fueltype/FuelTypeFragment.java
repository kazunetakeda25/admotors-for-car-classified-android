package com.panaceasoft.admotors.ui.fueltype;

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
import com.panaceasoft.admotors.databinding.FragmentFuelTypeBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.fueltype.FuelTypeViewModel;
import com.panaceasoft.admotors.viewobject.FuelType;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

public class FuelTypeFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private FuelTypeViewModel fuelTypeViewModel;
    public String fuelTypeId;

    @VisibleForTesting
    private AutoClearedValue<FragmentFuelTypeBinding> binding;
    private AutoClearedValue<FuelTypeAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentFuelTypeBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_fuel_type, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.fuelTypeId = intent.getStringExtra(Constants.FUEL_TYPE_ID);
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
            this.fuelTypeId = "";

            initAdapters();

            initData();

            navigationController.navigateBackToFuelTypeFragment(this.getActivity(), this.fuelTypeId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        fuelTypeViewModel = new ViewModelProvider(this, viewModelFactory).get(FuelTypeViewModel.class);
    }

    @Override
    protected void initAdapters() {

        FuelTypeAdapter nvadapter = new FuelTypeAdapter(dataBindingComponent,
                (fuelType, id) -> {

                    navigationController.navigateBackToFuelTypeFragment(this.getActivity(), fuelType.id, fuelType.fuelName);

                    if (getActivity() != null) {
                        this.getActivity().finish();
                    }
                }, this.fuelTypeId);
        this.adapter = new AutoClearedValue<>(this, nvadapter);
        binding.get().fuelTypeRecyclerView.setAdapter(nvadapter);

    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load FuelType List
        fuelTypeViewModel.setFuelTypeListObj("", String.valueOf(fuelTypeViewModel.offset));

        LiveData<Resource<List<FuelType>>> news = fuelTypeViewModel.getFuelTypeListData();

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

                            fuelTypeViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            fuelTypeViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (fuelTypeViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        fuelTypeViewModel.forceEndLoading = true;
                    }

                }

            });
        }
    }

    private void replaceData(List<FuelType> colorList) {

        adapter.get().replace(colorList);
        binding.get().executePendingBindings();

    }
}


