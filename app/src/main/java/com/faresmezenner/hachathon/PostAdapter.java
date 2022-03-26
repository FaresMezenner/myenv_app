package com.faresmezenner.hachathon;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    ArrayList<Post> posts;

    public PostAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.user.setText(posts.get(position).user);
        holder.rank.setText(posts.get(position).rank);
        holder.rate.setText(posts.get(position).rate);
        holder.title.setText(posts.get(position).title);
        holder.description.setText(posts.get(position).description);
        holder.postPic.setImageDrawable(posts.get(position).postPic);
        holder.userPfp.setImageDrawable(posts.get(position).userPfp);
        int rank = Integer.valueOf(posts.get(position).rank.toString());
        if(rank < 750){
            holder.rank.setTextColor(ContextCompat.getColor(context, R.color.red_flag));
        } else if (rank >= 750 && rank <=2000){

            holder.rank.setTextColor(ContextCompat.getColor(context, R.color.base));
        }else if (rank >=2000){

            holder.rank.setTextColor(ContextCompat.getColor(context, R.color.good));
        }

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}

