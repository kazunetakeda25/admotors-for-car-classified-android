package com.panaceasoft.admotors.repository.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.panaceasoft.admotors.AppExecutors;
import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.api.ApiResponse;
import com.panaceasoft.admotors.api.PSApiService;
import com.panaceasoft.admotors.db.ItemDao;
import com.panaceasoft.admotors.db.PSCoreDb;
import com.panaceasoft.admotors.repository.common.NetworkBoundResource;
import com.panaceasoft.admotors.repository.common.PSRepository;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewobject.ApiStatus;
import com.panaceasoft.admotors.viewobject.Image;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.ItemFavourite;
import com.panaceasoft.admotors.viewobject.ItemFromFollower;
import com.panaceasoft.admotors.viewobject.ItemHistory;
import com.panaceasoft.admotors.viewobject.ItemMap;
import com.panaceasoft.admotors.viewobject.ItemSpecs;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.holder.ItemParameterHolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

@Singleton
public class ItemRepository extends PSRepository {
    /**
     * Constructor of PSRepository
     *
     * @param psApiService Panacea-Soft API Service Instance
     * @param appExecutors Executors Instance
     * @param db           Panacea-Soft DB
     */
    private String isSelected;
    private final ItemDao itemDao;

    @Inject
    protected ItemRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, ItemDao itemDao) {
        super(psApiService, appExecutors, db);
        this.itemDao = itemDao;
    }

    //region Get Favourite Product

    public LiveData<Resource<List<Item>>> getFavouriteList(String apiKey, String loginUserId, String offset) {

        return new NetworkBoundResource<List<Item>, List<Item>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Item> itemList) {
                Utils.psLog("SaveCallResult of related products.");

                try {
                    db.runInTransaction(() -> {
                        db.itemDao().deleteAllFavouriteItems();

                        db.itemDao().insertAll(itemList);

                        for (int i = 0; i < itemList.size(); i++) {
                            db.itemDao().insertFavourite(new ItemFavourite(itemList.get(i).id, i + 1));
                        }

                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Item> data) {

                // Recent news always load from server
                return connectivity.isConnected();

            }

            @NonNull
            @Override
            protected LiveData<List<Item>> loadFromDb() {
                Utils.psLog("Load related From Db");

                return db.itemDao().getAllFavouriteProducts();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Item>>> createCall() {
                Utils.psLog("Call API Service to get related.");

                return psApiService.getFavouriteList(apiKey, Utils.checkUserId(loginUserId), String.valueOf(Config.ITEM_COUNT), offset);


            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getRelated) : " + message);
            }

        }.asLiveData();
    }

    public LiveData<Resource<Boolean>> getNextPageFavouriteProductList(String loginUserId, String offset) {

        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();

        LiveData<ApiResponse<List<Item>>> apiResponse = psApiService.getFavouriteList(Config.API_KEY, Utils.checkUserId(loginUserId), String.valueOf(Config.ITEM_COUNT), offset);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection Constant Conditions
            if (response.isSuccessful()) {

                appExecutors.diskIO().execute(() -> {

                    try {
                        db.runInTransaction(() -> {
                            if (response.body != null) {

                                int lastIndex = db.itemDao().getMaxSortingFavourite();
                                lastIndex = lastIndex + 1;

                                for (int i = 0; i < response.body.size(); i++) {
                                    db.itemDao().insertFavourite(new ItemFavourite(response.body.get(i).id, lastIndex + i));
                                }

                                db.itemDao().insertAll(response.body);
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
    //region Get Item List

    public LiveData<List<Item>> getItemListFromDbByKey(String loginUserId, String limit, String
            offset, ItemParameterHolder itemParameterHolder) {

        Utils.psLog("Load getProductListByKey From Db");

        if (!itemParameterHolder.lat.isEmpty() && !itemParameterHolder.lng.isEmpty() && !itemParameterHolder.mapMiles.isEmpty()) {
            itemParameterHolder.location_id = Constants.EMPTY_STRING;
        }

        String mapKey = itemParameterHolder.getItemMapKey();
        if (!limit.equals(Config.LIMIT_FROM_DB_COUNT)) {
            return itemDao.getItemByKey(mapKey);
        } else {
            return itemDao.getItemByKeyByLimit(mapKey, limit);
        }

    }

    public LiveData<Resource<List<Item>>> getItemListByKey(String loginUserId, String
            limit, String offset, ItemParameterHolder itemParameterHolder) {

        return new NetworkBoundResource<List<Item>, List<Item>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Item> itemList) {
                Utils.psLog("SaveCallResult of getProductListByKey.");

                try {
                    db.runInTransaction(() -> {
                        if (!itemParameterHolder.lat.isEmpty() && !itemParameterHolder.lng.isEmpty() && !itemParameterHolder.mapMiles.isEmpty()) {
                            itemParameterHolder.location_id = Constants.EMPTY_STRING;
                        }

                        String mapKey = itemParameterHolder.getItemMapKey();

                        db.itemMapDao().deleteByMapKey(mapKey);

                        itemDao.insertAll(itemList);

                        String dateTime = Utils.getDateTime();

                        for (int i = 0; i < itemList.size(); i++) {
                            db.itemMapDao().insert(new ItemMap(mapKey + itemList.get(i).id, mapKey, itemList.get(i).id, i + 1, dateTime));
                        }
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Item> data) {

                // Recent news always load from server
                return connectivity.isConnected();

            }

            @NonNull
            @Override
            protected LiveData<List<Item>> loadFromDb() {
                Utils.psLog("Load getProductListByKey From Db");

                if (!itemParameterHolder.lat.isEmpty() && !itemParameterHolder.lng.isEmpty() && !itemParameterHolder.mapMiles.isEmpty()) {
                    itemParameterHolder.location_id = Constants.EMPTY_STRING;
                }

                String mapKey = itemParameterHolder.getItemMapKey();
                if (!limit.equals(Config.LIMIT_FROM_DB_COUNT)) {
                    return itemDao.getItemByKey(mapKey);
                } else {
                    return itemDao.getItemByKeyByLimit(mapKey, limit);
                }

            }


            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Item>>> createCall() {
                Utils.psLog("Call API Service to getProductListByKey.");

                return psApiService.searchItem(Config.API_KEY,
                        limit, offset, loginUserId,
                        itemParameterHolder.keyword,
                        itemParameterHolder.manufacturer_id,
                        itemParameterHolder.model_id,
                        itemParameterHolder.type_id,
                        itemParameterHolder.price_type_id,
                        itemParameterHolder.currency_id,
                        itemParameterHolder.location_id,
                        itemParameterHolder.condition_id,
                        itemParameterHolder.color_id,
                        itemParameterHolder.fuelType_id,
                        itemParameterHolder.buildType_id,
                        itemParameterHolder.sellerTypeId,
                        itemParameterHolder.transmissionId,
                        itemParameterHolder.max_price,
                        itemParameterHolder.min_price,
                        itemParameterHolder.lat,
                        itemParameterHolder.lng,
                        itemParameterHolder.mapMiles,
                        itemParameterHolder.order_by,
                        itemParameterHolder.order_type,
                        itemParameterHolder.userId);

            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getProductListByKey) : " + message);
            }

        }.asLiveData();

    }

    public LiveData<Resource<Boolean>> getNextPageProductListByKey(ItemParameterHolder
                                                                           itemParameterHolder, String loginUserId, String limit, String offset) {

        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();

//        prepareRatingValueForServer(productParameterHolder);

        LiveData<ApiResponse<List<Item>>> apiResponse = psApiService.searchItem(Config.API_KEY,
                limit, offset, loginUserId,
                itemParameterHolder.keyword,
                itemParameterHolder.manufacturer_id,
                itemParameterHolder.model_id,
                itemParameterHolder.type_id,
                itemParameterHolder.price_type_id,
                itemParameterHolder.currency_id,
                itemParameterHolder.location_id,
                itemParameterHolder.condition_id,
                itemParameterHolder.color_id,
                itemParameterHolder.fuelType_id,
                itemParameterHolder.buildType_id,
                itemParameterHolder.sellerTypeId,
                itemParameterHolder.transmissionId,
                itemParameterHolder.max_price,
                itemParameterHolder.min_price,
                itemParameterHolder.lat,
                itemParameterHolder.lng,
                itemParameterHolder.mapMiles,
                itemParameterHolder.order_by,
                itemParameterHolder.order_type,
                itemParameterHolder.userId);


        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection Constant Conditions
            if (response.isSuccessful()) {

                if (response.body != null) {
                    appExecutors.diskIO().execute(() -> {

                        try {
                            db.runInTransaction(() -> {

                                itemDao.insertAll(response.body);

                                int finalIndex = db.itemMapDao().getMaxSortingByValue(itemParameterHolder.getItemMapKey());

                                int startIndex = finalIndex + 1;

                                String mapKey = itemParameterHolder.getItemMapKey();
                                String dateTime = Utils.getDateTime();

                                for (int i = 0; i < response.body.size(); i++) {
                                    db.itemMapDao().insert(new ItemMap(mapKey + response.body.get(i).id, mapKey, response.body.get(i).id, startIndex + i, dateTime));
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

            } else {
                statusLiveData.postValue(Resource.error(response.errorMessage, null));
            }
        });

        return statusLiveData;

    }

    //endregion


    //region Favourite post

    public LiveData<Resource<Boolean>> uploadFavouritePostToServer(String itemId, String userId) {

        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            try {
                try {
                    db.runInTransaction(() -> {

                        isSelected = itemDao.selectFavouriteById(itemId);
                        if (isSelected.equals(Constants.ONE)) {
                            itemDao.updateProductForFavById(itemId, Constants.ZERO);
                        } else {
                            itemDao.updateProductForFavById(itemId, Constants.ONE);
                        }

                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }

                // Call the API Service
                Response<Item> response;

                response = psApiService.setPostFavourite(Config.API_KEY, itemId, userId).execute();

                // Wrap with APIResponse Class
                ApiResponse<Item> apiResponse = new ApiResponse<>(response);

                // If response is successful
                if (apiResponse.isSuccessful()) {

                    try {
                        db.runInTransaction(() -> {
                            if (apiResponse.body != null) {
                                itemDao.insert(apiResponse.body);

                                if (isSelected.equals(Constants.ONE)) {
                                    db.itemDao().deleteFavouriteItemByItemId(apiResponse.body.id);
                                } else {
                                    int lastIndex = db.itemDao().getMaxSortingFavourite();
                                    lastIndex = lastIndex + 1;

                                    db.itemDao().insertFavourite(new ItemFavourite(apiResponse.body.id, lastIndex));
                                }
                            }
                        });
                    } catch (Exception ex) {
                        Utils.psErrorLog("Error at ", ex);
                    }

                    statusLiveData.postValue(Resource.success(apiResponse.getNextPage() != null));

                } else {

                    try {
                        db.runInTransaction(() -> {

                            isSelected = itemDao.selectFavouriteById(itemId);
                            if (isSelected.equals(Constants.ONE)) {
                                itemDao.updateProductForFavById(itemId, Constants.ZERO);
                            } else {
                                itemDao.updateProductForFavById(itemId, Constants.ONE);
                            }
                        });
                    } catch (Exception ex) {
                        Utils.psErrorLog("Error at ", ex);
                    }

                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, false));
                }

            } catch (IOException e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), false));
            }
        });

        return statusLiveData;
    }

    //endregion

    //item image upload

    public LiveData<Resource<Image>> uploadItemImage(String filePath, String imageId, String
            itemId) {

        //Init File
        MultipartBody.Part body = null;
        if (!filePath.equals("")) {
            File file = new File(filePath);
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // MultipartBody.Part is used to send also the actual file news_title
            body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        }
        // add another part within the multipart request
        RequestBody idRB =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), itemId);

        RequestBody imgIdRB =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), imageId);

        MultipartBody.Part finalBody = body;
        return new NetworkBoundResource<Image, Image>(appExecutors) {

            // Temp ResultType To Return

            @Override
            protected void saveCallResult(@NonNull Image image) {
                Utils.psLog("SaveCallResult");

                try {
                    db.runInTransaction(() -> {
                        db.imageDao().deleteTable();
                        db.imageDao().insert(image);
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Image data) {
                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<Image> loadFromDb() {

                return db.imageDao().getUploadImage();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Image>> createCall() {
                Utils.psLog("Call API Service to upload image.");

                return psApiService.itemImageUpload(Config.API_KEY, idRB, imgIdRB, finalBody);
            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of uploading image.");
            }
        }.asLiveData();
    }

    //endregion

    //item upload

    public LiveData<Resource<Item>> uploadItem(String manufacturerId, String modelId, String itemTypeId, String itemPriceTypeId, String itemCurrencyId, String conditionId,
                                               String locationId, String colorId, String fuelTypeId, String buildTypeId, String sellerTypeId, String transmissionId, String description,
                                               String highlightInfo, String price, String businessMode, String isSoldOut, String title, String address, String lat,
                                               String lng, String plateNumber, String enginePower, String steeringPosition,String numberOfOwner, String trimName, String vehicleId,
                                               String priceUnit, String year, String licenceStatus, String maxPassengers, String numberOfDoor, String milegate, String licenseExpirationDate,
                                               String itemId, String userId) {

        final MutableLiveData<Resource<Item>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            try {
                // Call the API Service
                Response<Item> response;

                response = psApiService.itemUpload(Config.API_KEY, manufacturerId, modelId, itemTypeId, itemPriceTypeId, itemCurrencyId, conditionId, locationId, colorId, fuelTypeId,
                        buildTypeId, sellerTypeId, transmissionId, description, highlightInfo, price, businessMode, isSoldOut, title, address, lat, lng, plateNumber, enginePower,
                        steeringPosition, numberOfOwner, trimName, vehicleId, priceUnit, year,licenceStatus,maxPassengers,numberOfDoor,milegate,licenseExpirationDate, itemId, userId).execute();

                // Wrap with APIResponse Class
                ApiResponse<Item> apiResponse = new ApiResponse<>(response);

                // If response is successful
                if (apiResponse.isSuccessful()) {

                    if (apiResponse.body != null) {

                        try {
                            db.runInTransaction(() -> itemDao.insert(apiResponse.body));
                        } catch (Exception ex) {
                            Utils.psErrorLog("Error at ", ex);
                        }

                        statusLiveData.postValue(Resource.success(apiResponse.body));
                    }

                } else {

                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, null));
                }

            } catch (IOException e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), null));
            }
        });

        return statusLiveData;
    }

    //endregion

    public LiveData<Resource<List<Item>>> getItemFromFollowerList(String loginUserId, String
            limit, String offset) {

        return new NetworkBoundResource<List<Item>, List<Item>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Item> itemList) {
                Utils.psLog("SaveCallResult of related products.");

                try {
                    db.runInTransaction(() -> {
                        db.itemDao().deleteAllItemFromFollower();
                        db.itemDao().insertAll(itemList);

                        for (int i = 0; i < itemList.size(); i++) {
                            db.itemDao().insertItemFromFollower(new ItemFromFollower(itemList.get(i).id, itemList.get(i).user.userId, i + 1));
                        }
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Item> data) {

                // Recent news always load from server
                return connectivity.isConnected();

            }

            @NonNull
            @Override
            protected LiveData<List<Item>> loadFromDb() {
                Utils.psLog("Load related From Db");

                return db.itemDao().getAllItemFromFollower();

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Item>>> createCall() {
                Utils.psLog("Call API Service to get related.");

                return psApiService.getItemListFromFollower(Config.API_KEY, Utils.checkUserId(loginUserId), limit, offset);

            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getRelated) : " + message);
            }

        }.asLiveData();

    }

    public LiveData<Resource<Boolean>> getNextPageItemFromFollowerList(String
                                                                               loginUserId, String limit, String offset) {

        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();

        LiveData<ApiResponse<List<Item>>> apiResponse = psApiService.getItemListFromFollower(Config.API_KEY, loginUserId, limit, offset);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection Constant Conditions
            if (response.isSuccessful()) {

                if (response.body != null) {
                    appExecutors.diskIO().execute(() -> {

                        try {
                            db.runInTransaction(() -> itemDao.insertAll(response.body));
                        } catch (Exception ex) {
                            Utils.psErrorLog("Error at ", ex);
                        }

                        statusLiveData.postValue(Resource.success(true));
                    });
                } else {
                    statusLiveData.postValue(Resource.error(response.errorMessage, null));
                }

            } else {
                statusLiveData.postValue(Resource.error(response.errorMessage, null));
            }
        });

        return statusLiveData;
    }


    //region Get Product detail

    public LiveData<Resource<Item>> getItemDetail(String apiKey, String itemId, String
            historyFlag, String userId) {

        return new NetworkBoundResource<Item, Item>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull Item itemList) {
                Utils.psLog("SaveCallResult of recent products.");

                try {
                    db.runInTransaction(() -> {

                        itemDao.insert(itemList);

                        db.specsDao().deleteItemSpecsById(itemId);

                        if (historyFlag.equals(Constants.ONE)) {

                            db.historyDao().insert(new ItemHistory(itemId, itemList.title, itemList.defaultPhoto.imgPath, Utils.getDateTime()));
                        }
                    });
                } catch (
                        Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Item data) {

                // Recent news always load from server
                return connectivity.isConnected();

            }

            @NonNull
            @Override
            protected LiveData<Item> loadFromDb() {
                Utils.psLog("Load discount From Db");

                return itemDao.getItemById(itemId);

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Item>> createCall() {
                Utils.psLog("Call API Service to get discount.");

                return psApiService.getItemDetail(apiKey, itemId, Utils.checkUserId(userId));

            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getDiscount) : " + message);
            }

        }.

                asLiveData();

    }

    //endregion

    //region mark as sold out
    public LiveData<Resource<Item>> markSoldOutItem(String apiKey, String itemId, String userId) {

        return new NetworkBoundResource<Item, Item>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull Item item) {
                Utils.psLog("SaveCallResult of recent products.");

                try {
                    db.runInTransaction(() -> {
                        itemDao.insert(item);

                        List<ItemMap> itemMapList = db.itemMapDao().getItemListByItemId(item.id);
                        db.itemMapDao().insert(itemMapList);
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Item data) {

                // Recent news always load from server
                return connectivity.isConnected();

            }

            @NonNull
            @Override
            protected LiveData<Item> loadFromDb() {
                Utils.psLog("Load discount From Db");

                return itemDao.getItemById(itemId);

            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Item>> createCall() {
                Utils.psLog("Call API Service to get discount.");

                return psApiService.markSoldOutItem(apiKey, userId, itemId);

            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed (getDiscount) : " + message);
            }

        }.asLiveData();
    }

    //endregion

    //region Touch count post

    public LiveData<Resource<Boolean>> uploadTouchCountPostToServer(String userId, String
            itemId) {

        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<ApiStatus> response;

            try {
                response = psApiService.setrawPostTouchCount(
                        Config.API_KEY, itemId, Utils.checkUserId(userId)).execute();

                ApiResponse<ApiStatus> apiResponse = new ApiResponse<>(response);

                if (apiResponse.isSuccessful()) {
                    statusLiveData.postValue(Resource.success(true));
                } else {
                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, false));
                }

            } catch (IOException e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), false));
            }

        });

        return statusLiveData;
    }

    //endregion

    //region delete item post

    public LiveData<Resource<Boolean>> deletePostItem(String itemId, String loginUserId) {

        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<ApiStatus> response;

            try {
                response = psApiService.deleteItem(
                        Config.API_KEY, itemId).execute();

                ApiResponse<ApiStatus> apiResponse = new ApiResponse<>(response);

                if (apiResponse.isSuccessful()) {

                    try {
                        db.runInTransaction(() -> {
                            itemDao.deleteItemById(itemId);

                            ItemParameterHolder holder = new ItemParameterHolder();
                            holder.userId = loginUserId;

                            String key = holder.getItemMapKey();
                            db.itemMapDao().deleteByMapKeyAndItemId(key, itemId);
                        });
                    } catch (Exception ex) {
                        Utils.psErrorLog("Error at ", ex);
                    }

                    statusLiveData.postValue(Resource.success(true));

                } else {
                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, false));
                }

            } catch (IOException e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), false));
            }

        });

        return statusLiveData;
    }

    //endregion

    //report item

    public LiveData<Resource<Boolean>> reportItem(String itemId, String reportUserId) {
        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<ApiStatus> response;

            try {
                response = psApiService.reportItem(Config.API_KEY, itemId, reportUserId).execute();

                if (response.isSuccessful()) {
                    statusLiveData.postValue(Resource.success(true));
                } else {
                    statusLiveData.postValue(Resource.error("error", false));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return statusLiveData;
    }

    //region Get history

    public LiveData<List<ItemHistory>> getAllHistoryList(String offset) {

        return db.historyDao().getAllHistoryItemListData(offset);

    }

    //endregion

    //region Get Product specs

    public LiveData<List<ItemSpecs>> getAllSpecificaions(String itemId) {
        return db.specsDao().getItemSpecsById(itemId);
    }

    //endregion

    //region Get item detail

    public LiveData<Item> getItemDetailFromDBById(String itemId) {
        return itemDao.getItemById(itemId);
    }

    //endregion


}
//endregion
