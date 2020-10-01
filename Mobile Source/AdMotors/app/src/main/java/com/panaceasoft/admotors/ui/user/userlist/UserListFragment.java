package com.panaceasoft.admotors.ui.user.userlist;

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

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentListUserBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.user.userlist.adapter.UserAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.user.UserViewModel;
import com.panaceasoft.admotors.viewobject.User;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;
import com.panaceasoft.admotors.viewobject.holder.UserParameterHolder;

import java.util.List;

public class UserListFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private UserViewModel userViewModel;

    @VisibleForTesting
    private AutoClearedValue<FragmentListUserBinding> binding;
    private AutoClearedValue<UserAdapter> followerUserAdapter;

    //endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentListUserBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_user, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());
        
        return binding.get().getRoot();
    }

    @Override
    public void onDispatched() {
        if (userViewModel.loadingDirection == Utils.LoadingDirection.top) {

            if (binding.get().followersUserRecycler != null) {

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        binding.get().followersUserRecycler.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
    }

    @Override
    protected void initUIAndActions() {

        binding.get().followersUserRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();

                if (layoutManager != null) {

                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();

                    if (lastPosition == followerUserAdapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !userViewModel.forceEndLoading) {

                            if (connectivity.isConnected()) {
                                userViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                                int limit = Config.LOGIN_USER_ITEM_COUNT;
                                userViewModel.offset = userViewModel.offset + limit;

                                userViewModel.setNextPageUserListByKeyObj(Utils.checkUserId(loginUserId),String.valueOf(Config.LOGIN_USER_ITEM_COUNT), String.valueOf(userViewModel.offset),userViewModel.userHolder);
                            }
                        }
                    }
                }
            }
        });
        binding.get().swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.view__primary_line));
        binding.get().swipeRefresh.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.global__primary));
        binding.get().swipeRefresh.setOnRefreshListener(() -> {

            userViewModel.loadingDirection = Utils.LoadingDirection.top;

            // reset productViewModel.offset
            userViewModel.offset = 0;

            // reset productViewModel.forceEndLoading
            userViewModel.forceEndLoading = false;

            // update live data
            userViewModel.setUserListByKeyObj(Utils.checkUserId(loginUserId), String.valueOf(Config.LOGIN_USER_ITEM_COUNT), String.valueOf(userViewModel.offset), userViewModel.userHolder);

        });
    }

    @Override
    protected void initViewModels() {
        userViewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);
    }

    @Override
    protected void initAdapters() {
        UserAdapter userAdapter = new UserAdapter(dataBindingComponent,
                user -> navigationController.navigateToUserDetail(UserListFragment.this.getActivity(), user.userId, user.userName));
        this.followerUserAdapter = new AutoClearedValue<>(this, userAdapter);
        binding.get().followersUserRecycler.setAdapter(userAdapter);
    }

    @Override
    protected void initData() {

        getIntentData();

        loadData();
    }

    private void getIntentData() {

        if(getActivity() != null) {

            userViewModel.userHolder = (UserParameterHolder) getActivity().getIntent().getSerializableExtra(Constants.USER_PARAM_HOLDER_KEY);
        }

        userViewModel.userHolder.login_user_id = loginUserId;
        userViewModel.userHolder.login_user_id = loginUserId;


    }

    private void loadData() {

        //Item
        userViewModel.setUserListByKeyObj(Utils.checkUserId(loginUserId), String.valueOf(Config.LOGIN_USER_ITEM_COUNT),  String.valueOf(userViewModel.offset), userViewModel.userHolder);

        LiveData<Resource<List<User>>> itemList = userViewModel.getUserListByKeyData();

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

                            userViewModel.setLoadingState(false);


                            break;

                        case ERROR:
                            // Error State

                            userViewModel.setLoadingState(false);

                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data

                    if (userViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        userViewModel.forceEndLoading = true;
                    }

                }

            });
        }


        userViewModel.getNextPageUserListByKeyData().observe(this, state -> {
            if (state != null) {
                if (state.status == Status.ERROR) {

                    userViewModel.setLoadingState(false);//hide
                    userViewModel.forceEndLoading = true;//stop

                }
            }
        });

        userViewModel.getLoadingState().observe(this, loadingState -> {
            binding.get().setLoadingMore(userViewModel.isLoading);

            if (loadingState != null && !loadingState) {
                binding.get().swipeRefresh.setRefreshing(false);
            }
        });

    }

    private void itemReplaceData(List<User> followerUsers) {
        followerUserAdapter.get().replace(followerUsers);
        binding.get().executePendingBindings();
    }
}
