package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class MessagesResult {
    public List<Message> messages = new ArrayList<>();

    public MessagesResult(List<Message> messages) {
        this.messages = messages;
    }
}
