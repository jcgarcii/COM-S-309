package com.example.frontend.model;

/**
 * Friendship Class that holds all stored information about a Friend
 * @author Cole Hunt and Benito Moeckly
 */
public class Friendship {

    private Long id;
    private User user1;
    private User user2;

    /**
     * Gets id associated with Friendship
     * @return Friendship's id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get User1 associated with Friendship
     * @return User1 with Friendship
     */
    public User getUser1() {
        return user1;
    }

    /**
     * Get User2 associated with Friendship
     * @return User2 with Friendship
     */
    public User getUser2() {
        return user2;
    }
}
