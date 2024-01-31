package com.example.vendor_app.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendor_app.R;

import java.util.ArrayList;


public class menuCardAdapter extends RecyclerView.Adapter<menuCardAdapter.viewHolder> {

    ArrayList<String> list;

    public menuCardAdapter(ArrayList<String> items) {
        list = items;
    }

    @NonNull
    @Override
    public menuCardAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull menuCardAdapter.viewHolder holder, int position) {
        String singleItem = list.get(position);

        holder.name.setText(singleItem);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.menu_item_name);
        }
    }
}

