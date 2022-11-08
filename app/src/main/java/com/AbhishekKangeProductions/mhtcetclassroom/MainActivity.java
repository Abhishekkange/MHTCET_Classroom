package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.activeuserData;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.personalNotificationModel;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.CollegeReviews;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.VideoCallingActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.announcment;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.chattingActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.formulaSheetActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.previousYearspapers;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.buybooksActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.UnityAds;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";
    public static String personalNoti;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    CardView previousPaper,formulaSheetCardView;
    CardView testSeries,buyBooks;
    CardView practicePapers;
    CardView announce;
    CardView CollegeReviews;
    CardView doubtDiscussion;
    CardView Handwritten,socialMedia;
    ImageView chattingIcon;
    ImageView livegrpStudy;
    ImageView aboutDev,profile_image;
    TextView welcomeText,helloText,freeCallBtn,personalNotification;
    FirebaseDatabase database;
    ImageView userProfileShowBtn,hambourgBtn;
    public  String studentNameHere;
    FirebaseAuth mAuth;
    private AdView mAdView;
    public  static String username;
    private InterstitialAd mInterstitialAd;
    private FirebaseAnalytics mFirebaseAnalytics;
    private LinearLayout  bannerAd;

    // App ID - ca-app-pub-7496362748387980~1940185949


    @Override
    protected void onStart() {
        super.onStart();

        database.getReference().child("UserDetails").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

              personalNotificationModel model3 = snapshot.getValue(personalNotificationModel.class);
              personalNoti = model3.getPersonalNotification();
              personalNotification.setText(personalNoti);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("UserDetails").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userCredentials credentials = snapshot.getValue(userCredentials.class);
                username = credentials.getName();
                helloText.setText("Hello "+username);



                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String date = dateFormat.format(calendar.getTime());
                String mydate = date.toString();

                //Time setting

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                String time = sdf.format(calendar.getTime());

                //model
                activeuserData model234 = new activeuserData();
                model234.setTime(time);
                model234.setUserName(username);

                database.getReference().child("ActiveUsers").child(mydate.toString()).push().setValue(model234).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                        Toast.makeText(MainActivity.this, "Welcome "+username, Toast.LENGTH_SHORT).show();
                    }
                });

                userActivity userActivity2 = new userActivity();
                userActivity2.setTime(time);
                userActivity2.setUsername(username);
                userActivity2.setActivity("MainActivity");
                database.getReference().child("UserActivity").child(mydate).child(mAuth.getCurrentUser().getUid()).push().setValue(userActivity2);





            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });




//



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //UNITY ADS
        UnityAds.initialize(this,GAMEID,false);

        // Initializing Mobile Ads

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //Intertitial Ad
//        setmInterstitialAd();

        // Finding ID's
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        previousPaper = findViewById(R.id.previousPaper);
        testSeries = findViewById(R.id.testSeries);
        practicePapers = findViewById(R.id.practicePaper);
        announce = findViewById(R.id.announce);
        CollegeReviews = findViewById(R.id.collegeReviews);
        Handwritten = findViewById(R.id.handwritten);
        chattingIcon = findViewById(R.id.chattingIconHere);
        database = FirebaseDatabase.getInstance();
        mAuth  = FirebaseAuth.getInstance();
        freeCallBtn = findViewById(R.id.freeCallBtn);
        buyBooks = findViewById(R.id.buyBooksCard);
        formulaSheetCardView = findViewById(R.id.formulaSheetCardView);
        doubtDiscussion = findViewById(R.id.doubtDiscussion);
        livegrpStudy = findViewById(R.id.livegrpstudy);
        hambourgBtn = findViewById(R.id.hambourgBtn);
        helloText = findViewById(R.id.helloToUser);
        personalNotification = findViewById(R.id.personalNotification);


        calendar = Calendar.getInstance();




        //FireBase Analytics

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "abc");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "abc");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        // Adding OnClickListeners



        freeCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,freeCallActivity.class);
                startActivity(intent);
                Animatoo.animateDiagonal(MainActivity.this);



            }
        });

        formulaSheetCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, formulaSheetActivity.class);
                startActivity(intent);
                Animatoo.animateFade(MainActivity.this);




            }
        });

        buyBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(MainActivity.this, buybooksActivity.class);
                startActivity(intent);
            }
        });

        hambourgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,navigationDrawerActivity.class);
                startActivity(intent);
                Animatoo.animateSlideRight(MainActivity.this);

            }
        });



        livegrpStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, VideoCallingActivity.class);
                startActivity(intent);

            }
        });
        previousPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Intent intent = new Intent(MainActivity.this, previousYearspapers.class);
                    startActivity(intent);
                    Animatoo.animateSlideUp(MainActivity.this);

                }

            }
        });

        testSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, listOfTests.class);
                startActivity(intent);
                Animatoo.animateSlideUp(MainActivity.this);

            }
        });

        practicePapers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Intent intent = new Intent(MainActivity.this, com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.practicePapers.class);
                    startActivity(intent);
                    Animatoo.animateSlideUp(MainActivity.this);
                }


                Intent intent = new Intent(MainActivity.this, com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.practicePapers.class);
                startActivity(intent);


            }
        });


        CollegeReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Intent intent = new Intent(MainActivity.this, CollegeReviews.class);
                    startActivity(intent);
                    Animatoo.animateSlideUp(MainActivity.this);

                }


                Intent intent = new Intent(MainActivity.this, com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.CollegeReviews.class);
                startActivity(intent);


            }
        });

        Handwritten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, optiongenerate.class);
                startActivity(intent);
                Animatoo.animateSlideUp(MainActivity.this);


            }
        });

        chattingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, chattingActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);

            }
        });

        announce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,announcment.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);
                
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
//    public void setmInterstitialAd(){
//        // Ad code here
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        InterstitialAd.load(this,"ca-app-pub-7496362748387980/6755616081", adRequest,
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                        mInterstitialAd = interstitialAd;
//
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        mInterstitialAd = null;
//
//                    }
//                });
//    }
}