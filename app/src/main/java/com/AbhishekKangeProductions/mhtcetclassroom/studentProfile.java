package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.studentDetailsModel;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.generateMoreCoins;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.unity3d.ads.UnityAds;


public class studentProfile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    ImageView profileImageShow;
    Button leaderboard;
    ImageView addExtraCoinsBtn;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";

    @Override
    protected void onStart() {
        super.onStart();

        database.getReference().child("Student Details").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1:snapshot.getChildren()){

                    studentDetailsModel model = snapshot1.getValue(studentDetailsModel.class);
                    Picasso.get().load(model.getUserPhotoDisplay().toString()).placeholder(R.drawable.profileicon).into(profileImageShow);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        InterstitialAd();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        profileImageShow = findViewById(R.id.profileImageShow);
        leaderboard = findViewById(R.id.leaderboardBtn);
        addExtraCoinsBtn = findViewById(R.id.addExtraCoinsBtn);


        //OnClick

        addExtraCoinsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(studentProfile.this, generateMoreCoins.class);
                startActivity(intent);


            }
        });

        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(studentProfile.this,leaderboardActivity.class));
                Animatoo.animateFade(studentProfile.this);
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(studentProfile.this,MainActivity.class));
        Animatoo.animateSlideRight(studentProfile.this);

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