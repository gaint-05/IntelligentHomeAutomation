package com.rajhack4.homeautomation;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import java.io.IOException;


/**
 * Created by dbot_5 on 19-03-2018.
 */

public class ChannelDbUtils {

    public static int getChannelThroughName(Context context, String channelName) {
        Log.e("Channel name", channelName);
        ChannelDbHelper myDbHelper = new ChannelDbHelper(context);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();


        } catch (SQLException sqle) {

            throw sqle;

        }
        return myDbHelper.queryChannelNo(channelName);
    }
}
