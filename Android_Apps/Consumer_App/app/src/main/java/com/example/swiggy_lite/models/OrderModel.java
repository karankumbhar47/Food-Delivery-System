package com.example.swiggy_lite.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.swiggy_lite.AppConstants;
import com.google.gson.Gson;
import com.openapi.deliveryApp.model.Order;
import com.openapi.deliveryApp.model.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderModel extends Order {
    private String date = null ;
    private String time = null ;
    private int tip = 0;

    public OrderModel(){
        super();
    }

    public OrderModel(List<OrderItem> orderedItems, String date, String time, String address, int tip) {
        super();
        setOrderDetails(orderedItems);
        setDeliveryAddress(address);
        this.date = date;
        this.time = time;
        this.tip = tip;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void saveToSharedPreferences(Context context) {
        SharedPreferences prefData = context.getSharedPreferences(AppConstants.PREF_CART_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefData.edit();
        Gson gson = new Gson();
        String deviceModelJson;
        deviceModelJson = gson.toJson(this);
        editor.putString(AppConstants.KEY_CURRENT_CART, deviceModelJson);
        editor.apply();
    }

    public static void saveToSharedPreferences(Context context, Map<String, OrderItem> map) {
        SharedPreferences prefData = context.getSharedPreferences(AppConstants.PREF_CART_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefData.edit();
        Gson gson = new Gson();
        String deviceModelJson;

        OrderModel orderModel = retrieveFromSharedPreferences(context);
        if (orderModel == null) { orderModel = new OrderModel(); }
        List<OrderItem> listPrevious = (orderModel.getOrderDetails() != null) ? orderModel.getOrderDetails() : new ArrayList<>();

        if (map != null && !map.isEmpty()) {
            for (OrderItem orderItem : listPrevious) {
                String itemId = orderItem.getItemId();
                OrderItem updatedOrderItem = map.get(itemId);
                if (updatedOrderItem != null) {
                    orderItem.setQuantity(updatedOrderItem.getQuantity());
                    map.remove(itemId);
                }
            }
        }

        List<OrderItem> listCurrent = new ArrayList<>(map.values());
        orderModel.setOrderDetails(Stream.concat(listPrevious.stream(), listCurrent.stream())
                .collect(Collectors.toList()));

        deviceModelJson = gson.toJson(orderModel);
        editor.putString(AppConstants.KEY_CURRENT_CART, deviceModelJson);
        editor.apply();
    }

    public static OrderModel retrieveFromSharedPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(AppConstants.PREF_CART_INFO, Context.MODE_PRIVATE);
        String deviceModelJson = preferences.getString(AppConstants.KEY_CURRENT_CART, null);

        if (deviceModelJson!=null) {
            Gson gson = new Gson();
            return gson.fromJson(deviceModelJson, OrderModel.class);
        } else {
            return null;
        }
    }

}
