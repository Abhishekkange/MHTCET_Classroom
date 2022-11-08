package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.myProfileShowModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class myProfileShowAdapter extends RecyclerView.Adapter<myProfileShowAdapter.viewHolder> {

    ArrayList<myProfileShowModel> list;
    Context context;

    public myProfileShowAdapter(ArrayList<myProfileShowModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.myprofilelayout,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        myProfileShowModel model = list.get(position);
        Picasso.get().load(model.getPostImage()).placeholder(R.drawable.loading).into(holder.postImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView postImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            postImage = itemView.findViewById(R.id.myProfileSectionLayoutImage);


        }
    }
}
