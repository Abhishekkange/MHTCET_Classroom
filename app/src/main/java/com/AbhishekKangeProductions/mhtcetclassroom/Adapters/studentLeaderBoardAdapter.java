package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.studentLeaderBoardModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.othersProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class studentLeaderBoardAdapter extends RecyclerView.Adapter<studentLeaderBoardAdapter.viewHolder> {

    ArrayList<studentLeaderBoardModel> list ;
    Context context;

    public studentLeaderBoardAdapter(ArrayList<studentLeaderBoardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.studentleaderboardlayout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        studentLeaderBoardModel model = list.get(position);
        holder.userName.setText(model.getName());
        Picasso.get().load(model.getProfilePhoto()).placeholder(R.drawable.girlprofileicon).into(holder.userImage);
        holder.userXp.setText(Integer.toString(model.getXp()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, model.getUserId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, othersProfileActivity.class);
                intent.putExtra("img",model.getProfilePhoto());
                intent.putExtra("name",model.getName().toString());
                intent.putExtra("xp",Integer.toString(model.getXp()));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView userImage;
        TextView userName;
        TextView userXp;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.studentLeaderBoarduserImage);
            userName = itemView.findViewById(R.id.studentLeaderBoardUserName);
            userXp = itemView.findViewById(R.id.studentLeaderBoardXp);





        }
    }
}
