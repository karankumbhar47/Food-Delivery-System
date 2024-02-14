package com.example.vendor_app.Controller;

import android.util.Log;

import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.openapi.deliveryApp.api.DefaultApi;

import java.io.File;

public class FileController {
    public static void putFile(String sessionId, File image, Callbacks callbacks){

        DefaultApi api = new DefaultApi();
        api.putFile(sessionId, image, new PutFileResponseListener(callbacks), new PutFileErrorListener(callbacks));
    }

    // Structures
    public interface Callbacks {
        default void onSuccess(String fileId){}
        default void onNetworkError(String error){}
        default void onInvalidSession(){};
    }

    private static class PutFileResponseListener implements Response.Listener<String> {
        Callbacks callback = null;
        public PutFileResponseListener(Callbacks _callbacks) {
            callback = _callbacks;
        }

        @Override
        public void onResponse(String response) {
            callback.onSuccess(response.trim().substring(1,41));
        }
    }

    private static class PutFileErrorListener implements Response.ErrorListener {
        Callbacks callback = null;
        public PutFileErrorListener(Callbacks _callbacks) {
            callback = _callbacks;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof TimeoutError){
                Log.d("Controller", "Network Error: Timeout");
                callback.onNetworkError("Network Timeout");
                return;
            }
            if (error instanceof NetworkError){
                Log.d("Controller", "Network Error: " + error.getMessage());
                callback.onNetworkError("Network error");
                return;
            }
            int statusCode = error.networkResponse.statusCode;
            String data  = new String(error.networkResponse.data);
            if(statusCode >= 500){
                Log.d("Controller", "Server error "+data);
                callback.onNetworkError("Server error");
                return;
            }
            if(statusCode == 401){
                Log.i("Controller", "Invalid Session");
                callback.onInvalidSession();
            }
        }
    }
}