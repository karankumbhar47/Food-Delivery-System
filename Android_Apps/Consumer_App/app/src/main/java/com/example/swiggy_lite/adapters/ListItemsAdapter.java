package com.example.swiggy_lite.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swiggy_lite.R;
import com.openapi.deliveryApp.model.FoodItem;
import com.openapi.deliveryApp.model.OrderItem;

import java.util.ArrayList;
import java.util.List;


public class ListItemsAdapter extends RecyclerView.Adapter<ListItemsAdapter.viewHolder> {
    List<FoodItem> searchList;
    Context context;
    String searchText = "";

    public void setList(List<FoodItem> updated_list, String searchText) {
        this.searchList = updated_list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

    public ListItemsAdapter(List<FoodItem> list, Context context) {
        searchList = list;
        this.context = context;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private static ListItemsAdapter.OnItemClickListener listener;
    public static void setOnItemClickListener(ListItemsAdapter.OnItemClickListener clickListener){
        listener = clickListener;
    }

    @NonNull
    @Override
    public ListItemsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemsAdapter.viewHolder holder, int position) {
        FoodItem singleItem = searchList.get(position);
        String name = singleItem.getName();

        int startIndex = name.toLowerCase().indexOf(searchText.toLowerCase());

        if (startIndex != -1) {
            SpannableString spannableString = new SpannableString(name);
            StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
            spannableString.setSpan(boldSpan, startIndex, startIndex + searchText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.name.setText(spannableString);
        } else {
            holder.name.setText(name);
        }

        holder.itemType.setText("Dish");
        holder.name.setOnClickListener(v -> listener.onItemClick(holder.getAdapterPosition()));
        holder.itemType.setOnClickListener(v -> listener.onItemClick(holder.getAdapterPosition()));
        holder.picImage.setOnClickListener(v -> listener.onItemClick(holder.getAdapterPosition()));

        int drawableId = holder.itemView.getResources()
                .getIdentifier("pizza_"+(position+1),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableId)
                .into(holder.picImage);
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView name, itemType;
        ImageView picImage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.food_item_name_textView);
            itemType = itemView.findViewById(R.id.list_type_textView);
            picImage = itemView.findViewById(R.id.food_item_imageView);
        }
    }
}

