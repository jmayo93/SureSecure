package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PWrecover_screen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwrecover_screen);

        Intent intent=getIntent();  //Get the current intent
        final String username = intent.getStringExtra("username");        //Get the passed value and store it in a string

        final Database db = new Database(this);
        TextView secQuestion = (TextView) findViewById(R.id.secQuestion);
        if(username.isEmpty()){                                                                     //Error handling -- if no username was sent
            Toast.makeText(this, "username was left empty", Toast.LENGTH_SHORT).show();
        }else {
            String Q1 = db.getSecQuestion(username);
            secQuestion.setText(Q1);
        }


        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //sets signup to go to sign up screen when clicked

                /*We hit the button to submit, heres what needs to happen
                    Get the value from the secAnswer
                    check it agaisnt the value stored in the database
                    If its correct, move onto the next page
                    If its wrong, send a toast message telling them to try again.
                 */
                EditText secAnswer_input = findViewById(R.id.secAnswer);                //Get the field
                String secAnswer = secAnswer_input.getText().toString();

                //Get the answer stored in the database
                String A1 = db.getSecAnswer(username);

                if(A1.equals(secAnswer)){                                                           //If the answer is correct
                    //Then we want to move onto the  update password page.
                    Intent changePass = new Intent(getApplicationContext(), changePass.class);      //Declare our new intent
                    changePass.putExtra("username",username);                          //We have to pass the username
                    finish();
                    startActivity(changePass);                                                //Go to the changePass screen

                }
                else{                                                                               //Otherwise the answer didnt match, output error
                    Toast.makeText(PWrecover_screen.this, "Wrong answer", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }//end oncreate



}//end class
