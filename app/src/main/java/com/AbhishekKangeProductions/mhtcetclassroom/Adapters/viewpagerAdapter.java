package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.AbhishekKangeProductions.mhtcetclassroom.fragments.chatListFrag;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.groupChatFrag;
import com.AbhishekKangeProductions.mhtcetclassroom.fragments.studyStatusFrag;

public class viewpagerAdapter extends FragmentPagerAdapter {
    public viewpagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :return new chatListFrag();
            case 1 :return new groupChatFrag();
            case 2 :return new studyStatusFrag();
            default: return new chatListFrag();
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
            title = "Chats";
        }
        else if(position==2){
            title = "Group Study";
        }
        else {
            title = "Study Status";
        }

        return title;

    }
}
