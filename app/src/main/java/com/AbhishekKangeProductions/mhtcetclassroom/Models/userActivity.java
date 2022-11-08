package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class userActivity {

    String username,time,activity;

    public userActivity() {
    }

    public userActivity(String username, String time, String activity) {
        this.username = username;
        this.time = time;
        this.activity = activity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }


}
