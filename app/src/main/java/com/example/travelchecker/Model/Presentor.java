package com.example.travelchecker.Model;

import com.example.model.Message;

import java.util.ArrayList;

public class Presentor {

    Data data;
    public Presentor(){
        data = Data.instance();
    }

    public String getUsername() {
        return data.getUsername();
    }

    public void setUsername(String username) {
        data.setUsername(username);
    }

    public Integer getHomeRadius() {
        return data.getHomeRadius();
    }

    public void setHomeRadius(Integer homeRadius) {
        data.setHomeRadius(homeRadius);
    }

    public Integer getWorkRadius() {
        return data.getWorkRadius();
    }

    public void setWorkRadius(Integer workRadius) {
        data.setWorkRadius(workRadius);
    }

    public Integer getSchoolRadius() {
        return data.getSchoolRadius();
    }

    public void setSchoolRadius(Integer schoolRadius) {
        data.setSchoolRadius(schoolRadius);
    }

    public Double getHomeLat() {
        return data.getHomeLat();
    }

    public void setHomeLat(Double homeLat) {
        data.setHomeLat(homeLat);
    }

    public Double getHomeLong() {
        return data.getHomeLong();
    }

    public void setHomeLong(Double homeLong) {
        data.setHomeLong(homeLong);
    }

    public Double getWorkLat() {
        return data.getWorkLat();
    }

    public void setWorkLat(Double workLat) {
        data.setWorkLat(workLat);
    }

    public Double getWorkLong() {
        return data.getWorkLong();
    }

    public void setWorkLong(Double workLong) {
        data.setWorkLong(workLong);
    }

    public Double getSchoolLat() {
        return data.getSchoolLat();
    }

    public void setSchoolLat(Double schoolLat) {
        data.setSchoolLat(schoolLat);
    }

    public Double getSchoolLong() {
        return data.getSchoolLong();
    }

    public void setSchoolLong(Double schoolLong) {
        data.setSchoolLong(schoolLong);
    }

    public boolean isSettingHome() {
        return data.isSettingHome();
    }

    public void setSettingHome(boolean settingHome) {
        data.setSettingHome(settingHome);
    }

    public boolean isSettingWork() {
        return data.isSettingWork();
    }

    public void setSettingWork(boolean settingWork) {
        data.setSettingWork(settingWork);
    }

    public boolean isSettingSchool() {
        return data.isSettingSchool();
    }

    public void setSettingSchool(boolean settingSchool) {
        data.setSettingSchool(settingSchool);
    }

    public boolean isInHome() {
        return data.isInHome();
    }

    public void setInHome(boolean inHome) {
        data.setInHome(inHome);
    }

    public boolean isInWork() {
        return data.isInWork();
    }

    public void setInWork(boolean inWork) {
        data.setInWork(inWork);
    }

    public boolean isInSchool() {
        return data.isInSchool();
    }

    public void setInSchool(boolean inSchool) {
        data.setInSchool(inSchool);
    }

    public ArrayList<Message> getMessages(){
        return data.getMessages();
    }

    public void addMessage(String time, String message){
        data.addMessage(new Message(data.getUsername(), time, message));
    }
}
