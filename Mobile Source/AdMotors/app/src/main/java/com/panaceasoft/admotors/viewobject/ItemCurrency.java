package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "id")
public class ItemCurrency {

    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("currency_short_form")
    public final String currencyShortForm;

    @SerializedName("currency_symbol")
    public final String currencySymbol;

    @SerializedName("status")
    public final String status;

    @SerializedName("added_date")
    public final String addedDate;

    public final String price;

    public ItemCurrency(@NonNull String id, String currencyShortForm, String currencySymbol, String status, String addedDate,  String price) {
        this.id = id;
        this.currencyShortForm = currencyShortForm;
        this.currencySymbol = currencySymbol;
        this.status = status;
        this.addedDate = addedDate;
        this.price = price;
    }
}
