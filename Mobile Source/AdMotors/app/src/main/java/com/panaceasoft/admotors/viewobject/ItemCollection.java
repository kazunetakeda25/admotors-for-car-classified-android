package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ItemCollection {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public final int id = 0;

    @NonNull
    public final String collectionId;

    public final String itemId;

    public ItemCollection(@NonNull String collectionId, String itemId) {
        this.collectionId = collectionId;
        this.itemId = itemId;
    }

}
