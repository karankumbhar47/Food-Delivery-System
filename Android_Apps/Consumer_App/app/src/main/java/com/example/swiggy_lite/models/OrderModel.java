package com.example.swiggy_lite.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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
    private List<OrderItemAdvanced> orderItemAdvanced = null;
    private int tip = 0;

    public OrderModel(){
        super();
    }

    public List<OrderItemAdvanced> getOrderItemAdvanced() {
        return orderItemAdvanced;
    }

    public void setOrderItemAdvanced(List<OrderItemAdvanced> orderItemAdvanced) {
        this.orderItemAdvanced = orderItemAdvanced;
    }

    public OrderModel(List<OrderItemAdvanced> orderedItems, String date, String time, String address, int tip) {
        super();
        setOrderItemAdvanced(orderedItems);
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

    public static void saveToSharedPreferences(Context context, Map<String, OrderItemAdvanced> map) {
        Log.d("myTag", "Saving ...");
        SharedPreferences prefCart = context.getSharedPreferences(AppConstants.PREF_CART_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefCart.edit();
        Gson gson = new Gson();
        String deviceModelJson;

        OrderModel orderModel = retrieveFromSharedPreferences(context);
        if (orderModel == null) { orderModel = new OrderModel(); }
        List<OrderItemAdvanced> listPrevious = (orderModel.getOrderDetails() != null) ? orderModel.getOrderItemAdvanced() : new ArrayList<>();

        if (map != null && !map.isEmpty()) {
            for (OrderItemAdvanced orderItem : listPrevious) {
                String itemId = orderItem.getItemId();
                OrderItemAdvanced updatedOrderItem = map.get(itemId);
                if (updatedOrderItem != null) {
                    orderItem.setQuantity(updatedOrderItem.getQuantity());
                    map.remove(itemId);
                }
            }
        }

        if(map!=null && !map.isEmpty()) {
            List<OrderItemAdvanced> listCurrent = new ArrayList<>(map.values());
            orderModel.setOrderItemAdvanced(Stream.concat(listPrevious.stream(), listCurrent.stream())
                    .collect(Collectors.toList()));
        }
        else{
            orderModel.setOrderItemAdvanced(listPrevious);
        }

        deviceModelJson = gson.toJson(orderModel);
        editor.putString(AppConstants.KEY_CURRENT_CART, deviceModelJson);
        editor.apply();
    }

    public static OrderModel retrieveFromSharedPreferences(Context context) {
        Log.d("myTag", "retrieving ..");
        SharedPreferences prefCart = context.getSharedPreferences(AppConstants.PREF_CART_INFO, Context.MODE_PRIVATE);
        String deviceModelJson = prefCart.getString(AppConstants.KEY_CURRENT_CART, null);

        if (deviceModelJson!=null) {
            Gson gson = new Gson();
            return gson.fromJson(deviceModelJson, OrderModel.class);
        } else {
            return null;
        }
    }

    public List<OrderItem> convertToOrderItemList(Map<String, OrderItemAdvanced> map) {
        List<OrderItem> orderItemList = map.values().stream()
                .map(OrderItemAdvanced::toOrderItem)
                .collect(Collectors.toList());

        return orderItemList;
    }

}
