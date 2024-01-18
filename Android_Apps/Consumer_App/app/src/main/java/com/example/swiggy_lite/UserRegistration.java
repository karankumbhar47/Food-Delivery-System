package com.example.swiggy_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegistration extends AppCompatActivity {
    TextView create_button, back_button;
    TextView full_name_textView, mobile_number_textView, email_id_textView, password_textView, confirm_password_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        create_button = findViewById(R.id.create_button);
        full_name_textView = findViewById(R.id.full_name_textView);
        mobile_number_textView = findViewById(R.id.mobile_numer_textView);
        email_id_textView = findViewById(R.id.email_id_textView);
        password_textView = findViewById(R.id.password_textView);
        confirm_password_textView = findViewById(R.id.confirm_password_textView);
        back_button = findViewById(R.id.back_button_textView);


        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateUser()) {
                    Intent intent = new Intent(UserRegistration.this, MainPage.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(UserRegistration.this,"Please fill all correct info", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean validateUser(){
        String  full_name, mobile_number, email_id, password, confirm_password;
        full_name = full_name_textView.getText().toString().trim();
        mobile_number = mobile_number_textView.getText().toString().trim();
        email_id = email_id_textView.getText().toString().trim();
        password = password_textView.getText().toString().trim();
        confirm_password = password_textView.getText().toString().trim();
        int c = 1;
        boolean isComplete = true;
        switch (c){
            case 1:
                if(full_name.length()==0){
                    full_name_textView.setError("Enter Your Name");
                    isComplete = false;
                }
            case 5:
                if(mobile_number.length()!=10){
                    mobile_number_textView.setError("Enter 10 digit mobile number");
                    isComplete = false;
                }
            case 2:
                if(email_id.length()==0 || !email_id.contains("@")){
                    email_id_textView.setError("Enter valid email id");
                    isComplete = false;
                }
            case 3:
                if(password.length()<6){
                    password_textView.setError("Enter password of at least length 6");
                    isComplete = false;
                }
            case 4:
                if(!confirm_password.equals(password)){
                    confirm_password_textView.setError("Password not matched");
                    isComplete = false;
                }
                if(isComplete){
                    SharedPreferences loginPreference = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
                    SharedPreferences userListPreference = getSharedPreferences(AppConstants.PREF_USER_LIST,MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginPreference.edit();
                    SharedPreferences.Editor editorUserList = userListPreference.edit();
                    Log.d("myTag", "validateUser: pref "+mobile_number+" "+password);
                    editorUserList.putString(full_name,password);
                    editor.putBoolean(AppConstants.KEY_LOGIN_FLAG, true);
                    return true;
                }
        }
        return false;
    }
}