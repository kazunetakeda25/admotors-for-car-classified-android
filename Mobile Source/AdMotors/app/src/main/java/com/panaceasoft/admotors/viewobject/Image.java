package com.panaceasoft.admotors.viewobject;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;

/**
 * Created by Panacea-Soft on 11/25/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Entity(primaryKeys = "imgId")
public class Image {

    @SerializedName("img_id")
    @NonNull
    public final String imgId;

    @SerializedName("img_parent_id")
    public final String imgParentId;

    @SerializedName("img_type")
    public final String imgType;

    @SerializedName("img_path")
    public final String imgPath;

    @SerializedName("img_width")
    public final String imgWidth;

    @SerializedName("img_height")
    public final String imgHeight;

    @SerializedName("img_desc")
    public final String imgDesc;

    public Image(@NonNull String imgId, String imgParentId, String imgType, String imgPath, String imgWidth, String imgHeight, String imgDesc) {
        this.imgId = imgId;
        this.imgParentId = imgParentId;
        this.imgType = imgType;
        this.imgPath = imgPath;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.imgDesc = imgDesc;

    }
}
