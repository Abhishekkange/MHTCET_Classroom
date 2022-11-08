package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.AbhishekKangeProductions.mhtcetclassroom.fragments.PCB_tests;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.PCM_tests;
import com.unity3d.ads.UnityAds;

public class listOfTests extends AppCompatActivity {

    TextView PCM,PCB;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onStart() {
        super.onStart();

        PCM_tests pcm = new PCM_tests();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.lastFrame,pcm);
        transaction.commit();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_tests);

        InterstitialAd();

        PCB = findViewById(R.id.pcbButton);
        PCM = findViewById(R.id.pcmButton);

        PCM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PCM_tests pcm = new PCM_tests();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.lastFrame,pcm);
                transaction.commit();
                PCM.setBackgroundResource(R.drawable.pcmbackchange);
                PCB.setBackgroundResource(R.drawable.pcmback);

            }
        });

        PCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PCB_tests pcb = new PCB_tests();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.lastFrame,pcb);
                transaction.commit();
                PCM.setBackgroundResource(R.drawable.pcmback);
                PCB.setBackgroundResource(R.drawable.pcmbackchange);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(listOfTests.this,MainActivity.class);
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