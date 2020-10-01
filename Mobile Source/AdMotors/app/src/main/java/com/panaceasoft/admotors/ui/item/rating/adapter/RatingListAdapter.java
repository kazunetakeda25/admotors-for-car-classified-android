package com.panaceasoft.admotors.ui.item.rating.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemRatingListBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.Rating;

public class RatingListAdapter extends DataBoundListAdapter<Rating, ItemRatingListBinding> {
    private androidx.databinding.DataBindingComponent dataBindingComponent;
    private RatingClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface;
    public Context context;

    public RatingListAdapter(androidx.databinding.DataBindingComponent dataBindingComponent, RatingClickCallback ratingClickCallback, DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = ratingClickCallback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    @Override
    protected ItemRatingListBinding createBinding(ViewGroup parent) {
        ItemRatingListBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_rating_list, parent, false,
                        dataBindingComponent);
        context = parent.getContext();
        binding.getRoot().setOnClickListener(v -> {
            Rating rating = binding.getRating();
            if (rating != null && callback != null) {
                callback.onClick(rating);
            }

        });

        return binding;
    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemRatingListBinding binding, Rating rating) {

        binding.setRating(rating);

        binding.ratingBar.setRating(Float.parseFloat(rating.rating));

    }

    @Override
    protected boolean areItemsTheSame(Rating oldItem, Rating newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.title.equals(newItem.title)
                && oldItem.description.equals(newItem.description)
                && oldItem.rating.equals(newItem.rating);
    }

    @Override
    protected boolean areContentsTheSame(Rating oldItem, Rating newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.title.equals(newItem.title)
                && oldItem.description.equals(newItem.description)
                && oldItem.rating.equals(newItem.rating);
    }

    public interface RatingClickCallback {
        void onClick(Rating rating);
    }

}

