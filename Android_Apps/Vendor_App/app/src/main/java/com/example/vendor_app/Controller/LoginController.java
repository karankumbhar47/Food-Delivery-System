package com.example.vendor_app.Controller;

import android.util.Log;
import android.util.Pair;

import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.openapi.deliveryApp.model.LoginRequest;
import com.openapi.deliveryApp.api.DefaultApi;

public class LoginController {
    public static void login(String username, String password, LoginCallback callback){
        // Verify Username and Password locally to avoid unnecessary server calls
        if(username.length() < 4){
            callback.onInvalidUsername("At least 4 characters");
            return;
        }
        if(username.contains(" ")){
            callback.onInvalidUsername("Space character is not allowed");
            return;
        }
        if(password.length() < 8){
            callback.onInvalidPassword("Too Short");
            return;
        }

        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        DefaultApi api = new DefaultApi();
        api.loginVendor(request, new LoginResponseListener(callback), new LoginErrorListener(callback));
    }

    private static class LoginResponseListener implements Response.Listener<String>{
        LoginCallback callback = null;
        LoginResponseListener(LoginCallback _callback){
            callback = _callback;
        }
        @Override
        public void onResponse(String response) {
            callback.onSuccess(response.trim().substring(1, 41));
            return;
        }
    }

    private static class LoginErrorListener implements Response.ErrorListener {
        LoginCallback callback = null;
        LoginErrorListener(LoginCallback _callback){
            callback = _callback;
        }
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof TimeoutError){
                Log.d("LoginController::NetworkError", "onErrorResponse: Timeout");
                callback.onNetworkError("Network Timeout");
                return;
            }
            if (error instanceof NetworkError){
                Log.d("LoginController::NetworkError", "onErrorResponse: " + error.getMessage());
                callback.onNetworkError("Network error");
                return;
            }
            int statusCode = error.networkResponse.statusCode;
            String data  = new String(error.networkResponse.data);
            if(statusCode >= 500){
                Log.d("LoginController::ServerError", "onErrorResponse: Server error"+data);
                callback.onNetworkError("Server error");
                return;
            }
            if(statusCode == 403){
                Log.d("LoginController::ClientError", "onErrorResponse: Server error"+data);
                if(data.contains("Password")) {
                    callback.onInvalidPassword("Invalid Password");
                } else {
                    callback.onInvalidUsername("Invalid Username");
                }
                return;
            }
        }
    }

    public static interface LoginCallback {
        default void onInvalidUsername(String error){}
        default void onInvalidPassword(String error){}
        default void onSuccess(String result){}
        default void onNetworkError(String error){}
    }
}
