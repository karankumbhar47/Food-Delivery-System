package com.example.swiggy_lite.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggy_lite.R;

import java.util.ArrayList;


public class demoAdapter extends RecyclerView.Adapter<demoAdapter.viewHolder> {
    ArrayList<ArrayList<String>> list;

    public demoAdapter(ArrayList<ArrayList<String>> items) {
        list = items;
    }

    @NonNull
    @Override
    public demoAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull demoAdapter.viewHolder holder, int position) {
        ArrayList<String> singleItem = list.get(position);
        holder.name.setText(singleItem.get(0));
        holder.price.setText(singleItem.get(1));
        holder.review.setText(singleItem.get(2));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView name, price , review ;
        ImageView picImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.food_name);
            price = itemView.findViewById(R.id.price);
            review = itemView.findViewById(R.id.review);
            picImage = itemView.findViewById(R.id.profile);
        }
    }
}
