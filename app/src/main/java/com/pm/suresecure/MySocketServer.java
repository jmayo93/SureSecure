package com.pm.suresecure;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

import java.net.InetSocketAddress;



public class MySocketServer extends WebSocketServer {

    private WebSocket mSocket;

    public MySocketServer(InetSocketAddress address) {
        super(address);
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

        try{
            JSONObject reader = new JSONObject(message);                                            //Convert the incomming message to JSON Object
            String url = reader.getString("url");                                             //Get the value from the JSON Object
            url = url.replaceAll("www.","");                                      //We only want the base url, so strip the www part
            System.out.println(url);

        }
        catch(Throwable t){                                                                         //Catch any errors converting message to JSON Object
            Log.e("My App", "Could not parse malformed JSON");
        }
        //Now we have the url, we can perform the database query by forming a connection and using db.getCredentials(String URL) -- returns a JSONArray which we can then send






        System.out.println(message);
        //mSocket.send(message);                                //replies to the client with their own message (echo)
        String url = "[{\"url\":\"www.linkedin.com\"}]";        //Hardcode our urls to look for
        String url2 = "[{\"url\":\"login.squarespace.com\"}]";        //Hardcode our urls to look for
        String url3 = "[{\"url\":\"www.instagram.com\"}]";        //Hardcode our urls to look for
        if(message.equals(url)){                                //If we recieve the url and match, then send custom stuff.
            System.out.println("we matched!");
            mSocket.send("[{\"username\":\"rfrisch@linkedin.com\",\"pass\":\"pass1\"},\n" +
                    "{\"username\":\"jtruesell@linkedin.com\",\"pass\":\"pass2\"},\n" +
                    "{\"username\":\"jmayo@linkedin.com\",\"pass\":\"pass3\"}]");
        }
        else if(message.equals(url2)){
            System.out.println("we matched!");
            mSocket.send("[{\"username\":\"admin@squarespace.com\",\"pass\":\"pass1\"},\n" +
                    "{\"username\":\"Developer@squarespace.com\",\"pass\":\"pass2\"},\n" +
                    "{\"username\":\"endUser@squarespace.com\",\"pass\":\"pass3\"}]");
        }
        else if(message.equals(url3)){
            System.out.println("we matched!");
            mSocket.send("[{\"username\":\"someone@example.com\",\"pass\":\"pass1\"},\n" +
                    "{\"username\":\"person@example.com\",\"pass\":\"pass2\"},\n" +
                    "{\"username\":\"someoneElse@example.com\",\"pass\":\"pass3\"}]");

        }
        else{                                                   //Otherwise send an echo response
            mSocket.send(message);
        }

        /*Above is just for test purposes
        What we really should be doing here is:
        taking the message (received as JSON). parse that so you have just the url by itself
        lookup that url in the database, and get any matching credentials
        then parse those credentials into an JSON array and send it back
         */
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {

    }

    @Override
    public void onStart() {

    }
}