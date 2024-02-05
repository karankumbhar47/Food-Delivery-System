package com.example.swiggy_lite.Home_Fragments;

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
import android.widget.Toast;

import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.MainFragments.CartFragment;
import com.example.swiggy_lite.MainFragments.HomeFragment;
import com.example.swiggy_lite.MasterActivity;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.RestaurantMenuAdapter;
import com.example.swiggy_lite.models.OrderModel;
import com.openapi.deliveryApp.model.FoodItemFull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.internal.operators.observable.ObservableMap;

public class RestaurantInfo extends Fragment {
    public RestaurantInfo() {
        restaurantMenuList = DummyData.dummyFoodList;
    }

    List<FoodItemFull> restaurantMenuList;
    SearchView searchView;
    RecyclerView recyclerView;
    RestaurantMenuAdapter restaurantMenuAdapter;
    ImageView back_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_info, container, false);
        {
            searchView = view.findViewById(R.id.food_item_searchView);
            back_button = view.findViewById(R.id.back_button_imageView);
            recyclerView = view.findViewById(R.id.menu_details_recyclerView);
        }

        restaurantMenuAdapter = new RestaurantMenuAdapter(restaurantMenuList, requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(restaurantMenuAdapter);

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
        back_button.setOnClickListener(v -> load(new HomeFragment()));
        RestaurantMenuAdapter.setOnItemClickListener(new RestaurantMenuAdapter.OnItemClickListener() {
            @Override
            public void onMinusClick(int position, int quantity) {
                FoodItemFull foodModel = restaurantMenuList.get(position);
                MasterActivity.addToCart(foodModel, quantity,false);
                restaurantMenuAdapter.changeQuantityList();
            }

            @Override
            public void onPlusClick(int position, int quantity) {
                FoodItemFull foodModel = restaurantMenuList.get(position);
                if(foodModel.getMaxQuantity()>=quantity) {
                    MasterActivity.addToCart(foodModel, quantity, true);
                    restaurantMenuAdapter.changeQuantityList();
                }else {
                    Toast.makeText(requireContext(),"Max Quantity Reached",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void filteredList(String text) {
        List<FoodItemFull> filteredList = new ArrayList<>();
        for (FoodItemFull foodItem : this.restaurantMenuList) {
            if (foodItem.getItemName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(foodItem);
                restaurantMenuList = filteredList;
            }
            restaurantMenuAdapter.setList(filteredList);
        }
    }

    void load(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}