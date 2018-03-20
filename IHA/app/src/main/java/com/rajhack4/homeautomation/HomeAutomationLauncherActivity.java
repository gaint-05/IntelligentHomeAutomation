package com.rajhack4.homeautomation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.Activity;

public class HomeAutomationLauncherActivity extends Activity {
    ListView list;
    String[] web = {
            "Air conditioner",
            "Television",
            "FanActivity",
            "Lights",
            "Camera",
            "Door",

    } ;
    Integer[] imageId = {
            R.drawable.ac,
            R.drawable.tv,
            R.drawable.fan,
            R.drawable.lighton,
            R.drawable.camera,
            R.drawable.door,


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_automation_launcher);

        CustomList adapter = new
                CustomList(HomeAutomationLauncherActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position==0){
                    Intent i=new Intent(HomeAutomationLauncherActivity.this,AirConditionerRemoteActivity.class);
                    startActivity(i);
                }
                if(position==1){
                    Intent i=new Intent(HomeAutomationLauncherActivity.this,MainActivity.class);
                    startActivity(i);
                }
                if(position==2){
                    Intent i=new Intent(HomeAutomationLauncherActivity.this,FanActivity.class);
                    startActivity(i);
                }
                if(position==3){
                    Intent i=new Intent(HomeAutomationLauncherActivity.this,LightActivity.class);
                    startActivity(i);
                }
                if(position==4){
                    Intent i=new Intent(HomeAutomationLauncherActivity.this,CameraActivity.class);
                    startActivity(i);
                }
                if(position==5){
                    Intent i=new Intent(HomeAutomationLauncherActivity.this,DoorActivity.class);
                    startActivity(i);
                }
            }
        });

    }

}