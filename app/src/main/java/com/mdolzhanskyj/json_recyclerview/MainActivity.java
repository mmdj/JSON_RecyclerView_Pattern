package com.mdolzhanskyj.json_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.mdolzhanskyj.json_recyclerview.adapters.AlbumRecyclerAdapter;
import com.mdolzhanskyj.json_recyclerview.model.Album;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;


public class MainActivity extends AppCompatActivity {
    ArrayList<Album> mImages;
    TreeSet<Integer> mAlbumsId;
    Gson gson;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get Data From Json and fill albums and images model
        getDataFromJson();


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_albums);

        // if changes in content are not change a RecyclerView's layout size
        //mRecyclerView.setHasFixedSize(true);


        // using linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //if we want to make adapter for images
        // mAdapter = new ImageRecyclerAdapter(mImages);

        //if we want to make adapter for albums
        mAdapter = new AlbumRecyclerAdapter(mAlbumsId);


        mRecyclerView.setAdapter(mAdapter);

        //making divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }









    /**
     * getJsonFromFile() <br>
     * and make ArrayList with objects <br>
     * and make TreeSet with albums numbers
     */
    private void getDataFromJson() {

         /* getting json from file: */
        String jsonStr = getJsonFromFile();     //TODO String jsonStr = getJsonFromHTTP()

         /* convert json from String to objects in ArrayList<Album>: */
        if (jsonStr != null) {
            //convert json to objects array
            Album[] albums = gson.fromJson(jsonStr, Album[].class);

            //all objects are going to ArrayList<Album>:
            mImages = new ArrayList<>(Arrays.asList(albums));

            //all albumsId are going to TreeSet(sorted and without doubles):
            mAlbumsId = new TreeSet<>();
            for (Album album : albums) {
                mAlbumsId.add(album.getAlbumId());
            }
        }
    }



    /**
     * getAsset from file
     * @return String with json
     */
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
