package com.example.delivery_app;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.DeliveryPersonDetails;


public class UserRegistration extends AppCompatActivity {

    DefaultApi api;
    Context context;
    RequestQueue queue;
    Boolean login_flag;
    TextView back_button;
    CardView create_button;

    DeliveryPersonDetails userDetails;
    LoadingDialog loadingDialog;
    SharedPreferences loginPreference;
    EditText user_name_editText, mobile_number_editText, password_editText, email_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        {
            context = this;
            login_flag = false;
            api = new DefaultApi();
            userDetails = new DeliveryPersonDetails();
            queue = Volley.newRequestQueue(this);
            loadingDialog = new LoadingDialog(this);
            create_button = findViewById(R.id.register_button_CardView);
            user_name_editText = findViewById(R.id.user_name_editText);
            mobile_number_editText = findViewById(R.id.phone_number_editText);
            password_editText = findViewById(R.id.password_editText);
            email_editText = findViewById(R.id.email_editText);
            back_button = findViewById(R.id.backTologin_textView);
            loginPreference = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
        }


    }

}