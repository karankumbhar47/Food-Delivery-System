package com.example.swiggy_lite;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.swiggy_lite.MainFragments.CartFragment;
import com.example.swiggy_lite.MainFragments.HistoryFragment;
import com.example.swiggy_lite.MainFragments.HomeFragment;
import com.example.swiggy_lite.MainFragments.SettingFragment;
import com.example.swiggy_lite.models.OrderItemAdvanced;
import com.example.swiggy_lite.models.OrderModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.openapi.deliveryApp.model.FoodItemFull;
import com.openapi.deliveryApp.model.OrderItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MasterActivity extends AppCompatActivity {
    public static BottomNavigationView bottomNavigationBar;
    public static Map<String, OrderItemAdvanced> itemCart;
    public static final String TAG = "myTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        {
            itemCart = new HashMap<>();
            bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        }

        load(new HomeFragment());
        bottomNavigationBar.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home_menu) {
                load(new HomeFragment());
            } else if (id == R.id.setting_menu) {
                load(new SettingFragment());
            } else if (id == R.id.history_menu) {
                load(new HistoryFragment());
            } else if (id == R.id.cart_menu) {
                load(new CartFragment());
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

    public static void addToCart(FoodItemFull item, int quantity, boolean isIncrease) {
        if (itemCart.get(item.getItemId()) == null && isIncrease && quantity<=item.getMaxQuantity()) {
            OrderItemAdvanced orderItem = new OrderItemAdvanced();
            orderItem.setItemName(item.getItemName());
            orderItem.setPrice(item.getPrice());
            orderItem.setItemId(item.getItemId());
            orderItem.setQuantity(quantity);
            orderItem.setMaxQuantity(item.getMaxQuantity());
            Log.d("myTag", "initial quan "+quantity);
            itemCart.put(item.getItemId(), orderItem);
        } else {
            OrderItemAdvanced orderItem = itemCart.get(item.getItemId());
            if (quantity <= 0) {
                itemCart.remove(item.getItemId());
                //Log.d("myTag", "remove "+item.getItemId());
                Log.d("myTag", "size in cart "+itemCart.size());
            } else {
                orderItem.setQuantity(quantity);
                itemCart.put(orderItem.getItemId(), orderItem);
                //Log.d("myTag", "add "+orderItem.getQuantity()+ " "+orderItem.getItemId());
                Log.d("myTag", "size in cart "+itemCart.size());
            }
        }
    }

    public static void addToCart(OrderItemAdvanced item, int quantity) {
        if (quantity <= 0) {
            itemCart.remove(item.getItemId());
            Log.d("myTag", "size in cart "+itemCart.size());
        } else {
            itemCart.put(item.getItemId(), item);
            Log.d("myTag", "size in cart "+itemCart.size());
        }
    }

    @Override
    protected void onStop() {
        SharedPreferences prefCart = getSharedPreferences(AppConstants.PREF_CART_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editorCart = prefCart.edit();
        Log.d("myTag", "onStop:cart flag previous "+prefCart.getBoolean(AppConstants.KEY_IS_DATA_CHANGED,false));
        if(!prefCart.getBoolean(AppConstants.KEY_IS_DATA_CHANGED,false)){
            OrderModel.saveToSharedPreferences(this,itemCart);
            editorCart.putBoolean(AppConstants.KEY_IS_DATA_CHANGED,true);
            editorCart.apply();
        }
        Log.d("myTag", "onStop:cart flag after "+prefCart.getBoolean(AppConstants.KEY_IS_DATA_CHANGED,false));
        super.onStop();
    }

//    public static String logMapEntries() {
//        StringBuilder logMessage = new StringBuilder("Map Entries: {");
//        for (Map.Entry<String, OrderItem> entry : itemCart.entrySet()) {
//            logMessage.append(entry.getKey())
//                    .append(" = ")
//                    .append(entry.getValue().getItemName())
//                    .append(" ==> ")
//                    .append(entry.getValue().getQuantity())
//                    .append(", ");
//        }
//        if (itemCart.size() > 0) { logMessage.setLength(logMessage.length() - 2); }
//        logMessage.append("}");
//        System.out.println(logMessage.toString());
//        return logMessage.toString();
//    }



}
