package com.panaceasoft.admotors.ui.item.itemlocation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemItemLocationBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.ItemLocation;

public class ItemLocationAdapter extends DataBoundListAdapter<ItemLocation, ItemItemLocationBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final ItemLocationAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    public String locationId = "";

    public ItemLocationAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                               ItemLocationAdapter.NewsClickCallback callback,
                               DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    public ItemLocationAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                               ItemLocationAdapter.NewsClickCallback callback, String locationId) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.locationId = locationId;
    }

    @Override
    protected ItemItemLocationBinding createBinding(ViewGroup parent) {
        ItemItemLocationBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_item_location, parent, false,
                        dataBindingComponent);

        binding.getRoot().setOnClickListener(v -> {

            ItemLocation itemCurrency = binding.getItemLocation();

            if (itemCurrency != null && callback != null) {

                binding.groupview.setBackgroundColor(parent.getResources().getColor(R.color.md_green_50));

                callback.onClick(itemCurrency, itemCurrency.id);
            }
        });
        return binding;
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemItemLocationBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemItemLocationBinding binding, ItemLocation item) {
        binding.setItemLocation(item);

        if (locationId != null) {
            if (item.id.equals(locationId)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(ItemLocation oldItem, ItemLocation newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(ItemLocation oldItem, ItemLocation newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(ItemLocation itemType, String id);
    }

}
