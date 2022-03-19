package com.dell.codingassessment.hangman.game.api.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class Word implements Serializable{
    private String word;
    private int qty_letters;
    private ArrayList<String> letters;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getQty_letters() {
        return qty_letters;
    }

    public void setQty_letters(int qty_letters) {
        this.qty_letters = qty_letters;
    }

    public ArrayList<String> getLetters() {
        return letters;
    }

    public void setLetters(ArrayList<String> letters) {
        this.letters = letters;
    }
}
