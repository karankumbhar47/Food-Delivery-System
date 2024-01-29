package com.example.vendor_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.vendor_app.Adapter.menuCardAdapter;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    RecyclerView menuCardRecyclerView;
    com.example.vendor_app.Adapter.menuCardAdapter menuCardAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ArrayList<String> completeArray = new ArrayList<>();

        completeArray.add("Pizza1");
        completeArray.add("Pizza2");
        completeArray.add("Pizza3");
        completeArray.add("Pizza4");
        completeArray.add("Pizza5");

        menuCardRecyclerView = findViewById(R.id.re);
        menuCardRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        //menuCardRecyclerView.setLayoutManager(new GridLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        menuCardAdapter = new menuCardAdapter(completeArray);
        menuCardRecyclerView.setAdapter(menuCardAdapter);


    }


}