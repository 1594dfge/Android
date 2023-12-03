package com.e.client2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bufferedreader extends Thread {
    BufferedReader br;
    String str;

    MainActivity mainActivity=new MainActivity();
    socket socket=new socket();

    @Override
    public void run() {
        try {
            br=new BufferedReader(new InputStreamReader(socket.socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                str=br.readLine();
                mainActivity.mainhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mainActivity.txv2.setText(str);
                    }
                });
            } catch (IOException e) {
                try {
                    br.close();
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
