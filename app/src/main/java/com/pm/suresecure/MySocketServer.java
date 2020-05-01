package com.pm.suresecure;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

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