package com.example.vendor_app.Controller;

import android.util.Log;

import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.VendorDetails;

public class RegisterVendorController {
    public static String[] locations = new String[]{"", "Mess Block", "Academic Block", "BH1", "BH2", "GH1", "Delta Block"};

    public static void register(String username, String password, String displayName,
                                String location, String phone, String email, Callbacks callbacks){
        // Verify Username and Password locally to avoid unnecessary server calls
        if(username.length() < 4){
            callbacks.onInvalidField(Field.FIELDS_USERNAME, "At least 4 characters");
            return;
        }
        if(username.contains(" ")){
            callbacks.onInvalidField(Field.FIELDS_USERNAME, "Space character is not allowed");
            return;
        }
        if(password.length() < 8){
            callbacks.onInvalidField(Field.FIELDS_PASSWORD, "Too Short");
            return;
        }

        VendorDetails request = new VendorDetails();
        request.setUsername(username);
        request.setPassword(password);
        request.setName(displayName);
        request.setLocation(location);
        request.setPhone(phone);
        request.setEmail(email);
        DefaultApi api = new DefaultApi();
        api.registerVendor(request, new RegisterResponseListener(callbacks), new RegisterErrorListener(callbacks));
    }

    private static class RegisterResponseListener implements Response.Listener<String>{
        Callbacks callback = null;
        RegisterResponseListener(Callbacks _callback){
            callback = _callback;
        }
        @Override
        public void onResponse(String response) {
            callback.onSuccess(response.trim().substring(1, 41));
            return;
        }
    }

    private static class RegisterErrorListener implements Response.ErrorListener {
        Callbacks callback = null;
        RegisterErrorListener(Callbacks _callback){
            callback = _callback;
        }
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof TimeoutError){
                Log.d("RegisterVendorController::NetworkError", "onErrorResponse: Timeout");
                callback.onNetworkError("Network Timeout");
                return;
            }
            if (error instanceof NetworkError){
                Log.d("RegisterVendorController::NetworkError", "onErrorResponse: " + error.getMessage());
                callback.onNetworkError("Network error");
                return;
            }
            int statusCode = error.networkResponse.statusCode;
            String data  = new String(error.networkResponse.data);
            if(statusCode >= 500){
                Log.d("RegisterVendorController::ServerError", "onErrorResponse: Server error"+data);
                callback.onNetworkError("Server error");
                return;
            }
            if(statusCode == 409){
                Log.d("RegisterVendorController::ClientError", "onErrorResponse: Server error "+data);
                if(data.contains("Password")) {
                    callback.onInvalidField(Field.FIELDS_PASSWORD, "Invalid Password");
                }
                else if (data.contains("Username")) {
                    callback.onInvalidField(Field.FIELDS_USERNAME, "Invalid Username");
                }
                else if (data.contains("Phone")) {
                    callback.onInvalidField(Field.FIELDS_PHONE, "Invalid Phone Number");
                }
                else{
                    Log.e("Server::RegisterVendorController", "Unknown error '"+data+"'");
                }
            }
        }
    }

    public static enum Field {
        FIELDS_DISPLAYNAME,
        FIELDS_USERNAME,
        FIELDS_PASSWORD,
        FIELDS_LOCATION,
        FIELDS_PHONE,
    }

    public interface Callbacks {
        default void onInvalidField(Field field, String error){}
        default void onSuccess(String result){}
        default void onNetworkError(String error){}
    }
}
