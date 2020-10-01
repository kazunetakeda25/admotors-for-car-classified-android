package com.panaceasoft.admotors.repository.sellertype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.panaceasoft.admotors.AppExecutors;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.api.ApiResponse;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.db.SellerTypeDao;
import com.panaceasoft.admotors.db.PSCoreDb;
import com.panaceasoft.admotors.repository.common.NetworkBoundResource;
import com.panaceasoft.admotors.repository.common.PSRepository;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.SellerType;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SellerTypeRepository extends PSRepository {
    private SellerTypeDao sellerTypeDao;

    @Inject
    SellerTypeRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, SellerTypeDao sellerTypeDao) {

        super(psApiService, appExecutors, db);
        this.sellerTypeDao = sellerTypeDao;
    }

    public LiveData<Resource<List<SellerType>>> getAllSellerTypeList(String limit, String offset) {

        return new NetworkBoundResource<List<SellerType>, List<SellerType>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<SellerType> transmissionList) {
                Utils.psLog("SaveCallResult of getTransmissionList");

                try {
                    db.runInTransaction(() -> {
                        sellerTypeDao.deleteAllSellerType();

                        sellerTypeDao.insertAll(transmissionList);

                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<SellerType> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<SellerType>> loadFromDb() {

                Utils.psLog("Load From Db (All SellerType)");

                return sellerTypeDao.getAllSellerType();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<SellerType>>> createCall() {
                Utils.psLog("Call Get All SellerType webservice.");

                return psApiService.getSellerTypeList(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of SellerType");
            }

        }.asLiveData();
    }
}


