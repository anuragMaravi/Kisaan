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
import com.mearkiphi.kisaan.activity.SellActivity;
import com.mearkiphi.kisaan.models.TodoRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anuragmaravi on 14/03/17.
 */

public class SellAdapter extends RecyclerView.Adapter<SellAdapter.MyViewHolder>  {

    List<TodoRecord> data = new ArrayList<>();
    Context context;
    public SellAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final TodoRecord todoRecord = data.get(position);
        Glide.with(context).load(todoRecord.getImage()).into(holder.imageView);
        holder.textView.setText(todoRecord.getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SellActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("key", todoRecord.getCategory());
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
        TextView textView;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
