package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.AbhishekKangeProductions.mhtcetclassroom.fragments.buycetbooksfrag;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.jeeneetbooksfrag;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.selfgrowthfrag;

public class buyBookAdapter extends FragmentPagerAdapter {
    public buyBookAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0 :return new buycetbooksfrag();
            case 1 :return new jeeneetbooksfrag();
            case 2 :return new selfgrowthfrag();

            default: return new buycetbooksfrag();

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
            title = "MHT-CET";
        }
        else if(position==2){
            title = "JEE/NEET";
        }
        else{
            title = "Self Growth";
        }

        return title;

    }
}
