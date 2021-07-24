package com.example.server;

import com.example.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServerModel {
    private static ServerModel instance = null;

    public static ServerModel getInstance(){
        if(instance == null){
            instance = new ServerModel();
        }
        return instance;
    }

    private Map<String, ArrayList<Message>> userMessages = new HashMap<>();

    private ServerModel(){

    }

    public void addMessage(String user, Message message){
        ArrayList<Message> messages;
        for(String x : userMessages.keySet()){
            if(!x.equals(user)){
                messages = userMessages.get(x);
                messages.add(message);
                userMessages.put(user, messages);
            }
        }

    }

    public ArrayList<Message> getMessages(String user){
        if(userMessages.get(user) == null){
            ArrayList<Message> messageArrayList = new ArrayList<Message>();
            messageArrayList.add(new Message("System", new java.util.Date().toString(), "This is the start of your notifications"));
            userMessages.put(user, messageArrayList);
        }

        return userMessages.get(user);
    }

}
