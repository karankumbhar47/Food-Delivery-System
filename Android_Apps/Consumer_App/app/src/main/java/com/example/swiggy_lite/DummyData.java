package com.example.swiggy_lite;

import com.example.swiggy_lite.models.FoodModel;
import com.example.swiggy_lite.models.OrderModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class DummyData {
    public static final ArrayList<String> categoryList =  new ArrayList<>(Arrays.asList(
            "category_biryani", "category_burger", "category_cakes", "category_chinese",
            "category_chole_bature", "category_coffee", "category_dosa", "category_gulab_jamun",
            "category_ice_creams", "category_idli", "category_kebabs", "category_khichdi",
            "category_noodles", "category_north_indian", "category_paratha", "category_pasta",
            "category_pastry", "category_pizza", "category_rolls", "category_vada"
    ));

    public static  final ArrayList<String> campusAddresses = new ArrayList<>();
    static {
        campusAddresses.add("University Avenue, Admin Building, Room 101");
        campusAddresses.add("Knowledge Lane, Central Library, Reference Section");
        campusAddresses.add("Campus Circle, Student Center, Cafeteria");
        campusAddresses.add("Innovation Street, Engineering Complex, Room E-205");
        campusAddresses.add("Discovery Drive, Science Lab Building, Lab-301");
        campusAddresses.add("Creativity Court, Arts Building, Room A-102");
        campusAddresses.add("Fitness Avenue, Sports Complex, Gymnasium");
        campusAddresses.add("Wellness Way, Health Center, Medical Office");
    }

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
    }

    public static ArrayList<FoodModel> selected = null;
    public static final ArrayList<OrderModel> orderList;

    static {
        orderList = new ArrayList<>();
        orderList.add(new OrderModel(getRandomFoodItems(), formatDate("2024-01-01"), "12:00 PM", "123 Street, City", 5));
        orderList.add(new OrderModel(getRandomFoodItems(), formatDate("2024-01-02"), "01:30 PM", "456 Avenue, Town", 2));
        orderList.add(new OrderModel(getRandomFoodItems(), formatDate("2024-01-03"), "07:45 PM", "789 Road, Village", 0));
        orderList.add(new OrderModel(getRandomFoodItems(), formatDate("2024-01-04"), "03:15 PM", "321 Lane, County", 3));
        orderList.add(new OrderModel(getRandomFoodItems(), formatDate("2024-01-05"), "09:30 AM", "654 Boulevard, Country", 1));
    }

    private static ArrayList<FoodModel> getRandomFoodItems() {
        ArrayList<FoodModel> selectedItems = new ArrayList<>();
        int numberOfItems = Math.min((int) (Math.random() * 8) + 1,3);

        for (int i = 0; i < numberOfItems; i++) {
            selectedItems.add(foodItemList.get((int) (Math.random() * foodItemList.size())));
        }

        return selectedItems;
    }

    private static String formatDate(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date date = inputFormat.parse(inputDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM. yyyy", Locale.US);
            return outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return inputDate; // Return original date in case of any error
        }
    }

    public static ArrayList<FoodModel> historyDetails = null;
    public static int position = 0;
}
