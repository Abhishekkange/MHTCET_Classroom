package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.userActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.UnityAds;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class viewFormulaActivity extends AppCompatActivity {


    WebView webView;
    AdView mAdView;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    public String url;
    TextView downloadPdfBtn;
    public static String username;
    public Calendar mycalendar;
    private SimpleDateFormat dateFormat;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";



    @Override
    protected void onStart() {
        super.onStart();

        database.getReference().child("UserDetails").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userCredentials credentials = snapshot.getValue(userCredentials.class);
                username = credentials.getName();


                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String date = dateFormat.format(mycalendar.getTime());
                String mydate = date.toString();

                //Time setting

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                String time = sdf.format(mycalendar.getTime());

                userActivity userActivity2 = new userActivity();
                userActivity2.setTime(time);
                userActivity2.setUsername(username);
                userActivity2.setActivity("PDF READER Activity");
                database.getReference().child("UserActivity").child(mydate).child(mAuth.getCurrentUser().getUid()).push().setValue(userActivity2);


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_formula);

        InterstitialAd();

        //Getting DATA from Intent

        Intent intent = getIntent();
        String chapterName = intent.getStringExtra("chapterName");
        url = intent.getStringExtra("url");
        downloadPdfBtn = findViewById(R.id.downloadPdfNotes);


        downloadPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                downloadImageNew("NOTES of "+chapterName+" by MHTCET Classroom",url);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        UnityAds.show(viewFormulaActivity.this,INTERSTRITIALID);
                    }
                },2000);



            }
        });




        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.setWebViewClient(new WebViewClient());

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mycalendar = Calendar.getInstance();

       String newUrl = URLEncoder.encode(url);

        webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+ newUrl);









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
            },3000);
        }
    }

    private void downloadImageNew(String filename, String url ){
        try{
            DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(filename)
                    .setMimeType("application/pdf") // Your file type. You can use this code to download other file types also.
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + filename + ".pdf");
            dm.enqueue(request);
            Toast.makeText(this, "PDF download started.", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "PDF download failed.", Toast.LENGTH_SHORT).show();
        }
    }
}