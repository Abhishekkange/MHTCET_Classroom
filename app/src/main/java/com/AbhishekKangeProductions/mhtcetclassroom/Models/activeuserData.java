package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class activeuserData {

    String userName;
    String time;

    public activeuserData() {
    }

    public activeuserData(String userName, String time) {

        this.userName = userName;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }
}
