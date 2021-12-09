package com.example.frontend.model;

/**
 * FriendRequest Class that holds all stored information about a FriendRequest
 * @author Cole Hunt and Benito Moeckly
 */
public class FriendRequest {

    public static final String d = "DENIED";
    public static final String p = "PENDING";
    public static final String a = "ACCEPTED";

    private int id;
    long userFrom;
    long userTo;
    String status;

    /**
     * Constructor for FriendRequest
     * @param userFrom User that sent FriendRequest
     * @param userTo User that the FriendRequest was sent
     */
    public FriendRequest(long userFrom, long userTo) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.status = p;
    }

    /**
     * Gets id associated with FriendRequest
     * @return FriendRequest's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id associated with FriendRequest
     * @param id new FriendRequest id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets User that sent FriendRequest
     * @return User that sent FriendRequest
     */
    public long getUserFrom() {
        return userFrom;
    }

    /**
     * Sets User that sent FriendRequest
     * @param userFrom new User that sent FriendRequest
     */
    public void setUserFrom(long userFrom) {
        this.userFrom = userFrom;
    }

    /**
     * Gets User the FriendRequest was sent to
     * @return User the FriendRequest was sent to
     */
    public long getUserTo() {
        return userTo;
    }

    /**
     * Sets User the FriendRequest was sent to
     * @param userTo new User the FriendRequest was sent to
     */
    public void setUserTo(long userTo) {
        this.userTo = userTo;
    }

    /**
     * Gets FriendRequest accept status
     * @return Friendship accept status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets FriendRequest as a String
     * @return From/To/Status associated with FriendRequest as a String
     */
    public String toString()
    {
        return "\nFrom: " + userFrom
                +"\nTo: " + userTo
                +"\nStatus" + status;
    }

}