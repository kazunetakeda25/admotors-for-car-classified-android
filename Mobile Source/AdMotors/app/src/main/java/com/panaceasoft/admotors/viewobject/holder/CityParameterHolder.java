package com.panaceasoft.admotors.viewobject.holder;

import com.panaceasoft.admotors.utils.Constants;

import java.io.Serializable;

public class CityParameterHolder implements Serializable {

    public String id, keyword, isFeatured, orderBy,orderType;

    public CityParameterHolder() {
        this.id = "";
        this.keyword = "";
        this.isFeatured = "";
        this.orderBy = "";
        this.orderType = "";
    }

    public CityParameterHolder getPopularCities()
    {
        this.id = "";
        this.keyword = "";
        this.isFeatured = "";
        this.orderBy = Constants.FILTERING_TRENDING;
        this.orderType = Constants.FILTERING_DESC;

        return this;
    }

    public CityParameterHolder getFeaturedCities()
    {
        this.id = "";
        this.keyword = "";
        this.isFeatured = Constants.ONE;
        this.orderBy = Constants.FILTERING_FEATURE;
        this.orderType = Constants.FILTERING_DESC;

        return this;
    }

    public CityParameterHolder getRecentCities()
    {
        this.id = "";
        this.keyword = "";
        this.isFeatured = "";
        this.orderBy = Constants.FILTERING_ADDED_DATE;
        this.orderType = Constants.FILTERING_DESC;

        return this;
    }

    public String getCityMapKey(){

        String result = "";

        if (!keyword.isEmpty()) {
            result += keyword + ":";
        }

        if (!isFeatured.isEmpty()) {
            result += isFeatured;
        }

        if (!orderBy.isEmpty()) {
            result += orderBy;
        }

        if (!orderType.isEmpty()) {
            result += orderType;
        }

        return result;

    }
}
