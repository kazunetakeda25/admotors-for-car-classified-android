package com.panaceasoft.admotors.ui.itemcolor;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemItemColorBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.Color;

public class ItemColorAdapter extends DataBoundListAdapter<Color, ItemItemColorBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final ItemColorAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    public String itemColorId = "";

    public ItemColorAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                   ItemColorAdapter.NewsClickCallback callback, String itemColorId) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.itemColorId = itemColorId;
    }

    @Override
    protected ItemItemColorBinding createBinding(ViewGroup parent) {
        ItemItemColorBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_item_color, parent, false,
                        dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {

            Color itemType = binding.getColor();

            if (itemType != null && callback != null) {

                binding.groupview.setBackgroundColor(parent.getResources().getColor(R.color.md_green_50));

                callback.onClick(itemType, itemType.id);
            }
        });
        
        return binding;
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemItemColorBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemItemColorBinding binding, Color color) {
        binding.setColor(color);

        if (itemColorId != null) {
            if (color.id.equals(itemColorId)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(Color oldItem, Color newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(Color oldItem, Color newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(Color itemType, String id);
    }

}
