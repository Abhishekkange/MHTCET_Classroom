package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

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
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.test;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.unity3d.ads.UnityAds;

import java.util.ArrayList;

public class dailyMockTestLayout extends AppCompatActivity {

    TextView option1,option2,option3,option4,nextBtn;
    ImageView question;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    FirebaseDatabase database;
    Button submit;
    public static String newscore;
    ArrayList<test> list;
    int index = 0;
    int score = 0;

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
        setContentView(R.layout.activity_daily_mock_test_layout);

        InterstitialAd();


        // getting intent data
        Intent intent = getIntent();
        String data = intent.getStringExtra("theTestNo");


        //Finding ID's

        question = findViewById(R.id.question2);
        option1 = findViewById(R.id.option12);
        option2 = findViewById(R.id.option22);
        option3 = findViewById(R.id.option32);
        option4 = findViewById(R.id.option42);
        nextBtn = findViewById(R.id.nextBtn2);
        submit = findViewById(R.id.submit2);
        database = FirebaseDatabase.getInstance();


        //finding ID's of QuestionButton
        t1= findViewById(R.id.t1);
        t2= findViewById(R.id.t2);
        t3= findViewById(R.id.t3);
        t4= findViewById(R.id.t4);
        t5= findViewById(R.id.t5);
        t6= findViewById(R.id.t6);
        t7= findViewById(R.id.t7);
        t8= findViewById(R.id.t8);

        //creating arrayList
        list = new ArrayList<>();


        database.getReference("DailyMockTest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot != null) {

                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        test model = snapshot1.getValue(test.class);
                        list.add(model);


                    }
                }else {
                    Toast.makeText(dailyMockTestLayout.this, "No Tests Available", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(dailyMockTestLayout.this, "There is an Error", Toast.LENGTH_SHORT).show();

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
                AlertDialog.Builder builder = new AlertDialog.Builder(dailyMockTestLayout.this);
                builder.setMessage("Do you want to submit test ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent1 = new Intent(dailyMockTestLayout.this, dailyMocktestResultActivity.class);
                              newscore = Integer.toString(score);
                                intent1.putExtra("myscore",newscore);
                                startActivity(intent1);

                            }
                        }).setNegativeButton("cancel",null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });

























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
            Intent intent1 = new Intent(dailyMockTestLayout.this, dailyMocktestResultActivity.class);
             newscore = Integer.toString(score);
            intent1.putExtra("myscore",newscore);
            startActivity(intent1);
        }




    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(dailyMockTestLayout.this);
        builder.setMessage("Do you want to submit test ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent1 = new Intent(dailyMockTestLayout.this, dailyMocktestResultActivity.class);
                        intent1.putExtra("myscore",newscore);
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