package com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.doubtDiscussionAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.MainActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.doubtDiscussionModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.unity3d.ads.UnityAds;

import java.util.ArrayList;

public class doubtDiscussion extends AppCompatActivity {

   EditText DoubtHere;
   Button upload;
   RecyclerView doubtShowRv;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubt_discussion);

        //findView By Id

        DoubtHere = findViewById(R.id.enterDoubtHere);
        upload  = findViewById(R.id.doubtUploadBtn);
        doubtShowRv = findViewById(R.id.doubtShowRv);
        loadIntertetial();

        //RecyclerView Show Doubts

        doubtShowRv = findViewById(R.id.doubtShowRv);

        ArrayList<doubtDiscussionModel> list = new ArrayList<>();

        doubtDiscussionAdapter adapter = new doubtDiscussionAdapter(list,getApplicationContext());

        list.add(new doubtDiscussionModel("This is my question","abcd","Abhishek Kange","abc"));



        doubtShowRv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(doubtDiscussion.this);
        doubtShowRv.setLayoutManager(layoutManager);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        UnityAds.show(this,INTERSTRITIALID);


        Intent intent = new Intent(doubtDiscussion.this, MainActivity.class);
        startActivity(intent);


    }

    public void loadIntertetial() {

        if(UnityAds.isInitialized()){

            UnityAds.load(INTERSTRITIALID);


        }else{

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    UnityAds.load(INTERSTRITIALID);

                }
            },5000);

        }


    }
}