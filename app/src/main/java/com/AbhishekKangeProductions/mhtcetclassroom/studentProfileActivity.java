package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.myProfileShowAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.myProfileShowModel;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.unity3d.ads.UnityAds;

import java.util.ArrayList;

public class studentProfileActivity extends AppCompatActivity {

    ImageView myprofileSectionPhoto;
    RecyclerView myProfileRv;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

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

                userCredentials credentials = new userCredentials();
                Picasso.get().load(credentials.getProfilePhoto()).placeholder(R.drawable.profileicon).into(myprofileSectionPhoto);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile2);

        InterstitialAd();

        //finding ID's
        myprofileSectionPhoto = findViewById(R.id.myprofileSectionPhoto);
        myProfileRv = findViewById(R.id.myPostsRv);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // ArrayList

        ArrayList<myProfileShowModel> list = new ArrayList<>();

        myProfileShowAdapter adapter = new myProfileShowAdapter(list,getApplicationContext());

        //Adding Values to List

        list.add(new myProfileShowModel("abc"));
        list.add(new myProfileShowModel("abc"));
        list.add(new myProfileShowModel("abc"));
        list.add(new myProfileShowModel("abc"));
        list.add(new myProfileShowModel("abc"));


        myProfileRv.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(studentProfileActivity.this,3);
        myProfileRv.setLayoutManager(layoutManager);


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