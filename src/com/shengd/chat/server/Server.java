package com.shengd.chat.server;

import com.shengd.chat.Message;
import com.shengd.chat.server.ui.ServerUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by da on 8/16/17.
 */
public class Server {

    static ArrayList<RequestHandler> allUsers = new ArrayList<RequestHandler>();

    static ServerSocket serverSocket = null;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        System.out.println("Waiting for clients...");
        int port = 9999; // HARD CODED FOR NOW  // TODO: use config

        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new ServerUI();

        while (true) {
            try {
                Socket socket = serverSocket.accept();
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

        public RequestHandler(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.flush();



                while(true) {
                    Message msg = (Message)objectInputStream.readObject();

                    if (msg.getType() == MessageType.TEXT) { //hard coded for now
                        broadcast(msg);
                    }
                    else if (msg.getType() == MessageType.LOGIN) {
                         allUsers.add(this);
                         System.out.println("Add client.");
                        // test
                        broadcast(msg);

                    } else if (msg.getType() == MessageType.LOGOUT) {
                        broadcast(msg);
                        allUsers.remove(this);
                        System.out.println("Remove client.");
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void broadcast(Message msg) {
            try {
                for (RequestHandler t : Server.allUsers) {
                    t.objectOutputStream.writeObject(msg); // broadcast
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
    }



}



