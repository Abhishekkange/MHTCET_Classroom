package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.activeuserData;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.collegeListFragment;
import com.AbhishekKangeProductions.mhtcetclassroom.navigationDrawerActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.newFragments.homeFragment;
import com.AbhishekKangeProductions.mhtcetclassroom.newFragments.leaderboardFrag;
import com.AbhishekKangeProductions.mhtcetclassroom.newFragments.newFragProfile;
import com.AbhishekKangeProductions.mhtcetclassroom.newFragments.newMockTestFrag;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.UnityAds;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class newMainActivity extends AppCompatActivity {

    ImageView home,test,collegereviews,leaderBoard,profile,addPost,messageNav;
    CardView hambourgIcon;
    TextView helloText;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    public String username;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;


    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onStart() {
        super.onStart();

        //setting Fragment Home
        homeFragment homeFrag = new homeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.newMainActivityFrame,homeFrag);
        transaction.commit();

        //getting UserName
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



                        Toast.makeText(newMainActivity.this, "Welcome "+username, Toast.LENGTH_SHORT).show();
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



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        InterstitialAd();

        UnityAds.initialize(this,GAMEID,false);

        //Find View By Id
        home = findViewById(R.id.newHomeActivity);
        test = findViewById(R.id.newTest);
        collegereviews = findViewById(R.id.searchBtn);
        leaderBoard = findViewById(R.id.learerBoardNav);
        hambourgIcon = findViewById(R.id.hamburgerIconNew);
        profile = findViewById(R.id.profileNav);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        helloText = findViewById(R.id.helloText);
        calendar = Calendar.getInstance();
        messageNav = findViewById(R.id.messagesHere);
        addPost = findViewById(R.id.addPostNav);
















        //Adding OnClickListerner
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeFragment homeFrag = new homeFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.newMainActivityFrame,homeFrag);
                transaction.commit();


            }
        });
        hambourgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(newMainActivity.this, navigationDrawerActivity.class);
                startActivity(intent);
                Animatoo.animateSlideRight(newMainActivity.this);


            }
        });

        messageNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(newMainActivity.this,newChattingActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(newMainActivity.this);
            }
        });

        collegereviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeFragment homeFrag = new homeFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.newMainActivityFrame,homeFrag);
                transaction.commit();




            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newFragProfile frag = new newFragProfile();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.newMainActivityFrame,frag);
                transaction.commit();




            }
        });

        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                leaderboardFrag frag = new leaderboardFrag();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.newMainActivityFrame,frag);
                transaction.commit();

            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                newMockTestFrag frag  = new newMockTestFrag();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.newMainActivityFrame,frag);
                transaction.commit();

            }
        });

        collegereviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                collegeListFragment frag = new collegeListFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.newMainActivityFrame,frag);
                transaction.commit();

            }
        });

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //intent
                Intent intent = new Intent(newMainActivity.this,createPostActivity.class);
                startActivity(intent);
                Animatoo.animateSlideRight(newMainActivity.this);

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