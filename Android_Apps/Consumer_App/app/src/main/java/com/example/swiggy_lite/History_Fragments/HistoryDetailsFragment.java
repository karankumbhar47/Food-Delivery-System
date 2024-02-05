package com.example.swiggy_lite.History_Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swiggy_lite.AppConstants;
import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.MainActivity;
import com.example.swiggy_lite.MainFragments.CartFragment;
import com.example.swiggy_lite.MasterActivity;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.HistoryItemsDetailsAdapter;
import com.example.swiggy_lite.models.OrderItemAdvanced;
import com.example.swiggy_lite.models.OrderModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HistoryDetailsFragment extends Fragment {
    RecyclerView recyclerView;
    HistoryItemsDetailsAdapter historyItemsDetailsAdapter;
    TextView item_total, delivery_fee, delivery_tip, GST_restaurant_charges, total_payment;
    SharedPreferences prefCart;
    SharedPreferences.Editor editorCart;
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
            prefCart = getActivity().getSharedPreferences(AppConstants.PREF_LOGIN, Context.MODE_PRIVATE);
            editorCart = prefCart.edit();
        }

        item_total.setText(String.format("₹ %s", String.valueOf(calculateItemTotal(orderModel.getOrderItemAdvanced()))));
        delivery_tip.setText(String.format("₹ %s", String.valueOf(orderModel.getTip())));
        calculateTotalPayment();

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        historyItemsDetailsAdapter = new HistoryItemsDetailsAdapter(orderModel.getOrderItemAdvanced());
        recyclerView.setAdapter(historyItemsDetailsAdapter);

        reorder_button = view.findViewById(R.id.reorder_textView);
        reorder_button.setOnClickListener(v ->{
            new AlertDialog.Builder(requireContext())
                    .setTitle("Add New Cart")
                    .setCancelable(false)
                    .setMessage("Are you sure you want remove previous cart items?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            orderModel.saveToSharedPreferences(requireContext());
                            List<OrderItemAdvanced> orderItemList = (orderModel == null) ? new ArrayList<>() : (orderModel.getOrderItemAdvanced()!=null ? orderModel.getOrderItemAdvanced()  : new ArrayList<>());
                            MasterActivity.itemCart = orderItemList.stream().collect(Collectors.toMap(OrderItemAdvanced::getItemId, Function.identity()));
                            Log.d("myTag", "itecart size "+MasterActivity.itemCart.size());
                            editorCart.putBoolean(AppConstants.KEY_IS_DATA_CHANGED,false);
                            editorCart.apply();
                            load( new CartFragment());
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();




        });
        return view;
    }



    public int calculateItemTotal(List<OrderItemAdvanced> list) {
        int sum = 0;
        for (OrderItemAdvanced foodmodel : list) {
            sum  += foodmodel.getPrice()*foodmodel.getQuantity();
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