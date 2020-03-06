package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class home_screen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        Button settings_button = findViewById(R.id.settings_button);
        TextView addNewPass = findViewById(R.id.addNewPass);

        addNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_pass_screen = new Intent(getApplicationContext(), add_password.class);
                startActivity(add_pass_screen);
            }
        });

        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreen = new Intent(getApplicationContext(), SettingsScreen.class);
                startActivity(SettingsScreen);
            }
        });

        //ListView password_list = findViewById(R.id.password_list);
    }
}

