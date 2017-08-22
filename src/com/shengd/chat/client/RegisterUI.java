package com.shengd.chat.client;

import com.shengd.chat.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by da on 8/20/17.
 */
public class RegisterUI { // not extending JFrame until needed

        static JFrame registerWindow = new JFrame("Register");
        static JPanel jPanel1 = new JPanel(),jPanel2 = new JPanel(),jPanel3= new JPanel(),jPanel4 = new JPanel();
        static JLabel passwordLabel = new JLabel("Password"),usernameLabel = new JLabel("Username"), emptyLabel = new JLabel("                  ");
        static JButton submitBtn = new JButton("submit"),exitBtn= new JButton("exit");
        static JTextField username = new JTextField(10), password1 = new JPasswordField(10),password2 = new JPasswordField(10);


        public RegisterUI() {

            jPanel1.add(usernameLabel);
            jPanel1.add(username);

            jPanel2.add(passwordLabel);
            jPanel2.add(password1);

            jPanel3.add(emptyLabel);
            jPanel3.add(password2);

            jPanel4.add(submitBtn);
            jPanel4.add(exitBtn);

            registerWindow.setLayout(new GridLayout(4,1));

            registerWindow.add(jPanel1);
            registerWindow.add(jPanel2);
            registerWindow.add(jPanel3);
            registerWindow.add(jPanel4);
            registerWindow.pack();
            registerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            registerWindow.setSize(400,150);
            registerWindow.setVisible(true);


            submitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    register();
                }
            });


            exitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });

        }

        public static void main(String[] args) {
            new RegisterUI();
        }

        public void register() {

            if (!password1.getText().equals(password2.getText())) {
                JOptionPane.showMessageDialog(registerWindow,"Two password should be the same", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Response response = null;
            Request request = new Request();
            request.setType(RequestType.REGISTER);
            request.addContent("username",username.getText());
            request.addContent("password",password1.getText());

            try {
                ClientBuffer.objectOutputStream.writeObject(request);
                try {
                    response = (Response) ClientBuffer.objectInputStream.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (response.getStatus() == ResponseStatus.FAIL) {
                JOptionPane.showMessageDialog(registerWindow,"Register Fail", "Server Error", JOptionPane.ERROR_MESSAGE);
            } else if (response.getStatus() == ResponseStatus.SUCCESS) {
                User user = (User) response.getContent("user");
                JOptionPane.showMessageDialog(registerWindow,"Success!\n" + "Your username: "
                        + user.getUsername() +"\nYour password: " + user.getPassword(),
                        "Register Success", JOptionPane.INFORMATION_MESSAGE);

                registerWindow.dispose();
            }
        }
}
