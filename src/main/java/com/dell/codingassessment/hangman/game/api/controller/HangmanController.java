package com.dell.codingassessment.hangman.game.api.controller;

import com.dell.codingassessment.hangman.game.api.dto.Game;
import com.dell.codingassessment.hangman.game.api.dto.Word;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@CrossOrigin
@RestController
@RequestMapping("/hangman")
public class HangmanController {

    //Retrieves the list of words from the XML file
    //The XML file should be present at 'src/main/resources/words.xml'
    //If the file isn't present at the given path, exception will be thrown
    private List<String> getListofWords(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("src/main/resources/words.xml"));

            NodeList list = doc.getElementsByTagName("word");

            List<String> wordsList = new ArrayList<String>();

            for(int i = 0; i < list.getLength(); i++){
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    wordsList.add(element.getTextContent());
                }
            }

            return wordsList;

        }catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Generates a random word from a given words list
    private String getNewRandomWord(List<String> wordsList){
        try{
            Random r = new Random();
            int randomIndex = r.nextInt(wordsList.size());
            return wordsList.get(randomIndex);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private boolean saveGame(Game g){
        File arq = new File("src/main/resources/game");
        try{
            arq.delete();
            arq.createNewFile();

            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arq));
            obj.writeObject(g);
            obj.close();
        }catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Game retrieveGame(){
        Game g = new Game();
        try{
            File arq = new File("src/main/resources/game");
            if(!arq.exists()){
                return null;
            }
            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(arq));
            g = (Game) obj.readObject();
            obj.close();

            return g;
        }catch(IOException e1) {
            e1.printStackTrace();
            return null;
        } catch(ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @GetMapping()
    public Game getHangmanGame(){

        Game savedGame = new Game();

        //Check if there is a savedGame in 'src/main/resources/game'
        //In case the file doesn't exist, we will assume there is no game (file will be created at game saving)
        savedGame = this.retrieveGame();
        if(savedGame != null){
            return savedGame;
        }

        //In case the game doesn't exist or it is finished, we will create a new one and return (old finished game will be replaced on new game saving)
        
        //Getting list of words from 'src/main/resources/words.xml' (if the file doesn't exist, it will throw an exception)
        List<String> wordsList = this.getListofWords();
        
        //If there is a wordsList, we ask for a random word from the list
        String randomWord = "";
        if(wordsList != null && !wordsList.isEmpty()){
            randomWord = this.getNewRandomWord(wordsList);
        }

        //Creating the Word object
        Word newWord = new Word();
        newWord.setWord(randomWord.toUpperCase());
        newWord.setQty_letters(randomWord.length());
        ArrayList<String> letters = new ArrayList<String>(Arrays.asList(randomWord.split("")));
        newWord.setLetters(letters);

        //Creating the Game object
        Game newGame = new Game();
        newGame.setLifes_left(6);
        newGame.setWord(newWord);
        newGame.setLetters_left(newGame.getWord().getQty_letters());
        newGame.setFinished(false);
        newGame.setIncorrect(new ArrayList<String>());
        newGame.setCorrect(new ArrayList<String>());

        //Saving the game in the 'src/main/resources/game' file
        boolean saved = this.saveGame(newGame);

        //Checking if the game was sucessfully saved
        if(saved){
            //If the game was saved, we return it
            return newGame;
        }

        //If we have any problem either at retrieving or creating a game, we return null
        return null;
    }

    private int checkLetter(Word word, String letter){
        ArrayList<String> wordLetters = word.getLetters();

        int occurrences = 0;

        for (String let : wordLetters) {
            if (letter.equals(let)){
                occurrences++;
            }
        }

        return occurrences;        
    }


    private Game incorrectLetterGuess(Game game, String letter){
        game.setLifes_left(game.getLifes_left() - 1);  
        ArrayList<String> incorrect = game.getIncorrect();
        incorrect.add(letter);
        game.setIncorrect(incorrect);
        
        boolean ok =  this.saveGame(game);

        if(!ok){
            return null;
        }

        return game;
    }

    private Game correctLetterGuess(Game game, String letter, int occurrences){
        game.setLetters_left(game.getLetters_left() - occurrences);
        ArrayList<String> correct = game.getCorrect();
        correct.add(letter);
        game.setCorrect(correct);

        boolean ok = this.saveGame(game);

        if(!ok){
            return null;
        }

        return game;
    }

    private Game checkendGame(Game game){
        if(game.getLifes_left() <= 0 || game.getLetters_left() <= 0){
            game.setFinished(true);
        }

        boolean ok = this.saveGame(game);

        if(!ok){
            return null;
        }

        return game;
    }


    @GetMapping(value = "/guess_letter/{letter}")
    public Game guessLetter(@PathVariable String letter){
        
        Game game = this.getHangmanGame();

        if(letter == null){
            return game;
        }

        if(game.isFinished()){
            return game;
        }

        letter = letter.toUpperCase();

        if(!game.getCorrect().isEmpty() && game.getCorrect().contains(letter)){
            return game;
        }

        if(!game.getIncorrect().isEmpty() && game.getIncorrect().contains(letter)){
            return game;
        }

        int occurrences = this.checkLetter(game.getWord(), letter);

        if(occurrences > 0){
            game = this.correctLetterGuess(game, letter, occurrences);
        }else{
            game = this.incorrectLetterGuess(game, letter);
        }

        game = checkendGame(game);

        if (game == null){
            return this.getHangmanGame();
        }
        
        return game;
    }

    @GetMapping(value = "reset_game")
    public Game resetGame(){
        Game game = retrieveGame();
        if(game != null){
            File arq = new File("src/main/resources/game");
            try{
                arq.delete();
            }catch(Exception e) {
                e.printStackTrace();
                return game;
            }
            
            return this.getHangmanGame();
        }
        return this.getHangmanGame();
    }

}
