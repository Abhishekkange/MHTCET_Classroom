package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.showClassroomModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;

import java.util.ArrayList;

public class classroomShowAdapter extends RecyclerView.Adapter<classroomShowAdapter.viewHolder> {

    ArrayList<showClassroomModel> list;
    Context context;

    public classroomShowAdapter(ArrayList<showClassroomModel> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.showclassroomlayout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        showClassroomModel model = list.get(position);
        holder.classroomName.setText(model.getClassroomName());
        holder.classroomTime.setText(model.getClassroomTime());
        holder.classrooomDate.setText(model.getClassroomDate());
        holder.classroomHost.setText(model.getClassroomHost());





    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView classroomName,classrooomDate,classroomTime,classroomHost;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            classroomName = itemView.findViewById(R.id.classroomNameShow);
            classroomTime = itemView.findViewById(R.id.timeShow);
            classrooomDate = itemView.findViewById(R.id.dateShow);
            classroomHost = itemView.findViewById(R.id.showClassroomHostName);



        }
    }
}
