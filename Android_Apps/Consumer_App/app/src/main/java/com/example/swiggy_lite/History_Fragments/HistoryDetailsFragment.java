package com.example.swiggy_lite.History_Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.MainFragments.CartFragment;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.HistoryItemsDetailsAdapter;
import com.example.swiggy_lite.models.FoodModel;
import com.example.swiggy_lite.models.OrderModel;

import java.util.ArrayList;

public class HistoryDetailsFragment extends Fragment {

    public HistoryDetailsFragment() {}
    RecyclerView recyclerView;
    HistoryItemsDetailsAdapter historyItemsDetailsAdapter;
    TextView item_total, delivery_fee, delivery_tip, GST_restaurant_charges, total_payment;
    TextView reorder_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_details, container, false);

        recyclerView = view.findViewById(R.id.history_details_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false));
        historyItemsDetailsAdapter = new HistoryItemsDetailsAdapter(DummyData.historyDetails);
        recyclerView.setAdapter(historyItemsDetailsAdapter);

        item_total = view.findViewById(R.id.item_total_textView);
        delivery_fee = view.findViewById(R.id.delivery_fee_textView);
        delivery_tip = view.findViewById(R.id.delivery_tip_textView);
        GST_restaurant_charges = view.findViewById(R.id.GST_restaurant_charges_textView);
        total_payment = view.findViewById(R.id.total_payment_textView);

        item_total.setText("₹ "+String.valueOf(calculateItemTotal(DummyData.historyDetails)));
        delivery_tip.setText("₹ "+String.valueOf( DummyData.orderList.get(DummyData.position).getTip()));
        calculateTotalPayment();

        reorder_button = view.findViewById(R.id.reorder_textView);
        reorder_button.setOnClickListener(v ->{
            OrderModel orderModel = new OrderModel();
            orderModel.setOrderedItems(DummyData.historyDetails);
            load( new CartFragment(orderModel));
        });
        return view;
    }


    public int calculateItemTotal(ArrayList<FoodModel> list){
        int sum = 0;
        for (FoodModel foodmodel : list) {
            sum+= foodmodel.getPrice() * foodmodel.getQuantity();
        }
        return sum;
    }

    public void calculateTotalPayment(){
        int sum = (int) (Integer.parseInt(item_total.getText().toString().split(" ")[1])
                + Integer.parseInt(delivery_fee.getText().toString().split(" ")[1])
                + Float.parseFloat(delivery_tip.getText().toString().split(" ")[1])
                + 3
                + Float.parseFloat(GST_restaurant_charges.getText().toString().split(" ")[1]));
        total_payment.setText(String.format("₹ %s", String.valueOf(sum)));
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}