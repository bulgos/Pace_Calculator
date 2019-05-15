package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    NumberPicker paceMin;
    NumberPicker paceSec;
    NumberPicker distKM;
    NumberPicker distM;
    NumberPicker timeHour;
    NumberPicker timeMin;
    NumberPicker timeSec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //Get the pace_widgets reference from XML layout
        final TextView textPace = findViewById(R.id.text_pace);
        NumberPicker paceMin = findViewById(R.id.paceMin);
        NumberPicker paceSec = findViewById(R.id.paceSec);

        //Get the dist_widgets reference from XML layout
        final TextView textDist = findViewById(R.id.text_dist);
        NumberPicker distKM = findViewById(R.id.distKM);
        NumberPicker distM = findViewById(R.id.distM);

        //Get the time_widgets reference from XML layout
        final TextView textTime = findViewById(R.id.text_time);
        NumberPicker timeHour = findViewById(R.id.timeHour);
        NumberPicker timeMin = findViewById(R.id.timeMin);
        NumberPicker timeSec = findViewById(R.id.timeSec);
        */

        //Get the pace_widgets reference from XML layout
        final TextView textPace = findViewById(R.id.text_pace);
        paceMin = findViewById(R.id.paceMin);
        paceSec = findViewById(R.id.paceSec);

        //Get the dist_widgets reference from XML layout
        final TextView textDist = findViewById(R.id.text_dist);
        distKM = findViewById(R.id.distKM);
        distM = findViewById(R.id.distM);

        //Get the time_widgets reference from XML layout
        final TextView textTime = findViewById(R.id.text_time);
        timeHour = findViewById(R.id.timeHour);
        timeMin = findViewById(R.id.timeMin);
        timeSec = findViewById(R.id.timeSec);

        //Set TextView text color
        textTime.setTextColor(Color.parseColor("#ffd32b3b"));

        CreateNumberPicker(0, 60, paceMin, true);
        CreateNumberPicker(0, 60, paceSec, true);

        CreateNumberPicker(0, 1000, distKM, false);
        CreateNumberPicker(0, 10, distM, false);

        CreateNumberPicker(0, 48, timeHour, true);
        CreateNumberPicker(0, 60, timeMin, true);
        CreateNumberPicker(0, 60, timeSec, true);


    }

    private void ChangePace(){
        int option = 1;
        float pace = getPace();

        if (option == 1){
            float time = getDist() / getPace();
            setTime(time);
        }
        else{
            float dist = getPace() * getTime();
            setDist(dist);
        }
    }

    private void ChangeDist(){
        int option = 0;

        if (option == 0){
            float time = getDist() / getPace();
            setTime(time);
        }
        else{
            float pace = getDist() / getTime();
            setPace(pace);
        }
    }

    private void ChangeTime(){
        int option = 2;
        if (option == 2){
            float pace = getDist() / getTime();
            setPace(pace);
        }
        else{
            float dist = getPace() * getTime();
            setDist(dist);
        }
    }

    private float getPace(){
        float pace = 60 * paceMin.getValue() + paceSec.getValue();
        return pace;
    }

    private float getDist(){
        float dist = distKM.getValue() + (float)distM.getValue()/(float)10;
        return dist;
    }

    private float getTime(){
        float time = 60 * (60 * timeHour.getValue() + timeMin.getValue()) + timeSec.getValue();
        return time;
    }

    private void setPace(float pace){
        int min = (int)(pace / 60);
        pace = pace % (min * 60);
        int sec = (int)pace;

        paceMin.setValue(min);
        paceSec.setValue(sec);
    }

    private void setDist(float dist){
        int km = (int)Math.floor(dist);
        int m = (int)(dist % 1.0);

        distKM.setValue(km);
        distM.setValue(m);
    }

    private void setTime(float time){
        int hour = (int)(time / (60*60));
        time = time % (hour * (60 * 60));
        int min = (int)(time / 60);
        time = time % (min * 60);
        int sec = (int)time;

        timeHour.setValue(hour);
        timeMin.setValue(min);
        timeSec.setValue(sec);
    }

    private NumberPicker CreateNumberPicker(int min, int max, NumberPicker numberPicker, boolean padFormat) {
        //Set the minimum value of NumberPicker
        numberPicker.setMinValue(min);
        //Specify the maximum value/number of NumberPicker
        numberPicker.setMaxValue(max);

        numberPicker.setWrapSelectorWheel(true);

        if (padFormat){
            numberPicker.setFormatter(new NumberPicker.Formatter() {
                @Override
                public String format(int value) {
                    return String.format("%02d", value);
                }
            });
        }


        //Set a value change listener for NumberPicker
//        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
//                //Display the newly selected number from picker
//                tv.setText("Selected Number : " + newVal);
//            }
//        });
        return numberPicker;
    }

    private void update(){

    }
}
