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

import com.AbhishekKangeProductions.mhtcetclassroom.Models.nftPostModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.othersProfileActivity;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class nftPostAdapter extends RecyclerView.Adapter<nftPostAdapter.viewHolder> {

    Context context;
    ArrayList<nftPostModel> list;

    public nftPostAdapter(Context context, ArrayList<nftPostModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.nft_post_layout,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        nftPostModel model = list.get(position);
        holder.userName.setText(model.getUserName());
        holder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, othersProfileActivity.class);
                intent.putExtra("img",model.getProfilePhoto());
                context.startActivity(intent);
            }
        });
        Glide.with(context).load(model.getPostImage()).placeholder(R.drawable.loadgif).into(holder.postImage);
        Picasso.get().load(model.getProfilePhoto()).placeholder(R.drawable.profile_vector_icon).into(holder.userProfile);

    }

    @Override
    public int getItemCount() {
        return list.size();


    }

    public  class  viewHolder extends RecyclerView.ViewHolder{

        ImageView userProfile,postImage;
        TextView userName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.nftPostUserName);
            postImage = itemView.findViewById(R.id.nftImage);
            userProfile = itemView.findViewById(R.id.nftPostUserProfilePost);




        }
    }
}
