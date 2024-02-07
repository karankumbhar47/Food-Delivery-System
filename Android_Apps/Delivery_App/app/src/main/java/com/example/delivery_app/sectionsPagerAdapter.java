package com.example.delivery_app;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SectionsPagerAdapter extends FragmentStateAdapter {

    public SectionsPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return the appropriate Fragment for each position
        switch (position) {
            case 0:
                return new MainPage.OrdersFragment("New Orders");
            case 1:
                return new MainPage.OrdersFragment("Ongoing Orders");
            case 2:
                return new MainPage.OrdersFragment("Completed Orders");
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        // Number of tabs
        return 3;
    }
}
