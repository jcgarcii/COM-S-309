package coms309.roundtrip.backend.websockets;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import coms309.roundtrip.backend.model.Message;
import coms309.roundtrip.backend.model.MessageAction;
import coms309.roundtrip.backend.model.User;
import coms309.roundtrip.backend.model.Word;
import coms309.roundtrip.backend.repository.ActionRepository;
import coms309.roundtrip.backend.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller      // this is needed for this to be an endpoint to springboot
@ServerEndpoint(value = "/chat/{username}/{userId}/{displayname}/{password}")  // this is Websocket url
public class WebSocketSession {

    // cannot autowire static directly (instead we do it by the below
    // method
    private static MessageRepository msgRepo;

    private final int MAX_ROUNDS = 3;

    private static ActionRepository actionRepo;
    private static ActionRepository messageRepo;


    private String status = "NOT_IN_ROUND";
    private MultipartFile currentDrawing = null;

    private static User player1 = null;
    private static User player2 = null;
    private static User player3 = null;
    private static User player4 = null;

    private static Session player1session;
    private static Session player2session;
    private static Session player3session;
    private static Session player4session;

    private User currentDood = player1;
    private Session currentDoodSession = player1session;

    private static int p1score; //temp solution until we figure out exactly how we want this to be done
    private static int p2score;
    private static int p3score;
    private static int p4score;

    private String currentWord; //The current word being used in the round.
    private boolean inRound;
    private int currentRound; //The current round being played.


    /*
     * Grabs the MessageRepository singleton from the Spring Application
     * Context.  This works because of the @Controller annotation on this
     * class and because the variable is declared as static.
     * There are other ways to set this. However, this approach is
     * easiest.
     */

    // Store all socket session and their corresponding username.
    private final Logger logger = LoggerFactory.getLogger(WebSocketSession.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("userId") long userId,
                                @PathParam("password") String password, @PathParam("displayname") String displayname)
            throws IOException, EncodeException {

        User user = new User();
        user.setIdNum(userId);
        user.setLoginname(username);
        user.setPassword(password);
        user.setDisplayname(displayname);

        logger.info("Entered into Open");

        if (addPlayer(user, session)){
            System.out.println("Player being added!");
            String message = user.getDisplayname() + " has joined the game.";
            broadcast(message);
        }
        else{
            broadcast("Server is full.");
            MessageAction message = new MessageAction(-1, "Game is full.");
            message.setGf();
            session.getBasicRemote().sendObject(message);
        }
    }


    @OnMessage
    public void onMessage(Session session, String message) throws IOException {

        // Handle new messages
        // Direct message to a user using the format "@username <message>"
        if (message.startsWith("#")) {
            String whatdo = message.split(" ")[0].substring(1);
            if (whatdo.equals("GAME_END")) {
                broadcast("The game is over!");
                resetGame();
            } else if (whatdo.contains("ROUND_START")) {
                inRound = true;
                broadcast("Round is beginning!");
                nextDood();
                currentWord = whatdo.split(" ")[10].substring(11);
            } else if (whatdo.equals("GAME_START")) {
                broadcast("Game is starting!");
                currentWord = "turtle";
            } else if (whatdo.equals("ROUND_END")) {
                inRound = false;
                broadcast("Round is over!");
                currentWord = null;
            } else if (whatdo.equals("CURRENT_DOODLE")) {
                if (player1session != null && player1session != currentDoodSession) {
                    try {
                        player1session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        logger.info("Exception " + e.getMessage().toString());
                        e.printStackTrace();
                    }
                }
                if (player2session != null && player2session != currentDoodSession) {
                    try {
                        player2session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        logger.info("Exception " + e.getMessage().toString());
                        e.printStackTrace();
                    }
                }
                if (player3session != null && player3session != currentDoodSession) {
                    try {
                        player3session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        logger.info("Exception " + e.getMessage().toString());
                        e.printStackTrace();
                    }
                }
                if (player4session != null && player4session != currentDoodSession) {
                    try {
                        player4session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        logger.info("Exception " + e.getMessage().toString());
                        e.printStackTrace();
                    }

                }
                broadcast("Drawing is done! Time to guess!");
            } else if (whatdo.contains("NEW_WORD: ")) {
                currentWord = null; //TODO: LOGIC TO PARSE FOR NEW WORD
            }
        }
        else { // broadcast

                if(message.equalsIgnoreCase(currentWord))
                {
                    if(session == player1session){p1score += 100;}
                    else if(session == player2session){p2score += 100;}
                    else if(session == player3session){p3score += 100;}
                    else if(session == player4session){p4score += 100;}
                } else {
                    if(session == player1session){broadcast(player1.getDisplayname() + ": " + message);}
                    else if(session == player2session){broadcast(player2.getDisplayname() + ": " + message);}
                    else if(session == player3session){broadcast(player3.getDisplayname() + ": " + message);}
                    else if(session == player4session){broadcast(player4.getDisplayname() + ": " + message);}
                    System.out.println(message);
                }
            }
            // Saving chat history to repository

    }


    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        // remove the user connection information

