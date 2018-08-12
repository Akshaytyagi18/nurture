package com.example.arpit.nurture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String pass;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences settings=getSharedPreferences("preff",0);
        pass =" ";

        new Handler().postDelayed(new Runnable() {

                                      @Override
                                      public void run() {

                                          Intent i =new Intent(Splash.this, SignUp.class);
                                          startActivity(i);
                                          finish();
                                      }
                                  }
                ,3000);
    }
}