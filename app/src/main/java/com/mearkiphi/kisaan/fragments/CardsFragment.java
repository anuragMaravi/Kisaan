package com.mearkiphi.kisaan.fragments;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.adapter.CatAdapter;
import com.mearkiphi.kisaan.hasura.Hasura;
import com.mearkiphi.kisaan.models.SelectCatQuery;
import com.mearkiphi.kisaan.models.TodoRecord;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by anuragmaravi on 05/04/17.
 */

public class CardsFragment extends Fragment {
    private RecyclerView recyclerViewMoviesInTheatres;
    private CatAdapter adapter;
    private List<Movie> movieListInTheatres;
    private View rootView;
    private ProgressBar progressBar;



    public CardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cards, container, false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        fetchTodosFromDB();
        return rootView;
    }

    private void fetchTodosFromDB() {
        Hasura.db.getCat(new SelectCatQuery(1)).enqueue(new Callback<List<TodoRecord>>() {

            @Override
            public void onResponse(Call<List<TodoRecord>> call, Response<List<TodoRecord>> response) {
                Log.i("Trying", "onResponse: " + response.body());
                if (response.isSuccessful()) {

                    recyclerViewMoviesInTheatres = (RecyclerView) rootView.findViewById(R.id.recyclerViewMoviesInTheatres);
                    movieListInTheatres = new ArrayList<>();
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
                    recyclerViewMoviesInTheatres.setLayoutManager(mLayoutManager);
                    recyclerViewMoviesInTheatres.setItemAnimator(new DefaultItemAnimator());
                    adapter = new CatAdapter(getActivity());
                    adapter.setData(response.body());
                    recyclerViewMoviesInTheatres.setAdapter(adapter);
                    recyclerViewMoviesInTheatres.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                } else {
//                    handleError(response.errorBody());
                    Toast.makeText(getActivity(), "Some Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TodoRecord>> call, Throwable t) {
                Toast.makeText(getActivity(), "Some Error Occurred", Toast.LENGTH_SHORT).show();            }
        });
    }

}
