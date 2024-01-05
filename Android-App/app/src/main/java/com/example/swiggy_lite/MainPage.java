package com.example.swiggy_lite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.swiggy_lite.MainFragments.CartFragment;
import com.example.swiggy_lite.MainFragments.HistoryFragment;
import com.example.swiggy_lite.MainFragments.HomeFragment;
import com.example.swiggy_lite.MainFragments.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        load(new HomeFragment());

        BottomNavigationView bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.home_menu){load(new HomeFragment());}
            else if(id == R.id.setting_menu){load(new SettingFragment());}
            else if(id == R.id.history_menu){load(new HistoryFragment());}
            else if(id == R.id.cart_menu){load(new CartFragment());}
            return true;});
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();}
}