package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 2019-06-25.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Entity(primaryKeys = "id")
public class ChatHistory {

    @NonNull
    @SerializedName("id")
    public final String id;

    @SerializedName("item_id")
    public final String itemId;

    @SerializedName("buyer_user_id")
    public final String buyerUserId;

    @SerializedName("seller_user_id")
    public final String sellerUserId;

    @SerializedName("nego_price")
    public final String negoPrice;

    @SerializedName("buyer_unread_count")
    public final String buyerUnreadCount;

    @SerializedName("seller_unread_count")
    public final String sellerUnreadCount;

    @SerializedName("added_date")
    public final String addedDate;

    @SerializedName("item")
    @Embedded(prefix = "item_")
    public final Item item;

    @SerializedName("buyer")
    @Embedded(prefix = "buyer_")
    public final User buyerUser;

    @SerializedName("seller")
    @Embedded(prefix = "seller_")
    public final User sellerUser;

    @SerializedName("added_date_str")
    public final String addedDateStr;

    @SerializedName("is_offer")
    public final String isOffer;

    @SerializedName("is_accept")
    public final String isAccept;

    @SerializedName("offer_amount")
    public final String offerAmount;

    public ChatHistory(@NonNull String id, String itemId, String buyerUserId, String sellerUserId, String negoPrice, String buyerUnreadCount, String sellerUnreadCount, String addedDate, Item item, User buyerUser, User sellerUser, String addedDateStr, String isOffer, String isAccept, String offerAmount) {
        this.id = id;
        this.itemId = itemId;
        this.buyerUserId = buyerUserId;
        this.sellerUserId = sellerUserId;
        this.negoPrice = negoPrice;
        this.buyerUnreadCount = buyerUnreadCount;
        this.sellerUnreadCount = sellerUnreadCount;
        this.addedDate = addedDate;
        this.item = item;
        this.buyerUser = buyerUser;
        this.sellerUser = sellerUser;
        this.addedDateStr = addedDateStr;
        this.isOffer = isOffer;
        this.isAccept = isAccept;
        this.offerAmount = offerAmount;
    }
}