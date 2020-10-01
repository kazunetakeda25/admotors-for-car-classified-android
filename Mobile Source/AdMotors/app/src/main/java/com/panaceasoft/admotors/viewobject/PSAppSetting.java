package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class PSAppSetting {

    @NonNull
    public String id;

    @SerializedName("lat")
    public final String appSettingLat;

    @SerializedName("lng")
    public final String appSettingLng;

    public PSAppSetting(@NonNull String id, String appSettingLat, String appSettingLng) {
        this.id = id;
        this.appSettingLat = appSettingLat;
        this.appSettingLng = appSettingLng;
    }
}
