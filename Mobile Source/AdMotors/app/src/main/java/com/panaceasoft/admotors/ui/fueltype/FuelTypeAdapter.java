package com.panaceasoft.admotors.ui.fueltype;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemFuelTypeBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.FuelType;

public class FuelTypeAdapter extends DataBoundListAdapter<FuelType, ItemFuelTypeBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final FuelTypeAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    public String itemColorId = "";

    public FuelTypeAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                            FuelTypeAdapter.NewsClickCallback callback, String itemColorId) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.itemColorId = itemColorId;
    }

    @Override
    protected ItemFuelTypeBinding createBinding(ViewGroup parent) {
        ItemFuelTypeBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_fuel_type, parent, false,
                        dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {

            FuelType itemType = binding.getFuelType();

            if (itemType != null && callback != null) {

                binding.groupview.setBackgroundColor(parent.getResources().getColor(R.color.md_green_50));

                callback.onClick(itemType, itemType.id);
            }
        });

        
        return binding;
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemFuelTypeBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemFuelTypeBinding binding, FuelType color) {
        binding.setFuelType(color);

        if (itemColorId != null) {
            if (color.id.equals(itemColorId)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(FuelType oldItem, FuelType newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(FuelType oldItem, FuelType newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(FuelType itemType, String id);
    }

}

