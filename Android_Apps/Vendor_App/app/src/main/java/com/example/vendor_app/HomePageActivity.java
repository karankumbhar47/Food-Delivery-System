package com.example.vendor_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.vendor_app.Fragments.Home.HomeFragment;
import com.example.vendor_app.Fragments.Settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.openapi.deliveryApp.model.OrderItem;

import java.util.Map;


public class HomePageActivity extends AppCompatActivity {
    public static final int YOUR_REQUEST_CODE = 409;
    public static final int FRAGMENT_ITEM_DETAILS = 5;
    public static BottomNavigationView bottomNavigationBar;
    public static final String EXTRA_FRAGMENT_ID = "fragment_id";
    private boolean isFragmentLoaded = false;
    private String sessionId;
    public static Map<String, OrderItem> orderItemMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent intent = getIntent();
        sessionId = intent.getStringExtra(AppConstants.KEY_SESSION_ID);


        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home_menu) {
                load(new HomeFragment());
            } else if (id == R.id.setting_menu) {
                load(new SettingsFragment());
            } else if (id == R.id.history_menu) {
                load(new HomeFragment());
            } else if (id == R.id.cart_menu) {
                load(new HomeFragment());
            }
            return true;
        });
    }


    public void load(Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
        } else {
            showExitConfirmationDialog();
        }
    }

    @Override
    public void setTheme(int resId) {
        super.setTheme(resId);
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setCancelable(false)
                .setMessage("Are you sure you want to exit the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAndRemoveTask();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
