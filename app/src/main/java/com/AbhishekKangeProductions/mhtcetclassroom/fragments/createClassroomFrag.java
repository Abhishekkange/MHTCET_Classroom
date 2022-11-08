package com.AbhishekKangeProductions.mhtcetclassroom.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.showClassroomModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class createClassroomFrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String userName;


    private String mParam1;
    private String mParam2;

    public createClassroomFrag() {

    }


    public static createClassroomFrag newInstance(String param1, String param2) {
        createClassroomFrag fragment = new createClassroomFrag();
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

        EditText classroomName, classroomDate , classroomTime;
        Button createClassroomList;
        FirebaseDatabase database;
        FirebaseAuth mAuth;

        View view =  inflater.inflate(R.layout.fragment_create_classroom, container, false);

        //Finding View By ID's
        classroomDate = view.findViewById(R.id.enterDate);
        classroomName = view.findViewById(R.id.enterClassroomName);
        classroomTime = view.findViewById(R.id.enterTime);
        createClassroomList = view.findViewById(R.id.createClassoonList);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        createClassroomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = classroomName.getText().toString();
                String Date = classroomDate.getText().toString();
                String Time  = classroomTime.getText().toString();

                //getting userName
              

                showClassroomModel model = new showClassroomModel();
                model.setClassroomDate(Date);
                model.setClassroomName(name);
                model.setClassroomTime(Time);
                model.setClassroomHost(userName);
                database.getReference().child("classroomNames").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        database.getReference().child("UserClassrooms").child(mAuth.getCurrentUser().getUid()).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                classroomDate.setText("");
                                classroomName.setText("");
                                classroomTime.setText("");
                                Toast.makeText(getContext(), "Classroom Created", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });


            }
        });









        return view;
    }
}