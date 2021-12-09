package com.example.frontend.api;

import org.java_websocket.client.WebSocketClient;

public class SharedWebsocketData {
    private static SharedWebsocketData instance;

    private WebSocketClient currentWebsocket = null;

    public static SharedWebsocketData getInstance(){
        if(instance == null) instance = new SharedWebsocketData();
        return instance;
    }

    private SharedWebsocketData(){}

    public void SetSharedWebSocket(WebSocketClient client){
        this.currentWebsocket = client;
    }

    public WebSocketClient GetSharedClient(){
        return this.currentWebsocket;
    }

}
