package com.example.arpit.nurture;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    MyHelper helper = new MyHelper(this);
    View resetLayout,changeLayout;
    String email,pass,oldPass,newPass,resetPass;
    EditText e_email,e_pass;
    TextView reset,change;
    Button login;

    SharedPreferences sp;
    User currentUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        e_email = findViewById(R.id.editText);
        e_pass = findViewById(R.id.editText2);
        login = findViewById(R.id.login);
        reset = findViewById(R.id.reset);
        change = findViewById(R.id.change);

        LayoutInflater inflater = getLayoutInflater();
        resetLayout = inflater.inflate(R.layout.reset_pass_layout, null);
        changeLayout = inflater.inflate(R.layout.change_pass_layout, null);


        final AlertDialog.Builder changeDialog = new AlertDialog.Builder(UserActivity.this);
        changeDialog.setView(changeLayout);

        final AlertDialog.Builder resetDialog = new AlertDialog.Builder(UserActivity.this);
        resetDialog.setView(resetLayout);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = e_email.getText().toString();
                pass = e_pass.getText().toString();

                User temp = helper.readUser(email);

                if(temp == null){
                    Toast.makeText(UserActivity.this, "Account not found! Please register.", Toast.LENGTH_LONG).show();
                }
                else if(temp.getPass().equals(pass)){
                    sp = PreferenceManager.getDefaultSharedPreferences(UserActivity.this);
                    SharedPreferences.Editor edit = sp.edit();

                    edit.putString("email", temp.getEmail());
                    edit.putString("name", temp.getName());
                    edit.commit();

                    startActivity(new Intent(UserActivity.this, MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(UserActivity.this, "Incorrect Password!", Toast.LENGTH_LONG).show();
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPass = "12345678";
                final EditText e_email = resetLayout.findViewById(R.id.email);
                TextView title = resetLayout.findViewById(R.id.title);

                if(resetLayout.getParent() != null) {
                    ((ViewGroup) resetLayout.getParent()).removeView(resetLayout); // <- fix
                    title.setText("Reset Password");
                    resetDialog.setView(resetLayout);
                }

                resetDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        email = e_email.getText().toString();

                        if(helper.readUser(email) != null){

                            currentUser.setEmail(email);
                            currentUser.setPass(resetPass);

                            helper.updateUser(currentUser, email);

                            AlertDialog.Builder notif = new AlertDialog.Builder(UserActivity.this);
                            notif.setTitle("Reset Password");
                            notif.setMessage("Your password has been reset to " + resetPass +"\n" + "It is recommended to change your password immediately.");
                            notif.setCancelable(false);
                            notif.setPositiveButton("OK", null);
                            notif.show();
                        }
                        else{
                            Toast.makeText(UserActivity.this, "Account not found!", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                resetDialog.setNeutralButton("Cancel", null);
                resetDialog.setCancelable(false);
                resetDialog.show();
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText e_email = changeLayout.findViewById(R.id.email);
                final EditText e_oldPass = changeLayout.findViewById(R.id.old_pass);
                final EditText e_newPass = changeLayout.findViewById(R.id.new_pass);
                TextView title = changeLayout.findViewById(R.id.title);

                if(changeLayout.getParent() != null) {
                    ((ViewGroup) changeLayout.getParent()).removeView(changeLayout); // <- fix
                    title.setText("Chnage Password");
                    changeDialog.setView(changeLayout);
                }

                changeDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        email = e_email.getText().toString();
                        oldPass = e_oldPass.getText().toString();
                        newPass = e_newPass.getText().toString();

                        if(helper.readUser(email) != null){

                            if(helper.readUser(email).getPass().equals(oldPass)){

                                currentUser.setEmail(email);
                                currentUser.setPass(newPass);

                                helper.updateUser(currentUser, email);

                                AlertDialog.Builder notif = new AlertDialog.Builder(UserActivity.this);
                                notif.setTitle("Reset Password");
                                notif.setMessage("Your password has been reset to " + newPass);
                                notif.setCancelable(false);
                                notif.setPositiveButton("OK", null);
                                notif.show();
                            }
                            else{
                                Toast.makeText(UserActivity.this, "Incorrect old Password", Toast.LENGTH_LONG).show();
                            }

                        }
                        else{
                            Toast.makeText(UserActivity.this, "Account not found!", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                changeDialog.setNeutralButton("Cancel", null);
                changeDialog.setCancelable(false);
                changeDialog.show();
            }
        });

    }
}