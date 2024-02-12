package com.example.vendor_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
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
import com.example.vendor_app.Controller.RegisterVendorController;
import com.example.vendor_app.Controller.RegisterVendorController.Field;
import com.example.vendor_app.Widgets.EditTextWithDrawableClick;
import com.example.vendor_app.Widgets.LoadingDialog;


public class VendorRegistrationActivity extends AppCompatActivity {
    Context context;
    Boolean login_flag;
    CardView register_button;
    TextView backToLoginBtn;
    Spinner locationDropdown;
    LoadingDialog loadingDialog;
    String vendorLocation;
    SharedPreferences loginPreference;
    EditText usernameTextbox, mobileNumberTextbox, displayNameTextbox;
    EditTextWithDrawableClick passwordTextbox;
    boolean passwordHidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_registration);

        {
            context = this;
            login_flag = false;
            vendorLocation = "Mess Block";
            loadingDialog = new LoadingDialog(this);
            register_button = findViewById(R.id.register_button_CardView);
            backToLoginBtn = findViewById(R.id.backTologin_textView);
            usernameTextbox = findViewById(R.id.usernameTextbox);
            mobileNumberTextbox = findViewById(R.id.phone_number_editText);
            locationDropdown = findViewById(R.id.location_dropdown_spinner);
            passwordTextbox = findViewById(R.id.password_editText);
            displayNameTextbox = findViewById(R.id.displayNameTextbox);
            loginPreference = getSharedPreferences(AppConstants.PREF_LOGIN, MODE_PRIVATE);
        }

        setupDropdown();
        register_button.setOnClickListener(v -> {
            SharedPreferences.Editor editor = loginPreference.edit();
            String username = usernameTextbox.getText().toString().trim();
            String password = passwordTextbox.getText().toString().trim();
            String phone = mobileNumberTextbox.getText().toString().trim();
            String displayName = displayNameTextbox.getText().toString().trim();

            RegisterVendorController.register(
                    username,
                    password,
                    displayName,
                    vendorLocation,
                    phone,
                    "",
                    new RegisterVendorController.Callbacks(){
                        @Override
                        public void onInvalidField(Field field, String error){
                            EditText errorTextBox = null;
                            if(field != Field.FIELDS_LOCATION) {
                                switch (field) {
                                    case FIELDS_DISPLAYNAME:
                                        errorTextBox = displayNameTextbox;
                                        break;
                                    case FIELDS_USERNAME:
                                        errorTextBox = usernameTextbox;
                                        break;
                                    case FIELDS_PASSWORD:
                                        errorTextBox = passwordTextbox;
                                        break;
                                    case FIELDS_PHONE:
                                        errorTextBox = mobileNumberTextbox;
                                        break;
                                }
                                errorTextBox.setError(error);
                            }
                            else {
                                Log.d("INFO", "onInvalidField: Found invalid value in location");
                            }
                        }

                        @Override
                        public void onNetworkError(String error) {
                            Toast.makeText(context, "Network: " + error, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess(String result) {
                            editor.putBoolean(AppConstants.KEY_LOGIN_FLAG, true);
                            editor.putString(AppConstants.KEY_SESSION_ID, result);
                            editor.apply();

                            String message = "Happy Serving!";
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            View viewOrder = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog, null);
                            TextView messageTextView = viewOrder.findViewById(R.id.dialog_message_textView);
                            LottieAnimationView animationView = viewOrder.findViewById(R.id.lottieAnimation);

                            messageTextView.setText(message);
                            animationView.playAnimation();

                            builder.setView(viewOrder)
                                    .setTitle("Registration Successful")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(VendorRegistrationActivity.this, HomePageActivity.class);
                                            startActivity(intent);
                                            finish();
                                            dialog.dismiss();
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
        });

        backToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        passwordTextbox.setOnDrawableClickListener(new EditTextWithDrawableClick.OnDrawableClickListener() {
            @Override
            public void onClick(EditText editText) {
                togglePassword();
            }
        });
    }

    private void setupDropdown() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                RegisterVendorController.locations
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationDropdown.setAdapter(adapter);

        locationDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                vendorLocation = RegisterVendorController.locations[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void togglePassword() {
        passwordHidden = !passwordHidden;
        if (passwordHidden) {
            passwordTextbox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            passwordTextbox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }
}