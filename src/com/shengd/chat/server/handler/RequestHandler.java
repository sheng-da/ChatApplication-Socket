//package com.shengd.chat.server.handler;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
///**
// * Created by da on 8/16/17.
// */
//public class RequestHandler implements Runnable{
//    Socket socket;
//    BufferedReader in;
//    PrintWriter out;
//    String name;
//
//    public RequestHandler(Socket socket) {
//        this.socket = socket;
//    }
//
//
//    @Override
//    public void run() {
//        boolean flag = true;
//        try{
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            out = new PrintWriter(socket.getOutputStream(),true);
//            while (flag) {
//                String request = in.readLine();
//                // handler request...
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
