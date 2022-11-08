package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class samplepapermodel {

    String samplePaper,url,image,oUrl;

    public samplepapermodel() {
    }

    public samplepapermodel(String samplePaper, String url, String image, String oUrl) {
        this.samplePaper = samplePaper;
        this.url = url;
        this.image = image;
        this.oUrl = oUrl;
    }

    public String getSamplePaper() {
        return samplePaper;
    }

    public void setSamplePaper(String samplePaper) {
        this.samplePaper = samplePaper;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getoUrl() {
        return oUrl;
    }

    public void setoUrl(String oUrl) {
        this.oUrl = oUrl;
    }
}
