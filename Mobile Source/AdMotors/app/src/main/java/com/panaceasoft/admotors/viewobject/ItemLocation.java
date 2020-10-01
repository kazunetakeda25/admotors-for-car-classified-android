package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class ItemLocation {

    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public final String name;

    @SerializedName("lat")
    public final String lat;

    @SerializedName("lng")
    public final String lng;

    @SerializedName("status")
    public final String status;

    @SerializedName("added_date")
    public final String addedDate;

    public ItemLocation(@NonNull String id, String name, String lat, String lng, String status, String addedDate) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.status = status;
        this.addedDate = addedDate;
    }
}
