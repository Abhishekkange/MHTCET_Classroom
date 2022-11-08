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

import com.AbhishekKangeProductions.mhtcetclassroom.Models.dateModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.viewFormulaActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.AbhishekKangeProductions.mhtcetclassroom.R.drawable.bookmark;
import static com.AbhishekKangeProductions.mhtcetclassroom.R.drawable.bookmarkother;

public class dateAdapter extends RecyclerView.Adapter<dateAdapter.viewHolder> {

    ArrayList<dateModel> list;
    Context context;


    public dateAdapter(ArrayList<dateModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         View view = LayoutInflater.from(context).inflate(R.layout.previousyearspapersnew,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        dateModel model = list.get(position);
        holder.paperName.setText("Previous Year Paper "+model.getDate());
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(model.getUrl()));
                context.startActivity(intent);

            }
        });
        holder.Online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, viewFormulaActivity.class);
                intent.putExtra("url",model.getoUrl());
                context.startActivity(intent);

            }
        });
        holder.bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(model.isBookmark()){
                    holder.bookmark.setImageResource(bookmark);
                }else{
                    holder.bookmark.setImageResource(bookmarkother);
                }



            }
        });
        Picasso.get().load(model.getImage()).placeholder(R.drawable.mhtcetlogo).into(holder.image);








    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView paperName;
        ImageView image,bookmark;
        Button download,Online;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

           paperName = itemView.findViewById(R.id.newPreviousPapersTitle);
           image = itemView.findViewById(R.id.newpreviouspaperimage);
           download = itemView.findViewById(R.id.newPreviousyearpaperdownload);
           Online = itemView.findViewById(R.id.newPreviousyearpaperOnline);
           bookmark = itemView.findViewById(R.id.newPreviousYearPaperBookmarkimage);





        }
    }
}
