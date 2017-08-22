package com.shengd.chat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by da on 8/21/17.
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private String content;
    private Date sendTime;
    private User fromUser;
    private User toUser;

    public Message(String content, User fromUser) {
        this.content = content;
        this.fromUser = fromUser;
        sendTime = new Date();
        toUser = null;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
}
