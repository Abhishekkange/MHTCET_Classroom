package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.shashank.sony.fancytoastlib.FancyToast;

public class signupActivity extends AppCompatActivity {

    EditText email,password,name,whichClasses,mobileNo;
    Button signUpBtn;
    FirebaseAuth mAuth;
    TextView signInJump;
    ImageView setProfileHere;
    TextView pickProfilePhoto;
    public static int PICK_IMAGE = 21;
    FirebaseDatabase database;
    FirebaseStorage storage;
    public static String IMAGE_URL ="";
    ProgressDialog processDialog;

    @Override
    public void onStart() {
        super.onStart();




        }
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Finding View BY ID's

        email = findViewById(R.id.signupemail);
        password = findViewById(R.id.signuppassword);
        mobileNo = findViewById(R.id.mobileNumberInput);
        signUpBtn = findViewById(R.id.signUpBtn);
        signInJump = findViewById(R.id.jumpSignIn);
        setProfileHere = findViewById(R.id.setProfileHere);
         name = findViewById(R.id.signUpName);
         whichClasses = findViewById(R.id.signupClass);
         database = FirebaseDatabase.getInstance();
         storage = FirebaseStorage.getInstance();
         mAuth = FirebaseAuth.getInstance();





        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser !=null) {

            Intent intent = new Intent(signupActivity.this, MainActivity.class);
            startActivity(intent);

        }



        signInJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(signupActivity.this,signInActivity.class);
                startActivity(intent);


            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(signupActivity.this, "Please Wait... Creating Account", Toast.LENGTH_SHORT).show();

                if(email.getEditableText().toString().equals("") || password.getEditableText().toString().equals("")  || name.equals("") || whichClasses.equals("")|| mobileNo.equals("")){

                    FancyToast.makeText(signupActivity.this,"Enter Details and Profile Photo",FancyToast.LENGTH_LONG,FancyToast.DEFAULT,true);

                }
                else {


                    mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(email.getEditableText().toString(),password.getEditableText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            userCredentials model = new userCredentials();
                                model.setUserId(mAuth.getCurrentUser().getUid());
                                model.setWhichClass(whichClasses.getText().toString());
                                model.setEmail(email.getText().toString());
                                model.setPassword(password.getText().toString());
                                model.setName(name.getText().toString());
                                model.setMobileNo(mobileNo.getText().toString());
                                database.getReference().child("UserDetails").child(mAuth.getUid()).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void aVoid) {

                             database.getReference().child("Student Analysis").child("userDetails").child(whichClasses.getText().toString()).child(mAuth.getUid()).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void aVoid) {

                                     Intent intent = new Intent(signupActivity.this,uploadProfilePictureActivity.class);
                                     startActivity(intent);
                                     Animatoo.animateSlideLeft(signupActivity.this);

                                 }
                             });





                          }
                         }).addOnFailureListener(new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull Exception e) {
                              Toast.makeText(signupActivity.this, "Try Later...", Toast.LENGTH_SHORT).show();
                            }
                             });




                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(signupActivity.this, "Unable to Create Account  TRY AGAIN", Toast.LENGTH_SHORT).show();
                        }
                    });







                }



            }
                });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {

            if (data != null) {

                Uri ImageUrl = data.getData();
                setProfileHere.setImageURI(ImageUrl);
                IMAGE_URL = ImageUrl.toString();



            }


        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }



}