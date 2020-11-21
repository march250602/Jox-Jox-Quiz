package com.example.joxapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joxapplication.model.ScorePlayer;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    final Context mContext;
    final List<ScorePlayer> mWordList;


    MyAdapter(Context context,List<ScorePlayer> wordList) {
        this.mContext=context;
        mWordList=wordList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.score_list, parent, false);
        MyViewHolder vh = new MyViewHolder(mContext,v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.ScoreView.setText(String.valueOf(mWordList.get(position).Score));
        holder.NameView.setText(mWordList.get(position).Name);
        holder.item = mWordList.get(position);

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView NameView;
        TextView ScoreView;
        View rootView;
        ScorePlayer item;
        MyViewHolder(final Context context, @NonNull final View itemView) {
            super(itemView);
            rootView=itemView;
            NameView = itemView.findViewById(R.id.word_text_view);
            ScoreView= itemView.findViewById(R.id.word_text_view2);



        }
    }
}

