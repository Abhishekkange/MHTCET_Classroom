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

import com.AbhishekKangeProductions.mhtcetclassroom.Models.collegeReviewModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.collegeReviewData;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class collegeReviewAdapter extends RecyclerView.Adapter<collegeReviewAdapter.viewHolder> {

        ArrayList<collegeReviewModel> list;
        Context context;

    public collegeReviewAdapter(ArrayList<collegeReviewModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.collegereview,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        collegeReviewModel model = list.get(position);
        Picasso.get().load(model.getImage()).placeholder(R.drawable.loading).into(holder.collegeReviewImage);
        holder.collegeName.setText(model.getName());
       // Picasso.get().load(model.getRating()).placeholder(R.drawable.loading).into(holder.rating);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(context, collegeReviewData.class);
                intent.putExtra("collegename",holder.collegeName.getText().toString());
                 context.startActivity(intent);
                Animatoo.animateSplit(context);



            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView collegeReviewImage,rating;
        TextView  collegeName;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            collegeReviewImage = itemView.findViewById(R.id.collegeImage);
            collegeName = itemView.findViewById(R.id.collegeName);
            rating = itemView.findViewById(R.id.collegeReviewRating);



        }
    }
}
