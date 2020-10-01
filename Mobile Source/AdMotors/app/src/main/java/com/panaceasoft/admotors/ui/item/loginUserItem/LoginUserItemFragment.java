package com.panaceasoft.admotors.ui.item.loginUserItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentLoginUserItemBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.item.adapter.ItemVerticalListAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.item.ItemViewModel;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;

import java.util.List;

public class LoginUserItemFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private ItemViewModel itemViewModel;

    @VisibleForTesting
    private AutoClearedValue<FragmentLoginUserItemBinding> binding;
    private AutoClearedValue<ItemVerticalListAdapter> userItemAdapter;

    //endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLoginUserItemBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_user_item, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());

        return binding.get().getRoot();
    }

    @Override
    public void onDispatched() {
        if (itemViewModel.loadingDirection == Utils.LoadingDirection.top) {

            if (binding.get().loginUserItemRecycler != null) {

                GridLayoutManager layoutManager = (GridLayoutManager)
                        binding.get().loginUserItemRecycler.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
    }

    @Override
    protected void initUIAndActions() {

        binding.get().loginUserItemRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager layoutManager = (GridLayoutManager)
                        recyclerView.getLayoutManager();

                if (layoutManager != null) {

                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();

                    if (lastPosition == userItemAdapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !itemViewModel.forceEndLoading) {

                            if (connectivity.isConnected()) {
                                itemViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                                int limit = Config.ITEM_COUNT;
                                itemViewModel.offset = itemViewModel.offset + limit;
                                itemViewModel.setNextPageItemListByKeyObj(String.valueOf(Config.ITEM_COUNT), String.valueOf(itemViewModel.offset), Utils.checkUserId(loginUserId),itemViewModel.holder);
                            }
                        }
                    }
                }
            }
        });
        binding.get().swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.view__primary_line));
        binding.get().swipeRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.global__primary));
        binding.get().swipeRefresh.setOnRefreshListener(() -> {

            itemViewModel.loadingDirection = Utils.LoadingDirection.top;

            // reset productViewModel.offset
            itemViewModel.offset = 0;

            // reset productViewModel.forceEndLoading
            itemViewModel.forceEndLoading = false;

            // update live data
            itemViewModel.setItemListByKeyObj(Utils.checkUserId(loginUserId), String.valueOf(Config.ITEM_COUNT), String.valueOf(itemViewModel.offset), itemViewModel.holder);

        });
    }

    @Override
    protected void initViewModels() {
        itemViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemViewModel.class);
    }

    @Override
    protected void initAdapters() {
        ItemVerticalListAdapter userItemAdapter = new ItemVerticalListAdapter(dataBindingComponent,
                new ItemVerticalListAdapter.NewsClickCallback() {
                    @Override
                    public void onClick(Item item) {
                        navigationController.navigateToItemDetailActivity(LoginUserItemFragment.this.getActivity(), item.id, item.title);
                    }
                },this);
        this.userItemAdapter = new AutoClearedValue<>(this, userItemAdapter);
        binding.get().loginUserItemRecycler.setAdapter(userItemAdapter);
    }

    @Override
    protected void initData() {
        String userId = "";
        try {
            if (getActivity() != null) {
                if (getActivity().getIntent() != null) {
                    if (getActivity().getIntent().getExtras() != null) {
                        userId = getActivity().getIntent().getExtras().getString(Constants.USER_ID);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        itemViewModel.holder.userId = userId;

        //Item
        itemViewModel.setItemListByKeyObj(Utils.checkUserId(loginUserId), String.valueOf(Config.ITEM_COUNT),  String.valueOf(itemViewModel.offset), itemViewModel.holder);

        LiveData<Resource<List<Item>>> itemList = itemViewModel.getItemListByKeyData();

        if (itemList != null) {

            itemList.observe(this, listResource -> {
                if (listResource != null) {

                    switch (listResource.status) {
                        case LOADING:
                            // Loading State
                            // Data are from Local DB

                            if (listResource.data != null) {
                                //fadeIn Animation
                                fadeIn(binding.get().getRoot());

                                // Update the data
                                itemReplaceData(listResource.data);

                            }

                            break;

                        case SUCCESS:
                            // Success State
                            // Data are from Server

                            if (listResource.data != null) {
                                // Update the data
                                itemReplaceData(listResource.data);
                            }

                            itemViewModel.setLoadingState(false);


                            break;

                        case ERROR:
                            // Error State

                            itemViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data

                    if (itemViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        itemViewModel.forceEndLoading = true;
                    }

                }

            });
        }


        itemViewModel.getNextPageItemListByKeyData().observe(this, state -> {
            if (state != null) {
                if (state.status == Status.ERROR) {

                    itemViewModel.setLoadingState(false);//hide
                    itemViewModel.forceEndLoading = true;//stop

                }
            }
        });

        itemViewModel.getLoadingState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loadingState) {
                binding.get().setLoadingMore(itemViewModel.isLoading);

                if (loadingState != null && !loadingState) {
                    binding.get().swipeRefresh.setRefreshing(false);
                }
            }
        });

    }

    private void itemReplaceData(List<Item> itemList) {
        userItemAdapter.get().replace(itemList);
        binding.get().executePendingBindings();
    }
}
