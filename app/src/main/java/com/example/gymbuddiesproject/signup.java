package com.example.gymbuddiesproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class signup extends AppCompatActivity {

    EditText Username , Useremail , DOB, GymName , Timing , Password , ConfirmPassword;
    Button signupButton;
    RadioGroup radioGroup;
    RadioButton radioButton;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Username = (EditText) findViewById(R.id.editTextTextPersonName2);
        Useremail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        DOB = (EditText) findViewById(R.id.editTextDate);
        GymName = (EditText) findViewById(R.id.editTextTextPersonName3);
        Timing = (EditText) findViewById(R.id.editTextTime);
        Password = (EditText) findViewById(R.id.editTextTextPassword2);
        ConfirmPassword = (EditText) findViewById(R.id.editTextTextPassword3);
        radioGroup = (RadioGroup) findViewById(R.id.radioId);
        signupButton = (Button) findViewById(R.id.signupBtn);

        myDB = new DBHelper(this);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = Username.getText().toString();
                String password = Password.getText().toString();
                String confirm_password = ConfirmPassword.getText().toString();
                String dob = DOB.getText().toString();
                String gymname = GymName.getText().toString();
                String timing = Timing.getText().toString();
                String useremail = Useremail.getText().toString();
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String gender = radioButton.getText().toString();


                if(username.equals("") || useremail.equals("") || dob.equals("") || gender.equals("") || gymname.equals("") || timing.equals("") || password.equals("") || confirm_password.equals(""))
                {
                    Toast.makeText(signup.this, "Fill all the input fields", Toast.LENGTH_SHORT).show();
                }

                else{
                    if(password.equals(confirm_password)) {
                        Boolean usercheckResult = myDB.checkusername(username);
                        if (usercheckResult == false) {
                            Boolean regResult = myDB.insertData(username, useremail , dob, gender, gymname, timing, password);
                            if (regResult == true) {
                                Toast.makeText(signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(signup.this, "User already exists. \n Please Sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(signup.this , "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

}