package com.rajhack4.homeautomation;


/**
 * Created by dbot_5 on 19-03-2018.
 */

public class Tvshow {
    private int mImage;
    private int mChannelNo;

    public Tvshow(int image, int channelNo) {
        this.mImage = image;
        this.mChannelNo = channelNo;
    }

    public int getImage() {
        return mImage;
    }

    public int getChannelNo() {
        return mChannelNo;
    }
}
