package com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.userActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.practicepaperPCM;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.newMainActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class practicePapers extends AppCompatActivity {

    TextView pcm,pcb;
    FrameLayout frameLayout;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    public static String username;
    public Calendar mycalendar;
    private SimpleDateFormat dateFormat;








    @Override
    protected void onStart() {
        super.onStart();

        pcm.setBackgroundResource(R.drawable.pcmbackchange);
        pcb.setBackgroundResource(R.drawable.pcmback);
        practicepaperPCM PCM = new practicepaperPCM();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.PreviousPCMFrame,PCM);
        transaction.commit();

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
                userActivity2.setActivity("Practice papers Activity");
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
        setContentView(R.layout.activity_practice_papers);


        pcm = findViewById(R.id.pcmTogglepractice);
        pcb = findViewById(R.id.pcbTogglepractice);
        frameLayout = findViewById(R.id.PreviousPCMFrame);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mycalendar = Calendar.getInstance();


        pcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pcm.setBackgroundResource(R.drawable.pcmbackchange);
                pcb.setBackgroundResource(R.drawable.pcmback);
                practicepaperPCM PCM = new practicepaperPCM();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.PreviousPCMFrame,PCM);
                transaction.commit();
            }
        });

        pcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pcb.setBackgroundResource(R.drawable.pcmbackchange);
                pcm.setBackgroundResource(R.drawable.pcmback);

            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(practicePapers.this, newMainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(this);




    }


}
