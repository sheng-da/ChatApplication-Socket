package com.shengd.chat.server;

import com.shengd.chat.server.handler.RequestHandler;
import com.shengd.chat.server.ui.ServerUI;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by da on 8/16/17.
 */
public class Server {

    static ArrayList<Thread> allUsers = new ArrayList<Thread>();

    static ServerSocket serverSocket = null;

    public static void main(String[] args) {


        System.out.println("Waiting for clients...");
        int port = 9999; // HARD CODED FOR NOW  // TODO: use config


        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // new thread listen to client connection request
        new Thread(new Runnable(){
            public void run() {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        System.out.println("Connection established");
                        Thread user = new Thread(new RequestHandler(socket));
                        allUsers.add(user);
                        user.start();
                        // conversation handler
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new ServerUI();
    }
}


