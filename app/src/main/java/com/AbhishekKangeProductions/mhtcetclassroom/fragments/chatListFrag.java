package com.AbhishekKangeProductions.mhtcetclassroom.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.showchatterListAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.showchatterListModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class chatListFrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public chatListFrag() {

    }

    public static chatListFrag newInstance(String param1, String param2) {
        chatListFrag fragment = new chatListFrag();
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
        FirebaseAuth mAuth;
        FirebaseDatabase database;
        ShimmerFrameLayout shimmerFrameLayout;


       View view = inflater.inflate(R.layout.fragment_chat_list, container, false);


        recyclerView = view.findViewById(R.id.chatlistrv);
        ArrayList<showchatterListModel> list = new ArrayList<>();
        showchatterListAdapter adapter = new showchatterListAdapter(getContext(),list);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        shimmerFrameLayout = view.findViewById(R.id.mShimmerViewContainerchattingActivity);
        shimmerFrameLayout.startShimmerAnimation();




        //Add List here

        database.getReference().child("chattingSection").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if(snapshot!=null){

                    list.clear();
                    for (DataSnapshot snapshot1:snapshot.getChildren()){

                        showchatterListModel model = snapshot1.getValue(showchatterListModel.class);
                        list.add(model);
                        shimmerFrameLayout.setAlpha(0);
                        shimmerFrameLayout.stopShimmerAnimation();



                    }
                }


                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });



        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        

       return view;
    }
}