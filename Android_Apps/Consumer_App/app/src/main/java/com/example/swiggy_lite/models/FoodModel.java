package com.example.swiggy_lite.models;

import java.io.Serializable;

public class FoodModel implements Serializable {
    String name, rating, render_location, category, img_path, cuisine;
    boolean isPureVeg;
    int item_id, price,quantity;

    public FoodModel(int item_id, String name, String rating, int price, String render_location){
        this.item_id = item_id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.render_location = render_location;
        this.isPureVeg = Math.random() > 0.5f;
        this.quantity = 1;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRender_location() {
        return render_location;
    }

    public void setRender_location(String render_location) {
        this.render_location = render_location;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public boolean isPureVeg() {
        return isPureVeg;
    }

    public void setPureVeg(boolean pureVeg) {
        isPureVeg = pureVeg;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
