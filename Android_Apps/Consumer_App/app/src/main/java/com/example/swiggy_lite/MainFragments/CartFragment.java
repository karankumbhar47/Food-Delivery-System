package com.example.swiggy_lite.MainFragments;

import android.content.SharedPreferences;
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
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiggy_lite.Cart_Fragment.SelectAddressFragment;
import com.example.swiggy_lite.Common_Fragment.ItemDetailsFragment;
import com.example.swiggy_lite.LoadingDialog;
import com.example.swiggy_lite.MasterActivity;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.adapters.CartAdapter;
import com.example.swiggy_lite.models.OrderItemAdvanced;
import com.example.swiggy_lite.models.OrderModel;
import com.openapi.deliveryApp.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    private int tip = 0, total_items;
    private ImageView back_button;
    private static OrderModel orderModel;
    private EditText custom_tip_editText;
    private RecyclerView orderRecyclerView;
    private CartAdapter cartAdapter;
    private CardView back_to_add_button, select_address_button, title_card, empty_card;
    private static List<OrderItemAdvanced> orderedItemsList;
    private CheckBox tip_20_textView, tip_30_textView, tip_50_textView, tip_other_textView;
    private TextView item_total, delivery_fee, delivery_tip, GST_restaurant_charges, total_payment;
    private ConstraintLayout main_layout;
    private LoadingDialog loadingDialog;

    public CartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        {
            orderedItemsList = new ArrayList<>(MasterActivity.itemCart.values());
            loadingDialog = new LoadingDialog(requireActivity());
            title_card = view.findViewById(R.id.header_card);
            main_layout = view.findViewById(R.id.main_layout);
            empty_card = view.findViewById(R.id.empty_cart_cardView);
            back_button = view.findViewById(R.id.back_button_imageView);
            tip_20_textView = view.findViewById(R.id.tip_20_textView);
            tip_30_textView = view.findViewById(R.id.tip_30_textView);
            tip_50_textView = view.findViewById(R.id.tip_50_textView);
            item_total = view.findViewById(R.id.item_total_textView);
            delivery_fee = view.findViewById(R.id.delivery_fee_textView);
            delivery_tip = view.findViewById(R.id.delivery_tip_textView);
            total_payment = view.findViewById(R.id.total_payment_textView);
            tip_other_textView = view.findViewById(R.id.tip_others_textView);
            custom_tip_editText = view.findViewById(R.id.custom_tip_editText);
            back_to_add_button = view.findViewById(R.id.back_to_add_cardView);
            select_address_button = view.findViewById(R.id.select_address_cardView);
            orderRecyclerView = view.findViewById(R.id.delivery_details_recyclerView);
            GST_restaurant_charges = view.findViewById(R.id.GST_restaurant_charges_textView);
        }

        loadingDialog.startLoadingDialog();

        if (!orderedItemsList.isEmpty()) {
            int childCount = main_layout.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View child = main_layout.getChildAt(i);
                child.setVisibility(View.VISIBLE);
            }

            cartAdapter = new CartAdapter(orderedItemsList);
            cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
                @Override
                public void onMinusClick(int position, int quantity) {
                    OrderItemAdvanced foodModel = orderedItemsList.get(position);
                    foodModel.setQuantity(quantity);
                    MasterActivity.addToCart(foodModel,quantity);
                    cartAdapter.setList(new ArrayList<>(MasterActivity.itemCart.values()));
                    updateUI();
                }

                @Override
                public void onPlusClick(int position, int quantity) {
                    OrderItemAdvanced foodModel = orderedItemsList.get(position);
                    if(quantity<=foodModel.getMaxQuantity()) {
                        foodModel.setQuantity(quantity);
                        MasterActivity.addToCart(foodModel, quantity);
                        cartAdapter.setList(new ArrayList<>(MasterActivity.itemCart.values()));
                        updateUI();
                    }
                    else{
                        Toast.makeText(requireContext(),"Only "+foodModel.getMaxQuantity()+" items are available", Toast.LENGTH_SHORT).show();
                    }
                }

            });
            orderRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
            orderRecyclerView.setAdapter(cartAdapter);

            updateUI();
            loadingDialog.dismissDialog();
        } else {
            int childCount = main_layout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = main_layout.getChildAt(i);
                child.setVisibility(View.GONE);
            }
            title_card.setVisibility(View.VISIBLE);
            empty_card.setVisibility(View.VISIBLE);
            loadingDialog.dismissDialog();
        }

        select_address_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderModel = new OrderModel();
                orderModel.setOrderDetails(new ArrayList<>(MasterActivity.itemCart.values()));
                orderModel.setTip(tip);
                load(new SelectAddressFragment(orderModel));
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(new ItemDetailsFragment(String.valueOf(orderedItemsList.get(0).getItemId())));
            }
        });
        tip_20_textView.setOnClickListener(v -> {
            setTipLayout(tip_20_textView);
        });
        tip_30_textView.setOnClickListener(v -> {
            setTipLayout(tip_30_textView);
        });
        tip_50_textView.setOnClickListener(v -> {
            setTipLayout(tip_50_textView);
        });
        tip_other_textView.setOnClickListener(v -> {
            setTipLayout(tip_other_textView);
        });
        back_to_add_button.setOnClickListener(v -> {

        });
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

        return view;
    }

    private void updateUI() {
        item_total.setText("₹ " + String.valueOf(calculateItemTotal(orderedItemsList)));
        calculateTotalPayment();
    }

    public void setTipLayout(CheckBox active) {
        CheckBox[] textViews = new CheckBox[]{
                tip_20_textView,
                tip_30_textView,
                tip_50_textView,
                tip_other_textView
        };

        this.tip = 0;
        for (CheckBox item : textViews) {
            if (item.isChecked() && item.equals(active)) {
                item.setBackgroundResource(R.drawable.tip_button_active_background);
                item.setTextColor(Color.parseColor("#E87600"));

                this.tip = item == tip_20_textView ? 20
                        : item == tip_30_textView ? 30
                        : item == tip_50_textView ? 50
                        : item == tip_other_textView ? Integer.parseInt(custom_tip_editText.getText().toString())
                        : 0;
            } else {
                item.setBackgroundResource(R.drawable.tip_button_inactive_background);
                item.setTextColor(Color.parseColor("#000000"));
                item.setChecked(false);
            }
        }
        delivery_tip.setText("₹ " + String.valueOf(this.tip));
        custom_tip_editText.setVisibility(tip_other_textView.isChecked() ? View.VISIBLE : View.GONE);
        calculateTotalPayment();
    }

    public int calculateItemTotal(List<OrderItemAdvanced> list) {
        float sum = 0;
        for (OrderItemAdvanced foodModel : list) {
            sum +=foodModel.getPrice()*foodModel.getQuantity();
        }
        return (int)sum;
    }

    public void calculateTotalPayment() {
        float sum = (float) (Float.parseFloat(item_total.getText().toString().split(" ")[1])
                + Float.parseFloat(delivery_fee.getText().toString().split(" ")[1])
                + Float.parseFloat(delivery_tip.getText().toString().split(" ")[1])
                + 3f
                + Float.parseFloat(GST_restaurant_charges.getText().toString().split(" ")[1]));

        total_payment.setText(String.format("₹ %s", String.valueOf(Math.round(sum))));
    }

    void load(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}