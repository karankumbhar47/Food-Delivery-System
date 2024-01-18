package com.example.swiggy_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences(AppConstants.PREF_LOGIN,MODE_PRIVATE);
                boolean login_flag = preferences.getBoolean(AppConstants.KEY_LOGIN_FLAG,true);

                if(!login_flag) {
                    Intent i = new Intent(MainActivity.this, UserLogin.class);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(MainActivity.this, MainPage.class);
                    startActivity(i);
                }
                finish();
            }
        }, 2000);

    }
}