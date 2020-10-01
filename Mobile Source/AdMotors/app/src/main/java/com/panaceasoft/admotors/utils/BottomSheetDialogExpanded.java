package com.panaceasoft.admotors.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Panacea-Soft on 11/21/18.
 * Contact Email : teamps.is.cool@gmail.com
 */
public class BottomSheetDialogExpanded extends BottomSheetDialog {

    private BottomSheetBehavior mBehavior;

    public BottomSheetDialogExpanded(@NonNull Context context) {
        super(context);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);

        if(getWindow() != null) {
            FrameLayout frameLayout = getWindow().getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet);
            mBehavior = BottomSheetBehavior.from(frameLayout);
            mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mBehavior != null) {
            mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void setState(int behavior) {
        if(mBehavior != null) {
            mBehavior.setState(behavior);
        }
    }
}
