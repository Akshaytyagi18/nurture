package com.example.arpit.nurture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp extends AppCompatActivity{

    MyHelper helper = new MyHelper(this); //database object;
    User temp = new User();

    private EditText e_name,e_password,e_cpassword;
    private String name,password,cpassword;
    Button reg;
    CheckBox checkBox;
    boolean check=true;//create a boolean variable check
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Spinner spinner=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence>adapter =ArrayAdapter.createFromResource(this,R.array.SelectCountry,android.R.layout.simple_spinner_item);//Initializing array adapter object, data is allocated in astring.xml.in  array named sclectcountry, layout for each item in spinner is simple_spinner_item
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//  now we specify layout for each dropdown list for spinner,ie simple spinner dropdown item
        spinner.setAdapter(adapter);// add this adapter into the spinner
        spinner.setPrompt("Select your Country");// sets hint
        //setting a listner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SignUp.this,"Selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SignUp.this, "Select as country", Toast.LENGTH_SHORT).show();
            }
        });

        e_name=findViewById(R.id.editText10);
        e_password=findViewById(R.id.editText8);
        e_cpassword=findViewById(R.id.editText9);
        checkBox=findViewById(R.id.checkBox);
        reg=findViewById(R.id.button2);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked==true){
                            check=true;
                        }
                        else{
                            check=false;
                        }
                    }
                });

                register();//called when button is clicked
            }
        });


    }
    public void register(){
        initialize();// initialize input to string variables
        User existingUser = helper.readUser(name);

        if (!validate()){
            Toast.makeText(SignUp.this, "Signup has failed", Toast.LENGTH_SHORT).show();
        }
        else if(existingUser != null)
        {
            Toast.makeText(SignUp.this, "Username already exists", Toast.LENGTH_SHORT).show();
        }
        else{
            onSignUpSuccess();
        }
    }

    public void onSignUpSuccess(){
        //To do what will go after validation success
        Toast.makeText(SignUp.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
        temp.setName(null);
        temp.setPass(password);
        temp.setEmail(name);
        temp.setProfilePicture(null);
        temp.setMobNo(null);
        temp.setUploadedImage(null);

        helper.addUser(temp);
//        userCount++;
//        edit.putInt("user_count", userCount);

//        edit.commit();

        startActivity(new Intent(SignUp.this, LoginActivity.class));
        finish();
    }

    public boolean validate(){
        boolean valid =true;
        if(name.isEmpty()||name.length()>32){
            e_name.setError("Enter valid name");
            valid=false;
        }
        if(password.length()<6){
            e_password.setError("Enter pasword of length more than 6");
            valid=false;
        }
        if(cpassword.equals(password)){
            valid=true;
        }
        else{
            e_cpassword.setError("Entered password doesnot match confirmed one");
            valid=false;
        }
        if (check==false){
            Toast.makeText(SignUp.this, "Accept all T&C to Register", Toast.LENGTH_SHORT).show();
            valid =false;
        }
        return valid;

    }

    public void initialize(){//to convert input into string
        name= e_name.getText().toString().trim();
        password =e_password.getText().toString().trim();
        cpassword =e_cpassword.getText().toString().trim();
    }
}