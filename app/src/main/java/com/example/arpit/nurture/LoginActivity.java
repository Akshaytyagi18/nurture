package com.example.arpit.nurture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp =  PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        final SharedPreferences.Editor edit = sp.edit();

        Button b= findViewById(R.id.button3);
        Button b1= findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit.putString("admin_name", "abhimanyu");
                edit.putString("admin_pass", "1234");
                edit.commit();

                Intent i=new Intent(LoginActivity.this, AdminActivity.class);
                startActivity(i);
                finish();

            }
        });
        b1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(LoginActivity.this, UserActivity.class);
                startActivity(j);
                finish();
            }
        }));
    }
}
