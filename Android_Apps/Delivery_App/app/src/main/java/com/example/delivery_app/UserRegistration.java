package com.example.delivery_app;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.DeliveryPersonDetails;


public class UserRegistration extends AppCompatActivity {

    public interface registerCallBack{
        void onSuccess(String sessionId);
        void onFailure(int errorCode, String errorMessage);
    }
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

        DeliveryPersonDetails deliveryPersonDetails = new DeliveryPersonDetails();
        deliveryPersonDetails.setName("Vinod");
        deliveryPersonDetails.setEmail("vinod2024@gmail.com");
        deliveryPersonDetails.setPassword("123456@Abc");
        deliveryPersonDetails.setPhone("9876543210");
        deliveryPersonDetails.setUsername("vinod24");

        create_button.setOnClickListener(v ->{
            sendRequest(deliveryPersonDetails, new registerCallBack() {

                SharedPreferences.Editor editor = loginPreference.edit();
                @Override
                public void onSuccess(String sessionId) {

                    editor.putBoolean(AppConstants.KEY_LOGIN_FLAG, true);
                    editor.putString(AppConstants.KEY_SESSION_ID, sessionId);
                    editor.apply();

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("User Registration successful")
                            .setMessage("You have been successfully registered")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(UserRegistration.this, MasterActivity.class);
                                    startActivity(intent);
                                    dialog.dismiss(); // Close the dialog
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

                @Override
                public void onFailure(int errorCode, String errorMessage) {

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
        });




    }

    private void sendRequest(DeliveryPersonDetails deliveryPersonDetails,registerCallBack callBack){

        loadingDialog.startLoadingDialog();
        api.registerDeliveryPerson(deliveryPersonDetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response.trim().substring(1,41));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    int statusCode = error.networkResponse.statusCode;
                    String data = new String(error.networkResponse.data);
                    callBack.onFailure(statusCode, data.trim().replace("\"",""));
                    loadingDialog.dismissDialog();
                } catch (Exception e) {
                    callBack.onFailure(0,null);
                    loadingDialog.dismissDialog();
                }

            }
        });
    }
}