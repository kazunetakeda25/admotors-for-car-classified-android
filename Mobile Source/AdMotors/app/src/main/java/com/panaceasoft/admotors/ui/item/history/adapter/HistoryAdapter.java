package com.panaceasoft.admotors.ui.item.history.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemHistoryAdapterBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.ItemHistory;

public class HistoryAdapter extends DataBoundListAdapter<ItemHistory, ItemHistoryAdapterBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private HistoryClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;

    public HistoryAdapter(androidx.databinding.DataBindingComponent dataBindingComponent, HistoryClickCallback historyClickCallback) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = historyClickCallback;
    }

    @Override
    protected ItemHistoryAdapterBinding createBinding(ViewGroup parent) {
        ItemHistoryAdapterBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_history_adapter, parent, false,
                        dataBindingComponent);

        binding.getRoot().setOnClickListener(v -> {
            ItemHistory itemHistory = binding.getHistory();
            if (itemHistory != null && callback != null) {
                callback.onClick(itemHistory);
            }

        });


        return binding;
    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemHistoryAdapterBinding binding, ItemHistory item) {
        binding.setHistory(item);
        binding.historyNameTextView.setText(item.historyName);
    }

    @Override
    protected boolean areItemsTheSame(ItemHistory oldItem, ItemHistory newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.historyName.equals(newItem.historyName);
    }

    @Override
    protected boolean areContentsTheSame(ItemHistory oldItem, ItemHistory newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.historyName.equals(newItem.historyName);
    }

    public interface HistoryClickCallback {
        void onClick(ItemHistory itemHistory);
    }
}
