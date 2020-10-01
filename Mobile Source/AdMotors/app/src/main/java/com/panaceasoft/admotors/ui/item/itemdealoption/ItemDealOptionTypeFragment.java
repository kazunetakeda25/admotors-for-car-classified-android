package com.panaceasoft.admotors.ui.item.itemdealoption;


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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.ads.AdRequest;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentItemDealOptionTypeBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.itemdealoption.ItemDealOptionViewModel;
import com.panaceasoft.admotors.viewobject.ItemDealOption;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDealOptionTypeFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemDealOptionViewModel itemDealOptionViewModel;
    public String priceTypeId;

    @VisibleForTesting
    private AutoClearedValue<FragmentItemDealOptionTypeBinding> binding;
    private AutoClearedValue<ItemDealOptionAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentItemDealOptionTypeBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_deal_option_type, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.priceTypeId = intent.getStringExtra(Constants.ITEM_OPTION_TYPE_ID);
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
            this.priceTypeId = "";

            initAdapters();

            initData();

            navigationController.navigateBackToItemDealOptionTypeFragment(this.getActivity(), this.priceTypeId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        itemDealOptionViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemDealOptionViewModel.class);
    }

    @Override
    protected void initAdapters() {

        ItemDealOptionAdapter nvadapter = new ItemDealOptionAdapter(dataBindingComponent,
                (category, id) -> {

                    navigationController.navigateBackToItemDealOptionTypeFragment(this.getActivity(), category.id, category.name);

                    if (getActivity() != null) {
                        this.getActivity().finish();
                    }
                }, this.priceTypeId);
        this.adapter = new AutoClearedValue<>(this, nvadapter);
        binding.get().searchCategoryRecyclerView.setAdapter(nvadapter);

    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load Category List
        itemDealOptionViewModel.manufacturerParameterHolder.cityId = selectedCityId;

        itemDealOptionViewModel.setItemDealOptionListObj("", String.valueOf(itemDealOptionViewModel.offset));

        LiveData<Resource<List<ItemDealOption>>> news = itemDealOptionViewModel.getItemDealOptionListData();

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

                            itemDealOptionViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            itemDealOptionViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (itemDealOptionViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemDealOptionViewModel.forceEndLoading = true;
                    }

                }

            });
        }

//        itemDealOptionViewModel.getNextPageLoadingStateData().observe(this, state -> {
//            if (state != null) {
//                if (state.status == Status.ERROR) {
//                    Utils.psLog("Next Page State : " + state.data);
//
//                    itemDealOptionViewModel.setLoadingState(false);
//                    itemDealOptionViewModel.forceEndLoading = true;
//                }
//            }
//        });

    }

    private void replaceData(List<ItemDealOption> categoryList) {

        adapter.get().replace(categoryList);
        binding.get().executePendingBindings();

    }
}