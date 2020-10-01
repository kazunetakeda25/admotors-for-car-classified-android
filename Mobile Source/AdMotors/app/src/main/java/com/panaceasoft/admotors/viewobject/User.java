package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Panacea-Soft on 12/6/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Entity(primaryKeys = "userId")
public class User {

    @NonNull
    @SerializedName("user_id")
    public String userId;

    @SerializedName("user_is_sys_admin")
    public String userIsSysAdmin;

    @SerializedName("is_city_admin")
    public String isCityAdmin;

    @SerializedName("facebook_id")
    public String facebookId;

    @SerializedName("phone_id")
    public String phoneId;

    @SerializedName("google_id")
    public String googleId;

    @SerializedName("user_name")
    public String userName;

    @SerializedName("user_email")
    public String userEmail;

    @SerializedName("user_phone")
    public String userPhone;

    @SerializedName("user_address")
    public String userAddress;

    @SerializedName("city")
    public String city;

    @SerializedName("user_password")
    public String userPassword;

    @SerializedName("user_about_me")
    public String userAboutMe;

    @SerializedName("user_cover_photo")
    public String userCoverPhoto;

    @SerializedName("user_profile_photo")
    public String userProfilePhoto;

    @SerializedName("role_id")
    public String roleId;

    @SerializedName("status")
    public String status;

    @SerializedName("is_banned")
    public String isBanned;

    @SerializedName("added_date")
    public String addedDate;

    @SerializedName("device_token")
    public String deviceToken;

    @SerializedName("code")
    public String code;

    @SerializedName("overall_rating")
    public String overallRating;

    @SerializedName("whatsapp")
    public String whatsapp;

    @SerializedName("messenger")
    public String messenger;

    @SerializedName("follower_count")
    public String followerCount;

    @SerializedName("following_count")
    public String followingCount;

    @SerializedName("is_followed")
    public String isFollowed;

    @SerializedName("added_date_str")
    public String added_date_str;

    @SerializedName("verify_types")
    public String verifyTypes;

    @SerializedName("email_verify")
    public String emailVerify;

    @SerializedName("facebook_verify")
    public String facebookVerify;

    @SerializedName("google_verify")
    public String googleVerify;

    @SerializedName("phone_verify")
    public String phoneVerify;

    @SerializedName("rating_count")
    public String ratingCount;

    @SerializedName("rating_details")
    @Embedded(prefix = "rating_details")
    public final RatingDetail ratingDetails;

    @Ignore
    @SerializedName("item")
    public List<Item> itemList;

    public User(@NonNull String userId, String userIsSysAdmin, String isCityAdmin, String facebookId, String phoneId, String googleId, String userName, String userEmail, String userPhone, String userAddress, String city, String userPassword, String userAboutMe, String userCoverPhoto, String userProfilePhoto, String roleId, String status, String isBanned, String addedDate, String deviceToken, String code, String overallRating, String whatsapp, String messenger, String followerCount, String followingCount, String isFollowed, String added_date_str, String verifyTypes, String emailVerify, String facebookVerify, String googleVerify, String phoneVerify, String ratingCount, RatingDetail ratingDetails) {
        this.userId = userId;
        this.userIsSysAdmin = userIsSysAdmin;
        this.isCityAdmin = isCityAdmin;
        this.facebookId = facebookId;
        this.phoneId = phoneId;
        this.googleId = googleId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.city = city;
        this.userPassword = userPassword;
        this.userAboutMe = userAboutMe;
        this.userCoverPhoto = userCoverPhoto;
        this.userProfilePhoto = userProfilePhoto;
        this.roleId = roleId;
        this.status = status;
        this.isBanned = isBanned;
        this.addedDate = addedDate;
        this.deviceToken = deviceToken;
        this.code = code;
        this.overallRating = overallRating;
        this.whatsapp = whatsapp;
        this.messenger = messenger;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.isFollowed = isFollowed;
        this.added_date_str = added_date_str;
        this.verifyTypes = verifyTypes;
        this.emailVerify = emailVerify;
        this.facebookVerify = facebookVerify;
        this.googleVerify = googleVerify;
        this.phoneVerify = phoneVerify;
        this.ratingCount = ratingCount;
        this.ratingDetails = ratingDetails;
    }
}
