package com.shengd.chat.server;

import com.shengd.chat.model.Request;
import com.shengd.chat.model.RequestType;
import com.shengd.chat.server.ui.ServerUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Created by da on 8/16/17.
 */
public class Server {

    //static ArrayList<RequestHandler> allUsers = new ArrayList<RequestHandler>();

    //static ServerSocket serverSocket = null;

 //   static int userID = 0;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        System.out.println("Waiting for clients...");
        int port = 9999; // HARD CODED FOR NOW  // TODO: use config

        try {
            ServerBuffer.serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ServerUI();

        while (true) {
            try {
                Socket socket = ServerBuffer.serverSocket.accept();
                System.out.println("Connection established");
                RequestHandler handler = new RequestHandler(socket);
                handler.start();


                // conversation handler
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }







}



