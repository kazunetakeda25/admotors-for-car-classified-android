package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = "aboutId")
public class AboutUs {

    @SerializedName("about_id")
    @NonNull
    public final String aboutId;

    @SerializedName("about_title")
    public final String aboutTitle;

    @SerializedName("about_description")
    public final String aboutDescription;

    @SerializedName("about_email")
    public final String aboutEmail;

    @SerializedName("about_phone")
    public final String aboutPhone;

    @SerializedName("ads_on")
    public final String adsOn;

    @SerializedName("safety_tips")
    public final String safetyTips;

    @SerializedName("ads_client")
    public final String adsClient;

    @SerializedName("ads_slot")
    public final String adsSlot;

    @SerializedName("analyt_on")
    public final String analytOn;

    @SerializedName("analyt_track_id")
    public final String analytTrackId;

    @SerializedName("about_website")
    public final String aboutWebsite;

    @SerializedName("facebook")
    public final String facebook;

    @SerializedName("google_plus")
    public final String googlePlus;

    @SerializedName("instagram")
    public final String instagram;

    @SerializedName("youtube")
    public final String youtube;

    @SerializedName("pinterest")
    public final String pinterest;

    @SerializedName("twitter")
    public final String twitter;

    @SerializedName("privacypolicy")
    public final String privacyPolicy;

    @SerializedName("GDPR")
    public final String gdpr;

    @SerializedName("upload_point")
    public final String uploadPoint;

    @Embedded
    @SerializedName("default_photo")
    public final Image defaultPhoto;

    public AboutUs(@NonNull String aboutId, String aboutTitle, String aboutDescription, String aboutEmail, String aboutPhone, String adsOn, String safetyTips, String adsClient, String adsSlot, String analytOn, String analytTrackId, String aboutWebsite, String facebook, String googlePlus, String instagram, String youtube, String pinterest, String twitter, String privacyPolicy, Image defaultPhoto, String gdpr, String uploadPoint) {
        this.aboutId = aboutId;
        this.aboutTitle = aboutTitle;
        this.aboutDescription = aboutDescription;
        this.aboutEmail = aboutEmail;
        this.aboutPhone = aboutPhone;
        this.adsOn = adsOn;
        this.safetyTips = safetyTips;
        this.adsClient = adsClient;
        this.adsSlot = adsSlot;
        this.analytOn = analytOn;
        this.analytTrackId = analytTrackId;
        this.aboutWebsite = aboutWebsite;
        this.facebook = facebook;
        this.googlePlus = googlePlus;
        this.instagram = instagram;
        this.youtube = youtube;
        this.pinterest = pinterest;
        this.twitter = twitter;
        this.privacyPolicy = privacyPolicy;
        this.defaultPhoto = defaultPhoto;
        this.gdpr = gdpr;
        this.uploadPoint = uploadPoint;
    }
}

