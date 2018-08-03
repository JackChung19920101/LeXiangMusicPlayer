package com.practice.jackchung.lexiangmusicplayer.entity;

/**
 * Created by Dell on 2018/7/27.
 */

public class NetMusic extends BaseMusic {
    String composer;
    String lyricEditor;

    public NetMusic() {
    }

    public NetMusic(String musicName, String singer, int duration, String musicUrl, String composer, String lyricEditor) {
        super(musicName, singer, duration, musicUrl);
        this.composer = composer;
        this.lyricEditor = lyricEditor;
    }

    @Override
    public String getMusicName() {
        return super.getMusicName();
    }

    @Override
    public void setMusicName(String musicName) {
        super.setMusicName(musicName);
    }

    @Override
    public String getSinger() {
        return super.getSinger();
    }

    @Override
    public void setSinger(String singer) {
        super.setSinger(singer);
    }

    @Override
    public int getDuration() {
        return super.getDuration();
    }

    @Override
    public void setDuration(int duration) {
        super.setDuration(duration);
    }

    @Override
    public String getMusicUrl() {
        return super.getMusicUrl();
    }

    @Override
    public void setMusicUrl(String musicUrl) {
        super.setMusicUrl(musicUrl);

    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getLyricEditor() {
        return lyricEditor;
    }

    public void setLyricEditor(String lyricEditor) {
        this.lyricEditor = lyricEditor;
    }

    @Override
    public String toString() {
        return "NetMusic{" +super.toString()+
                ", composer='" + composer + '\'' +
                ", lyricEditor='" + lyricEditor + '\'' +
                '}';
    }
}
