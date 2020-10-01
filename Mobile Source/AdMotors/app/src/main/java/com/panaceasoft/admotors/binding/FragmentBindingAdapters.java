package com.panaceasoft.admotors.binding;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.common.SyncStatus;

import javax.inject.Inject;

/**
 * Binding adapters that work with a fragment instance.
 */
public class FragmentBindingAdapters {
    private final Fragment fragment;

    @Inject
    public FragmentBindingAdapters(Fragment fragment) {
        this.fragment = fragment;
    }

    @BindingAdapter("imageUrl")
    public void bindImage(ImageView imageView, String imageUrl) {

        if(isValid(imageView, imageUrl)) {

            String fullUrl = Config.APP_IMAGES_URL + imageUrl;
            imageUrl = Config.APP_IMAGES_THUMB_URL + imageUrl;
            Utils.psLog("Image : " + imageUrl);

            if(Config.PRE_LOAD_FULL_IMAGE) {
                Glide.with(fragment).load(fullUrl).thumbnail(Glide.with(fragment).load(imageUrl)).into(imageView);
            }else {
                Glide.with(fragment).load(imageUrl).thumbnail(0.08f).into(imageView);
            }

        } else {

            if(imageView != null) {
                imageView.setImageResource(R.drawable.placeholder_image);
            }

        }
    }

    @BindingAdapter("bindFullImage")
    public void bindFullImage(ImageView imageView, String imageUrl) {

        if(isValid(imageView, imageUrl)) {
            String fullUrl = Config.APP_IMAGES_URL + imageUrl;
            imageUrl = Config.APP_IMAGES_THUMB_URL + imageUrl;
            Utils.psLog("Image : " + imageUrl);

            Glide.with(fragment).load(fullUrl).thumbnail(Glide.with(fragment).load(imageUrl)).into(imageView);

        } else {

            if(imageView != null) {
                imageView.setImageResource(R.drawable.placeholder_image);
            }

        }
    }

    @BindingAdapter("bindFullImageDrawable")
    public void bindFullImageDrawable(ImageView imageView, Drawable drawable) {

        if(imageView != null && drawable != null) {

            Glide.with(fragment).load(drawable).thumbnail(Glide.with(fragment).load(drawable)).into(imageView);

        } else {

            if(imageView != null) {
                imageView.setImageResource(R.drawable.placeholder_image);
            }

        }
    }
    @BindingAdapter("bindFullImageDrawable")
    public void bindStorageImageUri(ImageView imageView, String imgUrl) {

        if(imageView != null && imgUrl != null) {

            Glide.with(fragment).load(imgUrl).thumbnail(Glide.with(fragment).load(imgUrl)).into(imageView);

        } else {

            if(imageView != null) {
                imageView.setImageResource(R.drawable.placeholder_image);
            }

        }
    }

    @BindingAdapter("bindFullImageBitMap")
    public void bindFullImageBitMap(ImageView imageView, Bitmap bitmap) {

        if(imageView != null && bitmap != null) {

            Glide.with(fragment).load(bitmap).thumbnail(Glide.with(fragment).load(bitmap)).into(imageView);

        } else {

            if(imageView != null) {
                imageView.setImageResource(R.drawable.placeholder_image);
            }

        }
    }

    @BindingAdapter("bindFullImageUri")
    public void bindFullImageUri(ImageView imageView, Uri uri) {

        if(imageView != null && uri != null) {

            Glide.with(fragment).load(uri).thumbnail(Glide.with(fragment).load(uri)).into(imageView);

        } else {

            if(imageView != null) {
                imageView.setImageResource(R.drawable.placeholder_image);
            }

        }
    }

    @BindingAdapter("imageCircleUrl")
    public void bindCircleImage(ImageView imageView, String url) {

        if(isValid(imageView, url)) {

            url = Config.APP_IMAGES_URL + url;

            Glide.with(fragment).load(url).apply(RequestOptions.circleCropTransform()
                    .placeholder(R.drawable.circle_default_image)
            ).into(imageView);

        }else {

            if(imageView != null) {
                imageView.setImageResource(R.drawable.circle_default_image);
            }
        }
    }

