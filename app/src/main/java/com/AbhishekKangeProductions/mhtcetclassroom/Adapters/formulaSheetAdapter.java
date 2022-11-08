package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.AbhishekKangeProductions.mhtcetclassroom.fragments.biologyFormulaFragment;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.chemistryFormulaFrag;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.mathFormulaFrag;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.physicsFormulaFrag;

public class formulaSheetAdapter extends FragmentPagerAdapter {




    public formulaSheetAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0 :return new physicsFormulaFrag();
            case 1 :return new chemistryFormulaFrag();
            case 2 :return new mathFormulaFrag();
            case 3 :return new biologyFormulaFragment();
            default: return new physicsFormulaFrag();

        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        String title = null;

        if(position==1){
            title = "Chemistry";
        }
        else if(position==2){
            title = "Maths";
        }
        else if(position==3){
            title = "Bio";
        }else{

            title = "Physics";
        }

        return title;

    }
}
