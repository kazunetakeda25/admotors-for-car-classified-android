package com.panaceasoft.admotors.viewobject;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(primaryKeys = "id")
public class Noti {

    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("message")
    public String message;

    @SerializedName("description")
    public String description;

    @SerializedName("added_user_id")
    public String addedUserId;

    @SerializedName("added_date")
    public String addedDate;

    @SerializedName("added_date_str")
    public String addedDateStr;

    @SerializedName("is_read")
    public String isRead;

    @Embedded(prefix = "photo_")
    @SerializedName("default_photo")
    public Image defaultPhoto;

    public Noti(@NonNull String id, String message, String description, String addedUserId, String addedDate, String addedDateStr, String isRead, Image defaultPhoto) {
        this.id = id;
        this.message = message;
        this.description = description;
        this.addedUserId = addedUserId;
        this.addedDate = addedDate;
        this.addedDateStr = addedDateStr;
        this.isRead = isRead;
        this.defaultPhoto = defaultPhoto;
    }
}
