package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class dateModel {

    String date;
    String url,oUrl;
    String image;
    boolean bookmark;

    public dateModel(){

    }

    public dateModel(String date, String url, String oUrl, String image, boolean bookmark) {
        this.date = date;
        this.url = url;
        this.oUrl = oUrl;
        this.image = image;
        this.bookmark = bookmark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getoUrl() {
        return oUrl;
    }

    public void setoUrl(String oUrl) {
        this.oUrl = oUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }
}
