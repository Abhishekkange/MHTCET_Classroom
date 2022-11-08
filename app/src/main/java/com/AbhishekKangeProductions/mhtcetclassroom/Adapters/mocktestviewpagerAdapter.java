package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.newFragments.chemistryMockTests;
import com.AbhishekKangeProductions.mhtcetclassroom.newFragments.mathsMockTests;
import com.AbhishekKangeProductions.mhtcetclassroom.newFragments.physicsMockTests;

public class mocktestviewpagerAdapter extends FragmentPagerAdapter {
    public mocktestviewpagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0 :return new physicsMockTests();
            case 1 :return new chemistryMockTests();
            case 2 :return new mathsMockTests();

            default: return new physicsMockTests();


        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        String title = null;

        if(position==1){
            title = "Physics";
        }
        else if(position==2){
            title = "Chemistry";
        }
        else{
            title = "Maths";
        }

        return title;
    }
}
