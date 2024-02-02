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
                boolean login_flag = preferences.getBoolean(AppConstants.KEY_LOGIN_FLAG,false);
                String sessionId = preferences.getString(AppConstants.KEY_SESSION_ID,"");

                if(!login_flag) {
                    Intent intent = new Intent(MainActivity.this, UserLogin.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(MainActivity.this, MainPage.class);
                    intent.putExtra(AppConstants.KEY_SESSION_ID,sessionId);
                    startActivity(intent);
                }
                finish();
            }
        }, 1500);
    }
}