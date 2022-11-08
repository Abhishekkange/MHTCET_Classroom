package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class collegeReviewPublicModel {

    String userName,comment;

    public collegeReviewPublicModel() {
    }

    public collegeReviewPublicModel(String userName, String comment) {
        this.userName = userName;
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
