package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class ItemCategory {

    public int sorting;

    @NonNull
    @SerializedName("cat_id")
    public String id;

    @SerializedName("cat_name")
    public String name;

    @SerializedName("cat_ordering")
    public String ordering;

    @SerializedName("status")
    public String status;

    @SerializedName("added_date")
    public String addedDate;

    @SerializedName("updated_date")
    public String updatedDate;

    @SerializedName("added_user_id")
    public String addedUserId;

    @SerializedName("city_id")
    public String cityId;

    @SerializedName("updated_user_id")
    public String updatedUserId;

    @SerializedName("updated_flag")
    public String updatedFlag;

    @SerializedName("added_date_str")
    public String addedDateStr;

    @Embedded(prefix = "default_photo_")
    @SerializedName("default_photo")
    public Image defaultPhoto;

    @Embedded(prefix = "default_icon_")
    @SerializedName("default_icon")
    public Image defaultIcon;

    public ItemCategory(int sorting,@NonNull String id, String name, String ordering, String status, String addedDate, String updatedDate, String addedUserId, String cityId, String updatedUserId, String updatedFlag, String addedDateStr, Image defaultPhoto, Image defaultIcon) {

        this.sorting = sorting;
        this.id = id;
        this.name = name;
        this.ordering = ordering;
        this.status = status;
        this.addedDate = addedDate;
        this.updatedDate = updatedDate;
        this.addedUserId = addedUserId;
        this.cityId = cityId;
        this.updatedUserId = updatedUserId;
        this.updatedFlag = updatedFlag;
        this.addedDateStr = addedDateStr;
        this.defaultPhoto = defaultPhoto;
        this.defaultIcon = defaultIcon;
    }
}
