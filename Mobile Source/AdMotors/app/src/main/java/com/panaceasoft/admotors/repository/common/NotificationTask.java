package com.panaceasoft.admotors.repository.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.api.ApiResponse;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.ApiStatus;
import com.panaceasoft.admotors.viewobject.common.Resource;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * For register/un-register token to server to able to send notification
 * Created by Panacea-Soft on 12/12/17.
 * Contact Email : teamps.is.cool@gmail.com
 */
public class NotificationTask implements Runnable {


    //region Variables

    @Inject
    SharedPreferences prefs;
    private final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

    private final PSApiService service;
    private final String platform;
    private final Boolean isRegister;
    private final Context context;
    private String token;

    //endregion


    //region Constructor
    public NotificationTask(Context context, PSApiService service, String platform, Boolean isRegister, String token) {
        this.service = service;
        this.platform = platform;
        this.isRegister = isRegister;
        this.token = token;
        this.context = context;
    }

    //endregion


    //region Override Methods

    @Override
    public void run() {
        try {

            prefs = PreferenceManager.getDefaultSharedPreferences(context);

            if(isRegister) {

                //deprecated.
//                if(this.token.equals("")) {
//                    // Get Token for notification registration
//                    token = FirebaseInstanceId.getInstance().getToken();
//                }


                Utils.psLog("Token : " + token);

                if(token.equals("")) {
                    statusLiveData.postValue(Resource.error("Token is null.", true));

                    return;
                }

                // Call the API Service
                Response<ApiStatus> response = service.rawRegisterNotiToken(Config.API_KEY, platform, token).execute();

                // Wrap with APIResponse Class
                ApiResponse<ApiStatus> apiResponse = new ApiResponse<>(response);

                // If response is successful
                if (apiResponse.isSuccessful()) {

                    if (apiResponse.body != null) {

                        Utils.psLog("API Status : " + apiResponse.body.status);

                        if (apiResponse.body.status.equals("success")) {

                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean(Constants.NOTI_SETTING, true).apply();
                            editor.putString(Constants.NOTI_TOKEN, token).apply();
                        }
                    }

                    statusLiveData.postValue(Resource.success(true));
                } else {
                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, true));
                }
            }else { // Un-register

                // Get Token
                String token = prefs.getString(Constants.NOTI_TOKEN, "");

                if(!token.equals("")) {

                    // Call unregister service to server
                    Response<ApiStatus> response = service.rawUnregisterNotiToken(Config.API_KEY, platform, token).execute();

                    // Parse it to ApiResponse
                    ApiResponse<ApiStatus> apiResponse = new ApiResponse<>(response);

                    // If response is successful
                    if (apiResponse.isSuccessful()) {

                        if (apiResponse.body != null) {

                            Utils.psLog("API Status : " + apiResponse.body.status);

                            if (apiResponse.body.status.equals("success")) {
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putBoolean(Constants.NOTI_SETTING, false).apply();
                                editor.putString(Constants.NOTI_TOKEN, "-").apply();
                            }
                        }

                        statusLiveData.postValue(Resource.success(true));
                    } else {
                        statusLiveData.postValue(Resource.error(apiResponse.errorMessage, true));
                    }

                    // Clear notification setting
                }else {
                    statusLiveData.postValue(Resource.error("Token is null.", true));
                }


            }
        } catch (Exception e) {
            statusLiveData.postValue(Resource.error(e.getMessage(), true));
        }
    }

    //endregion


    //region public SyncCategory Methods

    /**
     * This function will return Status of Process
     * @return statusLiveData
     */

    public LiveData<Resource<Boolean>> getStatusLiveData() {
        return statusLiveData;
    }

    //endregion


}