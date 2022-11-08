package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.dailyMockInstructionsModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.UnityAds;

public class dataCollection1 extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth mAuth;
    TextView question;
    EditText answer;
    Button startTest;
    public  static String data;
    public  static String tag;
    public  static  String questionText= "";

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";

    @Override
    protected void onStart() {
        super.onStart();

        //getting Intent Data
        Intent intent = getIntent();
        data = intent.getStringExtra("theTestNo");



        //getting Data from Firebase Server;
        database.getReference().child("DailyMockTestInstructions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1:snapshot.getChildren()){


                    dailyMockInstructionsModel model = snapshot1.getValue(dailyMockInstructionsModel.class);
                    question.setText(model.getQuestion());
                    tag = model.getTag();
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
        setContentView(R.layout.activity_data_collection1);

        //finding ID's,
        question = findViewById(R.id.dataCollection1Question);
        answer = findViewById(R.id.dataCollectionAnswer);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        startTest = findViewById(R.id.letsStartDailyMocktestBtn);

        //addding OnClick
        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                 if(answer.getText().toString().equals("")) {

                    Toast.makeText(dataCollection1.this, "Please Answer the Question to continue", Toast.LENGTH_SHORT).show();
                }else {

                    String answerOfdata = answer.getText().toString();
                    database.getReference().child("UserDetails").child(mAuth.getCurrentUser().getUid()).child("Data").child(tag).setValue(answerOfdata).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Intent intent = new Intent(dataCollection1.this,dailyMockTestLayout.class);
                            intent.putExtra("theTestNo",data);
                            startActivity(intent);
                            Animatoo.animateSlideLeft(dataCollection1.this);

                        }
                    });
                }

            }
        });






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