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
import com.example.swiggy_lite.Interface.RegistrationCallback;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.UserDetails;

import java.math.BigDecimal;


public class UserRegistration extends AppCompatActivity {

    DefaultApi api;
    Context context;
    RequestQueue queue;
    Boolean login_flag;
    TextView back_button;
    CardView create_button;
    Spinner genderDropdown;
    UserDetails userDetails;
    LoadingDialog loadingDialog;
    UserDetails.GenderEnum userGender;
    SharedPreferences loginPreference;
    EditText user_name_editText, mobile_number_editText, password_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        {
            context = this;
            login_flag = false;
            api = new DefaultApi();
            userDetails = new UserDetails();
            userGender = UserDetails.GenderEnum.Male;
            queue = Volley.newRequestQueue(this);
            loadingDialog = new LoadingDialog(this);
            create_button = findViewById(R.id.register_button_CardView);
            user_name_editText = findViewById(R.id.user_name_editText);
            mobile_number_editText = findViewById(R.id.phone_number_editText);
            genderDropdown = findViewById(R.id.gender_dropdown_spinner);
            password_editText = findViewById(R.id.password_editText);
            back_button = findViewById(R.id.backTologin_textView);
            loginPreference = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
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

                        String message = "Enjoy Your Favourite Dish \uD83D\uDE01";
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View viewOrder = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog, null);
                        TextView message_textView = viewOrder.findViewById(R.id.dialog_message_textView);
                        message_textView.setText(message);
                        LottieAnimationView animationView = viewOrder.findViewById(R.id.lottieAnimation);
                        animationView.playAnimation();

                        builder.setView(viewOrder)
                                .setTitle("User Registered Successfully")
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
                                    Toast.makeText(UserRegistration.this, "Duplicate "+errorMessage+". Please Change your "+errorMessage, Toast.LENGTH_SHORT).show();
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

    public void validateUser(RegistrationCallback callback){
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
                    userDetails.setPhone(mobile_number);
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
                            Log.d("myTag", "onResponse: response length "+response.trim().length());
                            Log.d("myTag", "onResponse: response "+response);
                            callback.onRegistrationSuccess(response.trim().substring(1, 41));
                            loadingDialog.dismissDialog();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try {
                                int statusCode = error.networkResponse.statusCode;
                                String data = new String(error.networkResponse.data);
                                callback.onRegistrationError(statusCode, data.trim().replace("\"",""));
                                loadingDialog.dismissDialog();
                            } catch (Exception e) {
                                callback.onRegistrationError(0,null);
                                loadingDialog.dismissDialog();
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(UserRegistration.this,"Please fill all correct info", Toast.LENGTH_SHORT).show();
                }
        }
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