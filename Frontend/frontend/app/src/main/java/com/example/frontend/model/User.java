package com.example.frontend.model;

import java.util.ArrayList;

/**
 * User Class that holds all stored information about a user such as login info
 * and friends/friendrequests
 * @author Cole Hunt and Benito Moeckly
 */
public class User {

    private int idNum;
    private String loginname;
    private String password;
    private String displayname;
    private final ArrayList<String> friendsList = new ArrayList<String>();
    private final ArrayList<Long> friendRequests = new ArrayList<Long>();

    /**
     * Class to represent a User
     */
    public User() {
    }

    /**
     * Gets username associated with User
     * @return
     */
    public String getLoginname(){
        return loginname;
    }

    /**
     * Sets username associated with User
     * @param loginname new User username
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * Gets password associated with User
     * @return User's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password associated with User
     * @param password new User password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets displayname associated with User
     * @return User's displayname
     */
    public String getDisplayname() {
        return displayname;
    }

    /**
     * Sets displayname associated with User
     * @param displayname new User displayname
     */
    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    /**
     * Gets database id associated with User
     * @return User's database id
     */
    public int  getIdNum(){return idNum;}


    /**
     * Gets basic user info as a printable string
     * @return String with the username, password, and displayname
     */
    public String printable(){
        return "\nID:   " + getIdNum()
                +"\nUsername:  "+ getLoginname()
                +"\nPassword:  "+getPassword()
                +"\nDisplayname:  "+getDisplayname()+"\n";
    }
}
