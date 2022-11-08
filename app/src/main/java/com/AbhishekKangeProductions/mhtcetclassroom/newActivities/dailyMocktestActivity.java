package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.dailyMockInstructionsModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newFragments.dailyMockTestLeaderBoard;
import com.AbhishekKangeProductions.mhtcetclassroom.newFragments.dailyMocktestFrag;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.UnityAds;

public class dailyMocktestActivity extends AppCompatActivity {

    Button  liveTest,leaderBoard;
    TextView nextTest;
    FirebaseDatabase database;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";

    @Override
    protected void onStart() {
        super.onStart();

        dailyMocktestFrag mocktestFrag = new dailyMocktestFrag();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.dailyMockTestFrame,mocktestFrag);
        transaction.commit();
        database.getReference().child("DailyMockTestInstructions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1:snapshot.getChildren()){


                    dailyMockInstructionsModel model = snapshot1.getValue(dailyMockInstructionsModel.class);
                    //Picasso.get().load(model.getImage()).placeholder(R.drawable.dailymocktestloading).into(instructionImage);
                    nextTest.setText(model.getNext());

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
        setContentView(R.layout.activity_daily_mocktest);

        InterstitialAd();

        //finding ID's

        liveTest = findViewById(R.id.livetestFragBtn);
        leaderBoard = findViewById(R.id.liveTestLeaderboardFragBtn);
        nextTest = findViewById(R.id.nextTestTitle);
        database = FirebaseDatabase.getInstance();



        liveTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dailyMocktestFrag mocktestFrag = new dailyMocktestFrag();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.dailyMockTestFrame,mocktestFrag);
                transaction.commit();

            }
        });

        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dailyMockTestLeaderBoard leaderBoard = new dailyMockTestLeaderBoard();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.dailyMockTestFrame,leaderBoard);
                transaction.commit();



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