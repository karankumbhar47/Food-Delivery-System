package com.example.swiggy_lite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegistration extends AppCompatActivity {
    CardView create_button;
    EditText full_name_editText, mobile_number_editText, email_id_editText, password_editText, confirm_password_editText;
    TextView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        create_button = findViewById(R.id.register_button_CardView);
        full_name_editText = findViewById(R.id.user_name_editText);
        mobile_number_editText = findViewById(R.id.phone_number_editText);
        email_id_editText = findViewById(R.id.email_editText);
        password_editText = findViewById(R.id.password_editText);
        //confirm_password_editText = findViewById(R.id.confirm_password_editText);
        back_button = findViewById(R.id.backTologin_textView);


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
        full_name = full_name_editText.getText().toString().trim();
        mobile_number = mobile_number_editText.getText().toString().trim();
        email_id = email_id_editText.getText().toString().trim();
        password = password_editText.getText().toString().trim();
        confirm_password = password_editText.getText().toString().trim();
        int c = 1;
        boolean isComplete = true;
        switch (c){
            case 1:
                if(full_name.length()==0){
                    full_name_editText.setError("Enter Your Name");
                    isComplete = false;
                }
            case 5:
                if(mobile_number.length()!=10){
                    mobile_number_editText.setError("Enter 10 digit mobile number");
                    isComplete = false;
                }
            case 2:
                if(email_id.length()==0 || !email_id.contains("@")){
                    email_id_editText.setError("Enter valid email id");
                    isComplete = false;
                }
            case 3:
                if(password.length()<6){
                    password_editText.setError("Enter password of at least length 6");
                    isComplete = false;
                }
            case 4:
                if(!confirm_password.equals(password)){
                    confirm_password_editText.setError("Password not matched");
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