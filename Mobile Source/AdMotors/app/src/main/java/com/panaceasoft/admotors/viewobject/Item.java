package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class Item {

    @NonNull
    @SerializedName("id")
    public final String id;

    @SerializedName("item_type_id")
    public final String itemTypeId;

    @SerializedName("item_price_type_id")
    public final String itemPriceTypeId;

    @SerializedName("item_currency_id")
    public final String itemCurrencyId;

    @SerializedName("item_location_id")
    public final String itemLocationId;

    @SerializedName("condition_of_item_id")
    public final String conditionOfItem;

    @SerializedName("color_id")
    public final String colorId;

    @SerializedName("fuel_type_id")
    public final String fuelTypeId;

    @SerializedName("build_type_id")
    public final String buildTypeId;

    @SerializedName("model_id")
    public final String modelId;

    @SerializedName("manufacturer_id")
    public final String manufacutrerId;

    @SerializedName("seller_type_id")
    public final String sellerTypeId;

    @SerializedName("transmission_id")
    public final String transmissionId;

    @SerializedName("description")
    public final String description;

    @SerializedName("highlight_info")
    public final String highlightInfo;

    @SerializedName("price")
    public final String price;

    @SerializedName("business_mode")
    public final String businessMode;

    @SerializedName("is_sold_out")
    public final String isSoldOut;

    @SerializedName("title")
    public final String title;

    @SerializedName("address")
    public final String address;

    @SerializedName("lat")
    public final String lat;

    @SerializedName("lng")
    public final String lng;

    @SerializedName("status")
    public final String status;

    @SerializedName("added_date")
    public final String addedDate;

    @SerializedName("added_user_id")
    public final String addedUserId;

    @SerializedName("updated_date")
    public final String updatedDate;

    @SerializedName("updated_user_id")
    public final String updatedUserId;

    @SerializedName("updated_flag")
    public final String updatedFlag;

    @SerializedName("touch_count")
    public final String touchCount;

    @SerializedName("favourite_count")
    public final String favouriteCount;

    @SerializedName("plate_number")
    public final String plateNumber;

    @SerializedName("engine_power")
    public final String enginePower;

    @SerializedName("steering_position")
    public final String steeringPosition;

    @SerializedName("no_of_owner")
    public final String noOfOwner;

    @SerializedName("trim_name")
    public final String trimName;

    @SerializedName("vehicle_id")
    public final String vehicleId;

    @SerializedName("price_type")
    public final String priceType;

    @SerializedName("price_unit")
    public final String priceUnit;

    @SerializedName("year")
    public final String year;

    @SerializedName("licence_status")
    public final String licenceStatus;

    @SerializedName("max_passengers")
    public final String maxPassengers;

    @SerializedName("no_of_doors")
    public final String noOfDoors;

    @SerializedName("mileage")
    public final String mileage;

    @SerializedName("license_expiration_date")
    public final String licenseExpirationDate;

    @SerializedName("added_date_str")
    public final String addedDateStr;

    @SerializedName("photo_count")
    public final String photoCount;

    @Embedded(prefix = "default_photo_")
    @SerializedName("default_photo")
    public Image defaultPhoto;

    @Embedded(prefix = "manufacturer_")
    @SerializedName("manufacturer")
    public final Manufacturer manufacturer;

    @Embedded(prefix = "model_")
    @SerializedName("model")
    public final Model model;

    @Embedded(prefix = "item_type_")
    @SerializedName("item_type")
    public final ItemType itemType;

    @Embedded(prefix = "item_price_type_")
    @SerializedName("item_price_type")
    public final ItemPriceType itemPriceType;

    @Embedded(prefix = "item_currency_")
    @SerializedName("item_currency")
    public final ItemCurrency itemCurrency;

    @Embedded(prefix = "item_location_")
    @SerializedName("item_location")
    public final ItemLocation itemLocation;

    @Embedded(prefix = "condition_of_item_")
    @SerializedName("condition_of_item")
    public final ItemCondition itemCondition;

    @Embedded(prefix = "color_")
    @SerializedName("color")
    public final Color color;

    @Embedded(prefix = "fuel_type_")
    @SerializedName("fuel_type")
    public final FuelType fuelType;

    @Embedded(prefix = "build_type_")
    @SerializedName("build_type")
    public final BuildType buildType;

    @Embedded(prefix = "seller_type_")
    @SerializedName("seller_type")
    public final SellerType sellerType;

    @Embedded(prefix = "transmission_")
    @SerializedName("transmission")
    public final Transmission transmission;

    @Embedded(prefix = "user_")
    @SerializedName("user")
    public final User user;

    @SerializedName("is_owner")
    public final String isOwner;

    @SerializedName("is_favourited")
    public final String isFavourited;

    public Item(@NonNull String id, String itemTypeId, String itemPriceTypeId, String itemCurrencyId, String itemLocationId, String conditionOfItem, String colorId, String fuelTypeId, String buildTypeId,
                String modelId, String manufacutrerId, String sellerTypeId, String transmissionId, String description, String highlightInfo, String price, String businessMode, String isSoldOut,
                String title, String address, String lat, String lng, String status, String addedDate, String addedUserId, String updatedDate, String updatedUserId, String updatedFlag,
                String touchCount, String favouriteCount, String plateNumber, String enginePower, String steeringPosition, String noOfOwner, String trimName, String vehicleId, String priceType,
                String priceUnit, String year, String licenceStatus, String maxPassengers, String noOfDoors, String mileage, String licenseExpirationDate, String addedDateStr, String photoCount,
                Image defaultPhoto, Manufacturer manufacturer, Model model, ItemType itemType, ItemPriceType itemPriceType, ItemCurrency itemCurrency, ItemLocation itemLocation,
                ItemCondition itemCondition, Color color, FuelType fuelType, BuildType buildType, SellerType sellerType, Transmission transmission, User user, String isOwner, String isFavourited) {
        this.id = id;
        this.itemTypeId = itemTypeId;
        this.itemPriceTypeId = itemPriceTypeId;
        this.itemCurrencyId = itemCurrencyId;
        this.itemLocationId = itemLocationId;
        this.conditionOfItem = conditionOfItem;
        this.colorId = colorId;
        this.fuelTypeId = fuelTypeId;
        this.buildTypeId = buildTypeId;
        this.modelId = modelId;
        this.manufacutrerId = manufacutrerId;
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
        this.status = status;
        this.addedDate = addedDate;
        this.addedUserId = addedUserId;
        this.updatedDate = updatedDate;
        this.updatedUserId = updatedUserId;
        this.updatedFlag = updatedFlag;
        this.touchCount = touchCount;
        this.favouriteCount = favouriteCount;
        this.plateNumber = plateNumber;
        this.enginePower = enginePower;
        this.steeringPosition = steeringPosition;
        this.noOfOwner = noOfOwner;
        this.trimName = trimName;
        this.vehicleId = vehicleId;
        this.priceType = priceType;
        this.priceUnit = priceUnit;
        this.year = year;
        this.licenceStatus = licenceStatus;
        this.maxPassengers = maxPassengers;
        this.noOfDoors = noOfDoors;
        this.mileage = mileage;
        this.licenseExpirationDate = licenseExpirationDate;
        this.addedDateStr = addedDateStr;
        this.photoCount = photoCount;
        this.defaultPhoto = defaultPhoto;
        this.manufacturer = manufacturer;
        this.model = model;
        this.itemType = itemType;
        this.itemPriceType = itemPriceType;
        this.itemCurrency = itemCurrency;
        this.itemLocation = itemLocation;
        this.itemCondition = itemCondition;
        this.color = color;
        this.fuelType = fuelType;
        this.buildType = buildType;
        this.sellerType = sellerType;
        this.transmission = transmission;
        this.user = user;
        this.isOwner = isOwner;
        this.isFavourited = isFavourited;
    }
}
