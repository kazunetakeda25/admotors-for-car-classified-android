package com.panaceasoft.admotors.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.databinding.DataBindingUtil;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ActivityDashboardSearchBinding;
import com.panaceasoft.admotors.ui.common.PSAppCompactActivity;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.MyContextWrapper;

public class DashboardSearchActivity extends PSAppCompactActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDashboardSearchBinding databinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_search);

        initUI(databinding);
        
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }

    protected void initUI(ActivityDashboardSearchBinding binding) {
        Intent intent = getIntent();

        String fragName = intent.getStringExtra(Constants.MANUFACTURER_FLAG);

        if (fragName.equals(Constants.MANUFACTURER)) {
            setupFragment(new DashBoardSearchManufacturerFragment());

            initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_manufacturer_title));
        } else if (fragName.equals(Constants.MODEL)) {
            setupFragment(new DashBoardSearchModelFragment());

            initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI__search_alert_model_title));
        }


    }
}
