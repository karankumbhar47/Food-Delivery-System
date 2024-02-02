package com.example.swiggy_lite.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.collection.ObjectIntMap;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swiggy_lite.MainFragments.CartFragment;
import com.example.swiggy_lite.MainPage;
import com.example.swiggy_lite.R;
import com.openapi.deliveryApp.model.FoodItemFull;
import com.openapi.deliveryApp.model.OrderItem;

import java.util.List;
import java.util.Map;


public class RestaurantMenuAdapter extends RecyclerView.Adapter<RestaurantMenuAdapter.viewHolder> {
    Context context;
    List<FoodItemFull> menuList;
    Map<String,OrderItem> orderItemMap;
    private static RestaurantMenuAdapter.OnItemClickListener listener;

    public RestaurantMenuAdapter(List<FoodItemFull> items, Context context) {
        this.menuList = items;
        this.context = context;
        this.orderItemMap = MainPage.orderItemMap;
    }

    public void changeQuantityList() {
        this.orderItemMap = MainPage.orderItemMap;
        notifyDataSetChanged();
    }

    public void setList(List<FoodItemFull> updated_list) {
        this.menuList = updated_list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onMinusClick(int position, int quantity);
        void onPlusClick(int position, int quantity);
    }

    public static void setOnItemClickListener(RestaurantMenuAdapter.OnItemClickListener clickListener){
        listener = clickListener;
    }

    @NonNull
    @Override
    public RestaurantMenuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_menu_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        FoodItemFull singleItem = menuList.get(position);

        int drawableId = holder.itemView.getResources().getIdentifier("restaurant_"+(position+1),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableId).into(holder.item_pic);

        holder.item_name.setText(singleItem.getItemName());
        holder.rating.setText(String.format("%s (1k+)", singleItem.getStarRating()));
        holder.item_description.setText(singleItem.getVendorLocation());
        holder.item_price.setText(String.format("₹ %s", String.valueOf(singleItem.getPrice())));

        if (orderItemMap.get(singleItem.getItemId()) != null) {
                OrderItem orderItem = orderItemMap.get(singleItem.getItemId());
                holder.add_button.setVisibility(View.GONE);
                holder.number_picker.setVisibility(View.VISIBLE);
                holder.number_text.setText(String.valueOf(orderItem.getQuantity()));
                holder.minusImageView.setOnClickListener(v -> listener.onMinusClick(position, orderItem.getQuantity() - 1));
                holder.plusImageView.setOnClickListener(v -> listener.onPlusClick(position, orderItem.getQuantity() + 1));
        } else {
                holder.add_button.setVisibility(View.VISIBLE);
                holder.number_picker.setVisibility(View.GONE);
        }

         holder.add_button.setOnClickListener(v -> {
            holder.add_button.setVisibility(View.GONE);
            holder.number_picker.setVisibility(View.VISIBLE);
            listener.onPlusClick(position,1);
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView item_name, rating , item_description, item_price, number_text;
        ImageView item_pic, veg_imageView, nonVeg_imageView, plusImageView, minusImageView;
        CardView add_button;
        ConstraintLayout number_picker;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name_textView);
            rating = itemView.findViewById(R.id.ratings_textView);
            item_description = itemView.findViewById(R.id.food_description_textView);
            item_pic = itemView.findViewById(R.id.food_item_imageView);
            item_price = itemView.findViewById(R.id.item_price_textView);
            nonVeg_imageView = itemView.findViewById(R.id.non_veg_imageView);
            veg_imageView = itemView.findViewById(R.id.veg_icon_imageView);
            plusImageView = itemView.findViewById(R.id.plusIcon);
            minusImageView = itemView.findViewById(R.id.minusIcon);
            add_button = itemView.findViewById(R.id.add_button_cardView);
            number_picker = itemView.findViewById(R.id.numberPicker_constrainLayout);
            number_text = itemView.findViewById(R.id.numberText);
        }
    }
//
//    public OrderItem getOrderItem(String itemId){
//        if(orderItemMap !=null) {
//            for (OrderItem orderItem : orderItemMap) {
//                if (orderItem.getItemId().equals(itemId)) {
//                    return orderItem;
//                }
//            }
//        }
//        return null;
//    }

}

