package com.shengd.chat.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            // if success
            // pop message

            // send request to server // back to loginUI

            // if not success
            // pop message

        }
}
