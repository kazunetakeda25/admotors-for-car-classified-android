package com.panaceasoft.admotors.ui.city.selectedcity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.MainActivity;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentSelectedCityBinding;
import com.panaceasoft.admotors.ui.manufacturer.adapter.CityManufacturerAdapter;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.dashboard.adapter.DashBoardHomeBannerViewPagerAdapter;
import com.panaceasoft.admotors.ui.dashboard.adapter.DashBoardViewPagerAdapter;
import com.panaceasoft.admotors.ui.item.adapter.ItemHorizontalListAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.PSDialogMsg;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.blog.BlogViewModel;
import com.panaceasoft.admotors.viewmodel.homebanner.HomeBannerViewModel;
import com.panaceasoft.admotors.viewmodel.item.PopularItemViewModel;
import com.panaceasoft.admotors.viewmodel.item.RecentItemViewModel;
import com.panaceasoft.admotors.viewmodel.itemmanufacturer.ItemManufacturerViewModel;
import com.panaceasoft.admotors.viewmodel.itemfromfollower.ItemFromFollowerViewModel;
import com.panaceasoft.admotors.viewobject.Banner;
import com.panaceasoft.admotors.viewobject.Blog;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.Manufacturer;
import com.panaceasoft.admotors.viewobject.holder.ItemParameterHolder;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SelectedCityFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemManufacturerViewModel itemManufacturerViewModel;
    private PopularItemViewModel popularItemViewModel;
    private RecentItemViewModel recentItemViewModel;
    private BlogViewModel blogViewModel;
    private HomeBannerViewModel homeBannerViewModel;
    private PSDialogMsg psDialogMsg;
    private ItemFromFollowerViewModel itemFromFollowerViewModel;
    private ItemParameterHolder searchItemParameterHolder = new ItemParameterHolder().getRecentItem();
    private boolean searchKeywordOnFocus = false;

    private int NUM_PAGES = 10;

    private Runnable blogUpdate;
    private ImageView[] blogDots;
    private boolean blogLayoutDone = false;
    private int blogLoadingCount = 0;
    private int blogCurrentPage = 0;
    private boolean blogTouched = false;
    private Timer blogUnTouchedTimer;
    private Handler blogHandler = new Handler();

    private Runnable topUpdate;
    private boolean topLayoutDone = false;
    private int topLoadingCount = 0;
    private int topCurrentPage = 0;
    private boolean topTouched = false;
    private Timer topUnTouchedTimer;
    private Handler topHandler = new Handler();

    @VisibleForTesting
    private AutoClearedValue<FragmentSelectedCityBinding> binding;
    private AutoClearedValue<ItemHorizontalListAdapter> popularItemListAdapter;
    private AutoClearedValue<ItemHorizontalListAdapter> recentItemListAdapter;
    private AutoClearedValue<ItemHorizontalListAdapter> followerItemListAdapter;
    private AutoClearedValue<DashBoardViewPagerAdapter> blogViewPagerAdapter;
    private AutoClearedValue<DashBoardHomeBannerViewPagerAdapter> homeBannerViewPagerAdapter;
    private AutoClearedValue<CityManufacturerAdapter> cityManufacturerAdapter;
    private AutoClearedValue<ViewPager> blogViewPager, homeViewPager;
    private AutoClearedValue<LinearLayout> blogIndicatorLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentSelectedCityBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_selected_city, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        binding.get().setLoadingMore(connectivity.isConnected());

        return binding.get().getRoot();
    }

    @Override
    protected void initUIAndActions() {

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) this.getActivity()).binding.toolbar.setBackgroundColor(getResources().getColor(R.color.layout__primary_background));
            ((MainActivity) getActivity()).updateToolbarIconColor(Color.GRAY);
            ((MainActivity) getActivity()).updateMenuIconGrey();
            ((MainActivity) getActivity()).refreshPSCount();


        }

        getIntentData();

        binding.get().imageView35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).toggleDrawer();
            }
        });
        if (Config.SHOW_ADMOB && connectivity.isConnected()) {
            AdRequest adRequest2 = new AdRequest.Builder()
                    .build();
            binding.get().adView2.loadAd(adRequest2);
        } else {
            binding.get().adView2.setVisibility(View.GONE);
        }

        psDialogMsg = new PSDialogMsg(getActivity(), false);

        blogViewPager = new AutoClearedValue<>(this, binding.get().blogViewPager);

        blogIndicatorLayout = new AutoClearedValue<>(this, binding.get().blogpagerIndicator);

        homeViewPager = new AutoClearedValue<>(this, binding.get().homeBannerViewPager);

        binding.get().blogViewAllTextView.setOnClickListener(v -> navigationController.navigateToBlogList(getActivity()));

        binding.get().popularViewAllTextView.setOnClickListener(v -> navigationController.navigateToHomeFilteringActivity(getActivity(), popularItemViewModel.popularItemParameterHolder, getString(R.string.selected_city_popular_item), selectedCityLat, selectedCityLng, Constants.MAP_MILES));

        binding.get().followerViewAllTextView.setOnClickListener(v -> navigationController.navigateToItemListFromFollower(getActivity()));

        binding.get().recentItemViewAllTextView.setOnClickListener(v -> navigationController.navigateToHomeFilteringActivity(getActivity(), recentItemViewModel.recentItemParameterHolder, getString(R.string.selected_city_recent), selectedCityLat, selectedCityLng, Constants.MAP_MILES));

        binding.get().categoryViewAllTextView.setOnClickListener(v -> navigationController.navigateToCategoryActivity(getActivity()));

        binding.get().locationTextView.setOnClickListener(v -> navigationController.navigateToLocationActivity(getActivity(), Constants.SELECT_LOCATION_FROM_HOME, selected_location_id));


        binding.get().notiImageView.setOnClickListener(v -> navigationController.navigateToNotificationList(getActivity()));

        binding.get().blogImageView.setOnClickListener(v -> navigationController.navigateToBlogList(getActivity()));

        binding.get().searchBoxEditText.setOnFocusChangeListener((v, hasFocus) -> {

            searchKeywordOnFocus = hasFocus;
            Utils.psLog("Focus " + hasFocus);
        });
        binding.get().searchBoxEditText.setOnKeyListener((v, keyCode, event) -> {

            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                binding.get().searchBoxEditText.clearFocus();
                searchKeywordOnFocus = false;
                callSearchList();
                Utils.psLog("Down");

                return false;
            } else if (event.getAction() == KeyEvent.ACTION_UP) {

                Utils.psLog("Up");
            }
            return false;
        });
        binding.get().searchImageButton.setOnClickListener(v -> SelectedCityFragment.this.callSearchList());

        //blog
        if (blogViewPager.get() != null && blogViewPager.get() != null && blogViewPager.get() != null) {
            blogViewPager.get().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    if (searchKeywordOnFocus) {
                        binding.get().searchBoxEditText.clearFocus();
                    }
                }

                @Override
                public void onPageSelected(int position) {

                    blogCurrentPage = position;

                    if (blogIndicatorLayout.get() != null) {

                        setupBlogSliderPagination();
                    }

                    for (ImageView dot : blogDots) {
                        if (blogDots != null) {
                            dot.setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                        }
                    }

                    if (blogDots != null && blogDots.length > position) {
                        blogDots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
                    }

                    blogTouched = true;

                    blogHandler.removeCallbacks(blogUpdate);

                    setBlogUnTouchedTimer();

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        startBlogPagerAutoSwipe();

        //top view pager
        if (homeViewPager.get() != null && homeViewPager.get() != null && homeViewPager.get() != null) {
            homeViewPager.get().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    if (searchKeywordOnFocus) {
                        binding.get().searchBoxEditText.clearFocus();
                    }
                }

                @Override
                public void onPageSelected(int position) {

                    topCurrentPage = position;

                    topTouched = true;

                    topHandler.removeCallbacks(topUpdate);

                    setTopUnTouchedTimer();

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        startHomeBannerPagerAutoSwipe();

        if (force_update) {
            navigationController.navigateToForceUpdateActivity(this.getActivity(), force_update_title, force_update_msg);
        }
    }

    private void callSearchList() {

        searchItemParameterHolder.keyword = binding.get().searchBoxEditText.getText().toString();

        navigationController.navigateToHomeFilteringActivity(getActivity(), searchItemParameterHolder, searchItemParameterHolder.keyword, selectedCityLat, selectedCityLng, Constants.MAP_MILES);

    }


    @Override
    protected void initViewModels() {
        itemManufacturerViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemManufacturerViewModel.class);
        recentItemViewModel = new ViewModelProvider(this, viewModelFactory).get(RecentItemViewModel.class);
        popularItemViewModel = new ViewModelProvider(this, viewModelFactory).get(PopularItemViewModel.class);
        blogViewModel = new ViewModelProvider(this, viewModelFactory).get(BlogViewModel.class);
        itemFromFollowerViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemFromFollowerViewModel.class);
        homeBannerViewModel = new ViewModelProvider(this, viewModelFactory).get(HomeBannerViewModel.class);

    }

    @Override
    protected void initAdapters() {


        DashBoardViewPagerAdapter nvAdapter3 = new DashBoardViewPagerAdapter(dataBindingComponent, blog -> navigationController.navigateToBlogDetailActivity(SelectedCityFragment.this.getActivity(), blog.id));

        this.blogViewPagerAdapter = new AutoClearedValue<>(this, nvAdapter3);
        blogViewPager.get().setAdapter(blogViewPagerAdapter.get());

        DashBoardHomeBannerViewPagerAdapter topAdapter = new DashBoardHomeBannerViewPagerAdapter(dataBindingComponent);
        this.homeBannerViewPagerAdapter = new AutoClearedValue<>(this, topAdapter);
        homeViewPager.get().setAdapter(homeBannerViewPagerAdapter.get());

        CityManufacturerAdapter cityManufacturerAdapter = new CityManufacturerAdapter(dataBindingComponent,
                manufacturer -> navigationController.navigateToSubCategoryActivity(getActivity(), manufacturer.id, manufacturer.name), this);

        this.cityManufacturerAdapter = new AutoClearedValue<>(this, cityManufacturerAdapter);
        binding.get().cityCategoryRecyclerView.setAdapter(cityManufacturerAdapter);


        ItemHorizontalListAdapter followerItemListAdapter = new ItemHorizontalListAdapter(dataBindingComponent, item -> navigationController.navigateToItemDetailActivity(SelectedCityFragment.this.getActivity(), item.id, item.title), this);
        this.followerItemListAdapter = new AutoClearedValue<>(this, followerItemListAdapter);
        binding.get().followerRecyclerView.setAdapter(followerItemListAdapter);

        ItemHorizontalListAdapter popularAdapter = new ItemHorizontalListAdapter(dataBindingComponent, item -> navigationController.navigateToItemDetailActivity(SelectedCityFragment.this.getActivity(), item.id, item.title), this);

        this.popularItemListAdapter = new AutoClearedValue<>(this, popularAdapter);
        binding.get().popularItemRecyclerView.setAdapter(popularAdapter);

        ItemHorizontalListAdapter recentAdapter = new ItemHorizontalListAdapter(dataBindingComponent, item ->
                navigationController.navigateToItemDetailActivity(this.getActivity(), item.id, item.title), this);

        this.recentItemListAdapter = new AutoClearedValue<>(this, recentAdapter);
        binding.get().recentItemRecyclerView.setAdapter(recentAdapter);


    }

    private void replaceItemFromFollowerList(List<Item> itemList) {
        this.followerItemListAdapter.get().replace(itemList);
        binding.get().executePendingBindings();
    }

    private void replaceRecentItemList(List<Item> itemList) {
        this.recentItemListAdapter.get().replace(itemList);
        binding.get().executePendingBindings();
    }

    private void replacePopularItemList(List<Item> itemList) {
        this.popularItemListAdapter.get().replace(itemList);
        binding.get().executePendingBindings();
    }

    private void replaceCityCategory(List<Manufacturer> categories) {
        cityManufacturerAdapter.get().replace(categories);
        binding.get().executePendingBindings();
    }


    @Override
    protected void initData() {

        showItemFromFollower();

        loadProducts();
    }

    private void showItemFromFollower() {
        if (loginUserId.isEmpty()) {
            hideForFollower();
        } else {
            showForFollower();
        }
    }

    private void showForFollower() {

        binding.get().followerConstraintLayout.setVisibility(View.VISIBLE);
        binding.get().followerTitleTextView.setVisibility(View.VISIBLE);
        binding.get().followerViewAllTextView.setVisibility(View.VISIBLE);
        binding.get().followerDescTextView.setVisibility(View.VISIBLE);
        binding.get().followerRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideForFollower() {

        binding.get().followerConstraintLayout.setVisibility(View.GONE);
        binding.get().followerTitleTextView.setVisibility(View.GONE);
        binding.get().followerViewAllTextView.setVisibility(View.GONE);
        binding.get().followerDescTextView.setVisibility(View.GONE);
        binding.get().followerRecyclerView.setVisibility(View.GONE);
    }

    private void getIntentData() {

        if (getActivity() != null) {

            recentItemViewModel.locationId = selected_location_id;
            recentItemViewModel.locationName = selected_location_name;
            recentItemViewModel.locationLat = selectedLat;
            recentItemViewModel.locationLng = selectedLng;

            recentItemViewModel.recentItemParameterHolder.location_id = recentItemViewModel.locationId;
            popularItemViewModel.popularItemParameterHolder.location_id = recentItemViewModel.locationId;
            searchItemParameterHolder.location_id = recentItemViewModel.locationId;

            binding.get().locationTextView.setText(recentItemViewModel.locationName);

        }
    }

    private void loadProducts() {

        //Blog

        blogViewModel.setNewsFeedObj(String.valueOf(Config.LIST_NEW_FEED_COUNT_PAGER), String.valueOf(blogViewModel.offset));

        blogViewModel.getNewsFeedData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:
                        replaceNewsFeedList(result.data);
                        blogViewModel.setLoadingState(false);
                        break;

                    case LOADING:
                        replaceNewsFeedList(result.data);
                        break;

                    case ERROR:

                        blogViewModel.setLoadingState(false);
                        break;
                }
            }

        });


        //Home Banner

        homeBannerViewModel.setHomeBannerObj(String.valueOf(Config.HOME_BANNER_PAGER_COUNT), String.valueOf(homeBannerViewModel.offset));

        homeBannerViewModel.getHomeBannerData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:
                        replaceTopBlogList(result.data);
                        homeBannerViewModel.setLoadingState(false);
                        break;

                    case LOADING:
                        replaceTopBlogList(result.data);
                        break;

                    case ERROR:

                        blogViewModel.setLoadingState(false);
                        break;
                }
            }

        });


        //City Category

        itemManufacturerViewModel.setManufacturerListObj(String.valueOf(Config.LIMIT_MANUFACTURER_COUNT_IN_DASHBOARD), Constants.ZERO);

        itemManufacturerViewModel.getManufacturerListData().observe(this, listResource -> {

            if (listResource != null) {

                switch (listResource.status) {
                    case SUCCESS:

                        if (listResource.data != null) {

                            if (listResource.data.size() > 0) {
                                replaceCityCategory(listResource.data);
                            }

                        }
                        itemManufacturerViewModel.setLoadingState(false);

                        break;

                    case LOADING:

                        if (listResource.data != null) {

                            if (listResource.data.size() > 0) {
                                replaceCityCategory(listResource.data);
                            }

                        }

                        break;

                    case ERROR:
                        itemManufacturerViewModel.setLoadingState(false);
                        break;
                }
            }
        });

        //Popular Item

        popularItemViewModel.setPopularItemListByKeyObj(Utils.checkUserId(loginUserId), Config.LIMIT_FROM_DB_COUNT, Constants.ZERO, popularItemViewModel.popularItemParameterHolder);

        popularItemViewModel.getPopularItemListByKeyData().observe(this, listResource -> {

            if (listResource != null) {
                switch (listResource.status) {
                    case SUCCESS:

                        if (listResource.data != null) {
                            if (listResource.data.size() > 0) {
                                replacePopularItemList(listResource.data);
                            }
                        }

                        popularItemViewModel.setLoadingState(false);

                        break;

                    case LOADING:

                        if (listResource.data != null) {
                            if (listResource.data.size() > 0) {
                                replacePopularItemList(listResource.data);
                            }
                        }

                        break;

                    case ERROR:
                        popularItemViewModel.setLoadingState(false);
                        break;
                }
            }
        });

        //Popular Item

        //Recent Item

        recentItemViewModel.setRecentItemListByKeyObj(Utils.checkUserId(loginUserId), Config.LIMIT_FROM_DB_COUNT, Constants.ZERO, recentItemViewModel.recentItemParameterHolder);

        recentItemViewModel.getRecentItemListByKeyData().observe(this, listResource -> {

            if (listResource != null) {
                switch (listResource.status) {
                    case SUCCESS:

                        if (listResource.data != null) {
                            if (listResource.data.size() > 0) {
                                SelectedCityFragment.this.replaceRecentItemList(listResource.data);
                            }
                        }

                        break;

                    case LOADING:

                        if (listResource.data != null) {
                            if (listResource.data.size() > 0) {
                                SelectedCityFragment.this.replaceRecentItemList(listResource.data);
                            }
                        }

                        recentItemViewModel.setLoadingState(false);

                        break;

                    case ERROR:
                        recentItemViewModel.setLoadingState(false);
                        break;
                }
            }
        });

        // Item from follower

        itemFromFollowerViewModel.setItemFromFollowerListObj(Utils.checkUserId(loginUserId), Config.LIMIT_FROM_DB_COUNT, Constants.ZERO);

        itemFromFollowerViewModel.getItemFromFollowerListData().observe(this, listResource -> {

            if (listResource != null) {
                switch (listResource.status) {
                    case LOADING:

                        if (listResource.data != null) {
                            if (listResource.data.size() > 0) {
                                replaceItemFromFollowerList(listResource.data);
                            }
                        }

                        break;

                    case SUCCESS:

                        if (listResource.data != null) {
                            if (listResource.data.size() > 0) {
                                replaceItemFromFollowerList(listResource.data);
                                showForFollower();
                            }
                        } else {
                            hideForFollower();
                        }
                        itemFromFollowerViewModel.setLoadingState(false);

                        break;

                    case ERROR:
                        itemFromFollowerViewModel.setLoadingState(false);
                        break;
                }
            }
        });

        //endregion


        blogViewPager.get().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {


                if (binding.get() != null && blogViewPager.get() != null) {
                    if (blogViewPager.get().getChildCount() > 0) {
                        blogLayoutDone = true;
                        blogLoadingCount++;
                        hideBlogLoading();
                        blogViewPager.get().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });

        homeViewPager.get().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {


                if (binding.get() != null && homeViewPager.get() != null) {
                    if (homeViewPager.get().getChildCount() > 0) {
                        topLayoutDone = true;
                        topLoadingCount++;
                        hideTopLoading();
                        homeViewPager.get().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }


    @Override
    public void onDispatched() {

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void setupBlogSliderPagination() {

        int blogDotsCount = blogViewPagerAdapter.get().getCount();

        if (blogDotsCount > 0 && blogDots == null) {

            blogDots = new ImageView[blogDotsCount];

            if (binding.get() != null) {
                if (blogIndicatorLayout.get().getChildCount() > 0) {
                    blogIndicatorLayout.get().removeAllViewsInLayout();
                }
            }

            for (int i = 0; i < blogDotsCount; i++) {
                blogDots[i] = new ImageView(getContext());
                blogDots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.setMargins(4, 0, 4, 0);

                blogIndicatorLayout.get().addView(blogDots[i], params);
            }

            blogDots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        }

    }

    private void hideBlogLoading() {

        if (blogLoadingCount == 3 && blogLayoutDone) {

            binding.get().loadingView.setVisibility(View.GONE);
            binding.get().loadHolder.setVisibility(View.GONE);
        }
    }

    private void hideTopLoading() {

        if (topLoadingCount == 3 && topLayoutDone) {

            binding.get().loadingView.setVisibility(View.GONE);
            binding.get().loadHolder.setVisibility(View.GONE);
        }
    }

    private void startBlogPagerAutoSwipe() {

        blogUpdate = () -> {
            if (!blogTouched) {
                if (blogCurrentPage == NUM_PAGES) {
                    blogCurrentPage = 0;
                }

                if (blogViewPager.get() != null) {
                    blogViewPager.get().setCurrentItem(blogCurrentPage++, true);
                }

            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!searchKeywordOnFocus) {
                    blogHandler.post(blogUpdate);
                }
            }
        }, 1000, 3000);
    }

    private void startHomeBannerPagerAutoSwipe() {

        topUpdate = () -> {
            if (!topTouched) {
                if (topCurrentPage == NUM_PAGES) {
                    topCurrentPage = 0;
                }

                if (homeViewPager.get() != null) {
                    homeViewPager.get().setCurrentItem(topCurrentPage++, true);
                }

            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!searchKeywordOnFocus) {
                    topHandler.post(topUpdate);
                }
            }
        }, 1000, 3000);
    }

    private void setBlogUnTouchedTimer() {

        if (blogUnTouchedTimer == null) {
            blogUnTouchedTimer = new Timer();
            blogUnTouchedTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    blogTouched = false;
                    if (!searchKeywordOnFocus) {
                        blogHandler.post(blogUpdate);
                    }
                }
            }, 3000, 6000);
        } else {
            blogUnTouchedTimer.cancel();
            blogUnTouchedTimer.purge();

            blogUnTouchedTimer = new Timer();
            blogUnTouchedTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    blogTouched = false;
                    if (!searchKeywordOnFocus) {
                        blogHandler.post(blogUpdate);
                    }
                }
            }, 3000, 6000);
        }
    }

    private void setTopUnTouchedTimer() {

        if (topUnTouchedTimer == null) {
            topUnTouchedTimer = new Timer();
            topUnTouchedTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    topTouched = false;
                    if (!searchKeywordOnFocus) {
                        topHandler.post(topUpdate);
                    }
                }
            }, 3000, 6000);
        } else {
            topUnTouchedTimer.cancel();
            topUnTouchedTimer.purge();

            topUnTouchedTimer = new Timer();
            topUnTouchedTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    topTouched = false;
                    if (!searchKeywordOnFocus) {
                        topHandler.post(topUpdate);
                    }
                }
            }, 3000, 6000);
        }
    }

    private void replaceNewsFeedList(List<Blog> blogs) {

        this.blogViewPagerAdapter.get().replaceNewsFeedList(blogs);
        binding.get().executePendingBindings();
    }

    private void replaceTopBlogList(List<Banner> banners) {

        this.homeBannerViewPagerAdapter.get().replaceHomeBannerList(banners);
        binding.get().executePendingBindings();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == Constants.REQUEST_CODE__SELECTED_CITY_FRAGMENT
                    && resultCode == Constants.RESULT_CODE__SEARCH_WITH_ITEM_LOCATION_TYPE) {

                recentItemViewModel.locationId = data.getStringExtra(Constants.ITEM_LOCATION_TYPE_ID);
                recentItemViewModel.locationName = data.getStringExtra(Constants.ITEM_LOCATION_TYPE_NAME);
                recentItemViewModel.locationLat = data.getStringExtra(Constants.LAT);
                recentItemViewModel.locationLng = data.getStringExtra(Constants.LNG);

                pref.edit().putString(Constants.SELECTED_LOCATION_ID, recentItemViewModel.locationId).apply();
                pref.edit().putString(Constants.SELECTED_LOCATION_NAME, recentItemViewModel.locationName).apply();
                pref.edit().putString(Constants.LAT, recentItemViewModel.locationLat).apply();
                pref.edit().putString(Constants.LNG, recentItemViewModel.locationLng).apply();


                if (getActivity() != null) {

                    navigationController.navigateToHome((MainActivity) getActivity(), true, recentItemViewModel.locationId,
                            recentItemViewModel.locationName,false);
                }
            }
        }
    }

    @Override
    public void onResume() {
        loadLoginUserId();
        super.onResume();
    }

}
