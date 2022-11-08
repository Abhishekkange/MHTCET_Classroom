package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.nftPostModel;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.unity3d.ads.UnityAds;

public class createPostActivity extends AppCompatActivity {

    ImageView postImage;
    Button postBtn,selectPostBtn;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    FirebaseStorage storage;
    public static String userName2;
    public static String profilePhoto;
    EditText enterCommentSection;
    public static String imageLink;
    public static final int PICK_IMAGE = 1;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onStart() {
        super.onStart();

        //getting userData
        database.getReference().child("UserDetails").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userCredentials details = snapshot.getValue(userCredentials.class);

                userName2 = details.getName();
                profilePhoto = details.getProfilePhoto();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        InterstitialAd();


        //finding Id's

        postImage = findViewById(R.id.selectedPostViewImage);
        postBtn = findViewById(R.id.postImageBtn);
        selectPostBtn = findViewById(R.id.selectPostImage);
        database  = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();


        //adding OnClicks

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog dialog = new ProgressDialog(createPostActivity.this);
                dialog.setMessage("Uploading...");
                dialog.show();
                //ads
                UnityAds.show(createPostActivity.this,INTERSTRITIALID);


                final StorageReference reference = storage.getReference().child("AllPosts").child(mAuth.getCurrentUser().getUid()).child(String.valueOf(System.currentTimeMillis()));
                reference.putFile(Uri.parse(imageLink)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {


                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                //Toast.makeText(createPostActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();
                                nftPostModel model = new nftPostModel();
                                model.setPostImage(uri.toString());
                                model.setUserName(userName2);
                                model.setProfilePhoto(profilePhoto);
                                model.getUserId(mAuth.getCurrentUser().getUid());
                                database.getReference().child("posts").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(createPostActivity.this, "Post Uploaded", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        Intent intent = new Intent(createPostActivity.this,newMainActivity.class);
                                        startActivity(intent);



                                    }

                                });

                            }
                        });



                        }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


            }
        });

        selectPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent to open gallery

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);


            }
        });





    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {

            imageLink = data.getData().toString();
            postImage.setImageURI(data.getData());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(createPostActivity.this,newMainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(this);
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