package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class add_password extends AppCompatActivity {
    Database myDb;
    encryption e = new encryption();
    //SecretKey k = e.generateKey("AES");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);
        myDb = new Database(this);
        final String[] entry_name = new String[1];
        final String[] web_name = new String[1];
        final String[] web_usrName = new String[1];
        final String[] web_pass = new String[1];
        final String[] web_passCheck = new String[1];

        Button addPass_submit_Btn = findViewById(R.id.addPass_submit_Btn);

        addPass_submit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText addPass_name = findViewById(R.id.addPass_name);
                EditText addPass_URL = findViewById(R.id.addPass_URL);
                EditText addPass_UsrName = findViewById(R.id.addPass_UsrName);
                EditText addPass_password = findViewById(R.id.addPass_password);
                EditText addPass_password2 = findViewById(R.id.addPass_password2);

                entry_name[0] = addPass_name.getText().toString();
                web_name[0] = addPass_URL.getText().toString();
                web_usrName[0] = addPass_UsrName.getText().toString();
                web_pass[0] = addPass_password.getText().toString();
                web_passCheck[0] = addPass_password2.getText().toString();





                createAccountInstance();

                //Intent home_screen = new Intent(getApplicationContext(), home_screen.class);
                //startActivity(home_screen);
            }
            public void createAccountInstance(){
                byte[] encodedKey = Base64.decode(myDb.getKey("first", "SECRET_KEY"), Base64.DEFAULT);
                SecretKey originalKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
                byte[] pass = e.encryptString(web_pass[0], originalKey, e.ciph);
                String encryptedPass = new String(pass);
                if(entry_name[0].isEmpty() == true){
                    Toast.makeText(add_password.this, "Account name is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(web_name[0].isEmpty() == true){
                    Toast.makeText(add_password.this, "URL is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(web_usrName[0].isEmpty() == true){
                    Toast.makeText(add_password.this, "User name is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(web_pass[0].isEmpty() == true){
                    Toast.makeText(add_password.this, "Password is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if (web_pass[0].equals(web_passCheck[0]) == true) {
                        web_pass[0] = encryptedPass;
                        boolean isInserted = myDb.insertAccountData(entry_name[0], web_name[0], web_usrName[0], web_pass[0]);
                        if (isInserted == true) {
                            Toast.makeText(add_password.this, "An instance of account \n" + entry_name[0] + " was created", Toast.LENGTH_SHORT).show();
                            Intent home_screen = new Intent(getApplicationContext(), home_screen.class);
                            startActivity(home_screen);
                        } else
                            Toast.makeText(add_password.this, "Your instance of account \n" + entry_name[0] + " wasn't created.\n Please try again", Toast.LENGTH_SHORT).show();
                            return;
                    } else {
                        Toast.makeText(add_password.this, "Passwords not the same \n Please Try again", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            };
        });
    }
}
