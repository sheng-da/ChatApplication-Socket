package com.shengd.chat.client.ui;

import javax.swing.*;

/**
 * Created by da on 8/16/17.
 */
public class LoginUI extends JFrame{
    public LoginUI() {
        JPanel jpanel = new JPanel();
        JTextField userName = new JTextField();
        JLabel userNameLabel = new JLabel("UserName");
        jpanel.add(userNameLabel);
        jpanel.add(userName);

        this.add(jpanel);


        this.setVisible(true);

    }
}
