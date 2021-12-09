package com.example.frontend.Activities;

import static com.example.frontend.api.ApiClientFactory.GetWordApi;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.R;
import com.example.frontend.api.SharedUserData;
import com.example.frontend.api.SharedWebsocketData;
import com.example.frontend.api.SlimCallback;
import com.example.frontend.model.PaintView;
import com.example.frontend.model.User;
import com.example.frontend.model.Word;

import org.java_websocket.client.WebSocketClient;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Random;


/**
* Instantiates a new Drawing Acitivity that pops up a new in game menu in which a user can draw on the screen. 
* The in-game drawing activity allows the users to draw, customize their drawing with a few options - such as different pen colors 
* and also displays a random word from our databse. 
* @author : Jose Carlos Garcia
*/
public class DrawingActivity extends AppCompatActivity {

    private PaintView paintView;
    //Will need address + username/userid/displayname/password
    String w ="ws://coms-309-004.cs.iastate.edu:8080/chat/" + retrieveSoccket();
    private WebSocketClient gameSession;
    private User currentUser;
    String toSend ="";

    /**
    * Overrides AppCompatActivity's onCreate method, this creates content and context for the XML counterpart
    * @param savedInstanceState
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        //sets up PaintView object on the activity board
        currentUser = SharedUserData.getInstance().GetSharedUser();
        paintView = findViewById(R.id.PaintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        gameSession = SharedWebsocketData.getInstance().GetSharedClient();

        //Call the TextView filling proponent.
        getRandomWord();

        //Set red color
        final Button Red = findViewById(R.id.RED);
        Red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int set = Color.RED;
                paintView.setColor(set);
            }
        });
        //Set blue color
        final Button Blue = findViewById(R.id.BLUE);
        Blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int set = Color.BLUE;
                paintView.setColor(set);
            }
        });
        //Set green color
        final Button Green = findViewById(R.id.GREEN);
        Green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int set = Color.GREEN;
                paintView.setColor(set);

            }
        });
        //Set Black Color
        final Button Black = findViewById(R.id.BLACK);
        Black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int set = Color.BLACK;
                paintView.setColor(set);
            }
        });
        //Set eraser
        final Button Eraser = findViewById(R.id.WHITE);
        Eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int set = Color.WHITE;
                paintView.setColor(set);
            }
        });

        //Send Image to Back!
        final Button Save = findViewById(R.id.SAVE);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageFile();
                gameSession.send(toSend);
            }
        });
    }


    /**
     * Stores a saved instance into our API to be ported to another user's session. 
     * @param savedInstanceState : stores an instance of the current activity.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        
        PaintView saved = savedInstanceState.getParcelable("PaintView");

    }

    /**
    * Returns a random Word in type String from our databse, using the WordApi connecting 
    * the backend controller to the frontend for display in game
    * Uses helper class: getRandomItem(); 
    */
    public void getRandomWord() {
        TextView randomWord = findViewById(R.id.RANDOM);

        GetWordApi().GetAllWords().enqueue(new SlimCallback<List<Word>>(words -> {

            Random r = new Random();
            int size = words.size();
            int i = r.nextInt(size);

            String newWord = words.get(i) + "";
            randomWord.setText("Draw" + newWord);
        }));
    }
    /**
    * Returns a random word in String format based on the retrived ArrayList of Words using a random genereator
    * @param list - ports in the retrieved ArrayList from the API in getRandom() to be used. 
    *
    private Word getRandomItem(List<Word> list){
        //Establish a new Random generator, an index size, and finally generate a random int
        Random r = new Random();int size = list.size();int i = r.nextInt(size);
        //return random element from list
        return list.get(i);
    }
*/
   
    /**
     * Saves current Bitmap as a png file for use in our websocket feature!
     * Also to be used in our Gallery feature.
     *
     * Also sends image through websocket!
     */
    private void saveImageFile() {
        paintView.buildDrawingCache();
        Bitmap bmp = paintView.getDrawingCache();

        //Save file:
        ByteArrayOutputStream external = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 80, external);
        byte[] image = external.toByteArray();
        String imageStr = Base64.encodeToString(image, 0);
        toSend = imageStr;
    }


    /**
     * Launches a websocket.

    private void createWebSocketClient(){
        //Websocket for Image:
        Draft[] sendImage ={ new Draft_6455()};
        String connectionImage = w;
        try {
            Log.d("Socket:" , "Trying socket");
            sendImageSocket = new WebSocketClient(new URI(connectionImage), sendImage[1]) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.i("SendImage Socket", "Opened");
                    sendImageSocket.send("Image Sent!");
                }

                @Override
                public void onMessage(String message) {
                    final String s = message;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int i = 0;

                            if(i == 0) {
                                sendImageSocket.send("Preparing Image");
                                i++;
                            }else{
                                sendImageSocket.send("");
                            }
                        }
                    });
                }
                @Override
                public void onClose(int i, String reason, boolean b) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
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
        sendImageSocket.setConnectionLostTimeout(10000);
        sendImageSocket.connect();

    }
*/
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
