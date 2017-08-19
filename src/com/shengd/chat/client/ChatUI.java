package com.shengd.chat.client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by da on 8/16/17.
 */
public class ChatUI { // all using static structure for now
    static JFrame chatWindow = new JFrame("Chat Room");
    static JTextArea chatArea = new JTextArea(40,40);
    static JTextArea textArea = new JTextArea(5,40);
    static JLabel function = new JLabel("Future function...");
    static JLabel curUser = new JLabel("Current User");
    static JLabel blank = new JLabel("              ");
    static JButton sendBtn = new JButton("Send");
    static JPanel status = new JPanel();
    static JPanel chat = new JPanel();


    static BufferedReader in;
    static PrintWriter out;

    Socket socket;

    public ChatUI(Socket socket) {
        this.socket = socket;

        //chat.setLayout(new GridLayout(4,1));
        chat.add(chatArea);
        chat.add(function);
        chat.add(textArea);
        chat.add(sendBtn);

        status.setLayout(new BorderLayout());
        status.add(curUser, BorderLayout.NORTH);
        status.add(new JTextArea(30,5));
        status.setSize(30,5);
        status.add(blank,BorderLayout.SOUTH);

        chatWindow.setLayout(new GridLayout(1,2));
        chatWindow.add(status);
        chatWindow.add(chat);
        chatWindow.setVisible(true);
        chatWindow.setSize(500,800);
        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatWindow.out
            }
        });

    }

    public static void main(String[] args) {

    }






}
