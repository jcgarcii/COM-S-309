package coms309.roundtrip.backend.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String word;

    public Word()
    {}


    public Word(String word)
    {
        this.word = word;
    }
    public String getWord()
    {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setId(int newid)
    {
        this.id = newid;
    }
    private boolean stringArrayContains(String param, String[] list)
    {
        for(int i = 0; i < list.length; i++)
        {
            if(list[i] == param){return true;}
        }
        return false;
    }
}
