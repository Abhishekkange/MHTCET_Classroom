package com.AbhishekKangeProductions.mhtcetclassroom.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.testListAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.testListModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PCB_tests#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PCB_tests extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PCB_tests() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PCB_tests.
     */
    // TODO: Rename and change types and number of parameters
    public static PCB_tests newInstance(String param1, String param2) {
        PCB_tests fragment = new PCB_tests();
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

        View view = inflater.inflate(R.layout.fragment_p_c_b_tests, container, false);


        recyclerView = view.findViewById(R.id.pcbTestRv);
        database = FirebaseDatabase.getInstance();

        ShimmerFrameLayout shimmerFrameLayout = new ShimmerFrameLayout(getContext());
        shimmerFrameLayout = view.findViewById(R.id.mShimmerViewContainerPcb);
        shimmerFrameLayout.startShimmerAnimation();

        ArrayList<testListModel> list = new ArrayList<>();
        testListAdapter adapter = new testListAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        ShimmerFrameLayout finalShimmerFrameLayout = shimmerFrameLayout;
        database.getReference().child("TestNos").child("PCB").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    testListModel modelt =snapshot1.getValue(testListModel.class);
                    list.add(modelt);
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