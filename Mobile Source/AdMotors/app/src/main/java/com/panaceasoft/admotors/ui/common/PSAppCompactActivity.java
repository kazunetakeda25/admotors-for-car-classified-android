package com.panaceasoft.admotors.ui.common;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.utils.Utils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * Parent Activity class of all activity in this project.
 * Created by Panacea-Soft on 12/2/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

public abstract class PSAppCompactActivity extends AppCompatActivity implements HasAndroidInjector {

    //region Variables
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Inject
    protected NavigationController navigationController;

    //endregion


    //region Override Methods

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //endregion


    //region Toolbar Init

    protected Toolbar initToolbar(Toolbar toolbar, String title, int color) {

        if(toolbar != null) {

            toolbar.setTitle(title);

            if(color != 0) {
                try {
                    toolbar.setTitleTextColor(getResources().getColor(color));
                }catch (Exception e){
                    Utils.psErrorLog("Can't set color.", e);
                }
            }else {
                try {
                    toolbar.setTitleTextColor(getResources().getColor(R.color.text__white));
                }catch (Exception e){
                    Utils.psErrorLog("Can't set color.", e);
                }
            }

            try {
                setSupportActionBar(toolbar);
            }catch (Exception e) {
                Utils.psErrorLog("Error in set support action bar.", e);
            }

            try {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }catch (Exception e) {
                Utils.psErrorLog("Error in set display home as up enabled.", e);
            }

            /*
               Warning :

               Set Spannable text must call after set Support Action Bar

               The problem is actually that you have a non-String title set on the Toolbar
               at the time you're calling setSupportActionBar. It will end up in ToolbarWidgetWrapper,
               where it will be used when you click the navigation menu. You can use any CharSequence (e.g. Spannable)
               after calling setSuppportActionBar.

               https://stackoverflow.com/questions/27023802/clicking-toolbar-navigation-icon-crashes-the-app/27044868

             */
            if(!title.equals("")) {
                setToolbarText(toolbar, title);
            }

        }else {
            Utils.psLog("Toolbar is null");
        }

        return toolbar;
    }

    protected Toolbar initToolbar(Toolbar toolbar, String title) {

        if(toolbar != null) {

            toolbar.setTitle(title);

            try {
                toolbar.setTitleTextColor(getResources().getColor(R.color.text__white));
            }catch (Exception e){
                Utils.psErrorLog("Can't set color.", e);
            }

            try {
                setSupportActionBar(toolbar);
            }catch (Exception e) {
                Utils.psErrorLog("Error in set support action bar.", e);
            }

            try {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }catch (Exception e) {
                Utils.psErrorLog("Error in set display home as up enabled.", e);
            }

            /*
               Warning :

               Set Spannable text must call after set Support Action Bar

               The problem is actually that you have a non-String title set on the Toolbar
               at the time you're calling setSupportActionBar. It will end up in ToolbarWidgetWrapper,
               where it will be used when you click the navigation menu. You can use any CharSequence (e.g. Spannable)
               after calling setSuppportActionBar.

               https://stackoverflow.com/questions/27023802/clicking-toolbar-navigation-icon-crashes-the-app/27044868

             */

            if(!title.equals("")) {
                setToolbarText(toolbar, title);
            }

        }else {
            Utils.psLog("Toolbar is null");
        }
        return toolbar;
    }

    public void setToolbarText(Toolbar toolbar, String text) {
        Utils.psLog("Set Toolbar Text : " + text);
        toolbar.setTitle(Utils.getSpannableString(toolbar.getContext(), text, Utils.Fonts.ROBOTO));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item != null) {
            Utils.psLog("Clicked " + item.getItemId());
            switch (item.getItemId()) {
                case android.R.id.home:
                    onBackPressed();
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion


    //region Setup Fragment

    protected void setupFragment(Fragment fragment) {
        try {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commitAllowingStateLoss();
        }catch (Exception e){
            Utils.psErrorLog("Error! Can't replace fragment.", e);
        }
    }

    protected void setupFragment(Fragment fragment, int frameId) {
        try {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(frameId, fragment)
                    .commitAllowingStateLoss();
        }catch (Exception e){
            Utils.psErrorLog("Error! Can't replace fragment.", e);
        }
    }

    //endregion

}
