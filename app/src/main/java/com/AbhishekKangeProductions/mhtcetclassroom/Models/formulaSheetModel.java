package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class formulaSheetModel {

    String chapterName;
    String url;
    String image;

    public formulaSheetModel() {
    }

    public formulaSheetModel(String chapterName, String url, String image) {
        this.chapterName = chapterName;
        this.url = url;
        this.image = image;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
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
}
