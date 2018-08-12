package com.example.arpit.nurture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    String adminName,adminPass;
    EditText e_name,e_pass;
    Button login;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        e_name = findViewById(R.id.editText);
        e_pass = findViewById(R.id.editText2);
        login = findViewById(R.id.login);
        sp = PreferenceManager.getDefaultSharedPreferences(AdminActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminName = e_name.getText().toString();
                adminPass = e_pass.getText().toString();

                if(!adminName.equals(sp.getString("admin_name", "null"))){
                    Toast.makeText(AdminActivity.this, "Incorrect Username!", Toast.LENGTH_LONG).show();
                } else {
                    if (!adminPass.equals(sp.getString("admin_pass", "null"))) {
                        Toast.makeText(AdminActivity.this, "Incorrect Password!", Toast.LENGTH_LONG).show();
                    } else {
                        startActivity(new Intent(AdminActivity.this, AdminHompage.class));
                    }
                }
            }
        });

    }
}
