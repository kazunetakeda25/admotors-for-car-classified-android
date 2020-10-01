package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class ItemFavourite {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public final int id = 0;

    @NonNull
    @SerializedName("id")
    public final String itemId;

    public int sorting;

    public ItemFavourite(@NonNull String itemId, int sorting) {
        this.itemId = itemId;
        this.sorting = sorting;
    }
}
