/*package com.e.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class rrr extends java.lang.Thread {

    MainActivity mainActivity=new MainActivity();
    Thread thread=new Thread();

    BufferedReader br;
    String str;

    @Override
    public void run() {
        try {
            br=new BufferedReader(new InputStreamReader(thread.tempClientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            try {
                str=br.readLine();
                mainActivity.mainhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mainActivity.txv2.setText("str");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}*/
