package com.pm.suresecure;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.InetSocketAddress;




public class MySocketServer extends WebSocketServer {

    private WebSocket mSocket;
    private Database mydb;
    private String user;

    public MySocketServer(InetSocketAddress address, Database db, String username) {
        super(address);
        mydb=db;
        user=username;
    }

    public void sendMessage(String message) {
        mSocket.send(message);
    }


    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        mSocket = conn;
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        /*So this is where all the magic happens
        At this point weve recieved JSON containing the url.
        We need to parse that json into something useful
        then lookup the url in the database
        get any matching credentials
        and then form them into a json array
        and send it back to the client.

         */
        message = message.replaceAll("\\[","").replaceAll("\\]","");    //Before we can convert to a JSON object we have to strip the square brackets []
        JSONArray JA = new JSONArray();

        try{
            JSONObject reader = new JSONObject(message);                                            //Convert the incomming message to JSON Object
            String url = reader.getString("url");                                             //Get the value from the JSON Object
            url = url.replaceAll("www.","");                                      //We only want the base url, so strip the www part
            JA = mydb.getCredentials(url, user);                                                          //Get the credentials from the database, store them in a JSON Array (which well send)
            mSocket.send(JA.toString());
        }
        catch(Throwable t){                                                                         //Catch any errors converting message to JSON Object
            mSocket.send("[{\"response\":\"error in query\"}]");
            Log.e("My App", "Could not parse malformed JSON");
        }






    }

    @Override
    public void onError(WebSocket conn, Exception ex) {

    }

    @Override
    public void onStart() {

    }
}