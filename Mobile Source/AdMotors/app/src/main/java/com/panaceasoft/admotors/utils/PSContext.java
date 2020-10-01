package com.panaceasoft.admotors.utils;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

/**
 * Created by Panacea-Soft on 1/10/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class PSContext {

    public Context context;

    @Inject
    PSContext(Application App) {
        this.context = App.getApplicationContext();
    }
}
