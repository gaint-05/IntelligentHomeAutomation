package com.rajhack4.homeautomation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DoorActivity extends AppCompatActivity {
private Button mLock,mUnlock;
private static final String LOG_CAT = DoorActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_door);
        mLock =(Button) findViewById(R.id.lock);
        mUnlock =(Button) findViewById(R.id.unlock);
        mLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(LOG_CAT,"lock the DoorActivity ");
            }
        });
        mUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(LOG_CAT,"unlock the DoorActivity");
            }
        });
    }

}
