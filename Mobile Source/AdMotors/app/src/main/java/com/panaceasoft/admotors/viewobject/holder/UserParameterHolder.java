package com.panaceasoft.admotors.viewobject.holder;

import java.io.Serializable;

public class UserParameterHolder implements Serializable {

    public String user_name,overall_rating, return_types, login_user_id, otherUserId;

    public UserParameterHolder() {
        this.user_name = "";
        this.overall_rating = "";
        this.return_types = "";
        this.login_user_id = "";
        this.otherUserId = "";

    }

    public UserParameterHolder getFollowingUsers()
    {
        this.user_name = "";
        this.overall_rating = "";
        this.return_types = "following";
        this.login_user_id = "";
        this.otherUserId = "";

        return this;
    }

    public UserParameterHolder getFollowerUsers()
    {
        this.user_name = "";
        this.overall_rating = "";
        this.return_types = "follower";
        this.login_user_id = "";
        this.otherUserId = "";

        return this;
    }

    public String getUserMapKey(){

        String result = "";

        if (!user_name.isEmpty()) {
            result += user_name + ":";
        }

        if (!overall_rating.isEmpty()) {
            result += overall_rating + ";";
        }

        if (!return_types.isEmpty()) {
            result += return_types + ":";
        }

        if (!login_user_id.isEmpty()) {
            result += login_user_id + ":";
        }

        if (!otherUserId.isEmpty()) {
            result += otherUserId + ":";
        }

        return result;

    }

}
