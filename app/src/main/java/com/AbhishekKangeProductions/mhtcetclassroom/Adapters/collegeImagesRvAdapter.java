package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.collegeImagesRvModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class collegeImagesRvAdapter extends RecyclerView.Adapter<collegeImagesRvAdapter.viewHolder> {

    ArrayList<collegeImagesRvModel> list;
    Context context;

    public collegeImagesRvAdapter(ArrayList<collegeImagesRvModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.college_image_sample,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        collegeImagesRvModel model = list.get(position);
        Picasso.get().load(model.getImage()).into(holder.collegeImage);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView collegeImage;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            collegeImage = itemView.findViewById(R.id.collegeImageRvImg);

        }
    }
}
