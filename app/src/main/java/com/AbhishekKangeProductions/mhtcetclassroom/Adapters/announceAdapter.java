package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.announceModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class announceAdapter extends RecyclerView.Adapter<announceAdapter.viewHolder> {

    ArrayList<announceModel> list;
    Context context;

    public announceAdapter(ArrayList<announceModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.announcementsample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        announceModel model = list.get(position);
        holder.title.setText(model.getTitle());
        holder.date.setText(model.getDate());
        Picasso.get().load(model.getImage()).placeholder(R.drawable.mhtcetlogo).into(holder.image);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(context, fullAnnouncmentActivity.class);
//                intent.putExtra("announceID",model.getId());
//                intent.putExtra("image",model.getDate());
//                context.startActivity(intent);
//
//
//            }
//        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView title,date;
        ImageView image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.newAccouncementDate);
            title= itemView.findViewById(R.id.newAnnouncmentTitle);
            image = itemView.findViewById(R.id.announceImage);




        }
    }
}
