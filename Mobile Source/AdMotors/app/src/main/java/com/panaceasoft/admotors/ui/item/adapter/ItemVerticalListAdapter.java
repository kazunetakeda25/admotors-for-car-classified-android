package com.panaceasoft.admotors.ui.item.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemItemVerticalWithUserBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.Item;

/**
 * Created by Panacea-Soft on 9/18/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class ItemVerticalListAdapter extends DataBoundListAdapter<Item, ItemItemVerticalWithUserBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface;

    public ItemVerticalListAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                                   NewsClickCallback callback, DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    @Override
    protected ItemItemVerticalWithUserBinding createBinding(ViewGroup parent) {
        ItemItemVerticalWithUserBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_item_vertical_with_user, parent, false,
                        dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {
            Item item = binding.getItem();
            if (item != null && callback != null) {
                callback.onClick(item);
            }
        });


        return binding;
    }

    // For general animation
    @Override
    public void bindView(DataBoundViewHolder<ItemItemVerticalWithUserBinding> holder, int position) {
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
    protected void bind(ItemItemVerticalWithUserBinding binding, Item item) {
        binding.setItem(item);
        binding.conditionTextView.setText(binding.getRoot().getResources().getString(R.string.item_condition__type, item.itemCondition.name));
        String currencySymbol = item.itemCurrency.currencySymbol;
        String price;
        try {
            price = Utils.format(Double.parseDouble(item.price));

        } catch (Exception e) {
            price = item.price;
        }
        String currencyPrice;
        if (Config.SYMBOL_SHOW_FRONT) {
            currencyPrice = currencySymbol + " " + price;
        } else {
            currencyPrice = price + " " + currencySymbol;
        }
        binding.priceTextView.setText(currencyPrice);

        if (item.isSoldOut.equals(Constants.ONE)) {
            binding.isSoldTextView.setVisibility(View.VISIBLE);
        } else {
            binding.isSoldTextView.setVisibility(View.GONE);
        }
        if (item.priceUnit.equals("")){
            binding.lakhTextView.setVisibility(View.GONE);
        } else{
            binding.lakhTextView.setVisibility(View.VISIBLE);
            binding.lakhTextView.setText(item.priceUnit);
        }
    }

    @Override
    protected boolean areItemsTheSame(Item oldItem, Item newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.title.equals(newItem.title)
                && oldItem.isFavourited.equals(newItem.isFavourited)
                && oldItem.favouriteCount.equals(newItem.favouriteCount)
                && oldItem.isSoldOut.equals(newItem.isSoldOut);
    }

    @Override
    protected boolean areContentsTheSame(Item oldItem, Item newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.title.equals(newItem.title)
                && oldItem.isFavourited.equals(newItem.isFavourited)
                && oldItem.favouriteCount.equals(newItem.favouriteCount)
                && oldItem.isSoldOut.equals(newItem.isSoldOut);
    }

    public interface NewsClickCallback {
        void onClick(Item item);

    }


}
