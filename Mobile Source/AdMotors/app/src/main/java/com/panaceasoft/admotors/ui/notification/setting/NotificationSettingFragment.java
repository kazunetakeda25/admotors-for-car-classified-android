package com.panaceasoft.admotors.ui.notification.setting;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentNotificationSettingBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.PSDialogMsg;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.NotificationViewModel;

/**
 * NotificationSettingFragment
 */
public class NotificationSettingFragment extends PSFragment {


    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private PSDialogMsg psDialogMsg;

    @VisibleForTesting
    private AutoClearedValue<FragmentNotificationSettingBinding> binding;


    private NotificationViewModel notificationViewModel;

    //endregion


    //region Override Methods

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentNotificationSettingBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification_setting, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }

    @Override
    protected void initUIAndActions() {

        psDialogMsg = new PSDialogMsg(getActivity(), false);

        //fadeIn Animation
        fadeIn(binding.get().getRoot());

        binding.get().notiSwitch.setChecked(notificationViewModel.pushNotificationSetting);
        binding.get().notiSwitch.setOnCheckedChangeListener((CompoundButton compoundButton, boolean b) -> {
            Utils.psLog("Checked : " + b);

            if (connectivity.isConnected()) {

                if (!notificationViewModel.isLoading) {
                    updateNotificationSetting(b);
                } else {

                    psDialogMsg.showInfoDialog(getString(R.string.update_noti), getString(R.string.app__ok));

                    psDialogMsg.show();

                    binding.get().notiSwitch.setChecked(!b);
                }
            } else {

                psDialogMsg.showWarningDialog(getString(R.string.no_internet_error), getString(R.string.app__ok));

                psDialogMsg.show();
                binding.get().notiSwitch.setChecked(!b);
            }
        });
    }

    @Override
    protected void initViewModels() {
        notificationViewModel = new ViewModelProvider(this, viewModelFactory).get(NotificationViewModel.class);
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

        try {

            if (pref != null) {
                updateNotificationMessage();
            }

        } catch (NullPointerException ne) {
            Utils.psErrorLog("Null Pointer Exception.", ne);
        } catch (Exception e) {
            Utils.psErrorLog("Error in getting notification flag data.", e);
        }

        notificationViewModel.getLoadingStatus().observe(this, status -> {
            if (status == null) {
                Utils.psLog("Status is null");
                notificationViewModel.isLoading = false;
            } else {
                Utils.psLog("Status Update : " + status.isRunning());
                notificationViewModel.isLoading = status.isRunning();
                String error = status.getErrorMessageIfNotHandled();
                if (error != null) {
                    notificationViewModel.isLoading = false;
                    Utils.psLog("Error in Status : " + error);

                    psDialogMsg.showErrorDialog(error, getString(R.string.app__ok));
                    psDialogMsg.show();
                }
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

        if (pref != null) {
            updateNotificationMessage();
        }
    }

    private void updateNotificationMessage() {

        notificationViewModel.pushNotificationSetting = pref.getBoolean(Constants.NOTI_SETTING, false);
        binding.get().notiSwitch.setChecked(notificationViewModel.pushNotificationSetting);
        String message = pref.getString(Constants.NOTI_MSG, "");

        if (!message.equals("")) {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                binding.get().messageTextView.setText(Html.fromHtml(message, Html.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL));
            } else {
                binding.get().messageTextView.setText(Html.fromHtml(message));
            }


        }
    }

    @VisibleForTesting
    private void updateNotificationSetting(Boolean setting) {

        if (getActivity() != null) {

            if (notificationViewModel.pushNotificationSetting != setting) {

                if (setting) {
                    String token = pref.getString(Constants.NOTI_TOKEN, "");
                    notificationViewModel.registerNotification(getContext(), Constants.PLATFORM, token);

                } else {
                    notificationViewModel.unregisterNotification(getContext(), Constants.PLATFORM, "");

                }
                notificationViewModel.pushNotificationSetting = setting;
            }
        }
    }

    //endregion


}
