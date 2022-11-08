package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.testListModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.AbhishekKangeProductions.mhtcetclassroom.instructions;

import java.util.ArrayList;


public class testListAdapter extends RecyclerView.Adapter<testListAdapter.viewHolder> {

  Context context;
  ArrayList<testListModel> list;

    public testListAdapter(Context context, ArrayList<testListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.testlistsample,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        testListModel model = list.get(position);
        holder.mytestNo.setText(model.getTestNo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, instructions.class);
                intent.putExtra("theTestNo",model.getTestNo());
                context.startActivity(intent);



            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  viewHolder extends RecyclerView.ViewHolder {

        TextView mytestNo;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mytestNo = itemView.findViewById(R.id.testnoid);

        }

    }
}
