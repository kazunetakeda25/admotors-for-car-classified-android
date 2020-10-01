package com.panaceasoft.admotors.viewmodel.item;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.panaceasoft.admotors.Config;
import com.panaceasoft.admotors.repository.item.ItemRepository;
import com.panaceasoft.admotors.utils.AbsentLiveData;
import com.panaceasoft.admotors.utils.Constants;
import com.panaceasoft.admotors.utils.Utils;
import com.panaceasoft.admotors.viewmodel.common.PSViewModel;
import com.panaceasoft.admotors.viewobject.Image;
import com.panaceasoft.admotors.viewobject.Item;
import com.panaceasoft.admotors.viewobject.common.Resource;
import com.panaceasoft.admotors.viewobject.holder.ItemParameterHolder;

import java.util.List;

import javax.inject.Inject;

public class ItemViewModel extends PSViewModel {

    private final LiveData<Resource<List<Item>>> itemListByKeyData;
    private final MutableLiveData<ItemViewModel.ItemTmpDataHolder> itemListByKeyObj = new MutableLiveData<>();

    private final LiveData<List<Item>> itemListFromDbByKeyData;
    private final MutableLiveData<ItemViewModel.ItemTmpDataHolder> itemListFromDbByKeyObj = new MutableLiveData<>();

    private final LiveData<Resource<Item>> productDetailListData;
    private MutableLiveData<ItemViewModel.TmpDataHolder> productDetailObj = new MutableLiveData<>();

    private final LiveData<Resource<Item>> markAsSoldOutItemData;
    private MutableLiveData<ItemViewModel.TmpDataHolder> markAsSoldOutItemObj = new MutableLiveData<>();

    private final LiveData<Item> productDetailFromDBByIdData;
    private MutableLiveData<ItemViewModel.TmpDataHolder> productDetailFromDBByIdObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageItemListByKeyData;
    private final MutableLiveData<ItemViewModel.ItemTmpDataHolder> nextPageItemListByKeyObj = new MutableLiveData<>();

    private LiveData<Resource<Image>> uploadItemImageData;
    private MutableLiveData<UploadItemImageTmpDataHolder> uploadItemImageObj = new MutableLiveData<>();

    private LiveData<Resource<Item>> uploadItemData;
    private MutableLiveData<UploadItemTmpDataHolder> uploadItemObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> reportItemStatusData;
    private final MutableLiveData<ItemViewModel.ReportItemTmpDataHolder> reportItemStatusObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> deleteItemData;
    private final MutableLiveData<ItemViewModel.DeleteItemTmpDataHolder> deleteItemObj = new MutableLiveData<>();

    public ItemParameterHolder holder = new ItemParameterHolder();
    public String latValue = "", lngValue = "";
    public Item itemContainer;

    public String itemDescription = "";
    public String itemId = "";
    public String cityId = "";
    public String historyFlag = "";
    public String customImageUri;
    public String locationId = "";
    public String locationName = "";
    public String otherUserId = "";
    public String otherUserName = "";
    public String is_sold_out = "";
    public String mapLat = "";
    public String mapLng = "";

    public Item currentItem;

    public String firstImagePath = Constants.EMPTY_STRING, secImagePath = Constants.EMPTY_STRING,
            thirdImagePath = Constants.EMPTY_STRING, fouthImagePath = Constants.EMPTY_STRING, fifthImagePath = Constants.EMPTY_STRING;

    public String userId = "";


    @Inject
    ItemViewModel(ItemRepository repository) {

        itemListFromDbByKeyData = Transformations.switchMap(itemListFromDbByKeyObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getItemListFromDbByKey(obj.loginUserId, obj.limit, obj.offset, obj.itemParameterHolder);

        });

