package com.panaceasoft.admotors.ui.item.itemcurrency;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemItemCurrencyBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.ItemCurrency;

public class ItemCurrencyAdapter extends DataBoundListAdapter<ItemCurrency, ItemItemCurrencyBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final ItemCurrencyAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    public String currencyTypeId = "";

    public ItemCurrencyAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                               ItemCurrencyAdapter.NewsClickCallback callback,
                               DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    public ItemCurrencyAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                               ItemCurrencyAdapter.NewsClickCallback callback, String currencyTypeId) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.currencyTypeId = currencyTypeId;
    }

    @Override
    protected ItemItemCurrencyBinding createBinding(ViewGroup parent) {
        ItemItemCurrencyBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_item_currency, parent, false,
                        dataBindingComponent);

        binding.getRoot().setOnClickListener(v -> {

            ItemCurrency itemCurrency = binding.getItemCurrency();

            if (itemCurrency != null && callback != null) {

                binding.groupview.setBackgroundColor(parent.getResources().getColor(R.color.md_green_50));

                callback.onClick(itemCurrency, itemCurrency.id);
            }
        });
        return binding;
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemItemCurrencyBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemItemCurrencyBinding binding, ItemCurrency item) {
        binding.setItemCurrency(item);

        if (currencyTypeId != null) {
            if (item.id.equals(currencyTypeId)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(ItemCurrency oldItem, ItemCurrency newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(ItemCurrency oldItem, ItemCurrency newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(ItemCurrency itemType, String id);
    }

}
