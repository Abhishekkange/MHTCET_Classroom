package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class freeCallModel {

    String name,mobileNo,comment,userId,time;

    public freeCallModel() {
    }

    public freeCallModel(String name, String mobileNo, String comment, String userId, String time) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.comment = comment;
        this.userId = userId;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
