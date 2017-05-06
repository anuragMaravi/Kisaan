package com.mearkiphi.kisaan.fragments;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.adapter.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by anuragmaravi on 05/04/17.
 */

public class CardsFragment extends Fragment {
    private RecyclerView recyclerViewMoviesInTheatres;
    private MoviesAdapter adapter;
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


        /**
         * In Theatres Movies
         */
        recyclerViewMoviesInTheatres = (RecyclerView) rootView.findViewById(R.id.recyclerViewMoviesInTheatres);
        movieListInTheatres = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewMoviesInTheatres.setLayoutManager(mLayoutManager);
        recyclerViewMoviesInTheatres.setItemAnimator(new DefaultItemAnimator());
        adapter = new MoviesAdapter(getActivity(), movieListInTheatres);
                            recyclerViewMoviesInTheatres.setAdapter(adapter);
                            recyclerViewMoviesInTheatres.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, MOVIE_NOW_PLAYING_REQUEST,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            Log.i("Volley", "onResponse(MovieInTheatres): " + response);
//                            JSONObject parentObject = new JSONObject(response);
//                            JSONArray parentArray = parentObject.getJSONArray("results");
//                            for(int i=0;i<parentArray.length();i++){
//                                JSONObject finalObject = parentArray.getJSONObject(i);
//                                Movie movieModel = new Movie();
//                                movieModel.setId(finalObject.getString("id"));
//                                movieModel.setPosterPath(Contract.API_IMAGE_URL + finalObject.getString("poster_path"));
//
//                                movieListInTheatres.add(movieModel);
//                            }
//                            adapter = new MoviesAdapter(getActivity(), movieListInTheatres);
//                            recyclerViewMoviesInTheatres.setAdapter(adapter);
//                            recyclerViewMoviesInTheatres.setVisibility(View.VISIBLE);
//                            progressBar.setVisibility(View.GONE);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();
//            }
//        });
//        // Add the request to the RequestQueue.
//        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        return rootView;
    }


}
