import React, { useEffect, useState } from 'react';
import api from '../../services/api';

import './Word.css';

const Word = () => {

  const [game, setGame] = useState();

  useEffect(()=>{
    api
      .get("/hangman")
      .then((response) => setGame(response.data))
      .catch((err) => {
        console.error("Erro: " + err);
      })  
  }, []);

const hangmanImage = "../../"+game?.lifes_left+".png"

const alphabet = [
  "A", "B", "C", "D", "E",
  "F", "G", "H", "I", "J",
  "K", "L", "M", "N", "O", 
  "P", "Q", "R", "S", "T", 
  "U", "V", "W", "X", "Y", "Z",
]

function actionLetter(letter){
  api
    .get("/hangman/guess_letter/"+letter)
    .then((response) => setGame(response.data))
    .catch((err) => {
      console.error("Erro: " + err);
  }) 
}

const actionWord = (event) => {
  event.preventDefault();
  const word = event.target.word_guess;
  api
    .get("/hangman/guess_word/"+word.value)
    .then((response) => setGame(response.data))
    .catch((err) => {
      console.error("Erro: " + err);
  }) 
}

function restartButton(){
  api
    .get("/hangman/reset_game")
    .then((response) => setGame(response.data))
    .catch((err) => {
      console.error("Erro: " + err);
  }) 
}

  return(
    <>
      <div className="game-info">
        Letters Left: {game?.letters_left}  |  Status: {game?.lifes_left > 0?"Alive":"Game Over"} {game?.letters_left === 0?"(WIN)":""}  |  Lives Left: {game?.lifes_left}
      </div>
      <div className="restart-button">
        <br /><button type="button" key="restart" onClick={() => restartButton()}  className="btn btn-info">Restart Game</button>   
      </div>
      <div className="hangman-image">
        <img alt="hangman" src={hangmanImage}></img>
      </div>
      <div className="main-word">
        {game?.word?.letters.map((letter, index) => {
          return(
            <div className="letter-box" key={index}>
              {game?.correct.indexOf(letter) > -1?
                <span className="letter-text">{letter}</span>  
              :
                <span>*</span>}
            </div>
          );
        })}
      </div> 
      <div className="keyboard">
      <h6 className="display-6">Guess a letter:</h6> <br /> 
        {alphabet?.map((letter, index) => {
          return(
            <><button type="button" key={index} onClick={() => actionLetter(letter)} className="btn btn-warning">{letter}</button> <span></span></>
          );
        })}
      </div>
      <div className="word-input">
      <h6 className="display-6">Guess the word:</h6> <br /> 
      <form noValidate onSubmit={actionWord}>
        <input type="text" className="form-control" id="word_guess" /><br />   
        <button type="submit" key="word_guess"  className="btn btn-warning">Guess Word</button>
      </form>      
      </div>
      <div className="incorrect-letters">
        <h6 className="display-6">Incorrect Letters:</h6>
        {game?.incorrect.map((letter, index) => {
          return(
            <span key={index}>{letter} - </span>
          );
        })}
      </div>
    </>
  );
  
  };

export default Word;
