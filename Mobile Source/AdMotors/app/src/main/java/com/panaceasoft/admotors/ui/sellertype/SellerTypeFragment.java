package com.panaceasoft.admotors.ui.sellertype;

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
import com.panaceasoft.admotors.databinding.FragmentSellerTypeBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.sellertype.SellerTypeViewModel;
import com.panaceasoft.admotors.viewobject.SellerType;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

public class SellerTypeFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private SellerTypeViewModel sellerTypeViewModel;
    public String sellerTypeId;

    @VisibleForTesting
    private AutoClearedValue<FragmentSellerTypeBinding> binding;
    private AutoClearedValue<SellerTypeAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentSellerTypeBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_seller_type, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.sellerTypeId = intent.getStringExtra(Constants.SELLER_TYPE_ID);
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
            this.sellerTypeId = "";

            initAdapters();

            initData();

            navigationController.navigateBackToSellerTypeFragment(this.getActivity(), this.sellerTypeId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        sellerTypeViewModel = new ViewModelProvider(this, viewModelFactory).get(SellerTypeViewModel.class);
    }

    @Override
    protected void initAdapters() {

        SellerTypeAdapter nvadapter = new SellerTypeAdapter(dataBindingComponent,
                (sellerType, id) -> {

                    navigationController.navigateBackToSellerTypeFragment(this.getActivity(), sellerType.id, sellerType.sellerType);

                    if (getActivity() != null) {
                        this.getActivity().finish();
                    }
                }, this.sellerTypeId);
        this.adapter = new AutoClearedValue<>(this, nvadapter);
        binding.get().sellerTypeRecyclerView.setAdapter(nvadapter);

    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load SellerType List
        sellerTypeViewModel.setSellerTypeListObj("", String.valueOf(sellerTypeViewModel.offset));

        LiveData<Resource<List<SellerType>>> news = sellerTypeViewModel.getSellerTypeListData();

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

                            sellerTypeViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            sellerTypeViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (sellerTypeViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        sellerTypeViewModel.forceEndLoading = true;
                    }

                }

            });
        }
    }

    private void replaceData(List<SellerType> sellerTypeList) {

        adapter.get().replace(sellerTypeList);
        binding.get().executePendingBindings();

    }
}




