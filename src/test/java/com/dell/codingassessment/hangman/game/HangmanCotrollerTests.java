package com.dell.codingassessment.hangman.game;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dell.codingassessment.hangman.game.api.controller.HangmanController;
import com.dell.codingassessment.hangman.game.api.dto.Game;
import com.dell.codingassessment.hangman.game.api.dto.Word;

@SpringBootTest
class HangmanCotrollerTests {

	public static final HangmanController controller = new HangmanController();

	private Game setup(String word){

		Word newWord = new Word();
        newWord.setWord(word);
        newWord.setQty_letters(word.length());
        ArrayList<String> letters = new ArrayList<String>(Arrays.asList(word.split("")));
        newWord.setLetters(letters);


		Game newGame = new Game();
        newGame.setLifes_left(6);
        newGame.setWord(newWord);
        newGame.setLetters_left(newGame.getWord().getQty_letters());
        newGame.setFinished(false);
        newGame.setIncorrect(new ArrayList<String>());
        newGame.setCorrect(new ArrayList<String>());

		return newGame;
	}

	@Test
	void getListofWords_should_return_one_word() {
		List<String> list = controller.getListofWords();

		assertEquals(list.get(0), "DELL");
	}

	@Test
	void getNewRandomWord_should_return_one_word(){
		List<String> wordsList = new ArrayList<String>();
		wordsList.add("DELL");

		String test = controller.getNewRandomWord(wordsList);

		assertEquals(test, "DELL");
		assertNotNull(test);
	}

	@Test
	void getNewRandomWord_should_throw_exception() {
		
		List<String> wordsList = new ArrayList<String>();

		String test = controller.getNewRandomWord(wordsList);

		assertNull(test);
	}

	@Test
	void saveGame_should_return_true(){

		Game game = new Game();
		
		boolean test = controller.saveGame(game);

		assertTrue(test);

	}

	@Test
	void retrieveGame_should_return_game(){
		Game test = controller.retrieveGame();

		assertNotNull(test);
	}

	@Test
	void getHangmanGame_should_return_game(){
		Game test = controller.getHangmanGame();

		assertNotNull(test);
	}

	@Test
	void checkLetter_should_return_value_greater_then_zero(){
		String letter = "P";
		Word w = new Word();
		ArrayList<String> letters = new ArrayList<String>();
		letters.add("P");
		letters.add("Q");
		letters.add("R");
		w.setLetters(letters);

		int occurrences = controller.checkLetter(w, letter);

		assertEquals(occurrences, 1);
	}

	@Test
	void checkLetter_should_return_zero(){
		String letter = "T";
		Word w = new Word();
		ArrayList<String> letters = new ArrayList<String>();
		letters.add("P");
		letters.add("Q");
		letters.add("R");
		w.setLetters(letters);

		int occurrences = controller.checkLetter(w, letter);

		assertEquals(occurrences, 0);
	}

	@Test
	void incorrectLetterGuess_should_return_game(){
		Game game = new Game();
		game.setIncorrect(new ArrayList<String>());
		game.setLifes_left(6);
		String letter = "B";

		Game test = controller.incorrectLetterGuess(game, letter);
		assertNotNull(test);
	}

	@Test
	void correctLetterGuess_should_return_game(){
		Game game = new Game();
		game.setCorrect(new ArrayList<String>());
		game.setLetters_left(6);
		String letter = "B";

		Game test = controller.correctLetterGuess(game, letter, 1);
		assertNotNull(test);
	}


	@Test
	void checkendGame_should_return_game(){
		Game game = new Game();
		game.setLetters_left(6);
		game.setLifes_left(0);

		Game test = controller.checkendGame(game);
		assertNotNull(test);
	}


	@Test
	void guessWord_should_return_game(){
		String word = "blah";
		Game test = controller.guessWord(word);

		assertNotNull(test);
	}

	@Test
	void guessWord_should_return_game_null_word(){
		String word = null;
		Game test = controller.guessWord(word);

		assertNotNull(test);
	}

	@Test
	void guessWord_should_return_finished_game_after_successful_word_guess(){
		Game game = setup("BLAH");

		controller.saveGame(game);

		game = controller.guessWord("blah");

		assertTrue(game.isFinished());
	}

	@Test
	void guessWord_should_return_finished_game_after_unsuccessful_word_guess(){
		Game game = setup("BLAH");

		controller.saveGame(game);

		game = controller.guessWord("bleh");

		assertTrue(game.isFinished());
	}

	@Test
	void guessLetter_should_return_finished_game_after_succesful_letter_guess(){
		Game game = setup("A");

		controller.saveGame(game);

		game = controller.guessLetter("A");

		assertTrue(game.isFinished());
	}

	@Test
	void guessLetter_should_return_unfinished_game_after_succesful_letter_guess(){
		Game game = setup("AB");

		controller.saveGame(game);

		game = controller.guessLetter("A");

		assertFalse(game.isFinished());
	}
	
	@Test
	void guessLetter_should_return_finished_game_after_unsuccesful_letter_guess(){
		Game game = setup("A");
		game.setLifes_left(1);

		controller.saveGame(game);

		game = controller.guessLetter("B");

		assertTrue(game.isFinished());
	}

	@Test
	void guessLetter_should_return_unfinished_game_after_unsuccesful_letter_guess(){
		Game game = setup("A");

		controller.saveGame(game);

		game = controller.guessLetter("B");

		assertFalse(game.isFinished());
	}

	@Test
	void guessLetter_should_return_game_after_already_guessed_correct_letter(){
		Game game = setup("BLAH");
		ArrayList<String> correct = new ArrayList<String>(Arrays.asList("B".split("")));
		game.setCorrect(correct);

		controller.saveGame(game);

		game = controller.guessLetter("B");

		assertNotNull(game);;
	}

	@Test
	void guessLetter_should_return_game_after_already_guessed_incorrect_letter(){
		Game game = setup("BLAH");
		ArrayList<String> incorrect = new ArrayList<String>(Arrays.asList("T".split("")));
		game.setIncorrect(incorrect);

		controller.saveGame(game);

		game = controller.guessLetter("T");

		assertNotNull(game);;
	}
	
	@Test
	void guessLetter_receiving_null_string_should_return_game(){
		String letter = null;
		Game test = controller.guessLetter(letter);

		assertNotNull(test);
	}

	@Test
	void resetGame_should_return_game(){
		Game test = controller.resetGame();

		assertNotNull(test);
	}


}
