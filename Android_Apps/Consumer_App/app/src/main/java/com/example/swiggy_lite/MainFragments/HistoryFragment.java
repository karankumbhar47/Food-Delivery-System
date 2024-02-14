package com.example.swiggy_lite.MainFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.GnssAntennaInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.swiggy_lite.AppConstants;
import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.History_Fragments.HistoryDetailsFragment;
import com.example.swiggy_lite.LoadingDialog;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.HistoryListAdapter;
import com.example.swiggy_lite.models.OrderItemAdvanced;
import com.example.swiggy_lite.models.OrderModel;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.FoodItemFull;
import com.openapi.deliveryApp.model.VendorGetRequestedOrders200ResponseInner;
import com.openapi.deliveryApp.model.VendorGetRequestedOrders200ResponseInnerOrderItemsInner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HistoryFragment extends Fragment {
    public interface OrderDetailsCallback {
        void onSuccess(VendorGetRequestedOrders200ResponseInner orderId);
        void onFailure(int statusCode, String errorMessage);
    }

    public interface ProductDetailsCallback {
        void onSuccess(FoodItemFull itemDetails);
        void onFailure(int statusCode, String errorMessage);
    }

    RecyclerView recyclerView;
    HistoryListAdapter historyListAdapter;
    SharedPreferences prefLogin;
    SharedPreferences prefOrders;
    Context context;
    ArrayList<String> order_ids;
    List<OrderModel> orderHistory;
    String sessionId;
    LoadingDialog loadingDialog;

    public HistoryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_history, container, false);

        {
            recyclerView = view.findViewById(R.id.order_history_recyclerView);
            context = requireContext();
            prefOrders = context.getSharedPreferences(AppConstants.PREF_ORDERS,Context.MODE_PRIVATE);
            prefLogin = context.getSharedPreferences(AppConstants.PREF_LOGIN,Context.MODE_PRIVATE);
            order_ids = new ArrayList<>();
            sessionId = prefLogin.getString(AppConstants.KEY_SESSION_ID,"");
            loadingDialog = new LoadingDialog(requireActivity());
            orderHistory = new ArrayList<>();
        }

        loadingDialog.startLoadingDialog();
        Map<String, ?> allEntries = prefOrders.getAll();
        Set<String> keys = allEntries.keySet();
        for(String key: keys){
            getOrderDetails(key, new OrderDetailsCallback() {
                @Override
                public void onSuccess(VendorGetRequestedOrders200ResponseInner orderDetails) {
                    OrderModel orderModel = new OrderModel();
                    orderModel.setOrderId(orderDetails.getOrderId());
                    List<VendorGetRequestedOrders200ResponseInnerOrderItemsInner> orderItems = orderDetails.getOrderItems();
                    List<OrderItemAdvanced> orderItemAdvanced = new ArrayList<>();
                    for (VendorGetRequestedOrders200ResponseInnerOrderItemsInner item : orderItems) {
                        OrderItemAdvanced requiredItem = new OrderItemAdvanced();
                        requiredItem.setItemId(item.getItemId());
                        requiredItem.setQuantity(item.getQuantity());
                        requiredItem.setItemName("random name");
                        requiredItem.setPrice(40f);
                        requiredItem.setMaxQuantity(29);
                        orderItemAdvanced.add(requiredItem);

//                        getProductDetails(item.getItemId(), new ProductDetailsCallback() {
//                            @Override
//                            public void onSuccess(FoodItemFull itemDetails) {
//                                requiredItem.setPrice(itemDetails.getPrice());
//                                requiredItem.setMaxQuantity(itemDetails.getMaxQuantity());
//                                requiredItem.setItemName(itemDetails.getItemName());
//                            }
//
//                            @Override
//                            public void onFailure(int statusCode, String errorMessage) {
//                                Log.d("myTag", "failed to load data of item "+item.getItemId());
//                                Log.d("myTag", "error message is "+errorMessage);
//                            }
//                        });
                    }
                    orderModel.setOrderItemAdvanced(orderItemAdvanced);
                    orderModel.setDeliveryAddress(orderDetails.getLocation());
                    orderModel.setPickupLocation(orderDetails.getPickupLocation());

                    LocalDate currentDate = LocalDate.now();
                    LocalTime currentTime = LocalTime.now();
                    String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                    orderModel.setDate(formattedDate);
                    orderModel.setTime(formattedTime);
                    orderModel.setTip(49);
                    orderHistory.add(orderModel);
                }

                @Override
                public void onFailure(int statusCode, String errorMessage) {
                    Log.d("myTag", "error for "+key +" "+errorMessage);

                }
            });
        }
        loadingDialog.dismissDialog();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(),LinearLayoutManager.VERTICAL,false));
        historyListAdapter = new HistoryListAdapter(orderHistory,requireContext());
        historyListAdapter.setOnItemClickListener(new HistoryListAdapter.OnItemClickListener() {
            @Override
            public void viewDetails(int position) {
                load(new HistoryDetailsFragment(position));
            }
        });
        recyclerView.setAdapter(historyListAdapter);

        return view;
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void getOrderDetails(String orderId, OrderDetailsCallback callback){
        DefaultApi api = new DefaultApi();
        Log.d("myTag", "order id "+orderId);
        Log.d("myTag", "session Id "+sessionId);
        api.getOrders(sessionId, orderId, new Response.Listener<VendorGetRequestedOrders200ResponseInner>() {
            @Override
            public void onResponse(VendorGetRequestedOrders200ResponseInner response) {
                Log.d("myTag", "success");
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myTag", "failure "+error);
                try {
                    int statusCode = error.networkResponse.statusCode;
                    String data = new String(error.networkResponse.data);
                    Log.d("myTag", "error code "+statusCode);
                    callback.onFailure(statusCode, data.trim());;
                } catch (Exception e) {
                    callback.onFailure(0, null);
                }
            }
        });
    }

    public void getProductDetails(String itemId, ProductDetailsCallback callback){
        DefaultApi api = new DefaultApi();
        api.getProduct(itemId, new Response.Listener<FoodItemFull>() {
            @Override
            public void onResponse(FoodItemFull response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    int statusCode = error.networkResponse.statusCode;
                    String data = new String(error.networkResponse.data);
                    callback.onFailure(statusCode, data.trim());;
                } catch (Exception e) {
                    callback.onFailure(0, null);
                }
            }
        });
    }
}