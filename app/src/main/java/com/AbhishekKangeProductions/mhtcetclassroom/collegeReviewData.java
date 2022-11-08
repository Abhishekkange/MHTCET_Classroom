package com.AbhishekKangeProductions.mhtcetclassroom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.collegeReviewPublicAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.collegeReviewDataModel;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.collegeReviewPublicModel;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.unity3d.ads.UnityAds;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class collegeReviewData extends AppCompatActivity {

    ImageView collegeImageD,overallCollegeRating,facultyRating,infrastructureRating;
    TextView  collegeNameD,closingRank,fees,avgCtc,ranking,location,campusSize,description;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    public static String username;
    public Calendar mycalendar;
    private SimpleDateFormat dateFormat;
    public static String collegeName;
    RecyclerView recyclerView;
    Button reviewThisCollege;
    public String API = "AIzaSyDjdKGfZRkpDvyNjMPAndjpXV1hNtBA49E";
    YouTubePlayerView youTubePlayerView;
    AdView mAdView;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";










    @Override
    protected void onStart() {
        super.onStart();

        //getting Data from Intent
        Intent intent = getIntent();
        String data  = intent.getStringExtra("collegename");
        collegeName = data;
        collegeNameD.setText(collegeName);
        database = FirebaseDatabase.getInstance();

        database.getReference().child("UserDetails").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userCredentials credentials = snapshot.getValue(userCredentials.class);
                username = credentials.getName();


                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String date = dateFormat.format(mycalendar.getTime());
                String mydate = date.toString();

                //Time setting

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                String time = sdf.format(mycalendar.getTime());

                userActivity userActivity2 = new userActivity();
                userActivity2.setTime(time);
                userActivity2.setUsername(username);
                userActivity2.setActivity("College Review : collegeName = "+collegeName);
                database.getReference().child("UserActivity").child(mydate).child(mAuth.getCurrentUser().getUid()).push().setValue(userActivity2);





            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


        //youtube player

        //setting data

        database.getReference().child("collegeReviews").child("collegeData").child(collegeName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1:snapshot.getChildren()){

                    collegeReviewDataModel model = snapshot1.getValue(collegeReviewDataModel.class);
                    closingRank.setText(model.getClosingRank());
                    avgCtc.setText(model.getAvgPackage());
                    fees.setText(model.getFees());
                    ranking.setText(model.getRanking());
                    location.setText(model.getLocation());
                    description.setText(model.getDescription());
                    campusSize.setText(model.getCampusSize());
                    Picasso.get().load(model.getImage()).placeholder(R.drawable.loading).into(collegeImageD);
                    Picasso.get().load(model.getInfrastructurerating()).placeholder(R.drawable.loading_icon).into(infrastructureRating);
                    Picasso.get().load(model.getFacultyRating()).placeholder(R.drawable.loading_icon).into(facultyRating);
                    Picasso.get().load(model.getOverallRating()).placeholder(R.drawable.loading_icon).into(overallCollegeRating);

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
        setContentView(R.layout.activity_college_review_data);

        InterstitialAd();


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });




        //finding ID's here

        collegeImageD = findViewById(R.id.collegeImageD);
        collegeNameD = findViewById(R.id.collegeNameD);
        closingRank = findViewById(R.id.closingRank);
        fees = findViewById(R.id.fees);
        avgCtc = findViewById(R.id.averagePackage);
        ranking = findViewById(R.id.ranking);
        recyclerView = findViewById(R.id.studentFeed);
        reviewThisCollege = findViewById(R.id.revirewCollegeBtn);
        campusSize = findViewById(R.id.campusSize);
        location = findViewById(R.id.Location);
        description = findViewById(R.id.collegeDescription);
        mAdView = findViewById(R.id.adViewrev1);
        overallCollegeRating = findViewById(R.id.overallCollegerating);
        facultyRating = findViewById(R.id.facultyRating);
        infrastructureRating = findViewById(R.id.infrastructureRating);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mycalendar = Calendar.getInstance();


        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });


        //Adding On click

        reviewThisCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(collegeReviewData.this,reviewThisCollegeActivity.class);
                intent.putExtra("key",collegeName);
                startActivity(intent);
                Animatoo.animateWindmill(collegeReviewData.this);

            }
        });

        ArrayList<collegeReviewPublicModel> list = new ArrayList<>();
        collegeReviewPublicAdapter adapter2 = new collegeReviewPublicAdapter(list,getApplicationContext());

        recyclerView.setAdapter(adapter2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);






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