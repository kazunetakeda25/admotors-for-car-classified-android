package com.panaceasoft.admotors.viewobject;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Rating {
    @SerializedName("id")
    @NonNull
    @PrimaryKey
    public final String id;

    @SerializedName("from_user_id")
    public final String fromUserId;

    @SerializedName("to_user_id")
    public final String toUserId;

    @SerializedName("rating")
    public final String rating;

    @SerializedName("title")
    public final String title;

    @SerializedName("description")
    public final String description;

    @SerializedName("added_date")
    public final String addedDate;

    @SerializedName("added_date_str")
    public final String addedDateStr;

    @SerializedName("from_user")
    @Embedded(prefix = "from_user")
    public final User fromUser;

    @SerializedName("to_user")
    @Embedded(prefix = "to_user")
    public final User toUser;

//    @SerializedName("user")
//    @Embedded(prefix = "user")
//    public final User user;

    public Rating(@NonNull String id, String fromUserId, String toUserId, String rating, String title, String description, String addedDate, String addedDateStr,User fromUser,User toUser) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.addedDate = addedDate;
        this.addedDateStr = addedDateStr;
        this.fromUser = fromUser;
        this.toUser = toUser;
//        this.user = user;
    }
}
