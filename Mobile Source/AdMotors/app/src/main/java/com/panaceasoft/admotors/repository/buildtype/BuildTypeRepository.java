package com.panaceasoft.admotors.repository.buildtype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.panaceasoft.admotors.AppExecutors;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.api.ApiResponse;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.db.BuildTypeDao;
import com.panaceasoft.admotors.db.PSCoreDb;
import com.panaceasoft.admotors.repository.common.NetworkBoundResource;
import com.panaceasoft.admotors.repository.common.PSRepository;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.BuildType;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BuildTypeRepository extends PSRepository {
    private BuildTypeDao buildTypeDao;

    @Inject
    BuildTypeRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, BuildTypeDao buildTypeDao) {

        super(psApiService, appExecutors, db);
        this.buildTypeDao = buildTypeDao;
    }

    public LiveData<Resource<List<BuildType>>> getAllBuildTypeList(String limit, String offset) {

        return new NetworkBoundResource<List<BuildType>, List<BuildType>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<BuildType> transmissionList) {
                Utils.psLog("SaveCallResult of getTransmissionList");

                try {
                    db.runInTransaction(() -> {
                        buildTypeDao.deleteAllBuildType();

                        buildTypeDao.insertAll(transmissionList);

                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<BuildType> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<BuildType>> loadFromDb() {

                Utils.psLog("Load From Db (All BuildType)");

                return buildTypeDao.getAllBuildType();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<BuildType>>> createCall() {
                Utils.psLog("Call Get All BuildType webservice.");

                return psApiService.getBuildTypeList(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of BuildType");
            }

        }.asLiveData();
    }
}

