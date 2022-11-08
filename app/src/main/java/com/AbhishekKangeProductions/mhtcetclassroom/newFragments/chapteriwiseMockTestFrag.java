package com.AbhishekKangeProductions.mhtcetclassroom.newFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.chapterwisemocktestlistAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.chapterwisemocktestlist;
import com.AbhishekKangeProductions.mhtcetclassroom.R;

import java.util.ArrayList;


public class chapteriwiseMockTestFrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public chapteriwiseMockTestFrag() {

    }


    public static chapteriwiseMockTestFrag newInstance(String param1, String param2) {
        chapteriwiseMockTestFrag fragment = new chapteriwiseMockTestFrag();
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

        View view  = inflater.inflate(R.layout.fragment_chapteriwise_mock_test, container, false);

        recyclerView = view.findViewById(R.id.chapterwiseMockTestRv2);

        ArrayList<chapterwisemocktestlist> list = new ArrayList<>();

        chapterwisemocktestlistAdapter adapter = new chapterwisemocktestlistAdapter(list,getContext());
        recyclerView.setAdapter(adapter);


        //adding data

        list.add(new chapterwisemocktestlist("NLM Test 1"));



        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }
}