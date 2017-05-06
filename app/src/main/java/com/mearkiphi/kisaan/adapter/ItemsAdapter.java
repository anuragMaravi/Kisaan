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
import com.mearkiphi.kisaan.activity.ItemDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anuragmaravi on 06/02/17.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

	private List<Movie> mDataset = new ArrayList<>();
	private Context context;


	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView textViewGymName, textViewGymAddress, textViewGymCategory, textViewStarting;
		public ImageView imageView;
		public ViewHolder(View v) {
			super(v);
			textViewGymName = (TextView) v.findViewById(R.id.textViewGymName);
			textViewGymAddress = (TextView) v.findViewById(R.id.textViewGymAddress);
			textViewGymCategory = (TextView) v.findViewById(R.id.textViewGymCategory);
			textViewStarting = (TextView) v.findViewById(R.id.textViewStarting);
			imageView = (ImageView) v.findViewById(R.id.imageView);
		}
	}

	public ItemsAdapter(List<Movie> dataset, Context context) {
		mDataset.clear();
		mDataset = dataset;
		this.context = context;
	}

	@Override
	public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gym_list, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

//		final GymList gymList = mDataset.get(position);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, ItemDetailActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("gymId", "Hello");
		context.startActivity(intent);
	}
});
	}

	@Override
	public int getItemCount() {
		return 20;
	}

}
