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

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    ArrayList<String> list;
    Context context;

    public CategoryAdapter(ArrayList<String> items, Context context) {
        this.list = items;
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
    public CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position) {
        String singleItem = list.get(position);
        holder.name.setText(singleItem);
        holder.picImage.setOnClickListener(v -> listener.onItemClick(holder.getAdapterPosition()));
        int drawableId = holder.itemView.getResources()
                .getIdentifier("burger","drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableId)
                .into(holder.picImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView name ;
        ImageView picImage;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.category_name_textView);
            picImage = itemView.findViewById(R.id.category_imageView);
        }
    }
}
