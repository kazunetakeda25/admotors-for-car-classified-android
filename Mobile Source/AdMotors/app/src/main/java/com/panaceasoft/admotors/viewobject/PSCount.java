package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 2019-08-28.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Entity(primaryKeys = "id")
public class PSCount {

    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("blog_noti_unread_count")
    public String blogNotiUnreadCount;

    @SerializedName("buyer_unread_count")
    public String buyerUnreadCount;

    @SerializedName("seller_unread_count")
    public String sellerUnreadCount;

}
