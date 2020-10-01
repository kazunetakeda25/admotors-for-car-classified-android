package com.panaceasoft.admotors.viewobject;

import com.google.gson.annotations.SerializedName;
import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

@Entity(primaryKeys = "id")
public class City {

    @NonNull
    @SerializedName("id")
    public String id;

    @SerializedName("shipping_id")
    public String shippingId;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("phone")
    public String phone;

    @SerializedName("email")
    public String email;

    @SerializedName("address")
    public String address;

    @SerializedName("coordinate")
    public String coordinate;

    @SerializedName("lat")
    public String lat;

    @SerializedName("lng")
    public String lng;

    @SerializedName("cod_email")
    public String codEmail;

    @SerializedName("sender_email")
    public String senderEmail;

    @SerializedName("added_date")
    public String addedDate;

    @SerializedName("status")
    public String status;

    @SerializedName("is_featured")
    public String isFeatured;

    @SerializedName("terms")
    public String terms;

    @SerializedName("featured_date")
    public String featuredDate;

    @SerializedName("added_user_id")
    public String addedUserId;

    @SerializedName("updated_date")
    public String updatedDate;

    @SerializedName("updated_user_id")
    public String updatedUserId;

    @SerializedName("added_date_str")
    public String addedDateStr;

    @Embedded(prefix = "default_photo_")
    @SerializedName("default_photo")
    public Image defaultPhoto;

    @SerializedName("touch_count")
    public String touchCount;

    public City(String id, String shippingId, String name, String description, String phone, String email, String address, String coordinate, String lat, String lng, String codEmail, String senderEmail, String addedDate, String status, String isFeatured, String terms, String featuredDate, String addedUserId, String updatedDate, String updatedUserId, String addedDateStr, Image defaultPhoto, String touchCount) {
        this.id = id;
        this.shippingId = shippingId;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.coordinate = coordinate;
        this.lat = lat;
        this.lng = lng;
        this.codEmail = codEmail;
        this.senderEmail = senderEmail;
        this.addedDate = addedDate;
        this.status = status;
        this.isFeatured = isFeatured;
        this.terms = terms;
        this.featuredDate = featuredDate;
        this.addedUserId = addedUserId;
        this.updatedDate = updatedDate;
        this.updatedUserId = updatedUserId;
        this.addedDateStr = addedDateStr;
        this.defaultPhoto = defaultPhoto;
        this.touchCount = touchCount;
    }

}
