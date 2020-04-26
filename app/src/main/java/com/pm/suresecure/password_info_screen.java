package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class password_info_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_info_screen);
        Database db = new Database(this);
        if (getIntent().hasExtra("com.pm.suresecure.SOMETHING"))
        {
            TextView passText = (TextView) findViewById(R.id.passText);
            String site = getIntent().getExtras().getString("com.pm.suresecure.SOMETHING");
            String pass = db.getPassValues(site, "PASSWORD");
            String URL = db.getPassValues(site, "URL");
            TextView siteText = findViewById(R.id.siteText);
            TextView URLText = findViewById(R.id.URLText);
            siteText.setText("Site name: " + site);
            URLText.setText("Site Url: " + URL);
            passText.setText("Site password: "  + pass);
        }
        //TextView passText = findViewById(R.id.passText);
        //passText.setText(here);
    }
}
