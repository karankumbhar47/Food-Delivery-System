package com.example.delivery_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import deliveryApp.model.LoginRequest;

public class UserLogin extends AppCompatActivity {
    TextView  register_button;
    CardView login_button;
    CheckBox show_password;
    EditText user_name_textView;
    EditTextWithDrawableClick password_textView;
    String sessionId;
    ImageView appLogo;
    SharedPreferences loginPreference;
    Context context;
    boolean isChecked = false;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        {
            login_button = findViewById(R.id.login_button_CardView);
            register_button = findViewById(R.id.goToRegister_textView);
            user_name_textView = findViewById(R.id.user_name_editText);
            password_textView = findViewById(R.id.password_editText);
            //show_password = findViewById(R.id.show_password_checkBox);
            password_textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            appLogo = findViewById(R.id.app_logo_imageView);
            loginPreference = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
            context = this;
        }

        appLogo.setOnClickListener(v -> {
            Intent i = new Intent(UserLogin.this, MasterActivity.class);
            startActivity(i);
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = loginPreference.edit();
                validateUser(UserLogin.this, new RegistrationCallback() {
                    @Override
                    public void onRegistrationSuccess(String sessionId) {
                        editor.putBoolean(AppConstants.KEY_LOGIN_FLAG, true);
                        editor.putString(AppConstants.KEY_SESSION_ID, sessionId);
                        editor.putString(AppConstants.KEY_PASSWORD,password_textView.getText().toString().trim());
                        editor.putString(AppConstants.KEY_USER_NAME,user_name_textView.getText().toString().trim());
                        editor.apply();
// I don't get it.
                        String message = "Enjoy Your Favourite Dish \uD83D\uDE01";
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View viewOrder = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog, null);
                        TextView messageTextView = viewOrder.findViewById(R.id.dialog_message_textView);
                        LottieAnimationView animationView = viewOrder.findViewById(R.id.lottieAnimation);

                        messageTextView.setText(message);
                        animationView.playAnimation();

                        builder.setView(viewOrder)
                                .setTitle("User Login Successfully")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(UserLogin.this, MasterActivity.class);
                                        startActivity(intent);
                                        finish();
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onRegistrationError(int errorCode, String errorMessage) {
                        if(errorCode==0)
                            Toast.makeText(UserLogin.this, "Please Check your internet connection", Toast.LENGTH_SHORT).show();
                        else{
                            if(errorMessage!=null){
                                if(errorMessage.contains("Password")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UserLogin.this);
                                    builder.setTitle("Wrong password");
                                    builder.setMessage("Please Enter correct password");
                                    builder.setNegativeButton("Cancel", (dialog, i) -> dialog.dismiss());
                                    builder.show();
                                }
                                else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(UserLogin.this);
                                    builder.setTitle("User Not Found");
                                    builder.setMessage("You haven't registered, please create your account first");
                                    builder.setPositiveButton("Create", (dialog, i) -> createUserActivity());
                                    builder.setNegativeButton("Cancel", (dialog, i) -> dialog.dismiss());
                                    builder.show();
                                }
                            }
                        }
                        editor.putBoolean(AppConstants.KEY_LOGIN_FLAG, false);
                        editor.apply();
                    }
                });
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this, UserRegistration.class);
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
        Intent intent = new Intent(UserLogin.this, UserRegistration.class);
        startActivity(intent);
    }

    public void validateUser(Activity activity, RegistrationCallback callback) {
        String user_name = user_name_textView.getText().toString().trim();
        String password = password_textView.getText().toString().trim();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(user_name);
        loginRequest.setPassword(password);

        if (user_name.length() >= 4) {
            if (password.length() >= 8) {
                sendRequest(loginRequest,activity,callback);
            } else {
                if (password.length() == 0) {
                    password_textView.setError("Please Enter Valid Password");
                } else {
                    password_textView.setError("Password can't be less than 6 characters");
                }
            }
        } else {
            user_name_textView.setError("Username can't be less than 4 characters");
        }
    }
// I don't get it
    public static void sendRequest(LoginRequest loginRequest, Activity activity ,RegistrationCallback callback){
        LoadingDialog loadingDialog = new LoadingDialog(activity);
        DefaultApi api = new DefaultApi();
        Log.d("myTag", "api "+api.getBasePath());
        loadingDialog.startLoadingDialog();
        api.login(loginRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onRegistrationSuccess(response.trim().substring(1, 41));
                loadingDialog.dismissDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    int statusCode = error.networkResponse.statusCode;
                    String data = new String(error.networkResponse.data);
                    callback.onRegistrationError(statusCode, data.trim().substring(1,41));;
                    loadingDialog.dismissDialog();
                } catch (Exception e) {
                    callback.onRegistrationError(0, null);
                    loadingDialog.dismissDialog();
                }
            }
        });
    }
}