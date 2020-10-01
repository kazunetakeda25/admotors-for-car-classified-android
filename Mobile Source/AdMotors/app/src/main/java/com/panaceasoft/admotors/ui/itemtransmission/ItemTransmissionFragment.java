package com.panaceasoft.admotors.ui.itemtransmission;

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
import com.panaceasoft.admotors.databinding.FragmentItemTransmissionBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.itemtransmission.ItemTransmissionViewModel;
import com.panaceasoft.admotors.viewobject.Transmission;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

public class ItemTransmissionFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemTransmissionViewModel transmissionViewModel;
    public String itemTransmissionId;

    @VisibleForTesting
    private AutoClearedValue<FragmentItemTransmissionBinding> binding;
    private AutoClearedValue<ItemTransmissionAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentItemTransmissionBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_transmission, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);
        
        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.itemTransmissionId = intent.getStringExtra(Constants.TRANSMISSION_ID);
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
            this.itemTransmissionId = "";

            initAdapters();

            initData();

            navigationController.navigateBackToTransmissionFragment(this.getActivity(), this.itemTransmissionId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        transmissionViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemTransmissionViewModel.class);
    }

    @Override
    protected void initAdapters() {

        ItemTransmissionAdapter nvadapter = new ItemTransmissionAdapter(dataBindingComponent,
                (transmission, id) -> {

                    navigationController.navigateBackToTransmissionFragment(this.getActivity(), transmission.id, transmission.name);

                    if (getActivity() != null) {
                        this.getActivity().finish();
                    }
                }, this.itemTransmissionId);
        this.adapter = new AutoClearedValue<>(this, nvadapter);
        binding.get().transmissionRecyclerView.setAdapter(nvadapter);

    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load Transmission List
        transmissionViewModel.setTransmissionListObj("", String.valueOf(transmissionViewModel.offset));

        LiveData<Resource<List<Transmission>>> news = transmissionViewModel.getTransmissionListData();

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

                            transmissionViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            transmissionViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (transmissionViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        transmissionViewModel.forceEndLoading = true;
                    }

                }

            });
        }
    }

    private void replaceData(List<Transmission> transmissionList) {

        adapter.get().replace(transmissionList);
        binding.get().executePendingBindings();

    }
}
