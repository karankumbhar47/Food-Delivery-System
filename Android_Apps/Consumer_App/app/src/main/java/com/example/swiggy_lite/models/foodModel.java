package com.example.swiggy_lite.models;

public class foodModel {
    String name, rating, render_location, category;
    int item_id, price;

    public foodModel(int item_id, String name, String rating, int price, String render_location){
        this.item_id = item_id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.render_location = render_location;
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
}
