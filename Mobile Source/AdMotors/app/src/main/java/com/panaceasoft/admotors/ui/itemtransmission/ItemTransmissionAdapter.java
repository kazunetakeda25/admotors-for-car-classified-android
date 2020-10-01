package com.panaceasoft.admotors.ui.itemtransmission;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemItemTransmissionBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.Transmission;

public class ItemTransmissionAdapter extends DataBoundListAdapter<Transmission, ItemItemTransmissionBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final ItemTransmissionAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    public String itemTransmissionId = "";

    public ItemTransmissionAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                   ItemTransmissionAdapter.NewsClickCallback callback, String itemTransmissionId) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.itemTransmissionId = itemTransmissionId;
    }

    @Override
    protected ItemItemTransmissionBinding createBinding(ViewGroup parent) {
        ItemItemTransmissionBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_item_transmission, parent, false,
                        dataBindingComponent);
        
        binding.getRoot().setOnClickListener(v -> {

            Transmission itemType = binding.getTransmission();

            if (itemType != null && callback != null) {

                binding.groupview.setBackgroundColor(parent.getResources().getColor(R.color.md_green_50));

                callback.onClick(itemType, itemType.id);
            }
        });
        return binding;
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemItemTransmissionBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemItemTransmissionBinding binding, Transmission transmission) {
        binding.setTransmission(transmission);

        if (itemTransmissionId != null) {
            if (transmission.id.equals(itemTransmissionId)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(Transmission oldItem, Transmission newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(Transmission oldItem, Transmission newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(Transmission itemType, String id);
    }

}
