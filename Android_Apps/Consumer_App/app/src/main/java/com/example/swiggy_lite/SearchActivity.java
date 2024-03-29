package com.example.swiggy_lite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.android.volley.VolleyError;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ListItemsAdapter;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.FoodItem;
import com.openapi.deliveryApp.model.Filter;
import com.android.volley.Response;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class SearchActivity extends AppCompatActivity {
    private DefaultApi api;
    private String sessionId;
    private SearchView searchView;
    private CardView recentSearchCardView;
    private SharedPreferences prefLogin;
    private List<FoodItem> searchedList;
    private ImageView back_button_imageView;
    private CategoryAdapter categoryAdapter;
    private ListItemsAdapter listItemsAdapter;
    private RecyclerView recentSearchRecyclerView, foodItemSearchRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        {
            prefLogin = getSharedPreferences(AppConstants.PREF_LOGIN, MODE_PRIVATE);
            sessionId = prefLogin.getString(AppConstants.KEY_SESSION_ID,"");
            searchView = findViewById(R.id.food_item_searchView);
            back_button_imageView = findViewById(R.id.back_button_imageView);
            recentSearchCardView = findViewById(R.id.recent_search_cardView);
            recentSearchRecyclerView = findViewById(R.id.popular_search_recyclerView);
            recentSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            categoryAdapter = new CategoryAdapter(DummyData.categoryList, this);
            foodItemSearchRecyclerView = findViewById(R.id.food_items_recyclerView);
            //editor = prefLogin.edit();
            api = new DefaultApi();
            //filterItem = new Filter();
            searchedList = new ArrayList<>();
        }

        CategoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                searchView.setQuery(AppConstants.LIST_CATEGORY.get(position),false);
            }
        });
        recentSearchRecyclerView.setAdapter(categoryAdapter);

        foodItemSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listItemsAdapter = new ListItemsAdapter(searchedList,this);
        ListItemsAdapter.setOnItemClickListener(new ListItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(AppConstants.KEY_ITEM_ID, searchedList.get(position).getItemId());
                setResult(RESULT_OK, resultIntent);
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

        searchView.setIconified(false);

        back_button_imageView.setOnClickListener(v -> {finish();});

//        Disposable disposable = fromView(searchView)
//                .debounce(1500, TimeUnit.MILLISECONDS)
//                .filter(text -> !text.isEmpty() && text.length() >= 3)
//                .map(text -> text.toLowerCase().trim())
//                .distinctUntilChanged()
//                .switchMap(s -> Observable.just(s))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(query ->
//                        {}
//                );
    }

    public static Observable<String> fromView(SearchView searchView) {
        final PublishSubject<String> subject = PublishSubject.create();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener ()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                subject.onNext(text);
                return true;
            }
        });
        return subject;
    }

    public void filteredList(String text) {
//        Log.d("myTag", "query send ... "+ text);
        api.query(sessionId, text,  new Response.Listener<List<FoodItem>>() {
            @Override
            public void onResponse(List<FoodItem> response) {
                if(response != null) {
                    searchedList = (ArrayList<FoodItem>) response;
                    Log.d("myTag", "item "+searchedList.get(0).getThumbnail());
                    Log.d("myTag", "item "+searchedList.get(0).getItemId());
                    Log.d("myTag", "item "+searchedList.get(0).getName());
                    Log.d("myTag", "item "+searchedList.get(0).getTags().toString());
                    Log.d("myTag", "item "+searchedList.get(0).getPrice());
                    listItemsAdapter.setList(searchedList, text);
                }
                else{
                    searchedList = new ArrayList<>();
                    listItemsAdapter.setList(searchedList,"");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                try {
//                    Log.d("myTag", "Error in search "+error);
//                    Log.d("myTag", "Error data in search " + new String(error.networkResponse.data));
//                    Log.d("myTag", "Error code in search " + error.networkResponse.statusCode);
//                }catch (Exception e){
//                    Log.d("myTag", "onErrorResponse: error "+e);
//                }
                searchedList = new ArrayList<>();
                listItemsAdapter.setList(searchedList,text);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}