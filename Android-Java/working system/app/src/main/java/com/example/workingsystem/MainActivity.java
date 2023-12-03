package com.example.workingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "test";
    TableLayout tableLayout,tableLayout2;
    TableRow tableRow1,tableRow2,tableRow3,tableRow4,tableRow5,tableRow6,tableRow7,tableRow8,tableRow9,tableRow10,tableRow11;
    Spinner spinner,spinner2;
    Button button2;
    EditText editText;
    TextView textView,textView2;

    Handler mHandler;

    ArrayList<TableRow> Tablerow_list,Tablerow_list2;
    ArrayList<Integer> Parameter;
    ArrayList<Integer> Answer;
    ArrayList<Integer> p_list;
    ArrayList<Integer> color_list;
    ArrayList<Integer> reply_waiting_time;

    int p; //TableLayout裡TableRow的編號
    int spinner_number;
    int id1=10; //讓TableRow裡的EditText的ID不會重複

    String spinner_run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout=findViewById(R.id.TableLayout);
        tableLayout2=findViewById(R.id.TableLayout2);

        button2=findViewById(R.id.Button2);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        spinner=findViewById(R.id.spinner);
        spinner2=findViewById(R.id.spinner2);

        tableLayout.setStretchAllColumns(true);
        tableLayout.setStretchAllColumns(true);
        tableRow1=new TableRow(MainActivity.this); //Tablerow_list
        tableRow2=new TableRow(MainActivity.this);
        tableRow3=new TableRow(MainActivity.this);
        tableRow4=new TableRow(MainActivity.this);
        tableRow5=new TableRow(MainActivity.this);
        tableRow6=new TableRow(MainActivity.this);
        tableRow7=new TableRow(MainActivity.this);
        tableRow8=new TableRow(MainActivity.this);
        tableRow9=new TableRow(MainActivity.this);
        tableRow10=new TableRow(MainActivity.this);
        tableRow11=new TableRow(MainActivity.this); //Tablerow_list2

        Tablerow_list=new ArrayList<TableRow>();
        Tablerow_list.add(tableRow1);
        Tablerow_list.add(tableRow2);
        Tablerow_list.add(tableRow3);
        Tablerow_list.add(tableRow4);
        Tablerow_list.add(tableRow5);
        Tablerow_list.add(tableRow6);
        Tablerow_list.add(tableRow7);
        Tablerow_list.add(tableRow8);
        Tablerow_list.add(tableRow9);
        Tablerow_list.add(tableRow10);

        Tablerow_list2=new ArrayList<TableRow>();
        Tablerow_list2.add(tableRow11);

        Parameter=new ArrayList<>();

        Answer=new ArrayList<>();

        p_list=new ArrayList<>();

        color_list=new ArrayList<>();
        color_list.add(Color.parseColor("#ff0000"));
        color_list.add(Color.parseColor("#ff6600"));
        color_list.add(Color.parseColor("#ffff00"));
        color_list.add(Color.parseColor("#ff00ff"));
        color_list.add(Color.parseColor("#00ff00"));
        color_list.add(Color.parseColor("#00ffff"));
        color_list.add(Color.parseColor("#000000"));
        color_list.add(Color.parseColor("#006600"));
        color_list.add(Color.parseColor("#660000"));
        color_list.add(Color.parseColor("#ffffff"));

        reply_waiting_time=new ArrayList<>();
//----------------------------------------------------------------------------------------------------
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                        R.array.numbers_array,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener spnOnItemSelected
                = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String s;
                s=(String)spinner.getItemAtPosition(pos);
                spinner_number=Integer.parseInt(s);
                if(pos!=spinner_number){
                    p=1;
                    for (int i = 0; i < 10; i++) {
                        Tablerow_list.get(i).removeAllViews();
                    }
                    tableLayout.removeAllViews();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i =0;i<spinner_number;i++) {
                            try {
                                Thread.sleep(500);
                                mHandler.obtainMessage(1,i,0).sendToTarget();
                            } catch (Exception e) {
                            }
                        }
                    }
                }).start();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        spinner.setOnItemSelectedListener(spnOnItemSelected);
