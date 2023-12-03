package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private static final String ACTIVITY_TAG="Debug";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Myadapter myAdapter;
    private LinkedList<HashMap<String,String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);   //也可以使用GridLayoutManager
        recyclerView.setLayoutManager(mLayoutManager);

        doData();

        myAdapter=new Myadapter();
        recyclerView.setAdapter(myAdapter);
    }

    private void doData(){
        data=new LinkedList<>();
        for(int i=0; i<100; i++){
            HashMap<String,String> row=new HashMap<>();
            int random=(int)(Math.random()*100);
            row.put("title","Title"+random);
            row.put("date","Date"+random);
            data.add(row);
        }
    }

    private class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder>{

        class MyViewHolder extends RecyclerView.ViewHolder{
            public View itemView;
            public TextView title,date;
            public MyViewHolder(View v) {
                super(v);
                itemView=v;

                title=itemView.findViewById(R.id.item_title);
                date=itemView.findViewById(R.id.item_date);
            }
        }

        @NonNull
        @Override
        public Myadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            MyViewHolder vh=new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull Myadapter.MyViewHolder holder, int position) {
            holder.title.setText(data.get(position).get("title"));
            holder.date.setText(data.get(position).get("date"));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("Brad","Click:"+position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public void test1(View view){
        data.get(3).put("title","Brad");
        data.get(3).put("date","2018-09-03");
        myAdapter.notifyDataSetChanged();
    }
    public void test2(View view){
        data.removeFirst();
        myAdapter.notifyDataSetChanged();
    }
}