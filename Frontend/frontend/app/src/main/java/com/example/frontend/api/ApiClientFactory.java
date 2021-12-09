package com.example.frontend.api;

import com.example.frontend.model.User;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Generates API Clients from model classes
 * @author Benito Moeckly
 */
public class ApiClientFactory {
    static Retrofit apiClientSeed = null;

    /**
     * Establishes connection to database and return client seed
     * @return apiClientSeed
     */
    static Retrofit GetApiClientSeed() {

        if (apiClientSeed == null) {
            apiClientSeed = new Retrofit.Builder()
                    .baseUrl("http://coms-309-004.cs.iastate.edu:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return apiClientSeed;
    }


    public static PostApi GetPostApi(){
        return GetApiClientSeed().create(PostApi.class);
    }

    /**
     * Gets User object from database
     * @return User object from database
     */
    public static UserApi GetUserApi(){
        return GetApiClientSeed().create(UserApi.class);
    }

    /**
     * Get Word object from database
     * @return Word object from database
     */
    public static WordApi GetWordApi(){return GetApiClientSeed().create(WordApi.class);}

}
