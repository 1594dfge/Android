package com.example.workingsystem;

import android.util.Log;
import java.util.ArrayList;

public class RR {
    private static final String TAG = "test";

    ArrayList<Integer> cpu_arrive_ok=new ArrayList<>();
    ArrayList<Integer> arrive_min_max=new ArrayList<>();
    ArrayList<Integer> cpu_arrive=new ArrayList<>();

    public RR(ArrayList<Integer> Parameter,ArrayList<Integer> Answer,int rr,ArrayList<Integer> reply_waiting_time){
        int cpu_sum=0;
        for (int i = 1; i < Parameter.size(); i+=4) {
            cpu_sum=cpu_sum+Parameter.get(i);
        }

        for (int i = 2; i < Parameter.size(); i+=4) {
            if(Parameter.get(i)==0){
                cpu_arrive_ok.add(i-1);
                Parameter.set(i,-1);
            }
        }

        for (int i = 0; i < Parameter.size(); i+=4) {
            cpu_arrive.add(Parameter.get(i));
            cpu_arrive.add(Parameter.get(i+2));
        }

        int cpu_now=0;

        while(cpu_now!=cpu_sum){
            int cpu_rr=rr;

            int change = 0;
            int add_if = 0;
            if( Parameter.get(cpu_arrive_ok.get(0))-cpu_rr>0){
                Parameter.set(cpu_arrive_ok.get(0),Parameter.get(cpu_arrive_ok.get(0))-cpu_rr);
                change=cpu_arrive_ok.get(0);
                Answer.add(Parameter.get(cpu_arrive_ok.get(0)-1));
                cpu_arrive_ok.remove(0);
                add_if=1;
            }else if(Parameter.get(cpu_arrive_ok.get(0))-cpu_rr<=0){
                cpu_rr=Parameter.get(cpu_arrive_ok.get(0));
                Parameter.set(cpu_arrive_ok.get(0),0);
                Answer.add(Parameter.get(cpu_arrive_ok.get(0)-1));
                cpu_arrive_ok.remove(0);
            }

            cpu_now=cpu_now+cpu_rr;
            Answer.add(cpu_now);

            for (int i = 2; i < Parameter.size(); i+=4) {
                if(Parameter.get(i)<=cpu_now&&Parameter.get(i)!=-1){
                    arrive_min_max.add(i);
                }
            }

            int change2;
            for (int i = 0; i < arrive_min_max.size(); i++) {
                for (int j = i+1; j < arrive_min_max.size(); j++) {
                    if(Parameter.get(arrive_min_max.get(i))<Parameter.get(arrive_min_max.get(j))){
                    }
                    else if(Parameter.get(arrive_min_max.get(i))>Parameter.get(arrive_min_max.get(j))){
                        change2=arrive_min_max.get(i);
                        arrive_min_max.set(i,arrive_min_max.get(j));
                        arrive_min_max.set(j,change2);
                    }else if(Parameter.get(arrive_min_max.get(i))==Parameter.get(arrive_min_max.get(j))){
                    }
                }
            }

            for (int i = 0; i < arrive_min_max.size(); i++) {
                cpu_arrive_ok.add(arrive_min_max.get(i)-1);
                Parameter.set(arrive_min_max.get(i),-1);
            }

            if(add_if==1){
                cpu_arrive_ok.add(change);
            }else if(add_if==0){
            }

            arrive_min_max.clear();
        }

        cpu_arrive_ok.clear();

        Log.d(TAG, "rr: "+Answer);

        int sum=0;
        int reply_time;
        for (int i = Answer.size()-2,j=0; i >= 0&&j<cpu_arrive.size(); i-=2) {
            if(Answer.get(i)==cpu_arrive.get(j)){
                sum=sum+(Answer.get(i+1)-cpu_arrive.get(j+1));
                i = Answer.size();
                j+=2;
            }
        }

        reply_time=sum/(Parameter.size()/4);
        reply_waiting_time.add(sum/(Parameter.size()/4));
        reply_waiting_time.add(reply_time-cpu_sum/(Parameter.size()/4));

        cpu_arrive.clear();

        Log.d(TAG, "reply_time: "+reply_time);
        Log.d(TAG, "waiting_time: "+reply_waiting_time.get(1));
    }
}
