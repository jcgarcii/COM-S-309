package coms309.roundtrip.backend.model;


import javax.persistence.*;

@Entity
@Table(name = "action")
public class MessageAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idNum;

    /**
     * These constants are different actions that can be taken on the client side
     * to preform an action or trigger an event in the web socket.
     * Do we want to make these separate subclasses?
     */
    final String rs = "ROUND_START";
    final String g  = "GUESS";
    final String gs = "GAME_START";
    final String ge = "GAME_END";
    final String cd = "CURRENT_DOODLE";
    final String re = "ROUND_END";
    final String gf = "GAME_FULL";

    public MessageAction(){

    }

    public MessageAction(long userID, String message) {
        this.userID = userID;
        this.message = message;
    }

    private String action;
    private long userID;
    private String message;

    public String getAction()
    {
        return action;
    }

    public long getUserID(){
        return userID;
    }

    public void setRs()
    {
        action = rs;
    }
    public void setG()
    {
        action = g;
    }
    public void setGs()
    {
        action = gs;
    }
    public void setGe()
    {
        action = ge;
    }
    public void setCd() {action = cd;}
    public void setRe() {action = re;}
    public void setGf() {action = gf;}

    public String getMessage(){return message;}
    

}

