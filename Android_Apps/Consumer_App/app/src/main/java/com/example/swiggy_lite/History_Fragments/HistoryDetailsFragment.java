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
import com.example.swiggy_lite.models.OrderModel;

import java.math.BigDecimal;
import java.util.List;

public class HistoryDetailsFragment extends Fragment {
    RecyclerView recyclerView;
    HistoryItemsDetailsAdapter historyItemsDetailsAdapter;
    TextView item_total, delivery_fee, delivery_tip, GST_restaurant_charges, total_payment;
    TextView reorder_button;
    OrderModel orderModel;

    public HistoryDetailsFragment(int position) {
        //fetch OrderModel with orderId
        this.orderModel = DummyData.dummyOrderList.get(position);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_details, container, false);
        {
            recyclerView = view.findViewById(R.id.history_details_recyclerView);
            item_total = view.findViewById(R.id.item_total_textView);
            delivery_fee = view.findViewById(R.id.delivery_fee_textView);
            delivery_tip = view.findViewById(R.id.delivery_tip_textView);
            GST_restaurant_charges = view.findViewById(R.id.GST_restaurant_charges_textView);
            total_payment = view.findViewById(R.id.total_payment_textView);
        }

        item_total.setText(String.format("₹ %s", String.valueOf(calculateItemTotal(orderModel.getOrderDetails()))));
        delivery_tip.setText(String.format("₹ %s", String.valueOf(orderModel.getTip())));
        calculateTotalPayment();

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        historyItemsDetailsAdapter = new HistoryItemsDetailsAdapter(orderModel.getOrderDetails());
        recyclerView.setAdapter(historyItemsDetailsAdapter);

        reorder_button = view.findViewById(R.id.reorder_textView);
        reorder_button.setOnClickListener(v ->{
            orderModel.saveToSharedPreferences(requireContext());
            load( new CartFragment());
        });
        return view;
    }



    public BigDecimal calculateItemTotal(List<com.openapi.deliveryApp.model.OrderItem> list) {
        BigDecimal sum = BigDecimal.ZERO; // Initialize sum as BigDecimal.ZERO

        for (com.openapi.deliveryApp.model.OrderItem foodmodel : list) {
//            BigDecimal itemTotal = foodmodel.getPrice().multiply(BigDecimal.valueOf(foodmodel.getQuantity()));
//            sum = sum.add(itemTotal);
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