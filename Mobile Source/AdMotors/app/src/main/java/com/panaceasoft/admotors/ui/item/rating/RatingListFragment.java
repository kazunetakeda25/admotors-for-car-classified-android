package com.panaceasoft.admotors.ui.item.rating;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentRatingListBinding;
import com.panaceasoft.admotors.databinding.ItemRatingEntryBinding;
import com.panaceasoft.admotors.ui.common.DataBoundListAdapter;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.ui.item.rating.adapter.RatingListAdapter;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.PSDialogMsg;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.rating.RatingViewModel;
import com.panaceasoft.admotors.viewmodel.user.UserViewModel;
import com.panaceasoft.admotors.viewobject.Rating;
import com.panaceasoft.admotors.viewobject.User;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingListFragment extends PSFragment implements DataBoundListAdapter.DiffUtilDispatchedInterface {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    //    private ItemViewModel itemViewModel;
    private RatingViewModel ratingViewModel;
    private UserViewModel userViewModel;
    private PSDialogMsg psDialogMsg, psDialogRatingMsg;

    @VisibleForTesting
    private AutoClearedValue<RatingListAdapter> adapter;
    private AutoClearedValue<FragmentRatingListBinding> binding;
    private AutoClearedValue<Dialog> dialog;
    private AutoClearedValue<ProgressDialog> prgDialog;
    private AutoClearedValue<ItemRatingEntryBinding> itemRatingEntryBinding;

    public RatingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRatingListBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_rating_list, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        setHasOptionsMenu(true);
        return binding.get().getRoot();
    }

    @Override
    protected void initUIAndActions() {

        if (Config.SHOW_ADMOB && connectivity.isConnected()) {
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            binding.get().adView.loadAd(adRequest);
        } else {
            binding.get().adView.setVisibility(View.GONE);
        }

        psDialogMsg = new PSDialogMsg(getActivity(), false);
        psDialogRatingMsg = new PSDialogMsg(getActivity(), false);

        prgDialog = new AutoClearedValue<>(this, new ProgressDialog(getActivity()));
        prgDialog.get().setMessage((Utils.getSpannableString(getContext(), getString(R.string.message__please_wait), Utils.Fonts.MM_FONT)));
        prgDialog.get().setCancelable(false);

        binding.get().ratingListRecyclerView.setNestedScrollingEnabled(false);
        binding.get().writeReviewButton.setOnClickListener(v -> Utils.navigateOnUserVerificationActivity(userIdToVerify, loginUserId, psDialogMsg, RatingListFragment.this.getActivity(), navigationController, new Utils.NavigateOnUserVerificationActivityCallback() {
            @Override
            public void onSuccess() {
                getCustomLayoutDialog();
            }
        }));


        binding.get().ratingListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        recyclerView.getLayoutManager();

                if (layoutManager != null) {

                    int lastPosition = layoutManager
                            .findLastVisibleItemPosition();

                    if (lastPosition == adapter.get().getItemCount() - 1) {

                        if (!ratingViewModel.forceEndLoading) {

                            ratingViewModel.loadingDirection = Utils.LoadingDirection.bottom;

                            int limit = Config.ITEM_COUNT;

                            ratingViewModel.offset = ratingViewModel.offset + limit;

                            ratingViewModel.setNextPageLoadingStateObj(ratingViewModel.userId, String.valueOf(Config.RATING_COUNT), Constants.ZERO);

                        }
                    }
                }
            }
        });

    }

    @Override
    protected void initViewModels() {
//        itemViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemViewModel.class);
        ratingViewModel = new ViewModelProvider(this, viewModelFactory).get(RatingViewModel.class);
        userViewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);
    }

    @Override
    protected void initAdapters() {
        RatingListAdapter nvAdapter = new RatingListAdapter(dataBindingComponent,
                rating -> {
//                        navigationController.navigateToAboutUs(getContext());
                }, this);
        this.adapter = new AutoClearedValue<>(this, nvAdapter);
        binding.get().ratingListRecyclerView.setAdapter(nvAdapter);
    }

    @Override
    public void onDispatched() {

    }


    @Override
    protected void initData() {
        try {
            if (getActivity() != null) {
                if (getActivity().getIntent() != null) {
                    if (getActivity().getIntent().getExtras() != null) {

                        ratingViewModel.userId = getActivity().getIntent().getExtras().getString(Constants.ITEM_USER_ID);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //User

        userViewModel.getOtherUser(loginUserId,ratingViewModel.userId).observe(this, listResource -> {

            if (listResource != null) {

                Utils.psLog("Got Data" + listResource.message + listResource.toString());

                switch (listResource.status) {
                    case LOADING:
                        // Loading State
                        // Data are from Local DB

                        if (listResource.data != null) {
                            fadeIn(binding.get().getRoot());
                            bindingProgressBar(listResource.data);
                            bindingRatingData(listResource.data);
                        }

                        break;
                    case SUCCESS:
                        // Success State
                        // Data are from Server

                        if (listResource.data != null) {

                            bindingProgressBar(listResource.data);
                            bindingRatingData(listResource.data);
                            if (listResource.data.isFollowed != null) {

                                userViewModel.isFollowed = listResource.data.isFollowed;

                            }

                        }

                        break;
                    case ERROR:
                        // Error State

                        psDialogMsg.showErrorDialog(listResource.message, getString(R.string.app__ok));
                        psDialogMsg.show();

                        userViewModel.isLoading = false;

                        break;
                    default:
                        // Default
                        userViewModel.isLoading = false;

                        break;
                }

            } else {

                // Init Object or Empty Data
                Utils.psLog("Empty Data");

            }

            // we don't need any null checks here for the ModelAdapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            if (listResource != null && listResource.data != null) {
                Utils.psLog("Got Data");


            } else {
                //noinspection Constant Conditions
                Utils.psLog("Empty Data");

            }
        });

        //rating list
        ratingViewModel.setRatingListObj(ratingViewModel.userId, String.valueOf(Config.RATING_COUNT), Constants.ZERO);
        LiveData<Resource<List<Rating>>> news = ratingViewModel.getRatingList();

        if (news != null) {

            news.observe(this, (Resource<List<Rating>> listResource) -> {
                if (listResource != null) {

                    Utils.psLog("Got Data" + listResource.message + listResource.toString());

                    switch (listResource.status) {
                        case LOADING:
                            // Loading State
                            // Data are from Local DB

                            if (listResource.data != null) {
                                //fadeIn Animation
                                fadeIn(binding.get().getRoot());

                                // Update the data
                                replaceRatingData(listResource.data);

                            }

                            break;

                        case SUCCESS:
                            // Success State
                            // Data are from Server

                            if (listResource.data != null) {
                                // Update the data
                                replaceRatingData(listResource.data);
                                ratingViewModel.isData = listResource.data.size() == 0;


                            }

                            ratingViewModel.setLoadingState(false);

                            break;

                        case ERROR:
                            // Error State

                            ratingViewModel.setLoadingState(false);


                            break;
                        default:
                            // Default

                            break;
                    }

                } else {

                    // Init Object or Empty Data
                    Utils.psLog("Empty Data");

                    if (ratingViewModel.offset > 1) {
                        // No more data for this list
                        // So, Block all future loading
                        ratingViewModel.forceEndLoading = true;
                    }

                }

            });
        }

        // get rating post method
        ratingViewModel.getRatingPostData().observe(this, result -> {
            if (result != null) {
                if (result.status == Status.SUCCESS) {
                    if (RatingListFragment.this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                        ratingViewModel.setLoadingState(false);

                        //itemViewModel.setItemDetailObj(itemViewModel.itemId, itemViewModel.historyFlag, loginUserId);

                        dialog.get().dismiss();
                        dialog.get().cancel();
                        prgDialog.get().dismiss();
                        prgDialog.get().cancel();
                    }

                } else if (result.status == Status.ERROR) {
                    if (RatingListFragment.this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                        ratingViewModel.setLoadingState(false);
                        prgDialog.get().dismiss();
                        prgDialog.get().cancel();
                    }
                }
            }
        });

        ratingViewModel.getNextPageLoadingStateData().observe(this, state -> {
            if (state != null) {
                if (state.status == Status.ERROR) {
                    Utils.psLog("Next Page State : " + state.data);

                    ratingViewModel.setLoadingState(false);
                    ratingViewModel.forceEndLoading = true;
                }
            }
        });

        ratingViewModel.getLoadingState().observe(this, loadingState -> binding.get().setLoadingMore(ratingViewModel.isLoading));

        //end region

    }

    private void bindingProgressBar(User user) {

        binding.get().ProgressBar1.setProgress((int) user.ratingDetails.fiveStarPercent);
        binding.get().ProgressBar2.setProgress((int) user.ratingDetails.fourStarPercent);
        binding.get().ProgressBar3.setProgress((int) user.ratingDetails.threeStarPercent);
        binding.get().ProgressBar4.setProgress((int) user.ratingDetails.twoStarPercent);
        binding.get().ProgressBar5.setProgress((int) user.ratingDetails.oneStarPercent);


        setDrawableTint(binding.get().ProgressBar1);
        setDrawableTint(binding.get().ProgressBar2);
        setDrawableTint(binding.get().ProgressBar3);
        setDrawableTint(binding.get().ProgressBar4);
        setDrawableTint(binding.get().ProgressBar5);

    }

    private void setDrawableTint(ProgressBar progressBar) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable wrapDrawable = DrawableCompat.wrap(progressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(binding.get().getRoot().getContext(), R.color.md_yellow_A700));
            progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
            progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        } else {
            progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(binding.get().getRoot().getContext(), R.color.md_yellow_A700), PorterDuff.Mode.SRC_IN);
        }
    }

    private void getCustomLayoutDialog() {
        dialog = new AutoClearedValue<>(this, new Dialog(binding.get().getRoot().getContext()));
        itemRatingEntryBinding = new AutoClearedValue<>(this, DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.item_rating_entry, null, false, dataBindingComponent));

        dialog.get().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setContentView(R.layout.item_rating_entry);
        dialog.get().setContentView(itemRatingEntryBinding.get().getRoot());

        itemRatingEntryBinding.get().ratingBarDialog.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            ratingViewModel.numStar = rating;
            itemRatingEntryBinding.get().ratingBarDialog.setRating(rating);
        });

        itemRatingEntryBinding.get().cancelButton.setOnClickListener(v -> {
            dialog.get().dismiss();
            dialog.get().cancel();
        });

        itemRatingEntryBinding.get().submitButton.setOnClickListener(v -> {

            if (itemRatingEntryBinding.get().titleEditText.getText().toString().isEmpty() ||
                    itemRatingEntryBinding.get().messageEditText.getText().toString().isEmpty() || String.valueOf(itemRatingEntryBinding.get().ratingBarDialog.getRating()).equals("0.0")) {

                psDialogRatingMsg.showErrorDialog(getString(R.string.error_message__rating), getString(R.string.app__ok));
                psDialogRatingMsg.show();
                psDialogRatingMsg.okButton.setOnClickListener(v1 -> {
                    psDialogRatingMsg.cancel();
                });
            } else {
                ratingViewModel.setRatingPostObj(itemRatingEntryBinding.get().titleEditText.getText().toString(),
                        itemRatingEntryBinding.get().messageEditText.getText().toString(),
                        ratingViewModel.numStar + "",
                        loginUserId, ratingViewModel.userId);

                prgDialog.get().show();

                if (!ratingViewModel.isData) {
                    ratingViewModel.setLoadingState(false);
                    ratingViewModel.setRatingListObj(ratingViewModel.userId, String.valueOf(Config.RATING_COUNT), Constants.ZERO);
                }
            }

        });


        Window window = dialog.get().getWindow();
        if (dialog.get() != null && window != null) {
            WindowManager.LayoutParams params = getLayoutParams(dialog.get());
            if (params != null) {
                window.setAttributes(params);
            }
        }

        dialog.get().show();

    }

    private WindowManager.LayoutParams getLayoutParams(@NonNull Dialog dialog) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (dialog.getWindow() != null) {
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
        }
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        return layoutParams;
    }


    private void bindingRatingData(User user) {

        binding.get().totalRatingCountTextView.setText(getString(R.string.rating__total_count, String.valueOf(user.ratingDetails.totalRatingCount)));
        binding.get().totalRatingValueTextView.setText(getString(R.string.rating__total_value, String.valueOf((int) user.ratingDetails.totalRatingValue)));

        binding.get().ratingCountTextView1.setText(getString(R.string.rating__five_star));
        binding.get().ratingPercentTextView1.setText(getString(R.string.rating__percent, String.valueOf((int) user.ratingDetails.fiveStarPercent)));

        binding.get().ratingCountTextView2.setText(getString(R.string.rating__four_star));
        binding.get().ratingPercentTextView2.setText(getString(R.string.rating__percent, String.valueOf((int) user.ratingDetails.fourStarPercent)));

        binding.get().ratingCountTextView3.setText(getString(R.string.rating__three_star));
        binding.get().ratingPercentTextView3.setText(getString(R.string.rating__percent, String.valueOf((int) user.ratingDetails.threeStarPercent)));

        binding.get().ratingCountTextView4.setText(getString(R.string.rating__two_star));
        binding.get().ratingPercentTextView4.setText(getString(R.string.rating__percent, String.valueOf((int) user.ratingDetails.twoStarPercent)));

        binding.get().ratingCountTextView5.setText(getString(R.string.rating__one_star));
        binding.get().ratingPercentTextView5.setText(getString(R.string.rating__percent, String.valueOf((int) user.ratingDetails.oneStarPercent)));

        binding.get().ratingBar.setRating(user.ratingDetails.totalRatingValue);


    }

    private void replaceRatingData(List<Rating> ratingList) {
        adapter.get().replace(ratingList);
        binding.get().executePendingBindings();
    }

    @Override
    public void onResume() {
        loadLoginUserId();
        super.onResume();
    }
}
