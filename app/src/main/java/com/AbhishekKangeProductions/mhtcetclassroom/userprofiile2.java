package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class userprofiile2 extends AppCompatActivity {

    LinearLayout performanceBtn,downloadedBookBtn,leaderboardbtn,referAndEarnBtn,rateusBtn;
    Button viewUserProfileBtn;
    FirebaseDatabase database;
    ImageView profileImageShow;
    FirebaseAuth mAuth;


    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";



    @Override
    protected void onStart() {
        super.onStart();

        database.getReference().child("UserDetails").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userCredentials model = snapshot.getValue(userCredentials.class);
                String profilePic = model.getProfilePhoto();
                Picasso.get().load(profilePic).placeholder(R.drawable.profileicon).into(profileImageShow);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofiile2);

        //Finding View By ID

        performanceBtn = findViewById(R.id.performanceBtn);
        downloadedBookBtn = findViewById(R.id.downlaodedBooks);
        leaderboardbtn = findViewById(R.id.leaderBoardBtntw);
        referAndEarnBtn = findViewById(R.id.referAndEarnCardBtn);
        rateusBtn = findViewById(R.id.rateUscardBtn);
        viewUserProfileBtn = findViewById(R.id.viewProfileBtn);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        profileImageShow = findViewById(R.id.profileshow1234);





        //Adding On Click Listerners

        performanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        downloadedBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(userprofiile2.this,downloadedBooksActivity.class));
            }
        });
        rateusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(userprofiile2.this,rateUsActivity.class));

            }
        });
        leaderboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(userprofiile2.this,leaderboardActivity.class));

            }
        });
        referAndEarnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(userprofiile2.this,referAndEarnActivity.class));


            }
        });
        viewUserProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(userprofiile2.this,studentProfileActivity.class));


            }
        });









    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        Intent intent = new Intent(userprofiile2.this, navigationDrawerActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(userprofiile2.this);

    }
}