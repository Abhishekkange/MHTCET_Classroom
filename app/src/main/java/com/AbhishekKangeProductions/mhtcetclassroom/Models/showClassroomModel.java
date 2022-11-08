package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class showClassroomModel {

    String classroomName,classroomDate,classroomTime,classroomHost;

    public showClassroomModel() {
    }

    public showClassroomModel(String classroomName, String classroomDate, String classroomTime, String classroomHost) {
        this.classroomName = classroomName;
        this.classroomDate = classroomDate;
        this.classroomTime = classroomTime;
        this.classroomHost = classroomHost;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getClassroomDate() {
        return classroomDate;
    }

    public void setClassroomDate(String classroomDate) {
        this.classroomDate = classroomDate;
    }

    public String getClassroomTime() {
        return classroomTime;
    }

    public void setClassroomTime(String classroomTime) {
        this.classroomTime = classroomTime;
    }

    public String getClassroomHost() {
        return classroomHost;
    }

    public void setClassroomHost(String classroomHost) {
        this.classroomHost = classroomHost;
    }
}

