
package com.pm.suresecure;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

import de.greenrobot.event.EventBus;


public class MySocketServer extends WebSocketServer{
    private WebSocket mSocket;

    public MySocketServer(InetSocketAddress address) {
        super(address);
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
        EventBus.getDefault().post(new SocketMessageEvent(message));
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {

    }
}
