package com.example.swiggy_lite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.swiggy_lite.Common_Fragment.ItemDetailsFragment;
import com.example.swiggy_lite.MainFragments.HomeFragment;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ItemDetailsAdapter;
import com.example.swiggy_lite.adapters.RestaurantMenuAdapter;
import com.example.swiggy_lite.models.FoodModel;

import java.util.ArrayList;

public class RestaurantInfo extends Fragment {
    ArrayList<FoodModel> restaurantMenuList;

    public RestaurantInfo(ArrayList<FoodModel> foodModels) {
        restaurantMenuList = foodModels;
    }

    SearchView searchView;
    RecyclerView recyclerView;
    RestaurantMenuAdapter restaurantMenuAdapter;
    ImageView back_button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_restaurant_info, container, false);
        searchView = view.findViewById(R.id.food_item_searchView);
        back_button = view.findViewById(R.id.back_button_imageView);
        back_button.setOnClickListener(v -> load(new HomeFragment()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredList(newText);
                return true;
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.menu_details_recyclerView);
        restaurantMenuAdapter = new RestaurantMenuAdapter(DummyData.foodItemList,requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(restaurantMenuAdapter);
        RestaurantMenuAdapter.setOnItemClickListener(new RestaurantMenuAdapter.OnItemClickListener() {
            @Override
            public void onMinusClick(int position, int quantity) {
                FoodModel foodModel = restaurantMenuList.get(position);
                if (quantity > 0) {
                    foodModel.setQuantity(quantity);
                    restaurantMenuList.set(position, foodModel);
                }
                restaurantMenuAdapter.setList(restaurantMenuList);
            }

            @Override
            public void onPlusClick(int position, int quantity) {
                FoodModel foodModel = restaurantMenuList.get(position);
                foodModel.setQuantity(quantity);
                restaurantMenuList.set(position, foodModel);
                restaurantMenuAdapter.setList(restaurantMenuList);
            }
        });


        return view;
    }

    public void filteredList(String text) {
        ArrayList<FoodModel> filteredList = new ArrayList<>();
        for (FoodModel foodModel: this.restaurantMenuList) {
            if (foodModel.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(foodModel);
            }
            restaurantMenuAdapter.setList(filteredList);
        }
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();}

}