package com.panaceasoft.admotors.viewobject;


import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = "id")
public class CityMap {

    @NonNull
    public final String id;

    public final String mapKey;

    public final String cityId;

    public final int sorting;

    public final String addedDate;

    public CityMap(@NonNull String id, String mapKey, String cityId, int sorting, String addedDate) {
        this.id = id;
        this.mapKey = mapKey;
        this.cityId = cityId;
        this.sorting = sorting;
        this.addedDate = addedDate;
    }
}
