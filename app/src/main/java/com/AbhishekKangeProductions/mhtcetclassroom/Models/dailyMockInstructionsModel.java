package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class dailyMockInstructionsModel {

    String image,url;
    String next;
    String question;
    String tag;

    public dailyMockInstructionsModel() {
    }

    public dailyMockInstructionsModel(String image, String url, String next, String question, String tag) {
        this.image = image;
        this.url = url;
        this.next = next;
        this.question = question;
        this.tag = tag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
