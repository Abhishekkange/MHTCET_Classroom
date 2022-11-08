package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.unity3d.ads.UnityAds;

public class result extends AppCompatActivity {

    TextView score;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = findViewById(R.id.score);

        Intent intent = getIntent();
        String myScore = intent.getStringExtra("myscore");
        score.setText(myScore);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(result.this,listOfTests.class);
        startActivity(intent);
    }

    public void InterstitialAd(){

        if(UnityAds.isInitialized()) {
            UnityAds.load(INTERSTRITIALID);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    UnityAds.load(INTERSTRITIALID);
                }
            },5000);
        }
    }
}