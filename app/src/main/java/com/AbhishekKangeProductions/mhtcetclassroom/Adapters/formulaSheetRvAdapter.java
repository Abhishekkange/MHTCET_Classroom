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

import com.AbhishekKangeProductions.mhtcetclassroom.Models.formulaSheetModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.viewFormulaActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class formulaSheetRvAdapter extends RecyclerView.Adapter<formulaSheetRvAdapter.viewHolder> {

    ArrayList<formulaSheetModel> list;
    Context context;

    public formulaSheetRvAdapter(ArrayList<formulaSheetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.formularvsample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        formulaSheetModel model = list.get(position);
        holder.chapterName.setText(model.getChapterName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, viewFormulaActivity.class);
                intent.putExtra("chapterName",model.getChapterName());
                intent.putExtra("url",model.getUrl());
                context.startActivity(intent);
                Animatoo.animateSlideLeft(context);




            }
        });
        Picasso.get().load(model.getImage()).placeholder(R.drawable.mhtcetlogo).into(holder.iconImage);


        

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder{

        TextView chapterName;
        ImageView iconImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);


            chapterName = itemView.findViewById(R.id.formulaSheetRvsample);
            iconImage = itemView.findViewById(R.id.formulaIcon);








        }
    }
}
