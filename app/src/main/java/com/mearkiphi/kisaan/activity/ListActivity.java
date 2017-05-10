package com.mearkiphi.kisaan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.adapter.ListAdapter;
import com.mearkiphi.kisaan.hasura.Hasura;
import com.mearkiphi.kisaan.models.SelectItemDetailsQuery;
import com.mearkiphi.kisaan.models.TodoRecord;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMoviesInTheatres;
    private ListAdapter adapter;
//    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle(getIntent().getStringExtra("key"));
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        fetchTodosFromDB();
    }


    private void fetchTodosFromDB() {
        Hasura.db.getItemDetails(new SelectItemDetailsQuery(getIntent().getStringExtra("key"))).enqueue(new Callback<List<TodoRecord>>() {

            @Override
            public void onResponse(Call<List<TodoRecord>> call, Response<List<TodoRecord>> response) {
                if (response.isSuccessful()) {

                    recyclerViewMoviesInTheatres = (RecyclerView) findViewById(R.id.fragment_demo_recycler_view);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerViewMoviesInTheatres.setLayoutManager(mLayoutManager);
                    recyclerViewMoviesInTheatres.setItemAnimator(new DefaultItemAnimator());
                    adapter = new ListAdapter(getApplicationContext());
                    adapter.setData(response.body());
                    recyclerViewMoviesInTheatres.setAdapter(adapter);
                    recyclerViewMoviesInTheatres.setVisibility(View.VISIBLE);
//                    progressBar.setVisibility(View.GONE);

                } else {
//                    handleError(response.errorBody());
                    Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TodoRecord>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
