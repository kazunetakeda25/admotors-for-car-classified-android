package com.panaceasoft.admotors.ui.manufacturer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemCityManufacturerAdapterBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.Manufacturer;

import androidx.databinding.DataBindingUtil;

public class CityManufacturerAdapter extends DataBoundListAdapter<Manufacturer, ItemCityManufacturerAdapterBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final CityCategoryClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface ;
    private int lastPosition = -1;

    public CityManufacturerAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                   CityCategoryClickCallback callback,
                                   DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    @Override
    protected ItemCityManufacturerAdapterBinding createBinding(ViewGroup parent) {
        ItemCityManufacturerAdapterBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_city_manufacturer_adapter, parent, false,
                        dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {
            Manufacturer category = binding.getManufacturer();
            if (category != null && callback != null) {
                callback.onClick(category);
            }
        });
        return binding;
    }

    // For general animation
    @Override
    public void bindView(DataBoundViewHolder<ItemCityManufacturerAdapterBinding> holder, int position) {
        super.bindView(holder, position);

        //setAnimation(holder.itemView, position);
    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemCityManufacturerAdapterBinding binding, Manufacturer item) {

        binding.setManufacturer(item);

        binding.itemCategoryImageView.setOnClickListener(view -> callback.onClick(item));

    }

    @Override
    protected boolean areItemsTheSame(Manufacturer oldItem, Manufacturer newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.name.equals(newItem.name);
    }

    @Override
    protected boolean areContentsTheSame(Manufacturer oldItem, Manufacturer newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.name.equals(newItem.name);
    }

    public interface CityCategoryClickCallback {
        void onClick(Manufacturer category);
    }


    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.slide_in_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        } else {
            lastPosition = position;
        }
    }
}
