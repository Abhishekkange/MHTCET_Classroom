package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class nftPostModel {

    String profilePhoto,userName,postImage,userId;

    public nftPostModel() {
    }

    public nftPostModel(String profilePhoto, String userName, String postImage, String userId) {
        this.profilePhoto = profilePhoto;
        this.userName = userName;
        this.postImage = postImage;
        this.userId = userId;
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

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getUserId(String uid) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
