package com.dell.codingassessment.hangman.game.api.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable{
    private int lifes_left;
    private boolean finished;
    private int letters_left;
    private ArrayList<String> incorrect;
    private ArrayList<String> correct;
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

    public ArrayList<String> getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(ArrayList<String> incorrect) {
        this.incorrect = incorrect;
    }

    public ArrayList<String> getCorrect() {
        return correct;
    }

    public void setCorrect(ArrayList<String> correct) {
        this.correct = correct;
    }


    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
