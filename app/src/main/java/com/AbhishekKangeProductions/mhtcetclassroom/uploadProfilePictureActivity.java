package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.newMainActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class uploadProfilePictureActivity extends AppCompatActivity {

    ImageView profilePhoto;
    TextView selectImage;
    public static String  selectedImage =null;
    Button continueBtn;
    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_picture);


        //Finding View By ID's
        profilePhoto = findViewById(R.id.setProfileHereinActivity);
        selectImage = findViewById(R.id.selectImageProfilePhotoActivity);
        continueBtn = findViewById(R.id.continueBtnProfileActivity);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        progressBar = findViewById(R.id.progressBar2);



        //Adding Onclick listerners



        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select Picture"),2);





            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(selectedImage!=null){


                    Toast.makeText(uploadProfilePictureActivity.this, "Please Wait.... Setting up Profile", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                    final StorageReference reference1 = storage.getReference().child("Profile Pictures").child(mAuth.getUid()).child(mAuth.getUid());
                    reference1.putFile(Uri.parse(selectedImage)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                            reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    database.getReference().child("UserDetails").child(mAuth.getUid()).child("profilePhoto").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            Intent intent = new Intent(uploadProfilePictureActivity.this, newMainActivity.class);
                                            startActivity(intent);
                                            Animatoo.animateSlideLeft(uploadProfilePictureActivity.this);





                                        }
                                    });


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(uploadProfilePictureActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();

                                }
                            });



                        }
                    });


                }

                else{

                    Toast.makeText(uploadProfilePictureActivity.this, "Select Profile Image ", Toast.LENGTH_SHORT).show();

                }


                }





        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2){

            if(data!=null){
                Uri imageUrl = data.getData();
                selectedImage = imageUrl.toString();
                profilePhoto.setImageURI(imageUrl);

            }


        }




    }
}