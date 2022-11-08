package com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.newMainActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.newFragments.newAnnounceFrag;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.unity3d.ads.UnityAds;

public class announcment extends AppCompatActivity {

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";



    @Override
    protected void onStart() {
        super.onStart();

        InterstitialAd();

//        database.getReference().child("UserDetails").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                userCredentials credentials = snapshot.getValue(userCredentials.class);
//                username = credentials.getName();
//
//
//                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//                String date = dateFormat.format(mycalendar.getTime());
//                String mydate = date.toString();
//
//                //Time setting
//
//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
//                String time = sdf.format(mycalendar.getTime());
//
//                userActivity userActivity2 = new userActivity();
//                userActivity2.setTime(time);
//                userActivity2.setUsername(username);
//                userActivity2.setActivity("Announcment Activity");
//                database.getReference().child("UserActivity").child(mydate).child(mAuth.getCurrentUser().getUid()).push().setValue(userActivity2);
//
//
//
//
//
//            }
//
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//
//            }
//        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcment);

//
        newAnnounceFrag frag = new newAnnounceFrag();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tytyty,frag);
        transaction.commit();








    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(announcment.this, newMainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(announcment.this);

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