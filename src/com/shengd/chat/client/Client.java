package com.shengd.chat.client;

import com.shengd.chat.Message;

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
        new Client();
    }


    //connectServer
    public Client(){
        try {
            Socket socket = new Socket("127.0.0.1",9999);
            System.out.println("Connect to server.");

            ui = new ChatUI(socket,this); // pass the socket in UI for now

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e){
            e.printStackTrace();
        }

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
                if (msg.getType() == 0)  {// hard coded for now
                    ui.append(msg.getSendTime() + ": " + msg.getMessage());
                }
            }
        }
    }


}
