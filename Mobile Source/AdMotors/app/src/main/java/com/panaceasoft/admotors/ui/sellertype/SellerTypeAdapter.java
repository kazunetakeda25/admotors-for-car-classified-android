package com.panaceasoft.admotors.ui.sellertype;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemSellerTypeBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.SellerType;

public class SellerTypeAdapter  extends DataBoundListAdapter<SellerType, ItemSellerTypeBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final SellerTypeAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    public String itemSellerType = "";

    public SellerTypeAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                            SellerTypeAdapter.NewsClickCallback callback, String itemSellerType) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.itemSellerType = itemSellerType;
    }

    @Override
    protected ItemSellerTypeBinding createBinding(ViewGroup parent) {
        ItemSellerTypeBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_seller_type, parent, false,
                        dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {

            SellerType itemType = binding.getSellerType();

            if (itemType != null && callback != null) {

                binding.groupview.setBackgroundColor(parent.getResources().getColor(R.color.md_green_50));

                callback.onClick(itemType, itemType.id);
            }
        });

        
        return binding;
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemSellerTypeBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemSellerTypeBinding binding, SellerType sellerType) {
        binding.setSellerType(sellerType);

        if (itemSellerType != null) {
            if (sellerType.id.equals(itemSellerType)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(SellerType oldItem, SellerType newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(SellerType oldItem, SellerType newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(SellerType itemType, String id);
    }

}

