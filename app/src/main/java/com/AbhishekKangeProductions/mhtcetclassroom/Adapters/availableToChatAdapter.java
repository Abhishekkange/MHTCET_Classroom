package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.availableToChatModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class availableToChatAdapter extends RecyclerView.Adapter<availableToChatAdapter.viewHolder>{

    ArrayList<availableToChatModel> list;
    Context context;
    FirebaseDatabase database;

    public availableToChatAdapter(ArrayList<availableToChatModel> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.availablechatsample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        availableToChatModel model = list.get(position);
        holder.userName.setText(model.getName());
        Picasso.get().load(model.getProfilePhoto()).placeholder(R.drawable.profileicon).into(holder.profilePhoto);
        holder.chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                availableToChatModel model1 = new availableToChatModel();
                model1.setName(model.getName());
                model1.setUserId(model.getUserId());
                model1.setProfilePhoto(model.getProfilePhoto());
                 database.getReference().child("chattingSection").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(model1).addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void aVoid) {

                         database.getReference().child("chattingSection").child(model1.getUserId()).push().setValue(model1).addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void aVoid) {

                                 Toast.makeText(context, model1.getName() +" Added to chat", Toast.LENGTH_SHORT).show();
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {


                                 Toast.makeText(context, "TRY AGAIN", Toast.LENGTH_SHORT).show();
                             }
                         });

                         Toast.makeText(context, model.getName()+" Added to chat list", Toast.LENGTH_SHORT).show();



                     }
                 });



            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class viewHolder extends RecyclerView.ViewHolder   {


        ImageView profilePhoto;
        TextView userName;
        Button chatBtn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profilePhoto = itemView.findViewById(R.id.showChatusersPhoto);
            userName = itemView.findViewById(R.id.showchatUsersListName);
            chatBtn = itemView.findViewById(R.id.addToChatBtn);
            database = FirebaseDatabase.getInstance();


        }
    }
}