//----------------------------------------------------------------------------------------------------
        ArrayAdapter<CharSequence> adapter2 =
                ArrayAdapter.createFromResource(this,
                        R.array.run_array,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        AdapterView.OnItemSelectedListener spnOnItemSelected2
                = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s2;
                s2=(String)spinner.getItemAtPosition(position);
                if(s2.equals("1")){
                    s2="FCFS";
                }else if(s2.equals("2")){
                    s2="SJF";
                }
                else if(s2.equals("3")){
                    s2="SRT";
                }
                else if(s2.equals("4")){
                    s2="Priority";
                }
                else if(s2.equals("5")){
                    s2="Priority_rush";
                }
                else if(s2.equals("6")){
                    s2="RR";
                }
                spinner_run=s2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        spinner2.setOnItemSelectedListener(spnOnItemSelected2);
//----------------------------------------------------------------------------------------------------
        mHandler=new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                int what=msg.what;

                if(what==1){
                    int i=msg.arg1;
                    TextView number=new TextView(MainActivity.this);
                    int a=i; //0 1 2 3 4 5 6 7 8 9
                    number.setId(a);
                    EditText cpu=new EditText(MainActivity.this);
                    int b=a+id1; //10 11 12 13 14 15 16 17 18 19
                    cpu.setId(b);
                    EditText arrive=new EditText(MainActivity.this);
                    int c=b+id1; //20 21 22 23 24 25 26 27 28 29
                    arrive.setId(c);
                    EditText priority=new EditText(MainActivity.this);
                    int d=c+id1; //30 31 32 33 34 35 36 37 38 39
                    priority.setId(d);

                    number.setText("P"+p);
                    p=p+1;
                    Tablerow_list.get(i).addView(number);
                    Tablerow_list.get(i).addView(cpu);
                    Tablerow_list.get(i).addView(arrive);
                    Tablerow_list.get(i).addView(priority);
                    tableLayout.addView( Tablerow_list.get(i));
                }
                else if(what==2){
                    int j=msg.arg1;
                    //int k=msg.arg2;
                    TextView textView=new TextView(MainActivity.this);
                    textView.setWidth(100);
                    textView.setHeight(200);
                    textView.setText("p"+"\n"+Answer.get(j)+"\n\n"+Answer.get(j+1));

                    for (int i = 0; i < p_list.size(); i++) {
                        if(p_list.get(i)==Answer.get(j)){
                            textView.setBackgroundColor(color_list.get(i)); //判斷編號的顏色
                        }
                    }

                    Tablerow_list2.get(0).addView(textView);
                    if(j==Answer.size()-2){
                        tableLayout2.addView(Tablerow_list2.get(0));
                    }
                }
            }
        };
//----------------------------------------------------------------------------------------------------
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parameter.clear();
                Answer.clear();
                p_list.clear();
                reply_waiting_time.clear();

                Tablerow_list2.get(0).removeAllViews();
                tableLayout2.removeAllViews();

                    for (int i = 0; i < spinner_number; i++) {
                        Parameter.add(i+1);
                        for (int j = 1; j < 4; j++) {
                            int id=Tablerow_list.get(i).getVirtualChildAt(j).getId();
                            EditText editText=findViewById(id);
                            Parameter.add(Integer.parseInt(editText.getText().toString())); //EditText等於null時 程式會發生錯誤
                        }
                    }
                    Log.d(TAG, "Parameter: "+Parameter);

                    for (int i = 0; i < Parameter.size(); i+=4) {
                        p_list.add(Parameter.get(i));
                    }

                    if(spinner_run=="FCFS"){
                        FCFS fcfs=new FCFS(Parameter,Answer,reply_waiting_time);
                    }else if(spinner_run=="SJF"){
                        SJF sjf=new SJF(Parameter,Answer,reply_waiting_time);
                    }else if(spinner_run=="SRT"){
                        SRT srt=new SRT(Parameter,Answer,reply_waiting_time);
                    }else if(spinner_run=="Priority"){
                        Priority priority=new Priority(Parameter,Answer,reply_waiting_time);
                    }else if(spinner_run=="Priority_rush"){
                        Priority_rush priority_rush=new Priority_rush(Parameter,Answer,reply_waiting_time);
                    }else if(spinner_run=="RR"){
                        RR rr=new RR(Parameter,Answer,Integer.parseInt(editText.getText().toString()),reply_waiting_time);
                    }

                    textView.setText(Integer.toString(reply_waiting_time.get(0)));
                    textView2.setText(Integer.toString(reply_waiting_time.get(1)));

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for(int i =0;i<Answer.size();i+=2) {
                                try {
                                    Thread.sleep(500);
                                    mHandler.obtainMessage(2,i,0).sendToTarget();
                                } catch (Exception e) {
                                }
                            }
                        }
                    }).start();
            }
        });
    }
}