package com.mearkiphi.kisaan.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.activity.ListActivity;

import java.util.List;

/**
 * Created by anuragmaravi on 14/03/17.
 */

public class SellItemAdapter extends RecyclerView.Adapter<SellItemAdapter.MyViewHolder>  {

    private Context mContext;
    private List<Movie> movieList = null;
    private String[] itemNames = {"Vegetables", "Fruits", "Spices", "Spices", "Spices", "Spices", "Spices", "Spices"};


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categories, parent, false);
        return new SellItemAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(itemNames[position]);
//        Glide.with(mContext).load(movie.getPosterPath()).into(holder.thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ListActivity.class);
                intent.putExtra("movie_id", "Hello");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemNames.length;
    }

    public SellItemAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
