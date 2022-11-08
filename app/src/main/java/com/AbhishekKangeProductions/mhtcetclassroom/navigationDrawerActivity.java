package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.announcment;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.newMainActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.unity3d.ads.UnityAds;

public class navigationDrawerActivity extends AppCompatActivity {

    TextView notificationBtn,aboutus,feedbackus,logoutBtn;
    ImageView setprofileImagehere;
    Button viewProfileBtn;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";

    @Override
    protected void onStart() {
        super.onStart();

        //setting Profile Image here

        database.getReference().child("UserDetails").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userCredentials model = snapshot.getValue(userCredentials.class);
                String profilePic = model.getProfilePhoto();
                Picasso.get().load(profilePic).placeholder(R.drawable.profileicon).into(setprofileImagehere);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        database.getReference().child("UserDetails").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot snapshot1:snapshot.getChildren()){
//
//                    userCredentials model = snapshot1.getValue(userCredentials.class);
//                    if(model.getProfilePhoto().toString() !=null){
//
//                        Picasso.get().load(model.getProfilePhoto()).placeholder(R.drawable.profileicon).into(setprofileImagehere);
//
//                    }
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        //finding View by ID
        notificationBtn = findViewById(R.id.notificationNavBtn);
        aboutus = findViewById(R.id.aboutDeveloperNavBtn);
        feedbackus = findViewById(R.id.feedbackNavBtn);
        setprofileImagehere = findViewById(R.id.setProfileHereNavigation);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        viewProfileBtn = findViewById(R.id.navigationUserProfile);
        logoutBtn = findViewById(R.id.logoutNavBtn);






        //On click Listeners

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(navigationDrawerActivity.this, announcment.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(navigationDrawerActivity.this);

            }
        });
//        aboutus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent  = new Intent(navigationDrawerActivity.this,aboutDeveloper.class);
//                startActivity(intent);
//
//
//
//
//            }
//        });
        feedbackus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(navigationDrawerActivity.this, com.AbhishekKangeProductions.mhtcetclassroom.fragments.feedbackus.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(navigationDrawerActivity.this);


            }
        });
        viewProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(navigationDrawerActivity.this, userprofiile2.class);
                startActivity(intent);
                Animatoo.animateSlideRight(navigationDrawerActivity.this);

            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(navigationDrawerActivity.this,signInActivity.class);
                startActivity(intent);
                Animatoo.animateFade(navigationDrawerActivity.this);
                finish();


            }
        });






    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(navigationDrawerActivity.this, newMainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(navigationDrawerActivity.this);

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