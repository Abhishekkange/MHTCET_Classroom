package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.freeCallModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class freeCallActivity extends AppCompatActivity {

    Button freeCallBtn;
    EditText name,mobileNo,comment;
    TimePicker timepick;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    ProgressDialog dialog;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_call);

        //finding View By IDs
        freeCallBtn = findViewById(R.id.bookFreeCallBtn);
        name = findViewById(R.id.freeCallName);
        mobileNo = findViewById(R.id.freeCallMobileNo);
        comment = findViewById(R.id.freeCallComments);
        timepick = findViewById(R.id.freeCallTimePicker);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //OnClick

        freeCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.equals("") || mobileNo.equals("") || comment.equals("") ){

                    Toast.makeText(freeCallActivity.this, "Please Fill Details", Toast.LENGTH_SHORT).show();

                }else{

                  int hour =   timepick.getCurrentHour();
                  int min = timepick.getCurrentMinute();
                  String time = Integer.toString(hour)+":"+ Integer.toString(min);
                  freeCallModel model = new freeCallModel();
                  model.setName(name.getText().toString());
                  model.setMobileNo(mobileNo.getText().toString());
                  model.setComment(comment.getText().toString());
                  model.setTime(time);
                  model.setUserId(mAuth.getCurrentUser().getUid().toString());
                  database.getReference().child("FreeCalls").push().child(mAuth.getCurrentUser().getUid()).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                          Toast.makeText(freeCallActivity.this, "Call Booked...will catch u soon", Toast.LENGTH_SHORT).show();

                          Toast.makeText(freeCallActivity.this, "Failed to Book Call ", Toast.LENGTH_SHORT).show();
                          name.setText("");
                          comment.setText("");
                          mobileNo.setText("");

                      }
                  }).addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {



                      }
                  });


                }
            }
        });

    }



}