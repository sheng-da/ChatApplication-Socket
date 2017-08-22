package com.shengd.chat.server;

import com.shengd.chat.model.User;

import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.shengd.chat.client.ClientBuffer.objectInputStream;

/**
 * Created by da on 8/20/17.
 */
public class UserService { //save/user data to local database
    // provide method for I/O // static local variable for id count;

    private static int id = 0;

    public List<User> loadAllUsers() {
        List<User> userList = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream("/home/da/src/ChatRoomApplication-Socket/src/com/shengd/chat/server/UserInfo.txt"));
         //   if (objectInputStream.available() > 0) // if there is userinfo to read
                userList = (List<User>) objectInputStream.readObject();
                if (userList != null) id = userList.size();
                id = userList.size();
        } catch (EOFException eof){
            //this is fine

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close the objectinput...
//            try {
//                objectInputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
       return userList;
    }

    public void saveAllUsers(List<User> users){
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream("/home/da/src/ChatRoomApplication-Socket/src/com/shengd/chat/server/UserInfo.txt"));
            objectOutputStream.writeObject(users);
            objectOutputStream.flush(); // flush before close
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            try {
//             //   objectOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            // close the objectinput...
        }
    }


    public void addUser(User user){
        user.setId(++id);
        List<User> users = loadAllUsers();
        if (users == null) users = new CopyOnWriteArrayList<User>();
        users.add(user);
        saveAllUsers(users);
    }

    public User login(String username, String password) {
        User user = null;
        List<User> users = loadAllUsers();
        for (User u: users) {
            if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                user = u;
                break;
            }
        }
        return user;
    }

}
