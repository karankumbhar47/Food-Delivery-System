package com.example.swiggy_lite.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggy_lite.R;
import com.example.swiggy_lite.models.FoodModel;

import java.util.ArrayList;


public class PlaceOrderAdapter extends RecyclerView.Adapter<PlaceOrderAdapter.viewHolder> {
    ArrayList<FoodModel> foodItemList;

    public PlaceOrderAdapter(ArrayList<FoodModel> items) {
        foodItemList = items;
    }

    private PlaceOrderAdapter.OnItemClickListener listener;
    public interface OnItemClickListener{
        void onMinusClick(int position, int quantity);
        void onPlusClick(int position, int quantity);
    }
    public void setOnItemClickListener(PlaceOrderAdapter.OnItemClickListener clickListener){
        listener = clickListener;
    }

    public void setList(ArrayList<FoodModel> updateList){
        this.foodItemList = updateList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceOrderAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceOrderAdapter.viewHolder holder, int position) {
        FoodModel singleItem = foodItemList.get(position);
        holder.name.setText(singleItem.getName());
        holder.price.setText(String.format("₹ %s", String.valueOf(singleItem.getPrice() * singleItem.getQuantity())));
        holder.vegFoodType.setVisibility(singleItem.isPureVeg() ? View.VISIBLE : View.GONE);
        holder.nonVegFoodType.setVisibility(singleItem.isPureVeg() ? View.GONE : View.VISIBLE);
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

    public class viewHolder extends RecyclerView.ViewHolder{

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
