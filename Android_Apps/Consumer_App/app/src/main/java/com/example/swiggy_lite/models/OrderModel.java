package com.example.swiggy_lite.models;

import java.util.ArrayList;

public class OrderModel {
    private ArrayList<FoodModel> orderedItems;
    private String date;
    private String time;
    private String Address;
    private int tip = 0;

    public OrderModel(){}

    public OrderModel(ArrayList<FoodModel> orderedItems, String date, String time, String address, int tip) {
        this.orderedItems = orderedItems;
        this.date = date;
        this.time = time;
        Address = address;
        this.tip = tip;
    }

    public ArrayList<FoodModel> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(ArrayList<FoodModel> orderedItems) {
        this.orderedItems = orderedItems;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }
}
