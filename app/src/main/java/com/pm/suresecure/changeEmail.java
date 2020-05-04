package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class changeEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        Intent intent=getIntent();  //Get the current intent
        final String username = intent.getStringExtra("username");        //Get the passed value and store it in a string
        final EditText emailInput = findViewById(R.id.emailInput);
        final Database db = new Database(this);
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailInput.getText().toString().isEmpty()){                                      //If the email is empty, display an error
                    Toast.makeText(changeEmail.this, "e-mail is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.changeEmail(username,emailInput.getText().toString());
                    Toast.makeText(changeEmail.this, "Email Changed Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SettingsScreen.class);          //return to the settings screen upon submitting
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
            }
        });
    }
}
