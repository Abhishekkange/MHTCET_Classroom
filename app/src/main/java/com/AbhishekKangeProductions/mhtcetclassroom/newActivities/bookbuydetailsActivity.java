package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.userActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.unity3d.ads.UnityAds;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class bookbuydetailsActivity extends AppCompatActivity {


    ImageView bookImage2;
    TextView description2,bookPrice2,bookName2;
    Button buyOnAmazon,pdfBtn;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";

    FirebaseDatabase database;
    FirebaseAuth mAuth;
    public static String username;
    public Calendar mycalendar;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        String bookName = intent.getStringExtra("bookName");
        String bookImage = intent.getStringExtra("bookImage");
        String description  = intent.getStringExtra("description");
        String bookPrice = intent.getStringExtra("bookPrice");

        bookName2.setText(bookName);
        bookPrice2.setText(bookPrice);
        Picasso.get().load(bookImage).placeholder(R.drawable.loading).into(bookImage2);
        description2.setText(description);



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
                userActivity2.setActivity("Book Details : Book = "+ bookName.toString());
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
        setContentView(R.layout.activity_bookbuydetails);

        InterstitialAd();



        Intent intent2 = getIntent();
        String bookPdfUrl = intent2.getStringExtra("bookPdfUrl");
        String bookUrl = intent2.getStringExtra("bookUrl");

        bookImage2 = findViewById(R.id.bookDetailImage);
        description2 = findViewById(R.id.bookDetailTextView);
        buyOnAmazon = findViewById(R.id.buyOnAmazonBtn);
        pdfBtn = findViewById(R.id.pdfBookDetailBtn);
        bookPrice2 = findViewById(R.id.bookPrice);
        bookName2 = findViewById(R.id.bookName);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mycalendar = Calendar.getInstance();



        buyOnAmazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String url = bookUrl;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }catch (Exception e){

                    Toast.makeText(bookbuydetailsActivity.this, "Book not Available", Toast.LENGTH_SHORT).show();
                }



            }
        });

        pdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent1 = new Intent(bookbuydetailsActivity.this,viewFormulaActivity.class);
            intent1.putExtra("url",bookPdfUrl);
            startActivity(intent1);
            Animatoo.animateSlideLeft(bookbuydetailsActivity.this);

            }
        });






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