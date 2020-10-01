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
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.model.adapter.SearchModelAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.viewmodel.itemmodel.ItemModelViewModel;
import com.panaceasoft.admotors.viewobject.Model;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;

import java.util.List;

public class DashBoardSearchModelFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemModelViewModel itemModelViewModel;
    private String manufacturerId;
    private String modelId;

    @VisibleForTesting
    private AutoClearedValue<FragmentSearchManufacturerAndModelBinding> binding;
    private AutoClearedValue<SearchModelAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentSearchManufacturerAndModelBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_manufacturer_and_model, container, false, dataBindingComponent);
        
        binding = new AutoClearedValue<>(this, dataBinding);

        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.manufacturerId = intent.getStringExtra(Constants.MANUFACTURER_ID);
            this.modelId = intent.getStringExtra(Constants.MODEL_ID);
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
            this.modelId = "";

            initAdapters();

            initData();

            navigationController.navigateBackToSearchFragmentFromSubCategory(this.getActivity(), this.modelId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        itemModelViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemModelViewModel.class);
    }

    @Override
    protected void initAdapters() {

        SearchModelAdapter nvadapter = new SearchModelAdapter(dataBindingComponent,
                model -> {

                    navigationController.navigateBackToSearchFragmentFromSubCategory(this.getActivity(), model.id, model.name);

                    if (getActivity() != null) {
                        this.getActivity().finish();
                    }

                }, this.modelId);


        this.adapter = new AutoClearedValue<>(this, nvadapter);
        binding.get().searchManufacturerRecyclerView.setAdapter(nvadapter);
    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load Model List
        itemModelViewModel.setModelListByManufacturerIdObj(this.manufacturerId, "", String.valueOf(itemModelViewModel.offset));

        LiveData<Resource<List<Model>>> news = itemModelViewModel.getModelListByManufacturerIdData();

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

                    itemModelViewModel.setLoadingState(false);
                    itemModelViewModel.forceEndLoading = true;
                }
            }
        });
    }

    private void replaceData(List<Model> modelList) {

        adapter.get().replace(modelList);
        binding.get().executePendingBindings();

    }
}
