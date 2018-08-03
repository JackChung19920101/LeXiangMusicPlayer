package com.practice.jackchung.lexiangmusicplayer.entity;

/**
 * Created by Dell on 2018/7/27.
 */

public class BaseMusic {
    String musicName;//音乐名称
    String singer;// 歌手
    int duration;//总时长
    String musicUrl;//音乐文件路径

    public BaseMusic() {
    }

    public BaseMusic(String musicName, String singer, int duration, String musicUrl) {
        this.musicName = musicName;
        this.singer = singer;
        this.duration = duration;
        this.musicUrl = musicUrl;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    @Override
    public String toString() {
        return
                "musicName='" + musicName + '\'' +
                ", singer='" + singer + '\'' +
                ", duration=" + duration +
                ", musicUrl='" + musicUrl + '\'';
    }
}
