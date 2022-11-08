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
import android.widget.ImageView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.squareup.picasso.Picasso;
import com.unity3d.ads.UnityAds;

import java.io.File;

public class viewPhotoActivity extends AppCompatActivity {

    public  String imgUrl,name;
    ImageView photo,downloadPhoto;

    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        imgUrl = intent.getStringExtra("img");
        name = intent.getStringExtra("name");
        Picasso.get().load(imgUrl).placeholder(R.drawable.loading).into(photo);




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        //ad
        UnityAds.initialize(this,GAMEID,false);

        InterstitialAd();

        photo = findViewById(R.id.otherPhoto);
        downloadPhoto = findViewById(R.id.othersDownload);

        downloadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downloadImageNew(name,imgUrl);
                UnityAds.show(viewPhotoActivity.this,INTERSTRITIALID);

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
                    .setTitle(filename)
                    .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + filename + ".jpg");
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