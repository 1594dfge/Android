package com.e.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Thread extends java.lang.Thread {
    //server
    public ServerSocket serverSocket;
    public Socket tempClientSocket;
    public static final int SERVER_PORT=12345;

    @Override
    public void run() {
        try {
            serverSocket=new ServerSocket(SERVER_PORT);//建立sercerSocket 設定端口號
        } catch (IOException e) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        while (true){
            try {
                tempClientSocket=serverSocket.accept();//等待連線
            } catch (IOException e) {
                try {
                    tempClientSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
