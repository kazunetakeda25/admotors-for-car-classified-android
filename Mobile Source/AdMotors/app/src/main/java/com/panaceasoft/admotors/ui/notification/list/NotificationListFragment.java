package com.panaceasoft.admotors.ui.notification.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentNotificationListBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.notification.list.adapter.NotificationListAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.notification.NotificationsViewModel;
import com.panaceasoft.admotors.viewobject.Noti;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;

import java.util.List;

public class NotificationListFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private NotificationsViewModel notificationListViewModel;
    public NotificationListAdapter nvAdapter;

    @VisibleForTesting
    private AutoClearedValue<FragmentNotificationListBinding> binding;
    private AutoClearedValue<NotificationListAdapter> adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentNotificationListBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification_list, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());

        return binding.get().getRoot();

    }

    @Override
    public void onDispatched() {
        if (notificationListViewModel.loadingDirection == Utils.LoadingDirection.top) {

            if (binding.get().notificationList != null) {

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        binding.get().notificationList.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
    }

    @Override
    protected void initUIAndActions() {

        if (Config.SHOW_ADMOB && connectivity.isConnected()) {
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            binding.get().adView.loadAd(adRequest);
        } else {
            binding.get().adView.setVisibility(View.GONE);
        }

        binding.get().notificationList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();
                if (layoutManager != null) {
                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();
                    if (lastPosition == adapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !notificationListViewModel.forceEndLoading) {

                            if (connectivity.isConnected()) {

                                notificationListViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                                int limit = Config.NOTI_LIST_COUNT;
                                notificationListViewModel.offset = notificationListViewModel.offset + limit;

                                String deviceToken = pref.getString(Constants.NOTI_TOKEN, "");

                                notificationListViewModel.setNextPageLoadingStateObj(loginUserId, deviceToken, String.valueOf(Config.NOTI_LIST_COUNT), String.valueOf(notificationListViewModel.offset));
                            }
                        }
                    }
                }
            }
        });

        binding.get().swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.view__primary_line));
        binding.get().swipeRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.global__primary));
        binding.get().swipeRefresh.setOnRefreshListener(() -> {

            notificationListViewModel.loadingDirection = Utils.LoadingDirection.top;

            // reset productViewModel.offset
            notificationListViewModel.offset = 0;

            // reset productViewModel.forceEndLoading
            notificationListViewModel.forceEndLoading = false;

            String deviceToken = pref.getString(Constants.NOTI_TOKEN, "");

            // update live data
            notificationListViewModel.setNotificationListObj(loginUserId, deviceToken, String.valueOf(Config.NOTI_LIST_COUNT), String.valueOf(notificationListViewModel.offset));

        });
    }

    @Override
    protected void initViewModels() {
        notificationListViewModel = new ViewModelProvider(this, viewModelFactory).get(NotificationsViewModel.class);
    }

    @Override
    protected void initAdapters() {

        nvAdapter = new NotificationListAdapter(dataBindingComponent, notification ->
                navigationController.navigateToNotificationDetail(getActivity(), notification, notificationListViewModel.token), this);


        this.adapter = new AutoClearedValue<>(this, nvAdapter);
        binding.get().notificationList.setAdapter(nvAdapter);
    }

    @Override
    protected void initData() {
        LoadData();

        try {
            Utils.psLog(">>>> On initData.");

            notificationListViewModel.token = pref.getString(Constants.NOTI_TOKEN, "");

        } catch (NullPointerException ne) {
            Utils.psErrorLog("Null Pointer Exception.", ne);
        } catch (Exception e) {
            Utils.psErrorLog("Error in getting notification flag data.", e);
        }
    }

    private void LoadData() {


        String deviceToken = pref.getString(Constants.NOTI_TOKEN, "");

        notificationListViewModel.setNotificationListObj(loginUserId, deviceToken, String.valueOf(Config.NOTI_LIST_COUNT), String.valueOf(notificationListViewModel.offset));

        LiveData<Resource<List<Noti>>> news = notificationListViewModel.getNotificationListData();

        if (news != null) {
            news.observe(this, listResource -> {
                if (listResource != null) {

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

                            notificationListViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            notificationListViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (notificationListViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        notificationListViewModel.forceEndLoading = true;
                    }

                }

            });
        }
        notificationListViewModel.getNextPageLoadingStateData().observe(this, state -> {
            if (state != null) {
                if (state.status == Status.ERROR) {
                    Utils.psLog("Next Page State : " + state.data);

                    notificationListViewModel.setLoadingState(false);//hide
                    notificationListViewModel.forceEndLoading = true;//stop
                }
            }
        });

        notificationListViewModel.getLoadingState().observe(this, loadingState -> {

            binding.get().setLoadingMore(notificationListViewModel.isLoading);

            if (loadingState != null && !loadingState) {
                binding.get().swipeRefresh.setRefreshing(false);
            }

        });
    }

    private void replaceData(List<com.panaceasoft.admotors.viewobject.Noti> notificationList) {

        adapter.get().replace(notificationList);
        binding.get().executePendingBindings();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Utils.psLog("Request code " + requestCode);
        Utils.psLog("Result code " + resultCode);

        if (requestCode == Constants.REQUEST_CODE__NOTIFICATION_LIST_FRAGMENT
                && resultCode == Constants.RESULT_CODE__REFRESH_NOTIFICATION) {

            notificationListViewModel.notiId = data.getStringExtra(Constants.NOTI_HEADER_ID);


        }
    }
}
