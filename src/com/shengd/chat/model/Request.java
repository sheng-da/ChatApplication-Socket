package com.shengd.chat.model;

import com.shengd.chat.model.MessageType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by da on 8/18/17.
 */
public class Request implements Serializable{
    private static final long serialVersionUID = 1L;

    private MessageType type;
    private String message;
    private Date sendTime;
    private int userID = -1;

    public Request(MessageType type, String message){
        this.type = type;
        this. message = message;
        sendTime = new Date();
    }

    public Request(MessageType type, String message, int userID){
        this.type = type;
        this.message = message;
        this.userID = userID;
        sendTime = new Date();
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public MessageType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public int getUserID() {
        return  userID;
    }

}
