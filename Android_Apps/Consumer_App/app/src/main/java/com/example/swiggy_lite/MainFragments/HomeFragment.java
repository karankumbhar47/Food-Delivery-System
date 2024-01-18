package com.example.swiggy_lite.MainFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swiggy_lite.Home_Fragments.category_search;
import com.example.swiggy_lite.MainPage;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ItemDetailsAdapter;
import com.example.swiggy_lite.ItemDetailsActivity;
import com.example.swiggy_lite.models.foodModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    RecyclerView category_recyclerView, recommendation_recyclerView;
    RecyclerView.Adapter categoryAdapter, recommendationAdapter;
    ArrayList<foodModel> foodItemList;
    ArrayList<String> categoryLists;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryLists = new ArrayList<>();
        categoryLists.add("Vegetables");
        categoryLists.add("Fruits");
        categoryLists.add("Meat");
        categoryLists.add("Dairy");
        categoryLists.add("Bakery");
        categoryLists.add("Beverages");
        categoryLists.add("Snacks");
        categoryLists.add("Seafood");
        categoryLists.add("Grains");
        categoryLists.add("Sweets");
        categoryLists.add("Condiments");
        categoryLists.add("Pasta");
        categoryLists.add("Canned Goods");
        categoryLists.add("Frozen Foods");
        categoryLists.add("Breakfast Foods");
        categoryLists.add("Desserts");
        categoryLists.add("Nuts and Seeds");
        categoryLists.add("Herbs and Spices");

        category_recyclerView = view.findViewById(R.id.category_recyclerView);
        category_recyclerView.setLayoutManager(new GridLayoutManager(this.requireContext(), 2, RecyclerView.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(categoryLists, this.requireContext());
        CategoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                load(new category_search());
            }
        });
        category_recyclerView.setAdapter(categoryAdapter);

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

        recommendation_recyclerView = view.findViewById(R.id.recommendation_recyclerView);
        recommendation_recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(),RecyclerView.VERTICAL,false));
        recommendationAdapter = new ItemDetailsAdapter(foodItemList, this.requireContext());
        ItemDetailsAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(requireContext(), ItemDetailsActivity.class);
                startActivity(intent);
            }
        });
        recommendation_recyclerView.setAdapter(recommendationAdapter);
        return view;
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}