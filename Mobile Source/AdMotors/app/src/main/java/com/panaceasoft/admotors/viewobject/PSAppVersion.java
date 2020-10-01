package com.panaceasoft.admotors.viewobject;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = "versionNo")
public class PSAppVersion {

    @NonNull
    @SerializedName("version_no")
    public String versionNo;

    @SerializedName("version_force_update")
    public String versionForceUpdate;

    @SerializedName("version_title")
    public String versionTitle;

    @SerializedName("version_message")
    public String versionMessage;

    @SerializedName("version_need_clear_data")
    public String versionNeedClearData;

    public PSAppVersion(@NonNull String versionNo, String versionForceUpdate, String versionTitle, String versionMessage, String versionNeedClearData) {
        this.versionNo = versionNo;
        this.versionForceUpdate = versionForceUpdate;
        this.versionTitle = versionTitle;
        this.versionMessage = versionMessage;
        this.versionNeedClearData = versionNeedClearData;
    }
}
