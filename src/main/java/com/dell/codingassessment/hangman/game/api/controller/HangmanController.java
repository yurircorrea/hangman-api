package com.dell.codingassessment.hangman.game.api.controller;

import com.dell.codingassessment.hangman.game.api.dto.Game;
import com.dell.codingassessment.hangman.game.api.dto.Word;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/hangman")
public class HangmanController {

    @GetMapping()
    public Game getHangmanGame(){
        Word w = new Word();
        String word = "ABACAXI";
        w.setWord(word.toUpperCase());
        w.setQty_letters(word.length());
        w.setLetters(word.split(""));

        Game g = new Game();
        g.setLifes_left(6);
        g.setLetters_left(word.length());
        g.setFinished(false);
        g.setWord(w);
        g.setIncorrect(null);

        return g;
    }

    @GetMapping(value = "/new_word", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getNewWorld(){
        return Collections.singletonMap("word", "ABACAXI");
    }

}
