package com.shengd.chat.server;

import com.shengd.chat.model.Request;
import com.shengd.chat.model.MessageType;
import com.shengd.chat.server.ui.ServerUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by da on 8/16/17.
 */
public class Server {

    //static ArrayList<RequestHandler> allUsers = new ArrayList<RequestHandler>();

    //static ServerSocket serverSocket = null;

    static int userID = 0;

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



    // can create a new java.class
    class RequestHandler extends Thread {
        // new thread listen to client connection request

        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        Socket socket;
        int id;

        public RequestHandler(Socket socket) {
            this.socket = socket;
            id = Server.userID++;
        }
        public void run() {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.flush();



                while(true) {
                    Request msg = (Request)objectInputStream.readObject();
                    msg.setUserID(id); // set user Id here....TODO:

                    if (msg.getType() == MessageType.TEXT) { //hard coded for now
                        broadcast(msg);
                    }
                    else if (msg.getType() == MessageType.LOGIN) {
                       //  allUsers.add(this);

                         ServerBuffer.userOutputMap.put(id,objectOutputStream);
                         ServerBuffer.userInputMap.put(id,objectInputStream);
                         ServerBuffer.userIDs.add(id);

                         System.out.println("Add client.");







                        // test
                        broadcast(msg);

                    } else if (msg.getType() == MessageType.LOGOUT) {
                        broadcast(msg);

                        ServerBuffer.userIDs.remove(id);
                        ServerBuffer.userInputMap.remove(id);
                        ServerBuffer.userOutputMap.remove(id);

//
//                        allUsers.remove(this);
                        System.out.println("Remove client.");
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void broadcast(Request msg) {
            try {
                for (Map.Entry<Integer, ObjectOutputStream> entry: ServerBuffer.userOutputMap.entrySet()) {
                    entry.getValue().writeObject(msg); // broadcast
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
    }



}



