package com.panaceasoft.admotors.ui.user.verifyphone;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ActivityVerifyMobileBinding;
import com.panaceasoft.admotors.ui.common.PSAppCompactActivity;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.MyContextWrapper;

public class VerifyMobileActivity extends PSAppCompactActivity {


    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityVerifyMobileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_verify_mobile);

        // Init all UI
        initUI(binding);

    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }

    //endregion


    //region Private Methods

    private void initUI(ActivityVerifyMobileBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.verify_phone__title));

        // setup Fragment
        setupFragment(new VerifyMobileFragment());
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);

    }

    //endregion


}