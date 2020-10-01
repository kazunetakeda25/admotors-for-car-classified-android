package com.panaceasoft.admotors.ui.chathistory;


import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentBuyerBinding;
import com.panaceasoft.admotors.ui.chathistory.adapter.BuyerChatHistoryListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.chathistory.ChatHistoryViewModel;
import com.panaceasoft.admotors.viewobject.ChatHistory;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyerFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ChatHistoryViewModel chatHistoryViewModel;
    public String manufacturerId;

    @VisibleForTesting
    private AutoClearedValue<FragmentBuyerBinding> binding;
    private AutoClearedValue<BuyerChatHistoryListAdapter> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentBuyerBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_buyer, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            this.manufacturerId = intent.getStringExtra(Constants.MANUFACTURER_ID);
        }
        return binding.get().getRoot();
    }

    @Override
    protected void initUIAndActions() {
        binding.get().buyerList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();

                if (layoutManager != null) {

                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();
                    if (lastPosition == adapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !chatHistoryViewModel.forceEndLoading) {

                            if (connectivity.isConnected()) {

                                chatHistoryViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                                int limit = Config.LIST_MANUFACTURER_COUNT;
                                chatHistoryViewModel.offset = chatHistoryViewModel.offset + limit;

                                chatHistoryViewModel.setNextPageChatHistoryFromSellerObj(loginUserId, chatHistoryViewModel.holder.getBuyerHistoryList(), String.valueOf(Config.CHAT_HISTORY_COUNT), String.valueOf(chatHistoryViewModel.offset));
                            }
                        }
                    }
                }
            }
        });

        binding.get().swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.view__primary_line));
        binding.get().swipeRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.global__primary));
        binding.get().swipeRefresh.setOnRefreshListener(() -> {

            chatHistoryViewModel.loadingDirection = Utils.LoadingDirection.top;

            // reset productViewModel.offset
            chatHistoryViewModel.offset = 0;

            // reset productViewModel.forceEndLoading
            chatHistoryViewModel.forceEndLoading = false;

            // update live data
            if (!loginUserId.isEmpty()) {
                chatHistoryViewModel.setChatHistoryListObj(loginUserId, chatHistoryViewModel.holder.getBuyerHistoryList(), String.valueOf(Config.CHAT_HISTORY_COUNT), String.valueOf(chatHistoryViewModel.offset));
            }
        });
    }

    @Override
    protected void initViewModels() {

        chatHistoryViewModel = new ViewModelProvider(this, viewModelFactory).get(ChatHistoryViewModel.class);

    }

    @Override
    protected void initAdapters() {

        BuyerChatHistoryListAdapter buyerChatHistoryListAdapter = new BuyerChatHistoryListAdapter(dataBindingComponent,
                (chatHistoryFromBuyer, id) -> navigationController.navigateToChatActivity(BuyerFragment.this.getActivity(),
                        chatHistoryFromBuyer.itemId,
                        chatHistoryFromBuyer.buyerUserId,
                        chatHistoryFromBuyer.buyerUser.userName,
                        chatHistoryFromBuyer.item.defaultPhoto.imgPath,
                        chatHistoryFromBuyer.item.title,
                        chatHistoryFromBuyer.item.itemCurrency.currencySymbol,
                        chatHistoryFromBuyer.item.price,
                        chatHistoryFromBuyer.item.itemCondition.name,
                        Constants.CHAT_FROM_BUYER,
                        chatHistoryFromBuyer.buyerUser.userProfilePhoto,
                        chatHistoryFromBuyer.item.priceUnit,
                        Constants.REQUEST_CODE__BUYER_CHAT_FRAGMENT
                ), this);
        this.adapter = new AutoClearedValue<>(this, buyerChatHistoryListAdapter);
        binding.get().buyerList.setAdapter(buyerChatHistoryListAdapter);

    }

    @Override
    protected void initData() {
        loadCategory();
    }

    private void loadCategory() {

        // Load Category List
        if (!loginUserId.isEmpty()) {

            chatHistoryViewModel.setChatHistoryListObj(loginUserId, chatHistoryViewModel.holder.getBuyerHistoryList(), String.valueOf(Config.CHAT_HISTORY_COUNT), String.valueOf(chatHistoryViewModel.offset));
        }

        LiveData<Resource<List<ChatHistory>>> news = chatHistoryViewModel.getChatHistoryListData();

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

                            chatHistoryViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            chatHistoryViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (chatHistoryViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        chatHistoryViewModel.forceEndLoading = true;
                    }

                }

            });
        }

        chatHistoryViewModel.getNextPageChatHistoryListData().observe(this, state -> {
            if (state != null) {
                if (state.status == Status.ERROR) {
                    Utils.psLog("Next Page State : " + state.data);

                    chatHistoryViewModel.setLoadingState(false);
                    chatHistoryViewModel.forceEndLoading = true;
                }
            }
        });

        chatHistoryViewModel.getLoadingState().observe(this, loadingState -> {

            binding.get().setLoadingMore(chatHistoryViewModel.isLoading);

            if (loadingState != null && !loadingState) {
                binding.get().swipeRefresh.setRefreshing(false);
            }

        });

    }

    private void replaceData(List<ChatHistory> chatHistoryList) {

        adapter.get().replace(chatHistoryList);
        binding.get().executePendingBindings();

    }

    @Override
    public void onDispatched() {
        if (chatHistoryViewModel.loadingDirection == Utils.LoadingDirection.bottom) {

            if (binding.get().buyerList != null) {

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        binding.get().buyerList.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE__BUYER_CHAT_FRAGMENT) {

            chatHistoryViewModel.loadingDirection = Utils.LoadingDirection.top;

            // reset productViewModel.offset
            chatHistoryViewModel.offset = 0;

            // reset productViewModel.forceEndLoading
            chatHistoryViewModel.forceEndLoading = false;

            // update live data
            if (!loginUserId.isEmpty()) {
                chatHistoryViewModel.setChatHistoryListObj(loginUserId, chatHistoryViewModel.holder.getBuyerHistoryList(), String.valueOf(Config.CHAT_HISTORY_COUNT), String.valueOf(chatHistoryViewModel.offset));
            }

        }

    }

}

