package com.shengd.chat.client;

import java.awt.image.DataBuffer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by da on 8/20/17.
 */
public class ClientBuffer {
    public static ObjectOutputStream objectOutputStream;
    public static ObjectInputStream objectInputStream;
    public static int UserId = -1;

    private ClientBuffer(){}
}
