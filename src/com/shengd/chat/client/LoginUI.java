package com.shengd.chat.client;

import com.shengd.chat.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by da on 8/16/17.
 */
public class LoginUI { // not extending JFrame until needed

    static JFrame loginWindow = new JFrame("Login/Register");
    static JPanel jPanel1 = new JPanel(),jPanel2 = new JPanel(),jPanel3= new JPanel();
    static JLabel passwordLabel = new JLabel("Password"),usernameLabel = new JLabel("Username");
    static JButton loginBtn = new JButton("login"),exitBtn= new JButton("exit"),registerBtn= new JButton("register");
    static JTextField username = new JTextField(10), password = new JPasswordField(10);


    public LoginUI() {

        jPanel1.add(usernameLabel);
        jPanel1.add(username);

        jPanel2.add(passwordLabel);
        jPanel2.add(password);

        jPanel3.add(loginBtn);
        jPanel3.add(registerBtn);
        jPanel3.add(exitBtn);

        loginWindow.setLayout(new GridLayout(3,1));

        loginWindow.add(jPanel1);
        loginWindow.add(jPanel2);
        loginWindow.add(jPanel3);
        loginWindow.pack();
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        loginWindow.setSize(300,100);
        loginWindow.setVisible(true);


        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                login();
            }
        });


        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });




    }

    private void login() {

        // TODO: identity verification

        if (username.getText() == null) return;
        if (password.getText() == null) return;

        // I suppose we get the user object from the server.
        // these part are hard coded for now..
        Random rand = new Random();
        User test = new User(rand.nextInt(),username.getText(),password.getText());

        ClientBuffer.user = test;
        loginWindow.dispose();
        new ChatUI();



    }
}
