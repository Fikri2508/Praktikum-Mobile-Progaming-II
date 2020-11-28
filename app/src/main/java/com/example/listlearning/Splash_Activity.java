package com.example.listlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Handler handler = new Handler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SharedPrefManager sharedPrefManager = new SharedPrefManager(this);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPrefManager.getIsLogin()){
                    Intent i = new Intent(Splash_Activity.this, Profile_Activity.class);
                    finishAffinity();
                    startActivity(i);
                }else{
                    Intent i = new Intent(Splash_Activity.this, MainActivity.class);
                    finishAffinity();
                    startActivity(i);
                }
            }
        }, 3000);
    }
}