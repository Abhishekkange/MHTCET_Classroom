package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class collegeReviewModel {

   String image,name,rating;

    public collegeReviewModel() {
    }

    public collegeReviewModel(String image, String name, String rating) {
        this.image = image;
        this.name = name;
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
