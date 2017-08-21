package com.shengd.chat.server;

import com.shengd.chat.model.Request;
import com.shengd.chat.model.RequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

/**
 * Created by da on 8/20/17.
 */
public class RequestHandler extends Thread {
        // new thread listen to client connection request

        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        Socket socket;
    //    int id;

        public RequestHandler(Socket socket) {
            this.socket = socket;
      //      id = Server.userID++;
        }
        public void run() {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.flush();



                while(true) {
                    Request msg = (Request)objectInputStream.readObject();
                  //  msg.setUserID(id); // set user Id here....TODO:

                    if (msg.getType() == RequestType.TEXT) { //hard coded for now
                        broadcast(msg);
                    }
                    else if (msg.getType() == RequestType.LOGIN) {
                        //  allUsers.add(this);

                        ServerBuffer.userOutputMap.put(msg.getFromUser().getId(),objectOutputStream);
                        ServerBuffer.userInputMap.put(msg.getFromUser().getId(),objectInputStream);
                        ServerBuffer.userIDs.add(msg.getFromUser().getId());

                        System.out.println("Add client.");
                        // test
                        broadcast(msg);

                    } else if (msg.getType() == RequestType.LOGOUT) {
                        broadcast(msg);

                        ServerBuffer.userIDs.remove(msg.getFromUser().getId());
                        ServerBuffer.userInputMap.remove(msg.getFromUser().getId());
                        ServerBuffer.userOutputMap.remove(msg.getFromUser().getId());

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

