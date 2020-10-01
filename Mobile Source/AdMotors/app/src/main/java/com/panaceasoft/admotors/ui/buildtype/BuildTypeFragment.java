package com.panaceasoft.admotors.ui.buildtype;

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
import com.panaceasoft.admotors.databinding.FragmentBuildTypeBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.buildtype.BuildTypeViewModel;
import com.panaceasoft.admotors.viewobject.BuildType;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

public class BuildTypeFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private BuildTypeViewModel buildTypeViewModel;
    public String buildTypeId;

    @VisibleForTesting
    private AutoClearedValue<FragmentBuildTypeBinding> binding;
    private AutoClearedValue<BuildTypeAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentBuildTypeBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_build_type, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        setHasOptionsMenu(true);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.buildTypeId = intent.getStringExtra(Constants.BUILD_TYPE_ID);
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
            this.buildTypeId = "";

            initAdapters();

            initData();

            navigationController.navigateBackToBuildTypeFragment(this.getActivity(), this.buildTypeId, "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViewModels() {

        buildTypeViewModel = new ViewModelProvider(this, viewModelFactory).get(BuildTypeViewModel.class);
    }

    @Override
    protected void initAdapters() {

        BuildTypeAdapter nvadapter = new BuildTypeAdapter(dataBindingComponent,
                (buildType, id) -> {

                    navigationController.navigateBackToBuildTypeFragment(this.getActivity(), buildType.id, buildType.carType);

                    if (getActivity() != null) {
                        this.getActivity().finish();
                    }
                }, this.buildTypeId);
        this.adapter = new AutoClearedValue<>(this, nvadapter);
        binding.get().buildTypeRecyclerView.setAdapter(nvadapter);

    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load BuildType List
        buildTypeViewModel.setBuildTypeListObj("", String.valueOf(buildTypeViewModel.offset));

        LiveData<Resource<List<BuildType>>> news = buildTypeViewModel.getBuildTypeListData();

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

                            buildTypeViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            buildTypeViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (buildTypeViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        buildTypeViewModel.forceEndLoading = true;
                    }

                }

            });
        }
    }

    private void replaceData(List<BuildType> buildTypeList) {

        adapter.get().replace(buildTypeList);
        binding.get().executePendingBindings();

    }
}



