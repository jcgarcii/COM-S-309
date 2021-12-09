package com.example.frontend.api;

import com.example.frontend.model.Word;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * API connection for Word objects
 */
public interface WordApi {

    @GET("wordlist/all")
    Call<List<Word>> GetAllWords();

    @GET("wordlist/deleteById/{id}")
    void DeleteWordByPath(@Path ("id") int id );

    @POST("wordlist/post/{newWord}")
    void PostWordByPath (@Path("newWord") String newWord);

}
