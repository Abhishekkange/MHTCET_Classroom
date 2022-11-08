package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class updateYourProfile extends AppCompatActivity {

    EditText enterName,enterAge,enterGender,enterAbout,enterBirthday,enterMobileNo;
    Button updateProfile;
    FirebaseAuth mAuth;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_your_profile);

        //finding ID's

        enterAbout = findViewById(R.id.updateInfo);
        enterAge = findViewById(R.id.updateAge);
        enterMobileNo = findViewById(R.id.updateMobileNumber);
        enterGender = findViewById(R.id.updateGender);
        enterBirthday = findViewById(R.id.updateBirthday);
        enterName = findViewById(R.id.updateBirthday);
        updateProfile = findViewById(R.id.button3);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();




        //Adding On Click Listeners

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(enterAbout.equals("")||enterName.equals("")||enterMobileNo.equals("")){

                    Toast.makeText(updateYourProfile.this, "Fill remaing Details", Toast.LENGTH_SHORT).show();
                }else {



                }

            }
        });




    }
}