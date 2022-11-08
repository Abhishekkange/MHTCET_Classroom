package com.AbhishekKangeProductions.mhtcetclassroom.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.formulaSheetRvAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.formulaSheetModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class biologyFormulaFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public biologyFormulaFragment() {

    }


    public static biologyFormulaFragment newInstance(String param1, String param2) {
        biologyFormulaFragment fragment = new biologyFormulaFragment();
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

        RecyclerView formulaRv;
        FirebaseDatabase database;

       View view =  inflater.inflate(R.layout.fragment_biology_formula, container, false);


        formulaRv = view.findViewById(R.id.physicsFormulaRv);
        database = FirebaseDatabase.getInstance();

        //Creating an ArrayList
        ArrayList<formulaSheetModel> list= new ArrayList<>();

        //Adapter creation
        formulaSheetRvAdapter adapter  = new formulaSheetRvAdapter(list,getContext());

        //Adding elements to Adapter
        database.getReference().child("Formulas").child("Biology").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1:snapshot.getChildren()){

                    formulaSheetModel model = snapshot1.getValue(formulaSheetModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        formulaRv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        formulaRv.setLayoutManager(layoutManager);




        return view;
    }
}