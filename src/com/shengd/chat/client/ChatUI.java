package com.shengd.chat.client;

import com.shengd.chat.model.Request;
import com.shengd.chat.model.RequestType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
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


    public ChatUI() {


        chatArea.setEditable(false);
        textArea.setEditable(true);
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
        chatWindow.setSize(1000,800);
        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            ClientBuffer.objectOutputStream.writeObject(new Request(RequestType.LOGIN,"Login",ClientBuffer.user));
        } catch (IOException e) {
            e.printStackTrace();
        }


        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ClientBuffer.objectOutputStream.writeObject(new Request(RequestType.TEXT,textArea.getText().toString(),ClientBuffer.user));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textArea.setText("");
            }
        });

        chatWindow.addWindowListener(new WindowAdapter() {


            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    ClientBuffer.objectOutputStream.writeObject(new Request(RequestType.LOGOUT,"Logout",ClientBuffer.user));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });

        new ResponseHandler(this).start();

    }

    void append(String str) {
        chatArea.append(str);
        //chatArea.setCaretPosition(chatArea.getText().length()-1);
    }


}
