package com.AbhishekKangeProductions.mhtcetclassroom.newFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.chapterwiseMockTestPhysicsAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.chapterwiseMockTestModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class physicsMockTests extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public physicsMockTests() {

    }


    public static physicsMockTests newInstance(String param1, String param2) {
        physicsMockTests fragment = new physicsMockTests();
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

        FirebaseDatabase database;
        FirebaseAuth mAuth;
        RecyclerView RecyclerView;



       View view =  inflater.inflate(R.layout.fragment_physics_mock_tests, container, false);


       RecyclerView = view.findViewById(R.id.physicsMockTestRv);

        ArrayList<chapterwiseMockTestModel> list = new ArrayList<>();

        chapterwiseMockTestPhysicsAdapter adapter = new chapterwiseMockTestPhysicsAdapter(list,getContext());
        RecyclerView.setAdapter(adapter);


        //Adding data frm firebase
        list.add(new chapterwiseMockTestModel("Laws of Motion"));

        list.add(new chapterwiseMockTestModel("Laws of Motion"));
        list.add(new chapterwiseMockTestModel("Laws of Motion"));
        list.add(new chapterwiseMockTestModel("Laws of Motion"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.setLayoutManager(layoutManager);





       return view;

    }
}