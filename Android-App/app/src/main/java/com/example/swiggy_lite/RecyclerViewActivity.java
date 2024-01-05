package com.example.swiggy_lite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.swiggy_lite.adapters.CategoryAdapter;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        ArrayList<String> item1 = new ArrayList<>();
        item1.add("Rice");
        item1.add("200");
        item1.add("Good");

        ArrayList<String> item2 = new ArrayList<>();
        item2.add("Wheat");
        item2.add("300");
        item2.add("Very Good");

        ArrayList<ArrayList<String>>  info = new ArrayList<>();
        info.add(item1);
        info.add(item2);
        info.add(item2);
        info.add(item2);
        info.add(item2);

        recyclerView = findViewById(R.id.demo_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL , false));
//        adapter = new CategoryAdapter(info);
//        recyclerView.setAdapter(adapter);
    }
}