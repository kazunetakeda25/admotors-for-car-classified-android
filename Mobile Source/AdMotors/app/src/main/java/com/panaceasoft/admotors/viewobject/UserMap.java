package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = "id")
public class UserMap {
    @NonNull
    public final String id;

    public final String mapKey;

    public final String userId;

    public final int sorting;

    public final String addedDate;

    public UserMap(@NonNull String id, String mapKey, String userId, int sorting, String addedDate) {
        this.id = id;
        this.mapKey = mapKey;
        this.userId = userId;
        this.sorting = sorting;
        this.addedDate = addedDate;
    }

}
