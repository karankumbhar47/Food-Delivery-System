package com.example.swiggy_lite.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggy_lite.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CuisinesAdapter extends RecyclerView.Adapter<CuisinesAdapter.viewHolder> {
    public static HashMap<String,Boolean> cuisine_map;
    public static ArrayList<String> cuisine_list;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private static CategoryAdapter.OnItemClickListener listener;
    public static void setOnItemClickListener(CategoryAdapter.OnItemClickListener clickListener){
        listener = clickListener;
    }

    public CuisinesAdapter(HashMap<String , Boolean> items) {
        cuisine_map = items;
        cuisine_list = new ArrayList<>(cuisine_map.keySet());
    }
    public void setCuisine_list(HashMap<String , Boolean> updatedMap){
        cuisine_map = updatedMap;
        cuisine_list = new ArrayList<>(updatedMap.keySet());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CuisinesAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuisine_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CuisinesAdapter.viewHolder holder, int position) {
        String cuisine_name = cuisine_list.get(position);
        holder.cuisine_checkbox.setText(cuisine_name);
        holder.cuisine_checkbox.setChecked(Boolean.TRUE.equals(cuisine_map.get(cuisine_name)));

        holder.cuisine_checkbox.setOnClickListener(v -> { listener.onItemClick(position);});
    }

    @Override
    public int getItemCount() {
        return cuisine_map.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        CheckBox cuisine_checkbox;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cuisine_checkbox = itemView.findViewById(R.id.cuisine_item_checkbox);
        }
    }
}
