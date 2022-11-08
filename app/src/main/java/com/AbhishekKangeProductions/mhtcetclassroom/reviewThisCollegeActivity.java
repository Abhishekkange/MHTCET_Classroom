package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.collegeReviewPublicModel;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import static com.AbhishekKangeProductions.mhtcetclassroom.collegeReviewData.collegeName;

public class reviewThisCollegeActivity extends AppCompatActivity {

    EditText userName,Description;
    Button addReview;
    FirebaseDatabase database;


    @Override
    protected void onStart() {
        super.onStart();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_this_college);

        Intent intent = getIntent();
        String collegeNameHere = intent.getStringExtra("key");


        userName = findViewById(R.id.publicReviewUsername);
        Description = findViewById(R.id.publicReviewReview);
        addReview = findViewById(R.id.addReviewBtn);
        database = FirebaseDatabase.getInstance();


        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UserNameText = userName.getText().toString();
                String DescriptionText = Description.getText().toString();

                collegeReviewPublicModel model = new collegeReviewPublicModel(UserNameText,DescriptionText);

                database.getReference().child("collegePublicReviews").child(collegeNameHere).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(reviewThisCollegeActivity.this, "Review Added Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(reviewThisCollegeActivity.this,collegeReviewData.class);
                        intent1.putExtra("collegename",collegeName);
                        startActivity(intent1);
                        Animatoo.animateSlideDown(reviewThisCollegeActivity.this);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(reviewThisCollegeActivity.this, "Something Went wrong", Toast.LENGTH_SHORT).show();
                    }
                });





            }
        });








    }
}