package com.shengd.chat.client;

import com.shengd.chat.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.DataBuffer;
import java.io.IOException;
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

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new RegisterUI();
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


        Response response = null;
        Request request = new Request();
        request.setType(RequestType.LOGIN);
        request.addContent("username",username.getText());
        request.addContent("password",password.getText());

        try {
            ClientBuffer.objectOutputStream.writeObject(request);
            try {

                // BUG: CAN NOT LOG IN AFTER REGISTRATION
                response = (Response) ClientBuffer.objectInputStream.readObject(); //BUG HERE: RESPONSE == NULL???? WHY
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.getStatus() == ResponseStatus.FAIL) {
            JOptionPane.showMessageDialog(loginWindow,"Username not found/password incorrect", "Login Fail", JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() == ResponseStatus.SUCCESS) {  //SHOULD AVOID REPEATED LOGIN
            User user = (User) response.getContent("usr");
            ClientBuffer.user = user;
            loginWindow.dispose();
            new ChatUI();
        }
    }
}
