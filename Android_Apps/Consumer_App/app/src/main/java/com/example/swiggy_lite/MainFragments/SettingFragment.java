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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.swiggy_lite.AppConstants;
import com.example.swiggy_lite.Interface.RegistrationCallback;
import com.example.swiggy_lite.LoadingDialog;
import com.example.swiggy_lite.MainActivity;
import com.example.swiggy_lite.MasterActivity;
import com.example.swiggy_lite.R;
import com.example.swiggy_lite.Setting_Fragment.AboutFragment;
import com.example.swiggy_lite.Setting_Fragment.UserProfileFragment;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.Profile;

public class SettingFragment extends Fragment {
    public interface ProfileCallback{
        void onSuccessProfile(Profile profile);
        void onFailureProfile(int errorCode, String errorMessage);
    }

    CardView edit_profile_card, logout_card, about_card, favourite_order_card, favourite_restaurant_card;
    TextView name_text, phone_text, email_id_text;
    SharedPreferences prefLogin;
    SharedPreferences.Editor editorLogin;
    String sesstionId;
    public Profile userProfile;

    public SettingFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_setting, container, false);
        {
            edit_profile_card = view.findViewById(R.id.edit_profile_card);
            logout_card = view.findViewById(R.id.logout_card);
            about_card = view.findViewById(R.id.about_card);
            favourite_restaurant_card = view.findViewById(R.id.favourite_restaurant_card);
            favourite_order_card = view.findViewById(R.id.favourite_order_card);
            name_text = view.findViewById(R.id.user_name_textView);
            phone_text = view.findViewById(R.id.phone_number_textView);
            email_id_text = view.findViewById(R.id.email_id_textView);
            prefLogin = requireActivity().getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
            sesstionId = prefLogin.getString(AppConstants.KEY_SESSION_ID,"");
            editorLogin = prefLogin.edit();
            userProfile = new Profile();
        }

        getProfile(new ProfileCallback() {
            @Override
            public void onSuccessProfile(Profile profile) {
                userProfile = profile;
                name_text.setText(profile.getName());
                phone_text.setText(profile.getPhone());
                email_id_text.setText(profile.getEmail());
            }

            @Override
            public void onFailureProfile(int errorCode, String errorMessage) {
                name_text.setText(prefLogin.getString(AppConstants.KEY_USER_NAME,"Not found"));
                phone_text.setText("Update profile");
                email_id_text.setText("Update profile");
            }
        });

        edit_profile_card.setOnClickListener(v -> {load(new UserProfileFragment(userProfile));});
        logout_card.setOnClickListener(v -> {
                new AlertDialog.Builder(getActivity())
                .setTitle("Confirm")
                .setMessage("Are you sure you want to go back?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    for (String preference: AppConstants.LIST_PREF) {
                        Log.d("myTag", "preferences "+preference);
                        SharedPreferences pref = requireActivity().getSharedPreferences(preference,MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        MasterActivity.itemCart.clear();
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
                .show();
        });
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

    public void getProfile(ProfileCallback callback) {
        DefaultApi api = new DefaultApi();
        LoadingDialog loadingDialog = new LoadingDialog(requireActivity());
        loadingDialog.startLoadingDialog();
        api.getProfile(sesstionId, new Response.Listener<Profile>() {
            @Override
            public void onResponse(Profile response) {
                callback.onSuccessProfile(response);
                loadingDialog.dismissDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    int statusCode = error.networkResponse.statusCode;
                    String data = new String(error.networkResponse.data);
                    callback.onFailureProfile(statusCode, data.trim().substring(1,41));;
                    loadingDialog.dismissDialog();
                } catch (Exception e) {
                    callback.onFailureProfile(0, null);
                    loadingDialog.dismissDialog();
                }
            }
        });
    }
}