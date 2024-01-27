package com.example.swiggy_lite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.models.FoodModel;

import java.util.ArrayList;


public class RestaurantMenuAdapter extends RecyclerView.Adapter<RestaurantMenuAdapter.viewHolder> {
    ArrayList<FoodModel> menuList;
    Context context;

    public RestaurantMenuAdapter(ArrayList<FoodModel> items,Context context) {
        menuList = items;
        this.context = context;
    }


    public void setList(ArrayList<FoodModel> updated_list) {
        this.menuList = updated_list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onMinusClick(int position, int quantity);
        void onPlusClick(int position, int quantity);
    }


    private static RestaurantMenuAdapter.OnItemClickListener listener;
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
        FoodModel singleItem = menuList.get(position);
        holder.item_name.setText(singleItem.getName());
        holder.rating.setText(singleItem.getRating() + " (1k+)");
        holder.item_description.setText(singleItem.getRender_location());
        int drawableId = holder.itemView.getResources()
                .getIdentifier("restaurant_"+(position+1),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableId)
                .into(holder.item_pic);
        //holder.item_list.setText();
        holder.item_price.setText(String.format("₹ %s", String.valueOf(singleItem.getPrice())));
        holder.minusImageView.setOnClickListener(v -> {
            if(singleItem.getQuantity()-1<=0){
                holder.add_button.setVisibility(holder.add_button.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
                holder.number_picker.setVisibility(holder.number_picker.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
                notifyDataSetChanged();
            }
            listener.onMinusClick(position,singleItem.getQuantity()-1);
        });
        holder.plusImageView.setOnClickListener(v -> listener.onPlusClick(position,singleItem.getQuantity()+1));
        holder.add_button.setOnClickListener(v -> {
            holder.add_button.setVisibility(holder.add_button.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
            holder.number_picker.setVisibility(holder.number_picker.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
            notifyDataSetChanged();
        }
        );
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView item_name, rating , item_description, item_price;
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
        }
    }
}

