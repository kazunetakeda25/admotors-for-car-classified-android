package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = "id")
public class ItemHistory {
    @NonNull
    public final String id;

    public  String historyName;

    public String historyUrl;

    public String historyDate;

    public ItemHistory(@NonNull String id, String historyName, String historyUrl, String historyDate) {
        this.id = id;
        this.historyName = historyName;
        this.historyUrl = historyUrl;
        this.historyDate = historyDate;
    }
}
