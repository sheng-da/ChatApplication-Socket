package com.shengd.chat.server.ui;

import com.shengd.chat.model.User;
import com.shengd.chat.server.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by da on 8/16/17.
 */
public class ServerUI { // not extending JFrame untill needed
    static JFrame serverWindow = new JFrame("Client Server");
    static JPanel panel = new JPanel();
    static JLabel label =  new JLabel("Port:" + "9999"); // hard coded for now
    static JButton currentUserBtn = new JButton("Online User");
    static JButton RegUserBtn = new JButton("Reg User");
    static JButton exitBtn = new JButton("Exit");
    static JPanel panel2 = new JPanel();
    static JTextArea info  = new JTextArea(10,30);

    public ServerUI() {

        serverWindow.setSize(400,200); // hard coded right now
        serverWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.add(currentUserBtn);
        panel.add(RegUserBtn);
        panel.add(exitBtn);

        serverWindow.add(panel, BorderLayout.NORTH);
        panel2.add(info);
        serverWindow.add(panel2, BorderLayout.CENTER);
        serverWindow.setVisible(true);

        currentUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                info.setText(null);
                info.append("current users info");


            }
        });

        RegUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                info.setText(null);
                info.append("registerd users info");
                // test purpose only
                info.append("\n" + displayRegUser());
            }
        });


        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    /**
     * Test purpose only
     */
    public String  displayRegUser() {
        String res = "";
        UserService userService = new UserService();
        List<User> usersList = userService.loadAllUsers();
        if (usersList == null) return res;
        for (User usr: usersList) {
            String line = "Name: " + usr.getUsername() + " Password: " + usr.getPassword() + "\n"
                    + "ID: " + usr.getId() + "\n";
            res += line;
        }
        return res;
    }





}
