package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.forgotPassword;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.newMainActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signInActivity extends AppCompatActivity {

    EditText signInEmail,signInPassword;
    Button signInBtn;
    TextView registor,forgotpassword;
    FirebaseAuth mAuth;




    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null){


            Intent intent = new Intent(signInActivity.this, newMainActivity.class);
            startActivity(intent);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Finding View By Id

        signInEmail = findViewById(R.id.signinemail);
        signInPassword = findViewById(R.id.signinpassword);
        signInBtn = findViewById(R.id.signInBtn);
        registor = findViewById(R.id.jumpRegistor);
        forgotpassword = findViewById(R.id.forgotPassword);
        mAuth = FirebaseAuth.getInstance();



        //OnclickListerners

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(signInActivity.this, forgotPassword.class);
                startActivity(intent);

            }
        });

        registor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(signInActivity.this,signupActivity.class);
                startActivity(intent);
                Animatoo.animateSlideUp(signInActivity.this);

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              FirebaseAuth  mAuth = FirebaseAuth.getInstance();

             if (signInPassword.getEditableText().toString().equals("") || signInEmail.getEditableText().toString().equals("")){

                 Toast.makeText(signInActivity.this, "enter Complete details", Toast.LENGTH_SHORT).show();
             }else {

                 mAuth.signInWithEmailAndPassword(signInEmail.getEditableText().toString(),signInPassword.getEditableText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {

                         if(task.isSuccessful()){

                             Intent intent = new Intent(signInActivity.this,newMainActivity.class);
                             startActivity(intent);

                         }else{

                             Toast.makeText(signInActivity.this, "Sign In Error", Toast.LENGTH_SHORT).show();


                         }




                     }
                 });


             }


            }
        });







    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}