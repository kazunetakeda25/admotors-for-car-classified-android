package com.panaceasoft.admotors.ui.item.history;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.MainActivity;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentHistoryBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.item.history.adapter.HistoryAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.item.HistoryViewModel;
import com.panaceasoft.admotors.viewobject.ItemHistory;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private HistoryViewModel historyViewModel;

    @VisibleForTesting
    private AutoClearedValue<FragmentHistoryBinding> binding;
    private AutoClearedValue<HistoryAdapter> historyAdapter;

    //endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHistoryBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }

    @Override
    public void onDispatched() {
        if (historyViewModel.loadingDirection == Utils.LoadingDirection.bottom) {

            if (binding.get().historyRecycler != null) {

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        binding.get().historyRecycler.getLayoutManager();

                if (layoutManager != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
    }

    @Override
    protected void initUIAndActions() {

        if (getActivity() instanceof MainActivity) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            ((MainActivity) this.getActivity()).binding.toolbar.setBackgroundColor(getResources().getColor(R.color.global__primary));
            ((MainActivity) getActivity()).updateToolbarIconColor(Color.WHITE);
            ((MainActivity) getActivity()).updateMenuIconWhite();
            ((MainActivity) getActivity()).refreshPSCount();
        }

        binding.get().historyRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();

                if (layoutManager != null) {

                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();
                    if (lastPosition == historyAdapter.get().getItemCount() - 1) {

                        if (!binding.get().getLoadingMore() && !historyViewModel.forceEndLoading) {

                            historyViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                            int limit = Config.HISTORY_COUNT;
                            historyViewModel.offset = historyViewModel.offset + limit;
                            historyViewModel.setHistoryItemListObj(String.valueOf(historyViewModel.offset));
                        }
                    }
                }
            }
        });

    }

    @Override
    protected void initViewModels() {
        historyViewModel = new ViewModelProvider(this, viewModelFactory).get(HistoryViewModel.class);
    }

    @Override
    protected void initAdapters() {
        HistoryAdapter historyAdapter = new HistoryAdapter(dataBindingComponent,
                itemHistory -> navigationController.navigateToItemDetailFromHistoryListOnly(HistoryFragment.this.getActivity(), itemHistory.id, itemHistory.historyName));
        this.historyAdapter = new AutoClearedValue<>(this, historyAdapter);
        binding.get().historyRecycler.setAdapter(historyAdapter);
    }

    @Override
    protected void initData() {
        loadData();
    }

    private void loadData() {

        //load basket
        historyViewModel.offset = Config.HISTORY_COUNT;
        historyViewModel.setHistoryItemListObj(String.valueOf(historyViewModel.offset));
        LiveData<List<ItemHistory>> historyItemList = historyViewModel.getAllHistoryItemList();
        if (historyItemList != null) {
            historyItemList.observe(this, listResource -> {
                if (listResource != null) {

                    replaceItemHistoryData(listResource);

                }

            });
        }


        historyViewModel.getLoadingState().observe(this, loadingState -> binding.get().setLoadingMore(historyViewModel.isLoading));

    }

    private void replaceItemHistoryData(List<ItemHistory> historyItemList) {
        historyAdapter.get().replace(historyItemList);
        binding.get().executePendingBindings();

    }
}
