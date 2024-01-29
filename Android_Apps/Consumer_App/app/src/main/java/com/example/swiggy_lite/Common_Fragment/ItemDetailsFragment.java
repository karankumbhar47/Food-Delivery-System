package com.example.swiggy_lite.Common_Fragment;


import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.MainFragments.CartFragment;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ItemDetailsAdapter;
import com.example.swiggy_lite.models.FoodModel;
import com.example.swiggy_lite.models.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailsFragment extends Fragment implements View.OnFocusChangeListener {
    TextView numberText, added_items_status, view_cart_button;
    ImageView plusIcon, minusIcon, item_pic;
    CardView add_button, sticky_button, searchCard, recyclerViewCard;
    SearchView searchView;
    ItemDetailsAdapter adapter;
    private NestedScrollView nestedScrollView;
    private boolean isCardTranslated = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contianer, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details,contianer,false);
        item_pic = view.findViewById(R.id.item_profile_imageView);
        numberText = view.findViewById(R.id.numberText);
        plusIcon = view.findViewById(R.id.plusIcon);
        minusIcon = view.findViewById(R.id.minusIcon);
        add_button = view.findViewById(R.id.add_button_cardView);
        sticky_button = view.findViewById(R.id.stickyButton);
        view_cart_button = view.findViewById(R.id.view_cart_textView);
        added_items_status = view.findViewById(R.id.total_items_textView);
        searchView = view.findViewById(R.id.food_item_searchView);
        searchCard = view.findViewById(R.id.search_card);
        recyclerViewCard = view.findViewById(R.id.recyclerView_card);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);

        ConstraintLayout number_picker = view.findViewById(R.id.numberPicker_constrainLayout);



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

        // Set the OnQueryTextFocusChangeListener to the SearchView
        searchView.setOnFocusChangeListener(this);
        add_button.setOnClickListener(v -> {
            add_button.setVisibility(add_button.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
            number_picker.setVisibility(number_picker.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
            sticky_button.setVisibility(View.VISIBLE);
        });
        plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(numberText.getText().toString());
                if (currentValue < Integer.MAX_VALUE) {
                    animateNumberChange(++currentValue, true);
                    added_items_status.setText(String.format("%d %s added", currentValue, currentValue == 1 ? " item " : " items "));
                }
            }
        });
        minusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(numberText.getText().toString());
                if (currentValue > 1) {
                    animateNumberChange(--currentValue,false);
                }
                else{
                    number_picker.setVisibility(number_picker.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
                    add_button.setVisibility(add_button.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
                    sticky_button.setVisibility(View.GONE);
                }
                added_items_status.setText(String.format("%d %s added", currentValue, currentValue == 1 ? " item " : " items "));
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.item_details_recyclerView);
        adapter = new ItemDetailsAdapter(DummyData.foodItemList,requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false));
        ItemDetailsAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                load(new ItemDetailsFragment());
            }
        });
        recyclerView.setAdapter(adapter);

        view_cart_button = view.findViewById(R.id.view_cart_textView);
        view_cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderModel orderModel = new OrderModel();
                List<FoodModel> list = DummyData.foodItemList.subList(1,Integer.parseInt(numberText.getText().toString()));
                orderModel.setOrderedItems(new ArrayList<>(list));
                load(new CartFragment(orderModel));
            }
        });
        int drawableId = getResources()
                .getIdentifier("food_image","drawable",this.requireContext().getPackageName());

        Glide.with(this)
                .load(drawableId)
                .into(item_pic);

        return view;
    }

    private void animateNumberChange(int value, boolean isIncrease) {
        String newValue = String.valueOf(value);
        ObjectAnimator oldAnimator = ObjectAnimator.ofFloat(numberText,
                "translationY",
                0f,
                isIncrease ? -numberText.getHeight() : numberText.getHeight()
        );

        oldAnimator.setDuration(150);
        oldAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                numberText.setText(newValue);
                //numberText.setTranslationY(2*numberText.getHeight());

                ObjectAnimator newAnimator = ObjectAnimator.ofFloat(numberText,
                        "translationY",
                        isIncrease ? numberText.getHeight() : -numberText.getHeight(),
                        0f
                );
                newAnimator.setDuration(150);
                newAnimator.start();
            }
        });
        oldAnimator.start();
    }
    public void filteredList(String text) {
        ArrayList<FoodModel> filteredList = new ArrayList<>();
        for (FoodModel foodModel: DummyData.foodItemList) {
            if (foodModel.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(foodModel);
            }
            adapter.setList(filteredList);
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.food_item_searchView) {
            if (hasFocus) {
                // If search view gains focus, translate the cards to the top
                if (!isCardTranslated) {
                    translateCardsUp();
                    isCardTranslated = true;
                }
            } else {
                // If search view loses focus, translate the cards back down
                if (isCardTranslated) {
                    translateCardsDown();
                    isCardTranslated = false;
                }
            }
        }
    }


    private void translateCardsUp() {
        TranslateAnimation animateSearchCard = new TranslateAnimation(0, 0, 0, -searchCard.getHeight());
        animateSearchCard.setDuration(300);
        animateSearchCard.setFillAfter(true);
        searchCard.startAnimation(animateSearchCard);

        TranslateAnimation animateRecyclerViewCard = new TranslateAnimation(0, 0, 0, -recyclerViewCard.getHeight());
        animateRecyclerViewCard.setDuration(300);
        animateRecyclerViewCard.setFillAfter(true);
        recyclerViewCard.startAnimation(animateRecyclerViewCard);
    }

    private void translateCardsDown() {
        TranslateAnimation animateSearchCard = new TranslateAnimation(0, 0, -searchCard.getHeight(), 0);
        animateSearchCard.setDuration(300);
        animateSearchCard.setFillAfter(true);
        searchCard.startAnimation(animateSearchCard);

        TranslateAnimation animateRecyclerViewCard = new TranslateAnimation(0, 0, -recyclerViewCard.getHeight(), 0);
        animateRecyclerViewCard.setDuration(300);
        animateRecyclerViewCard.setFillAfter(true);
        recyclerViewCard.startAnimation(animateRecyclerViewCard);
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}