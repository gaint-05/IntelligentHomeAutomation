package com.rajhack4.homeautomation;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class AirConditionerRemoteActivity extends AppCompatActivity {
    private Button on, direction, swing, tempinc, tempdec;
    private Button sensoon;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    public Vibrator v;
    boolean accelerometerPresent;
    private float acelVal; // current acceleration including gravity shake
    private float acelLast; // last acceleration including gravity shake
    private float shake; // acceleration apart from gravity shake
    private static final String LOG_CAT = AirConditionerRemoteActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioner_remote);
        on = (Button) findViewById(R.id.power);
        direction = (Button) findViewById(R.id.direction);
        swing = (Button) findViewById(R.id.swing);
        tempinc = (Button) findViewById(R.id.tempinc);
        tempdec = (Button) findViewById(R.id.tempdec);
        sensoon = (Button) findViewById(R.id.sensor_toggle);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(accelerometerListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        shake = 0.00f;
        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensorList.size() > 0) {
            accelerometerPresent = true;
            accelerometerSensor = sensorList.get(0);
            Toast.makeText(this, "accelerometer present", Toast.LENGTH_SHORT).show();
        } else {
            accelerometerPresent = false;
            Toast.makeText(this, "no accelerometer present", Toast.LENGTH_SHORT).show();
        }


        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(LOG_CAT,"power_toggle");

            }


        });
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(LOG_CAT,"direction_toggle");

            }


        });
        swing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(LOG_CAT,"swing_toggle");

            }


        });
        tempdec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(LOG_CAT,"temperature decrease");

            }


        });
        tempinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(LOG_CAT,"temperature increase");

            }


        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (accelerometerPresent) {
            sensorManager.registerListener(accelerometerListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (accelerometerPresent) {
            sensorManager.unregisterListener(accelerometerListener);
        }
    }

    private SensorEventListener accelerometerListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent arg0) {
            // TODO Auto-generated method stub

            float x = arg0.values[0];
            float y = arg0.values[1];
            float z = arg0.values[2];
            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = acelVal - acelLast;
            shake = shake * 0.9f + delta; // perform low-cut filter
            if (shake > 12) {
                Toast toast = Toast.makeText(getApplicationContext(), "Power Toggle", Toast.LENGTH_LONG);
                Log.e(LOG_CAT, "Power Toggle");
                toast.show();
                while(shake > 12){

                }



            }
            else if (Math.abs(x) > Math.abs(y)) {
                if (x < 0 || y < 0) {
                    Log.e(LOG_CAT, "right direction of swing");
                    while(x<0 || y<0){

                    }
                } else if (x > 0 || y > 0) {
                    Log.e(LOG_CAT, "left direction of swing");
                    while(x>0 || y>0){

                    }
                }
            }

            else if(z>0){
                Log.e(LOG_CAT,"set direction right");
                while(z>0){

                }
            }
            else if(z<0){
                Log.e(LOG_CAT,"set direction left");
                while(z<0){

                }
            }


        }
    };}
