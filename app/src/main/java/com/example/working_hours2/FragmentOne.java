package com.example.working_hours2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class FragmentOne extends Fragment {


    private boolean isResume;
    long tMilliSec, tStart, tBuff, tUpdate = 0L;

    private int seconds = 0;
    int milliSec, sec, min, hour;
    Chronometer chronometer;
    Handler handler;

    ImageButton bottomstart, bottomstop;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_fragment_one, container, false);

        chronometer = v.findViewById(R.id.chronometer);
        bottomstart = v.findViewById(R.id.imageButton_play);
        bottomstop = v.findViewById(R.id.imageButton_stop);

        handler = new Handler();
        // Inflate the layout for this fragment
      //  bottomstart = getView().findViewById(R.id.chronometer);
        bottomstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResume){

                    tStart = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable,0);
                    chronometer.start();
                    isResume=true;

                }
                else{
                    tBuff += tMilliSec;
                    handler.removeCallbacks(runnable);
                    chronometer.stop();
                    isResume=false;

                }
            }
        });

        bottomstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResume){
                     bottomstart.setImageDrawable(getResources().getDrawable(R.drawable.ic_play));
                }
                chronometer.setFormat("00:00:00");
            }
        });



         //getView().findViewById(R.id.chronometer);
        return v;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tMilliSec = SystemClock.uptimeMillis() - tStart;
            tUpdate = tBuff + tMilliSec;
            sec = (int)(tUpdate/1000);
            min = sec/60;
            milliSec = (int)(tUpdate%100);
            chronometer.setText(String.format("%02d",min) + ":" +String.format("%02d",sec) + ":" + String.format("%02d",milliSec));
            handler.postDelayed(this, 60);


            /*hour = (int)(seconds/3600);
            min = (int)(seconds%3600)/60;
            sec = (int)(seconds%60);
            chronometer.setText(String.format("%02d",hour) + ":" +String.format("%02d",min) + ":" + String.format("%02d",sec));
            seconds++;
            handler.postDelayed(this, 1000);*/
        }
    };

    /*public void onstart(View view){
        running = true;
    }
    public void onstop(View view){
        running = false;
    }



    private void startTimer(){
      //  final Chronometer chronometer = findViewById(R.id.chronometer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                hour = (int)(seconds/3600);
                min = (int)(seconds%3600)/60;
                sec = (int)(seconds%60);
                chronometer.setText(String.format("%02d",hour) + ":" +String.format("%02d",min) + ":" + String.format("%02d",sec));
                if(running){
                    seconds ++;
                }
                handler.postDelayed(this, 1000);

            }
        });
    }*/
}
