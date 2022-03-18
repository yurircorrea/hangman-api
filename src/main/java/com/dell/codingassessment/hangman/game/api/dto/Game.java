package com.dell.codingassessment.hangman.game.api.dto;

public class Game {
    private int lifes_left;
    private boolean finished;
    private int letters_left;
    private String[] incorrect;
    private Word word;

    public int getLifes_left() {
        return lifes_left;
    }

    public void setLifes_left(int lifes_left) {
        this.lifes_left = lifes_left;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getLetters_left() {
        return letters_left;
    }

    public void setLetters_left(int letters_left) {
        this.letters_left = letters_left;
    }

    public String[] getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(String[] incorrect) {
        this.incorrect = incorrect;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
