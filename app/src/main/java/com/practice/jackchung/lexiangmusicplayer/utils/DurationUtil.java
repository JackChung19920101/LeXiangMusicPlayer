package com.practice.jackchung.lexiangmusicplayer.utils;

/**
 * Created by Dell on 2018/7/29.
 */

public class DurationUtil {
    public static String formatTime(long totalTime){
        String duration="";
        int min = (int)((totalTime/1000)/60);
        int sec = (int)((totalTime/1000)%60);
        duration = String.valueOf(min)+":"+String.valueOf(sec);
        return duration;
    }
}
