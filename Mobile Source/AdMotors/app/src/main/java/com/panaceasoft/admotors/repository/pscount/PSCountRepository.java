package com.panaceasoft.admotors.repository.pscount;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.panaceasoft.admotors.AppExecutors;
import com.panaceasoft.admotors.api.ApiResponse;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.db.PSCoreDb;
import com.panaceasoft.admotors.db.PSCountDao;
import com.panaceasoft.admotors.repository.common.NetworkBoundResource;
import com.panaceasoft.admotors.repository.common.PSRepository;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.PSCount;
import com.panaceasoft.admotors.viewobject.common.Resource;

import javax.inject.Inject;


/**
 * Created by Panacea-Soft on 2019-08-28.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class PSCountRepository extends PSRepository {

    //region variable
    private final PSCountDao psCountDao;
    //end region


    //region constructor
    @Inject
    PSCountRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, PSCountDao psCountDao) {
        super(psApiService, appExecutors, db);
        this.psCountDao = psCountDao;
    }
    //end region

    //Get PS Count
    public LiveData<Resource<PSCount>> getPSCount(String apiKey, String userId, String deviceToken) {
        return new NetworkBoundResource<PSCount, PSCount>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull PSCount psCount) {
                Utils.psLog("SaveCallResult of getPSCount.");


                try {
                    db.runInTransaction(() -> {
                        psCountDao.deleteAll();
                        psCount.id = "1"; // Always id is "1"
                        psCountDao.insert(psCount);
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at save PSCount", ex);
                }

            }

            @Override
            protected boolean shouldFetch(@Nullable PSCount data) {
                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<PSCount> loadFromDb() {
                Utils.psLog("Load Recent notification From Db");
                return psCountDao.getPSCount();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<PSCount>> createCall() {
                return psApiService.postGetAllCount(apiKey,
                        userId,
                        deviceToken);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getRecentNotificationList) : " + message);
            }
        }.asLiveData();
    }

}
