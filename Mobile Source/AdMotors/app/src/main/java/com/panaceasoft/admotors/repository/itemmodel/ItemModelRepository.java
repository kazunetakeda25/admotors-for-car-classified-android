package com.panaceasoft.admotors.repository.itemmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.panaceasoft.admotors.AppExecutors;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.api.ApiResponse;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.db.ItemModelDao;
import com.panaceasoft.admotors.db.PSCoreDb;
import com.panaceasoft.admotors.repository.common.NetworkBoundResource;
import com.panaceasoft.admotors.repository.common.PSRepository;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.Model;
import com.panaceasoft.admotors.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemModelRepository extends PSRepository {

    private final ItemModelDao itemModelDao;

    @Inject
    ItemModelRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, ItemModelDao subCategoryDao) {
        super(psApiService, appExecutors, db);

        Utils.psLog("Inside SubCategoryRepository");

        this.itemModelDao = subCategoryDao;
    }

    public LiveData<Resource<List<Model>>> getAllItemModelList(String apiKey) {
        return new NetworkBoundResource<List<Model>, List<Model>>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull List<Model> itemList) {

                Utils.psLog("SaveCallResult of getAllSubCategoryList.");

                try {
                    db.runInTransaction(() -> {
                        itemModelDao.deleteAllSubCategory();

                        itemModelDao.insertAll(itemList);

                        for (Model item : itemList) {
                            itemModelDao.insert(new Model(item.id, item.manufacturerId,item.name, item.status, item.addedDate, item.addedUserId,
                                    item.updateDate,item.updatedUserId, item.updatedFlag, item.defaultPhoto, item.defaultIcon));
                        }
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Model> data) {
                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<Model>> loadFromDb() {
                return itemModelDao.getAllModel();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Model>>> createCall() {
                return psApiService.getAllSubCategoryList(apiKey);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of " + message);
            }

        }.asLiveData();
    }

    public LiveData<Resource<List<Model>>> getModelList(String apiKey, String manufacturerId, String limit, String offset) {
        return new NetworkBoundResource<List<Model>, List<Model>>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull List<Model> itemList) {

                Utils.psLog("SaveCallResult of getModelList.");

                try {
                    db.runInTransaction(() -> {
                        itemModelDao.deleteAllSubCategory();

                        itemModelDao.insertAll(itemList);

                        for (Model item : itemList) {
                            itemModelDao.insert(new Model(item.id,item.manufacturerId, item.name, item.status, item.addedDate, item.addedUserId,
                                    item.updateDate, item.updatedUserId, item.updatedFlag, item.defaultPhoto, item.defaultIcon));
                        }
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Model> data) {
                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<Model>> loadFromDb() {
                return itemModelDao.getModelList(manufacturerId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Model>>> createCall() {
                return psApiService.getModelList(apiKey, manufacturerId, limit, offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of " + message);
            }

        }.asLiveData();
    }

    public LiveData<Resource<Boolean>> getNextPageModel(String manufacturerId, String limit, String offset) {
        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();
        LiveData<ApiResponse<List<Model>>> apiResponse = psApiService.getModelList(Config.API_KEY, manufacturerId, limit, offset);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection Constant Conditions
            if (response.isSuccessful()) {

                appExecutors.diskIO().execute(() -> {

                    try {
                        db.runInTransaction(() -> {
                            if (response.body != null) {
                                for (Model item : response.body) {
                                    itemModelDao.insert(new Model(item.id, item.manufacturerId,item.name, item.status, item.addedDate, item.addedUserId, item.updateDate,item.updatedUserId, item.updatedFlag, item.defaultPhoto, item.defaultIcon));
                                }

                                db.itemModelDao().insertAll(response.body);
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
    
    public LiveData<Resource<List<Model>>> getModelWithManufacturerId(String offset, String manufacturerId) {
        return new NetworkBoundResource<List<Model>, List<Model>>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull List<Model> itemList) {

                Utils.psLog("SaveCallResult of Sub Category.");

                try {
                    db.runInTransaction(() -> {
                        itemModelDao.insertAll(itemList);

                        for (Model item : itemList) {
                            itemModelDao.insert(new Model(item.id,item.manufacturerId, item.name, item.status, item.addedDate, item.addedUserId, item.updateDate,item.updatedUserId,item.updatedFlag,
                                    item.defaultPhoto, item.defaultIcon));
                        }
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Model> data) {
                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<Model>> loadFromDb() {
                return itemModelDao.getModelList(manufacturerId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Model>>> createCall() {
                return psApiService.getModelListWithManufacturerId(Config.API_KEY, manufacturerId, "", offset);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of " + message);
            }

        }.asLiveData();
    }

    public LiveData<Resource<Boolean>> getNextPageModelWithManufacturerId(String limit, String offset, String manufacturerId) {
        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();
        LiveData<ApiResponse<List<Model>>> apiResponse = psApiService.getModelListWithManufacturerId(Config.API_KEY, manufacturerId, limit, offset);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection Constant Conditions
            if (response.isSuccessful()) {

                appExecutors.diskIO().execute(() -> {

                    try {
                        db.runInTransaction(() -> {
                            if (response.body != null) {
                                for (Model item : response.body) {
                                    itemModelDao.insert(new Model(item.id,item.manufacturerId, item.name, item.status, item.addedDate, item.addedUserId, item.updateDate,
                                           item.updatedUserId, item.updatedFlag, item.defaultPhoto, item.defaultIcon));
                                }

                                itemModelDao.insertAll(response.body);
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
