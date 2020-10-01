package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class ItemSpecs {
    @SerializedName("id")
    @NonNull
    public final String id;

    @SerializedName("item_id")
    public final String itemId;

    @SerializedName("name")
    public final String name;

    @SerializedName("description")
    public final String description;

    @SerializedName("added_date")
    public final String addedDate;

    @SerializedName("added_user_id")
    public final String addedUserId;

    @SerializedName("updated_date")
    public final String updatedDate;

    @SerializedName("updated_user_id")
    public final String updatedUserId;

    @SerializedName("updated_flag")
    public final String updatedFlag;

    @SerializedName("is_empty_object")
    public final String isEmptyObject;

    public ItemSpecs(@NonNull String id, String itemId, String name, String description, String addedDate, String addedUserId, String updatedDate, String updatedUserId, String updatedFlag, String isEmptyObject) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.addedDate = addedDate;
        this.addedUserId = addedUserId;
        this.updatedDate = updatedDate;
        this.updatedUserId = updatedUserId;
        this.updatedFlag = updatedFlag;
        this.isEmptyObject = isEmptyObject;
    }
}
