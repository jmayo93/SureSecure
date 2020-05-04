package com.pm.suresecure;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsScreen extends AppCompatActivity {

    @Override
    public void onBackPressed(){
        Intent intent=getIntent();  //Get the current intent
        final String username = intent.getStringExtra("username");        //Get the passed value and store it in a string
        Intent home_screen = new Intent(getApplicationContext(), home_screen.class);
        home_screen.putExtra("username",username);
        finish();
        startActivity(home_screen);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();  //Get the current intent
        final String username = intent.getStringExtra("username");        //Get the passed value and store it in a string

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen2);
        Button homeButton = findViewById(R.id.homeButton);
        Button deleteMasterAccountButton = findViewById(R.id.deleteMasterAccount);
        Button changeMasterPasswordButton = findViewById(R.id.changeMasterPassword);
        Button changeEmailButton = findViewById(R.id.changeEmail);
        Button changePhoneNumberButton = findViewById(R.id.changePhoneNumber);
        Button logoutButton = findViewById(R.id.logout);


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), home_screen.class);
                intent.putExtra("username",username);
                finish();
                startActivity(intent);
            }
        });

        deleteMasterAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete Everything??
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
            }
        });

        changeMasterPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Redirect to the ChangePassword, have to pass the username intention.putextra
                Intent intent = new Intent(getApplicationContext(), changePass.class);
                intent.putExtra("username",username);                                           //Well have to pass the current user for this
                finish();
                startActivity(intent);
            }
        });

        changeEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), changeEmail.class);
                intent.putExtra("username",username);                                           //Well have to pass the current user for this
                startActivity(intent);
            }
        });

        changePhoneNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), changePhone.class);
                intent.putExtra("username",username);                                           //Well have to pass the current user for this
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();                                                              //Close all tasks and shut down the application
            }
        });

        //This part handles displaying the IP address
        try {
            TextView textView = findViewById(R.id.getIPAddress);
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
            textView.setText("Your IP Adress: " +ipAddress+":12345");
        }
        catch(Throwable T){
            Log.e("MyApp",T.toString());
        }


    }
}
