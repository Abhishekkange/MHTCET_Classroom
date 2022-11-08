package com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.userActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.collegeListFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.UnityAds;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CollegeReviews extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth mAuth;
    public static String username;
    public Calendar mycalendar;
    private SimpleDateFormat dateFormat;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onStart() {
        super.onStart();

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
                userActivity2.setActivity("College List ");
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
        setContentView(R.layout.activity_college_reviews);

        InterstitialAd();



        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mycalendar = Calendar.getInstance();






        // Fragment Transaction

        collegeListFragment fragment = new collegeListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame31,fragment);
        transaction.commit();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();




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