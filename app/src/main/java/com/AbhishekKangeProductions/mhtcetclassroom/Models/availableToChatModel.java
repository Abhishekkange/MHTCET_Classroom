package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class availableToChatModel {

    String profilePhoto;
    String name;
    String userId;


    public availableToChatModel() {
    }

    public availableToChatModel(String profilePhoto, String name, String userId) {
        this.profilePhoto = profilePhoto;
        this.name = name;
        this.userId = userId;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
