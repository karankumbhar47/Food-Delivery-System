package com.example.swiggy_lite;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.UserDetails;

import java.math.BigDecimal;


public class UserRegistration extends AppCompatActivity {
    public interface RegistrationCallback {
        void onRegistrationSuccess(String sessionId);
        void onRegistrationError(int errorCode, String errorMessage);
    }

    CardView create_button;
    EditText user_name_editText, mobile_number_editText, gender_id_editText, password_editText, confirm_password_editText;
    TextView back_button;
    DefaultApi api;
    RequestQueue queue;
    Spinner genderDropdown;
    UserDetails userDetails;
    UserDetails.GenderEnum userGender = UserDetails.GenderEnum.Male;
    Boolean login_flag = false;
    SharedPreferences loginPreference;
    LoadingDialog loadingDialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        {
            api = new DefaultApi();
            userDetails = new UserDetails();
            queue = Volley.newRequestQueue(this);
            create_button = findViewById(R.id.register_button_CardView);
            user_name_editText = findViewById(R.id.user_name_editText);
            mobile_number_editText = findViewById(R.id.phone_number_editText);
            genderDropdown = findViewById(R.id.gender_dropdown_spinner);
            password_editText = findViewById(R.id.password_editText);
            back_button = findViewById(R.id.backTologin_textView);
            loginPreference = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
            loadingDialog = new LoadingDialog(this);
            context = this;
        }

        setupDropdown();
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = loginPreference.edit();
                validateUser(new RegistrationCallback() {
                    @Override
                    public void onRegistrationSuccess(String response) {
                        editor.putBoolean(AppConstants.KEY_LOGIN_FLAG, true);
                        editor.putString(AppConstants.KEY_SESSION_ID, response);
                        editor.apply();

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View viewOrder = LayoutInflater.from(context).inflate(R.layout.order_success_dialog, null);
                        LottieAnimationView animationView = viewOrder.findViewById(R.id.lottieAnimation);
                        animationView.playAnimation();

                        builder.setView(viewOrder)
                                .setTitle("User Registered Successfully")
                                .setMessage("Enjoy Your Favourite Dish \uD83D\uDE01")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(UserRegistration.this, MainPage.class);
                                        startActivity(intent);
                                        finish();
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onRegistrationError(int errorCode, String errorMessage) {

                        if(errorCode==0)
                            Toast.makeText(UserRegistration.this, "Please Check your internet connection", Toast.LENGTH_SHORT).show();
                        else{
                            if(errorMessage!=null){
                                if(errorMessage.contains("Password")){
                                    Toast.makeText(UserRegistration.this, "Weak Password. Please Use Strong Password", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(UserRegistration.this, "Dublicate "+errorMessage+". Please Change your "+errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        editor.putBoolean(AppConstants.KEY_LOGIN_FLAG, false);
                        editor.apply();
                    }
                });
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public boolean validateUser(RegistrationCallback callback){
        String  user_name, mobile_number, password;

        user_name = user_name_editText.getText().toString().trim();
        mobile_number = mobile_number_editText.getText().toString().trim();
        password = password_editText.getText().toString().trim();

        int c = 1;
        boolean isComplete = true;
        switch (c){
            case 1:
                if(user_name.length() < 4 ){
                    user_name_editText.setError("Enter At least 4 char");
                    isComplete = false;
                }
                if(user_name.contains(" ")){
                    user_name_editText.setError("Remove spaces in user name");
                    isComplete = false;
                }
            case 2:
                if(mobile_number.length()!=10){
                    mobile_number_editText.setError("Enter 10 digit mobile number");
                    isComplete = false;
                }
            case 3:
                if(password.length()<6){
                    password_editText.setError("Enter password of at least length 6");
                    isComplete = false;
                }
                if(isComplete){
                    loadingDialog.startLoadingDialog();
                    Double phoneNumberValue = Double.parseDouble(mobile_number);
                    BigDecimal phoneBigDecimal = new BigDecimal(phoneNumberValue);

                    userDetails.setUsername(user_name);
                    userDetails.setPhone(phoneBigDecimal);
                    userDetails.setPassword(password);

                    userDetails.setEmail("");

                    userDetails.setName("");
                    userDetails.setGender(userGender);
                    userDetails.setDob("");

                    Log.d("myTag", "validateUser: user details "+userDetails.toString());

                    api.register(userDetails, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            login_flag = true;
                            callback.onRegistrationSuccess(response);
                            loadingDialog.dismissdialog();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try {
                                int statusCode = error.networkResponse.statusCode;
                                String data = new String(error.networkResponse.data);
                                callback.onRegistrationError(statusCode, data);
                                loadingDialog.dismissdialog();
                            } catch (Exception e) {
                                callback.onRegistrationError(0,null);
                                loadingDialog.dismissdialog();
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(UserRegistration.this,"Please fill all correct info", Toast.LENGTH_SHORT).show();
                }
        }
        return false;
    }

    private void setupDropdown() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"Male", "Female", "Others"}
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderDropdown.setAdapter(adapter);

        genderDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                userGender = position == 0 ? UserDetails.GenderEnum.Male :
                             position == 1 ? UserDetails.GenderEnum.Female :
                             position == 2 ? UserDetails.GenderEnum.Others :
                             UserDetails.GenderEnum.Male;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

}