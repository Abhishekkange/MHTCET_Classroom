package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class studentLeaderBoardModel {

    String profilePhoto;
    int xp;
    String name;
    String userId;

    public studentLeaderBoardModel() {
    }

    public studentLeaderBoardModel(String profilePhoto, int xp, String name, String userId) {
        this.profilePhoto = profilePhoto;
        this.xp = xp;
        this.name = name;
        this.userId = userId;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
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
