package com.dell.codingassessment.hangman.game.api.dto;

public class Word {
    private String word;
    private int qty_letters;
    private String[] letters;

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

    public String[] getLetters() {
        return letters;
    }

    public void setLetters(String[] letters) {
        this.letters = letters;
    }
}
