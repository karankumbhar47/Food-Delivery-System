package com.example.swiggy_lite.models;

import com.openapi.deliveryApp.model.OrderItem;

public class OrderItemAdvanced extends OrderItem {
    private int maxQuantity;

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public OrderItem toOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemName(getItemName());
        orderItem.setItemId(getItemName());
        orderItem.setQuantity(getQuantity());
        orderItem.setPrice(getPrice());
        return orderItem;
    }
}
