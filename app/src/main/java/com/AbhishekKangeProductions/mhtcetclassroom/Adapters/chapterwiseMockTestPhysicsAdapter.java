package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.chapterwiseMockTestModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.newActivities.mockTestChapterWiseActivity;

import java.util.ArrayList;

public class chapterwiseMockTestPhysicsAdapter extends RecyclerView.Adapter<chapterwiseMockTestPhysicsAdapter.viewHolder>{

    ArrayList<chapterwiseMockTestModel> list;
    Context context;

    public chapterwiseMockTestPhysicsAdapter(ArrayList<chapterwiseMockTestModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view = LayoutInflater.from(context).inflate(R.layout.mocktestchapterlistayout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        chapterwiseMockTestModel mockTestModel = list.get(position);
        holder.chapterName.setText(mockTestModel.getChapterName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, mockTestChapterWiseActivity.class);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView chapterName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            chapterName = itemView.findViewById(R.id.chapterwiseMockTestChapterName);

        }
    }
}
