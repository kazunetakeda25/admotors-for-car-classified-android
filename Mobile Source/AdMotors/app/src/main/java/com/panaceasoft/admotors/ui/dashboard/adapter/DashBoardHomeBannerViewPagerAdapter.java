package com.panaceasoft.admotors.ui.dashboard.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ItemDashboardHomeBannerViewpagerBinding;
import com.panaceasoft.admotors.viewobject.Banner;

import java.util.List;

public class DashBoardHomeBannerViewPagerAdapter extends PagerAdapter {

    public List<Banner> bannerList;

    public final androidx.databinding.DataBindingComponent dataBindingComponent;

    public DashBoardHomeBannerViewPagerAdapter(androidx.databinding.DataBindingComponent dataBindingComponent)
    {
        this.dataBindingComponent = dataBindingComponent;
    }

    @Override
    public int getCount() {
        if (bannerList == null) {
            return 0;
        } else {
            return bannerList.size();
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ItemDashboardHomeBannerViewpagerBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(container.getContext()),
                        R.layout.item_dashboard_home_banner_viewpager, container, false,
                        dataBindingComponent);

        binding.setBanner(bannerList.get(position));

        container.addView(binding.getRoot());
        
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    public void replaceHomeBannerList(List<Banner> bannerList) {
        this.bannerList = bannerList;
        this.notifyDataSetChanged();
    }

}


