package coms309.roundtrip.backend.controllers;


import coms309.roundtrip.backend.model.Word;
import coms309.roundtrip.backend.repository.WordRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@Api(value = "Swagger2DemoRestController", description = "Rest controller API for the word prompts in our drawing game")
@RestController
public class WordController {

    @Autowired
    WordRepository wordRepo;

    @ApiOperation(value = "Get a list of all the words in the database. ",
                    response = Iterable.class, tags = "getAllWords")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 404, message = "not found!")
    })
    @GetMapping("wordlist/all")
    List<Word> GetAllWords(){
        return wordRepo.findAll();
    }

    @ApiOperation(value = "This controller lets you post a new word to the database ",
            response = Word.class, tags = "postWordByPath")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 404, message = "not found!")
    })
    @PostMapping("wordlist/post/{newWord}")
    Word PostWordByPath(@PathVariable String newWord)
    {
        Word word = new Word(newWord);
        wordRepo.save(word);
        return word;
    }

    @ApiOperation(value = "This controller lets you delete a word form the database ",
            response = void.class, tags = "DeleteWordByPath")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 404, message = "not found!")
    })
    @PostMapping("wordlist/deleteById/{id}")
    void DeleteWordByPath(@PathVariable int id )
    {
        wordRepo.deleteById(id);
    }

}
