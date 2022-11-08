package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.collegeReviewPublicModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;

import java.util.ArrayList;

public class collegeReviewPublicAdapter extends RecyclerView.Adapter<collegeReviewPublicAdapter.viewHolder> {

    ArrayList<collegeReviewPublicModel> list;
    Context context;

    public collegeReviewPublicAdapter(ArrayList<collegeReviewPublicModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.college_public_review_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        collegeReviewPublicModel model  = list.get(position);
        holder.comment.setText(model.getComment());
        holder.userName.setText(model.getUserName());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView userName, comment;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.collegeReviewUserName);
            comment = itemView.findViewById(R.id.collegeReviewPublicReviewText);



        }
    }
}
