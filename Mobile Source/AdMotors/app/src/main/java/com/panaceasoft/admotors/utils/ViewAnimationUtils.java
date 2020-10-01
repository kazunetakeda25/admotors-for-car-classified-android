package com.panaceasoft.admotors.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Panacea-Soft on 6/13/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class ViewAnimationUtils {
    public static void expand(final View v) {
        v.measure(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight;

        if (0 <= v.getMeasuredHeight()) {

            targetHeight = 200;
        } else {
            targetHeight = v.getMeasuredHeight();
        }

        Log.d("TEAMPS", "expand: " + targetHeight);

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ConstraintLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        Log.d("TEAMPS", "expand: " + v.getContext().getResources().getDisplayMetrics().density);
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        Log.d("TEAMPS", "collapse: " + (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
}
