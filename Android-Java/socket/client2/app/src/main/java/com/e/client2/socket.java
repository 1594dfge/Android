package com.e.client2;

import java.io.IOException;
import java.net.Socket;

public class socket extends Thread{
    Socket socket;
    public static final int port=12345;
    public static final String host="192.168.1.102";

    @Override
    public void run() {
        try {
            socket=new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
