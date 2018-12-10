package com.example.alexa.teammbafinalproject;

import java.util.Date;

public class ChatMessage {
    String message;
    String source;
    String chatUID;
    Date sentTime;

    public ChatMessage(String message, String source, String chatUID) {
        this.message = message;
        this.source = source;
        this.chatUID = chatUID;
        this.sentTime = new Date();
    }


    public ChatMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChatUID() {
        return chatUID;
    }

    public void setChatUID(String chatUID) {
        this.chatUID = chatUID;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }
}
