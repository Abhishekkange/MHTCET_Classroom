package com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.viewpagerAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.MainActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.availableToChat;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.unity3d.ads.UnityAds;

public class chattingActivity extends AppCompatActivity {

        ImageView backBtn;
        RecyclerView recyclerView;
        FirebaseAuth mAuth;
        FirebaseDatabase database;
        ImageView bannerAdmine,searchBtn;
        ViewPager viewPager;
        TabLayout tabLayout;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";



    @Override
    protected void onStart() {
        super.onStart();

      //  Picasso.get().load(database.getReference().child("Advertsing").child("PersonalChatBanner").getKey());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        InterstitialAd();

        //finding View By ID

        backBtn = findViewById(R.id.backArrowChats);
        recyclerView = findViewById(R.id.chattingPersonalRv);
        mAuth = FirebaseAuth.getInstance();
        searchBtn = findViewById(R.id.chatSearchBtn);
        database = FirebaseDatabase.getInstance();



        viewPager = findViewById(R.id.viewPagerChatting);
        tabLayout = findViewById(R.id.formulaSheetTabLayout);

        viewpagerAdapter adapter = new viewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        //Adding On Click Listeners

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(chattingActivity.this, availableToChat.class);
                startActivity(intent);
                Animatoo.animateSlideUp(chattingActivity.this);





            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(chattingActivity.this,MainActivity.class);
                startActivity(intent);
                Animatoo.animateSlideRight(chattingActivity.this);


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