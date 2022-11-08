package com.AbhishekKangeProductions.mhtcetclassroom.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.dateAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.dateModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PCB_previous_years extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public PCB_previous_years() {
        // Required empty public constructor
    }


    public static PCB_previous_years newInstance(String param1, String param2) {
        PCB_previous_years fragment = new PCB_previous_years();
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

        RecyclerView recyclerView ;
        FirebaseDatabase database;
        ShimmerFrameLayout shimmerFrameLayout = new ShimmerFrameLayout(getContext());

        View view =  inflater.inflate(R.layout.fragment_p_c_b_previous_years, container, false);

        recyclerView = view.findViewById(R.id.PCB_recyclerView);
        database = FirebaseDatabase.getInstance();
        ArrayList<dateModel> list = new ArrayList<>();

        dateAdapter adapter = new dateAdapter(list,getContext());
        recyclerView.setAdapter(adapter);

        shimmerFrameLayout = view.findViewById(R.id.mShimmerViewContainer);
        shimmerFrameLayout.startShimmerAnimation();


        ShimmerFrameLayout finalShimmerFrameLayout = shimmerFrameLayout;
        database.getReference().child("PCB_Years").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    dateModel model = snapshot1.getValue(dateModel.class);
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
        return  view;

    }
}