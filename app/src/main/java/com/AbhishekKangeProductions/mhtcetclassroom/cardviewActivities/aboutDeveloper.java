package com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.aboutdeveloperModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.navigationDrawerActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class aboutDeveloper extends AppCompatActivity {

    ImageView mypic;
    ImageView instagram,linkdin,youtube;
    TextView mydescription;
    FirebaseDatabase database;


    @Override
    protected void onStart() {
        super.onStart();






    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);

        instagram = findViewById(R.id.instagram);
        linkdin = findViewById(R.id.linkdin);
        youtube = findViewById(R.id.youtube);
        database = FirebaseDatabase.getInstance();
        mydescription = findViewById(R.id.textView17);
        mypic = findViewById(R.id.imageView11);
        mydescription.setMovementMethod(new ScrollingMovementMethod());


        database.getReference().child("AboutMe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){

                    aboutdeveloperModel modelme = snapshot1.getValue(aboutdeveloperModel.class);
                    mydescription.setText(modelme.getInfo());
                    Picasso.get().load(modelme.getImage()).placeholder(R.drawable.loading).into(mypic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(aboutDeveloper.this, "follow @ abhishek_kange_insta", Toast.LENGTH_SHORT).show();
            }
        });
        linkdin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String url = "https://www.linkedin.com/in/abhishek-kange-708a431b8";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                String url = "https://www.youtube.com/channel/UCHaxvgcy3abHptAYCv_4o8A";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);



            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(aboutDeveloper.this, navigationDrawerActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(aboutDeveloper.this);

    }

}