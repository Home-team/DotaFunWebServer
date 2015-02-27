package com.hometeam.bean;

import org.json.simple.JSONObject;

import java.util.Date;

public class Message {
    private int sender;
    private String message;
    private Date date;

    public Message() {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        JSONObject object = new JSONObject();
        object.put("sender", sender);
        object.put("message", message);
        object.put("date", date.toString());
        return object.toJSONString();
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
