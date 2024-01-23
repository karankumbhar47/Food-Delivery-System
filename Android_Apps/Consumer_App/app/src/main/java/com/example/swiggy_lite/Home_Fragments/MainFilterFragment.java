package com.example.swiggy_lite.Home_Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.CuisinesAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainFilterFragment extends BottomSheetDialogFragment implements NavigationView.OnNavigationItemSelectedListener {
    CheckBox checkBox;
    
    public MainFilterFragment(CheckBox checkBox) {
        this.checkBox = checkBox;
        checkBox.setBackgroundResource(R.drawable.active_button_background);
        checkBox.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.cancel_icon, 0);
    }

    View view;
    int frameTypeId;
    HashMap<String, Boolean> cuisineMap;
    CuisinesAdapter cuisinesAdapter;
    RecyclerView cuisineRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_main_filter, container, false);
        this.frameTypeId = R.id.sort_linear_layout;

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.filter_naviagationView);
        navigationView.setNavigationItemSelectedListener(this);

        TextView clear_button  = view.findViewById(R.id.clear_filter_textView);
        clear_button.setOnClickListener(v -> {
            LinearLayout linearLayout = view.findViewById(frameTypeId);
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View child = linearLayout.getChildAt(i);
                if (child instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) child;
                    checkBox.setChecked(false);
                }

                if (child instanceof RadioGroup) {
                    RadioGroup radioGroup = (RadioGroup) child;
                    for (int j=0; j< radioGroup.getChildCount(); j++){
                        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(j);
                        radioButton.setChecked(false);
                    }
                }

                if (child.getId() == R.id.relevance_radioButton){
                    RadioButton radioButton = (RadioButton) child;
                    radioButton.setChecked(true);
                }
            }
        });


        TextView apply_button  = view.findViewById(R.id.apply_filter_textView);
        ImageView cancel_button = view.findViewById(R.id.cancel_imageView);
        cancel_button.setOnClickListener(v ->{ dismiss();});

        cuisineMap = new HashMap<>();
        cuisineMap.put("Italian", false);
        cuisineMap.put("Chinese", false);
        cuisineMap.put("Mexican", false);
        cuisineMap.put("Indian", false);
        cuisineMap.put("Japanese", false);
        cuisineMap.put("French", false);
        cuisineMap.put("Thai", false);
        cuisineMap.put("Mediterranean", false);
        cuisineMap.put("American", false);
        cuisineMap.put("Greek", false);
        cuisineMap.put("Spanish", false);
        cuisineMap.put("Brazilian", false);
        cuisineMap.put("Korean", false);
        cuisineMap.put("Vietnamese", false);
        cuisineMap.put("Turkish", false);
        cuisineMap.put("Lebanese", false);
        cuisineMap.put("Moroccan", false);
        cuisineMap.put("Russian", false);
        cuisineMap.put("Jamaican", false);
        cuisineMap.put("Peruvian", false);
        cuisineMap.put("Argentinian", false);
        cuisineMap.put("German", false);
        cuisineMap.put("African", false);
        cuisineMap.put("Cajun", false);
        cuisineMap.put("Filipino", false);
        cuisineMap.put("Irish", false);
        cuisineMap.put("Swedish", false);
        cuisineMap.put("Portuguese", false);
        cuisineMap.put("Hawaiian", false);
        cuisineMap.put("Australian", false);

        SearchView searchView = view.findViewById(R.id.cuisine_search_bar);
        searchView.clearFocus();

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

    public void filteredList(String text){
        HashMap<String , Boolean > filteredList = new HashMap<>();
        for(String cuisine_item : CuisinesAdapter.cuisine_list){
            if(cuisine_item.toLowerCase().contains(text.toLowerCase())){
                filteredList.put(cuisine_item,CuisinesAdapter.cuisine_map.get(cuisine_item));
            }

            if(filteredList.isEmpty()){
                Toast.makeText(this.requireContext(), "No Data found",Toast.LENGTH_SHORT).show();
                Log.d("myTag", "filteredList: called if 2 "+text);
            }
            cuisinesAdapter.setCuisine_list(filteredList);
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_sort) {
            handleVisibility(R.id.sort_linear_layout);
        } else if (id == R.id.menu_delivery_time) {
            handleVisibility(R.id.delivery_time_linearLayout);
        } else if (id == R.id.menu_explore) {
            handleVisibility(R.id.explore_linearLayout);
        } else if (id == R.id.menu_rating) {
            handleVisibility(R.id.rating_linearLayout);
        } else if (id == R.id.menu_veg_nonveg) {
            handleVisibility(R.id.veg_nonVeg_linearLayout);
        } else if (id == R.id.menu_offers) {
            handleVisibility(R.id.offers_linearLayout);
        } else if (id == R.id.menu_cost_for_two) {
            handleVisibility(R.id.cost_two_linearLayout);
        } else if (id == R.id.menu_cuisines) {
            handleVisibility(R.id.cuisine_linearLayout);
        } else {
            handleVisibility(R.id.sort_linear_layout);
        }

        return true;
    }

    public void handleVisibility(int id){
        int[] array = {
                R.id.veg_nonVeg_linearLayout,
                R.id.sort_linear_layout,
                R.id.delivery_time_linearLayout,
                R.id.rating_linearLayout,
                R.id.offers_linearLayout,
                R.id.cost_two_linearLayout,
                R.id.explore_linearLayout
        };

        for(int i: array){
            LinearLayout linearLayout = view.findViewById(i);
            linearLayout.setVisibility(View.GONE);
        }
        LinearLayout linearLayout = view.findViewById(id);
        linearLayout.setVisibility(View.VISIBLE);
        this.frameTypeId = id;

        if(id == R.id.cuisine_linearLayout){
            cuisineRecyclerView = view.findViewById(R.id.cuisine_recyclerView);
            cuisineRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false));
            if(CuisinesAdapter.cuisine_map==null) {
                cuisinesAdapter = new CuisinesAdapter(cuisineMap);
            }
            else{
                cuisinesAdapter = new CuisinesAdapter(CuisinesAdapter.cuisine_map);
            }
            cuisineRecyclerView.setAdapter(cuisinesAdapter);

            CuisinesAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    String key  = CuisinesAdapter.cuisine_list.get(position);
                    CuisinesAdapter.cuisine_map.put(key, Boolean.FALSE.equals(CuisinesAdapter.cuisine_map.get(key)));
                    cuisinesAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        checkBox.setChecked(false);
        checkBox.setBackgroundResource(R.drawable.rounded_corner_background);
        checkBox.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.filter_icon, 0);
    }
}