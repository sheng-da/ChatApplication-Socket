package com.shengd.chat.server;

import com.shengd.chat.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
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
                    Request request = (Request)objectInputStream.readObject();

                    if (request.getType() == RequestType.TEXT) { // DUPLICATE CODE FOR REQUEST AND RESPONSE
                        Response response = new Response();
                        response.setType(ResponseType.TEXT);
                        response.addContent("msg",request.getContent("msg"));
                        broadcast(response);
                    }
                    else if (request.getType() == RequestType.LOGIN) {

                        String username = (String) request.getContent("username");
                        String password = (String) request.getContent("password");

                        UserService userService = new UserService();
                        List<User> users = userService.loadAllUsers();
                        User user = userService.login(username,password);

                        // not exist
                        if (user == null) {
                            Response response = new Response();
                            response.setStatus(ResponseStatus.FAIL);
                            objectOutputStream.writeObject(response);
                        } else { // found
                            Response response = new Response();
                            response.setStatus(ResponseStatus.SUCCESS);
                            response.addContent("usr",user);
                            objectOutputStream.writeObject(response);
                        }



//                        User user = (User)request.getContent("usr");
//
//                        ServerBuffer.userOutputMap.put(user.getId(),objectOutputStream);
//                        ServerBuffer.userInputMap.put(user.getId(),objectInputStream);
//                        ServerBuffer.userIDs.add(user.getId());
//
//                        System.out.println("Add client: Id "+ user.getId());
//
//                        // create response
//                        Response response= new Response();
//                        response.setType(ResponseType.LOGIN);
//                        response.addContent("usr", user);
//                        broadcast(response);

                    } else if (request.getType() == RequestType.LOGOUT) {
                        User user = (User)request.getContent("usr");

                        ServerBuffer.userIDs.remove(user.getId());
                        ServerBuffer.userInputMap.remove(user.getId());
                        ServerBuffer.userOutputMap.remove(user.getId());

                        // create response
                        Response response= new Response();
                        response.setType(ResponseType.LOGIN);
                        response.addContent("usr", user);
                        broadcast(response);


                        System.out.println("Remove client: Id " + user.getId());
                        break;
                    } else if (request.getType() == RequestType.REGISTER) {
                        UserService userService = new UserService();
                        String username = (String) request.getContent("username");
                        String password = (String) request.getContent("password");

                        // too slow......in checking if dupilicate username...might use map instead...
                        // if username is unique....
                        List<User> users = userService.loadAllUsers();
                        if (users != null) {
                            for (User user: users) {
                                if (username.equals(user.getUsername())) { //duplicate username
                                    Response response = new Response();
                                    response.setStatus(ResponseStatus.FAIL);
                                    objectOutputStream.writeObject(response);
                                    break;
                                }
                            }
                        }

                        //success
                        User user = new User(username,password);
                        userService.addUser(user);

                        // send response to notify the client
                        Response response = new Response();
                        response.addContent("usr",user);
                        response.setStatus(ResponseStatus.SUCCESS);
                        objectOutputStream.writeObject(response);
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void broadcast(Response response) {
            try {
                for (Map.Entry<Integer, ObjectOutputStream> entry: ServerBuffer.userOutputMap.entrySet()) {
                    entry.getValue().writeObject(response); // broadcast
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
    }

