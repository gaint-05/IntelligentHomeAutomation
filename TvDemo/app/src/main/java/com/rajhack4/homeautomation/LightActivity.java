package com.rajhack4.homeautomation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LightActivity extends AppCompatActivity {
Button mPowerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        mPowerToggle=(Button) findViewById(R.id.on);
        mPowerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(LightActivity.class.getName(),"Tubelight/ bulb power toggled");
            }
        });

    }
}
