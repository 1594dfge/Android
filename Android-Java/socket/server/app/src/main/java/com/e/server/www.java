/*package com.e.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class www extends java.lang.Thread {

    MainActivity mainActivity=new MainActivity();
    Thread thread=new Thread();

    BufferedWriter bw;

    @Override
    public void run() {
        try {
            bw=new BufferedWriter(new OutputStreamWriter(thread.tempClientSocket.getOutputStream()));
            bw.write(mainActivity.edt.getText().toString());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
