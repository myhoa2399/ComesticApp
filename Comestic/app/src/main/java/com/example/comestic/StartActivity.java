package com.example.comestic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class StartActivity extends AppCompatActivity {
    VideoView mvideoView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        mvideoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.initial );

        try {
            mvideoView.setVideoURI(uri);
        } catch (NullPointerException techmaster1)
        {
            System.out.println("Couldn't load video" + techmaster1);
        }
        mvideoView.start();


        getSupportActionBar().hide();
        //Dùng cài đặt sau 3 giây màn hình tự chuyển
        Thread bamgio=new Thread(){
            public void run()
            {
                try {



                    sleep(6000);
                } catch (Exception e) {

                }
                finally
                {
                    Intent callMainAcc=new Intent(StartActivity.this, MainActivity.class);
                    startActivity(callMainAcc);
                }
            }
        };
        bamgio.start();
    }
    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause(){
        super.onPause();
        finish();
    }

}

