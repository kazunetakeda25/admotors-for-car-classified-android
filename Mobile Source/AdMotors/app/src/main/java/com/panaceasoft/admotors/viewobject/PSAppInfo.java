package com.panaceasoft.admotors.viewobject;


import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(primaryKeys = "id")
public class PSAppInfo {

    @NonNull
    public String id;

    @Embedded(prefix = "version_")
    @SerializedName("version")
    public PSAppVersion psAppVersion;

    @SerializedName("app_setting")
    @Embedded(prefix = "app_setting_")
    public PSAppSetting psAppSetting;

    @SerializedName("delete_history")
    @Ignore
    public List<DeletedObject> deletedObjects;

    public PSAppInfo(@NonNull String id, PSAppVersion psAppVersion, PSAppSetting psAppSetting) {
        this.id = id;
        this.psAppVersion = psAppVersion;
        this.psAppSetting = psAppSetting;
    }
}
