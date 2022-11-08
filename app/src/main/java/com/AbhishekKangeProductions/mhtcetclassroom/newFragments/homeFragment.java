package com.AbhishekKangeProductions.mhtcetclassroom.newFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.nftPostAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.nftPostModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.formulaSheetActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.practicePapers;
import com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.previousYearspapers;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.buybooksActivity;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.dailyMocktestActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class homeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public homeFragment() {

    }


    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
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


        LinearLayout previousyearsPapers,mockTests,books;
        CardView dailymocktestBtn;
        ImageView announcement,formulaSheet;
        RecyclerView nftPostRv;
        FirebaseDatabase database;
        FirebaseAuth mAuth;








        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        //finding Ids
        previousyearsPapers = view.findViewById(R.id.newPreviousyearsPapers);
        mockTests = view.findViewById(R.id.newMockTestIcon);
        books = view.findViewById(R.id.newBooksIcon);
        announcement = view.findViewById(R.id.announcmentNav);
        formulaSheet = view.findViewById(R.id.formulaSheetNav);
        dailymocktestBtn = view.findViewById(R.id.daliyMockTestBtn);
        nftPostRv = view.findViewById(R.id.nftPostRv);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();



        //ArrayList for NFT Rv
        ArrayList<nftPostModel> list = new ArrayList<>();



        //Adapter for NFT Post RV
        nftPostAdapter adapter = new nftPostAdapter(getContext(),list);

        //Adding Data to RV

        database.getReference().child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){

                    nftPostModel model = snapshot1.getValue(nftPostModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //setting Adapter
        nftPostRv.setAdapter(adapter);
        //Rv Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        nftPostRv.setLayoutManager(layoutManager);

        //Adding story libraries content

        //adding On Click listeners

        dailymocktestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), dailyMocktestActivity.class);
                startActivity(intent);
                Animatoo.animateFade(getContext());



            }
        });

        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), com.AbhishekKangeProductions.mhtcetclassroom.cardviewActivities.announcment.class);
                startActivity(intent);
                Animatoo.animateFade(getContext());



            }
        });

        formulaSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), formulaSheetActivity.class);
                startActivity(intent);
                Animatoo.animateFade(getContext());
            }
        });

        previousyearsPapers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(), previousYearspapers.class);
                startActivity(intent);
                Animatoo.animateZoom(getContext());


            }
        });

        mockTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), practicePapers.class);
                startActivity(intent);
                Animatoo.animateZoom(getContext());


            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), buybooksActivity.class);
                startActivity(intent);
                Animatoo.animateZoom(getContext());


            }
        });









        return view;
    }
}