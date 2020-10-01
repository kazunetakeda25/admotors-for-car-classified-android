package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class ItemCondition {
    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public final String name;

    @SerializedName("status")
    public final String status;

    @SerializedName("added_date")
    public final String addedDate;

    @SerializedName("added_date_str")
    public final String addedDateStr;

    public ItemCondition(@NonNull String id, String name, String status, String addedDate, String addedDateStr) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.addedDate = addedDate;
        this.addedDateStr = addedDateStr;
    }
}
