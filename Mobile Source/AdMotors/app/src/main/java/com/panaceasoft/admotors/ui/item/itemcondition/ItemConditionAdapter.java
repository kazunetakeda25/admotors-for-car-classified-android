package com.panaceasoft.admotors.ui.item.itemcondition;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemItemConditionBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.ItemCondition;

public class ItemConditionAdapter extends DataBoundListAdapter<ItemCondition, ItemItemConditionBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final ItemConditionAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    public String itemConditionId = "";

    public ItemConditionAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                               ItemConditionAdapter.NewsClickCallback callback,
                               DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    ItemConditionAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                               ItemConditionAdapter.NewsClickCallback callback, String itemConditionId) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.itemConditionId = itemConditionId;
    }

    @Override
    protected ItemItemConditionBinding createBinding(ViewGroup parent) {
        ItemItemConditionBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_item_condition, parent, false,
                        dataBindingComponent);

        binding.getRoot().setOnClickListener(v -> {

            ItemCondition itemCurrency = binding.getItemCondition();

            if (itemCurrency != null && callback != null) {

                binding.groupview.setBackgroundColor(parent.getResources().getColor(R.color.md_green_50));

                callback.onClick(itemCurrency, itemCurrency.id);
            }
        });
        return binding;
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemItemConditionBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemItemConditionBinding binding, ItemCondition item) {
        binding.setItemCondition(item);

        if (itemConditionId != null) {
            if (item.id.equals(itemConditionId)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(ItemCondition oldItem, ItemCondition newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(ItemCondition oldItem, ItemCondition newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(ItemCondition itemType, String id);
    }

}
