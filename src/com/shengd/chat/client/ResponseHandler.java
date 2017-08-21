package com.shengd.chat.client;

import com.shengd.chat.model.Request;
import com.shengd.chat.model.RequestType;

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
                Request msg = null;
                try {
                    msg = (Request)ClientBuffer.objectInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (msg.getType() == RequestType.TEXT)  {// hard coded for now
                    displayMessage(msg.getFromUser().getId()+"[" + msg.getFromUser().getUsername() + "] (" + msg.getSendTime() + "): " + msg.getMessage() + "\n");
                } else if (msg.getType() == RequestType.LOGIN) {
                    displayMessage("User " + msg.getFromUser().getId() + " Connected\n");
                } else if (msg.getType() == RequestType.LOGOUT) {
                    displayMessage("User " + msg.getFromUser().getId() + " Disconnected\n");
                }
            }
        }
    }


