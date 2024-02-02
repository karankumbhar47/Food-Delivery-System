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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.LoadingDialog;
import com.example.swiggy_lite.MainFragments.CartFragment;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.CategoryAdapter;
import com.example.swiggy_lite.adapters.ItemDetailsAdapter;
import com.example.swiggy_lite.models.OrderModel;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.FoodItemFull;
import com.openapi.deliveryApp.model.OrderItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ItemDetailsFragment extends Fragment implements View.OnFocusChangeListener {
    public interface ApiResponseCallback {
        void onRegistrationSuccess(FoodItemFull foodItemFull);
        void onRegistrationError(int errorCode, String errorMessage);
    }

    private ConstraintLayout number_picker;
    private TextView numberText, added_items_status, view_cart_button;
    private ImageView plusIcon, minusIcon, item_pic;
    private TextView item_name_textView, rating_textView, price_textView;
    private CardView add_button, sticky_button, searchCard, recyclerViewCard;
    private SearchView searchView;
    private ItemDetailsAdapter adapter;
    private String item_id;
    private DefaultApi api;
    private NestedScrollView nestedScrollView;
    private boolean isCardTranslated = false;
    private RecyclerView recyclerView;
    private LoadingDialog loadingDialog;
    private OrderModel orderModel;
    private ArrayList<OrderItem> orderedList;
    private OrderItem orderItem;
    private Map<String,OrderItem> orderItemMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details,container,false);

        {
            api = new DefaultApi();
            orderItem = new OrderItem();
            orderModel = new OrderModel();
            orderedList = new ArrayList<>();
            orderItemMap = new HashMap<>();
            loadingDialog = new LoadingDialog(requireActivity());
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
            number_picker = view.findViewById(R.id.numberPicker_constrainLayout);
            item_name_textView = view.findViewById(R.id.food_item_name_textView);
            price_textView = view.findViewById(R.id.price_textView);
            rating_textView = view.findViewById(R.id.rating_textView);
            view_cart_button = view.findViewById(R.id.view_cart_textView);

            int drawableId = getResources().getIdentifier("food_image","drawable",this.requireContext().getPackageName());
            Glide.with(this).load(drawableId).into(item_pic);
        }

        getProductDetails(this.item_id.trim(), new ApiResponseCallback() {
            @Override
            public void onRegistrationSuccess(FoodItemFull foodItem) {
                Random random = new Random();
                int randomNumber = random.nextInt(1000) + 1;

                item_name_textView.setText(foodItem.getItemName());
                rating_textView.setText((CharSequence) foodItem.getStarRating() +"("+String.valueOf(randomNumber)+"+ ratings)");
                price_textView.setText("₹ "+foodItem.getPrice());

                orderItem.setItemId(foodItem.getItemId());
                orderItem.setItemName(foodItem.getItemName());
                orderItem.setPrice(foodItem.getPrice());
                orderItem.setQuantity(0);
            }

            @Override
            public void onRegistrationError(int errorCode, String errorMessage) {
                if(errorCode==0)
                    Toast.makeText(requireContext(), "Please Check your internet connection", Toast.LENGTH_SHORT).show();
                else{
                    if(errorMessage!=null){
                        if(errorCode == 404){
                            Toast.makeText(requireContext(), "Unable to load details", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

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
                    changeQuantity(currentValue);
                }
            }
        });
        minusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(numberText.getText().toString());
                if (currentValue > 1) {
                    animateNumberChange(--currentValue,false);
                    changeQuantity(currentValue);
                }
                else{
                    number_picker.setVisibility(number_picker.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
                    add_button.setVisibility(add_button.getVisibility()==View.GONE ? View.VISIBLE : View.GONE);
                    sticky_button.setVisibility(View.GONE);
                }
                added_items_status.setText(String.format("%d %s added", currentValue, currentValue == 1 ? " item " : " items "));
            }
        });
        view_cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(new CartFragment());
            }
        });

        recyclerView = view.findViewById(R.id.item_details_recyclerView);
        adapter = new ItemDetailsAdapter(DummyData.dummyFoodList,requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false));
        ItemDetailsAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                load(new ItemDetailsFragment(String.valueOf(DummyData.dummyFoodList.get(position).getItemId())));
            }
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void changeQuantity(int quantity){
        if(quantity!=0) {
            orderItem.setQuantity(quantity);
            orderedList.add(0, orderItem);
            orderModel.setOrderDetails(orderedList);
            orderItemMap.put(item_id, orderItem);
            orderModel.saveToSharedPreferences(requireContext(), orderItemMap);
        }
        else{
            orderedList.clear();
            orderModel.setOrderDetails(orderedList);
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

    public ItemDetailsFragment(String ItemId){
        this.item_id = ItemId;
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
        List<FoodItemFull> filteredList = new ArrayList<>();
        for (FoodItemFull item: DummyData.dummyFoodList) {
            if (item.getItemName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            adapter.setList(filteredList);
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

    public void getProductDetails(String item_id, ApiResponseCallback callback){
        Log.d("myTag", "getProductDetails: len "+item_id.length());
        Log.d("myTag", "getProductDetails: len "+item_id);

        loadingDialog.startLoadingDialog();
        api.getProduct(item_id, new Response.Listener<com.openapi.deliveryApp.model.FoodItemFull>() {
            @Override
            public void onResponse(com.openapi.deliveryApp.model.FoodItemFull foodItem) {
                callback.onRegistrationSuccess(foodItem);
                loadingDialog.dismissDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    int statusCode = error.networkResponse.statusCode;
                    String data = new String(error.networkResponse.data);
                    callback.onRegistrationError(statusCode, data.trim().replace("\"",""));
                    loadingDialog.dismissDialog();
                } catch (Exception e) {
                    callback.onRegistrationError(0,null);
                    loadingDialog.dismissDialog();
                }
            }
        });
    }

}