package com.mdolzhanskyj.json_recyclerview.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mdolzhanskyj.json_recyclerview.R;

import java.util.TreeSet;

/**
 * Created by mmdj on 22.04.2017.
 */


    public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.ViewHolder> {


        private TreeSet<Integer> albumsList;

        static class ViewHolder extends RecyclerView.ViewHolder {

            ImageButton  mImageBtn_album;
            TextView mTxtVw_albumRow_albumId;

            ViewHolder(View v) {
                super(v);
                mImageBtn_album = (ImageButton) v.findViewById(R.id.imageBtn_album);
                mTxtVw_albumRow_albumId = (TextView) v.findViewById(R.id.txtVw_albumRow_albumId);
            }
        }


        public AlbumRecyclerAdapter(TreeSet<Integer>  albumsList) {
            this.albumsList = albumsList;
        }

        // make new views (from layout manager)
        @Override
        public AlbumRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_view_album_row, parent, false);

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
       holder.mTxtVw_albumRow_albumId.setText("album #"+albumsList.toArray()[position]);

       // holder.mImageButton_album.setImageURI(album.get(position).getUrl());


        }

        @Override
        public int getItemCount() {
            return albumsList.size();
        }
    }




