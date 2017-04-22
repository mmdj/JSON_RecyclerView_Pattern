package com.mdolzhanskyj.json_recyclerview.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mdolzhanskyj.json_recyclerview.R;
import com.mdolzhanskyj.json_recyclerview.model.Album;

import java.util.List;

/**
 *  Created by mmdj on 19.04.2017.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {

    private List<Album> album;


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton mImageButton_album;
        TextView mTxtVw_albumId, mTxtVw_imageId, mTxtVw_title;

        ViewHolder(View v) {
            super(v);
            mTxtVw_albumId = (TextView) v.findViewById(R.id.txtVw_albumId);
            mTxtVw_imageId = (TextView) v.findViewById(R.id.txtVw_imageId);
            mTxtVw_title = (TextView) v.findViewById(R.id.txtVw_title);
            mImageButton_album = (ImageButton) v.findViewById(R.id.imageButton);


        }
    }


    public ImageRecyclerAdapter(List<Album> album) {
        this.album = album;
    }


    // make new views (from layout manager)
    @Override
    public ImageRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row, parent, false);

        // here can change  layout attributs (to all rows - size, margins, paddings...)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    //Change a content to a view (one row)(called by layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Log.d("TAG","album0  "+ album.get(0).toString());

        //change background to even rows:
        if (holder.getAdapterPosition() % 2 != 0) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else holder.itemView.setBackgroundColor(Color.WHITE);



      // set content to recycler_view_row.xml
       holder.mTxtVw_albumId.setText("album #"+album.get(position).getAlbumId());
        holder.mTxtVw_imageId.setText("image #"+album.get(position).getId());
        holder.mTxtVw_title.setText(album.get(position).getTitle());
       // holder.mImageButton_album.setImageURI(album.get(position).getUrl());

    }

    @Override
    public int getItemCount() {
        return album.size();
    }
}


