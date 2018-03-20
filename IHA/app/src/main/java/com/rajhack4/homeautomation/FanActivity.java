package com.rajhack4.homeautomation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FanActivity extends AppCompatActivity {
private Button mTogglePower,mFanSpeedIncrement, mFanSpeedDecrement;
private static final String LOG_CAT = FanActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);
    mTogglePower =(Button) findViewById(R.id.power_toggle_fan);
    mFanSpeedIncrement=(Button) findViewById(R.id.fan_speed_increment);
    mFanSpeedDecrement = (Button)findViewById(R.id.fan_speed_decrement);
    mTogglePower.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e(LOG_CAT,"toggle power button");
        }
    });
    mFanSpeedIncrement.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e(LOG_CAT,"fan speed increased");
        }
    });
    mFanSpeedDecrement.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e(LOG_CAT,"fan speed decreased");
        }
    });
    }
}
