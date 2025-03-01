package com.example.test;

import android.media.MediaPlayer;

public class MyMediaPlayer {
    static MediaPlayer instance;
    public static MediaPlayer getIntance(){
        if (instance==null){
            instance = new MediaPlayer();

        }
        return instance;
    }
    public static int currentIndex =-1;
}
