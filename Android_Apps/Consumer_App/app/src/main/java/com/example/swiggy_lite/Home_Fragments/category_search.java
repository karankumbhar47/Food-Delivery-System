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
import android.widget.SearchView;

import com.example.swiggy_lite.Common_Fragment.ItemDetailsFragment;
import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ItemDetailsAdapter;
import com.openapi.deliveryApp.model.FoodItemFull;

import org.jetbrains.skiko.FullscreenAdapter;

import java.util.ArrayList;
import java.util.List;

public class category_search extends Fragment {
    public category_search() {}

    SearchView searchView;
    ItemDetailsAdapter itemDetailsAdapter;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_search, container, false);
        recyclerView = view.findViewById(R.id.item_details_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false));
        itemDetailsAdapter = new ItemDetailsAdapter(DummyData.dummyFoodList, this.requireContext());

        searchView = view.findViewById(R.id.food_item_searchView);
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



        ItemDetailsAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                load(new ItemDetailsFragment(String.valueOf(DummyData.dummyFoodList.get(0).getItemId())));
            }
        });
        recyclerView.setAdapter(itemDetailsAdapter);


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

        return view;
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager =  this.requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void filteredList(String text) {
        List<FoodItemFull> filteredList = new ArrayList<>();
        for (FoodItemFull foodModel: DummyData.dummyFoodList) {
            if (foodModel.getItemName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(foodModel);
            }
            itemDetailsAdapter.setList(filteredList);
        }

    }

}