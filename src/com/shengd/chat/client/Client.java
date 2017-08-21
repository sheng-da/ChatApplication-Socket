package com.shengd.chat.client;

import com.shengd.chat.model.Request;
import com.shengd.chat.model.RequestType;

import java.io.*;
import java.net.Socket;

/**
 * Created by da on 8/16/17.
 */
public class Client {

    private Socket socket;
    // private LoginUI ui;


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

        System.out.println("I/O loaded");

       // ui = new ChatUI(this); // pass the socket in UI for now
        new LoginUI();


        return true;
    }


}
