package com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.test;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.result;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.unity3d.ads.UnityAds;

import java.util.ArrayList;

import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

public class testLayout extends AppCompatActivity {

    TextView option1,option2,option3,option4,nextBtn;
    ImageView question;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    FirebaseDatabase database;
    Button submit;
    ArrayList<test> list;
    int index = 0;
    int score = 0;
    CircularView circularView;
    public LinearLayout bannerAd2;
    private AdView mAdView;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "Press Next Button ", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_layout);

        InterstitialAd();

        // getting intent data
        Intent intent = getIntent();
        String data = intent.getStringExtra("theTestNo");






        //Finding ID's

        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);
        mAdView = findViewById(R.id.adViewtestlay);
        submit = findViewById(R.id.submit);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });




        //finding ID's of QuestionButton
        t1= findViewById(R.id.t1);
        t2= findViewById(R.id.t2);
        t3= findViewById(R.id.t3);
        t4= findViewById(R.id.t4);
        t5= findViewById(R.id.t5);
        t6= findViewById(R.id.t6);
        t7= findViewById(R.id.t7);
        t8= findViewById(R.id.t8);






        database = FirebaseDatabase.getInstance();



       list = new ArrayList<>();


       database.getReference("Tests").child("PCM").child(data).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if (snapshot != null) {

                   for(DataSnapshot snapshot1:snapshot.getChildren()){
                       test model = snapshot1.getValue(test.class);
                       list.add(model);


                   }
               }else {
                   Toast.makeText(testLayout.this, "No Tests Available", Toast.LENGTH_SHORT).show();
               }


           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

               Toast.makeText(testLayout.this, "There is an Error", Toast.LENGTH_SHORT).show();

           }
       });

       // OnclickListeners

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                setNextQuestion();



            }
        });
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option1.setBackgroundResource(R.drawable.optionbackchange);
                option2.setBackgroundResource(R.drawable.optionback);
                option3.setBackgroundResource(R.drawable.optionback);
                option4.setBackgroundResource(R.drawable.optionback);
                String selectedAnswer = option1.getText().toString();
                test myquestion = list.get(index);
                String answer =  myquestion.getAnswer();
                if(selectedAnswer.equals(answer)){
                    if(index>100){
                        score = score+2;
                    }
                    else{
                        score = score+1;
                    }

                }else {

                }

            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option2.setBackgroundResource(R.drawable.optionbackchange);
                option1.setBackgroundResource(R.drawable.optionback);
                option3.setBackgroundResource(R.drawable.optionback);
                option4.setBackgroundResource(R.drawable.optionback);
                String selectedAnswer = option2.getText().toString();
                test myquestion = list.get(index);
                String answer =  myquestion.getAnswer();
                if(selectedAnswer.equals(answer)){
                    if(selectedAnswer.equals(answer)){
                        if(index>100){
                            score = score+2;
                        }
                        else{
                            score = score+1;
                        }
                    }else {

                    }
                }

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                option3.setBackgroundResource(R.drawable.optionbackchange);
                option1.setBackgroundResource(R.drawable.optionback);
                option2.setBackgroundResource(R.drawable.optionback);
                option4.setBackgroundResource(R.drawable.optionback);
                String selectedAnswer = option3.getText().toString();
                test myquestion = list.get(index);
                String answer =  myquestion.getAnswer();

                    if(selectedAnswer.equals(answer)){
                        if(index>100){
                            score = score+2;
                        }
                        else{
                            score = score+1;
                        }
                    }else {

                    }

                }

        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option1.setBackgroundResource(R.drawable.optionback);
                option2.setBackgroundResource(R.drawable.optionback);
                option3.setBackgroundResource(R.drawable.optionback);
                option4.setBackgroundResource(R.drawable.optionbackchange);
                String selectedAnswer = option4.getText().toString();
                test myquestion = list.get(index);
                String answer =  myquestion.getAnswer();
                if(selectedAnswer.equals(answer)){
                    if(index>100){
                        score = score+2;
                    }
                    else{
                        score = score+1;
                    }
                }else {

                }


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(testLayout.this);
                builder.setMessage("Do you want to submit test ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent1 = new Intent(testLayout.this, result.class);
                                String newScore = Integer.toString(score);
                               intent1.putExtra("myscore",newScore);
                                startActivity(intent1);

                            }
                        }).setNegativeButton("cancel",null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
               // Toast.makeText(testLayout.this, Integer.toString(score), Toast.LENGTH_SHORT).show();

            }
        });





        //QuestionButton OnClickListeners


















    }

   public void setNextQuestion(){
        if(index<list.size()){
            test myquestion = list.get(index);
            //question.set
            Picasso.get().load(myquestion.getQuestion()).placeholder(R.drawable.loading).into(question);
            option1.setText(myquestion.getOption1());
            option2.setText(myquestion.getOption2());
            option3.setText(myquestion.getOption3());
            option4.setText(myquestion.getOption4());
            option1.setBackgroundResource(R.drawable.optionback);
            option2.setBackgroundResource(R.drawable.optionback);
            option3.setBackgroundResource(R.drawable.optionback);
            option4.setBackgroundResource(R.drawable.optionback);

        }
        else {
            Intent intent1 = new Intent(testLayout.this, result.class);
            String newScore = Integer.toString(score);
            intent1.putExtra("myscore",newScore);
            startActivity(intent1);
        }




    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(testLayout.this);
        builder.setMessage("Do you want to submit test ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent1 = new Intent(testLayout.this, result.class);
                        //  intent1.putExtra()
                        startActivity(intent1);

                    }
                }).setNegativeButton("cancel",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        // Toast.makeText(testLayout.this, Integer.toString(score), Toast.LENGTH_SHORT).show();






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