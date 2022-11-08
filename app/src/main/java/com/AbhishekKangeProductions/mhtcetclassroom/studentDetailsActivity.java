package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.studentDetailsModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.unity3d.ads.UnityAds;

public class studentDetailsActivity extends AppCompatActivity {

    EditText studentName,studentClass;
    Button Next;
    //public static String studentNameData;
   // public static  String studentClassData;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        InterstitialAd();

        //finding Id's

        studentName = findViewById(R.id.enterStudentName);
        studentClass = findViewById(R.id.enterStd);
        Next = findViewById(R.id.NextButtonForMain);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();



        //OnClickListeners

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String studentNameData = studentName.getText().toString();
                String studentClassData = studentClass.getText().toString();

                if(studentClassData.equals("") && studentNameData.equals("")){
                    Toast.makeText(studentDetailsActivity.this, "", Toast.LENGTH_SHORT).show();
                }
                else{

                    String userName = mAuth.getCurrentUser().getDisplayName();
                    String userPhotoDisplay = mAuth.getCurrentUser().getPhotoUrl().toString();
                    String userId = mAuth.getUid();
                    String userEmail = mAuth.getCurrentUser().getEmail();
                    String studentName = studentNameData;
                    String studentClass = studentClassData;
                    studentDetailsModel  model = new studentDetailsModel(userName,userPhotoDisplay,userId,userEmail,studentName,studentClass);
                    database.getReference().child("Student Details").child(mAuth.getUid()).child("1").setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {


                            startActivity(new Intent(studentDetailsActivity.this,MainActivity.class));



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(studentDetailsActivity.this, "Failed to login , Plz try Again", Toast.LENGTH_SHORT).show();

                        }
                    });



                }

            }
        });
    }
    private void getDetails(){

      



    }

    private void addDetailsToServer(){

       





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