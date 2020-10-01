package com.panaceasoft.admotors.ui.item.itemtype.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemItemTypeBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.ItemType;

public class ItemTypeAdapter extends DataBoundListAdapter<ItemType, ItemItemTypeBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final ItemTypeAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    public String itemTypeId = "";

    public ItemTypeAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                 ItemTypeAdapter.NewsClickCallback callback,
                                 DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    public ItemTypeAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                 ItemTypeAdapter.NewsClickCallback callback, String itemTypeId) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.itemTypeId = itemTypeId;
    }

    @Override
    protected ItemItemTypeBinding createBinding(ViewGroup parent) {
        ItemItemTypeBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_item_type, parent, false,
                        dataBindingComponent);

        binding.getRoot().setOnClickListener(v -> {

            ItemType itemType = binding.getItemType();

            if (itemType != null && callback != null) {

                binding.groupview.setBackgroundColor(parent.getResources().getColor(R.color.md_green_50));

                callback.onClick(itemType, itemType.id);
            }
        });
        return binding;
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemItemTypeBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemItemTypeBinding binding, ItemType item) {
        binding.setItemType(item);

        if (itemTypeId != null) {
            if (item.id.equals(itemTypeId)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(ItemType oldItem, ItemType newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(ItemType oldItem, ItemType newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(ItemType itemType, String id);
    }

}
