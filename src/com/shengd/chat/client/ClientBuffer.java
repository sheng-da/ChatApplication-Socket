package com.shengd.chat.client;

import com.shengd.chat.model.User;

import java.awt.image.DataBuffer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by da on 8/20/17.
 */
public class ClientBuffer {
    public static ObjectOutputStream objectOutputStream;
    public static ObjectInputStream objectInputStream;
    public static User user;

    private ClientBuffer(){}
}
