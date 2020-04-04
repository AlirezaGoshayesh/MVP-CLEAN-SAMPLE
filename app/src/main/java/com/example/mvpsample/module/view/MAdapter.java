package com.example.mvpsample.module.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mvpsample.R;
import com.example.mvpsample.data.model.UserModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MAdapter extends RecyclerView.Adapter<MAdapter.ViewHolder> {

    private List<UserModel> wallPapers;
    private Activity activity;

    public MAdapter(List<UserModel> wallPapers, Activity activity) {
        this.wallPapers = wallPapers;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(activity.getLayoutInflater().inflate(R.layout.user_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        UserModel user = wallPapers.get(i);
        viewHolder.textView.setText(user.getName());
    }



    @Override
    public int getItemCount() {
        return wallPapers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

}
