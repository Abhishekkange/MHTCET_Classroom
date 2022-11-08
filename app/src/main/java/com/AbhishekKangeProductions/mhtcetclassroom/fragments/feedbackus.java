package com.AbhishekKangeProductions.mhtcetclassroom.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.MainActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.navigationDrawerActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

public class feedbackus extends AppCompatActivity {

    Button button;
    EditText feedbackText;
    FirebaseDatabase database;
    LinearLayout bannerAd;
    public  String GAME_ID = "4709585";
    public String BANNER_ID = "Banner_Android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackus);

        //finding Id's
        button = findViewById(R.id.feedbacksend);
        feedbackText = findViewById(R.id.editTextTextPersonName);
        database = FirebaseDatabase.getInstance();


        bannerAd = findViewById(R.id.Banner7);
        UnityAds.initialize(this,GAME_ID,false);
        BannerView view = new BannerView(this,BANNER_ID,new UnityBannerSize(100,100));
        view.load();
        bannerAd.addView(view);






        //onclick
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = feedbackText.getText().toString();
                database.getReference().child("FeedBacks").push().child("feedBack").setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void aVoid) {

                        feedbackText.setText("");
                        Toast.makeText(feedbackus.this, "FeedBack Accepted Succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(feedbackus.this, MainActivity.class);
                        startActivity(intent);

                    }
                });

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(feedbackus.this, navigationDrawerActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(feedbackus.this);
    }
}