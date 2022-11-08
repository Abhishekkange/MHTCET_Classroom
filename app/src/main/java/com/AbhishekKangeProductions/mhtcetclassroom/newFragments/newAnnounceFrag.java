package com.AbhishekKangeProductions.mhtcetclassroom.newFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.announceAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.announceModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.unity3d.services.core.properties.ClientProperties.getApplicationContext;


public class newAnnounceFrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public newAnnounceFrag() {

    }


    public static newAnnounceFrag newInstance(String param1, String param2) {
        newAnnounceFrag fragment = new newAnnounceFrag();
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

    RecyclerView recyclerView;
    FirebaseDatabase database;
    public static String notificationMsg;
    FirebaseAuth mAuth;
    public static String username;
    public Calendar mycalendar;
    private SimpleDateFormat dateFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




   View view = inflater.inflate(R.layout.fragment_new_announce, container, false);


        database = FirebaseDatabase.getInstance();

        recyclerView = view.findViewById(R.id.announceRv2);
        ArrayList<announceModel> list = new ArrayList<>();
        announceAdapter adapter = new announceAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);
        database.getReference().child("Announcement").child("titles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    announceModel model = snapshot1.getValue(announceModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);



        return view;
    }
}