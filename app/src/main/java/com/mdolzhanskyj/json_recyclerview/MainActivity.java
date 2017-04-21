package com.mdolzhanskyj.json_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.mdolzhanskyj.json_recyclerview.adapters.RecyclerAdapter;
import com.mdolzhanskyj.json_recyclerview.model.Album;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    ArrayList<Album> mAlbums;
    Gson gson;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* getting json from file: */
        String jsonStr = getJsonFromFile();     //TODO String jsonStr = getJsonFromHTTP()


        /* convert json from String to objects in ArrayList<Album>: */
        if (jsonStr != null) {
            Album[] albums = gson.fromJson(jsonStr, Album[].class);
            mAlbums = new ArrayList<>(Arrays.asList(albums));
            Log.i("TAG", "Album " + albums[0].toString());
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_albums);

        // if changes in content are not change a RecyclerView's layout size
        //mRecyclerView.setHasFixedSize(true);


        // using linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // making adapter
        mAdapter = new RecyclerAdapter(mAlbums);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }





    private String getJsonFromFile() {

        String res = null;
        gson = new Gson();

        try {
            InputStream is = getAssets().open("alboms.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            res = new String(buffer, "UTF-8");
            //  Log.d("TAG", "loadJSONFromAsset: " + res);

        } catch (IOException ex) {
            Log.d("TAG", "ERROR loadJSONFromAsset : " + ex.toString());
            ex.printStackTrace();
        }
        return res;
    }


}
