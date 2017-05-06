package com.mearkiphi.kisaan.activity;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.adapter.ItemsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> gymArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        recyclerView = (RecyclerView) findViewById(R.id.fragment_demo_recycler_view);
        recyclerView.setHasFixedSize(true);
        gymArrayList = new ArrayList<>();

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        ItemsAdapter adapter = new ItemsAdapter(gymArrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);

    }
}
