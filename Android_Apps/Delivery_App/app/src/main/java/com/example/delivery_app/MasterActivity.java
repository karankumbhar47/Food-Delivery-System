package com.example.delivery_app;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

public class MasterActivity extends AppCompatActivity {
    public MasterActivity() {
    }
    CardView user_image_card;

    View new_orders_tab;
    View ongoing_orders_tab;
    View completed_orders_tab;

    ViewPager2 list_of_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        user_image_card = findViewById(R.id.userImageCard);
        new_orders_tab = findViewById(R.id.newOrderTab);
        ongoing_orders_tab = findViewById(R.id.ongoingOrderTab);
        completed_orders_tab = findViewById(R.id.completedOrderTab);
        list_of_orders = findViewById(R.id.listOfOrders);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }
}