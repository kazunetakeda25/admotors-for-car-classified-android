package com.panaceasoft.admotors.ui.city.selectedcity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.databinding.ActivitySelectedCityBinding;
import com.panaceasoft.admotors.ui.common.PSAppCompactActivity;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.MyContextWrapper;
import com.panaceasoft.admotors.utils.PSDialogMsg;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.holder.ItemParameterHolder;

import javax.inject.Inject;

public class SelectedCityActivity extends PSAppCompactActivity {

    ActivitySelectedCityBinding binding;
    PSDialogMsg psDialogMsg;
    ItemParameterHolder itemParameterHolder;

    @Inject
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_selected_city);
        itemParameterHolder = new ItemParameterHolder().getRecentItem();

        initUI(binding);
        initToolbar(binding.toolbar, Constants.EMPTY_STRING);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home_menu:

                    navigationController.navigateToHomeFragment(SelectedCityActivity.this);
                    initToolbar(binding.toolbar, Constants.EMPTY_STRING);

                    break;
                case R.id.message_menu:
//                    navigationController.navigateToMessage(SelectedCityActivity.this);
                    setToolbarText(binding.toolbar, getString(R.string.menu__message));

                    break;

                case R.id.interest_menu:

                    navigationController.navigateToCategoryFragment(SelectedCityActivity.this);
                    setToolbarText(binding.toolbar, SelectedCityActivity.this.getString(R.string.category__list_title));

                    break;

//                case R.id.search_menu:
//
////                    navigationController.navigateToSearch(this);
//
//                    navigationController.navigateToFilteringFragment(SelectedCityActivity.this);
//                    setToolbarText(binding.toolbar, SelectedCityActivity.this.getString(R.string.menu__search));
//
//                    break;

//                case R.id.me_menu:
//
//                    navigationController.navigateToCityMenu(SelectedCityActivity.this);
//                    setToolbarText(binding.toolbar, getString(R.string.city_menu__title));
//
//                    break;

                default:

//                    navigationController.navigateToShopProfile(this);
//                    setToolbarText(binding.toolbar, getString(R.string.app__app_name));

                    break;
            }

            return true;
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }

    private void initUI(ActivitySelectedCityBinding binding) {

        psDialogMsg = new PSDialogMsg(this, false);

        if (getIntent() != null) {
            if (getIntent().getExtras() != null) {

                pref.edit().putString(getIntent().getStringExtra(Constants.CITY_ID),"").apply();
//                initToolbar(binding.toolbar, getIntent().getExtras().getString(Constants.CITY_NAME));

                setupFragment(new SelectedCityFragment());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            psDialogMsg.showConfirmDialog(getString(R.string.city__sure_to_exit), getString(R.string.app__ok), getString(R.string.app__cancel));

            psDialogMsg.show();

            psDialogMsg.okButton.setOnClickListener(view -> finish());
            psDialogMsg.cancelButton.setOnClickListener(view -> psDialogMsg.cancel());

            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);

        if(fragment != null)
        {
            if(fragment instanceof SelectedCityFragment)
            {
                psDialogMsg.showConfirmDialog(getString(R.string.city__sure_to_exit), getString(R.string.app__ok), getString(R.string.app__cancel));

                psDialogMsg.show();

                psDialogMsg.okButton.setOnClickListener(view -> finish());
                psDialogMsg.cancelButton.setOnClickListener(view -> psDialogMsg.cancel());
            }else {

                binding.bottomNavigationView.setSelectedItemId(R.id.home_menu);

                navigationController.navigateToHomeFragment(this);
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Utils.psLog("Inside Result MainActivity");
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
