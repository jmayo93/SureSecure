package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import javax.crypto.*;

public class signup_screen extends AppCompatActivity {

    Database myDb;
    encryption e = new encryption();
    EditText addUserName, addEmail, addPassword, addPhone, addPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);
        myDb = new Database(this);

        Button next_Btn = (Button) findViewById(R.id.next_Btn);
        //declare variables accessible to createUser() but also assignable inside onClick()
        final String[] accept_userName = new String[1];
        final String[] accept_email = new String[1];
        final String[] accept_pass = new String[1];
        final String[] accept_phone = new String[1];
        final String[] check_pass = new String[1];
        next_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //when "next" button click
                //takes user to confirmation page
                addUserName = (EditText) findViewById(R.id.signup_userName);
                addEmail = (EditText) findViewById(R.id.signup_email);
                addPassword2 = (EditText) findViewById(R.id.signup_pass);
                addPassword = (EditText) findViewById(R.id.signup_pass2);
                addPhone = (EditText) findViewById(R.id.signup_phone);

                accept_userName[0] = addUserName.getText().toString();
                accept_email[0] = addEmail.getText().toString();
                accept_pass[0] = addPassword.getText().toString();
                accept_phone[0] = addPhone.getText().toString();
                check_pass[0] = addPassword2.getText().toString();
                //make sure passwords are the same before submitting to the database.
                if((accept_pass[0].equals(check_pass[0])) == false) {
                    Toast.makeText(signup_screen.this, "Passwords not the same \n Please Try again", Toast.LENGTH_LONG).show();
                    return;
                }
                //future: Check here to see if user name is already registered
                Intent confirm_page = new Intent(getApplicationContext(), confirm_page.class);
                System.out.println("We got to the create user call");
                createUser();
                startActivity(confirm_page);
            }
            public void createUser()
            {
                boolean isInserted = myDb.insertMasterData(accept_userName[0],accept_email[0], accept_pass[0], accept_phone[0]);
                myDb.insertKeyData("first", e.generateKey("AES"));
                if (isInserted == true)
                    Toast.makeText(signup_screen.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(signup_screen.this, "Data not inserted", Toast.LENGTH_LONG).show();
                Intent home_screen = new Intent(getApplicationContext(), home_screen.class);
                startActivity(home_screen);
            }
            });
    }

}
