package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class doubtDiscussionModel {

    String question,questionImage,userName,userProfile;

    public doubtDiscussionModel() {
    }

    public doubtDiscussionModel(String question, String questionImage, String userName, String userProfile) {
        this.question = question;
        this.questionImage = questionImage;
        this.userName = userName;
        this.userProfile = userProfile;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }


}
