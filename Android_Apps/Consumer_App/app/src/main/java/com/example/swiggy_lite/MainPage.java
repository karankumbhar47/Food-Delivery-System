package com.example.swiggy_lite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.swiggy_lite.Common_Fragment.ItemDetailsFragment;
import com.example.swiggy_lite.MainFragments.CartFragment;
import com.example.swiggy_lite.MainFragments.HistoryFragment;
import com.example.swiggy_lite.MainFragments.HomeFragment;
import com.example.swiggy_lite.MainFragments.SettingFragment;
import com.example.swiggy_lite.models.OrderModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {
    public static final int YOUR_REQUEST_CODE = 409;
    public static final int FRAGMENT_ITEM_DETAILS = 5;
    public static BottomNavigationView bottomNavigationBar;
    public static final String EXTRA_FRAGMENT_ID = "fragment_id";
    private boolean isFragmentLoaded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Intent intent = getIntent();
        try{
            if(FRAGMENT_ITEM_DETAILS == intent.getIntExtra(EXTRA_FRAGMENT_ID,0)){
                load(new ItemDetailsFragment());
                isFragmentLoaded = true;
            }
        }catch ( Exception e) {}

        if(!isFragmentLoaded){
            load(new HomeFragment());
        }

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.home_menu){load(new HomeFragment());}
            else if(id == R.id.setting_menu){load(new SettingFragment());}
            else if(id == R.id.history_menu){load(new HistoryFragment());}
            else if(id == R.id.cart_menu){load(new CartFragment(new OrderModel()));}
            return true;});
    }


    public void load(Fragment fragment){
        FragmentManager fragmentManager =  this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();}
}