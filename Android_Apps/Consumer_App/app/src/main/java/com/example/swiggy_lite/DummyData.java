package com.example.swiggy_lite;

import com.example.swiggy_lite.models.FoodModel;

import java.util.ArrayList;
import java.util.Arrays;

public class DummyData {
    public static final ArrayList<String> categoryList =  new ArrayList<>(Arrays.asList(
            "category_biryani", "category_burger", "category_cakes", "category_chinese",
            "category_chole_bature", "category_coffee", "category_dosa", "category_gulab_jamun",
            "category_ice_creams", "category_idli", "category_kebabs", "category_khichdi",
            "category_noodles", "category_north_indian", "category_paratha", "category_pasta",
            "category_pastry", "category_pizza", "category_rolls", "category_vada"
    ));

    public static final ArrayList<FoodModel> foodItemList;

    static {
        foodItemList = new ArrayList<>();
        foodItemList.add(new FoodModel(1, "Spaghetti Bolognese", "4.5", 12, "Kitchen"));
        foodItemList.add(new FoodModel(2, "Chicken Alfredo", "4.8", 15, "Kitchen"));
        foodItemList.add(new FoodModel(3, "Margherita Pizza", "4.2", 10, "Oven"));
        foodItemList.add(new FoodModel(4, "Caesar Salad", "4.0", 8, "Salad Bar"));
        foodItemList.add(new FoodModel(5, "Chocolate Cake", "4.6", 18, "Dessert Station"));
        foodItemList.add(new FoodModel(6, "Green Tea Ice Cream", "4.3", 6, "Freezer"));
        foodItemList.add(new FoodModel(7, "Grilled Salmon", "4.7", 20, "Grill"));
        foodItemList.add(new FoodModel(8, "Vegetable Stir Fry", "4.4", 14, "Stovetop"));
        foodItemList.add(new FoodModel(9, "Berry Smoothie", "4.1", 7, "Blender"));
        foodItemList.add(new FoodModel(10, "Classic Burger", "4.9", 16, "Grill"));
        foodItemList.add(new FoodModel(11, "French Fries", "4.0", 5, "Fryer"));
        foodItemList.add(new FoodModel(12, "Cheese Platter", "4.6", 22, "Cold Storage"));
        foodItemList.add(new FoodModel(13, "Sushi Roll", "4.8", 25, "Sushi Bar"));
        foodItemList.add(new FoodModel(14, "Pasta Primavera", "4.2", 13, "Stovetop"));
        foodItemList.add(new FoodModel(15, "Chicken Noodle Soup", "4.5", 9, "Stovetop"));
        foodItemList.add(new FoodModel(16, "Blueberry Pancakes", "4.3", 11, "Griddle"));
        foodItemList.add(new FoodModel(17, "Vegetarian Burrito", "4.7", 17, "Assembly Line"));
        foodItemList.add(new FoodModel(18, "Mango Sorbet", "4.4", 8, "Freezer"));
    }}
