package com.practice.jackchung.lexiangmusicplayer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.practice.jackchung.lexiangmusicplayer.R;
import com.practice.jackchung.lexiangmusicplayer.entity.LocalMusic;
import com.practice.jackchung.lexiangmusicplayer.service.MusicPlayService;
import com.practice.jackchung.lexiangmusicplayer.utils.DurationUtil;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayActivity extends AppCompatActivity implements View.OnClickListener {

    private List<LocalMusic> musicList;
    private int playPosition;
    private LocalMusic playMusic;
    private ImageButton imageButton_myTitleBar_leftButton;
    private ImageButton imageButton_myTitleBar_rightButton;
    private TextView textView_myTitleBar_middleText;
    private ImageView imageView_musicPlayActivity_playPin;
    private ImageView imageView_musicPlayActivity_playDisk;
    private ImageButton imageButton_musicPlayActivity_favorite;
    private ImageButton imageButton_musicPlayActivity_download;
    private TextView textView_musicPlayActivity_musicLyric;
    private ImageButton imageButton_musicPlayActivity_lastMusic;
    private ImageButton imageButton_musicPlayActivity_pausePlayMusic;
    private ImageButton imageButton_musicPlayActivity_nextMusic;
    private TextView textView_musicPlayActivity_playTime;
    private SeekBar seekBar_musicPlayActivity_playProgress;
    private TextView textView_musicPlayActivity_totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);

        initialUI();
        initialData();
    }

    private void initialUI() {
        // 初始化控件
        imageButton_myTitleBar_leftButton = (ImageButton) findViewById(R.id.imageButton_MyTitleBar_LeftButton);
        imageButton_myTitleBar_rightButton = (ImageButton) findViewById(R.id.imageButton_MyTitleBar_RightButton);
        textView_myTitleBar_middleText = (TextView) findViewById(R.id.textView_MyTitleBar_MiddleText);

        imageView_musicPlayActivity_playPin = (ImageView) findViewById(R.id.imageView_MusicPlayActivity_PlayPin);
        imageView_musicPlayActivity_playDisk = (ImageView) findViewById(R.id.imageView_MusicPlayActivity_PlayDisk);
        imageButton_musicPlayActivity_favorite = (ImageButton) findViewById(R.id.imageButton_MusicPlayActivity_Favorite);
        imageButton_musicPlayActivity_download = (ImageButton) findViewById(R.id.imageButton_MusicPlayActivity_Download);
        textView_musicPlayActivity_musicLyric = (TextView) findViewById(R.id.textView_MusicPlayActivity_MusicLyric);

        imageButton_musicPlayActivity_lastMusic = (ImageButton) findViewById(R.id.imageButton_MusicPlayActivity_LastMusic);
        imageButton_musicPlayActivity_pausePlayMusic = (ImageButton) findViewById(R.id.imageButton_MusicPlayActivity_PausePlayMusic);
        imageButton_musicPlayActivity_nextMusic = (ImageButton) findViewById(R.id.imageButton_MusicPlayActivity_NextMusic);

        textView_musicPlayActivity_playTime = (TextView) findViewById(R.id.textView_MusicPlayActivity_PlayTime);
        seekBar_musicPlayActivity_playProgress = (SeekBar) findViewById(R.id.seekBar_MusicPlayActivity_PlayProgress);
        textView_musicPlayActivity_totalTime = (TextView) findViewById(R.id.textView_MusicPlayActivity_TotalTime);

        // 设置监听器
        imageButton_myTitleBar_leftButton.setOnClickListener(this);
        imageButton_myTitleBar_rightButton.setOnClickListener(this);
        imageButton_musicPlayActivity_favorite.setOnClickListener(this);
        imageButton_musicPlayActivity_download.setOnClickListener(this);
        imageButton_musicPlayActivity_lastMusic.setOnClickListener(this);
        imageButton_musicPlayActivity_pausePlayMusic.setOnClickListener(this);
        imageButton_musicPlayActivity_nextMusic.setOnClickListener(this);
    }

    private void initialData() {
        Intent intent = getIntent();
        playPosition = intent.getIntExtra("position",0);
        musicList = (ArrayList<LocalMusic>) intent.getSerializableExtra("musicList");
        playMusic = musicList.get(playPosition);

        Log.i("TAG","playPosition = "+playPosition);
        Log.i("TAG","playMusic = "+playMusic.toString());

        // 播放处理
        playMusic();
    }

    private void playMusic() {
        // 更新播放的歌曲名称显示
        textView_myTitleBar_middleText.setText(playMusic.getTitle());
        // 更新显示总的播放时间
        textView_musicPlayActivity_totalTime.setText(String.valueOf(DurationUtil.formatTime(playMusic.getTotalTime())));

        // 请求播放服务
        requestPlayService();
    }

    private void requestPlayService() {
        // 启动播放服务
        Intent playIntent = new Intent(this,MusicPlayService.class);
        playIntent.putExtra("musicUrl",playMusic.getData());
        startService(playIntent);
    }

    @Override
    public void onClick(View v) {
        int clickId = v.getId();
        switch (clickId){
            case R.id.imageButton_MyTitleBar_LeftButton:
                    Intent intent = new Intent(MusicPlayActivity.this,MainActivity.class);
                    startActivity(intent);
                break;
            case R.id.imageButton_MyTitleBar_RightButton:

                break;
            case R.id.imageButton_MusicPlayActivity_Favorite:

                break;
            case R.id.imageButton_MusicPlayActivity_Download:

                break;
            case R.id.imageButton_MusicPlayActivity_LastMusic:
                playLastMusic();
                break;
            case R.id.imageButton_MusicPlayActivity_PausePlayMusic:

                break;
            case R.id.imageButton_MusicPlayActivity_NextMusic:
                playNextMusic();
                break;
        }
    }

    private void playNextMusic() {
        playPosition = (Math.abs(playPosition+1))%musicList.size();
        playMusic = musicList.get(playPosition);
        Log.i("TAG","playPosition = "+playPosition);
        Log.i("TAG","playMusic = "+playMusic.toString());
        // 播放处理
        playMusic();
    }

    private void playLastMusic() {
        playPosition = (Math.abs(playPosition-1))%musicList.size();
        playMusic = musicList.get(playPosition);
        Log.i("TAG","playPosition = "+playPosition);
        Log.i("TAG","playMusic = "+playMusic.toString());
        // 播放处理
        playMusic();
    }
}
