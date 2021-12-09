package coms309.roundtrip.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "drawSessions" )
public class DrawSession {

    public static final String o = "ONGOING";
    public static final String f = "FINISHED";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idNum;

    /*
    * The round timer is the number of seconds each round will last.
    * Will be betweeen 30 and 90 seconds.
     */
    private int roundTimer;

    /*
    * Long id number of the user that initiated the game
     */
    private long gameHost;

    /*
    * Number of rounds in a game. Will be between 1 and 4.
     */
    private int roundNumbers;

    /* I'll probably make this a hash map but there won't be any more than three players and a game host */
    private int player1;

    private int currentWord;

    private String gameStatus;


    public Long getIdNum() {
        return idNum;
    }

    public void setIdNum(Long idNum) {
        this.idNum = idNum;
    }

    public int getRoundTimer() {
        return roundTimer;
    }

    public void setRoundTimer(int roundTimer) {
        this.roundTimer = roundTimer;
    }

    public long getGameHost() {
        return gameHost;
    }

    public void setGameHost(long gameHost) {
        this.gameHost = gameHost;
    }

    public int getRoundNumbers() {
        return roundNumbers;
    }

    public void setRoundNumbers(int roundNumbers) {
        this.roundNumbers = roundNumbers;
    }

    public int getPlayer1() {
        return player1;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public int getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(int currentWord) {
        this.currentWord = currentWord;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}
