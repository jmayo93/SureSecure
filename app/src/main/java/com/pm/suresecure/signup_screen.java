package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        Button next_Btn = findViewById(R.id.next_Btn);

        next_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText signup_email = (EditText) findViewById(R.id.signup_email);
                EditText signup_pass = (EditText) findViewById(R.id.signup_pass);
                EditText signup_pass2 = (EditText) findViewById(R.id.signup_pass2);
                EditText signup_phone = (EditText) findViewById(R.id.signup_phone);

                String accept_email = signup_email.getText().toString();
                String accept_pass = signup_pass.getText().toString();
                String accept_pass2 = signup_pass2.getText().toString();
                String accept_phone = signup_phone.getText().toString();

                Intent confirm_page = new Intent(getApplicationContext(), confirm_page.class);
                startActivity(confirm_page);
            }
        });
    }
}
