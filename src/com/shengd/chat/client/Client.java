package com.shengd.chat.client;

import com.shengd.chat.model.Request;
import com.shengd.chat.model.MessageType;

import java.io.*;
import java.net.Socket;

/**
 * Created by da on 8/16/17.
 */
public class Client {
//    private ObjectInputStream objectInputStream;
//    private ObjectOutputStream objectOutputStream;
    private Socket socket;
    private ChatUI ui;


    public static void main(String[] args) {
        Client client = new Client();
        client.start();

    }


    //connectServer
    public Client(){
        try {
            socket = new Socket("127.0.0.1",9999);
            ClientBuffer.objectOutputStream =  new ObjectOutputStream(socket.getOutputStream());
            ClientBuffer.objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connect to server.");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean start() {

//        try {
//            //must in this order to avoid blocke
//            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//            objectOutputStream.flush();
//            objectInputStream = new ObjectInputStream(socket.getInputStream());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("I/O loaded");

        new ResponseHandler().start();

        ui = new ChatUI(this); // pass the socket in UI for now



        return true;
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

    class ResponseHandler extends Thread {
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
                if (msg.getType() == MessageType.TEXT)  {// hard coded for now
                    displayMessage(msg.getUserID() + "(" + msg.getSendTime() + "): " + msg.getMessage() + "\n");
                } else if (msg.getType() == MessageType.LOGIN) {
                    displayMessage("User " + msg.getUserID() + " Connected\n");
                    if ( ClientBuffer.UserId == -1) { //NOT SAFE TODO:....
                        ClientBuffer.UserId = msg.getUserID();
                    }
                } else if (msg.getType() == MessageType.LOGOUT) {
                    displayMessage("User " + msg.getUserID() + " Disconnected\n");
                }
            }
        }
    }


}
