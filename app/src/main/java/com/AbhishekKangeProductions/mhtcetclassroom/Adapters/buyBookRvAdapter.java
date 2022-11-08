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

import com.AbhishekKangeProductions.mhtcetclassroom.Models.buyBooksRvModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.bookbuydetailsActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class buyBookRvAdapter extends RecyclerView.Adapter<buyBookRvAdapter.viewHolder> {

    ArrayList<buyBooksRvModel> list;
    Context context;

    public buyBookRvAdapter(ArrayList<buyBooksRvModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.buybooksshowlayout,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        buyBooksRvModel model = list.get(position);
       // holder.bookName.setText(model.getBookName());
        Picasso.get().load(model.getBookImage()).placeholder(R.drawable.loading).into(holder.bookImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, bookbuydetailsActivity.class);
                intent.putExtra("bookName",model.getBookName());
                intent.putExtra("bookImage",model.getBookImage());
                intent.putExtra("bookPdfUrl",model.getBookPdfUrl());
                intent.putExtra("bookUrl",model.getBookUrl());
                intent.putExtra("description",model.getDescription());
                intent.putExtra("bookPrice",model.getPrice());
                context.startActivity(intent);
                Animatoo.animateSlideLeft(context);


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView bookImage;
        TextView bookName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            bookImage = itemView.findViewById(R.id.buybooksimg);
           // bookName = itemView.findViewById(R.id.buyBookName);


        }
    }
}
