package com.AbhishekKangeProductions.mhtcetclassroom.newFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.dailyMockInstructionsModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.dataCollection1;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class dailyMocktestFrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public dailyMocktestFrag() {

    }


    public static dailyMocktestFrag newInstance(String param1, String param2) {
        dailyMocktestFrag fragment = new dailyMocktestFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    FirebaseDatabase database;
    ImageView instructionImage;


    @Override
    public void onStart() {
        super.onStart();

        database.getReference().child("DailyMockTestInstructions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1:snapshot.getChildren()){


                    dailyMockInstructionsModel model = snapshot1.getValue(dailyMockInstructionsModel.class);
                    Picasso.get().load(model.getImage()).placeholder(R.drawable.dailymocktestloading).into(instructionImage);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Button startTest;


        View view =  inflater.inflate(R.layout.fragment_daily_mocktest, container, false);
        instructionImage = view.findViewById(R.id.imageView29);


        startTest = view.findViewById(R.id.startDailyMockTest);

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), dataCollection1.class);
                intent.putExtra("theTestNo","Test 1");
                startActivity(intent);
                Animatoo.animateSlideLeft(getContext());






            }
        });
        database = FirebaseDatabase.getInstance();




        return view;
    }
}