package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.unity3d.ads.UnityAds;

import java.io.File;

public class othersProfileActivity extends AppCompatActivity {

    Button downloadSexy;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    FirebaseStorage storage;
    ImageView profileImg;
    TextView xpNo;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";

    public static  String imgUrl,name;
    public static  String xp;

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        imgUrl = intent.getStringExtra("img");
        xp = intent.getStringExtra("xp");
        name = intent.getStringExtra("name");



        //setting img profile

        Picasso.get().load(imgUrl).placeholder(R.drawable.girlprofileicon).into(profileImg);
        xpNo.setText(xp);





    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_profile);

        //finding ids

        profileImg = findViewById(R.id.myprofileSectionPhoto);
        xpNo  = findViewById(R.id.nyProfileXp);



        InterstitialAd();

        database = FirebaseDatabase.getInstance();
        mAuth  = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        //adding on Click Listeners

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(othersProfileActivity.this,viewPhotoActivity.class);
                intent.putExtra("img",imgUrl);
                intent.putExtra("name",name);
                startActivity(intent);
                Animatoo.animateZoom(othersProfileActivity.this);
            }
        });


    }


    private void downloadImageNew(String filename, String downloadUrlOfImage){
        try{
            DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(imgUrl);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle("abcd")
                    .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,File.separator + filename + ".jpg");
            dm.enqueue(request);
            Toast.makeText(this, "Image download started.", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Image download failed.", Toast.LENGTH_SHORT).show();
        }
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