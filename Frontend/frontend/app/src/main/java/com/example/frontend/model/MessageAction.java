package com.example.frontend.model;


public class MessageAction {

    private long idNum;

    /**
     * These constants are different actions that can be taken on the client side
     * to preform an action or trigger an event in the web socket.
     * Do we want to make these separate subclasses?
     */
    final private String rs = "ROUND_START";
    final private String g  = "GUESS";
    final private String gs = "GAME_START";
    final private String ge = "GAME_END";
    final private String cd = "CURRENT_DOODLE";

    private String action;

    private String message;

    public String getAction()
    {
        return action;
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
    public String getMessage(){return message;}


}

