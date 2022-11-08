package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class postIModel {

    String postImage,profilePhoto,userName,userId;

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public postIModel(String postImage, String profilePhoto, String userName, String userId) {
        this.postImage = postImage;
        this.profilePhoto = profilePhoto;
        this.userName = userName;
        this.userId = userId;
    }

    public postIModel() {
    }
}
