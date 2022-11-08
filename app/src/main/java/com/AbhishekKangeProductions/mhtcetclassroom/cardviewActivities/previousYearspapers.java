package com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.userActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.PCB_previous_years;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.PCM_Previous_years_paper;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.newMainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.UnityAds;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class previousYearspapers extends AppCompatActivity {

    TextView pcbToggle,pcmToggle;
    FrameLayout frameLayout;
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


        //FRAGMNET REPLACEMENT
        PCM_Previous_years_paper PCM = new PCM_Previous_years_paper();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame1,PCM);
        transaction.commit();
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
                userActivity2.setActivity("Previous years papers Activity");
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
        setContentView(R.layout.activity_previous_yearspapers);

        InterstitialAd();

        // Finding ID's
        pcmToggle = findViewById(R.id.pcmToggle);
        pcbToggle = findViewById(R.id.pcbToggle);
        frameLayout = findViewById(R.id.frame1);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mycalendar = Calendar.getInstance();

        // OnClickListeners
        pcmToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pcmToggle.setBackgroundResource(R.drawable.pcmbackchange);
                pcbToggle.setBackgroundResource(R.drawable.pcmback);
                PCM_Previous_years_paper PCM = new PCM_Previous_years_paper();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame1,PCM);
                transaction.commit();


            }
        });
        pcbToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                PCB_previous_years PCB = new PCB_previous_years();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame1,PCB);
                transaction.commit();


                pcbToggle.setBackgroundResource(R.drawable.pcmbackchange);
                pcmToggle.setBackgroundResource(R.drawable.pcmback);



            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(previousYearspapers.this, newMainActivity.class);
        startActivity(intent);

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