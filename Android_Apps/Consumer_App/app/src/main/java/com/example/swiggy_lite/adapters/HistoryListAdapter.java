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
import com.example.swiggy_lite.models.OrderModel;
import com.openapi.deliveryApp.model.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.viewHolder> {
    List<OrderModel> orderedList;
    Context context;

    public HistoryListAdapter(List<OrderModel> items,Context context) {
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
        BigDecimal sum = BigDecimal.ZERO; // Initialize sum as BigDecimal.ZERO

        for (OrderItem foodModel : orderModel.getOrderDetails()) {
            BigDecimal itemTotal = foodModel.getPrice().multiply(BigDecimal.valueOf(foodModel.getQuantity()));
            sum = sum.add(itemTotal);
        }

        holder.order_date_textView.setText(String.format("%s, %s", orderModel.getDate(), orderModel.getTime()));
        holder.order_total_textView.setText(String.format("₹ %s", String.valueOf(sum)));
        holder.view_details_button.setOnClickListener(v -> listener.viewDetails(position));

        HistoryItemsDetailsAdapter itemList = new HistoryItemsDetailsAdapter(orderModel.getOrderDetails());
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
