package com.example.swiggy_lite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ItemDetailsAdapter;
import com.example.swiggy_lite.models.foodModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        ArrayList<foodModel> foodItemList = new ArrayList<>();
        foodItemList.add(new foodModel(1, "Spaghetti Bolognese", "4.5", 12, "Kitchen"));
        foodItemList.add(new foodModel(2, "Chicken Alfredo", "4.8", 15, "Kitchen"));
        foodItemList.add(new foodModel(3, "Margherita Pizza", "4.2", 10, "Oven"));
        foodItemList.add(new foodModel(4, "Caesar Salad", "4.0", 8, "Salad Bar"));
        foodItemList.add(new foodModel(5, "Chocolate Cake", "4.6", 18, "Dessert Station"));
        foodItemList.add(new foodModel(6, "Green Tea Ice Cream", "4.3", 6, "Freezer"));
        foodItemList.add(new foodModel(7, "Grilled Salmon", "4.7", 20, "Grill"));
        foodItemList.add(new foodModel(8, "Vegetable Stir Fry", "4.4", 14, "Stovetop"));
        foodItemList.add(new foodModel(9, "Berry Smoothie", "4.1", 7, "Blender"));
        foodItemList.add(new foodModel(10, "Classic Burger", "4.9", 16, "Grill"));
        foodItemList.add(new foodModel(11, "French Fries", "4.0", 5, "Fryer"));
        foodItemList.add(new foodModel(12, "Cheese Platter", "4.6", 22, "Cold Storage"));
        foodItemList.add(new foodModel(13, "Sushi Roll", "4.8", 25, "Sushi Bar"));
        foodItemList.add(new foodModel(14, "Pasta Primavera", "4.2", 13, "Stovetop"));
        foodItemList.add(new foodModel(15, "Chicken Noodle Soup", "4.5", 9, "Stovetop"));
        foodItemList.add(new foodModel(16, "Blueberry Pancakes", "4.3", 11, "Griddle"));
        foodItemList.add(new foodModel(17, "Vegetarian Burrito", "4.7", 17, "Assembly Line"));
        foodItemList.add(new foodModel(18, "Mango Sorbet", "4.4", 8, "Freezer"));

        RecyclerView recyclerView = findViewById(R.id.item_details_recyclerView);
        RecyclerView.Adapter adapter = new ItemDetailsAdapter(foodItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        ItemDetailsAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ItemDetailsActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        TextView cart_button = findViewById(R.id.cart_button);
        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetailsActivity.this,PlaceOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}