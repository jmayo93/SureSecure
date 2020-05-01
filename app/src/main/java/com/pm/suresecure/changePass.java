package com.pm.suresecure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class changePass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        Intent intent=getIntent();  //Get the current intent
        final String username = intent.getStringExtra("username");                              //Get the passed value and store it in a string
        final Database db = new Database(this);                                             //Get the database




        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                      //What happens when we hit the submit button
                //Get the field, get its value, then use the RecoverPassword function to send it
                EditText newPass_input = findViewById(R.id.newPass);                                 //Get the field
                String newPass = newPass_input.getText().toString();
                if(newPass.isEmpty()){                                                              //If the user hasnt entered anything in the pass yet, display an alert
                    Toast.makeText(changePass.this, "Password is empty", Toast.LENGTH_SHORT).show();
                }
                else{                                                                               //Otherwise, update the password.
                    db.RecoverPassword(username,newPass);
                    Toast.makeText(changePass.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                    //After that we can return to the homescreen
                    Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);      //Declare our new intent
                    startActivity(mainActivity);                                                //Go to the changePass screen

                }


            }
        });









    }
}
