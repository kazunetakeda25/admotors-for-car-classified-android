package com.panaceasoft.admotors.ui.gallery.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentGalleryDetailBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.TouchImageView;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.image.ImageViewModel;
import com.panaceasoft.admotors.viewobject.Image;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

/**
 * Gallery Detail Fragment
 */
public class GalleryDetailFragment extends PSFragment {


    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    @VisibleForTesting
    private AutoClearedValue<FragmentGalleryDetailBinding> binding;

    private ImageViewModel imageViewModel;

    //endregion


    //region Override Methods

    // Only Open for leakcanary
//    private static void fixLeakCanary696(Context context) {

        // Only Open for leakcanary
        //https://github.com/square/leakcanary/issues/696
//        try {
//            Class clazz = Class.forName(Constants.GALLERY_BOOST);
//            Utils.psLog("clazz " + clazz);
//
//            Field _sGestureBoostManager = clazz.getDeclaredField(Constants.GALLERY_GESTURE);
//            _sGestureBoostManager.setAccessible(true);
//            Field _mContext = clazz.getDeclaredField(Constants.GALLERY_CONTEXT);
//            _mContext.setAccessible(true);
//
//            Object sGestureBoostManager = _sGestureBoostManager.get(null);
//            if (sGestureBoostManager != null) {
//                _mContext.set(sGestureBoostManager, context);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentGalleryDetailBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery_detail, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }

    @Override
    protected void initUIAndActions() {

        binding.get().viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int arg0) {

            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            public void onPageSelected(int currentPage) {

                if (imageViewModel.newsImageList != null) {
                    if (currentPage >= imageViewModel.newsImageList.size()) {
                        currentPage = currentPage % imageViewModel.newsImageList.size();
                    }

                    binding.get().imgDesc.setText(imageViewModel.newsImageList.get(currentPage).imgDesc);
                }

            }

        });
    }

    @Override
    protected void initViewModels() {
        imageViewModel = new ViewModelProvider(this, viewModelFactory).get(ImageViewModel.class);
    }

    @Override
    protected void initAdapters() {

    }

    //endregion


    //region Pager Adapter Class

    @Override
    protected void initData() {

        try {

            if (getActivity() != null) {
                imageViewModel.imgType = getActivity().getIntent().getStringExtra(Constants.IMAGE_TYPE);
                imageViewModel.id = getActivity().getIntent().getStringExtra(Constants.ITEM_ID);
                imageViewModel.imgId = getActivity().getIntent().getStringExtra(Constants.IMAGE_ID);
            }

        } catch (Exception e) {
            Utils.psErrorLog("Error in getting intent.", e);
        }

        LiveData<Resource<List<Image>>> imageListLiveData = imageViewModel.getImageListLiveData();
        imageViewModel.setImageParentId(imageViewModel.imgType, imageViewModel.id);
        imageListLiveData.observe(this, listResource -> {
            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            if (listResource != null && listResource.data != null) {
                Utils.psLog("Got Data");

                // Update the data
                imageViewModel.newsImageList = listResource.data;

                int selectedItemPosition = 0;

                for (int i = 0; i < imageViewModel.newsImageList.size(); i++) {
                    if (imageViewModel.newsImageList.get(i).imgId.equals(imageViewModel.imgId)) {
                        selectedItemPosition = i;
                        break;
                    }
                }

                binding.get().viewPager.setAdapter(new TouchImageAdapter());

                try {
                    binding.get().viewPager.setCurrentItem(selectedItemPosition);

                    binding.get().imgDesc.setText(imageViewModel.newsImageList.get(selectedItemPosition).imgDesc);
                } catch (Exception e) {
                    Utils.psErrorLog("", e);
                }

            } else {
                //noinspection Constant Conditions
                Utils.psLog("Empty Data");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Only Open for leakcanary
//        if(getActivity() != null) {
//            fixLeakCanary696(getActivity().getApplicationContext());
//        }
    }

    class TouchImageAdapter extends PagerAdapter {

        private TouchImageAdapter() {
        }

        @Override
        public int getCount() {
            if (imageViewModel.newsImageList != null) {
                return imageViewModel.newsImageList.size();
            }

            return 0;

        }

        @Override
        @NonNull
        public View instantiateItem(@NonNull ViewGroup container, int position) {

            TouchImageView imgView = new TouchImageView(container.getContext());
            if (imageViewModel.newsImageList != null) {
                if (position >= imageViewModel.newsImageList.size()) {
                    position = position % imageViewModel.newsImageList.size();
                }

                if (getActivity() != null) {

                    dataBindingComponent.getFragmentBindingAdapters().bindFullImage(imgView,  imageViewModel.newsImageList.get(position).imgPath);

                    container.addView(imgView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                }
            }
            return imgView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

    }

    //endregion

}
