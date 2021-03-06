package com.pm.suresecure;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class home_screen extends AppCompatActivity{
    private static final String TAG = "home_screen";
    private CountDownTimer cdt;
    private long timeLeftInMilli = 300000;
    private TextView changeText;

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(intent);

    }

    Database db;
    ArrayList<String> mNames = new ArrayList<String>();
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Intent intent=getIntent();  //Get the current intent
        final String username = intent.getStringExtra("username");        //Get the passed value and store it in a string
        db = new Database(this);
        changeText = findViewById(R.id.timer);
        start();
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

    //Start and fill Recycler View
    private void initRecyclerView(){
        try {
            Intent intent = getIntent();  //Get the current intent
            final String username = intent.getStringExtra("username");        //Get the passed value and store it in a string
            //query database and fill recycler view
            mNames.addAll(db.returnList(username));
            RecyclerView recyclerView = findViewById(R.id.accounts_recyclerView);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, this,username);
            recyclerView.setAdapter(adapter);
            Log.d(TAG, "initRecyclerView: init recyclerview");
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        catch(Throwable T){
            Log.e("MyApp",T.toString());
        }
    }
    //starts the timer
    public void start() {
        cdt = new CountDownTimer(timeLeftInMilli, 1000) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTick(long l) {
                timeLeftInMilli = l;
                updateTimer();

            }

            @Override
            public void onFinish() {
            }
        }.start();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    //changes the time on the timer
    public void updateTimer(){
        changeText = findViewById(R.id.timer);
        int minutes = (int) timeLeftInMilli/ 60000;
        int seconds = (int) timeLeftInMilli % 60000 / 1000;
        if(minutes + seconds == 0){
            //when timer is done, close the app
            this.finishAffinity();
        }

        String timeLeft;

        timeLeft = "Time left: " + minutes;
        timeLeft += " : ";
        if (seconds < 10) timeLeft += 0;
        timeLeft += seconds;
        changeText.setText(timeLeft);
    }
}

