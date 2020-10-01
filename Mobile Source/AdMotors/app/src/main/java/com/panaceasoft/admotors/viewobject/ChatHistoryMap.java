package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

/**
 * Created by Panacea-Soft on 2019-06-25.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Entity(primaryKeys = "id")
public class ChatHistoryMap {

    @NonNull
    public final String id;

    public final String mapKey;

    public final String chatHistoryId;

    public final int sorting;

    public final String addedDate;

    public ChatHistoryMap(@NonNull String id, String mapKey, String chatHistoryId, int sorting, String addedDate) {
        this.id = id;
        this.mapKey = mapKey;
        this.chatHistoryId = chatHistoryId;
        this.sorting = sorting;
        this.addedDate = addedDate;
    }

}
