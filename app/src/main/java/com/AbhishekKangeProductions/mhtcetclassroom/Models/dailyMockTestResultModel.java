package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class dailyMockTestResultModel {

    String date,score;

    public dailyMockTestResultModel() {
    }

    public dailyMockTestResultModel(String date, String score) {
        this.date = date;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
