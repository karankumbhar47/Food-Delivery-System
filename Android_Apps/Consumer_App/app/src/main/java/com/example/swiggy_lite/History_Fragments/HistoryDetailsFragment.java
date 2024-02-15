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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.swiggy_lite.AppConstants;
import com.example.swiggy_lite.MainFragments.CartFragment;
import com.example.swiggy_lite.MainFragments.HistoryFragment;
import com.example.swiggy_lite.MasterActivity;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.HistoryItemsDetailsAdapter;
import com.example.swiggy_lite.models.OrderItemAdvanced;
import com.example.swiggy_lite.models.OrderModel;
import com.openapi.deliveryApp.api.DefaultApi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HistoryDetailsFragment extends Fragment {
    public interface OTPCallBack{
        void onSuccess(int OTP);
        void onFailure(int statusCode, String errorMessage);
    }

    RecyclerView recyclerView;
    HistoryItemsDetailsAdapter historyItemsDetailsAdapter;
    TextView item_total, delivery_fee, delivery_tip, GST_restaurant_charges, total_payment;
    TextView otp_number_textView, get_otp_button;
    SharedPreferences prefCart;
    SharedPreferences prefLogin;
    SharedPreferences prefOrderHistory;
    SharedPreferences.Editor editorCart;
    TextView reorder_button;
    OrderModel orderModel;
    int position;

    public HistoryDetailsFragment(OrderModel orderModel,int position) {
        //fetch OrderModel with orderId
        this.orderModel = orderModel;
        this.position = position;
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
            get_otp_button = view.findViewById(R.id.otp_textView);
            otp_number_textView = view.findViewById(R.id.otp_number_textView);
            GST_restaurant_charges = view.findViewById(R.id.GST_restaurant_charges_textView);
            total_payment = view.findViewById(R.id.total_payment_textView);
            prefCart = getActivity().getSharedPreferences(AppConstants.PREF_CART_INFO, Context.MODE_PRIVATE);
            prefLogin = getActivity().getSharedPreferences(AppConstants.PREF_LOGIN, Context.MODE_PRIVATE);
            prefOrderHistory = getActivity().getSharedPreferences(AppConstants.PREF_ORDER_HISTORY, Context.MODE_PRIVATE);
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
       if(orderModel.getStatus()==AppConstants.STATUS_ONGOING){
            get_otp_button.setVisibility(View.VISIBLE);
            otp_number_textView.setVisibility(View.GONE);
            get_otp_button.setOnClickListener(v -> {
                getOTP(orderModel.getOrderId(), new OTPCallBack() {
                    @Override
                    public void onSuccess(int OTP) {
                        orderModel.setOTP(OTP);
                        orderModel.setStatus(AppConstants.STATUS_OTP);
                        HistoryFragment.orderHistory.set(position, orderModel);
                        HistoryFragment.saveHistory();
                        otp_number_textView.setVisibility(View.VISIBLE);
                        get_otp_button.setVisibility(View.GONE);
                        otp_number_textView.setText(String.valueOf(OTP));
                    }

                    @Override
                    public void onFailure(int statusCode, String errorMessage) {
                        Toast.makeText(requireContext(), "Failed to get Otp", Toast.LENGTH_SHORT).show();
                        Log.d("myTag", "status code "+statusCode);
                        Log.d("myTag", "error message "+errorMessage);
                    }
                });
            });
        }
        else {
            get_otp_button.setVisibility(View.GONE);
            otp_number_textView.setVisibility(View.GONE);
        }
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

    public void getOTP(String orderId, OTPCallBack callback) {
        DefaultApi api = new DefaultApi();
        String sessionId = prefLogin.getString(AppConstants.KEY_SESSION_ID,"");
        Log.d("myTag", "called get otp "+orderId+" "+sessionId+" ");
        api.confirmOrder(sessionId, orderId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String otpString = response.trim().replace("\"","");
                Log.d("myTag", "otp string "+otpString);
                Log.d("myTag", "otp string "+otpString.length());
                int otp = Integer.parseInt(otpString);
                callback.onSuccess(otp);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myTag", "failure " + error);
                try {
                    int statusCode = error.networkResponse.statusCode;
                    String data = new String(error.networkResponse.data);
                    Log.d("myTag", "error code " + statusCode);
                    callback.onFailure(statusCode, data.trim());
                } catch (Exception e) {
                    callback.onFailure(0, null);
                }
            }
        });
    }

}