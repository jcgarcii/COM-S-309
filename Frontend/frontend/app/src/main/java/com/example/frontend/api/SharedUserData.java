package com.example.frontend.api;

import com.example.frontend.model.User;

public class SharedUserData {

    private static SharedUserData instance;

    private User currentUser = null;

    public static SharedUserData getInstance(){
        if(instance == null) instance = new SharedUserData();
        return instance;
    }

    private SharedUserData(){}

    public void SetSharedUser(User user){
        this.currentUser = user;
    }

    public User GetSharedUser(){
        return this.currentUser;
    }

}


