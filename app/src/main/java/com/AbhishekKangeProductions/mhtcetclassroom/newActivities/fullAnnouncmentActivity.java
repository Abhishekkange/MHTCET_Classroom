package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.fullAnnounceModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.unity3d.ads.UnityAds;

public class fullAnnouncmentActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    ImageView announceImage;
    TextView announceText;
    public  static String image,announceTextData;
    public  static String id;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";



    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        image = intent.getStringExtra("image");
         id = intent.getStringExtra("announceId");



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_announcment);

        InterstitialAd();


        //finding View by ID:

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        announceImage = findViewById(R.id.fullAnnounceImage);
        announceText = findViewById(R.id.fullAnnounceText);


        //getting textdata

        database.getReference().child("Announcment").child("data").child("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot snapshot1:snapshot.getChildren()){

                    fullAnnounceModel model = snapshot1.getValue(fullAnnounceModel.class);
                    announceTextData = model.getText();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //setting data

        Picasso.get().load(image).placeholder(R.drawable.mhtcetlogo).into(announceImage);
        announceText.setText(announceTextData);



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