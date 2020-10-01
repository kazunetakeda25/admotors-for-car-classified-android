package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class ItemSubCategory {

    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("status")
    public String status;

    @SerializedName("added_date")
    public String addedDate;

    @SerializedName("added_user_id")
    public String addedUserId;

    @SerializedName("updated_date")
    public String updatedDate;

    @SerializedName("city_id")
    public String cityId;

    @SerializedName("cat_id")
    public String catId;

    @SerializedName("deleted_flag")
    public String deletedFlag;

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

    public ItemSubCategory(String id, String name, String status, String addedDate, String addedUserId, String updatedDate, String cityId, String catId, String deletedFlag, String updatedUserId, String updatedFlag, String addedDateStr, Image defaultPhoto, Image defaultIcon) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.addedDate = addedDate;
        this.addedUserId = addedUserId;
        this.updatedDate = updatedDate;
        this.cityId = cityId;
        this.catId = catId;
        this.deletedFlag = deletedFlag;
        this.updatedUserId = updatedUserId;
        this.updatedFlag = updatedFlag;
        this.addedDateStr = addedDateStr;
        this.defaultPhoto = defaultPhoto;
        this.defaultIcon = defaultIcon;
    }
}
