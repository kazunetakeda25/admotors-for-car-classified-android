package com.panaceasoft.admotors.ui.item.itemcondition;


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
import com.panaceasoft.admotors.databinding.FragmentItemConditionBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.itemcondition.ItemConditionViewModel;
import com.panaceasoft.admotors.viewobject.ItemCondition;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemConditionFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemConditionViewModel itemConditionViewModel;
    public String itemConditionId;

    @VisibleForTesting
    private AutoClearedValue<FragmentItemConditionBinding> binding;
    private AutoClearedValue<ItemConditionAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentItemConditionBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_condition, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.itemConditionId = intent.getStringExtra(Constants.ITEM_CONDITION_TYPE_ID);
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
            this.itemConditionId = "";

            initAdapters();

            initData();

            navigationController.navigateBackToItemConditionFragment(this.getActivity(), this.itemConditionId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        itemConditionViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemConditionViewModel.class);
    }

    @Override
    protected void initAdapters() {

        ItemConditionAdapter nvadapter = new ItemConditionAdapter(dataBindingComponent,
                (itemCondition, id) -> {

                    navigationController.navigateBackToItemConditionFragment(ItemConditionFragment.this.getActivity(), itemCondition.id, itemCondition.name);

                    if (ItemConditionFragment.this.getActivity() != null) {
                        ItemConditionFragment.this.getActivity().finish();
                    }
                }, this.itemConditionId);
        this.adapter = new AutoClearedValue<>(this, nvadapter);
        binding.get().searchCategoryRecyclerView.setAdapter(nvadapter);

    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load Category List
        itemConditionViewModel.manufacturerParameterHolder.cityId = selectedCityId;

        itemConditionViewModel.setItemConditionListObj("", String.valueOf(itemConditionViewModel.offset));

        LiveData<Resource<List<ItemCondition>>> news = itemConditionViewModel.getItemConditionListData();

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

                            itemConditionViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            itemConditionViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (itemConditionViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemConditionViewModel.forceEndLoading = true;
                    }

                }

            });
        }

//        itemConditionViewModel.getNextPageLoadingStateData().observe(this, state -> {
//            if (state != null) {
//                if (state.status == Status.ERROR) {
//                    Utils.psLog("Next Page State : " + state.data);
//
//                    itemConditionViewModel.setLoadingState(false);
//                    itemConditionViewModel.forceEndLoading = true;
//                }
//            }
//        });

    }

    private void replaceData(List<ItemCondition> categoryList) {

        adapter.get().replace(categoryList);
        binding.get().executePendingBindings();

    }
}