package com.shengd.chat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by da on 8/18/17.
 */
public class Message implements Serializable{

    private int type;
    private String message;
    private Date sendTime;

    public Message(int type, String message){
        this.type = type;
        this. message = message;
        sendTime = new Date();
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Date getSendTime() {
        return sendTime;
    }

}
