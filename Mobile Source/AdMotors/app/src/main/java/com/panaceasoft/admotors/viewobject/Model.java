package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class Model {
    @NonNull
    @SerializedName("id")
    public final String id;

    @SerializedName("manufacturer_id")
    public final String manufacturerId;

    @SerializedName("name")
    public final String name;

    @SerializedName("status")
    public final String status;

    @SerializedName("added_date")
    public final String addedDate;

    @SerializedName("added_user_id")
    public final String addedUserId;

    @SerializedName("updated_date")
    public final String updateDate;

    @SerializedName("updated_user_id")
    public final String updatedUserId;

    @SerializedName("updated_flag")
    public final String updatedFlag;

    @Embedded(prefix = "default_photo_")
    @SerializedName("default_photo")
    public Image defaultPhoto;

    @Embedded(prefix = "default_icon_")
    @SerializedName("default_icon")
    public Image defaultIcon;

//    @Embedded(prefix = "manufacturer_")
//    @SerializedName("manufacturer")
//    public final Manufacturer manufacturer;

    public Model(@NonNull String id, String manufacturerId, String name, String status, String addedDate, String addedUserId, String updateDate, String updatedUserId, String updatedFlag , Image defaultPhoto, Image defaultIcon) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.status = status;
        this.addedDate = addedDate;
        this.addedUserId = addedUserId;
        this.updateDate = updateDate;
        this.updatedUserId = updatedUserId;
        this.updatedFlag = updatedFlag;
        this.defaultPhoto = defaultPhoto;
        this.defaultIcon = defaultIcon;
//        this.manufacturer = manufacturer;
    }
}
