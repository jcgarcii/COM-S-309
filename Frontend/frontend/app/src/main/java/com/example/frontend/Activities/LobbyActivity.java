package com.example.frontend.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.R;
import com.example.frontend.api.SharedUserData;
import com.example.frontend.api.SharedWebsocketData;
import com.example.frontend.model.User;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * MAIN IN-GAME LOBBY
 * This activity commences as soon as you start the Game and have logged in.
 * You are able to see available players with you before the game starts.
 * This is where players get assigned their roles.
 *
 * @author Jose Carlos Garcia <jcgarcia@iastate.edu>
 */
public class LobbyActivity extends AppCompatActivity {

    public DrawingActivity drawActivity;
    public GuessActivity guessActivity;
    private WebSocketClient lobbyChat;
    private User currentUser;
    private boolean leftLobby;
    private WebSocketClient sharedClient;
    private String w = "ws://coms-309-004.cs.iastate.edu:8080/chat/";

    /**
     * This onCreate() method doesn't differ much from the standard one, main difference is that this calls and instantiates
     * each activity based on User input with the exclusion of the UserLogin as that comes up first when the application is launched.
     * @param savedInstanceState - used in conjunction with our XML files corresponding to each activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        //Setup stuffs
        currentUser = SharedUserData.getInstance().GetSharedUser();
        Button drawingButton = findViewById(R.id.drawingButton);
        Button guessingButton = findViewById(R.id.guessingButton);
        Button sendChat = findViewById(R.id.sendChatLobby);


        //Activity instances
        drawActivity = new DrawingActivity();
        guessActivity = new GuessActivity();

        //Intents for our activities - based on player entry.
        Intent drawing = new Intent(LobbyActivity.this, DrawingActivity.class);
        Intent guess = new Intent(LobbyActivity.this, GuessActivity.class);

        drawingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(drawing);
                finish();
                leftLobby = true;
            }
        });

        guessingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(guess);
                finish();
                leftLobby = true;
            }
        });

        createWebsocketClient();

        sendChat.setOnClickListener(v -> sendMessage());
    }

    private void createWebsocketClient(){
        //ChatLog Connection
        TextView chatlog = findViewById(R.id.chatLogLobby);
        //Websocket parameters
        Draft[] drafts ={new Draft_6455()};
        String connect =  w + retrieveSoccket();
        try{
            Log.d("Socket:", "Trying socket");
            lobbyChat = new WebSocketClient(new URI(connect), (Draft) drafts[0]) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.i("Websocket", "Opened");
                    lobbyChat.send("Greetings!");
                }

                @Override
                public void onMessage(String s) {
                    final String message = s;
                    if(leftLobby == true){
                        Log.i("Recieved", "Message");
                    }else{
                        runOnUiThread(() -> chatlog.setText(chatlog.getText() + "\n" + message));
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.d("CLOSE", "onClose() returned: " + s);
                    System.out.println(connect);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception:", e.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage());
            e.printStackTrace();
        }
        lobbyChat.connect();
        SharedWebsocketData.getInstance().SetSharedWebSocket(lobbyChat);
    }


    /**
     * Sends a message from the websocket after the button is pushed! (See lines 120-125)
     */
    public void sendMessage(){
        EditText sendchat = findViewById(R.id.chatEntryLobby);
        lobbyChat.send(sendchat.getText().toString());
        sendchat.setText("");
    }

    /**
     * Retrieves a string of information uses to initiate websocket.
     * @return String s: as stated, this will initiate the websocket process.
     */
    public String retrieveSoccket(){
        currentUser = SharedUserData.getInstance().GetSharedUser();
        //Will need address + username/userid/displayname/password
        String s = currentUser.getLoginname() + "/" + currentUser.getIdNum() + "/" + currentUser.getDisplayname() + "/" + currentUser.getPassword();
        return s;
    }

}
