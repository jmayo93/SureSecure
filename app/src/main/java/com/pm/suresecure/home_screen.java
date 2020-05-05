package com.pm.suresecure;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class home_screen extends AppCompatActivity{
    private static final String TAG = "home_screen";

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(intent);

    }

    Database db;
    ArrayList<String> mNames = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Intent intent=getIntent();  //Get the current intent
        final String username = intent.getStringExtra("username");        //Get the passed value and store it in a string

        db = new Database(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button settings_button = findViewById(R.id.settings_button);
        TextView addNewPass = findViewById(R.id.addNewPass);
        Log.d(TAG, "onCreate: started.");

        addNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_pass_screen = new Intent(getApplicationContext(), add_password.class);
                finish();
                add_pass_screen.putExtra("username",username);
                startActivity(add_pass_screen);
            }
        });

        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsScreen = new Intent(getApplicationContext(), SettingsScreen.class);
                SettingsScreen.putExtra("username",username);
                finish();
                startActivity(SettingsScreen);
            }
        });
        //ListView password_list = findViewById(R.id.password_list);
        initRecyclerView();
    }
    private void initRecyclerView(){
        try {
            Intent intent = getIntent();  //Get the current intent
            final String username = intent.getStringExtra("username");        //Get the passed value and store it in a string
            mNames.addAll(db.returnList(username));
            RecyclerView recyclerView = findViewById(R.id.accounts_recyclerView);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, this,username);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        catch(Throwable T){
            Log.e("MyApp",T.toString());
        }
    }
}

