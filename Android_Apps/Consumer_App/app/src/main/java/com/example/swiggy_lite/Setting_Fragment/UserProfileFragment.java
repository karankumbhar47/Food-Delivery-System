package com.example.swiggy_lite.Setting_Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.cardemulation.CardEmulation;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.swiggy_lite.AppConstants;
import com.example.swiggy_lite.LoadingDialog;
import com.example.swiggy_lite.MainFragments.SettingFragment;
import com.example.swiggy_lite.R;
import com.google.android.material.textfield.TextInputEditText;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.Profile;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class UserProfileFragment extends Fragment {
    Profile userProfile;
    TextInputEditText name_editText, phone_editText, email_id_editText, dob_editText;
    AutoCompleteTextView gender_editText;
    CardView update_button_cardView;
    ImageView edit_profilePic_imageView, profile_pic_imageView;
    TextView user_name_textView;
    Profile.GenderEnum gender;
    SharedPreferences prefLogin;
    SharedPreferences.Editor editorLogin;
    String sessionId;
    boolean isDateSet = false;


    public UserProfileFragment(Profile profile) {
        userProfile = profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        {
            name_editText = view.findViewById(R.id.name_editText);
            phone_editText = view.findViewById(R.id.phone_number_editText);
            email_id_editText = view.findViewById(R.id.email_id_editText);
            dob_editText = view.findViewById(R.id.dob_editText);
            gender_editText = view.findViewById(R.id.gender_editText);
            update_button_cardView = view.findViewById(R.id.update_button_cardView);
            edit_profilePic_imageView = view.findViewById(R.id.edit_profile_image);
            profile_pic_imageView = view.findViewById(R.id.profile_pic_imageView);
            user_name_textView = view.findViewById(R.id.user_name_textView);
            prefLogin = requireActivity().getSharedPreferences(AppConstants.PREF_LOGIN, Context.MODE_PRIVATE);
            editorLogin = prefLogin.edit();
            sessionId = prefLogin.getString(AppConstants.KEY_SESSION_ID,"");
        }

        setProfile();
        update_button_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 updateProfile();
            }
        });

        String[] genders = {Profile.GenderEnum.Male.toString(), Profile.GenderEnum.Female.toString(), Profile.GenderEnum.Others.toString()};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, genders);
        gender_editText.setAdapter(adapter);
        gender_editText.setThreshold(1);
        gender_editText.setOnItemClickListener((parent, view1 , position, id) -> {
            String selectedGender = (String) parent.getItemAtPosition(position);
            gender = Profile.GenderEnum.valueOf(selectedGender);
        });

        dob_editText.setOnClickListener(v -> {
            int day,year,month;
            if(!isDateSet) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                isDateSet = true;
            }
            else{
                year = Integer.parseInt(dob_editText.getText().toString().split("-")[2]);
                month = Integer.parseInt(dob_editText.getText().toString().split("-")[1]);
                day = Integer.parseInt(dob_editText.getText().toString().split("-")[0]);
                isDateSet = true;
            }
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view1, year1, month1, dayOfMonth) -> {
                month1 = month1 + 1;
                String user_DOB = dayOfMonth + "-" + month1 + "-" + year1;
                int currentYear = Year.now().getValue();
                if (year1 - currentYear <=10) {
                    isDateSet = false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Not Eligible!");
                    builder.setMessage("You are not eligible to use this service");
                    builder.setPositiveButton("Ok", (dialog, i) -> dialog.dismiss());
                    builder.show();
                    //dob_editText.setText("");
                } else {
                    dob_editText.setText(user_DOB);
                }
            }, year, month, day);
            datePickerDialog.show();
        });

        return view;
    }

    private void setProfile(){
        if(userProfile.getName()!=null){
            name_editText.setText(userProfile.getName());
        }
        if(userProfile.getPhone()!=null){
            phone_editText.setText(userProfile.getPhone());
        }
        if(userProfile.getDob()!=null){
            dob_editText.setText(userProfile.getDob());
            isDateSet = true;
        }
        if(userProfile.getEmail()!=null){
            email_id_editText.setText(userProfile.getEmail());
        }
        if(userProfile.getGender()!=null){
            gender = userProfile.getGender();
            gender_editText.setText(gender.toString());
        }
    }

    private void updateProfile(){
        String name = name_editText.getText().toString();
        String phone = phone_editText.getText().toString();
        String email = email_id_editText.getText().toString();
        String dob = dob_editText.getText().toString();
        Boolean isValid = true;
        if(!name.equals(userProfile.getUsername())){
            if(name.length()>=2){
                userProfile.setName(name);
            }
            else{
                isValid = false;
                name_editText.setError("Name should be of at least length 2");
            }
        }
        if(!phone.equals(userProfile.getPhone()) ){
            if(phone.length()==10){
                userProfile.setPhone(phone);
            }
            else{
                isValid = false;
                phone_editText.setError("Not Valid Phone Number");
            }
        }
        if(!email.equals(userProfile.getEmail())){
            if(email.contains("@")) {
                userProfile.setEmail(email);
            }
            else{
                isValid = false;
                email_id_editText.setError("Not Valid email id");
            }
        }
        if(!dob.equals(userProfile.getDob())){
            userProfile.setDob(dob);
        }
        if(!gender.equals(userProfile.getGender())){
            userProfile.setGender(gender);
        }

        if(isValid){
             updateProfileToDatabase(userProfile, new SettingFragment.ProfileCallback() {
                 @Override
                 public void onSuccessProfile(Profile profile) {
                     userProfile = profile;
                     Log.d("myTag", "profile "+profile.getName());
                     Log.d("myTag", "profile "+profile.getPhone());
                     Log.d("myTag", "profile "+profile.getGender());
                     Log.d("myTag", "profile "+profile.getDob());
                     Log.d("myTag", "success ");
                     setProfile();
                 }

                 @Override
                 public void onFailureProfile(int errorCode, String errorMessage) {
                     Toast.makeText(requireContext(), "Profile not updated, try again later", Toast.LENGTH_SHORT).show();
                     Log.d("myTag", "failure ");
                     Log.d("myTag", "code "+errorCode);
                     Log.d("myTag", "message "+errorMessage);
                     setProfile();
                 }
             });
        }
    }

    public void updateProfileToDatabase(Profile userProfile, SettingFragment.ProfileCallback callback) {
        DefaultApi api = new DefaultApi();
        LoadingDialog loadingDialog = new LoadingDialog(requireActivity());
        loadingDialog.startLoadingDialog();
        api.updateProfile(sessionId, userProfile, new Response.Listener<Profile>() {
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