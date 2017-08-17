package com.shengd.chat.server.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by da on 8/16/17.
 */
public class ServerUI extends JFrame{
    private static final long serialVersionUID = 1L; // TODO: ??

    public ServerUI() {
        this.setTitle("Client Server");
        this.setSize(400,200); // hard coded right now
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel label = new JLabel("Port:" + "9999"); // hard coded for now
        panel.add(label);
        JButton currentUserBtn = new JButton("Online User");
        JButton RegUserBtn = new JButton("Reg User");
        JButton exitBtn = new JButton("Exit");
        panel.add(currentUserBtn);
        panel.add(RegUserBtn);
        panel.add(exitBtn);

        this.add(panel,BorderLayout.NORTH);


        JPanel panel2 = new JPanel();

//        JTable table = new JTable();
//        DefaultTableModel  tableModel= (DefaultTableModel) table.getModel();
//
//        tableModel.addColumn("1");
//        tableModel.addColumn("2");
//        tableModel.addColumn("3");
//
//        JScrollPane spTable = new JScrollPane(table);
//

        //should use Jtable to display info
        // use Jtext to test right now
        JTextArea info  = new JTextArea(10,30);
        panel2.add(info);


        this.add(panel2, BorderLayout.CENTER);


        this.setVisible(true);



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
            }
        });


        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }





}
