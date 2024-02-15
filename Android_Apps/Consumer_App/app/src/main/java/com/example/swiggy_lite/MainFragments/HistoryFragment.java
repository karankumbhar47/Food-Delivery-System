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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

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
    Map<String, FoodItemFull> foodItemDetails;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        {
            recyclerView = view.findViewById(R.id.order_history_recyclerView);
            context = requireContext();
            prefOrders = context.getSharedPreferences(AppConstants.PREF_ORDERS, Context.MODE_PRIVATE);
            prefLogin = context.getSharedPreferences(AppConstants.PREF_LOGIN, Context.MODE_PRIVATE);
            order_ids = new ArrayList<>();
            sessionId = prefLogin.getString(AppConstants.KEY_SESSION_ID, "");
            loadingDialog = new LoadingDialog(requireActivity());
            orderHistory = new ArrayList<>();
            foodItemDetails = new HashMap<>();
        }

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        loadingDialog.startLoadingDialog();

        Map<String, ?> allEntries = prefOrders.getAll();
        int totalOrders = allEntries.size();
        AtomicInteger ordersProcessed = new AtomicInteger(0);

       // Iterate over each order
        for (String key : allEntries.keySet()) {
            getOrderDetails(key, new OrderDetailsCallback() {
                @Override
                public void onSuccess(VendorGetRequestedOrders200ResponseInner orderDetails) {
                    processOrder(orderDetails, formattedDate, formattedTime, totalOrders, ordersProcessed);
                }

                @Override
                public void onFailure(int statusCode, String errorMessage) {
                    Log.d("myTag", "error for " + key + " " + errorMessage);
                    checkAndUpdateUI(totalOrders, ordersProcessed);
                }
            });
        }

        return view;
    }

    void load(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void getOrderDetails(String orderId, OrderDetailsCallback callback) {
        DefaultApi api = new DefaultApi();
        api.getOrders(sessionId, orderId, new Response.Listener<VendorGetRequestedOrders200ResponseInner>() {
            @Override
            public void onResponse(VendorGetRequestedOrders200ResponseInner response) {
                Log.d("myTag", "success");
                callback.onSuccess(response);
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
                    ;
                } catch (Exception e) {
                    callback.onFailure(0, null);
                }
            }
        });
    }

    public void getProductDetails(String itemId, ProductDetailsCallback callback) {
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
                    callback.onFailure(statusCode, data.trim());
                    ;
                } catch (Exception e) {
                    callback.onFailure(0, null);
                }
            }
        });


    }

    private void processOrder(VendorGetRequestedOrders200ResponseInner orderDetails, String formattedDate, String formattedTime,
                              int totalOrders, AtomicInteger ordersProcessed) {
        OrderModel orderModel = new OrderModel();
        orderModel.setDate(formattedDate);
        orderModel.setTime(formattedTime);
        orderModel.setTip(49);

        orderModel.setOrderId(orderDetails.getOrderId());
        orderModel.setDeliveryAddress(orderDetails.getLocation());
        orderModel.setPickupLocation(orderDetails.getPickupLocation());

        List<VendorGetRequestedOrders200ResponseInnerOrderItemsInner> orderItems = orderDetails.getOrderItems();
        List<OrderItemAdvanced> orderItemAdvanced = new ArrayList<>();

        // Keep track of the number of products processed for this order
        AtomicInteger productsProcessed = new AtomicInteger(0);

        // Iterate over each item in the order
        for (VendorGetRequestedOrders200ResponseInnerOrderItemsInner item : orderItems) {
            orderItemAdvanced.add(processOrderItem(item, orderItemAdvanced, productsProcessed, orderItems.size()));
        }

        orderModel.setOrderItemAdvanced(orderItemAdvanced);
        // Add the order to the list

        orderHistory.add(orderModel);

        // Check if all orders are processed and update UI
        checkAndUpdateUI(totalOrders, ordersProcessed);
    }

    // Method to process each item in the order
    private OrderItemAdvanced processOrderItem(VendorGetRequestedOrders200ResponseInnerOrderItemsInner item, List<OrderItemAdvanced> orderItemAdvanced,
                                  AtomicInteger productsProcessed, int totalProducts) {
        OrderItemAdvanced requiredItem = new OrderItemAdvanced();
        requiredItem.setItemId(item.getItemId());
        requiredItem.setQuantity(item.getQuantity());
        requiredItem.setItemName("random name");
        requiredItem.setPrice(40f);
        requiredItem.setMaxQuantity(29);
        return requiredItem;
    }

    // Method to check if all orders are processed and update UI
    private void checkAndUpdateUI(int totalOrders, AtomicInteger ordersProcessed) {
        int processed = ordersProcessed.incrementAndGet();
        if (processed == totalOrders) {
            // Notify UI that all order details are updated
            updateUI();
            loadingDialog.dismissDialog();
        }
    }



    // Method to update the UI with the updated order details
    private void updateUI() {
        Log.d("myTag", "called update ui "+orderHistory.size());
        Log.d("myTag", "first item "+orderHistory.get(0).getOrderId());
        Log.d("myTag", "second item "+orderHistory.get(1).getOrderId());
        // Update RecyclerView with orderHistory
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        historyListAdapter = new HistoryListAdapter(orderHistory, requireContext());
        historyListAdapter.setOnItemClickListener(new HistoryListAdapter.OnItemClickListener() {
            @Override
            public void viewDetails(int position) {
                load(new HistoryDetailsFragment(position));
            }
        });
        recyclerView.setAdapter(historyListAdapter);

        // Dismiss loading dialog
        loadingDialog.dismissDialog();
    }

}

