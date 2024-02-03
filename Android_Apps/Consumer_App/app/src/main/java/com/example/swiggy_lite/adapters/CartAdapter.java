package com.example.swiggy_lite.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggy_lite.R;
import com.openapi.deliveryApp.model.OrderItem;

import java.math.RoundingMode;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder> {
    public interface OnItemClickListener{
        void onMinusClick(int position, int quantity);
        void onPlusClick(int position, int quantity);
    }

    List<OrderItem> foodItemList;
    private CartAdapter.OnItemClickListener listener;

    public CartAdapter(List<OrderItem> items) {
        foodItemList = items;
    }

    public void setOnItemClickListener(CartAdapter.OnItemClickListener clickListener){
        listener = clickListener;
    }

    public void setList(List<OrderItem> updateList){
        this.foodItemList = updateList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewHolder holder, int position) {
        OrderItem singleItem = foodItemList.get(position);
        holder.name.setText(singleItem.getItemName());
        holder.price.setText(String.format("₹ %s", String.valueOf((singleItem.getPrice()*singleItem.getQuantity()))));
        //holder.vegFoodType.setVisibility(singleItem.isPureVeg() ? View.VISIBLE : View.GONE);
        //holder.nonVegFoodType.setVisibility(singleItem.isPureVeg() ? View.GONE : View.VISIBLE);
        holder.numberText.setText(String.valueOf(singleItem.getQuantity()));

        holder.plusImageView.setOnClickListener(v -> {
            listener.onPlusClick(position,singleItem.getQuantity()+1);
        });
        holder.minusImageView.setOnClickListener(v -> listener.onMinusClick(position,singleItem.getQuantity()-1));

    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView name, price , numberText;
        ImageView vegFoodType,  nonVegFoodType;
        ImageView plusImageView, minusImageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.food_item_name_textView);
            price = itemView.findViewById(R.id.price_textView);
            numberText = itemView.findViewById(R.id.numberText);
            vegFoodType = itemView.findViewById(R.id.veg_icon_imageView);
            nonVegFoodType = itemView.findViewById(R.id.non_veg_imageView);
            plusImageView = itemView.findViewById(R.id.plusIcon);
            minusImageView = itemView.findViewById(R.id.minusIcon);
        }
    }
}
