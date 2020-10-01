package com.panaceasoft.admotors.ui.manufacturer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemSearchManufacturerAdapterBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.Manufacturer;

public class SearchManufacturerAdapter extends DataBoundListAdapter<Manufacturer, ItemSearchManufacturerAdapterBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final SearchManufacturerAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    private int lastPosition = -1;
    public String manufacturerId = "";

    public SearchManufacturerAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                     SearchManufacturerAdapter.NewsClickCallback callback,
                                     DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    public SearchManufacturerAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                     SearchManufacturerAdapter.NewsClickCallback callback, String manufacturerId) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.manufacturerId = manufacturerId;
    }

    @Override
    protected ItemSearchManufacturerAdapterBinding createBinding(ViewGroup parent) {
        ItemSearchManufacturerAdapterBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_search_manufacturer_adapter, parent, false,
                        dataBindingComponent);

        binding.getRoot().setOnClickListener(v -> {

            Manufacturer category = binding.getManufacturer();

            if (category != null && callback != null) {

                binding.groupview.setBackgroundColor(parent.getResources().getColor(R.color.md_green_50));

                callback.onClick(category, category.id);
            }
        });
        return binding;
        
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemSearchManufacturerAdapterBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemSearchManufacturerAdapterBinding binding, Manufacturer item) {
        binding.setManufacturer(item);

        if (manufacturerId != null) {
            if (item.id.equals(manufacturerId)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(Manufacturer oldItem, Manufacturer newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(Manufacturer oldItem, Manufacturer newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(Manufacturer manufacturer, String id);
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
