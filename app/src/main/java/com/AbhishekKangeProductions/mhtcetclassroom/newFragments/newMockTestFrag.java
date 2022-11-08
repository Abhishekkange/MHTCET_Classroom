package com.AbhishekKangeProductions.mhtcetclassroom.newFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.mocktestviewpagerAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.android.material.tabs.TabLayout;


public class newMockTestFrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public newMockTestFrag() {

    }


    public static newMockTestFrag newInstance(String param1, String param2) {
        newMockTestFrag fragment = new newMockTestFrag();
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

        ViewPager viewPager;
        TabLayout tabLayout;

        View view =  inflater.inflate(R.layout.fragment_new_mock_test, container, false);

        viewPager = view.findViewById(R.id.mockTestViewPager);
        tabLayout = view.findViewById(R.id.tabLayout1234);

        mocktestviewpagerAdapter adapter = new mocktestviewpagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);





        return view;
    }
}