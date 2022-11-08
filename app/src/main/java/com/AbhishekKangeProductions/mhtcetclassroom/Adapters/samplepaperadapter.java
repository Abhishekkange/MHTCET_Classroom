package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.samplepapermodel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.viewFormulaActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class samplepaperadapter extends RecyclerView.Adapter<samplepaperadapter.viewHolder>{


    ArrayList<samplepapermodel> list;
    Context context;

    public samplepaperadapter(ArrayList<samplepapermodel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.practicepapersample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        samplepapermodel model = list.get(position);
        holder.samplepaperno.setText(model.getSamplePaper());
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(model.getUrl()));
                context.startActivity(intent);
            }
        });
        holder.online.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(context, viewFormulaActivity.class);
               intent.putExtra("url",model.getoUrl());
               context.startActivity(intent);


           }
       });
        Picasso.get().load(model.getImage()).placeholder(R.drawable.mhtcetlogo).into(holder.samplepaperimage);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView samplepaperno;
        ImageView samplepaperimage;
        Button download,online;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            samplepaperno = itemView.findViewById(R.id.samplepaperno);
            samplepaperimage = itemView.findViewById(R.id.practicepaperimage);
            download = itemView.findViewById(R.id.practicepaperdownload);
            online = itemView.findViewById(R.id.practicepaperOnline);



        }

    }
}
