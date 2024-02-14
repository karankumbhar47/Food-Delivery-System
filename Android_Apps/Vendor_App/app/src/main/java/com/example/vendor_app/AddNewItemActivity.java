package com.example.vendor_app;

import static com.example.vendor_app.Controller.AddNewItemController.Field.FIELDS_IMAGE;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vendor_app.Controller.AddNewItemController;
import com.example.vendor_app.Controller.AddNewItemController.Callbacks;
import com.example.vendor_app.Controller.AddNewItemController.FoodType;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

public class AddNewItemActivity extends AppCompatActivity {

    // UI elements
    private CardView thumbnailImageCard;
    private ImageView thumbnailImageView;
    private CardView vegCategoryBtn, nonVegCategoryBtn;
    private TextInputEditText productNameTextBox, maxQuantityTextBox, priceTextBox, tagsTextBox;
    private CardView saveBtn;
    private Context context;

    // State variables
    private FoodType isVeg = FoodType.UNINITIALIZED;
    private File thumbnailImage;

    // Utility variables
    // Registers a photo picker activity launcher in single-select mode.
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri != null) {
                    Log.i("PhotoPicker", "Selected URI: " + uri);
                    setThumbnailImage(uri);
                } else {
                    Log.i("PhotoPicker", "No media selected");
                }
            });

    // UI Functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        {
            context = this;
            thumbnailImageCard = findViewById(R.id.thumbnailImageCard);
            thumbnailImageView = findViewById(R.id.thumbnailImageView);
            vegCategoryBtn = findViewById(R.id.veg_btn);
            nonVegCategoryBtn = findViewById(R.id.non_veg_btn);
            productNameTextBox = findViewById(R.id.productNameTextBox);
            maxQuantityTextBox = findViewById(R.id.maxQuantityTextBox);
            priceTextBox = findViewById(R.id.priceTextBox);
            tagsTextBox = findViewById(R.id.tagsTextBox);
            saveBtn = findViewById(R.id.saveBtn);
        }

        setIsVeg(FoodType.VEG);

        nonVegCategoryBtn.setOnClickListener(v -> {
            setIsVeg(FoodType.NON_VEG);});
        vegCategoryBtn.setOnClickListener(v -> {
            setIsVeg(FoodType.VEG);});
        setIsVeg(FoodType.VEG);
        thumbnailImageCard.setOnClickListener(v -> {
            pickThumbnail();});

        saveBtn.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
            boolean loginFlag = preferences.getBoolean(AppConstants.KEY_LOGIN_FLAG,false);
            String sessionId = preferences.getString(AppConstants.KEY_SESSION_ID,"");

            if (!loginFlag){
                clearLoginInfo();
            }

            String name = productNameTextBox.getText().toString().trim();
            int maxQuantity;
            Float price;
            String tags = tagsTextBox.getText().toString().trim();

            try {
                maxQuantity = Integer.parseInt(maxQuantityTextBox.getText().toString().trim());
            }
            catch (NumberFormatException e){
                maxQuantityTextBox.setError("Invalid value");
                return;
            };
            try {
                price = Float.valueOf(priceTextBox.getText().toString().trim());
            }
            catch (NumberFormatException e){
                priceTextBox.setError("Invalid value");
                return;
            };

            new AddNewItemController().addProduct(sessionId, name, thumbnailImage, price, maxQuantity, tags, isVeg,
                new Callbacks(){
                    @Override
                    public void onInvalidField(AddNewItemController.Field field, String error){
                        EditText errorTextBox = null;
                        switch (field) {
                            case FIELDS_NAME:
                                errorTextBox = productNameTextBox;
                                break;
                            case FIELDS_PRICE:
                                errorTextBox = priceTextBox;
                                break;
                            case FIELDS_TAGS:
                                errorTextBox = tagsTextBox;
                                break;
                            case FIELDS_MAX_QUANTITY:
                                errorTextBox = maxQuantityTextBox;
                                break;
                        }
                        if (field != FIELDS_IMAGE) {
                            errorTextBox.setError(error);
                        }
                        else{
                            Toast.makeText(context, "Please select image", Toast.LENGTH_SHORT).show();
                            pickThumbnail();
                        }
                        Log.i("Activity", "Invalid field, " + error);
                    }

                    @Override
                    public void onSuccess(String result, String imageId){
                        Log.i("Activity", "Success");
                        Log.i("Activity", "Product ID is " + result);
                        Log.i("Activity", "Image ID is " + imageId);
                        Toast.makeText(context, "Product registered", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNetworkError(String error){
                        Log.i("Activity", "Network Error");
                        Toast.makeText(context, "Network: " + error, Toast.LENGTH_SHORT).show();
                    }
                    public void onInvalidSession(){
                        clearLoginInfo();
                    }
                }
            );
        });
    }

    private void clearLoginInfo() {
        SharedPreferences preferences = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Log.e("DEBUG", "AddNewItem: Missing session ID, cleaning data");
        editor.putBoolean(AppConstants.KEY_LOGIN_FLAG, false);
        startActivity(new Intent(AddNewItemActivity.this, VendorLoginActivity.class));
        finish();
    }

    private void setIsVeg(FoodType type){
        if(type == isVeg){
            return;
        }
        Resources res = getResources();
        this.isVeg = type;
        Log.i("Activity", "setVeg: Food type changed");
        if (type == FoodType.VEG){
            vegCategoryBtn.setCardBackgroundColor(res.getColor(R.color.primary));
            nonVegCategoryBtn.setCardBackgroundColor(res.getColor(R.color.background));
        }
        else{
            nonVegCategoryBtn.setCardBackgroundColor(res.getColor(R.color.primary));
            vegCategoryBtn.setCardBackgroundColor(res.getColor(R.color.background));
        }
    }

    void pickThumbnail() {
        // Launch the photo picker and let the user choose only images.
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }

    private void setThumbnailImage(Uri thumbnailImageURI) {
        this.thumbnailImage = new File(String.valueOf(thumbnailImageURI));
        thumbnailImageView.setImageURI(thumbnailImageURI);
    }
}