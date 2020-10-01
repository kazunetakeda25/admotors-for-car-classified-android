package com.panaceasoft.admotors.ui.setting.appinfo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentAppInfoBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.aboutus.AboutUsViewModel;
import com.panaceasoft.admotors.viewobject.AboutUs;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppInfoFragment extends PSFragment {

    //region Variables
    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private AboutUsViewModel aboutUsViewModel;

    private static final int REQUEST_CALL = 1;

    @VisibleForTesting
    private AutoClearedValue<FragmentAppInfoBinding> binding;
    //endregion

    //region Override Methods
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentAppInfoBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_app_info, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);
        setHasOptionsMenu(true);

        return binding.get().getRoot();
    }


    @Override
    protected void initUIAndActions() {

        //For phone
        binding.get().phoneTextView.setOnClickListener(v -> {
            String number = binding.get().phoneTextView.getText().toString();
            if (!(number.trim().isEmpty() || number.trim().equals("-"))) {
                Utils.callPhone(this, number);
            }
        });

        //For website
        binding.get().WebsiteTextView.setOnClickListener(view -> {
            String url = binding.get().WebsiteTextView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        //For facebook
        binding.get().facebookTextView.setOnClickListener(view -> {
            String url = binding.get().facebookTextView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        //For google plus
        binding.get().gplusTextView.setOnClickListener(view -> {
            String url = binding.get().gplusTextView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        //For twitter
        binding.get().twitterTextView.setOnClickListener(view -> {
            String url = binding.get().twitterTextView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        //For instagram
        binding.get().instaTextView.setOnClickListener(view -> {
            String url = binding.get().instaTextView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        //For youtube
        binding.get().youtubeTextView.setOnClickListener(view -> {
            String url = binding.get().youtubeTextView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        //For pinterest
        binding.get().pinterestTextView.setOnClickListener(view -> {
            String url = binding.get().pinterestTextView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
    }

    @Override
    protected void initViewModels() {
        aboutUsViewModel = new ViewModelProvider(this, viewModelFactory).get(AboutUsViewModel.class);
    }

    @Override
    protected void initAdapters() {
    }

    //  private  void replaceAboutUsData()
    @Override
    protected void initData() {

        aboutUsViewModel.setAboutUsObj("about us");
        aboutUsViewModel.getAboutUsData().observe(this, resource -> {

            if (resource != null) {

                switch (resource.status) {
                    case LOADING:
                        // Loading State
                        // Data are from Local DB

                        if (resource.data != null) {

                            fadeIn(binding.get().getRoot());

                        }
                        break;
                    case SUCCESS:
                        // Success State
                        // Data are from Server

                        if (resource.data != null) {

                            setAboutUsData(resource.data);
                        }

                        break;
                    case ERROR:
                        // Error State

                        break;
                    default:
                        // Default

                        break;
                }

            } else {

                // Init Object or Empty Data
                Utils.psLog("Empty Data");

            }


            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            if (resource != null) {
                Utils.psLog("Got Data Of About Us.");


            } else {
                //noinspection ConstantConditions
                Utils.psLog("No Data of About Us.");
            }
        });
    }

    private void setAboutUsData(AboutUs aboutUs) {

        binding.get().setAboutUs(aboutUs);

        //For Pinterest.com
        if (aboutUs.pinterest.equals("")) {
            binding.get().pinterestImage.setVisibility(View.GONE);
            binding.get().pinterestTextView.setVisibility(View.GONE);
            binding.get().pinterestTitleTextView.setVisibility(View.GONE);
        } else {

            binding.get().pinterestTextView.setText(aboutUs.pinterest);
            binding.get().pinterestImage.setVisibility(View.VISIBLE);
            binding.get().pinterestTextView.setVisibility(View.VISIBLE);
            binding.get().pinterestTitleTextView.setVisibility(View.VISIBLE);
        }

        // For youtube.com
        if (aboutUs.youtube.equals("")) {
            binding.get().youtubeImage.setVisibility(View.GONE);
            binding.get().youtubeTextView.setVisibility(View.GONE);
            binding.get().youTubeTitleTextView.setVisibility(View.GONE);
        } else {

            binding.get().youtubeTextView.setText(aboutUs.youtube);
            binding.get().youtubeImage.setVisibility(View.VISIBLE);
            binding.get().youtubeTextView.setVisibility(View.VISIBLE);
            binding.get().youTubeTitleTextView.setVisibility(View.VISIBLE);
        }

        // For instagram.com
        if (aboutUs.instagram.equals("")) {
            binding.get().instagramImage.setVisibility(View.GONE);
            binding.get().instaTextView.setVisibility(View.GONE);
            binding.get().instaTitleTextView.setVisibility(View.GONE);
        } else {

            binding.get().instaTextView.setText(aboutUs.instagram);
            binding.get().instagramImage.setVisibility(View.VISIBLE);
            binding.get().instaTextView.setVisibility(View.VISIBLE);
            binding.get().instaTitleTextView.setVisibility(View.VISIBLE);
        }

        // For twitter.com
        if (aboutUs.twitter.equals("")) {
            binding.get().twitterImage.setVisibility(View.GONE);
            binding.get().twitterTextView.setVisibility(View.GONE);
            binding.get().twitterTitleTextView.setVisibility(View.GONE);
        } else {

            binding.get().twitterTextView.setText(aboutUs.twitter);
            binding.get().twitterImage.setVisibility(View.VISIBLE);
            binding.get().twitterTextView.setVisibility(View.VISIBLE);
            binding.get().twitterTitleTextView.setVisibility(View.VISIBLE);
        }

        // For googleplus.com
        if (aboutUs.googlePlus.equals("")) {
            binding.get().gplusImage.setVisibility(View.GONE);
            binding.get().gplusTextView.setVisibility(View.GONE);
            binding.get().gplusTitleTextView.setVisibility(View.GONE);
        } else {

            binding.get().gplusTextView.setText(aboutUs.googlePlus);
            binding.get().gplusImage.setVisibility(View.VISIBLE);
            binding.get().gplusTextView.setVisibility(View.VISIBLE);
            binding.get().gplusTitleTextView.setVisibility(View.VISIBLE);
        }

        // For facebook.com
        if (aboutUs.facebook.equals("")) {
            binding.get().facebookImage.setVisibility(View.GONE);
            binding.get().facebookTextView.setVisibility(View.GONE);
            binding.get().facebookTitleTextView.setVisibility(View.GONE);
        } else {

            binding.get().facebookTextView.setText(aboutUs.facebook);
            binding.get().facebookImage.setVisibility(View.VISIBLE);
            binding.get().facebookTextView.setVisibility(View.VISIBLE);
            binding.get().facebookTitleTextView.setVisibility(View.VISIBLE);
        }

        // For phone
        if (aboutUs.aboutPhone.equals("")) {
            binding.get().phoneImage.setVisibility(View.GONE);
            binding.get().phoneTextView.setVisibility(View.GONE);
            binding.get().phoneTitleTextView.setVisibility(View.GONE);
        } else {

            binding.get().phoneTextView.setText(aboutUs.aboutPhone);
            binding.get().phoneImage.setVisibility(View.VISIBLE);
            binding.get().phoneTextView.setVisibility(View.VISIBLE);
            binding.get().phoneTitleTextView.setVisibility(View.VISIBLE);
        }

        // For website.com
        if (aboutUs.aboutWebsite.equals("")) {
            binding.get().webImage.setVisibility(View.GONE);
            binding.get().WebsiteTextView.setVisibility(View.GONE);
            binding.get().websiteTitleTextView.setVisibility(View.GONE);
        } else {

            binding.get().WebsiteTextView.setText(aboutUs.aboutWebsite);
            binding.get().webImage.setVisibility(View.VISIBLE);
            binding.get().WebsiteTextView.setVisibility(View.VISIBLE);
            binding.get().websiteTitleTextView.setVisibility(View.VISIBLE);
        }

        // For description
        if (aboutUs.aboutDescription.equals("")) {
            binding.get().descTextView.setVisibility(View.GONE);
        } else {

            binding.get().descTextView.setText(aboutUs.aboutDescription);
            binding.get().descTextView.setVisibility(View.VISIBLE);

        }

        // For title
        if (aboutUs.aboutTitle.equals("")) {
            binding.get().titleTextView.setVisibility(View.GONE);
        } else {

            binding.get().titleTextView.setText(aboutUs.aboutTitle);
            binding.get().titleTextView.setVisibility(View.VISIBLE);
        }

    }
    //endregion

}