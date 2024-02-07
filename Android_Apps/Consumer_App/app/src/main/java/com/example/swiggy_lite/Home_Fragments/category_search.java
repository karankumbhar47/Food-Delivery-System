package com.example.swiggy_lite.Home_Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.swiggy_lite.AppConstants;
import com.example.swiggy_lite.Common_Fragment.ItemDetailsFragment;
import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ItemDetailsAdapter;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.FoodItem;
import com.openapi.deliveryApp.model.FoodItemFull;
import com.openapi.deliveryApp.model.PlaceOrderRequestItemCartInner;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class category_search extends Fragment {
    SearchView searchView;
    ItemDetailsAdapter itemDetailsAdapter;
    RecyclerView recyclerView;
    String searchedText;
    String sessionId;
    SharedPreferences prefLogin;
    List<FoodItem> searchedList;

    public category_search(String searchText) {
        this.searchedText = searchText;
        searchedList = new ArrayList<>();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_search, container, false);
        {
            recyclerView = view.findViewById(R.id.item_details_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false));
            itemDetailsAdapter = new ItemDetailsAdapter(searchedList, this.requireContext());
            searchView = view.findViewById(R.id.food_item_searchView);
            prefLogin = requireContext().getSharedPreferences(AppConstants.PREF_LOGIN, Context.MODE_PRIVATE);
            sessionId = prefLogin.getString(AppConstants.KEY_SESSION_ID,"");
        }

        ItemDetailsAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                load(new ItemDetailsFragment(searchedList.get(position).getItemId()));
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
        searchView.setQuery(searchedText,true);

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
        Log.d("myTag", "query send ... "+ text);
        DefaultApi api = new DefaultApi();
        api.query(sessionId, text,  new Response.Listener<List<FoodItem>>() {
            @Override
            public void onResponse(List<FoodItem> response) {
                if(response != null) {
                    searchedList = (ArrayList<FoodItem>) response;
                    itemDetailsAdapter.setList(searchedList);
                }
                else{
                    searchedList = new ArrayList<>();
                    itemDetailsAdapter.setList(searchedList);
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
                itemDetailsAdapter.setList(searchedList);
            }
        });
    }

}