        // broadcast that the user disconnected
        String message = "User has left the game, must restart game.";
        if(session == player1session){
            player1 = null;
            player1session = null;
        }
        else if(session == player2session){
            player2 = null;
            player2session = null;
        }
        else if(session == player3session){
            player3 = null;
            player3session = null;
        }
        else if(session == player4session){
            player4 = null;
            player4session = null;
        }
        broadcast(message);

        session = null;
        /*player1session.close();
        player2session.close();
        player3session.close();
        player4session.close();
        resetGame();
         */
    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        logger.info("Entered into Error");
        throwable.printStackTrace();
    }

    public int findPlayer(long givenID) {
        if (givenID == player1.idNum) {
            return 1;
        }
        if (givenID == player2.idNum) {
            return 2;
        }
        if (givenID == player3.idNum) {
            return 3;
        }
        if (givenID == player4.idNum) {
            return 4;
        }
        return 0;
    }


    /**
     * this will occur on a game end messgae. It will reset all of the values in the websocket.
     */
    private void resetGame() {
        player1 = null;
        player2 = null;
        player3 = null;
        player4 = null;
        actionRepo.deleteAll();
        messageRepo.deleteAll();
        player1session = null;
        player2session = null;
        player3session = null;
        player4session = null;
        p1score = 0;
        p2score = 0;
        p3score = 0;
        p4score = 0;
        currentRound = 0;
    }

    private Boolean addPlayer(User joinUser, Session session) {
        if ((player4 != null) && (player3 != null) && (player2 != null) && (player1 != null)) {
            return false; //This means the game is full, we will want to throw an error.
        }
        if (player1 == null) {
            System.out.println("Player1 was null!");
            player1 = joinUser;
            player1session = session;
        } //Go through the users and add the user to the first available slot
        else if (player2 == null) {
            System.out.println("Player2 was null!");
            player2 = joinUser;
            player2session = session;
        } else if (player3 == null) {
            player3 = joinUser;
            player3session = session;
        } else {
            player4 = joinUser;
            player4session = session;
        }

        return true;
    }

    private void broadcast(String message) {
        if (player1 != null) {
            try {
                player1session.getBasicRemote().sendText(message);
                System.out.println("Player 1 got this message!");
            } catch (IOException e) {
                logger.info("Exception " + e.getMessage().toString());
                e.printStackTrace();
            }
        }
        if (player2 != null) {
            try {
                player2session.getBasicRemote().sendText(message);
                System.out.println("Player 2 got this message!");
            } catch (IOException e) {
                logger.info("Exception " + e.getMessage().toString());
                e.printStackTrace();
            }
        }
        if (player3 != null) {
            try {
                player3session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.info("Exception " + e.getMessage().toString());
                e.printStackTrace();
            }
        }
        if (player4 != null) {
            try {
                player4session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.info("Exception " + e.getMessage().toString());
                e.printStackTrace();
            }
        }
    }

    private void nextDood()
    {
        if(currentDood.equals(player1))
        {
            currentDood = player2;
            currentDoodSession = player2session;
        }
        else if(currentDood.equals(player2) && player3 != null)
        {
            currentDood = player3; currentDoodSession = player3session;
        }
        else if(currentDood.equals(player3) && player4 != null)
        {
            currentDood = player4; currentDoodSession = player4session;
        }
        else{currentDood = player1; currentDoodSession = player1session;}
    }


} // end of Class
