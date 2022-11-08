package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class userCredentials {

    String email,password,whichClass,name,profilePhoto,userId,coins,xp,mobileNo;

    public userCredentials() {
    }

    public userCredentials(String email, String password, String whichClass, String name, String profilePhoto, String userId, String coins, String xp, String mobileNo) {
        this.email = email;
        this.password = password;
        this.whichClass = whichClass;
        this.name = name;
        this.profilePhoto = profilePhoto;
        this.userId = userId;
        this.coins = coins;
        this.xp = xp;
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWhichClass() {
        return whichClass;
    }

    public void setWhichClass(String whichClass) {
        this.whichClass = whichClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getXp() {
        return xp;
    }

    public void setXp(String xp) {
        this.xp = xp;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
