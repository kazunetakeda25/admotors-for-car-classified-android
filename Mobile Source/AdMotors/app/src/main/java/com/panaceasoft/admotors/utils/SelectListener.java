package com.panaceasoft.admotors.utils;

/**
 * Created by Panacea-Soft on 7/25/15.
 * Contact Email : teamps.is.cool@gmail.com
 */


public interface SelectListener {
    void Select(int position, CharSequence text);
    void Select(int position, CharSequence text, String id);
    /*void Select(View view, int position, CharSequence text, String id, float additional_price);*/
}
