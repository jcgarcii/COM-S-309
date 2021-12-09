package com.example.frontend.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.R;
import com.example.frontend.api.SharedUserData;
import com.example.frontend.api.SharedWebsocketData;
import com.example.frontend.model.User;

import org.java_websocket.client.WebSocketClient;

/**
 * Secondary multiplayer screen, displays an image (websocket feature) from the doodler's activity.
 * Three players are assigned this role while the first player in the lobby is assigned to draw.
 * Players are to guess via Textbox while a history of texts are stored and showcased.
 * When a text matches the doodler's given word - the session ends and the gamer is awarded points.
 *
 * @author Jose Carlos Garcia <jcgarcia@iastate.edu>
 */
public class GuessActivity extends AppCompatActivity {
    String w ="ws://coms-309-004.cs.iastate.edu:8080/chat/" + retrieveSoccket();
    protected WebSocketClient gameSession;
    private User currentUser;

    /**
     * Launching action - based on assignment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        //Initialize widgets to be used!
        currentUser = SharedUserData.getInstance().GetSharedUser();
        gameSession = SharedWebsocketData.getInstance().GetSharedClient();
        Button chatEntryGuess = findViewById(R.id.sendChatGuess);
        TextView chatlogGuess = findViewById(R.id.chatGuess);
        ImageView display = findViewById(R.id.imageViewGuess);

        chatEntryGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        
    }

    /**
     * Sends chat whenever the send button is pressed!
     */
    public void sendMessage(){
        EditText sendChat = findViewById(R.id.sendMessageGuess);
        gameSession.send(sendChat.getText().toString());
        sendChat.setText("");
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

