package com.example.model;

public class Message {
    private String username;
    private String time;
    private String message;


    public Message(String username, String time, String message) {
        this.username = username;
        this.time = time;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

}
