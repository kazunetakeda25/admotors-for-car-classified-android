package com.panaceasoft.admotors.ui.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemSearchModelAdapterBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.Model;

public class SearchModelAdapter extends DataBoundListAdapter<Model, ItemSearchModelAdapterBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final SearchModelAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface = null;
    private int lastPosition = -1;
    private String modelId;

    public SearchModelAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                              SearchModelAdapter.NewsClickCallback callback,
                              DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    public SearchModelAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                              SearchModelAdapter.NewsClickCallback callback, String modelId) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.modelId = modelId;
    }

    @Override
    protected ItemSearchModelAdapterBinding createBinding(ViewGroup parent) {
        ItemSearchModelAdapterBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_search_model_adapter, parent, false,
                        dataBindingComponent);

        binding.getRoot().setOnClickListener(v -> {
            Model subCategory = binding.getModel();
            if (subCategory != null && callback != null) {

                callback.onClick(subCategory);
            }
        });
        return binding;
        
    }

    @Override
    public void bindView(DataBoundViewHolder<ItemSearchModelAdapterBinding> holder, int position) {
        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemSearchModelAdapterBinding binding, Model item) {
        binding.setModel(item);

        if (modelId != null) {
            if (item.id.equals(modelId)) {
                binding.groupview.setBackgroundColor(binding.groupview.getResources().getColor((R.color.md_green_50)));
            }
        }

    }

    @Override
    protected boolean areItemsTheSame(Model oldItem, Model newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    @Override
    protected boolean areContentsTheSame(Model oldItem, Model newItem) {
        return Objects.equals(oldItem.id, newItem.id);
    }

    public interface NewsClickCallback {
        void onClick(Model subCategory);
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
