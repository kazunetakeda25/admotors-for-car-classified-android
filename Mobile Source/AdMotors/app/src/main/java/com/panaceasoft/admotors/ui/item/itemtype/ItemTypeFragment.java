package com.panaceasoft.admotors.ui.item.itemtype;


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
import com.panaceasoft.admotors.databinding.FragmentItemTypeBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.item.itemtype.adapter.ItemTypeAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.itemtype.ItemTypeViewModel;
import com.panaceasoft.admotors.viewobject.ItemType;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemTypeFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemTypeViewModel itemTypeViewModel;
    public String itemTypeId;

    @VisibleForTesting
    private AutoClearedValue<FragmentItemTypeBinding> binding;
    private AutoClearedValue<ItemTypeAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentItemTypeBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_type, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.itemTypeId = intent.getStringExtra(Constants.ITEM_TYPE_ID);
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
            this.itemTypeId = "";

            initAdapters();

            initData();

            navigationController.navigateBackToItemTypeFragment(this.getActivity(), this.itemTypeId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        itemTypeViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemTypeViewModel.class);
    }

    @Override
    protected void initAdapters() {

        ItemTypeAdapter nvadapter = new ItemTypeAdapter(dataBindingComponent,
                (itemType, id) -> {

                    navigationController.navigateBackToItemTypeFragment(ItemTypeFragment.this.getActivity(), itemType.id, itemType.name);

                    if (ItemTypeFragment.this.getActivity() != null) {
                        ItemTypeFragment.this.getActivity().finish();
                    }
                }, this.itemTypeId);
        this.adapter = new AutoClearedValue<>(this, nvadapter);
        binding.get().searchCategoryRecyclerView.setAdapter(nvadapter);

    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load Category List
        itemTypeViewModel.manufacturerParameterHolder.cityId = selectedCityId;

        itemTypeViewModel.setItemTypeListObj("", String.valueOf(itemTypeViewModel.offset));

        LiveData<Resource<List<ItemType>>> news = itemTypeViewModel.getItemTypeListData();

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

                            itemTypeViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            itemTypeViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (itemTypeViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemTypeViewModel.forceEndLoading = true;
                    }

                }

            });
        }

//        itemTypeViewModel.getNextPageLoadingStateData().observe(this, state -> {
//            if (state != null) {
//                if (state.status == Status.ERROR) {
//                    Utils.psLog("Next Page State : " + state.data);
//
//                    itemTypeViewModel.setLoadingState(false);
//                    itemTypeViewModel.forceEndLoading = true;
//                }
//            }
//        });

    }

    private void replaceData(List<ItemType> categoryList) {

        adapter.get().replace(categoryList);
        binding.get().executePendingBindings();

    }
}