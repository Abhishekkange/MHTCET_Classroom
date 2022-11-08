package com.AbhishekKangeProductions.mhtcetclassroom.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.classroomShowAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.showClassroomModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;
import java.util.ArrayList;


public class joinClassroomFrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public joinClassroomFrag() {
    }


    public static joinClassroomFrag newInstance(String param1, String param2) {
        joinClassroomFrag fragment = new joinClassroomFrag();
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

        EditText secretCode;
        Button joinVideoCall;
        URL serverurl;
        RecyclerView showClassroomRv;
        FirebaseDatabase database;

        View view =  inflater.inflate(R.layout.fragment_join_classroom, container, false);


        showClassroomRv = view.findViewById(R.id.showClassroomRv);
        database = FirebaseDatabase.getInstance();


        //Addding OnClick
//        joinVideoCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                JitsiMeetConferenceOptions newOptions = new JitsiMeetConferenceOptions.Builder()
//                        .setRoom(secretCode.getText().toString())
//                        .setWelcomePageEnabled(true)
//                        .build();
//
//                JitsiMeetActivity.launch(getContext(),newOptions);
//
//            }
//        });
//
//        try {
//            serverurl = new URL("https://meet.jit.si");
//            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder().setServerURL(serverurl)
//                    .setWelcomePageEnabled(false)
//                    .setAudioMuted(true)
//                    .build();
//            JitsiMeet.setDefaultConferenceOptions(options);
//
//
//
//        } catch (MalformedURLException e) {
//
//            Toast.makeText(getContext(), "Network Issue", Toast.LENGTH_SHORT).show();
//        }

        ArrayList<showClassroomModel> list = new ArrayList<>();

        classroomShowAdapter classroomShowAdapter = new classroomShowAdapter(list,getContext());


        database.getReference().child("classroomNames").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1: snapshot.getChildren()){

                    showClassroomModel model = snapshot1.getValue(showClassroomModel.class);
                    list.add(model);

                }
                classroomShowAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        showClassroomRv.setAdapter(classroomShowAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        showClassroomRv.setLayoutManager(layoutManager);





        return view;
    }
}