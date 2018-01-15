package com.example.mohitagarwal.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView  textView;
    Button button;
    Boolean countdownisactive=false;
    CountDownTimer countDownTimer;


    public void resetTimer(){
        textView.setText("00:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        button.setText("GO !!");
        seekBar.setEnabled(true);
        countdownisactive=false;

    }

    public void updateTimer(int secondsleft){
        int minutes = (int) secondsleft /60;
        int seconds = secondsleft-minutes*60;
        String secondstring = Integer.toString(seconds);
        if(seconds <= 9){
            secondstring="0"+secondstring;
        }


        textView.setText(Integer.toString(minutes)+" : "+ secondstring);

    }
    public void eggTimer(View view){


        if(countdownisactive == false) {
            countdownisactive = true;
            seekBar.setEnabled(false);
            button.setText("Stop");
             countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {


                @Override
                public void onTick(long l) {

                    updateTimer((int) (l / 1000));

                }

                @Override
                public void onFinish() {

                    resetTimer();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();

                }
            }.start();

        }else{

            resetTimer();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=(SeekBar)findViewById(R.id.seekBar);
        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        seekBar.setMax(1200);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
