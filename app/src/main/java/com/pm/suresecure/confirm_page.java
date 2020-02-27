package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class confirm_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_page);

        Button verify_next_Btn = findViewById(R.id.verify_next_Btn);

        verify_next_Btn.setOnClickListener(new View.OnClickListener() { //when next button clicked
            @Override
            public void onClick(View v) {
                EditText enter_verify_code = findViewById(R.id.enter_verify_code); //takes in verification code
                String verify_code = enter_verify_code.getText().toString(); //puts in string for now

                //have to add if statement here to see if correct code or not

                Intent success_screen = new Intent(getApplicationContext(), success_screen.class);
                startActivity(success_screen);
            }
        });
    }
}
