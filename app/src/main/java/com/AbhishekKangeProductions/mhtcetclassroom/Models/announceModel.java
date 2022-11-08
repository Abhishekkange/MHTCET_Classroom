package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class announceModel {

    String date, title,image,id;

    public announceModel() {
    }

    public announceModel(String date, String title, String image, String id) {
        this.date = date;
        this.title = title;
        this.image = image;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
