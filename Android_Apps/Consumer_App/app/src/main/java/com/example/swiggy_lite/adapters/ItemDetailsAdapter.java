package com.example.swiggy_lite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swiggy_lite.R;
import com.openapi.deliveryApp.model.FoodItemFull;
import com.openapi.deliveryApp.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.viewHolder> {
    List<FoodItemFull> list;
    Context context;

    public ItemDetailsAdapter(List<FoodItemFull> items, Context context) {
        this.list = items;
        this.context = context;
    }

    public void setList(List<FoodItemFull> updated_list) {
        this.list = updated_list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private static CategoryAdapter.OnItemClickListener listener;
    public static void setOnItemClickListener(CategoryAdapter.OnItemClickListener clickListener){
        listener = clickListener;
    }

    @NonNull
    @Override
    public ItemDetailsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDetailsAdapter.viewHolder holder, int position) {
        FoodItemFull singleItem = list.get(position);
        holder.item_name.setText(singleItem.getItemName());
        holder.rating.setText(String.format("%s(500+ rating)", singleItem.getStarRating()));
        holder.location.setText(singleItem.getVendorLocation());

        int drawableId = holder.itemView.getResources()
                .getIdentifier("restaurant_"+(position+1),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableId)
                .into(holder.item_pic);
        //holder.item_list.setText();
        holder.item_price.setText(String.valueOf(singleItem.getPrice()));
        holder.item_pic.setOnClickListener(v -> listener.onItemClick(holder.getAdapterPosition()));
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
            item_list = itemView.findViewById(R.id.food_description_textView);
            item_pic = itemView.findViewById(R.id.food_item_imageView);
            location = itemView.findViewById(R.id.shop_location_textView);
            item_price = itemView.findViewById(R.id.item_price);
        }
    }
}