    @BindingAdapter("likeImage")
    public void bindLikeImage(ImageView imageView, String isLiked) {

        if(isValid(imageView, isLiked)) {

            switch (isLiked) {
                case SyncStatus.SERVER_SELECTED:
                    imageView.setImageResource(R.drawable.baseline_like_orange_24);
                    break;
                case SyncStatus.SERVER_NOT_SELECTED:
                    imageView.setImageResource(R.drawable.baseline_like_grey_24);
                    break;

//                case SyncStatus.LOCAL_SELECTED:
//                    imageView.setImageResource(R.drawable.liked);
//                    break;
//                case SyncStatus.LOCAL_NOT_SELECTED:
//                    imageView.setImageResource(R.drawable.like);
//                    break;
                default:
                    imageView.setImageResource(R.drawable.baseline_like_grey_24);
                    break;
            }

        }else {
            if(imageView != null) {
                imageView.setImageResource(R.drawable.baseline_like_grey_24);
            }
        }
    }

    @BindingAdapter("favImage")
    public void bindFavouriteImage(ImageView imageView, String isFavourite) {

        if(isValid(imageView, isFavourite)) {

            switch (isFavourite) {
                case SyncStatus.SERVER_SELECTED:
                    imageView.setImageResource(R.drawable.liked);
                    break;
                case SyncStatus.SERVER_NOT_SELECTED:
                    imageView.setImageResource(R.drawable.like);
                    break;
                default:
                    imageView.setImageResource(R.drawable.like);
                    break;
            }

        }else {
            if(imageView != null) {
                imageView.setImageResource(R.drawable.like);
            }
        }
    }

    @BindingAdapter("followButton")
    public void bindUserFollowButton(Button button, String isFollow) {

        if(isButtonValid(button, isFollow)) {

            switch (isFollow) {
                case SyncStatus.SERVER_SELECTED:
                    button.setText(R.string.profile__unfollow);
                    break;
                case SyncStatus.SERVER_NOT_SELECTED:
                    button.setText(R.string.profile__follow);
                    break;
                default:
                    button.setText(R.string.profile__follow);
                    break;
            }

        }else {
            if(button != null) {
                button.setText(R.string.profile__follow);
            }
        }
    }

    @BindingAdapter("rateByServer")
    public void rateByServer(RatingBar ratingBar, float count) {

        if(isValidRatingBar(ratingBar)) {

            ratingBar.setRating(count);

        } else {

            if(ratingBar != null) {
                ratingBar.setRating(Float.parseFloat(Constants.ZERO));
            }

        }
    }

    @BindingAdapter("imageProfileUrl")
    public void bindProfileImage(ImageView imageView, String url) {

        if(isValid(imageView, url)) {

            url = Config.APP_IMAGES_URL + url;

            Glide.with(fragment).load(url).apply(RequestOptions.circleCropTransform()
                    .placeholder(R.drawable.default_profile)
            ).into(imageView);

        }else {

            if(imageView != null) {
                imageView.setImageResource(R.drawable.default_profile);
            }
        }
    }


