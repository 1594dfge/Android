package com.e.client2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class printwriter implements Runnable {
    PrintWriter pw;

    MainActivity mainActivity=new MainActivity();
    socket socket=new socket();

    @Override
    public void run() {
        try {
            pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.socket.getOutputStream())));
            pw.println(mainActivity.edt.getText());
            pw.flush();
        } catch (IOException e) {
            pw.close();
        }
    }
}
