package com.pm.suresecure;

import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;

import de.greenrobot.event.EventBus;


public class home_screen extends AppCompatActivity{
    private static final String TAG = "home_screen";
    private static final int SERVER_PORT = 12345;
    Database db;
    ArrayList<String> mNames = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new Database(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        Button settings_button = findViewById(R.id.settings_button);
        TextView addNewPass = findViewById(R.id.addNewPass);
        Log.d(TAG, "onCreate: started.");
        //ListView password_list = findViewById(R.id.password_list);
        initRecyclerView();
        startServer();
        EventBus.getDefault().register(this);
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
    }
    private MySocketServer mServer;
    public void onEvent(SocketMessageEvent event) {
        System.out.println("Yellow");
        System.out.println(event.getMessage());
    }
    private void startServer() {
        InetAddress inetAddress = getInetAddress();
        if (inetAddress == null) {
            Log.e(TAG, "Unable to lookup IP address");
            return;
        }
        mServer = new MySocketServer(new InetSocketAddress(inetAddress.getHostAddress(), SERVER_PORT));
        mServer.start();
        Log.d(TAG, "startServer Started");
    }

    private static InetAddress getInetAddress() {
        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface networkInterface = (NetworkInterface) en.nextElement();

                for (Enumeration enumIpAddr = networkInterface.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();

                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        System.out.println(inetAddress);
                        return inetAddress;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            Log.e(TAG, "Error getting the network interface information");
        }

        return null;
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

