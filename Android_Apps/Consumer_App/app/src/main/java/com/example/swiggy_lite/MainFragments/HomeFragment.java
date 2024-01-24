package com.example.swiggy_lite.MainFragments;

import android.content.Context;
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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.PopupWindow;

import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.Home_Fragments.MainFilterFragment;
import com.example.swiggy_lite.Home_Fragments.category_search;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.SearchActivity;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ItemDetailsAdapter;
import com.example.swiggy_lite.ItemDetailsActivity;
import com.example.swiggy_lite.models.FoodModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    RecyclerView category_recyclerView, recommendation_recyclerView;
    RecyclerView.Adapter categoryAdapter, recommendationAdapter;
    ArrayList<FoodModel> foodItemList;
    private PopupWindow popupWindow;
    CheckBox sort_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        category_recyclerView = view.findViewById(R.id.category_recyclerView);
        category_recyclerView.setLayoutManager(new GridLayoutManager(this.requireContext(), 1, RecyclerView.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(DummyData.categoryList, this.requireContext());
        CategoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                load(new category_search());
            }
        });
        category_recyclerView.setAdapter(categoryAdapter);


        SearchView food_item_searchView = view.findViewById(R.id.food_item_searchView);
        food_item_searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("isSearchBarActivated", true);
                startActivity(intent);
            }
        });

        recommendation_recyclerView = view.findViewById(R.id.recommendation_recyclerView);
        recommendation_recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(),RecyclerView.VERTICAL,false));
        recommendationAdapter = new ItemDetailsAdapter(DummyData.foodItemList, this.requireContext());
        ItemDetailsAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(requireContext(), ItemDetailsActivity.class);
                startActivity(intent);
            }
        });
        recommendation_recyclerView.setAdapter(recommendationAdapter);

        CheckBox filter_button = view.findViewById(R.id.filter_textView);
        filter_button.setOnClickListener(v -> {
            BottomSheetDialogFragment filter_fragment = new MainFilterFragment(filter_button);
            if (filter_button.isChecked()) {
                FragmentManager fragmentManager = this.getFragmentManager();
                filter_fragment.show(Objects.requireNonNull(fragmentManager),"filter");
            } else {
            }
        });

        CheckBox fast_delivery = view.findViewById(R.id.fast_delivery_textView);
        fast_delivery.setOnClickListener( v -> {
            if (fast_delivery.isChecked()) {
                fast_delivery.setBackgroundResource(R.drawable.active_button_background);
                fast_delivery.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.cancel_icon, 0);
            } else {
                fast_delivery.setBackgroundResource(R.drawable.rounded_corner_background);
                fast_delivery.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        });

        CheckBox cuisine = view.findViewById(R.id.cuisine_textView);
        cuisine.setOnClickListener( v -> {
            if (cuisine.isChecked()) {
                cuisine.setBackgroundResource(R.drawable.active_button_background);
                cuisine.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.cancel_icon, 0);
            } else {
                cuisine.setBackgroundResource(R.drawable.rounded_corner_background);
                cuisine.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        });

        CheckBox pureVeg = view.findViewById(R.id.pure_veg_textView);
        pureVeg.setOnClickListener( v -> {
            if (pureVeg.isChecked()) {
                pureVeg.setBackgroundResource(R.drawable.active_button_background);
                pureVeg.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.cancel_icon, 0);
            } else {
                pureVeg.setBackgroundResource(R.drawable.rounded_corner_background);
                pureVeg.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        });

        CheckBox offers = view.findViewById(R.id.offers_textView);
        offers.setOnClickListener( v -> {
            if (offers.isChecked()) {
                offers.setBackgroundResource(R.drawable.active_button_background);
                offers.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.cancel_icon, 0);
            } else {
                offers.setBackgroundResource(R.drawable.rounded_corner_background);
                offers.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        });

        sort_button = view.findViewById(R.id.sort_textView);
        sort_button.setOnClickListener( v -> { if (sort_button.isChecked()) {showCustomDialog(v);}
        });

        return view;
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showCustomDialog(View anchorView) {
        sort_button.setBackgroundResource(R.drawable.active_button_background);
        sort_button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dropup_icon, 0);

        LayoutInflater inflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.sort_dialog, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // if the popup window should dismiss when clicking outside
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window below the button
        popupWindow.showAsDropDown(anchorView, 0, 0);

        // dismiss the popup window when clicked
        popupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                sort_button.setChecked(false);
                sort_button.setBackgroundResource(R.drawable.rounded_corner_background);
                sort_button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dropdown_icon, 0);
            }
        });
    }

}