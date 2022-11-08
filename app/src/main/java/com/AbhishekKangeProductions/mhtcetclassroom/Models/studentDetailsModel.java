package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class studentDetailsModel {

    String userName,userPhotoDisplay, userId , userEmail, studentName , studentClass;

    public studentDetailsModel() {
    }

    public studentDetailsModel(String userName, String userPhotoDisplay, String userId, String userEmail, String studentName, String studentClass) {
        this.userName = userName;
        this.userPhotoDisplay = userPhotoDisplay;
        this.userId = userId;
        this.userEmail = userEmail;
        this.studentName = studentName;
        this.studentClass = studentClass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhotoDisplay() {
        return userPhotoDisplay;
    }

    public void setUserPhotoDisplay(String userPhotoDisplay) {
        this.userPhotoDisplay = userPhotoDisplay;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
}
