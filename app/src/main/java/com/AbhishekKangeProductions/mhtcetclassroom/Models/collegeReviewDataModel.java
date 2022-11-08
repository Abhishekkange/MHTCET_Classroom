package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class collegeReviewDataModel {

    String closingRank, fees, avgPackage, ranking,image,location,description,campusSize,infrastructurerating,facultyRating,overallRating;

    public collegeReviewDataModel() {
    }

    public collegeReviewDataModel(String closingRank, String fees, String avgPackage, String ranking, String image, String location, String description, String campusSize, String infrastructurerating, String facultyRating, String overallRating) {
        this.closingRank = closingRank;
        this.fees = fees;
        this.avgPackage = avgPackage;
        this.ranking = ranking;
        this.image = image;
        this.location = location;
        this.description = description;
        this.campusSize = campusSize;
        this.infrastructurerating = infrastructurerating;
        this.facultyRating = facultyRating;
        this.overallRating = overallRating;
    }

    public String getClosingRank() {
        return closingRank;
    }

    public void setClosingRank(String closingRank) {
        this.closingRank = closingRank;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getAvgPackage() {
        return avgPackage;
    }

    public void setAvgPackage(String avgPackage) {
        this.avgPackage = avgPackage;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCampusSize() {
        return campusSize;
    }

    public void setCampusSize(String campusSize) {
        this.campusSize = campusSize;
    }

    public String getInfrastructurerating() {
        return infrastructurerating;
    }

    public void setInfrastructurerating(String infrastructurerating) {
        this.infrastructurerating = infrastructurerating;
    }

    public String getFacultyRating() {
        return facultyRating;
    }

    public void setFacultyRating(String facultyRating) {
        this.facultyRating = facultyRating;
    }

    public String getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(String overallRating) {
        this.overallRating = overallRating;
    }



}