package com.panaceasoft.admotors.viewobject.messageHolder;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;
import com.panaceasoft.admotors.Config;

import java.util.Date;

@Entity(primaryKeys = {"id"})
public class Message {

    public Long addedDate;

    @NonNull
    public String id;

    public String sessionId;

    public String itemId;

    public String message;

    public int type;

    public String sendByUserId;

    public int offerStatus;

    public boolean isSold;

    @Ignore
    public Date date;

    @Ignore
    public String dateString;

    @Ignore
    public String time;

    public java.util.Map<String, String> getAddedDate() {
        return ServerValue.TIMESTAMP;
    }

    @Exclude
    public Long getAddedDateLong() {
        return addedDate;
    }

    public void setAddedDate(Long addedDate) {
        this.addedDate = addedDate;
    }

    public Message(String sessionId, String itemId, String message, int type, String sendByUserId, int offerStatus, boolean isSold) {
        this.sessionId = sessionId;
        this.itemId = itemId;
        this.message = message;
        this.type = type;
        this.sendByUserId = sendByUserId;
        this.offerStatus = offerStatus;
        this.isSold = isSold;
    }

    @Ignore
    public Message() {

    }

    @Ignore
    @Exclude
    public String getPriceOnly() {
        String price = "0";

        String [] data = this.message.split(" ");

        if(data != null && data.length > 1) {
            if(Config.SYMBOL_SHOW_FRONT){
                price = data[1];
            }else{
                price = data[0];
            }

        }

        return price;
    }

//    public String getSendBy_id() {
//        return sendByUserId;
//    }
//
//    public void setSendBy_id(String sendByUserId) {
//        this.sendByUserId = sendByUserId;
//    }
//
//    public String getSendBy_name() {
//        return sendBy_name;
//    }
//
//    public void setSendBy_name(String sendBy_name) {
//        this.sendBy_name = sendBy_name;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getChatId() {
//        return sessionId;
//    }
//
//    public void setChatId(String sessionId) {
//        this.sessionId = sessionId;
//    }
}
