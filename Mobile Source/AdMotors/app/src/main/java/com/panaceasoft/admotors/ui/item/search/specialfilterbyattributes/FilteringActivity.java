package com.panaceasoft.admotors.ui.item.search.specialfilterbyattributes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ActivityFilteringBinding;
import com.panaceasoft.admotors.ui.manufacturer.manufacturerfilter.ManufacturerFilterFragment;
import com.panaceasoft.admotors.ui.common.PSAppCompactActivity;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.MyContextWrapper;

public class FilteringActivity extends PSAppCompactActivity {


    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityFilteringBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_filtering);

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

    private void initUI(ActivityFilteringBinding binding) {

        Intent intent = getIntent();
        String name = intent.getStringExtra(Constants.FILTERING_FILTER_NAME);

        if (name.equals(Constants.FILTERING_TYPE_FILTER)) {

            // Toolbar
            initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI_Type__Button));

            // setup Fragment
            setupFragment(new ManufacturerFilterFragment());
            // Or you can call like this
            //setupFragment(new NewsListFragment(), R.id.content_frame);
        } else if (name.equals(Constants.FILTERING_SPECIAL_FILTER)) {
            initToolbar(binding.toolbar, getResources().getString(R.string.Feature_UI_Tune__Button));

            setupFragment(new FilteringFragment());
        }

    }

    //endregion

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
    }
}
