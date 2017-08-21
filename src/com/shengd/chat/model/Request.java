package com.shengd.chat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by da on 8/18/17.
 */
public class Request implements Serializable{
    private static final long serialVersionUID = 1L;



    private RequestType type;
    private String message;
    private Date sendTime;
    private User fromUser;

    public Request(RequestType type, String message){
        this.type = type;
        this.message = message;
        sendTime = new Date();
    }

    public Request(RequestType type, String message, User user){
        this.type = type;
        this.message = message;
        fromUser = user;
        sendTime = new Date();
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

}
