package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.chatListFrag;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.unity3d.ads.UnityAds;

public class newChattingActivity extends AppCompatActivity {


    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onStart() {
        super.onStart();

        chatListFrag frag = new chatListFrag();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.newChattingFrame,frag);
        transaction.commit();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chatting);

        InterstitialAd();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        Intent intent = new Intent(newChattingActivity.this,newMainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
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