package com.pm.suresecure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class password_info_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_info_screen);
        Database db = new Database(this);
        encryption e = new encryption();
       // SecretKey k = e.generateKey("AES");
        if (getIntent().hasExtra("com.pm.suresecure.SOMETHING"))
        {
            TextView passText = (TextView) findViewById(R.id.passText);
            String site = getIntent().getExtras().getString("com.pm.suresecure.SOMETHING");
            String pass = db.getSiteValues(site, "PASSWORD");
            System.out.println(pass);
            byte[] encodedKey = Base64.decode(db.getKey("first", "SECRET_KEY"), Base64.DEFAULT);
            System.out.println(encodedKey);
            SecretKey originalKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
            System.out.println(originalKey);
            byte[] byteArr = pass.getBytes();
            System.out.println(byteArr);
            String decrypt = e.decryptString(byteArr, originalKey, e.ciph);
            System.out.println(decrypt);
            System.out.println(e.ciph);
            System.out.println(e.ciph);

            String URL = db.getSiteValues(site, "URL");
            TextView siteText = findViewById(R.id.siteText);
            TextView URLText = findViewById(R.id.URLText);
            siteText.setText("Site name: " + site);
            URLText.setText("Site Url: " + URL);
            passText.setText("Site password: "  + decrypt);
        }
        //TextView passText = findViewById(R.id.passText);
        //passText.setText(here);
    }
}
