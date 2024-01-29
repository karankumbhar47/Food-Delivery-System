package com.example.swiggy_lite.MainFragments;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swiggy_lite.DummyData;
import com.example.swiggy_lite.Common_Fragment.ItemDetailsFragment;
import com.example.swiggy_lite.MainPage;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.Cart_Fragment.SelectAddressFragment;
import com.example.swiggy_lite.adapters.PlaceOrderAdapter;
import com.example.swiggy_lite.models.FoodModel;
import com.example.swiggy_lite.models.OrderModel;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    RecyclerView orderRecyclerView;
    PlaceOrderAdapter placeOrderAdapter;
    CardView back_to_add_button, select_address_button;
    CheckBox tip_20_textView, tip_30_textView,  tip_50_textView, tip_other_textView;
    TextView item_total, delivery_fee, delivery_tip, GST_restaurant_charges, total_payment;
    EditText custom_tip_editText;
    ImageView back_button;
    ArrayList<FoodModel> orderedItemsList;
    OrderModel orderModel;
    int tip = 0,total_items;

    public CartFragment(OrderModel orderModel){
        this.orderModel = orderModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart,container,false);
        {
            orderedItemsList = orderModel.getOrderedItems();
            back_button = view.findViewById(R.id.back_button_imageView);
            back_to_add_button = view.findViewById(R.id.back_to_add_cardView);
            orderRecyclerView = view.findViewById(R.id.delivery_details_recyclerView);
            tip_20_textView = view.findViewById(R.id.tip_20_textView);
            tip_30_textView = view.findViewById(R.id.tip_30_textView);
            tip_50_textView = view.findViewById(R.id.tip_50_textView);
            tip_other_textView = view.findViewById(R.id.tip_others_textView);
            custom_tip_editText = view.findViewById(R.id.custom_tip_editText);
            item_total = view.findViewById(R.id.item_total_textView);
            delivery_fee = view.findViewById(R.id.delivery_fee_textView);
            delivery_tip = view.findViewById(R.id.delivery_tip_textView);
            GST_restaurant_charges = view.findViewById(R.id.GST_restaurant_charges_textView);
            total_payment = view.findViewById(R.id.total_payment_textView);
            select_address_button = view.findViewById(R.id.select_address_cardView);
            calculateTotalPayment();
        }

        tip_20_textView.setOnClickListener(v ->{setTipLayout(tip_20_textView);});
        tip_30_textView.setOnClickListener(v ->{setTipLayout(tip_30_textView);});
        tip_50_textView.setOnClickListener(v ->{setTipLayout(tip_50_textView);});
        tip_other_textView.setOnClickListener(v ->{setTipLayout(tip_other_textView);});


        back_to_add_button.setOnClickListener(v ->{
            Intent intent1 = new Intent(requireContext(), MainPage.class);
            startActivity(intent1);
        });

        if(orderedItemsList!=null) {
            ConstraintLayout main_layout = view.findViewById(R.id.main_layout);
            int childCount = main_layout.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View child = main_layout.getChildAt(i);
                child.setVisibility(View.VISIBLE);
            }

            orderRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
            placeOrderAdapter = new PlaceOrderAdapter(orderedItemsList);
            DummyData.selected = orderedItemsList;
            item_total.setText("₹ " + String.valueOf(calculateItemTotal(orderedItemsList)));
            calculateTotalPayment();
            placeOrderAdapter.setOnItemClickListener(new PlaceOrderAdapter.OnItemClickListener() {
                @Override
                public void onMinusClick(int position, int quantity) {
                    FoodModel foodModel = orderedItemsList.get(position);
                    if (quantity > 0) {
                        foodModel.setQuantity(quantity);
                        orderedItemsList.set(position, foodModel);
                        item_total.setText("₹ " + String.valueOf(calculateItemTotal(orderedItemsList)));
                        calculateTotalPayment();
                    } else {
                        orderedItemsList.remove(position);
                        item_total.setText("₹ " + String.valueOf(calculateItemTotal(orderedItemsList)));
                        calculateTotalPayment();
                    }
                    placeOrderAdapter.setList(orderedItemsList);
                    DummyData.selected = orderedItemsList;
                }

                @Override
                public void onPlusClick(int position, int quantity) {
                    FoodModel foodModel = orderedItemsList.get(position);
                    foodModel.setQuantity(quantity);
                    orderedItemsList.set(position, foodModel);
                    item_total.setText("₹ " + String.valueOf(calculateItemTotal(orderedItemsList)));
                    calculateTotalPayment();
                    placeOrderAdapter.setList(orderedItemsList);
                    DummyData.selected = orderedItemsList;
                }
            });
            orderRecyclerView.setAdapter(placeOrderAdapter);

            custom_tip_editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    try {
                        tip = Integer.parseInt(editable.toString());
                    } catch (Exception ignored) {
                        tip = 0;
                    }

                    if (!editable.toString().isEmpty() || tip > 10) {
                        delivery_tip.setText(editable.toString());
                    } else {
                        tip = 10;
                        custom_tip_editText.setText("10");
                        delivery_tip.setText("10");
                        custom_tip_editText.setError("Tip should be at least ₹ 10");
                    }
                }
            });


            select_address_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    load(new SelectAddressFragment());
                }
            });

            back_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    load(new ItemDetailsFragment());
                }
            });
        }
        else{
            ConstraintLayout linearLayout = view.findViewById(R.id.main_layout);
            int childCount = linearLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = linearLayout.getChildAt(i);
                child.setVisibility(View.GONE);
            }
            CardView title = view.findViewById(R.id.header_card);
            title.setVisibility(View.VISIBLE);
            CardView empty = view.findViewById(R.id.empty_cart_cardView);
            empty.setVisibility(View.VISIBLE);
        }
        return view;
    }

    public void setTipLayout(CheckBox active){
        CheckBox [] textViews = new CheckBox[]{
                tip_20_textView,
                tip_30_textView,
                tip_50_textView,
                tip_other_textView
        };

        this.tip = 0;
        for (CheckBox item: textViews) {
            if (item.isChecked() && item.equals(active)) {
                item.setBackgroundResource(R.drawable.tip_button_active_background);
                item.setTextColor(Color.parseColor("#E87600"));

                this.tip = item == tip_20_textView ? 20
                        : item == tip_30_textView ? 30
                        : item == tip_50_textView ? 50
                        : item == tip_other_textView ? Integer.parseInt(custom_tip_editText.getText().toString())
                        : 0 ;
            }
            else{
                item.setBackgroundResource(R.drawable.tip_button_inactive_background);
                item.setTextColor(Color.parseColor("#000000"));
                item.setChecked(false);
            }
        }
        delivery_tip.setText("₹ "+String.valueOf(this.tip));
        custom_tip_editText.setVisibility(tip_other_textView.isChecked() ? View.VISIBLE : View.GONE);
        calculateTotalPayment();
    }

    public int calculateItemTotal(ArrayList<FoodModel> list){
        int sum = 0;
        for (FoodModel foodmodel : list) {
             sum+= foodmodel.getPrice() * foodmodel.getQuantity();
        }
        return sum;
    }

    public void calculateTotalPayment(){
        int sum = (int) (Integer.parseInt(item_total.getText().toString().split(" ")[1])
                        + Integer.parseInt(delivery_fee.getText().toString().split(" ")[1])
                        + Float.parseFloat(delivery_tip.getText().toString().split(" ")[1])
                        + 3
                        + Float.parseFloat(GST_restaurant_charges.getText().toString().split(" ")[1]));
        total_payment.setText(String.format("₹ %s", String.valueOf(sum)));
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}