package com.panaceasoft.admotors.repository.itemtransmission;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.panaceasoft.admotors.AppExecutors;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.api.ApiResponse;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.db.PSCoreDb;
import com.panaceasoft.admotors.db.TransmissionDao;
import com.panaceasoft.admotors.repository.common.NetworkBoundResource;
import com.panaceasoft.admotors.repository.common.PSRepository;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.Transmission;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemTransmissionRepository extends PSRepository {
    private TransmissionDao transmissionDao;

    @Inject
    ItemTransmissionRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, TransmissionDao transmissionDao) {

        super(psApiService, appExecutors, db);
        this.transmissionDao = transmissionDao;
    }

    public LiveData<Resource<List<Transmission>>> getAllTransmissionList(String limit, String offset) {

        return new NetworkBoundResource<List<Transmission>, List<Transmission>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Transmission> transmissionList) {
                Utils.psLog("SaveCallResult of getTransmissionList");

                try {
                    db.runInTransaction(() -> {
                        transmissionDao.deleteAllItemPriceType();

                        transmissionDao.insertAll(transmissionList);

                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<Transmission> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<Transmission>> loadFromDb() {

                Utils.psLog("Load From Db (All Transmission)");

                return transmissionDao.getAllItemPriceType();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Transmission>>> createCall() {
                Utils.psLog("Call Get All Transmission webservice.");

                return psApiService.getTransmissionList(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of Transmission");
            }

        }.asLiveData();
    }
}