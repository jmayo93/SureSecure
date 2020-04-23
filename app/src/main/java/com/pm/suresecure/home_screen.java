package com.pm.suresecure;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class home_screen extends AppCompatActivity{
    private static final String TAG = "home_screen";

    Database db;
    ArrayList<String> mNames = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**mNames.add("Petunia");
        mNames.add("Cauliflower");
        mNames.add("horticulture");
        mNames.add("Petunia");
        mNames.add("Cauliflower");
        mNames.add("horticulture");
        mNames.add("Petunia");
        mNames.add("Cauliflower");
        mNames.add("horticulture");
        mNames.add("Petunia");
        mNames.add("Cauliflower");
        mNames.add("horticulture");**/
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
        initRecyclerView();
    }
    private void initRecyclerView(){
        mNames.addAll(db.returnList());
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.accounts_recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

