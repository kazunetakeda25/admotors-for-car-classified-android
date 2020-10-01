package com.panaceasoft.admotors.ui.item.itemfromfollower;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentItemFromFollowerListBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.item.adapter.ItemVerticalListAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.itemfromfollower.ItemFromFollowerViewModel;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFromFollowerListFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemFromFollowerViewModel itemFromFollowerViewModel;

    @VisibleForTesting
    private AutoClearedValue<FragmentItemFromFollowerListBinding> binding;
    private AutoClearedValue<ItemVerticalListAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentItemFromFollowerListBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_from_follower_list, container, false, dataBindingComponent);


        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());

        return binding.get().getRoot();

    }

    @Override
    protected void initUIAndActions() {
        binding.get().subcategoryList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager layoutManager = (GridLayoutManager)
                        recyclerView.getLayoutManager();

                if (layoutManager != null) {

                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();
                    if (lastPosition == adapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !itemFromFollowerViewModel.forceEndLoading) {

                            if (connectivity.isConnected()) {

                                itemFromFollowerViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                                int limit = Config.LIST_MANUFACTURER_COUNT;
                                itemFromFollowerViewModel.offset = itemFromFollowerViewModel.offset + limit;

                                itemFromFollowerViewModel.setNextPageItemFromFollowerListObj(loginUserId, String.valueOf(Config.ITEM_COUNT), String.valueOf(itemFromFollowerViewModel.offset));
                            }
                        }
                    }
                }
            }
        });

        binding.get().swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.view__primary_line));
        binding.get().swipeRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.global__primary));
        binding.get().swipeRefresh.setOnRefreshListener(() -> {

            itemFromFollowerViewModel.loadingDirection = Utils.LoadingDirection.top;

            // reset productViewModel.offset
            itemFromFollowerViewModel.offset = 0;

            // reset productViewModel.forceEndLoading
            itemFromFollowerViewModel.forceEndLoading = false;

            // update live data
            itemFromFollowerViewModel.setItemFromFollowerListObj(loginUserId, String.valueOf(Config.ITEM_COUNT), String.valueOf(itemFromFollowerViewModel.offset));

        });
    }


    @Override
    protected void initViewModels() {
        // ViewModel need to get from new ViewModelProviders
        itemFromFollowerViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemFromFollowerViewModel.class);
    }

    @Override
    protected void initAdapters() {
        ItemVerticalListAdapter nvAdapter = new ItemVerticalListAdapter(dataBindingComponent, item -> {

            navigationController.navigateToItemDetailActivity(getActivity(), item.id, item.title);

        }, this);
        this.adapter = new AutoClearedValue<>(this, nvAdapter);
        binding.get().subcategoryList.setAdapter(nvAdapter);
    }

    @Override
    protected void initData() {
        loadNews();
    }

    private void loadNews() {

        // Load Sub Category
        itemFromFollowerViewModel.setItemFromFollowerListObj(loginUserId, String.valueOf(Config.ITEM_COUNT), String.valueOf(itemFromFollowerViewModel.offset));

        LiveData<Resource<List<Item>>> news = itemFromFollowerViewModel.getItemFromFollowerListData();

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

                            itemFromFollowerViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            itemFromFollowerViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (itemFromFollowerViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemFromFollowerViewModel.forceEndLoading = true;
                    }

                }

            });
        }

        itemFromFollowerViewModel.getNextPageItemFromFollowerListData().observe(this, state -> {
            if (state != null) {
                if (state.status == Status.ERROR) {
                    Utils.psLog("Next Page State : " + state.data);

                    itemFromFollowerViewModel.setLoadingState(false);
                    itemFromFollowerViewModel.forceEndLoading = true;
                }
            }
        });

        itemFromFollowerViewModel.getLoadingState().observe(this, loadingState -> {

            binding.get().setLoadingMore(itemFromFollowerViewModel.isLoading);

            if (loadingState != null && !loadingState) {
                binding.get().swipeRefresh.setRefreshing(false);
            }

        });

    }


    private void replaceData(List<Item> newsList) {

        adapter.get().replace(newsList);
        binding.get().executePendingBindings();

    }

    @Override
    public void onDispatched() {

        if (itemFromFollowerViewModel.loadingDirection == Utils.LoadingDirection.top) {

            if (binding.get().subcategoryList != null) {

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        binding.get().subcategoryList.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
    }

    //endregion

}