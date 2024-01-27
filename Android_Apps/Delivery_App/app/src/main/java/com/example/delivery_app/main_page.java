package com.example.delivery_app;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.delivery_app.ui.main.SectionsPagerAdapter;
import com.example.delivery_app.databinding.ActivityMainPageBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.viewpager2.widget.ViewPager2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


//////////////////////////////////////////////////////////////////////
//
//public class main_page extends AppCompatActivity {
//
//    private ActivityMainPageBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        ViewPager viewPager = binding.viewList
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = binding.tabs;
//        tabs.setupWithViewPager(viewPager);
//        //FloatingActionButton fab = binding.fab;
//
//        // Initialize fragments
//        Fragment newOrdersFragment = new OrdersFragment("New Orders");
//        Fragment ongoingOrdersFragment = new OrdersFragment("Ongoing Orders");
//        Fragment completedOrdersFragment = new OrdersFragment("Completed Orders");
//
//        // Set up onClick listeners for the tabs
//        tabs.getTabAt(0).setOnClickListener(view -> showFragment(newOrdersFragment));
//        tabs.getTabAt(1).setOnClickListener(view -> showFragment(ongoingOrdersFragment));
//        tabs.getTabAt(2).setOnClickListener(view -> showFragment(completedOrdersFragment));
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    private void showFragment(Fragment fragment) {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragmentContainer, fragment)
//                .commit();
//    }
//}
//
//class OrdersFragment extends Fragment {
//
//    private String category;
//
//    public OrdersFragment(String category) {
//        this.category = category;
//    }
//
//    // Implement onCreateView to inflate the layout for the specific category
//}

//////////////////////////////////////////////////////////////////////////


public class main_page extends AppCompatActivity {

    private ActivityMainPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this);
        ViewPager2 viewPager = binding.viewList; // Change to ViewPager2
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = binding.tabs;
        new TabLayoutMediator(tabs, viewPager, (tab, position) -> {
            // Set tab titles here if needed
        }).attach();

        FloatingActionButton fab = binding.fab;


        // Initialize fragments
        Fragment newOrdersFragment = new OrdersFragment("New Orders");
        Fragment ongoingOrdersFragment = new OrdersFragment("Ongoing Orders");
        Fragment completedOrdersFragment = new OrdersFragment("Completed Orders");

        // Set up onClick listeners for the tabs
        tabs.getTabAt(0).select(view -> showFragment(newOrdersFragment));
        tabs.getTabAt(1).select(view -> showFragment(ongoingOrdersFragment));
        tabs.getTabAt(2).select(view -> showFragment(completedOrdersFragment));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        private void showFragment(Fragment fragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

    static class OrdersFragment extends Fragment {

        private String category;

        public OrdersFragment(String category) {
            this.category = category;
        }

        // Implement onCreateView to inflate the layout for the specific category
    }
}