    @BindingAdapter("font")
    public void setFont(TextView textView, String type) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(textView.getContext());
        String LANG_CURRENT = preferences.getString("Language", Config.DEFAULT_LANGUAGE);
        switch (type) {
            case "normal":
//                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
//                {
                textView.setTypeface(Utils.getTypeFace(textView.getContext(),Utils.Fonts.ROBOTO));
//                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT));
//                }

                break;

            case "bold":
                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    textView.setTypeface(Utils.getTypeFace(textView.getContext(),Utils.Fonts.ROBOTO),Typeface.BOLD);
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);
                }
                break;

            case "bold_italic":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    textView.setTypeface(Utils.getTypeFace(textView.getContext(),Utils.Fonts.ROBOTO),Typeface.BOLD_ITALIC);
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD_ITALIC);
                }

                break;

            case "italic":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    textView.setTypeface(Utils.getTypeFace(textView.getContext(),Utils.Fonts.ROBOTO),Typeface.ITALIC);
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.ITALIC);

                }

                break;

            case "medium":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    textView.setTypeface(Utils.getTypeFace(textView.getContext(),Utils.Fonts.ROBOTO_MEDIUM));
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);

                }

                break;
            case "light":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    textView.setTypeface(Utils.getTypeFace(textView.getContext(),Utils.Fonts.ROBOTO_LIGHT));
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);

                }

                break;
            default:

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    textView.setTypeface(Utils.getTypeFace(textView.getContext(),Utils.Fonts.ROBOTO));
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);

                }

                break;
        }
    }

    @BindingAdapter("font")
    public void setFont(CheckBox checkBox, String type) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(checkBox.getContext());
        String LANG_CURRENT = preferences.getString("Language", Config.DEFAULT_LANGUAGE);
        switch (type) {
            case "normal":
//                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
//                {
                checkBox.setTypeface(Utils.getTypeFace(checkBox.getContext(),Utils.Fonts.ROBOTO));
//                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT));
//                }

                break;

            case "bold":
                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    checkBox.setTypeface(Utils.getTypeFace(checkBox.getContext(),Utils.Fonts.ROBOTO),Typeface.BOLD);
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);
                }
                break;

            case "bold_italic":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    checkBox.setTypeface(Utils.getTypeFace(checkBox.getContext(),Utils.Fonts.ROBOTO),Typeface.BOLD_ITALIC);
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD_ITALIC);
                }

                break;

            case "italic":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    checkBox.setTypeface(Utils.getTypeFace(checkBox.getContext(),Utils.Fonts.ROBOTO),Typeface.ITALIC);
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.ITALIC);

                }

                break;

            case "medium":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    checkBox.setTypeface(Utils.getTypeFace(checkBox.getContext(),Utils.Fonts.ROBOTO_MEDIUM));
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);

                }

                break;
            case "light":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    checkBox.setTypeface(Utils.getTypeFace(checkBox.getContext(),Utils.Fonts.ROBOTO_LIGHT));
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);

                }

                break;
            default:

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    checkBox.setTypeface(Utils.getTypeFace(checkBox.getContext(),Utils.Fonts.ROBOTO));
                }else {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);

                }

                break;
        }
    }

    @BindingAdapter("font")
    public void setFont(EditText editText, String type) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(editText.getContext());
        String LANG_CURRENT = preferences.getString("Language", Config.DEFAULT_LANGUAGE);
        switch (type) {
            case "normal":
//                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
//                {
//                    textView.setTypeface(Utils.getTypeFace(textView.getContext(),Utils.Fonts.ROBOTO));
//                }else {
                editText.setTypeface(Utils.getTypeFace(editText.getContext(), Utils.Fonts.ROBOTO));
                //mmtext.prepareView(editText.getContext(), editText, mmtext.TEXT_UNICODE, true, true);
//                }

                break;

            case "bold":
//                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
//                {
                editText.setTypeface(Utils.getTypeFace(editText.getContext(),Utils.Fonts.ROBOTO),Typeface.BOLD);
//                }else {
//                    editText.setTypeface(Utils.getTypeFace(editText.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);
//
//                }
                break;

            case "bold_italic":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    editText.setTypeface(Utils.getTypeFace(editText.getContext(),Utils.Fonts.ROBOTO),Typeface.BOLD_ITALIC);
                }else {
                    editText.setTypeface(Utils.getTypeFace(editText.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD_ITALIC);

                }

                break;

            case "italic":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    editText.setTypeface(Utils.getTypeFace(editText.getContext(),Utils.Fonts.ROBOTO),Typeface.ITALIC);
                }else {
                    editText.setTypeface(Utils.getTypeFace(editText.getContext(), Utils.Fonts.MM_FONT),Typeface.ITALIC);

                }

                break;

            case "medium":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    editText.setTypeface(Utils.getTypeFace(editText.getContext(),Utils.Fonts.ROBOTO_MEDIUM));
                }else {
                    editText.setTypeface(Utils.getTypeFace(editText.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);

                }

                break;
            case "light":

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    editText.setTypeface(Utils.getTypeFace(editText.getContext(),Utils.Fonts.ROBOTO_LIGHT));
                }else {
                    editText.setTypeface(Utils.getTypeFace(editText.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);

                }

                break;
            default:

                if(LANG_CURRENT.equals(Config.LANGUAGE_CODE))
                {
                    editText.setTypeface(Utils.getTypeFace(editText.getContext(),Utils.Fonts.ROBOTO));
                }else {
                    editText.setTypeface(Utils.getTypeFace(editText.getContext(), Utils.Fonts.MM_FONT),Typeface.BOLD);

                }

                break;
        }
    }





    @BindingAdapter("font")
    public void setFont(Button button, String type) {

        switch (type) {
            case "normal":
                button.setTypeface(Utils.getTypeFace(button.getContext(), Utils.Fonts.ROBOTO));
                break;
            case "bold":
                button.setTypeface(Utils.getTypeFace(button.getContext(), Utils.Fonts.ROBOTO_MEDIUM));
                break;
            case "medium":
                button.setTypeface(Utils.getTypeFace(button.getContext(), Utils.Fonts.ROBOTO_MEDIUM));
                break;
            case "light":
                button.setTypeface(Utils.getTypeFace(button.getContext(), Utils.Fonts.ROBOTO_LIGHT));
                break;
            default:
                button.setTypeface(Utils.getTypeFace(button.getContext(), Utils.Fonts.ROBOTO));
                break;
        }

    }

    @BindingAdapter("textSize")
    public void setTextSize(TextView textView, String dimenType) {

        float dimenPix = 0;
        switch (dimenType) {

            case "font_h1_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_h1_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;

            case "font_h2_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_h2_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;

            case "font_h3_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_h3_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;

            case "font_h4_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_h4_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;

            case "font_h5_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_h5_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;

            case "font_h6_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_h6_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;

            case "font_h7_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_h7_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;

            case "font_title_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_title_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;

            case "font_body_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_body_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;

            case "font_body_s_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_body_s_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;

            case "font_body_xs_size":
                dimenPix = textView.getResources().getDimensionPixelOffset(R.dimen.font_body_xs_size);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);
                break;
        }
    }

    @BindingAdapter("textSize")
    public void setTextSize(EditText editText, String dimenType) {

        float dimenPix = 0;
        switch (dimenType) {
            case "edit_text":

                dimenPix = editText.getResources().getDimensionPixelOffset(R.dimen.edit_text__size);
                editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);

                break;
        }
    }

    @BindingAdapter("textSize")
    public void setTextSize(Button button, String dimenType) {

        float dimenPix = 0;
        switch (dimenType) {
            case "button_text":

                dimenPix = button.getResources().getDimensionPixelOffset(R.dimen.button__text_size);
                button.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenPix);

                break;
        }
    }

//    @BindingAdapter("youTubeImage")
//    public void bindYouTubeImage(ImageView imageView, String youTubeId) {
//
//        if(isValid(imageView, youTubeId)) {
//
//            String url = String.format(Config.YOUTUBE_IMAGE_BASE_URL, youTubeId);
//            Glide.with(fragment).load(url).apply(new RequestOptions()
//                    .placeholder(R.drawable.default_image)
//                    .centerCrop()
//                    .dontAnimate()
//                    .dontTransform()).into(imageView);
//
//        } else {
//
//            if(imageView != null) {
//                imageView.setImageResource(R.drawable.default_image);
//            }
//        }
//    }

    private Boolean isValid(ImageView imageView, String url) {
        return !(url == null
                || imageView == null
                || fragment == null
                || url.equals(""));
    }

    private Boolean isButtonValid(Button button, String url) {
        return !(url == null
                || button == null
                || fragment == null
                || url.equals(""));
    }

    private Boolean isValidRatingBar(RatingBar ratingBar) {
        return !(ratingBar == null
                || fragment == null);
    }

}

