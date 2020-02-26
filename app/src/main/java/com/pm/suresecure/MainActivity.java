package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView signup_text = findViewById(R.id.signup_text);
        TextView FP_text = findViewById(R.id.FP_text);
        Button login_Btn = findViewById(R.id.login_Btn);

        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup_screen = new Intent(getApplicationContext(), signup_screen.class);
                startActivity(signup_screen);
            }
        });

        FP_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PWrecover_Screen = new Intent(getApplicationContext(), PWrecover_screen.class);
                startActivity(PWrecover_Screen);
            }
        });

        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText signin_email = (EditText) findViewById(R.id.signin_email);
                EditText signin_pass = (EditText) findViewById(R.id.signin_pass);

                String user = signin_email.getText().toString();
                String pass = signin_pass.getText().toString();
                Intent home_screen = new Intent(getApplicationContext(), home_screen.class);
                startActivity(home_screen);
            }
        });
    }
}
