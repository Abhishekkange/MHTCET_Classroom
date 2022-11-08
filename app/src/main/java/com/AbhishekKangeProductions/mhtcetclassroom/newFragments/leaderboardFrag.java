package com.AbhishekKangeProductions.mhtcetclassroom.newFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.studentLeaderBoardAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.studentLeaderBoardModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class leaderboardFrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public leaderboardFrag() {

    }


    public static leaderboardFrag newInstance(String param1, String param2) {
        leaderboardFrag fragment = new leaderboardFrag();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView studentLeaderBoardRv;
        FirebaseAuth mAuth;
        FirebaseDatabase database;
        View view =  inflater.inflate(R.layout.fragment_leaderboard, container, false);

        studentLeaderBoardRv = view.findViewById(R.id.studentLeaderBoardRv);
        mAuth = FirebaseAuth.getInstance();
        database  = FirebaseDatabase.getInstance();


        ArrayList<studentLeaderBoardModel> list = new ArrayList<>();

        studentLeaderBoardAdapter adapter = new studentLeaderBoardAdapter(list,getContext());

        //set data here
        database.getReference().child("UserDetails").orderByChild("xp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){

                    studentLeaderBoardModel model = snapshot1.getValue(studentLeaderBoardModel.class);
                    list.add(model);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        studentLeaderBoardRv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        studentLeaderBoardRv.setLayoutManager(layoutManager);


        return view;
    }
}