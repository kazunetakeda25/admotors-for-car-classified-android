package com.panaceasoft.admotors.viewobject;


import com.google.gson.annotations.SerializedName;

public class RatingDetail {

    @SerializedName("five_star_count")
    public final int fiveStarCount;

    @SerializedName("five_star_percent")
    public final float fiveStarPercent;

    @SerializedName("four_star_count")
    public final int fourStarCount;

    @SerializedName("four_star_percent")
    public final float fourStarPercent;

    @SerializedName("three_star_count")
    public final int threeStarCount;

    @SerializedName("three_star_percent")
    public final float threeStarPercent;

    @SerializedName("two_star_count")
    public final int twoStarCount;

    @SerializedName("two_star_percent")
    public final float twoStarPercent;

    @SerializedName("one_star_count")
    public final int oneStarCount;

    @SerializedName("one_star_percent")
    public final float oneStarPercent;

    @SerializedName("total_rating_count")
    public final int totalRatingCount;

    @SerializedName("total_rating_value")
    public final float totalRatingValue;

    public RatingDetail(int fiveStarCount, float fiveStarPercent, int fourStarCount, float fourStarPercent, int threeStarCount, float threeStarPercent, int twoStarCount, float twoStarPercent, int oneStarCount, float oneStarPercent, int totalRatingCount, float totalRatingValue) {
        this.fiveStarCount = fiveStarCount;
        this.fiveStarPercent = fiveStarPercent;
        this.fourStarCount = fourStarCount;
        this.fourStarPercent = fourStarPercent;
        this.threeStarCount = threeStarCount;
        this.threeStarPercent = threeStarPercent;
        this.twoStarCount = twoStarCount;
        this.twoStarPercent = twoStarPercent;
        this.oneStarCount = oneStarCount;
        this.oneStarPercent = oneStarPercent;
        this.totalRatingCount = totalRatingCount;
        this.totalRatingValue = totalRatingValue;
    }
}
