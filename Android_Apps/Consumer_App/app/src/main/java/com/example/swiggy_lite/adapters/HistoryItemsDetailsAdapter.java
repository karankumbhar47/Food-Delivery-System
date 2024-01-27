package com.example.swiggy_lite.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggy_lite.R;
import com.example.swiggy_lite.models.FoodModel;

import java.util.ArrayList;

public class HistoryItemsDetailsAdapter extends RecyclerView.Adapter<HistoryItemsDetailsAdapter.viewHolder> {
    ArrayList<FoodModel> itemList;

    public HistoryItemsDetailsAdapter(ArrayList<FoodModel> items) {
        this.itemList = items;
    }

    @NonNull
    @Override
    public HistoryItemsDetailsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_food_item_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemsDetailsAdapter.viewHolder holder, int position) {
        FoodModel singleItem = itemList.get(position);
        holder.name.setText(singleItem.getName());
        holder.vegLogo.setVisibility(singleItem.isPureVeg() ? View.VISIBLE : View.GONE);
        holder.nonVegLogo.setVisibility(!singleItem.isPureVeg() ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView vegLogo, nonVegLogo;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.food_item_name_textView);
            vegLogo = itemView.findViewById(R.id.veg_icon_imageView);
            nonVegLogo = itemView.findViewById(R.id.non_veg_imageView);
        }
    }
}
