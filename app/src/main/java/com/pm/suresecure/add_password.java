package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);

        Button addPass_submit_Btn = findViewById(R.id.addPass_submit_Btn);

        addPass_submit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText addPass_name = findViewById(R.id.addPass_name);
                EditText addPass_URL = findViewById(R.id.addPass_URL);
                EditText addPass_UsrName = findViewById(R.id.addPass_UsrName);
                EditText addPass_password = findViewById(R.id.addPass_password);

                String entry_name = addPass_name.getText().toString();
                String web_name = addPass_URL.getText().toString();
                String web_UsrName = addPass_UsrName.getText().toString();
                String web_pass = addPass_password.getText().toString();

                Intent home_screen = new Intent(getApplicationContext(), home_screen.class);
                startActivity(home_screen);
            }
        });
    }
}
