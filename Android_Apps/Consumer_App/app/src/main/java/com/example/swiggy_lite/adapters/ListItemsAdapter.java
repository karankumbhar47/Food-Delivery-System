package com.example.swiggy_lite.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.models.FoodModel;

import java.util.ArrayList;


public class ListItemsAdapter extends RecyclerView.Adapter<ListItemsAdapter.viewHolder> {
    ArrayList<FoodModel> searchList;
    Context context;
    String searchText = "";

    public void setList(ArrayList<FoodModel> updated_list, String searchText) {
        this.searchList = updated_list;
        this.searchText = searchText;
        notifyDataSetChanged();
    }

    public ListItemsAdapter(ArrayList<FoodModel> list, Context context) {
        searchList = list;
        this.context = context;
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
    public ListItemsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemsAdapter.viewHolder holder, int position) {
        FoodModel singleItem = searchList.get(position);
        String name = singleItem.getName();
        holder.itemType.setText("Dish");

        int startIndex = name.toLowerCase().indexOf(searchText.toLowerCase());

        if (startIndex != -1) {
            SpannableString spannableString = new SpannableString(name);
            StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
            spannableString.setSpan(boldSpan, startIndex, startIndex + searchText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.name.setText(spannableString);
        } else {
            holder.name.setText(name);
        }



        int drawableId = holder.itemView.getResources()
                .getIdentifier("pizza_"+(position+1),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableId)
                .into(holder.picImage);
//        holder.picImage.setOnClickListener(v -> listener.onItemClick(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

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

