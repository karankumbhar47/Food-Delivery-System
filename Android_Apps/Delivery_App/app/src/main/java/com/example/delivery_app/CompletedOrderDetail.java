package com.example.delivery_app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompletedOrderDetail extends AppCompatActivity {
    public CompletedOrderDetail() {
    }

    TextView user_name_textview;
    TextView order_id_textview;
    TextView date_textview;
    TextView phone_textview;
    TextView email_textview;
    TextView pick_location_textview;
    TextView drop_location_textview;
    TextView message_textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        user_name_textview = findViewById(R.id.userName_textView);
        date_textview = findViewById(R.id.date_textView);
        order_id_textview = findViewById(R.id.orderId_textView);
        phone_textview = findViewById(R.id.phone_textView);
        email_textview = findViewById(R.id.email_textView);
        pick_location_textview = findViewById(R.id.pickLocation_textView);
        drop_location_textview = findViewById(R.id.dropLocation_textView);
        message_textview = findViewById(R.id.message_textView);






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order_detail);
    }




}
