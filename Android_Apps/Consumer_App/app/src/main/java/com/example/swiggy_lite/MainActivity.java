package com.example.swiggy_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.swiggy_lite.Interface.RegistrationCallback;
import com.example.swiggy_lite.models.OrderItemAdvanced;
import com.example.swiggy_lite.models.OrderModel;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.LoginRequest;
import com.openapi.deliveryApp.model.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.MacSpi;


public class MainActivity extends AppCompatActivity {
    public String TAG = "myTag";
    public SharedPreferences prefCart, prefLogin;
    public SharedPreferences.Editor editorCart, editorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_main);

         prefLogin = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
         prefCart = getSharedPreferences(AppConstants.PREF_CART_INFO,MODE_PRIVATE);
         editorLogin = prefLogin.edit();
         editorCart = prefCart.edit();

        boolean login_flag = prefLogin.getBoolean(AppConstants.KEY_LOGIN_FLAG,false);

        if (!login_flag) {
            redirectToLogin();
        } else {
            attemptLogin(editorLogin, editorCart);
        }
/*
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(prefLogin.getString(AppConstants.KEY_PASSWORD, ""));
        loginRequest.setUsername(prefLogin.getString(AppConstants.KEY_USER_NAME, ""));


        else{
            sendRequest(loginRequest,MainActivity.this, new RegistrationCallback() {
                @Override
                public void onRegistrationSuccess(String sessionId) {
                    editorLogin.putString(AppConstants.KEY_SESSION_ID,sessionId);
                    editorLogin.apply();

                    editorCart.putBoolean(AppConstants.KEY_IS_DATA_CHANGED, false);
                    editorCart.apply();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, MasterActivity.class);
                            intent.putExtra(AppConstants.KEY_SESSION_ID,sessionId);
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);

                }

                @Override
                public void onRegistrationError(int errorCode, String errorMessage) {
                    Toast.makeText(MainActivity.this,"Inappropriate Login Credentials",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this,UserLogin.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1500);
                }
            });
//                    Intent intent = new Intent(MainActivity.this, MasterActivity.class);
//                    startActivity(intent);
        }*/

    }

    public void sendRequest(LoginRequest loginRequest, Activity activity , RegistrationCallback callback){
        DefaultApi api = new DefaultApi();
        api.login(loginRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onRegistrationSuccess(response.trim().substring(1, 41));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    int statusCode = error.networkResponse.statusCode;
                    String data = new String(error.networkResponse.data);
                    callback.onRegistrationError(statusCode, data.trim().substring(1,41));;
                } catch (Exception e) {
                    callback.onRegistrationError(0, null);
                }
            }
        });
    }
    private void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private void redirectToLogin() {
        startActivity(new Intent(MainActivity.this, UserLogin.class));
        finish();
    }
    private void attemptLogin(SharedPreferences.Editor editorLogin, SharedPreferences.Editor editorCart) {
        LoginRequest loginRequest = createLoginRequest();
        sendLoginRequest(loginRequest, new RegistrationCallback() {
            @Override
            public void onRegistrationSuccess(String sessionId) {
                handleSuccessfulLogin(editorLogin, editorCart, sessionId);
            }

            @Override
            public void onRegistrationError(int errorCode, String errorMessage) {
                handleFailedLogin();
            }
        });
    }
    private LoginRequest createLoginRequest() {
        SharedPreferences prefLogin = getSharedPreferences(AppConstants.PREF_LOGIN, MODE_PRIVATE);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(prefLogin.getString(AppConstants.KEY_USER_NAME, ""));
        loginRequest.setPassword(prefLogin.getString(AppConstants.KEY_PASSWORD, ""));
        return loginRequest;
    }

    private void sendLoginRequest(LoginRequest loginRequest, RegistrationCallback callback) {
        DefaultApi api = new DefaultApi();
        api.login(loginRequest, response -> callback.onRegistrationSuccess(response.trim().substring(1, 41)),
                error -> {
                    try {
                        int statusCode = error.networkResponse.statusCode;
                        String data = new String(error.networkResponse.data);
                        callback.onRegistrationError(statusCode, data.trim().substring(1, 41));
                    } catch (Exception e) {
                        callback.onRegistrationError(0, null);
                    }
                });
    }

    private void handleSuccessfulLogin(SharedPreferences.Editor editorLogin, SharedPreferences.Editor editorCart, String sessionId) {
        editorLogin.putString(AppConstants.KEY_SESSION_ID, sessionId).apply();
        editorCart.putBoolean(AppConstants.KEY_IS_DATA_CHANGED, true).apply();

        Log.d(TAG, "onStart: cart flag previous  "+prefCart.getBoolean(AppConstants.KEY_IS_DATA_CHANGED,true));
        if(prefCart.getBoolean(AppConstants.KEY_IS_DATA_CHANGED,true)){
            OrderModel orderModel = OrderModel.retrieveFromSharedPreferences(MainActivity.this);
            List<OrderItemAdvanced> orderItemList = (orderModel == null) ? new ArrayList<>() : (orderModel.getOrderItemAdvanced()!=null ? orderModel.getOrderItemAdvanced()  : new ArrayList<>());
            MasterActivity.itemCart = orderItemList.stream().collect(Collectors.toMap(OrderItemAdvanced::getItemId, Function.identity()));
            Log.d("myTag", "item cart lenght "+ MasterActivity.itemCart.size());
            editorCart.putBoolean(AppConstants.KEY_IS_DATA_CHANGED,false);
            editorCart.apply();
        }
        Log.d(TAG, "onStart: cart flag after "+prefCart.getBoolean(AppConstants.KEY_IS_DATA_CHANGED,true));
        new Handler().postDelayed(() -> redirectToMasterActivity(sessionId), 1500);
    }

    private void redirectToMasterActivity(String sessionId) {
        startActivity(new Intent(MainActivity.this, MasterActivity.class)
                .putExtra(AppConstants.KEY_SESSION_ID, sessionId));
        finish();
    }

    private void handleFailedLogin() {
        Toast.makeText(MainActivity.this, "Inappropriate Login Credentials", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(this::redirectToLogin, 1000);
    }

}