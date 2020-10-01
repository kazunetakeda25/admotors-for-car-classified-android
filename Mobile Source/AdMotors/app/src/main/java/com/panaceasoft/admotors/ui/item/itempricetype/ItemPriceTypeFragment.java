package com.panaceasoft.admotors.ui.item.itempricetype;


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
import com.panaceasoft.admotors.databinding.FragmentItemPriceTypeBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.itempricetype.ItemPriceTypeViewModel;
import com.panaceasoft.admotors.viewobject.ItemPriceType;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemPriceTypeFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemPriceTypeViewModel itemPriceTypeViewModel;
    public String priceTypeId;

    @VisibleForTesting
    private AutoClearedValue<FragmentItemPriceTypeBinding> binding;
    private AutoClearedValue<ItemPriceTypeAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentItemPriceTypeBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_price_type, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.priceTypeId = intent.getStringExtra(Constants.ITEM_PRICE_TYPE_ID);
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

            navigationController.navigateBackToItemPriceTypeFragment(this.getActivity(), this.priceTypeId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        itemPriceTypeViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemPriceTypeViewModel.class);
    }

    @Override
    protected void initAdapters() {

        ItemPriceTypeAdapter nvadapter = new ItemPriceTypeAdapter(dataBindingComponent,
                (category, id) -> {

                    navigationController.navigateBackToItemPriceTypeFragment(this.getActivity(), category.id, category.name);

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
        itemPriceTypeViewModel.manufacturerParameterHolder.cityId = selectedCityId;

        itemPriceTypeViewModel.setItemPriceTypeListObj("", String.valueOf(itemPriceTypeViewModel.offset));

        LiveData<Resource<List<ItemPriceType>>> news = itemPriceTypeViewModel.getItemPriceTypeListData();

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

                            itemPriceTypeViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            itemPriceTypeViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (itemPriceTypeViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemPriceTypeViewModel.forceEndLoading = true;
                    }

                }

            });
        }

//        itemPriceTypeViewModel.getNextPageLoadingStateData().observe(this, state -> {
//            if (state != null) {
//                if (state.status == Status.ERROR) {
//                    Utils.psLog("Next Page State : " + state.data);
//
//                    itemPriceTypeViewModel.setLoadingState(false);
//                    itemPriceTypeViewModel.forceEndLoading = true;
//                }
//            }
//        });

    }

    private void replaceData(List<ItemPriceType> categoryList) {

        adapter.get().replace(categoryList);
        binding.get().executePendingBindings();

    }
}