package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class ItemFromFollower {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public final int id = 0;

    @NonNull
    @SerializedName("id")
    public final String itemId;

    @SerializedName("user_id")
    public final String userId;

    public int sorting;

    public ItemFromFollower(@NonNull String itemId, String userId, int sorting) {
        this.itemId = itemId;
        this.userId = userId;
        this.sorting = sorting;
    }
}
