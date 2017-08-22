package com.shengd.chat.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by da on 8/20/17.
 */
public class ServerBuffer { //severbuffer for online users

    public static ServerSocket serverSocket;

    public static Map<Integer, ObjectInputStream> userInputMap;
    public static Map<Integer, ObjectOutputStream> userOutputMap;
    public static Set<Integer> userIDs;

    static {
        userInputMap = new ConcurrentSkipListMap<Integer,ObjectInputStream>();
        userOutputMap = new ConcurrentSkipListMap<Integer, ObjectOutputStream>();
        userIDs = new ConcurrentSkipListSet<Integer>();
    }
}
