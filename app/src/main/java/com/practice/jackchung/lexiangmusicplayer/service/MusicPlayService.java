package com.practice.jackchung.lexiangmusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;



import java.io.IOException;


public class MusicPlayService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private String musicStoragePath;


    public MusicPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("TAG","MusicPlayService.onStart()");
        // 处理播放请求
        processPlayRequest(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TAG","MusicPlayService.onCreate()");
    }

    /**
     * 处理播放的请求
     */
    private void processPlayRequest(Intent intent){
        Log.i("TAG","MusicPlayService.processPlayRequest()");
        //1. 创建播放器对象
        if(mediaPlayer==null){
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
        }else{
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        musicStoragePath = intent.getStringExtra("musicUrl");
        if(musicStoragePath !=null) {
            try {
                mediaPlayer.setDataSource(musicStoragePath);
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        if(mp!=null){
            Log.i("TAG","MusicPlayService.onPrepared()");
            mp.start();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if(mp!=null){
            Log.i("TAG","MusicPlayService.onCompletion()");
            mp.start();
        }
    }
}
