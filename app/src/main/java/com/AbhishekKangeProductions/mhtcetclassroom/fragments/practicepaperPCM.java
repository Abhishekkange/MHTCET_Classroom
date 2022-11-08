package com.AbhishekKangeProductions.mhtcetclassroom.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.samplepaperadapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.samplepapermodel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class practicepaperPCM extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public practicepaperPCM() {

    }


    public static practicepaperPCM newInstance(String param1, String param2) {
        practicepaperPCM fragment = new practicepaperPCM();
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

        RecyclerView recyclerView;
        FirebaseDatabase database;

        View view =  inflater.inflate(R.layout.fragment_practicepaper_p_c_m, container, false);

        recyclerView  = view.findViewById(R.id.sampleRVpcm);
        ShimmerFrameLayout shimmerFrameLayout = new ShimmerFrameLayout(getContext());
        shimmerFrameLayout = view.findViewById(R.id.mShimmerViewContainer);
        shimmerFrameLayout.startShimmerAnimation();
        database = FirebaseDatabase.getInstance();
        ArrayList<samplepapermodel> list = new ArrayList<>();
        samplepaperadapter adapter = new samplepaperadapter(list,getContext());
        recyclerView.setAdapter(adapter);
        ShimmerFrameLayout finalShimmerFrameLayout = shimmerFrameLayout;
        database.getReference().child("Practice Papers").child("PCM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    samplepapermodel model = snapshot1.getValue(samplepapermodel.class);
                    list.add(model);
                    finalShimmerFrameLayout.stopShimmerAnimation();
                    finalShimmerFrameLayout.setAlpha(0);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);




        return view;
    }
}