package com.panaceasoft.admotors.viewobject.holder;

import com.panaceasoft.admotors.utils.Constants;

public class ManufacturerParameterHolder {

    public String order_by, cityId;

    public ManufacturerParameterHolder() {

        this.order_by = Constants.FILTERING_ADDED_DATE;
        this.cityId = "";

    }

    public ManufacturerParameterHolder getTrendingCategories()
    {
        this.cityId = "";
        this.order_by = Constants.FILTERING_TRENDING;

        return this;
    }

    public String changeToMapValue() {
        String result = "";

        if (!cityId.isEmpty()) {
            result += cityId;
        }

        if (!order_by.isEmpty()) {
            result += order_by;
        }

        return result;
    }
}
