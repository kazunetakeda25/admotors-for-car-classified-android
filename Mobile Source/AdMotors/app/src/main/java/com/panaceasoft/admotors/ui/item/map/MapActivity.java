package com.panaceasoft.admotors.ui.item.map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ActivityMapBinding;
import com.panaceasoft.admotors.ui.common.PSAppCompactActivity;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.MyContextWrapper;
import com.panaceasoft.admotors.utils.Utils;

public class MapActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMapBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_map);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Utils.psLog("Inside Result MainActivity");
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if(fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //region Private Methods

    private void initUI(ActivityMapBinding binding) {
        Intent intent = getIntent();

        String fragName = intent.getStringExtra(Constants.MAP_FLAG);

        switch (fragName) {
            case Constants.MAP:
                setupFragment(new MapFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.map_filter__map_title));
                break;
            case Constants.MAP_PICK:
                setupFragment(new PickMapFragment());
                initToolbar(binding.toolbar, getResources().getString(R.string.map_filter__map_title));
                break;
        }
    }
}
