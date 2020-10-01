package com.panaceasoft.admotors.ui.itemcolor;

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
import com.panaceasoft.admotors.databinding.FragmentItemColorBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.itemcolor.ItemColorViewModel;
import com.panaceasoft.admotors.viewobject.Color;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

public class ItemColorFragment  extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemColorViewModel itemColorViewModel;
    public String itemColorId;

    @VisibleForTesting
    private AutoClearedValue<FragmentItemColorBinding> binding;
    private AutoClearedValue<ItemColorAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentItemColorBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_color, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.itemColorId = intent.getStringExtra(Constants.ITEM_COLOR_ID);
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
            this.itemColorId = "";

            initAdapters();

            initData();

            navigationController.navigateBackToItemColorFragment(this.getActivity(), this.itemColorId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        itemColorViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemColorViewModel.class);
    }

    @Override
    protected void initAdapters() {

        ItemColorAdapter nvadapter = new ItemColorAdapter(dataBindingComponent,
                (color, id) -> {

                    navigationController.navigateBackToItemColorFragment(this.getActivity(), color.id, color.colorValue);

                    if (getActivity() != null) {
                        this.getActivity().finish();
                    }
                }, this.itemColorId);
        this.adapter = new AutoClearedValue<>(this, nvadapter);
        binding.get().colorRecyclerView.setAdapter(nvadapter);

    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load Color List
        itemColorViewModel.setItemColorListObj("", String.valueOf(itemColorViewModel.offset));

        LiveData<Resource<List<Color>>> news = itemColorViewModel.getItemColorListData();

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

                            itemColorViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            itemColorViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (itemColorViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemColorViewModel.forceEndLoading = true;
                    }

                }

            });
        }
    }

    private void replaceData(List<Color> colorList) {

        adapter.get().replace(colorList);
        binding.get().executePendingBindings();

    }
}

