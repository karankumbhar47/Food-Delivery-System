package com.example.vendor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class OngoingOrder extends AppCompatActivity {
    String order_status;
    Spinner order_status_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_order);
        order_status_spinner = findViewById(R.id.order_status);
        setupDropdown();

    }
    private void setupDropdown() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.simple_spinner_item,
                new String[]{"Order In-Process", "Order Completed"}
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        order_status_spinner.setAdapter(adapter);

        order_status_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                order_status = (String) parentView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }


}
