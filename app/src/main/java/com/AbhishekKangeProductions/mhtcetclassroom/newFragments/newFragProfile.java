package com.AbhishekKangeProductions.mhtcetclassroom.newFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.userCredentials;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.settingsActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.updateYourProfile;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class newFragProfile extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public newFragProfile() {

    }


    public static newFragProfile newInstance(String param1, String param2) {
        newFragProfile fragment = new newFragProfile();
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

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    ImageView profilePhoto,settings;
    TextView userName,coins,xp;
    public static String userName2 = "Loading...";
    public static  String profilePhoto2;

    @Override
    public void onStart() {
        super.onStart();

        database.getReference().child("UserDetails").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userCredentials details = snapshot.getValue(userCredentials.class);

                userName2 = details.getName();
                profilePhoto2 = details.getProfilePhoto();
                coins.setText(details.getCoins());
                xp.setText(details.getXp());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        userName.setText(userName2);
        Picasso.get().load(profilePhoto2).placeholder(R.drawable.girlprofileicon).into(profilePhoto);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view =  inflater.inflate(R.layout.fragment_new_frag_profile, container, false);

        profilePhoto = view.findViewById(R.id.profileActivityImage);
        userName = view.findViewById(R.id.profileActivityName);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        settings = view.findViewById(R.id.settingsIcon);
        xp = view.findViewById(R.id.profileXp);
        coins = view.findViewById(R.id.profileCoins);


        //addingOnClickListeners

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(), updateYourProfile.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(getContext());
            }
        });






        return view;
    }
}