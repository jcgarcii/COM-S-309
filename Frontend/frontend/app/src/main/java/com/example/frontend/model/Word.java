package com.example.frontend.model;

import static com.example.frontend.api.ApiClientFactory.GetWordApi;

import com.example.frontend.api.SlimCallback;
import com.example.frontend.api.WordApi;

import java.util.List;
import java.util.Random;

/**
 * Word Class that holds all stored information about a Word
 * @author Cole Hunt and Benito Moeckly
 */
public class Word {


    long id;
    String word;

    /**
     * Gets the database ID for the current Word
     * @return Word's database id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets word associated with Word
     * @return Word's word
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets word associated with Word
     * @param word new Word's word
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Gets word associated with Word as String
     * @return Word's word as String
     */
    public String toString()
    {
        return "\n"+word;
    }

}
