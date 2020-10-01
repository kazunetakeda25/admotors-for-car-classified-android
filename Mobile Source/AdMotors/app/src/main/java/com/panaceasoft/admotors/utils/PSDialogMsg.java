package com.panaceasoft.admotors.utils;

import android.app.Activity;
import android.app.Dialog;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.panaceasoft.admotors.R;

/**
 * Created by Panacea-Soft on 11/19/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class PSDialogMsg {

    public TextView msgTextView, titleTextView,descriptionTextView;
    public Button okButton, cancelButton, halfButton;
    private ImageView imageView;
    private Dialog dialog;
    private View view;
    private boolean cancelable;
    public RatingBar ratingBar;
    public float newRating;
    private boolean attached = false;

    public PSDialogMsg(Activity activity, Boolean cancelable) {

        this.dialog = new Dialog(activity);
        this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.cancelable = cancelable;
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

    public void show() {
        if (dialog != null) {
            this.dialog.show();
        }
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void cancel() {
        if (dialog != null) {
            this.dialog.dismiss();
        }
    }

    public void showAppInfoDialog(String updateText, String cancelText,String title,String description) {
        this.dialog.setContentView(R.layout.dialog_app_info);
        view = dialog.findViewById(R.id.dialogTitleView);
        titleTextView = dialog.findViewById(R.id.dialogTitleTextView);
        descriptionTextView = dialog.findViewById(R.id.descriptionTextView);
        view.setBackgroundColor(dialog.getContext().getResources().getColor(R.color.md_amber_800));
        cancelButton = dialog.findViewById(R.id.dialogCancelButton);

        msgTextView = dialog.findViewById(R.id.titleTextView);
        okButton = dialog.findViewById(R.id.dialogOkButton);

        descriptionTextView.setMovementMethod(new ScrollingMovementMethod());

        titleTextView.setText(dialog.getContext().getString(R.string.version_update));
        msgTextView.setText(title);
        descriptionTextView.setText(description);
        okButton.setText(updateText);
        cancelButton.setText(cancelText);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setAttributes(getLayoutParams(dialog));

            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            cancelButton.setOnClickListener(view -> PSDialogMsg.this.cancel());
        }
    }

    public void showConfirmDialog(String message, String okTitle, String cancelTitle) {
        this.dialog.setContentView(R.layout.dialog_message);
        view = dialog.findViewById(R.id.dialogTitleView);
        titleTextView = dialog.findViewById(R.id.dialogTitleTextView);
        imageView = dialog.findViewById(R.id.dialogIconImageView);
        imageView.setImageResource(R.drawable.baseline_confirm_white_24);
        view.setBackgroundColor(dialog.getContext().getResources().getColor(R.color.md_amber_800));
        cancelButton = dialog.findViewById(R.id.dialogCancelButton);

        msgTextView = dialog.findViewById(R.id.dialogMessageTextView);
        okButton = dialog.findViewById(R.id.dialogOkButton);

        titleTextView.setText(dialog.getContext().getString(R.string.confirm));
        msgTextView.setText(message);
        okButton.setText(okTitle);
        cancelButton.setText(cancelTitle);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setAttributes(getLayoutParams(dialog));

            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//            dialog.setCancelable(cancelable);
            okButton.setOnClickListener(view -> PSDialogMsg.this.cancel());
        }
    }

    public void showInfoDialog(String message, String okTitle) {
        this.dialog.setContentView(R.layout.dialog_box_design);
        view = dialog.findViewById(R.id.dialogTitleView);
        titleTextView = dialog.findViewById(R.id.dialogTitleTextView);
        view.setBackgroundColor(dialog.getContext().getResources().getColor(R.color.md_grey_500));

        msgTextView = dialog.findViewById(R.id.dialogMessageTextView);
        okButton = dialog.findViewById(R.id.dialogOkButton);

        titleTextView.setText(dialog.getContext().getString(R.string.info));
        msgTextView.setText(message);
        okButton.setText(okTitle);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setAttributes(getLayoutParams(dialog));


            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(cancelable);
            okButton.setOnClickListener(view -> PSDialogMsg.this.cancel());
        }

    }

    public void showSuccessDialog(String message, String okTitle) {
        this.dialog.setContentView(R.layout.dialog_box_design);
        view = dialog.findViewById(R.id.dialogTitleView);
        titleTextView = dialog.findViewById(R.id.dialogTitleTextView);
        imageView = dialog.findViewById(R.id.dialogIconImageView);
        imageView.setImageResource(R.drawable.baseline_success_white_24);
        view.setBackgroundColor(dialog.getContext().getResources().getColor(R.color.md_green_800));

        msgTextView = dialog.findViewById(R.id.dialogMessageTextView);
        okButton = dialog.findViewById(R.id.dialogOkButton);

        titleTextView.setText(dialog.getContext().getString(R.string.success));
        msgTextView.setText(message);
        okButton.setText(okTitle);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setAttributes(getLayoutParams(dialog));

            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(cancelable);
            okButton.setOnClickListener(view -> PSDialogMsg.this.cancel());
        }
    }

    public void showErrorDialog(String message, String okTitle) {
        this.dialog.setContentView(R.layout.dialog_box_design);
        view = dialog.findViewById(R.id.dialogTitleView);
        titleTextView = dialog.findViewById(R.id.dialogTitleTextView);
        imageView = dialog.findViewById(R.id.dialogIconImageView);
        imageView.setImageResource(R.drawable.baseline_error_white_24);
        view.setBackgroundColor(dialog.getContext().getResources().getColor(R.color.md_red_900));

        msgTextView = dialog.findViewById(R.id.dialogMessageTextView);
        okButton = dialog.findViewById(R.id.dialogOkButton);

        titleTextView.setText(dialog.getContext().getString(R.string.error));
        msgTextView.setText(message);
        okButton.setText(okTitle);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setAttributes(getLayoutParams(dialog));

            this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            this.dialog.setCancelable(cancelable);
            okButton.setOnClickListener(view -> PSDialogMsg.this.cancel());
        }
    }

    public void showWarningDialog(String message, String okTitle) {
        this.dialog.setContentView(R.layout.dialog_box_design);
        view = dialog.findViewById(R.id.dialogTitleView);
        titleTextView = dialog.findViewById(R.id.dialogTitleTextView);
        imageView = dialog.findViewById(R.id.dialogIconImageView);
        imageView.setImageResource(R.drawable.baseline_warning_white_24);
        view.setBackgroundColor(dialog.getContext().getResources().getColor(R.color.md_lime_700));

        msgTextView = dialog.findViewById(R.id.dialogMessageTextView);
        okButton = dialog.findViewById(R.id.dialogOkButton);

        titleTextView.setText(dialog.getContext().getString(R.string.warning));
        msgTextView.setText(message);
        okButton.setText(okTitle);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setAttributes(getLayoutParams(dialog));

            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(cancelable);
            okButton.setOnClickListener(view -> PSDialogMsg.this.cancel());
        }
    }

//    public void showCategoryConfirm(String message, String okTitle, String cancelTitle, String halfTitle) {
//        this.dialog.setContentView(R.layout.dialog_category_delete_confirm);
//        view = dialog.findViewById(R.id.dialogTitleTextView);
//        titleTextView = dialog.findViewById(R.id.dialogTitleTextView);
//        cancelButton = dialog.findViewById(R.id.dialogCancelButton);
//        halfButton = dialog.findViewById(R.id.onlyCategoryButton);
//        imageView = dialog.findViewById(R.id.imageView14);
//        imageView.setImageResource(R.drawable.baseline_confirm_white_24);
//
//        msgTextView = dialog.findViewById(R.id.dialogMessageTextView);
//        okButton = dialog.findViewById(R.id.dialogOkButton);
//
//        titleTextView.setText(dialog.getContext().getString(R.string.success));
//        msgTextView.setText(message);
//        okButton.setText(okTitle);
//        cancelButton.setText(cancelTitle);
//        halfButton.setText(halfTitle);
//
//        if (dialog.getWindow() != null) {
//            dialog.getWindow().setAttributes(getLayoutParams(dialog));
//
//            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//            dialog.setCancelable(cancelable);
//            okButton.setOnClickListener(view -> PSDialogMsg.this.cancel());
//        }
//    }

    public boolean isShowing() {
        return dialog.isShowing();
    }
}
