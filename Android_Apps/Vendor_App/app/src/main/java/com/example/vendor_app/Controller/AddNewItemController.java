package com.example.vendor_app.Controller;

import android.util.Log;

import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.openapi.deliveryApp.api.DefaultApi;
import com.openapi.deliveryApp.model.VendorAddProductRequest;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddNewItemController {
    String fileId;

    public void addProduct(String sessionId, String name, File thumbnail, Float price,
                           int maxQuantity, String tags, FoodType veg, Callbacks callbacks){
        DefaultApi api = new DefaultApi();

        // Verify received information
        if(price <= 0){
            callbacks.onInvalidField(Field.FIELDS_PRICE, "Price should be at least Rs. 1");
            return;
        }
        if(maxQuantity <= 0){
            callbacks.onInvalidField(Field.FIELDS_MAX_QUANTITY, "At least 4 characters");
            return;
        }

        VendorAddProductRequest request = new VendorAddProductRequest();
        request.setName(name);
        request.setPrice(price);
        request.setMaxQuantity(maxQuantity);
        List<String> tagList = Arrays.asList((veg == FoodType.VEG ? "veg," : "non-veg," + tags).split(","));
        request.setTags(tagList);
        request.setImageUrls(Collections.emptyList());

        // Put image first, then make the request
        if(thumbnail != null) {
            FileController.putFile(sessionId, thumbnail, new FileController.Callbacks() {
                @Override
                public void onSuccess(String _fileId){
                    AddNewItemController.this.fileId = _fileId;
                    request.setThumbnail(_fileId);
                    api.vendorAddProduct(sessionId, request, new AddProductResponseListener(callbacks), new AddProductErrorListener(callbacks));
                }

                @Override
                public void onNetworkError(String error){
                    callbacks.onNetworkError(error);
                }

                @Override
                public void onInvalidSession(){
                    callbacks.onInvalidSession();
                };
            });
        }
        else {
            api.vendorAddProduct(sessionId, request, new AddProductResponseListener(callbacks), new AddProductErrorListener(callbacks));
            callbacks.onInvalidField(Field.FIELDS_IMAGE, "Please select an image");
        }
    }

    // Structures
    public enum FoodType {
        UNINITIALIZED,
        VEG,
        NON_VEG
    }

    public static enum Field {
        FIELDS_NAME,
        FIELDS_MAX_QUANTITY,
        FIELDS_PRICE,
        FIELDS_TAGS,
        FIELDS_IMAGE,
    }

    private class AddProductResponseListener implements Response.Listener<String>{
        Callbacks callback = null;
        AddProductResponseListener(Callbacks _callback){
            callback = _callback;
        }
        @Override
        public void onResponse(String response) {
            callback.onSuccess(response.trim().substring(1, 41), AddNewItemController.this.fileId);
            return;
        }
    }

    private static class AddProductErrorListener implements Response.ErrorListener {
        Callbacks callback = null;
        AddProductErrorListener(Callbacks _callback){
            callback = _callback;
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
            if(statusCode == 403){
                Log.i("RegisterVendorController::", "Invalid Session");
                callback.onInvalidSession();
            }
        }
    }

    public interface Callbacks {
        default void onInvalidField(Field field, String error){}
        default void onSuccess(String result, String imageId){}
        default void onNetworkError(String error){}
        default void onInvalidSession(){};
    }
}
