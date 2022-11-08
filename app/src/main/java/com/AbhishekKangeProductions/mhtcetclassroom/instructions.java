package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.testLayout;
import com.unity3d.ads.UnityAds;

public class instructions extends AppCompatActivity {

    Button next;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        InterstitialAd();

        Intent intent = getIntent();
        String data = intent.getStringExtra("theTestNo");
        next = findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(instructions.this, testLayout.class);
                intent.putExtra("theTestNo",data);
                startActivity(intent);


            }
        });


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