package com.practice.jackchung.lexiangmusicplayer.entity;

import java.io.Serializable;

/**
 * Created by Dell on 2018/7/27.
 */

public class LocalMusic implements Serializable{
    private long id;
    private String title;
    private String artist;
    private String data;
    private long totalTime;


    public LocalMusic() {
    }

    public LocalMusic(long id, String title, String artist, String data, long totalTime) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.data = data;
        this.totalTime = totalTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return "LocalMusic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", data='" + data + '\'' +
                ", totalTime=" + totalTime +
                '}';
    }
}
