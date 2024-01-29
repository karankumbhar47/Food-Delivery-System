package com.example.swiggy_lite.MainFragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swiggy_lite.MainActivity;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.Setting_Fragment.AboutFragment;
import com.example.swiggy_lite.Setting_Fragment.UserProfileFragment;

public class SettingFragment extends Fragment {
    public SettingFragment() {}

    CardView edit_profile_card, logout_card, about_card, favourite_order_card, favourite_restaurant_card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_setting, container, false);
        edit_profile_card = view.findViewById(R.id.edit_profile_card);
        logout_card = view.findViewById(R.id.logout_card);
        about_card = view.findViewById(R.id.about_card);
        favourite_restaurant_card = view.findViewById(R.id.favourite_restaurant_card);
        favourite_order_card = view.findViewById(R.id.favourite_order_card);

        edit_profile_card.setOnClickListener(v -> {load(new UserProfileFragment());});


        logout_card.setOnClickListener(v -> new AlertDialog.Builder(getActivity())
                .setTitle("Confirm")
                .setMessage("Are you sure you want to go back?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    String[] preferences = new String[]{};
                    for (String preference: preferences) {
                        SharedPreferences pref = requireActivity().getSharedPreferences(preference,MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.clear();
                        editor.apply();
                    }

                    /*SharedPreferences prefCredentials = requireActivity().getSharedPreferences(AppConstants.PREF_CREDENTIALS,MODE_PRIVATE);
                    SharedPreferences.Editor editorCredentials = prefCredentials.edit();
                    editorCredentials.putBoolean(AppConstants.KEY_LOGIN_FLAG,false);
                    editorCredentials.apply();*/
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                })
                .setNegativeButton("No", null)
                .show());

        about_card.setOnClickListener(v -> {load(new AboutFragment());});

        return view;
    }

    void load(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}