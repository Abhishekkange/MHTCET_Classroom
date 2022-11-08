package com.AbhishekKangeProductions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.AbhishekKangeProductions.mhtcetclassroom.Adapters.personalChattingAdapter;
import com.AbhishekKangeProductions.mhtcetclassroom.Models.sendMessageModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class personalChatFragment extends AppCompatActivity {

    ImageView chattingImageView,sendbtn;
    TextView userName;
    EditText enterMsgHere;
    FirebaseAuth mAuth;
    RecyclerView personalChattingRv;
    FirebaseDatabase database;
    NotificationManager manager;

    @Override
    protected void onStart() {
        super.onStart();

        // Getting Data From Intent
        Intent intent2 = getIntent();
        String profilePicData = intent2.getStringExtra("profilePic");
        String nameData = intent2.getStringExtra("name");


        userName.setText(nameData);
        Picasso.get().load(profilePicData).placeholder(R.drawable.group_study).into(chattingImageView);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat_fragment);

        Intent intent = getIntent();
        String  recieverId = intent.getStringExtra("userId");


        //findViewById

        chattingImageView = findViewById(R.id.chattingProfileImage);
        userName = findViewById(R.id.chatterName);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        sendbtn = findViewById(R.id.sendMessageBtnty);
        enterMsgHere = findViewById(R.id.enterMessageHere);
        String senderId = mAuth.getInstance().getUid();
        String senderRoom = senderId+recieverId;
        String recieverRoom = recieverId+senderId;
        personalChattingRv = findViewById(R.id.chattingPersonalRv);

        //Notification





        //ArrayList For Chatting

        final ArrayList<sendMessageModel> list = new ArrayList<>();
        final personalChattingAdapter adapter = new personalChattingAdapter(list,getApplicationContext());



        database.getReference().child("PersonalChats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                list.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){

                    sendMessageModel model = snapshot1.getValue(sendMessageModel.class);
                    list.add(model);

                   notification();




                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        personalChattingRv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(personalChatFragment.this);

        personalChattingRv.setLayoutManager(layoutManager);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendMessageModel model = new sendMessageModel();
                model.setMessage(enterMsgHere.getText().toString());
                enterMsgHere.setText("");
                model.setSenderId(senderId);
                model.setTimestamp(345);
                database.getReference().child("PersonalChats").child(senderRoom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        database.getReference().child("PersonalChats").child(recieverRoom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {



                            }
                        });


                    }
                });



            }
        });





    }
    public void notification(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"n")
                    .setContentText("View msg in App ")
                    .setSmallIcon(R.drawable.mhtcetlogo)
                    .setAutoCancel(true)
                    .setContentTitle("Someone Just Messaged You");



            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(999,builder.build());



        }



    }


}