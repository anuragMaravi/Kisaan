package com.mearkiphi.kisaan.activity;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.adapter.ItemsAdapter;
import com.mearkiphi.kisaan.hasura.Hasura;
import com.mearkiphi.kisaan.models.SelectTodoQuery;
import com.mearkiphi.kisaan.models.TodoRecord;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> gymArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        fetchTodosFromDB();


        recyclerView = (RecyclerView) findViewById(R.id.fragment_demo_recycler_view);
        recyclerView.setHasFixedSize(true);
        gymArrayList = new ArrayList<>();

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        ItemsAdapter adapter = new ItemsAdapter(gymArrayList, getApplicationContext());
        recyclerView.setAdapter(adapter);

    }

    private void fetchTodosFromDB() {
        Hasura.db.getTodos(new SelectTodoQuery(Hasura.getUserId())).enqueue(new Callback<List<TodoRecord>>() {

            @Override
            public void onResponse(Call<List<TodoRecord>> call, Response<List<TodoRecord>> response) {
                Log.i("Trying", "onResponse: " + response.body().get(0).getTitle());
//                if (response.isSuccessful()) {
//                    adapter.setData(response.body());
//                } else {
//                    handleError(response.errorBody());
//                }
            }

            @Override
            public void onFailure(Call<List<TodoRecord>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();            }
        });
    }
}
