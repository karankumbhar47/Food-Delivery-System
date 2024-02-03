package com.example.vendor_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.vendor_app.Controller.LoginController;
import com.example.vendor_app.Widgets.EditTextWithDrawableClick;
import com.example.vendor_app.Widgets.LoadingDialog;
import com.openapi.deliveryApp.api.DefaultApi;

public class VendorLoginActivity extends AppCompatActivity {
    TextView register_button;
    CardView login_button;
    EditText username_textView;
    EditTextWithDrawableClick password_textView;
    String sessionId;
    DefaultApi api;
    LoadingDialog loadingDialog;
    ImageView appLogo;
    SharedPreferences loginPreference;
    Context context;
    boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);

        {
            login_button = findViewById(R.id.login_button_CardView);
            register_button = findViewById(R.id.goToRegister_textView);
            username_textView = findViewById(R.id.user_name_editText);
            password_textView = findViewById(R.id.password_editText);
            password_textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            appLogo = findViewById(R.id.app_logo_imageView);
            loadingDialog = new LoadingDialog(this);
            loginPreference = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
            api = new DefaultApi();
            context = this;
        }

        appLogo.setOnClickListener(v -> {
            Intent i = new Intent(VendorLoginActivity.this, HomePageActivity.class);
            startActivity(i);
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = loginPreference.edit();
                String username = username_textView.getText().toString().trim();
                String password = password_textView.getText().toString().trim();

                LoginController.login(username, password,
                    new LoginController.LoginCallback() {
                        @Override
                        public void onInvalidUsername(String error) {
                            Toast.makeText(context, "Username: " + error, Toast.LENGTH_SHORT).show();
                            username_textView.setError(error);
                        }

                        @Override
                        public void onInvalidPassword(String error) {
                            Toast.makeText(context, "Password: " + error, Toast.LENGTH_SHORT).show();
                            password_textView.setError(error);
                        }

                        @Override
                        public void onNetworkError(String error) {
                            Toast.makeText(context, "Network: " + error, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess(String result) {
                            editor.putBoolean(AppConstants.KEY_LOGIN_FLAG, true);
                            editor.putString(AppConstants.KEY_SESSION_ID, sessionId);
                            editor.apply();

                            String message = "Happy Serving!";
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            View viewOrder = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog, null);
                            TextView messageTextView = viewOrder.findViewById(R.id.dialog_message_textView);
                            LottieAnimationView animationView = viewOrder.findViewById(R.id.lottieAnimation);

                            messageTextView.setText(message);
                            animationView.playAnimation();

                            builder.setView(viewOrder)
                                    .setTitle("Login Successfully")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(VendorLoginActivity.this, HomePageActivity.class);
                                            startActivity(intent);
                                            finish();
                                            dialog.dismiss();
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                );
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorLoginActivity.this, VendorRegistrationActivity.class);
                startActivity(intent);
            }
        });

        password_textView.setOnDrawableClickListener(new EditTextWithDrawableClick.OnDrawableClickListener() {
            @Override
            public void onClick(EditText editText) {
                togglePassword();
            }
        });
    }

    private void togglePassword() {
        isChecked = ! isChecked;
        if (isChecked) {
            password_textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            password_textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void createUserActivity(){
        Intent intent = new Intent(VendorLoginActivity.this, VendorRegistrationActivity.class);
        startActivity(intent);
    }
}