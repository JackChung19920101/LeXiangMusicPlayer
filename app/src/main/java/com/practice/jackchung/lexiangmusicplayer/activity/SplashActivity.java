package com.practice.jackchung.lexiangmusicplayer.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.practice.jackchung.lexiangmusicplayer.R;

public class SplashActivity extends AppCompatActivity {

    private TextView textView_splashActivity_timerText;
    private ImageView imageView_splashActivity_welcomeImage;
    private int time = 5;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        onInitialUI();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    Log.i("TAG","time="+msg.obj);
                    textView_splashActivity_timerText.setText(msg.obj+"S");
                    if(((int)msg.obj)==0){
                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (time>0){
                    try {
                        Thread.sleep(1000);
                        time--;
                        Message message = handler.obtainMessage();
                        message.what=1;
                        message.obj=time;
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private void onInitialUI() {
        textView_splashActivity_timerText = (TextView) findViewById(R.id.textView_SplashActivity_timerText);
        imageView_splashActivity_welcomeImage = (ImageView) findViewById(R.id.imageView_SplashActivity_welcomeImage);
    }
}
