package com.example.swiggy_lite.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggy_lite.R;
import com.example.swiggy_lite.models.foodModel;

import java.util.ArrayList;

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.viewHolder> {
    ArrayList<foodModel> list;

    public ItemDetailsAdapter(ArrayList<foodModel> items) {
        list = items;
    }

    @NonNull
    @Override
    public ItemDetailsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDetailsAdapter.viewHolder holder, int position) {
        foodModel singleItem = list.get(position);
        holder.item_name.setText(singleItem.getName());
        holder.rating.setText(singleItem.getRating());
        holder.location.setText(singleItem.getRender_location());
        //holder.item_list.setText();
        holder.item_price.setText(String.valueOf(singleItem.getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView item_name, rating , item_list, location, item_price;
        ImageView item_pic;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name_textView);
            rating = itemView.findViewById(R.id.ratings_textView);
            item_list = itemView.findViewById(R.id.food_list_textView);
            item_pic = itemView.findViewById(R.id.item_imageView);
            location = itemView.findViewById(R.id.shop_location_textView);
            item_price = itemView.findViewById(R.id.item_price);
        }
    }
}

