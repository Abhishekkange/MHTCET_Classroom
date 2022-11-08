package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class forgotPassword extends AppCompatActivity {

    EditText gmail,mobileNo;
    Button submit;
    FirebaseDatabase database;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //finding ID's

        gmail = findViewById(R.id.enterGmailForgot);
        mobileNo = findViewById(R.id.enterMobileForgot);
        submit = findViewById(R.id.forgotpasswordSubmit);
        database = FirebaseDatabase.getInstance();

        //adding On Click listener

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // database.getReference().child("Forgot Password Queries").push().setValue(model);
            }
        });


    }
}