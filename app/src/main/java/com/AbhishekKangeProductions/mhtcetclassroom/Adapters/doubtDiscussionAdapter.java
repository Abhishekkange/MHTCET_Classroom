package com.AbhishekKangeProductions.mhtcetclassroom.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.AbhishekKangeProductions.mhtcetclassroom.Models.doubtDiscussionModel;
import com.AbhishekKangeProductions.mhtcetclassroom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class doubtDiscussionAdapter extends RecyclerView.Adapter<doubtDiscussionAdapter.viewHolder> {

    ArrayList<doubtDiscussionModel> list;
    Context context;

    public doubtDiscussionAdapter(ArrayList<doubtDiscussionModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.doubt_sample,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        doubtDiscussionModel model = list.get(position);
       // holder.question.setText(model.getQuestion());
        holder.userName.setText(model.getUserName());
        Picasso.get().load(model.getUserProfile()).placeholder(R.drawable.profile).into(holder.userProfile);
        Picasso.get().load(model.getQuestionImage()).into(holder.questionImage);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView userName,question;
        ImageView userProfile,questionImage;
        Button answerTheQuestion;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.doubtDiscussionUserName);
            userProfile = itemView.findViewById(R.id.doubtDiscussionProfileImage);
            questionImage = itemView.findViewById(R.id.doubtDiscussionDoubtImage);
            answerTheQuestion = itemView.findViewById(R.id.answerQuestionBtn);
            question = itemView.findViewById(R.id.doubtDiscussionQuestion);


        }
    }

}
