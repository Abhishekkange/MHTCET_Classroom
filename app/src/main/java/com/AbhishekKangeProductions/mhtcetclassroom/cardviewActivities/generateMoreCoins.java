package com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.AbhishekKangeProductions.mhtcetclassroom.R;

public class generateMoreCoins extends AppCompatActivity {

    Button watchAdsBtn;
    String rewardedAdId = "Rewarded_Android";
    public  String GAME_ID = "4709585";









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_more_coins);



        //Listener



        //Finding View by Id

        watchAdsBtn = findViewById(R.id.watchVideoAndEarn);
        watchAdsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });










    }






}
