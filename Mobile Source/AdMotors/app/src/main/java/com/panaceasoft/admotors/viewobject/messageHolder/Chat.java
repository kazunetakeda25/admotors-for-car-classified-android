package com.panaceasoft.admotors.viewobject.messageHolder;

public class Chat {

    String sender_id, receiver_id, itemId;

    public Chat(String sender_id, String receiver_id, String itemId) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.itemId = itemId;
    }

    public Chat() {
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
