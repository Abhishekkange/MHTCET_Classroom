package com.AbhishekKangeProductions.mhtcetclassroom.SocialMediaFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.AbhishekKangeProductions.mhtcetclassroom.R;

public class MainPage extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onStart() {
        super.onStart();

        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);



        //Creating mediaPlayer
        mediaPlayer = MediaPlayer.create(this,R.raw.forestambiance);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mediaPlayer.stop();
    }
}