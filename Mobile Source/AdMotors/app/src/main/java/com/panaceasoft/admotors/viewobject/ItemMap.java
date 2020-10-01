package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = "id")
public class ItemMap {

    @NonNull
    public final String id;

    public final String mapKey;

    public final String itemId;

    public final int sorting;

    public final String addedDate;

    public ItemMap(@NonNull String id, String mapKey, String itemId, int sorting, String addedDate) {
        this.id = id;
        this.mapKey = mapKey;
        this.itemId = itemId;
        this.sorting = sorting;
        this.addedDate = addedDate;
    }

}
