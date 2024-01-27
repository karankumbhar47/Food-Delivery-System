package com.example.swiggy_lite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggy_lite.R;
import com.example.swiggy_lite.models.FoodModel;
import com.example.swiggy_lite.models.OrderModel;

import java.util.ArrayList;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.viewHolder> {
    ArrayList<OrderModel> orderedList;
    Context context;

    public HistoryListAdapter(ArrayList<OrderModel> items,Context context) {
        this.orderedList = items;
        this.context = context;
    }

    private HistoryListAdapter.OnItemClickListener listener;
    public interface OnItemClickListener{
        void viewDetails(int position);
    }

    public void setOnItemClickListener(HistoryListAdapter.OnItemClickListener clickListener){
        listener = clickListener;
    }

    public void setList(ArrayList<OrderModel> updateList){
        this.orderedList = updateList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_card, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        OrderModel orderModel = orderedList.get(position);
        ArrayList<FoodModel> foodModelArrayList = orderModel.getOrderedItems();
        int sum = 0;
        for(FoodModel foodModel: foodModelArrayList){
            sum += foodModel.getQuantity() * foodModel.getPrice();
        }

        holder.order_date_textView.setText(orderModel.getDate()+", "+orderModel.getTime());
        holder.order_total_textView.setText("₹ "+String.valueOf(sum));
        holder.view_details_button.setOnClickListener(v -> listener.viewDetails(position));

        HistoryItemsDetailsAdapter itemList = new HistoryItemsDetailsAdapter(foodModelArrayList);
        holder.item_list_recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL, false));
        holder.item_list_recyclerView.setAdapter(itemList);
    }

    @Override
    public int getItemCount() {
        return orderedList.size();
    }

    public static class viewHolder extends  RecyclerView.ViewHolder{
        TextView order_date_textView, order_total_textView, view_details_button;
        RecyclerView item_list_recyclerView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            order_date_textView = itemView.findViewById(R.id.order_date_textView);
            order_total_textView = itemView.findViewById(R.id.order_total_textView);
            view_details_button = itemView.findViewById(R.id.view_details_textView);
            item_list_recyclerView = itemView.findViewById(R.id.ordered_list_recyclerView);
        }
    }
}
