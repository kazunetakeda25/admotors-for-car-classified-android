package com.panaceasoft.admotors.viewmodel.common;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.panaceasoft.admotors.utils.Utils;

/**
 * Created by Panacea-Soft on 9/18/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class PSViewModel extends ViewModel{

    public Utils.LoadingDirection loadingDirection = Utils.LoadingDirection.none;
    private final MutableLiveData<Boolean> loadingState = new MutableLiveData<>();

    public int offset = 0;

    public boolean forceEndLoading = false;
    public boolean isLoading = false;


    //region For loading status
    public void setLoadingState(Boolean state) {
        isLoading = state;
        loadingState.setValue(state);
    }

    public MutableLiveData<Boolean> getLoadingState() {
        return loadingState;
    }

    //endregion
}
