package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.showchatterListModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.personalChatFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class showchatterListAdapter extends RecyclerView.Adapter<showchatterListAdapter.viewHolder> {

    Context context;
    ArrayList<showchatterListModel> list;

    public showchatterListAdapter(Context context, ArrayList<showchatterListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.showchatterlistsample,parent,false);
        return new viewHolder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        showchatterListModel model = list.get(position);

        try {
            Picasso.get().load(model.getProfilePhoto()).placeholder(R.drawable.profileicon).into(holder.profileImage256);

        }catch (Exception e){

        }
        holder.userName.setText(model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, personalChatFragment.class);
                intent.putExtra("name",model.getName());
                intent.putExtra("profilePic",model.getProfilePhoto());
                intent.putExtra("userId",model.getUserId());


                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profileImage256;
        TextView userName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.showProfileName);
            profileImage256 = itemView.findViewById(R.id.chattingProfileImage);


        }
    }


}
