package com.example.swiggy_lite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.swiggy_lite.Common_Fragment.ItemDetailsFragment;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ListItemsAdapter;
import com.example.swiggy_lite.models.FoodModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recentSearchRecyclerView, foodItemSearchRecyclerView;
    CardView recentSearchCardView, foodItemListCardView;
    CategoryAdapter categoryAdapter;
    ListItemsAdapter listItemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchView searchView = findViewById(R.id.food_item_searchView);
        ImageView back_button_imageView =  findViewById(R.id.back_button_imageView);
        recentSearchCardView = findViewById(R.id.recent_search_cardView);

        recentSearchRecyclerView = findViewById(R.id.popular_search_recyclerView);
        recentSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        categoryAdapter = new CategoryAdapter(DummyData.categoryList,this);
        CategoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchActivity.this, MainPage.class);
                intent.putExtra(MainPage.EXTRA_FRAGMENT_ID,MainPage.FRAGMENT_ITEM_DETAILS);
                startActivity(intent);
                finish();
            }
        });
        recentSearchRecyclerView.setAdapter(categoryAdapter);

        foodItemSearchRecyclerView = findViewById(R.id.food_items_recyclerView);
        foodItemSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listItemsAdapter = new ListItemsAdapter(DummyData.foodItemList,this);
        ListItemsAdapter.setOnItemClickListener(new ListItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchActivity.this, MainPage.class);
                intent.putExtra(MainPage.EXTRA_FRAGMENT_ID,MainPage.FRAGMENT_ITEM_DETAILS);
                startActivity(intent);
                finish();
            }
        });

        foodItemSearchRecyclerView.setAdapter(listItemsAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    recentSearchCardView.setVisibility(View.VISIBLE);
                    foodItemSearchRecyclerView.setVisibility(View.GONE);
                } else {
                    recentSearchCardView.setVisibility(View.GONE);
                    foodItemSearchRecyclerView.setVisibility(View.VISIBLE);
                    foodItemSearchRecyclerView.setAdapter(listItemsAdapter);
                }

                filteredList(newText);
                return true;
            }
        });

        boolean isSearchBarActivated = getIntent().getBooleanExtra("isSearchBarActivated", false);
        if (isSearchBarActivated) {
            searchView.setIconified(false);
        }

        back_button_imageView.setOnClickListener(v -> {startActivity(new Intent(this,MainPage.class));});
    }

    public void filteredList(String text) {
        ArrayList<FoodModel> filteredList = new ArrayList<>();
        for (FoodModel foodModel: DummyData.foodItemList) {
            if (foodModel.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(foodModel);
            }
            listItemsAdapter.setList(filteredList,text);
        }
    }
}