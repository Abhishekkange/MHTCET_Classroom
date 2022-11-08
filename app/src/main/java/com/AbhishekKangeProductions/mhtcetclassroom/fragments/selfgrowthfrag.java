package com.AbhishekKangeProductions.mhtcetclassroom.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.buyBookRvAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.buyBooksRvModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class selfgrowthfrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public selfgrowthfrag() {

    }


    public static selfgrowthfrag newInstance(String param1, String param2) {
        selfgrowthfrag fragment = new selfgrowthfrag();
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


        RecyclerView selfgrowthrv;
        FirebaseDatabase database;


        View view =  inflater.inflate(R.layout.fragment_selfgrowthfrag, container, false);


        selfgrowthrv = view.findViewById(R.id.selfgrowthRv);
        ArrayList<buyBooksRvModel> list = new ArrayList<>();
        buyBookRvAdapter adapter = new buyBookRvAdapter(list,getContext());
        database = FirebaseDatabase.getInstance();

        database.getReference().child("SellBooks").child("SelfGrowth").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){

                    buyBooksRvModel model = snapshot1.getValue(buyBooksRvModel.class);
                    list.add(model);


                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        selfgrowthrv.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        selfgrowthrv.setLayoutManager(layoutManager);






        return view;

    }
}