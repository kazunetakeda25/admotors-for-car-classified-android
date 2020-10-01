package com.panaceasoft.admotors.repository.itemmanufacturer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.panaceasoft.admotors.AppExecutors;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.api.ApiResponse;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.db.ItemManufacturerDao;
import com.panaceasoft.admotors.db.PSCoreDb;
import com.panaceasoft.admotors.repository.common.NetworkBoundResource;
import com.panaceasoft.admotors.repository.common.PSRepository;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.Manufacturer;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Panacea-Soft on 11/25/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Singleton
public class ItemManufacturerRepository extends PSRepository {


    //region Variables

    private ItemManufacturerDao itemManufacturerDao;

    //endregion


    //region Constructor

    @Inject
    ItemManufacturerRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, ItemManufacturerDao itemManufacturerDao) {
        super(psApiService, appExecutors, db);

        Utils.psLog("Inside CityCategoryRepository");

        this.itemManufacturerDao = itemManufacturerDao;
    }

    //endregion


    //region Category Repository Functions for ViewModel

    /**
     * Load Category List
     */

    public LiveData<Resource<List<Manufacturer>>> getAllSearchCityManufacturer(String limit, String offset) {

        return new NetworkBoundResource<List<Manufacturer>, List<Manufacturer>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Manufacturer> item) {
                Utils.psLog("SaveCallResult of getAllCategoriesWithUserId");

                try {
                    db.runInTransaction(() -> {
                        db.itemManufacturerDao().deleteAllCityCategory();

                        for (int i = 0; i < item.size(); i++) {
                            itemManufacturerDao.insert(new Manufacturer(i + 1, item.get(i).id, item.get(i).name, item.get(i).status, item.get(i).addedDate,
                                    item.get(i).addedUserId, item.get(i).updateDate, item.get(i).updatedUserId, item.get(i).updatedFlag, item.get(i).defaultPhoto, item.get(i).defaultIcon));
                        }
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }


            @Override
            protected boolean shouldFetch(@Nullable List<Manufacturer> data) {

                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<Manufacturer>> loadFromDb() {
                Utils.psLog("Load From Db (All Categories)");

                if(limit.equals(String.valueOf(Config.LIMIT_MANUFACTURER_COUNT_IN_DASHBOARD))){
                    return itemManufacturerDao.getItemManufacturerByLimit(limit);
                }else{
                    return itemManufacturerDao.getAllCityManufacturerById();
                }


            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Manufacturer>>> createCall() {
                Utils.psLog("Call Get All Categories webservice.");

                return psApiService.getManufacturer(Config.API_KEY, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of About Us");
            }

        }.asLiveData();
    }

    public LiveData<Resource<Boolean>> getNextSearchCityManufacturer(String limit, String offset) {
        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();
        LiveData<ApiResponse<List<Manufacturer>>> apiResponse = psApiService.getManufacturer(Config.API_KEY, limit, offset);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection Constant Conditions
            if (response.isSuccessful()) {

                appExecutors.diskIO().execute(() -> {

                    try {
                        db.runInTransaction(() -> {
                            if (response.body != null) {

                                int finalIndex = itemManufacturerDao.getMaxSortingByValue();

                                int startIndex = finalIndex + 1;

                                for (int i = 0; i < response.body.size(); i++) {
                                    itemManufacturerDao.insert(new Manufacturer(startIndex + i, response.body.get(i).id, response.body.get(i).name,response.body.get(i).status, response.body.get(i).addedDate,
                                            response.body.get(i).addedUserId,  response.body.get(i).updateDate, response.body.get(i).updatedUserId, response.body.get(i).updatedFlag, response.body.get(i).defaultPhoto, response.body.get(i).defaultIcon));
                                }

                            }
                        });
                    } catch (Exception ex) {
                        Utils.psErrorLog("Error at ", ex);
                    }

                    statusLiveData.postValue(Resource.success(true));
                });

            } else {
                statusLiveData.postValue(Resource.error(response.errorMessage, null));
            }

        });

        return statusLiveData;
    }
}
