package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declare 'Database variable'
    Database myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create new instance of a database
        myDb = new Database(this);
        System.out.println("Test");
        TextView signup_text = findViewById(R.id.signup_text);
        TextView FP_text = findViewById(R.id.FP_text);
        Button login_Btn = findViewById(R.id.login_Btn);

        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //sets signup to go to sign up screen when clicked
                Intent signup_screen = new Intent(getApplicationContext(), signup_screen.class);
                startActivity(signup_screen);
            }
        });

        FP_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //sets forgot password to go to forgot password screen when clicked
                Intent PWrecover_Screen = new Intent(getApplicationContext(), PWrecover_screen.class);
                startActivity(PWrecover_Screen);
            }
        });
        login_Btn.setOnClickListener(new View.OnClickListener() { //when login button clicked
            @Override
            public void onClick(View v) {
                //variable pre-set as EditText so we can use the 'getText()' function
                //otherwise variable will be populated with meta-data
                EditText preUserName = findViewById(R.id.signin_email);
                EditText prePw = findViewById(R.id.signin_pass);
                //then get the text values and store as strings to test
                String userName = preUserName.getText().toString();
                String pw = prePw.getText().toString();
                // check to see if there is a username in the field
                if (userName.isEmpty() == true) {
                    Toast.makeText(MainActivity.this, "User Name is empty", Toast.LENGTH_SHORT).show();
                    //check to see if there is a password in the field
                } else if (pw.isEmpty() == true) {
                    Toast.makeText(MainActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
                } else {
                    //takes in email and password
                    EditText signin_email = (EditText) findViewById(R.id.signin_email);
                    EditText signin_pass = (EditText) findViewById(R.id.signin_pass);

                    //puts email and password into these strings for now
                    String email = signin_email.getText().toString();
                    String pass = signin_pass.getText().toString();
                    try {
                        if (pass.equals(myDb.getUserValues(email, "PASSWORD")) == false) {
                            Toast.makeText(MainActivity.this, "Wrong password, \n please try again.", Toast.LENGTH_SHORT).show();
                        } else {
                            //takes user to app home screen
                            Toast.makeText(MainActivity.this, "Correct Password", Toast.LENGTH_SHORT).show();
                            Intent home_screen = new Intent(getApplicationContext(), home_screen.class);
                            startActivity(home_screen);
                        }
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Something went wrong \n Are you using the correct user name?\n Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }}
