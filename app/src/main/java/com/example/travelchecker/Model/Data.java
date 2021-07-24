package com.example.travelchecker.Model;

import com.example.model.Message;

import java.util.ArrayList;

public class Data {

    private static Data _instance;
    public static Data instance() {
        if (_instance == null){
            _instance = new Data();
        }
        return _instance;
    }

    private String username;
    //ensure radiuses are never null, set to 0 by default
    private Integer homeRadius = 15;
    private Integer workRadius = 43;
    private Integer schoolRadius = 204;

    private Double homeLat = 40.232134;
    private Double homeLong = -111.654052;
    private Double workLat = 40.249462;
    private Double workLong = -111.650832;
    private Double schoolLat = 40.246805;
    private Double schoolLong = -111.649226;

    private boolean settingHome = false;
    private boolean settingWork = false;
    private boolean settingSchool = false;

    private boolean inHome = false;
    private boolean inWork = false;
    private boolean inSchool = false;

    private ArrayList<Message> messages;


    private Data(){
        messages = new ArrayList<>();
        messages.add(new Message("System", new java.util.Date().toString(), "This is the start of your notifications"));

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHomeRadius() {
        return homeRadius;
    }

    public void setHomeRadius(Integer homeRadius) {
        this.homeRadius = homeRadius;
    }

    public Integer getWorkRadius() {
        return workRadius;
    }

    public void setWorkRadius(Integer workRadius) {
        this.workRadius = workRadius;
    }

    public Integer getSchoolRadius() {
        return schoolRadius;
    }

    public void setSchoolRadius(Integer schoolRadius) {
        this.schoolRadius = schoolRadius;
    }

    public Double getHomeLat() {
        return homeLat;
    }

    public void setHomeLat(Double homeLat) {
        this.homeLat = homeLat;
    }

    public Double getHomeLong() {
        return homeLong;
    }

    public void setHomeLong(Double homeLong) {
        this.homeLong = homeLong;
    }

    public Double getWorkLat() {
        return workLat;
    }

    public void setWorkLat(Double workLat) {
        this.workLat = workLat;
    }

    public Double getWorkLong() {
        return workLong;
    }

    public void setWorkLong(Double workLong) {
        this.workLong = workLong;
    }

    public Double getSchoolLat() {
        return schoolLat;
    }

    public void setSchoolLat(Double schoolLat) {
        this.schoolLat = schoolLat;
    }

    public Double getSchoolLong() {
        return schoolLong;
    }

    public void setSchoolLong(Double schoolLong) {
        this.schoolLong = schoolLong;
    }

    public boolean isSettingHome() {
        return settingHome;
    }

    public void setSettingHome(boolean settingHome) {
        this.settingHome = settingHome;
    }

    public boolean isSettingWork() {
        return settingWork;
    }

    public void setSettingWork(boolean settingWork) {
        this.settingWork = settingWork;
    }

    public boolean isSettingSchool() {
        return settingSchool;
    }

    public void setSettingSchool(boolean settingSchool) {
        this.settingSchool = settingSchool;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public boolean isInHome() {
        return inHome;
    }

    public void setInHome(boolean inHome) {
        this.inHome = inHome;
    }

    public boolean isInWork() {
        return inWork;
    }

    public void setInWork(boolean inWork) {
        this.inWork = inWork;
    }

    public boolean isInSchool() {
        return inSchool;
    }

    public void setInSchool(boolean inSchool) {
        this.inSchool = inSchool;
    }


}
