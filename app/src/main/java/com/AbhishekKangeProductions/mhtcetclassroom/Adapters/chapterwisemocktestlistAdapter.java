package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.chapterwisemocktestlist;
import com.AbhishekKangeProductions.mhtcetclassroom.R;

import java.util.ArrayList;

public class chapterwisemocktestlistAdapter extends RecyclerView.Adapter<chapterwisemocktestlistAdapter.viewHolder>{

    ArrayList<chapterwisemocktestlist> list;
    Context context;

    public chapterwisemocktestlistAdapter(ArrayList<chapterwisemocktestlist> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.chapterwisemocktestlistlayout,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        chapterwisemocktestlist model = new  chapterwisemocktestlist();
        holder.title.setText(model.getTitle());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView title;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.chapterwisemocktesttitle);

        }
    }
}