/*
        orderHistory = new ArrayList<>();
        for (String key : keys) {
            getOrderDetails(key, new OrderDetailsCallback() {
                @Override
                public void onSuccess(VendorGetRequestedOrders200ResponseInner orderDetails) {
                    OrderModel orderModel = new OrderModel();

                    orderModel.setDate(formattedDate);
                    orderModel.setTime(formattedTime);
                    orderModel.setTip(49);
                    orderModel.setOrderId(orderDetails.getOrderId());
                    orderModel.setDeliveryAddress(orderDetails.getLocation());
                    orderModel.setPickupLocation(orderDetails.getPickupLocation());

                    List<VendorGetRequestedOrders200ResponseInnerOrderItemsInner> orderItems = orderDetails.getOrderItems();
                    List<OrderItemAdvanced>orderItemAdvanced = new ArrayList<>();

                    AtomicInteger productProcessed = new AtomicInteger(0);

                    for (VendorGetRequestedOrders200ResponseInnerOrderItemsInner item : orderItems) {
                        OrderItemAdvanced requiredItem = new OrderItemAdvanced();
                        requiredItem.setItemId(item.getItemId());
                        requiredItem.setQuantity(item.getQuantity());
                        requiredItem.setItemName("random name");
                        requiredItem.setPrice(40f);
                        requiredItem.setMaxQuantity(29);

                        if (foodItemDetails != null && foodItemDetails.get(item.getItemId()) != null) {
                            FoodItemFull itemFromMap = foodItemDetails.get(item.getItemId());
                            requiredItem.setMaxQuantity(itemFromMap.getMaxQuantity());
                            requiredItem.setItemName(itemFromMap.getItemName());
                            requiredItem.setPrice(itemFromMap.getPrice());
                        } else {
                            getProductDetails(item.getItemId(), new ProductDetailsCallback() {
                                @Override
                                public void onSuccess(FoodItemFull itemDetails) {
                                    requiredItem.setPrice(itemDetails.getPrice());
                                    requiredItem.setMaxQuantity(itemDetails.getMaxQuantity());
                                    requiredItem.setItemName(itemDetails.getItemName());
                                    orderItemAdvanced.add(requiredItem);
                                    Log.d("myTag", "success get item name " + itemDetails.getItemName());

                                    int allItem = productProcessed.getAndIncrement();
                                    if(allItem == orderItems.size()){
                                        orderModel.setOrderItemAdvanced(orderItemAdvanced);
                                    }
                                    foodItemDetails.put(itemDetails.getItemId(), itemDetails);
                                }

                                @Override
                                public void onFailure(int statusCode, String errorMessage) {
                                    Log.d("myTag", "failed to load data of item " + item.getItemId());
                                    Log.d("myTag", "error message is " + errorMessage);
                                    orderItemAdvanced.add(requiredItem);
                                    int allItem = productProcessed.getAndIncrement();
                                    if(allItem == orderItems.size()){
                                        orderModel.setOrderItemAdvanced(orderItemAdvanced);
                                    }
                                }
                            });
                        }
                    }

                    orderHistory.add(orderModel);

                    int processed = ordersProcessed.incrementAndGet();
                    if (processed == totalOrders) {
                        updateUI();
                    }
                }

                @Override
                public void onFailure(int statusCode, String errorMessage) {
                    Log.d("myTag", "error for " + key + " " + errorMessage);
                    int processed = ordersProcessed.incrementAndGet();
                    if (processed == totalOrders) {
                        updateUI();
                    }
                }
            });

        }

     */
