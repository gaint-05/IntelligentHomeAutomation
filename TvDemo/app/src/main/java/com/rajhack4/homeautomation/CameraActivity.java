package com.rajhack4.homeautomation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CameraActivity extends AppCompatActivity {
private Button mCapture,mRecord,mDestroy;
private static final String LOG_CAT = CameraActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mCapture =(Button) findViewById(R.id.capture);
        mRecord =(Button) findViewById(R.id.record);

        mDestroy =(Button) findViewById(R.id.play);
        mCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(LOG_CAT,"video snapshot taken");
            }
        });
        mRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(LOG_CAT, "record footage and stream to server");
            }
        });
        mDestroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(LOG_CAT,"clear history");
            }
        });

    }
}
