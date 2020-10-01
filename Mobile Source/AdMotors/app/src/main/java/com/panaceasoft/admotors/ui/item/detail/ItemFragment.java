package com.panaceasoft.admotors.ui.item.detail;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentItemBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.PSDialogMsg;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.utils.ViewAnimationUtil;
import com.panaceasoft.admotors.viewmodel.aboutus.AboutUsViewModel;
import com.panaceasoft.admotors.viewmodel.item.FavouriteViewModel;
import com.panaceasoft.admotors.viewmodel.item.ItemViewModel;
import com.panaceasoft.admotors.viewmodel.item.SpecsViewModel;
import com.panaceasoft.admotors.viewmodel.item.TouchCountViewModel;
import com.panaceasoft.admotors.viewmodel.rating.RatingViewModel;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.common.Status;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends PSFragment {

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private ItemViewModel itemViewModel;
    private TouchCountViewModel touchCountViewModel;
    private AboutUsViewModel aboutUsViewModel;
    private FavouriteViewModel favouriteViewModel;
    private SpecsViewModel specsViewModel;
    private RatingViewModel ratingViewModel;
    private PSDialogMsg psDialogMsg;
    private ImageView imageView;

    @VisibleForTesting
    private AutoClearedValue<FragmentItemBinding> binding;
    private AutoClearedValue<ProgressDialog> prgDialog;

    //endregion

    //region Override Methods
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentItemBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        imageView = binding.get().coverUserImageView;

        return binding.get().getRoot();
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initUIAndActions() {

        psDialogMsg = new PSDialogMsg(getActivity(), false);
        psDialogMsg.showInfoDialog(getString(R.string.error_message__login_first), getString(R.string.app__ok));

        prgDialog = new AutoClearedValue<>(this, new ProgressDialog(getActivity()));
        prgDialog.get().setMessage((Utils.getSpannableString(getContext(), getString(R.string.message__please_wait), Utils.Fonts.MM_FONT)));
        prgDialog.get().setCancelable(false);

        binding.get().phoneTextView.setOnClickListener(v -> {
            String number = binding.get().phoneTextView.getText().toString();
            if (!(number.trim().isEmpty() || number.trim().equals("-"))) {
                Utils.callPhone(this, number);
            }
        });

        binding.get().callButton.setOnClickListener(v -> {
            String number = binding.get().phoneTextView.getText().toString();
            if (!(number.trim().isEmpty() || number.trim().equals("-"))) {
                Utils.callPhone(this, number);
            }
        });

        binding.get().viewOnMapTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationController.navigateToMapActivity(getActivity(),itemViewModel.itemContainer.lng,itemViewModel.itemContainer.lat,Constants.MAP);
            }
        });

        binding.get().safetyTipButton.setOnClickListener(v -> navigationController.navigateToSafetyTipsActivity(getActivity()));

        binding.get().userCardView.setOnClickListener(v -> navigationController.navigateToUserDetail(getActivity(), itemViewModel.otherUserId, itemViewModel.otherUserName));

        binding.get().userNameActiveHourTextView.setOnClickListener(v -> navigationController.navigateToUserDetail(getActivity(), itemViewModel.otherUserId, itemViewModel.otherUserName));

        binding.get().menuImageView.setOnClickListener(v -> {

            PopupMenu popupMenu = new PopupMenu(getActivity(), binding.get().menuImageView);
            popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {

                if (item.getTitle().toString().equals(getString(R.string.menu__report_item))) {

                    itemViewModel.setReportItemStatusObj(itemViewModel.itemId, loginUserId);
                } else {//share

                    Bitmap bitmap = getBitmapFromView(getCurrentImageView());
                    shareImageUri(saveImageExternal(bitmap));
                }
                return false;
            });

            popupMenu.show();

        });

        binding.get().countPhotoConstraint.setOnClickListener(v -> navigationController.navigateToGalleryActivity(ItemFragment.this.getActivity(), Constants.IMAGE_TYPE_PRODUCT, itemViewModel.itemId));

        binding.get().coverUserImageView.setOnClickListener(v -> navigationController.navigateToGalleryActivity(ItemFragment.this.getActivity(), Constants.IMAGE_TYPE_PRODUCT, itemViewModel.itemId));

        binding.get().editButton.setOnClickListener(v -> navigationController.navigateToItemEntryActivity(getActivity(), itemViewModel.itemId, itemViewModel.locationId, itemViewModel.locationName));

        binding.get().soldTextView.setOnClickListener(v -> {
            if (binding.get().soldTextView.getText().equals(getResources().getString(R.string.item_detail__mark_sold))) {
                psDialogMsg.showConfirmDialog(getString(R.string.item_detail__confirm_sold_out), getString(R.string.app__ok), getString(R.string.message__cancel_close));
                psDialogMsg.show();

                psDialogMsg.okButton.setOnClickListener(v12 -> {
                    itemViewModel.setMarkAsSoldOutItemObj(itemViewModel.itemId, loginUserId);

                    psDialogMsg.cancel();
                });

                psDialogMsg.cancelButton.setOnClickListener(v1 -> psDialogMsg.cancel());

            }
        });

        binding.get().deleteButton.setOnClickListener(v -> {
            psDialogMsg.showConfirmDialog(getString(R.string.item_detail__confirm_delete), getString(R.string.app__ok), getString(R.string.message__cancel_close));
            psDialogMsg.show();

            psDialogMsg.okButton.setOnClickListener(v12 -> {
                itemViewModel.setDeleteItemObj(itemViewModel.itemId, loginUserId);

                psDialogMsg.cancel();
            });

            psDialogMsg.cancelButton.setOnClickListener(v1 -> psDialogMsg.cancel());

        });

        binding.get().ratingBarInformation.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                navigationController.navigateToRatingList(ItemFragment.this.getActivity(), binding.get().getItem().user.userId);
            }
            return true;
        });

        binding.get().backImageView.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        binding.get().statisticDownImageView.setOnClickListener(v -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtil.expand(binding.get().viewConstraintLayout);
                ViewAnimationUtil.expand(binding.get().reviewConstraintLayout);
            } else {
                ViewAnimationUtil.collapse(binding.get().viewConstraintLayout);
                ViewAnimationUtil.collapse(binding.get().reviewConstraintLayout);
            }
        });

        binding.get().statisticTextView.setOnClickListener(v -> {
            boolean show = Utils.toggleUpDownWithAnimation(binding.get().statisticDownImageView);
            if (show) {
                ViewAnimationUtil.expand(binding.get().viewConstraintLayout);
                ViewAnimationUtil.expand(binding.get().reviewConstraintLayout);
            } else {
                ViewAnimationUtil.collapse(binding.get().viewConstraintLayout);
                ViewAnimationUtil.collapse(binding.get().reviewConstraintLayout);
            }
        });

        binding.get().locationTitleDownImageView.setOnClickListener(v -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                //if add more field ,wrap with constraint
                ViewAnimationUtil.expand(binding.get().addressConstraintLayout);

            } else {
                ViewAnimationUtil.collapse(binding.get().addressConstraintLayout);
            }
        });

        binding.get().locationTitleTextView.setOnClickListener(v -> {
            boolean show = Utils.toggleUpDownWithAnimation(binding.get().locationTitleDownImageView);
            if (show) {
                ViewAnimationUtil.expand(binding.get().addressConstraintLayout);
            } else {
                ViewAnimationUtil.collapse(binding.get().addressConstraintLayout);
            }
        });

        binding.get().meetTheSellerDownImageView.setOnClickListener(v -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtil.expand(binding.get().meetTheSellerConstraintLayout);
            } else {
                ViewAnimationUtil.collapse(binding.get().meetTheSellerConstraintLayout);
            }
        });

        binding.get().meetTheSellerTextView.setOnClickListener(v -> {
            boolean show = Utils.toggleUpDownWithAnimation(binding.get().meetTheSellerDownImageView);
            if (show) {
                ViewAnimationUtil.expand(binding.get().meetTheSellerConstraintLayout);
            } else {
                ViewAnimationUtil.collapse(binding.get().meetTheSellerConstraintLayout);

            }
        });


        binding.get().safetyTipsDownImageView.setOnClickListener(v -> {
            boolean show = Utils.toggleUpDownWithAnimation(v);
            if (show) {
                ViewAnimationUtil.expand(binding.get().safetyTipButton);
                ViewAnimationUtil.expand(binding.get().safetyTextView);

            } else {
                ViewAnimationUtil.collapse(binding.get().safetyTipButton);
                ViewAnimationUtil.collapse(binding.get().safetyTextView);

            }
        });


        binding.get().safetyTitleTextView.setOnClickListener(v -> {
            boolean show = Utils.toggleUpDownWithAnimation(binding.get().safetyTipsDownImageView);
            if (show) {
                ViewAnimationUtil.expand(binding.get().safetyTipButton);
                ViewAnimationUtil.expand(binding.get().safetyTextView);

            } else {
                ViewAnimationUtil.collapse(binding.get().safetyTipButton);
                ViewAnimationUtil.collapse(binding.get().safetyTextView);

            }
        });

        binding.get().favouriteImageView.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

                Item item = binding.get().getItem();
                if (item != null) {
                    favFunction(item, likeButton);
                }

            }

            @Override
            public void unLiked(LikeButton likeButton) {

                Item item = binding.get().getItem();
                if (item != null) {
                    unFavFunction(item, likeButton);
                }
            }
        });

        binding.get().chatButton.setOnClickListener(v -> {

            Utils.navigateOnUserVerificationActivity(userIdToVerify, loginUserId, psDialogMsg, ItemFragment.this.getActivity(), navigationController, new Utils.NavigateOnUserVerificationActivityCallback() {
                @Override
                public void onSuccess() {

                    if (itemViewModel.currentItem.user.userId.isEmpty()) {
                        psDialogMsg.showWarningDialog(getString(R.string.item_entry_user_not_exit), getString(R.string.app__ok));
                        psDialogMsg.show();
                    } else {
                        navigationController.navigateToChatActivity(getActivity(),
                                itemViewModel.currentItem.id,
                                itemViewModel.currentItem.user.userId,
                                itemViewModel.currentItem.user.userName,
                                itemViewModel.currentItem.defaultPhoto.imgPath,
                                itemViewModel.currentItem.title,
                                itemViewModel.currentItem.itemCurrency.currencySymbol,
                                itemViewModel.currentItem.price,
                                itemViewModel.currentItem.itemCondition.name,
                                Constants.CHAT_FROM_SELLER,
                                itemViewModel.currentItem.user.userProfilePhoto,
                                itemViewModel.currentItem.priceUnit,
                                0
                        );
                    }
                }
            });

//            if (userIdToVerify.isEmpty()) {
//                if (loginUserId.equals("")) {
//                    navigationController.navigateToUserLoginActivity(getActivity());
//                } else if (itemViewModel.currentItem.user.userId.isEmpty()) {
//                    psDialogMsg.showWarningDialog(getString(R.string.item_entry_user_not_exit), getString(R.string.app__ok));
////                    psDialogMsg.show();
//                } else {
//                    navigationController.navigateToChatActivity(getActivity(),
//                            itemViewModel.currentItem.id,
//                            itemViewModel.currentItem.user.userId,
//                            itemViewModel.currentItem.user.userName,
//                            itemViewModel.currentItem.defaultPhoto.imgPath,
//                            itemViewModel.currentItem.title,
//                            itemViewModel.currentItem.itemCurrency.currencySymbol,
//                            itemViewModel.currentItem.price,
//                            itemViewModel.currentItem.itemCondition.name,
//                            Constants.CHAT_FROM_SELLER,
//                            itemViewModel.currentItem.user.userProfilePhoto,
//                            0
//                    );
//                }
//            } else {
//
//                navigationController.navigateToVerifyEmailActivity(getActivity());
//            }

        });

        binding.get().ratingBarInformation.setOnClickListener(v -> navigationController.navigateToRatingList(ItemFragment.this.getActivity(), binding.get().getItem().user.userId));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        String number = binding.get().phoneTextView.getText().toString();
        if (!(number.trim().isEmpty() || number.trim().equals("-"))) {
            Utils.phoneCallPermissionResult(requestCode, grantResults, this, number);
        }
    }

    @Override
    protected void initViewModels() {
        itemViewModel = new ViewModelProvider(this, viewModelFactory).get(ItemViewModel.class);
        ratingViewModel = new ViewModelProvider(this, viewModelFactory).get(RatingViewModel.class);
        specsViewModel = new ViewModelProvider(this, viewModelFactory).get(SpecsViewModel.class);
        favouriteViewModel = new ViewModelProvider(this, viewModelFactory).get(FavouriteViewModel.class);
        touchCountViewModel = new ViewModelProvider(this, viewModelFactory).get(TouchCountViewModel.class);
        aboutUsViewModel = new ViewModelProvider(this, viewModelFactory).get(AboutUsViewModel.class);

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

        getIntentData();

        getItemDetail();


        getMarkAsSoldOutData();

        getFavData();

        getTouchCount();

        getFavData();

        getReportItemStatus();

        getDeleteItemStatus();

        getAboutUsData();
    }

    private void getAboutUsData(){
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

                            binding.get().safetyTextView.setText(resource.data.safetyTips);
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
                //noinspection Constant Conditions
                Utils.psLog("No Data of About Us.");
            }
        });
    }

    private void getDeleteItemStatus() {
        itemViewModel.getDeleteItemStatus().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:

                        //add offer text
                        Toast.makeText(getContext(), "Success Delete this Item", Toast.LENGTH_SHORT).show();
                        if (getActivity() != null) {
                            getActivity().finish();
                        }

                        break;

                    case ERROR:
                        Toast.makeText(getContext(), "Fail Delete this item", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void getMarkAsSoldOutData() {
        LiveData<Resource<Item>> itemDetail = itemViewModel.getMarkAsSoldOutItemData();
        if (itemDetail != null) {
            itemDetail.observe(this, listResource -> {
                if (listResource != null) {

                    switch (listResource.status) {
                        case LOADING:
                            // Loading State
                            // Data are from Local DB

                            if (listResource.data != null) {
                                //fadeIn Animation

                                fadeIn(binding.get().getRoot());

                            }

                            break;

                        case SUCCESS:
                            // Success State
                            // Data are from Server

                            if (listResource.data != null) {

                                Toast.makeText(getContext(), "success make sold out", Toast.LENGTH_SHORT).show();

                            }

                            itemViewModel.setLoadingState(false);

                            break;

                        case ERROR:

                            // Error State
                            itemViewModel.setLoadingState(false);
//                            binding.get().markAsSoldButton.setVisibility(View.VISIBLE);

                            break;

                        default:
                            // Default

                            break;
                    }

                } else {

                    itemViewModel.setLoadingState(false);

                }
            });
        }
    }

    private void getReportItemStatus() {

        itemViewModel.getReportItemStatusData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {
                    case SUCCESS:

                        //add offer text
                        Toast.makeText(getContext(), "Success Report this Item", Toast.LENGTH_SHORT).show();

                        break;

                    case ERROR:
                        Toast.makeText(getContext(), "Fail Report this item", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void getTouchCount() {

        //get touch count post method
        touchCountViewModel.getTouchCountPostData().observe(this, result -> {
            if (result != null) {
                if (result.status == Status.SUCCESS) {
                    if (ItemFragment.this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                    }

                } else if (result.status == Status.ERROR) {
                    if (ItemFragment.this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                    }
                }
            }
        });
    }

    private void getFavData() {
        //get favourite post method
        favouriteViewModel.getFavouritePostData().observe(this, result -> {
            if (result != null) {
                if (result.status == Status.SUCCESS) {
                    if (this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                        favouriteViewModel.setLoadingState(false);
                        itemViewModel.setItemDetailObj(itemViewModel.itemId, itemViewModel.historyFlag, loginUserId);
                    }

                } else if (result.status == Status.ERROR) {
                    if (this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                        favouriteViewModel.setLoadingState(false);
                    }
                }
            }
        });
    }

    private void getIntentData() {
        try {
            if (getActivity() != null) {
                if (getActivity().getIntent().getExtras() != null) {
                    itemViewModel.itemId = getActivity().getIntent().getExtras().getString(Constants.ITEM_ID);
                    itemViewModel.historyFlag = getActivity().getIntent().getExtras().getString(Constants.HISTORY_FLAG);

                }
            }
        } catch (Exception e) {
            Utils.psErrorLog("", e);
        }
    }

    private void shareImageUri(Uri uri) {

        new Thread(() -> {
            try {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setType("image/*");
                Objects.requireNonNull(getContext()).startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private Bitmap getBitmapFromView(ImageView view) {
        Drawable drawable = view.getDrawable();

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private ImageView getCurrentImageView() {
        return imageView;
    }

    private Uri saveImageExternal(Bitmap image) {
        Uri uri = null;
        try {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            File file = new File(Objects.requireNonNull(getContext()).getExternalFilesDir(Environment.DIRECTORY_PICTURES), "to-share.png");
            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, stream);
            stream.close();
            uri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uri;
    }

    private void getItemDetail() {

        itemViewModel.setItemDetailObj(itemViewModel.itemId, itemViewModel.historyFlag, loginUserId);

        LiveData<Resource<Item>> itemDetail = itemViewModel.getItemDetailData();
        if (itemDetail != null) {
            itemDetail.observe(this, listResource -> {
                if (listResource != null) {

                    switch (listResource.status) {
                        case LOADING:
                            // Loading State
                            // Data are from Local DB

                            if (listResource.data != null) {
                                //fadeIn Animation
                                fadeIn(binding.get().getRoot());

                                itemViewModel.itemContainer = listResource.data;

                                specsViewModel.setSpecsListObj(itemViewModel.itemId);
                                itemViewModel.userId = listResource.data.user.userId;
                                replaceItemData(listResource.data);
                                showOrHide(listResource.data);
                                bindingRatingData(listResource.data);
                                bindingCountData(listResource.data);
                                bindingFavoriteData(listResource.data);
                                bindingPriceWithCurrencySymbol(listResource.data);
                                bindingPhoneNo(listResource.data);
                                bindingSoldData(listResource.data);
                                bindindAddedDateUserName(listResource.data);
                                bindingBottomConstraintLayout(listResource.data);
                                bindingPhotoCount(listResource.data);
                                bindingVerifiedData(listResource.data);


                            }

                            break;

                        case SUCCESS:
                            // Success State
                            // Data are from Server

                            if (listResource.data != null) {

                                specsViewModel.setSpecsListObj(itemViewModel.itemId);

                                itemViewModel.itemContainer = listResource.data;

                                // Update the data
                                replaceItemData(listResource.data);
                                showOrHide(listResource.data);
                                itemViewModel.userId = listResource.data.user.userId;
//                                if (itemViewModel.userId != null){
                                callTouchCount();
//                                }
                                bindingRatingData(listResource.data);
                                bindingCountData(listResource.data);
                                bindingFavoriteData(listResource.data);
                                bindingPriceWithCurrencySymbol(listResource.data);
                                bindingPhoneNo(listResource.data);
                                bindingSoldData(listResource.data);
                                bindindAddedDateUserName(listResource.data);
                                bindingBottomConstraintLayout(listResource.data);
                                bindingPhotoCount(listResource.data);
                                bindingVerifiedData(listResource.data);

                                itemViewModel.locationId = listResource.data.itemLocation.id;
                                itemViewModel.locationName = listResource.data.itemLocation.name;
                                itemViewModel.otherUserId = listResource.data.user.userId;
                                itemViewModel.otherUserName = listResource.data.user.userName;
//                                checkText(listResource.data);

                            }

                            itemViewModel.setLoadingState(false);

                            break;

                        case ERROR:

                            // Error State
                            itemViewModel.setLoadingState(false);

                            break;

                        default:
                            // Default

                            break;
                    }

                } else {

                    itemViewModel.setLoadingState(false);

                }
            });
        }


        //get rating post method
        ratingViewModel.getRatingPostData().observe(this, result -> {
            if (result != null) {
                if (result.status == Status.SUCCESS) {
                    if (ItemFragment.this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                        ratingViewModel.setLoadingState(false);
                        prgDialog.get().dismiss();
                        prgDialog.get().cancel();
                        navigationController.navigateToRatingList(ItemFragment.this.getActivity(), binding.get().getItem().user.userId);
                    }

                } else if (result.status == Status.ERROR) {
                    if (ItemFragment.this.getActivity() != null) {
                        Utils.psLog(result.status.toString());
                        ratingViewModel.setLoadingState(false);
                        prgDialog.get().dismiss();
                        prgDialog.get().cancel();
                    }
                }
            }
        });

    }

    private void callTouchCount() {
        if (!loginUserId.equals(itemViewModel.userId)) {
            if (connectivity.isConnected()) {
                touchCountViewModel.setTouchCountPostDataObj(loginUserId, itemViewModel.itemId);
            }
        }
    }

    private void replaceItemData(Item item) {
        itemViewModel.currentItem = item;

        String licenceExpirationDate;
        if(item.licenseExpirationDate != null && item.licenseExpirationDate.length() > 0){
            licenceExpirationDate = item.licenseExpirationDate.substring(0,item.licenseExpirationDate.length() -8);

            binding.get().licenseExpirationTextView.setText(licenceExpirationDate);
        }
        binding.get().setItem(item);

    }

    private void bindingCountData(Item item) {
        binding.get().favouriteCountTextView.setText(getString(R.string.item_detail__fav_count, item.favouriteCount));
        binding.get().viewCountTextView.setText(getString(R.string.item_detail__view_count, item.touchCount));
    }

    private void bindingPriceWithCurrencySymbol(Item item) {
        String currencySymbol = item.itemCurrency.currencySymbol;
        String price;
        String priceUnit = item.priceUnit;
        try{
            price = Utils.format(Double.parseDouble(item.price));
        }catch (Exception e){
            price = item.price;
        }

        String currencyPrice;
        if (Config.SYMBOL_SHOW_FRONT) {
            currencyPrice = currencySymbol + " " + price +" "+ priceUnit;
        } else {
            currencyPrice = priceUnit +" "+ price + " " + currencySymbol;
        }
        binding.get().priceTextView.setText(currencyPrice);
    }

    private void bindingPhoneNo(Item item) {
        if (item.user.userPhone.trim().isEmpty()) {
            binding.get().callButton.setVisibility(View.GONE);
        } else {
            binding.get().callButton.setVisibility(View.VISIBLE);
        }
    }

    private void bindingRatingData(Item item) {

        if (item.user.overallRating.isEmpty()) {
            binding.get().ratingCountTextView.setText(getString(R.string.item_detail__rating));
        } else {
            binding.get().ratingCountTextView.setText(item.user.overallRating);
        }

        if (!item.user.overallRating.isEmpty()) {
            binding.get().ratingBarInformation.setRating(item.user.ratingDetails.totalRatingValue);
        }

        String ratingCount = "( " + item.user.ratingCount + " )";

        binding.get().ratingInfoTextView.setText(ratingCount);

    }

    private void bindingVerifiedData(Item item) {

        if (item.user.emailVerify.equals("1")) {
            binding.get().mailImageView.setVisibility(View.VISIBLE);
        }else {
            binding.get().mailImageView.setVisibility(View.GONE);
        }

        if (item.user.facebookVerify.equals("1")) {
            binding.get().facebookImageView.setVisibility(View.VISIBLE);
        }else {
            binding.get().facebookImageView.setVisibility(View.GONE);
        }

        if (item.user.phoneVerify.equals("1")) {
            binding.get().phoneImage.setVisibility(View.VISIBLE);
        }else {
            binding.get().phoneImage.setVisibility(View.GONE);
        }

        if (item.user.googleVerify.equals("1")) {
            binding.get().googleImage.setVisibility(View.VISIBLE);
        }else {
            binding.get().googleImage.setVisibility(View.GONE);
        }
    }

    private void bindingFavoriteData(Item item) {
        if (item.isFavourited.equals(Constants.ONE)) {
            binding.get().favouriteImageView.setLiked(true);
        } else {
            binding.get().favouriteImageView.setLiked(false);
        }
    }

    private void bindingSoldData(Item item) {
        if (item.isSoldOut.equals(Constants.ONE)) {
            binding.get().soldTextView.setText(getString(R.string.item_detail__sold));
        } else {
            if (item.addedUserId.equals(loginUserId)) {
                binding.get().soldTextView.setText(R.string.item_detail__mark_sold);
            } else {
                binding.get().soldTextView.setVisibility(View.GONE);
            }
        }
    }

    private void bindingPhotoCount(Item item) {
        if (item.photoCount.equals("1")) {
            String photoCount = item.photoCount + " " + getString(R.string.item_detail__photo);
            binding.get().photoCountTextView.setText(photoCount);
        } else {
            String photoCount = item.photoCount + " " + getString(R.string.item_detail__photos);
            binding.get().photoCountTextView.setText(photoCount);
        }
    }

    private void bindindAddedDateUserName(Item item) {
        binding.get().activeHourTextView.setText(item.addedDateStr);
        binding.get().userNameActiveHourTextView.setText(item.user.userName);
    }

    private void bindingBottomConstraintLayout(Item item) {
        if (item.isOwner.equals(Constants.ONE)) {
            binding.get().itemOwnerConstraintLayout.setVisibility(View.VISIBLE);
            binding.get().itemSupplierConstraintLayout.setVisibility(View.GONE);
        } else {
            binding.get().itemSupplierConstraintLayout.setVisibility(View.VISIBLE);
            binding.get().itemOwnerConstraintLayout.setVisibility(View.GONE);
        }
    }

    private void unFavFunction(Item item, LikeButton likeButton) {

        Utils.navigateOnUserVerificationActivityFromFav(userIdToVerify, loginUserId, psDialogMsg, getActivity(), navigationController,likeButton, () -> {

            if (!favouriteViewModel.isLoading) {
                favouriteViewModel.setFavouritePostDataObj(item.id, loginUserId);
                likeButton.setLikeDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.heart_off, null));
            }

        });

    }

    private void favFunction(Item item, LikeButton likeButton) {

        Utils.navigateOnUserVerificationActivityFromFav(userIdToVerify, loginUserId, psDialogMsg, getActivity(), navigationController,likeButton, () -> {

            if (!favouriteViewModel.isLoading) {
                favouriteViewModel.setFavouritePostDataObj(item.id, loginUserId);
                likeButton.setLikeDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.heart_on, null));
            }

        });

    }

    private void showOrHide(Item item) {

        if (item != null && item.manufacturer.name.equals("")){
            binding.get().manufactureImageView.setVisibility(View.GONE);
            binding.get().manufacturersTextView.setVisibility(View.GONE);
            binding.get().manufacturesTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().manufactureImageView.setVisibility(View.VISIBLE);
            binding.get().manufacturersTextView.setVisibility(View.VISIBLE);
            binding.get().manufacturesTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.model.name.equals("")){
            binding.get().modelImageView.setVisibility(View.GONE);
            binding.get().modelTextView.setVisibility(View.GONE);
            binding.get().modelTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().modelImageView.setVisibility(View.VISIBLE);
            binding.get().modelTextView.setVisibility(View.VISIBLE);
            binding.get().modelTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.color.colorValue.equals("")){
            binding.get().colorImageView.setVisibility(View.GONE);
            binding.get().colorTextView.setVisibility(View.GONE);
            binding.get().colorTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().colorImageView.setVisibility(View.VISIBLE);
            binding.get().colorTextView.setVisibility(View.VISIBLE);
            binding.get().colorTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.fuelType.fuelName.equals("")){
            binding.get().fuelTypeImageView.setVisibility(View.GONE);
            binding.get().fuelTypeTextView.setVisibility(View.GONE);
            binding.get().fuelTypeTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().fuelTypeImageView.setVisibility(View.VISIBLE);
            binding.get().fuelTypeTextView.setVisibility(View.VISIBLE);
            binding.get().fuelTypeTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.steeringPosition.equals("")){
            binding.get().steeringPositionImageView.setVisibility(View.GONE);
            binding.get().steeringPositionTextView.setVisibility(View.GONE);
            binding.get().steeringPositionTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().steeringPositionImageView.setVisibility(View.VISIBLE);
            binding.get().steeringPositionTextView.setVisibility(View.VISIBLE);
            binding.get().steeringPositionTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.noOfOwner.equals("")){
            binding.get().noOfOwnerImageView.setVisibility(View.GONE);
            binding.get().numbersOfOwnersTextView.setVisibility(View.GONE);
            binding.get().numbersOfOwnersTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().noOfOwnerImageView.setVisibility(View.VISIBLE);
            binding.get().numbersOfOwnersTextView.setVisibility(View.VISIBLE);
            binding.get().numbersOfOwnersTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.trimName.equals("")){
            binding.get().trimNameImageView.setVisibility(View.GONE);
            binding.get().trimNameTextView.setVisibility(View.GONE);
            binding.get().trimNameTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().trimNameImageView.setVisibility(View.VISIBLE);
            binding.get().trimNameTextView.setVisibility(View.VISIBLE);
            binding.get().trimNameTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.vehicleId.equals("")){
            binding.get().vehicleIdImageView.setVisibility(View.GONE);
            binding.get().vehicleIdTextView.setVisibility(View.GONE);
            binding.get().vehicleIdTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().vehicleIdImageView.setVisibility(View.VISIBLE);
            binding.get().vehicleIdTextView.setVisibility(View.VISIBLE);
            binding.get().vehicleIdTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.buildType.carType.equals("")){
            binding.get().buildTypeImageView.setVisibility(View.GONE);
            binding.get().buildTypeTextView.setVisibility(View.GONE);
            binding.get().buildTypeTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().buildTypeImageView.setVisibility(View.VISIBLE);
            binding.get().buildTypeTextView.setVisibility(View.VISIBLE);
            binding.get().buildTypeTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.maxPassengers.equals("0")){
            binding.get().maxPassengersImageView.setVisibility(View.GONE);
            binding.get().maxPassengersTextView.setVisibility(View.GONE);
            binding.get().maxPassengerTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().maxPassengersImageView.setVisibility(View.VISIBLE);
            binding.get().maxPassengersTextView.setVisibility(View.VISIBLE);
            binding.get().maxPassengerTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.noOfDoors.equals("0")){
            binding.get().noOfDoorsImageView.setVisibility(View.GONE);
            binding.get().numberOfDoorsTextView.setVisibility(View.GONE);
            binding.get().numOfDoorTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().noOfDoorsImageView.setVisibility(View.VISIBLE);
            binding.get().numberOfDoorsTextView.setVisibility(View.VISIBLE);
            binding.get().numOfDoorTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.sellerType.sellerType.equals("")){
            binding.get().sellerTypeImageView.setVisibility(View.GONE);
            binding.get().sellerTypeTextView.setVisibility(View.GONE);
            binding.get().sellerTypeTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().sellerTypeImageView.setVisibility(View.VISIBLE);
            binding.get().sellerTypeTextView.setVisibility(View.VISIBLE);
            binding.get().sellerTypeTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.address.equals("")){
            binding.get().addressImageView.setVisibility(View.GONE);
            binding.get().addressTextView.setVisibility(View.GONE);
            binding.get().addressTitleTextView.setVisibility(View.GONE);
        }else{
            binding.get().addressImageView.setVisibility(View.VISIBLE);
            binding.get().addressTextView.setVisibility(View.VISIBLE);
            binding.get().addressTitleTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.addedDateStr != null && item.addedDateStr.equals("")) {
            binding.get().activeHourTextView.setVisibility(View.GONE);
        } else {
            binding.get().activeHourTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.price != null && item.price.equals("")) {
            binding.get().priceTextView.setVisibility(View.GONE);
        } else {
            binding.get().priceTextView.setVisibility(View.VISIBLE);
        }

        if (item != null && item.addedUserId != null && item.addedUserId.equals(loginUserId)) {
            binding.get().editButton.setVisibility(View.VISIBLE);
            binding.get().deleteButton.setVisibility(View.VISIBLE);
            binding.get().itemSupplierConstraintLayout.setVisibility(View.GONE);
        } else {
            binding.get().editButton.setVisibility(View.GONE);
            binding.get().deleteButton.setVisibility(View.GONE);
            binding.get().itemSupplierConstraintLayout.setVisibility(View.VISIBLE);
        }

        if (item != null && item.licenceStatus.equals("0")){
            binding.get().licenceStatusTextView.setText(getString(R.string.item_detail__no_licence));
        }else{
            binding.get().licenceStatusTextView.setText(getString(R.string.item_detail__licence));
        }

        if (item != null && item.licenseExpirationDate.equals("0000-00-00 00:00:00")){
            binding.get().licenseExpirationTextView.setText("-");
        }

        if (item != null && item.mileage.equals("")){
            binding.get().mileageTextView.setText("-");
        }else{
            binding.get().mileageTextView.setText(item.mileage);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        loadLoginUserId();
        if (loginUserId != null) {
            itemViewModel.setItemDetailObj(itemViewModel.itemId, itemViewModel.historyFlag, loginUserId);
        }
        psDialogMsg.cancel();
//        binding.get().rating.setRating(0);
    }


}
