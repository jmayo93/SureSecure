package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class success_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_screen);


        Button continueButton = (Button) findViewById(R.id.continue_Btn);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                      //What happens when we hit the submit button
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);      //Declare our new intent
                startActivity(mainActivity);                                                //Go to the changePass screen

            }
        });

    }
}
