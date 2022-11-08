package com.AbhishekKangeProductions.mhtcetclassroom.newActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class settingsActivity extends AppCompatActivity {

    private String INTERSTRITIALID = "Interstitial_Android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(settingsActivity.this,newMainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);

//        newFragProfile frag = new newFragProfile();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.newMainActivityFrame,frag);
//        transaction.commit();
    }
}