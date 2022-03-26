package com.faresmezenner.hachathon;

import android.graphics.drawable.Drawable;

import androidx.recyclerview.widget.RecyclerView;

public class Post{
    public String user, rank,rate,title, description;
    public Drawable postPic, userPfp;
    public Post(String user, String rank, String rate, String title, String description, Drawable postPic, Drawable userPfp){
        this.user = user;
        this.rank = rank;
        this.rate = rate;
        this.title = title;
        this.description = description;
        this.postPic = postPic;
        this.userPfp = userPfp;
    }
}
