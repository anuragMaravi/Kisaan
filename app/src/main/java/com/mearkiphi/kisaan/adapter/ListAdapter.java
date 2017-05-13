package com.mearkiphi.kisaan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mearkiphi.kisaan.R;
import com.mearkiphi.kisaan.activity.BuyActivity;
import com.mearkiphi.kisaan.models.TodoRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anuragmaravi on 14/03/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>  {

    List<TodoRecord> data = new ArrayList<>();
    Context context;
    public ListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gym_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final TodoRecord todoRecord = data.get(position);
        Glide.with(context).load(todoRecord.getImage()).into(holder.imageView);
        holder.textViewGymName.setText(todoRecord.getSubCategory());
        holder.textViewStarting.setText(String.valueOf(todoRecord.getRate()));
        holder.textViewGymAddress.setText(todoRecord.getCategory());
        holder.textViewGymCategory.setText(todoRecord.getLocation());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BuyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("key", todoRecord.getCategory());
                intent.putExtra("id", todoRecord.getId());
                intent.putExtra("sub_category", todoRecord.getSubCategory());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<TodoRecord> recordList) {
        this.data = recordList;
        notifyDataSetChanged();
    }

    public void deleteData(int position, TodoRecord record) {
        this.data.remove(position);
        notifyDataSetChanged();
    }

    public void updateData(int position, TodoRecord record) {
        this.data.set(position,record);
        notifyDataSetChanged();
    }

    public void addData(TodoRecord record) {
        this.data.add(record);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewGymName, textViewGymAddress, textViewGymCategory, textViewStarting;
        public ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewGymName = (TextView) itemView.findViewById(R.id.textViewGymName);
            textViewGymAddress = (TextView) itemView.findViewById(R.id.textViewGymAddress);
            textViewGymCategory = (TextView) itemView.findViewById(R.id.textViewGymCategory);
            textViewStarting = (TextView) itemView.findViewById(R.id.textViewStarting);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
