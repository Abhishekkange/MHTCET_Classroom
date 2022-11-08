package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class sendMessageModel {

    String message;
    String senderId;
    String userId;
    long timestamp;



    public sendMessageModel() {
    }

    public sendMessageModel(String message, String senderId, String userId, long timestamp) {
        this.message = message;
        this.senderId = senderId;
        this.userId = userId;
        this.timestamp = timestamp;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
