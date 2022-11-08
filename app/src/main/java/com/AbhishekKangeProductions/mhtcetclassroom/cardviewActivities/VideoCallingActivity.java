package com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.createClassroomFrag;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.joinClassroomFrag;

public class VideoCallingActivity extends AppCompatActivity {

    Button classroomCreateBtn, joinClassroomBtn;

    //unity ads
    private String GAMEID = "4709585";
    private String BANNER_ID = "Banner_Android";
    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onStart() {
        super.onStart();

        createClassroomFrag classroomFrag = new createClassroomFrag();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.classroomFrame,classroomFrag);
        transaction.commit();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_calling);



        //Finding View By ID
        classroomCreateBtn = findViewById(R.id.createClassroomBtn);
        joinClassroomBtn = findViewById(R.id.joinClassroomBtn);

        //Adding On Click Listerners


        joinClassroomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                joinClassroomFrag joinClassroom  = new joinClassroomFrag();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.classroomFrame,joinClassroom);
                transaction.commit();

            }
        });


        classroomCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                createClassroomFrag classroomFrag = new createClassroomFrag();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.classroomFrame,classroomFrag);
                transaction.commit();




            }
        });



        



    }
}