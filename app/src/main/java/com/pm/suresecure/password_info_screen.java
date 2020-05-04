package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class password_info_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_info_screen);
        final Database db = new Database(this);
        if (getIntent().hasExtra("com.pm.suresecure.SOMETHING"))
        {
            TextView passText = (TextView) findViewById(R.id.passText);
            String site = getIntent().getExtras().getString("com.pm.suresecure.SOMETHING");
            String pass = db.getPassValues(site, "PASSWORD");
            String URL = db.getPassValues(site, "URL");
            final String id = db.getPassValues(site,"ID");
            TextView siteText = findViewById(R.id.siteText);
            TextView URLText = findViewById(R.id.URLText);
            siteText.setText("Site name: " + site);
            URLText.setText("Site Url: " + URL);
            passText.setText("Site password: "  + pass);

            Button deleteButton = findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Well delete the record, then go back to the previous view
                    db.deleteRecord(id);
                    Toast.makeText(password_info_screen.this, "Record Removed", Toast.LENGTH_SHORT).show();
                    Intent home_screen = new Intent(getApplicationContext(), home_screen.class);
                    finish();
                    startActivity(home_screen);
                }
            });


        }

        //TextView passText = findViewById(R.id.passText);
        //passText.setText(here);
    }
}
