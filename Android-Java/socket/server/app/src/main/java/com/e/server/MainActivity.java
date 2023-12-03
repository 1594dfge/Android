package com.e.server;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    private HandlerThread handlerThread=new HandlerThread("HandlerThread");
    private Handler threadHandler;
    Handler mainhandler=new Handler();

    Thread thread=new Thread();

    java.lang.Thread thread123;

    TextView txv,txv2;
    Button btn,btn2,btn3;
    EditText edt;

    BufferedReader br;
    PrintWriter bw;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv=findViewById(R.id.txv);
        txv2=findViewById(R.id.txv2);
        btn=findViewById(R.id.btn);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        edt=findViewById(R.id.edt);

        thread123=new java.lang.Thread(r1);

        thread.start();//開始server端的子執行緒
        handlerThread.start();
        threadHandler=new Handler(handlerThread.getLooper());
    }

    public void onClick(View view){//判斷連接
        if(!thread.tempClientSocket.isClosed()){
            if(thread.tempClientSocket.isConnected()){
                txv.setText("連線成功");
            }
        }
    }

    private Runnable r1=new Runnable() {
        @Override
        public void run() {
            try {
                br=new BufferedReader(new InputStreamReader(thread.tempClientSocket.getInputStream()));
            } catch (IOException e) {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            while(true){
                try {
                    str=br.readLine();
                    mainhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            txv2.setText(str);
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
    };

    public void onClick2(View view){
        thread123.start();
    }

    class ExampleRunnable1 implements Runnable{
    @Override
    public void run() {
        try {
            bw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(thread.tempClientSocket.getOutputStream())));
            bw.println(edt.getText());
            bw.flush();
        } catch (IOException e) {
            bw.close();
        }
    }
}

    public void onClick3(View v){
        threadHandler.post(new ExampleRunnable1());
    }
}
