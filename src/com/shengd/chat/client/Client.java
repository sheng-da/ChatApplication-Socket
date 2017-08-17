package com.shengd.chat.client;

import com.shengd.chat.client.ui.LoginUI;

import java.net.Socket;

/**
 * Created by da on 8/16/17.
 */
public class Client {
    public static void main(String[] args) {
        connectServer();
    }


    //connectServer
    public static void connectServer(){
        try {
            Socket socket = new Socket("127.0.0.1",9999);
            System.out.println("Connect to server.");
            new LoginUI();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
