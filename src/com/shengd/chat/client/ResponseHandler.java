package com.shengd.chat.client;

import com.shengd.chat.model.*;

import java.io.IOException;

/**
 * Created by da on 8/20/17.
 */
public class ResponseHandler extends Thread {

    private ChatUI ui;

    public ResponseHandler(ChatUI ui) {
        this.ui = ui;
    }


    public void displayMessage(String message){
        ui.append(message);
    }

    public void sendMessage(Request msg) {
        try {
            ClientBuffer.objectOutputStream.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        @Override
        public void run() {
            while(true) {
                Response response = null;
                try {
                    response = (Response)ClientBuffer.objectInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (response.getType() == ResponseType.TEXT)  {
                    Message msg = (Message) response.getContent("msg");
                    displayMessage(msg.getFromUser().getId()+"[" + msg.getFromUser().getUsername() + "] (" + msg.getSendTime() + "): " + msg.getContent() + "\n");

                }
//
//                else if (response.getType() == ResponseType.LOGIN) { //NOT SAFE TO DELIVER A USER OBJECT TO CLIENT
//                    User user = (User) response.getContent("usr");
//                    displayMessage("User: " + user.getUsername() + " Connected\n");
//                }
//
//
                else if (response.getType() == ResponseType.LOGOUT) {
                    User user = (User) response.getContent("usr");
                    displayMessage("User: " + user.getUsername() + " Disconnected\n");
                }
            }
        }
    }


