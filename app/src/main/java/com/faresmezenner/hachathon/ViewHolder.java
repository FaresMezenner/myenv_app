package com.faresmezenner.hachathon;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView user, rank,rate,title,description;
    public ImageView postPic, userPfp;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        user = itemView.findViewById(R.id.user);
        rank = itemView.findViewById(R.id.rank);
        rate = itemView.findViewById(R.id.rate);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        postPic = itemView.findViewById(R.id.post_pic);
        userPfp = itemView.findViewById(R.id.user_pfp);
    }
}
