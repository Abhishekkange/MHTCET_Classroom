package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.sendMessageModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class personalChattingAdapter extends RecyclerView.Adapter {


    ArrayList<sendMessageModel> list;
    Context context;
    FirebaseAuth mAuth;
    String myId = FirebaseAuth.getInstance().getUid();

    int SENDER_MESSAGE_VIEW = 1;
    int REIEVER_MESSAGE_VIEW = 2;

    public personalChattingAdapter(ArrayList<sendMessageModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType==SENDER_MESSAGE_VIEW){

            View view = LayoutInflater.from(context).inflate(R.layout.senderlayout,parent,false);
            return new senderViewHolder(view);

        }
        else {

            View view = LayoutInflater.from(context).inflate(R.layout.recieverlayout,parent,false);
            return new senderViewHolder(view);




        }

    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        sendMessageModel model = list.get(position);
        if (holder.getClass()== senderViewHolder.class){

            ((senderViewHolder)holder).senderText.setText(model.getMessage());

        }
        else {

            ((recieverViewHolder)holder).Recievermessage.setText(model.getMessage());
        }


    }

    @Override
    public int getItemViewType(int position) {


//        return SENDER_MESSAGE_VIEW;
        if(list.get(position).getSenderId().equals(myId)){

            return SENDER_MESSAGE_VIEW;

        }else {

            return SENDER_MESSAGE_VIEW;

        }







    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class recieverViewHolder extends RecyclerView.ViewHolder{

        TextView Recievermessage;
        TextView time;

        public recieverViewHolder(@NonNull View itemView) {
            super(itemView);

            Recievermessage = itemView.findViewById(R.id.recieverMsg);
            mAuth = FirebaseAuth.getInstance();


        }
    }


    public class senderViewHolder extends RecyclerView.ViewHolder{


        TextView senderText ;


        public senderViewHolder(@NonNull View itemView) {
            super(itemView);

            senderText = itemView.findViewById(R.id.senderMsg);
            mAuth = FirebaseAuth.getInstance();





        }
    }




}
