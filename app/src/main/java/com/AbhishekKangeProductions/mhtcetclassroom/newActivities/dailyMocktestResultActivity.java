package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.dailyMockInstructionsModel;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.dailyMockTestResultModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.UnityAds;

import java.util.Date;

public class dailyMocktestResultActivity extends AppCompatActivity {


    TextView score,xpGained;
    Button backToLeaderBoard,instaBtn;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    public static String testScore;
    public static String url;
    public int xpPerAnswer = 5;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";

    @Override
    protected void onStart() {
        super.onStart();

        //getting Today's Date

        Date d = new Date();
        CharSequence todaysDate = DateFormat.format("MMMM d, yyyy ", d.getTime());


        //getting Intent data
        Intent intent = getIntent();
        testScore = intent.getStringExtra("myscore");

        dailyMockTestResultModel model = new dailyMockTestResultModel();
        model.setDate(todaysDate.toString());
        model.setScore(testScore);

        database.getReference().child("UserDetails").child(mAuth.getCurrentUser().getUid()).child("DailyMockTestReults").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                score.setText(testScore+"/10");


            }
        });

        database.getReference().child("DailyMockTestInstructions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1:snapshot.getChildren()){


                    dailyMockInstructionsModel model = snapshot1.getValue(dailyMockInstructionsModel.class);
                    url = model.getUrl();
                    
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_mocktest_result);

        InterstitialAd();

        //finding ID's
        backToLeaderBoard = findViewById(R.id.backToLeaderBoard);
        instaBtn = findViewById(R.id.instaBtn);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        score = findViewById(R.id.dailyMockTestScore);
        xpGained = findViewById(R.id.xpGainedText);

//
//      int totalXp = xpPerAnswer +Integer.parseInt(testScore);
//      String sTotalXp = Integer.toString(totalXp);
//      xpGained.setText("XP Gained : "+sTotalXp);
//





        //adding On click Listeners

        instaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }catch (Exception e){

                    Toast.makeText(dailyMocktestResultActivity.this, "Solution Not released..Plz wait", Toast.LENGTH_SHORT).show();
                }


            }
        });
        backToLeaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(dailyMocktestResultActivity.this,dailyMocktestActivity.class);
                startActivity(intent);
                Animatoo.animateSlideRight(dailyMocktestResultActivity.this);


            }
        });







    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        AlertDialog.Builder builder = new AlertDialog.Builder(dailyMocktestResultActivity.this);
        builder.setMessage("Exit to Home")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent1 = new Intent(dailyMocktestResultActivity.this,newMainActivity.class);
                        startActivity(intent1);

                    }
                }).setNegativeButton("cancel",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

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