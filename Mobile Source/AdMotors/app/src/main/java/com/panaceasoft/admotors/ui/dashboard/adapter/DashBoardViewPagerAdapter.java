package com.panaceasoft.admotors.ui.dashboard.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemDashboardViewpagerBlogBinding;
import com.panaceasoft.admotors.viewobject.Blog;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DashBoardViewPagerAdapter extends PagerAdapter {

    public List<Blog> blogList;

    public final androidx.databinding.DataBindingComponent dataBindingComponent;
    public DashBoardViewPagerAdapter.newsFeedClickCallBack callback;

    public DashBoardViewPagerAdapter(androidx.databinding.DataBindingComponent dataBindingComponent,
                              DashBoardViewPagerAdapter.newsFeedClickCallBack callback) {
        this.dataBindingComponent = dataBindingComponent;
        this.callback = callback;

    }

    @Override
    public int getCount() {
        if (blogList == null) {
            return 0;
        } else {
            return blogList.size();
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ItemDashboardViewpagerBlogBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(container.getContext()),
                        R.layout.item_dashboard_viewpager_blog, container, false,
                        dataBindingComponent);

        binding.setBlog(blogList.get(position));

        binding.getRoot().setOnClickListener(v -> callback.onPagerClick(binding.getBlog()));

        container.addView(binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    public void replaceNewsFeedList(List<Blog> blogList) {
        this.blogList = blogList;
        this.notifyDataSetChanged();
    }

    public interface newsFeedClickCallBack {
        void onPagerClick(Blog blog);
    }
}

