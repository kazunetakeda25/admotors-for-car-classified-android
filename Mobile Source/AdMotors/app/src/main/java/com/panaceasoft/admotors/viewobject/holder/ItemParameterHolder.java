package com.panaceasoft.admotors.viewobject.holder;

import com.panaceasoft.admotors.utils.Constants;

import java.io.Serializable;

public class ItemParameterHolder implements Serializable {

    public String keyword, manufacturer_id, model_id,type_id,type_name,price_type_id,priceTypeName,currency_id,location_id,condition_id,conditionName,color_id,colorName,fuelType_id,fuelTypeName,buildType_id,
            buildTypeName,sellerTypeId,sellerTypeName,transmissionId,transmissionName, max_price, min_price,lat, lng, mapMiles, order_by, order_type, userId;

    public ItemParameterHolder() {
        this.keyword = "";
        this.manufacturer_id = "";
        this.model_id = "";
        this.type_id = "";
        this.price_type_id = "";
        this.priceTypeName = "";
        this.conditionName = "";
        this.colorName = "";
        this.fuelTypeName = "";
        this.buildTypeName = "";
        this.sellerTypeName = "";
        this.transmissionName = "";
        this.type_name = "";
        this.currency_id = "";
        this.location_id = "";
        this.condition_id = "";
        this.color_id = "";
        this.fuelType_id = "";
        this.buildType_id = "";
        this.sellerTypeId = "";
        this.transmissionId = "";
        this.max_price = "";
        this.min_price = "";
        this.lat = "";
        this.lng = "";
        this.mapMiles = "";
        this.order_by = Constants.FILTERING_ADDED_DATE;
        this.order_type = Constants.FILTERING_DESC;
        this.userId = "";

    }

    public ItemParameterHolder getPopularItem()
    {
        this.keyword = "";
        this.manufacturer_id = "";
        this.model_id = "";
        this.type_id = "";
        this.type_name = "";
        this.price_type_id = "";
        this.priceTypeName = "";
        this.conditionName = "";
        this.colorName = "";
        this.fuelTypeName = "";
        this.buildTypeName = "";
        this.sellerTypeName = "";
        this.transmissionName = "";
        this.currency_id = "";
        this.location_id = "";
        this.condition_id = "";
        this.color_id = "";
        this.fuelType_id = "";
        this.buildType_id = "";
        this.sellerTypeId = "";
        this.transmissionId = "";
        this.max_price = "";
        this.min_price = "";
        this.lat = "";
        this.lng = "";
        this.mapMiles = "";
        this.order_by = Constants.FILTERING_TRENDING;
        this.order_type = Constants.FILTERING_DESC;
        this.userId = "";

        return this;
    }

    public ItemParameterHolder getRecentItem()
    {
        this.keyword = "";
        this.manufacturer_id = "";
        this.model_id = "";
        this.type_id = "";
        this.type_name = "";
        this.price_type_id = "";
        this.priceTypeName = "";
        this.conditionName = "";
        this.colorName = "";
        this.fuelTypeName = "";
        this.buildTypeName = "";
        this.sellerTypeName = "";
        this.transmissionName = "";
        this.currency_id = "";
        this.location_id = "";
        this.condition_id = "";
        this.color_id = "";
        this.fuelType_id = "";
        this.buildType_id = "";
        this.sellerTypeId = "";
        this.transmissionId = "";
        this.max_price = "";
        this.min_price = "";
        this.lat = "";
        this.lng = "";
        this.mapMiles = "";
        this.order_by = Constants.FILTERING_ADDED_DATE;
        this.order_type = Constants.FILTERING_DESC;
        this.userId = "";

        return this;
    }


    public String getItemMapKey(){

        String result = "";

        if (!keyword.isEmpty()) {
            result += keyword + ":";
        }

        if (!manufacturer_id.isEmpty()) {
            result += manufacturer_id + ":";
        }

        if (!model_id.isEmpty()) {
            result += model_id + ":";
        }

        if (!type_id.isEmpty()) {
            result += type_id + ":";
        }

        if (!type_name.isEmpty()) {
            result += type_name + ":";
        }

        if (!price_type_id.isEmpty()) {
            result += price_type_id + ":";
        }

        if (!priceTypeName.isEmpty()) {
            result += priceTypeName + ":";
        }

        if (!conditionName.isEmpty()) {
            result += conditionName + ":";
        }

        if (!colorName.isEmpty()) {
            result += colorName + ":";
        }

        if (!fuelTypeName.isEmpty()) {
            result += fuelTypeName + ":";
        }

        if (!buildTypeName.isEmpty()) {
            result += buildTypeName + ":";
        }

        if (!sellerTypeName.isEmpty()) {
            result += sellerTypeName + ":";
        }

        if (!transmissionName.isEmpty()) {
            result += transmissionName + ":";
        }

        if (!condition_id.isEmpty()) {
            result += condition_id + ":";
        }

        if (!color_id.isEmpty()) {
            result += color_id + ":";
        }

        if (!fuelType_id.isEmpty()) {
            result += fuelType_id + ":";
        }

        if (!buildType_id.isEmpty()) {
            result += buildType_id + ":";
        }

        if (!sellerTypeId.isEmpty()) {
            result += sellerTypeId + ":";
        }

        if (!transmissionId.isEmpty()) {
            result += transmissionId + ":";
        }
        if (!order_by.isEmpty()) {
            result += order_by + ":";
        }

        if (!order_type.isEmpty()) {
            result += order_type + ":";
        }

        if (!max_price.isEmpty()) {
            result += max_price + ":";
        }

        if (!min_price.isEmpty()) {
            result += min_price + ":";
        }

        if(!lat.isEmpty() && !lng.isEmpty() && !mapMiles.isEmpty()){
            result += "";
        }else {
            if (!location_id.isEmpty()) {
                result += location_id + ":";
            }
        }

        if (!lat.isEmpty()) {
            result += lat + ":";
        }

        if (!lng.isEmpty()) {
            result += lng + ":";
        }

        if (!mapMiles.isEmpty()) {
            result += mapMiles + ":";
        }

        if (!userId.isEmpty()) {
            result += userId + ":";
        }
        return result;
    }
}