        itemListByKeyData = Transformations.switchMap(itemListByKeyObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getItemListByKey(obj.loginUserId, obj.limit, obj.offset, obj.itemParameterHolder);

        });

        nextPageItemListByKeyData = Transformations.switchMap(nextPageItemListByKeyObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getNextPageProductListByKey(obj.itemParameterHolder, obj.loginUserId, obj.limit, obj.offset);

        });

        reportItemStatusData = Transformations.switchMap(reportItemStatusObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.reportItem(obj.itemId, obj.reportUserId);

        });

        deleteItemData = Transformations.switchMap(deleteItemObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.deletePostItem(obj.itemId, obj.loginUserId);

        });

        //  item detail List
        productDetailListData = Transformations.switchMap(productDetailObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("product detail List.");
            return repository.getItemDetail(Config.API_KEY, obj.itemId, obj.historyFlag, obj.userId);
        });

        //  item mark as sold out
        markAsSoldOutItemData = Transformations.switchMap(markAsSoldOutItemObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("product detail List.");
            return repository.markSoldOutItem(Config.API_KEY, obj.itemId, obj.userId);
        });

        productDetailFromDBByIdData = Transformations.switchMap(productDetailFromDBByIdObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("product detail List.");
            return repository.getItemDetailFromDBById(obj.itemId);
        });

        uploadItemImageData = Transformations.switchMap(uploadItemImageObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.uploadItemImage(obj.filePath, obj.imageId, obj.itemId);

        });

        uploadItemData = Transformations.switchMap(uploadItemObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.uploadItem(obj.manufacturerId, obj.modelId, obj.itemTypeId, obj.itemPriceTypeId, obj.itemCurrencyId, obj.conditionId, obj.locationId, obj.colorId,
                    obj.fuelTypeId, obj.buildTypeId, obj.sellerTypeId, obj.transmissionId, obj.description, obj.highlightInfo, obj.price, obj.businessMode, obj.isSoldOut, obj.title,
                    obj.address, obj.lat, obj.lng, obj.plateNumber, obj.enginePower, obj.steeringPosition, obj.numberOfOwner, obj.trimName,obj.vehicleId,obj.priceUnit, obj.year,
                    obj.licenceStatus,obj.maxPassengers,obj.numberOfDoor, obj.mileage,obj.licenceExpirationDate, obj.itemId, obj.userId);


        });
    }


    //item image upload

    public void setUploadItemImageObj(String filePath, String itemId, String imageId) {
        UploadItemImageTmpDataHolder tmpDataHolder = new UploadItemImageTmpDataHolder(filePath, itemId, imageId);

        this.uploadItemImageObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Image>> getUploadItemImageData() {
        return uploadItemImageData;
    }

    //region getItemList

    //item upload

    public void setUploadItemObj(String manufacturerId, String modelId, String itemTypeId, String itemPriceTypeId, String itemCurrencyId, String conditionId, String locationId,
                                 String colorId, String fuelTypeId, String buildTypeId, String sellerTypeId, String transmissionId, String description, String highlightInfo, String price,
                                 String businessMode, String isSoldOut, String title, String address, String lat, String lng, String platNumber, String enginePower, String steeringPosition,
                                 String numberOfOwner, String trimName, String vehicleId, String priceUnit, String year, String licenceStatus, String maxPassengers,
                                 String numberOfDoor, String mileage, String licenceExpirationDate, String itemId, String userId) {

        UploadItemTmpDataHolder tmpDataHolder = new UploadItemTmpDataHolder(manufacturerId, modelId, itemTypeId, itemPriceTypeId, itemCurrencyId, conditionId, locationId, colorId, fuelTypeId, buildTypeId,
                sellerTypeId, transmissionId, description, highlightInfo, price, businessMode, isSoldOut, title, address, lat, lng, platNumber, enginePower, steeringPosition, numberOfOwner, trimName,
                vehicleId, priceUnit, year, licenceStatus, maxPassengers, numberOfDoor, mileage, licenceExpirationDate, itemId, userId);

        this.uploadItemObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Item>> getUploadItemData() {
        return uploadItemData;
    }

    //endregion

    public void setItemListFromDbByKeyObj(String loginUserId, String limit, String offset, ItemParameterHolder parameterHolder) {

        ItemTmpDataHolder tmpDataHolder = new ItemTmpDataHolder(limit, offset, loginUserId, parameterHolder);

        this.itemListFromDbByKeyObj.setValue(tmpDataHolder);
        setLoadingState(true);


    }

    public void setItemListByKeyObj(String loginUserId, String limit, String offset, ItemParameterHolder parameterHolder) {
        if (!isLoading) {
            ItemTmpDataHolder tmpDataHolder = new ItemTmpDataHolder(limit, offset, loginUserId, parameterHolder);

            this.itemListByKeyObj.setValue(tmpDataHolder);
            setLoadingState(true);

        }
    }

    public LiveData<List<Item>> getItemListFromDbByKeyData() {
        return itemListFromDbByKeyData;
    }

    public LiveData<Resource<List<Item>>> getItemListByKeyData() {
        return itemListByKeyData;
    }

    public void setNextPageItemListByKeyObj(String limit, String offset, String loginUserId, ItemParameterHolder parameterHolder) {

        if (!isLoading) {
            ItemTmpDataHolder tmpDataHolder = new ItemTmpDataHolder(limit, offset, loginUserId, parameterHolder);

            setLoadingState(true);

            this.nextPageItemListByKeyObj.setValue(tmpDataHolder);
        }
    }

    public LiveData<Resource<Boolean>> getNextPageItemListByKeyData() {
        return nextPageItemListByKeyData;
    }

    //report item

    public void setReportItemStatusObj(String itemId, String reportUserId) {

//        if (!isLoading) {
        ReportItemTmpDataHolder tmpDataHolder = new ReportItemTmpDataHolder(itemId, reportUserId);

//            setLoadingState(true);

        this.reportItemStatusObj.setValue(tmpDataHolder);
//        }
    }

    public LiveData<Resource<Boolean>> getReportItemStatusData() {
        return reportItemStatusData;
    }

    //endregion

    //delete item

    public void setDeleteItemObj(String itemId, String loginUserId) {

        DeleteItemTmpDataHolder deleteItemTmpDataHolder = new DeleteItemTmpDataHolder(itemId, loginUserId);

        this.deleteItemObj.setValue(deleteItemTmpDataHolder);

    }

    public LiveData<Resource<Boolean>> getDeleteItemStatus() {
        return deleteItemData;
    }

    //endregion

    class ItemTmpDataHolder {

        private String limit, offset, loginUserId;
        private ItemParameterHolder itemParameterHolder;

        public ItemTmpDataHolder(String limit, String offset, String loginUserId, ItemParameterHolder itemParameterHolder) {
            this.limit = limit;
            this.offset = offset;
            this.loginUserId = loginUserId;
            this.itemParameterHolder = itemParameterHolder;
        }
    }

    class ReportItemTmpDataHolder {

        private String itemId, reportUserId;

        public ReportItemTmpDataHolder(String itemId, String reportUserId) {
            this.itemId = itemId;
            this.reportUserId = reportUserId;
        }
    }

    class DeleteItemTmpDataHolder {

        public String itemId;
        public String loginUserId;

        private DeleteItemTmpDataHolder(String itemId, String loginUserId) {
            this.itemId = itemId;
            this.loginUserId = loginUserId;

        }
    }

    //region Getter And Setter for item detail List

    public void setItemDetailObj(String itemId, String historyFlag, String userId) {
        if (!isLoading) {
            ItemViewModel.TmpDataHolder tmpDataHolder = new ItemViewModel.TmpDataHolder();
            tmpDataHolder.itemId = itemId;
            tmpDataHolder.historyFlag = historyFlag;
            tmpDataHolder.userId = userId;
            productDetailObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Item>> getItemDetailData() {
        return productDetailListData;
    }

    //endregion

    //region Getter And Setter for item mark as sold out

    public void setMarkAsSoldOutItemObj(String itemId, String userId) {
        if (!isLoading) {
            ItemViewModel.TmpDataHolder tmpDataHolder = new ItemViewModel.TmpDataHolder();
            tmpDataHolder.itemId = itemId;
            tmpDataHolder.userId = userId;
            markAsSoldOutItemObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<Item>> getMarkAsSoldOutItemData() {
        return markAsSoldOutItemData;
    }

    //endregion

    //region Getter And Setter for item detail List

    public void setItemDetailFromDBById(String itemId) {
        if (!isLoading) {
            ItemViewModel.TmpDataHolder tmpDataHolder = new ItemViewModel.TmpDataHolder();
            tmpDataHolder.itemId = itemId;
            productDetailFromDBByIdObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Item> getItemDetailFromDBByIdData() {
        return productDetailFromDBByIdData;
    }

    //endregion

    //region Holder
    class TmpDataHolder {
        public String offset = "";
        public String itemId = "";
        public String historyFlag = "";
        public String userId = "";
        public String cityId = "";
        public Boolean isConnected = false;
    }
    //endregion

    class UploadItemImageTmpDataHolder {

        String filePath, itemId, imageId;

        private UploadItemImageTmpDataHolder(String filePath, String itemId, String imageId) {
            this.filePath = filePath;
            this.itemId = itemId;
            this.imageId = imageId;
        }
    }

    class UploadItemTmpDataHolder {

        String manufacturerId, modelId, itemTypeId, itemPriceTypeId, itemCurrencyId, conditionId, locationId, colorId, fuelTypeId, buildTypeId,
                sellerTypeId, transmissionId, description, highlightInfo, price, businessMode, isSoldOut, title, address, lat, lng, plateNumber,
                enginePower, steeringPosition, numberOfOwner, trimName, vehicleId, priceUnit, year, licenceStatus, maxPassengers, numberOfDoor, mileage, licenceExpirationDate, itemId, userId;

        public UploadItemTmpDataHolder(String manufacturerId, String modelId, String itemTypeId, String itemPriceTypeId,
                                       String itemCurrencyId, String conditionId, String locationId, String colorId, String fuelTypeId, String buildTypeId, String sellerTypeId, String transmissionId,
                                       String description, String highlightInfo, String price, String businessMode, String isSoldOut, String title, String address,
                                       String lat, String lng, String plateNumber, String enginePower, String steeringPosition, String numberOfOwner, String trimName, String vehicleId,
                                       String priceUnit, String year, String licenceStatus, String maxPassengers, String numberOfDoor, String mileage, String licenceExpirationDate,
                                       String itemId, String userId) {
            this.manufacturerId = manufacturerId;
            this.modelId = modelId;
            this.itemTypeId = itemTypeId;
            this.itemPriceTypeId = itemPriceTypeId;
            this.itemCurrencyId = itemCurrencyId;
            this.conditionId = conditionId;
            this.locationId = locationId;
            this.colorId = colorId;
            this.fuelTypeId = fuelTypeId;
            this.buildTypeId = buildTypeId;
            this.sellerTypeId = sellerTypeId;
            this.transmissionId = transmissionId;
            this.description = description;
            this.highlightInfo = highlightInfo;
            this.price = price;
            this.businessMode = businessMode;
            this.isSoldOut = isSoldOut;
            this.title = title;
            this.address = address;
            this.lat = lat;
            this.lng = lng;
            this.plateNumber = plateNumber;
            this.enginePower = enginePower;
            this.steeringPosition = steeringPosition;
            this.numberOfOwner = numberOfOwner;
            this.trimName = trimName;
            this.vehicleId = vehicleId;
            this.priceUnit = priceUnit;
            this.year = year;
            this.licenceStatus = licenceStatus;
            this.maxPassengers = maxPassengers;
            this.numberOfDoor = numberOfDoor;
            this.mileage = mileage;
            this.licenceExpirationDate = licenceExpirationDate;
            this.itemId = itemId;
            this.userId = userId;
        }
    }
}

