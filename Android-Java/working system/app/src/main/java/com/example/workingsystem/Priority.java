package com.example.workingsystem;

import android.util.Log;

import java.util.ArrayList;

public class Priority {
    private static final String TAG = "test";

    ArrayList<Integer> cpu_arrive_ok=new ArrayList<>();

    public Priority(ArrayList<Integer> Parameter,ArrayList<Integer> Answer,ArrayList<Integer> reply_waiting_time){
        int cpu_sum=0;
        for (int i = 1; i < Parameter.size(); i+=4) {
            cpu_sum=cpu_sum+Parameter.get(i);
        }

        int cpu_now=0;

        while(cpu_now!=cpu_sum){
            for (int i = 2; i < Parameter.size(); i+=4) {
                if(Parameter.get(i)<=cpu_now&&Parameter.get(i-1)>-1){
                    cpu_arrive_ok.add(i+1);
                }
            }

            int change;
            int cpu_min = 0;
            if(cpu_arrive_ok.size()>1){
                for (int i = 0; i < cpu_arrive_ok.size(); i++) {
                    for (int j = i+1; j < cpu_arrive_ok.size(); j++) {
                        if(Parameter.get(cpu_arrive_ok.get(i))<Parameter.get(cpu_arrive_ok.get(j))){
                        }
                        else if(Parameter.get(cpu_arrive_ok.get(i))>Parameter.get(cpu_arrive_ok.get(j))){
                            change= cpu_arrive_ok.get(i);
                            cpu_arrive_ok.set(i,cpu_arrive_ok.get(j));
                            cpu_arrive_ok.set(j,change);
                        }else if(Parameter.get(cpu_arrive_ok.get(i))==Parameter.get(cpu_arrive_ok.get(j))){
                            if(Parameter.get(cpu_arrive_ok.get(i)-1)<Parameter.get(cpu_arrive_ok.get(j)-1)){
                            }else if(Parameter.get(cpu_arrive_ok.get(i)-1)>Parameter.get(cpu_arrive_ok.get(j)-1)){
                                change= cpu_arrive_ok.get(i);
                                cpu_arrive_ok.set(i,cpu_arrive_ok.get(j));
                                cpu_arrive_ok.set(j,change);
                            }else if(Parameter.get(cpu_arrive_ok.get(i)-1)==Parameter.get(cpu_arrive_ok.get(j)-1)){
                            }
                        }
                    }
                }
            }else if(cpu_arrive_ok.size()==1){
            }

            cpu_min=Parameter.get(cpu_arrive_ok.get(0)-2);
            Answer.add(Parameter.get(cpu_arrive_ok.get(0)-3));
            Parameter.set(cpu_arrive_ok.get(0)-2,-1);
            cpu_arrive_ok.clear();

            cpu_now=cpu_now+cpu_min;
            Answer.add(cpu_now);
        }

        Log.d(TAG, "priority: "+Answer);

        int sum=0;
        int reply_time;
        for (int i = Answer.size()-2,j=0; i >= 0&&j<Parameter.size(); i-=2) {
            if(Answer.get(i)==Parameter.get(j)){
                sum=sum+(Answer.get(i+1)-Parameter.get(j+2));
                i = Answer.size();
                j+=4;
            }
        }

        reply_time=sum/(Parameter.size()/4);
        reply_waiting_time.add(sum/(Parameter.size()/4));
        reply_waiting_time.add(reply_time-cpu_sum/(Parameter.size()/4));

        Log.d(TAG, "reply_time: "+reply_time);
        Log.d(TAG, "waiting_time: "+reply_waiting_time.get(1));
    }
}
