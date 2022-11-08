package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.availableToChatAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.availableToChatModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.UnityAds;

import java.util.ArrayList;

public class availableToChat extends AppCompatActivity {

    RecyclerView availableToChatRv;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    ShimmerFrameLayout shimmerFrameLayout;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_to_chat);

        InterstitialAd();


        //findingViewById

        availableToChatRv = findViewById(R.id.availableChatRv);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();


        shimmerFrameLayout = findViewById(R.id.mShimmerViewContaineravailableToChat);
        shimmerFrameLayout.startShimmerAnimation();


        //ArrayList

        ArrayList<availableToChatModel> list = new ArrayList<>();
        availableToChatAdapter adapter = new availableToChatAdapter(list,getApplicationContext());

        availableToChatRv.setAdapter(adapter);

        //Adapter

        database.getReference().child("UserDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


            list.clear();
            for (DataSnapshot snapshot1:snapshot.getChildren()){


                availableToChatModel model = snapshot1.getValue(availableToChatModel.class);
                list.add(model);
                shimmerFrameLayout.setAlpha(0);
                shimmerFrameLayout.stopShimmerAnimation();

            }
            adapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        availableToChatRv.setLayoutManager(layoutManager);




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