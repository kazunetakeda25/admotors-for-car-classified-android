package com.panaceasoft.admotors.ui.blog.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemBlogListAdapterBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundViewHolder;
import com.panaceasoft.admotors.utils.Objects;
import com.panaceasoft.admotors.viewobject.Blog;

import androidx.databinding.DataBindingUtil;

public class BlogListAdapter extends DataBoundListAdapter<Blog, ItemBlogListAdapterBinding> {

    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final BlogListAdapter.NewsClickCallback callback;
    private DataBoundListAdapter.DiffUtilDispatchedInterface diffUtilDispatchedInterface;
    private int lastPosition = -1;

    public BlogListAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                           BlogListAdapter.NewsClickCallback callback,
                           DiffUtilDispatchedInterface diffUtilDispatchedInterface) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;
        this.diffUtilDispatchedInterface = diffUtilDispatchedInterface;
    }

    @Override
    protected ItemBlogListAdapterBinding createBinding(ViewGroup parent) {
        ItemBlogListAdapterBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_blog_list_adapter, parent, false,
                        dataBindingComponent);
        binding.getRoot().setOnClickListener(v -> {
            Blog blog = binding.getBlog();
            if (blog != null && callback != null) {
                callback.onClick(blog);
            }
        });
        return binding;
    }


    @Override
    public void bindView(DataBoundViewHolder<ItemBlogListAdapterBinding> holder, int position) {

        super.bindView(holder, position);

    }

    @Override
    protected void dispatched() {
        if (diffUtilDispatchedInterface != null) {
            diffUtilDispatchedInterface.onDispatched();
        }
    }

    @Override
    protected void bind(ItemBlogListAdapterBinding binding, Blog blog) {

        binding.setBlog(blog);

    }

    @Override
    protected boolean areItemsTheSame(Blog oldItem, Blog newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.name.equals(newItem.name);
    }

    @Override
    protected boolean areContentsTheSame(Blog oldItem, Blog newItem) {
        return Objects.equals(oldItem.id, newItem.id)
                && oldItem.name.equals(newItem.name);
    }

    public interface NewsClickCallback {
        void onClick(Blog blog);
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



