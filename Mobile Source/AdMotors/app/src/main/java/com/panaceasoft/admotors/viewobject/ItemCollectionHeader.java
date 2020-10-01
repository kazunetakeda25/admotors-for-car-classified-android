package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(primaryKeys = "id")
public class ItemCollectionHeader {

    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("city_id")
    public String cityId;

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

    @SerializedName("updated_user_id")
    public String updatedUserId;

    @SerializedName("updated_flag")
    public String updatedFlag;

    @SerializedName("added_date_str")
    public String addedDateStr;

    @Embedded(prefix = "default_photo_")
    @SerializedName("default_photo")
    public Image defaultPhoto;

    @Ignore
    @SerializedName("items")
    public List<Item> itemList;

    public ItemCollectionHeader(@NonNull  String id, String cityId, String name, String status, String addedDate, String addedUserId, String updatedDate, String updatedUserId, String updatedFlag, String addedDateStr, Image defaultPhoto) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.status = status;
        this.addedDate = addedDate;
        this.addedUserId = addedUserId;
        this.updatedDate = updatedDate;
        this.updatedUserId = updatedUserId;
        this.updatedFlag = updatedFlag;
        this.addedDateStr = addedDateStr;
        this.defaultPhoto = defaultPhoto;
    }

}
