package com.example.delivery_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserLogin extends AppCompatActivity {
    TextView login_button, register_button, user_name_textView, password_textView;
    CheckBox show_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_login);
        CardView login_button = findViewById(R.id.login_button_CardView);
        TextView register_button = findViewById(R.id.goToRegister_textView);
        EditText user_name_textView = findViewById(R.id.user_name_editText);
        EditText password_textView = findViewById(R.id.password_editText);
        //show_password = findViewById(R.id.show_password_checkBox);
        password_textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        ImageView appLogo = findViewById(R.id.app_logo_imageView);
        appLogo.setOnClickListener(v -> {
            Intent i = new Intent(UserLogin.this, MainPage.class);
            startActivity(i);
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = user_name_textView.getText().toString().trim();
                String password = password_textView.getText().toString().trim();
                if(user_name.length()>=3){
                    if(password.length()>=6){
                        loginUser(user_name, password);
                    }
                    else{
                        if(password.length()==0){
                            password_textView.setError("Please Enter Valid Password");
                        }
                        else{
                            password_textView.setError("Password can't be less than 6 characters");
                        }
                    }
                }
                else{
                    if(!(user_name.length()==10)){
                        user_name_textView.setError("Please Enter Valid User Name");
                    }
                    else{
                        user_name_textView.setError("Username can't be less than 3 characters");
                    }
                }
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this, UserRegistration.class);
                startActivity(intent);
            }
        });

        /*show_password.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                password_textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                password_textView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });*/
    }

    public void loginUser(String user_name, String password){
        SharedPreferences userListPreferences = getSharedPreferences(AppConstants.PREF_USER_LIST,MODE_PRIVATE);

        SharedPreferences loginPreferences = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();

        String real_password = userListPreferences.getString(user_name,"");
        Log.d("myTag", "validateUser: pref "+user_name+" "+password+" "+real_password);
        if(real_password.equals(password)) {
            Intent intent = new Intent(UserLogin.this, MainPage.class);
            editor.putBoolean(AppConstants.KEY_LOGIN_FLAG,true);
            startActivity(intent);
        }
        else if(real_password.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(UserLogin.this);
            builder.setTitle("User Not Found");
            builder.setMessage("You haven't registered, please create your account first");
            builder.setPositiveButton("Create", (dialog, i) -> createUserActivity());
            builder.setNegativeButton("Cancel",(dialog, i) -> dialog.dismiss());
            builder.show();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(UserLogin.this);
            builder.setTitle("Wrong password");
            builder.setMessage("Please Enter correct password");
            builder.setNegativeButton("Cancel",(dialog, i) -> dialog.dismiss());
            builder.show();
        }
    }

    public void createUserActivity(){
        Intent intent = new Intent(UserLogin.this, UserRegistration.class);
        startActivity(intent);
    }
}