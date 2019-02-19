package com.cm.cdc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlay extends AppCompatActivity {

    VideoView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_play);

        v = findViewById(R.id.vid);

        Intent i = getIntent();
        int code = i.getIntExtra("code",-1);

        Uri uri;
        if(code==0){
            uri = Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/"+R.raw.vidone);
            v.setVideoURI(uri);
        }else {
            uri = Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/"+R.raw.vidtwo);
            v.setVideoURI(uri);
        }

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(v);
        v.setMediaController(mediaController);
        v.start();

        v.setMediaController(new MediaController(this){
            public boolean dispatchKeyEvent(KeyEvent event)
            {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                    ((Activity) getContext()).finish();

                return super.dispatchKeyEvent(event);
            }
        });
    }


}
