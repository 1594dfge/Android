package com.e.client2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private HandlerThread handlerThread=new HandlerThread("HandlerThread");
    private Handler threadHandler;
    Handler mainhandler=new Handler();

    socket socket=new socket();
    bufferedreader bufferedreader=new bufferedreader();
    printwriter printwriter=new printwriter();

    TextView txv,txv2;
    Button btn,btn2,btn3,btn4;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv=findViewById(R.id.txv);
        txv2=findViewById(R.id.txv2);
        btn=findViewById(R.id.btn);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        edt=findViewById(R.id.edt);

        handlerThread.start();
        threadHandler=new Handler(handlerThread.getLooper());
    }

    public void onClick(View v){
        socket.start();
    }

    public void onClick2(View v){
        if(!socket.socket.isClosed()){
            if(socket.socket.isConnected()){
                txv.setText("連線成功");
            }
        }
    }

    public void onClick3(View v){
        bufferedreader.start();
    }

    public void onClick4(View v){
        threadHandler.post(printwriter);
    }
}
