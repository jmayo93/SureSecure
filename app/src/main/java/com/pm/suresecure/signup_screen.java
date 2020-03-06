package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup_screen extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText addEmail, addPassword, addPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);
        myDb = new DatabaseHelper(this);
        createUser();

        Button next_Btn = (Button)findViewById(R.id.next_Btn);
        addEmail = (EditText)findViewById(R.id.signup_email);
        addPassword = (EditText)findViewById(R.id.signup_pass2);
        addPhone = (EditText)findViewById(R.id.signup_phone);

                next_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when "next" button clicked
                //take in email, password, and phone
                EditText signup_email = findViewById(R.id.signup_email);
                EditText signup_pass = findViewById(R.id.signup_pass);
                EditText signup_pass2 = findViewById(R.id.signup_pass2);
                EditText signup_phone = findViewById(R.id.signup_phone);

                //puts info in strings for now
                String accept_email = signup_email.getText().toString();
                String accept_pass = signup_pass.getText().toString();
                String accept_pass2 = signup_pass2.getText().toString();
                String accept_phone = signup_phone.getText().toString();

                //takes user to confirmation page
                Intent confirm_page = new Intent(getApplicationContext(), confirm_page.class);
                startActivity(confirm_page);
            }
        });
        createUser();
    }
    public void createUser(){
        Button next_Btn = (Button)findViewById(R.id.next_Btn);
        next_Btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(addEmail.getText().toString(),
                                addPassword.getText().toString(),
                                addPhone.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(signup_screen.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(signup_screen.this, "Data not inserted", Toast.LENGTH_LONG).show();
                        Intent home_screen = new Intent(getApplicationContext(), home_screen.class);
                        startActivity(home_screen);
                    }
                }
        );
    }

}
