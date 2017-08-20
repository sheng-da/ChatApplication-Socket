package com.shengd.chat.client;

import com.shengd.chat.Message;
import com.shengd.chat.server.MessageType;

import java.io.*;
import java.net.Socket;

/**
 * Created by da on 8/16/17.
 */
public class Client {
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
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
            System.out.println("Connect to server.");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean start() {

        try {
            //must in this order to avoid blocke
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("I/O loaded");

        new ServerListener().start();

        ui = new ChatUI(socket,this); // pass the socket in UI for now



        return true;
    }


    public void displayMessage(String message){
        ui.append(message);
    }

    public void sendMessage(Message msg) {
        try {
            objectOutputStream.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ServerListener extends Thread {
        @Override
        public void run() {
            while(true) {
                Message msg = null;
                try {
                    msg = (Message)objectInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (msg.getType() == MessageType.TEXT)  {// hard coded for now
                    displayMessage(msg.getSendTime() + ": " + msg.getMessage() + "\n");
                } else if (msg.getType() == MessageType.LOGIN) {
                    displayMessage("User Connected\n");
                } else if (msg.getType() == MessageType.LOGOUT) {
                    displayMessage("User Disconnected\n");
                }
            }
        }
    }


}
