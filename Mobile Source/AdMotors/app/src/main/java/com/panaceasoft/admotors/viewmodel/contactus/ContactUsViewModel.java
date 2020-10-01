package com.panaceasoft.admotors.viewmodel.contactus;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.repository.contactus.ContactUsRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.common.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

/**
 * Created by Panacea-Soft on 6/2/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */

public class ContactUsViewModel extends PSViewModel {

//    private final ContactUsBackgroundTaskHandler backgroundTaskHandler;
    private final LiveData<Resource<Boolean>> postContactUsData;
    private MutableLiveData<TmpDataHolder> postContactUsObj = new MutableLiveData<>();

    public boolean isLoading = false;
    public String contactName = "";
    public String contactEmail = "";
    public String contactDesc = "";
    public String contactPhone = "";


    @Inject
    ContactUsViewModel(ContactUsRepository repository) {
        Utils.psLog("Inside ContactUsViewModel");

        postContactUsData = Transformations.switchMap(postContactUsObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("Contact Us.");
            return repository.postContactUs(Config.API_KEY, obj.contactName, obj.contactEmail, obj.contactDesc, obj.contactPhone);
        });

    }

    public void postContactUs(String apiKey, String contactName, String contactEmail, String contactDesc, String contactPhone ) {

        setLoadingState(true);

        TmpDataHolder holder = new TmpDataHolder(contactName, contactEmail, contactDesc, contactPhone);
        postContactUsObj.setValue(holder);
    }

    public LiveData<Resource<Boolean>> getPostContactUsData() {
        return postContactUsData;
    }

    //region Holder

    class TmpDataHolder {
        public String contactName = "";
        public String contactEmail = "";
        public String contactDesc = "";
        public String contactPhone = "";
        public Boolean isConnected = false;

        public TmpDataHolder(String contactName, String contactEmail, String contactDesc, String contactPhone) {
            this.contactName = contactName;
            this.contactEmail = contactEmail;
            this.contactDesc = contactDesc;
            this.contactPhone = contactPhone;
        }
    }

    //endregion

}
