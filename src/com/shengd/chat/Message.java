package com.shengd.chat;

import com.shengd.chat.server.MessageType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by da on 8/18/17.
 */
public class Message implements Serializable{
    private static final long serialVersionUID = 1L;

    private MessageType type;
    private String message;
    private Date sendTime;

    public Message(MessageType type, String message){
        this.type = type;
        this. message = message;
        sendTime = new Date();
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

}
