# hangman-api

## Overview

### About

This application consists of a REST API, which implements the Hangman Game.
It consists of a Java backend and a JavaScript frontend and will be submitted as a coding assessment for Stefannini/Dell.

### Main technologies:

- Java Programming Language;
- Spring Framework;
- Spring Boot;
- JavaScript Language;
- ReactJs Framework.

## Build / Instructions

### Requirements

1. [Java 1.8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html);
2. [Maven](https://maven.apache.org/download.cgi);
3. [Node Package Manager (npm)](https://nodejs.org/en/download/);
4. Your preferred Text Editor or IDE;
5. It is required that the JAVA_HOME and PATH variables are correctly set. The Java bin folder path should be added to the PATH variable, meanwhile the root Java folder path should be added to the JAVA_HOME variable.
6. It is important to run and maintain the execution of the backend before running the frontend.


### Backend building and running

- Compiling and bundling the project:

```shell
mvn install
```

- Executing the tests:

```shell
mvn test
```

- Runnning the project:

```shell
mvn spring-boot:run
```
- NOTE: In Windows systems, it is possible that Java requires access to your private and public networks. Check both and allow it.


### Frontend building and running

- Accessing the ClientApp folder:

```shell
cd ClientApp
```

- Installing the dependencies and building the project:

```shell
npm install
```

- NOTE: If the installation fails with error E401, simply delete the package-lock.json file and run the command above again.

- Running the project:

```shell
npm start
```

- NOTE: In Windows systems, it is possible that Node.js requires access to your private and public networks. Just check both and allow it.

- The application should be automatically presented via your default web browser. If it doesn't happen, you can access it at

```text
http://localhost:3000
```

## Information about the Hangman Game user interface:

- This hangman game consists of a single page application containing:
1. Information about the game, such as the number of letters left to be found, the game's status, and the number of lives left. These are located right behind the title;
2. Restart game button;
3. Hangman image, which changes according to the number of lives left.
3. Panel with 26 alphabet letters;
4. Panel with input and button for guessing the entire word;
5. Panel with the already discovered incorrect letters;

## How to play the game

* Just load the page for the first time and a new game will be generated and prompted. Remember that if you simply reload the browser page, it will continue your current game, instead of creating a new one. When the game ends or every time you wish to restart, you should press the "Restart Game" button.

* Once the game has started, you must use either the left panel (Guess a letter) or the right panel (Guess the word) to try to guess a letter or the entire word.

* Guess a letter by simply clicking on its corresponding button on the left panel. Remember that:
1. In order to guess a letter, you should use the buttons on the LEFT panel. Trying to guess a letter using the input on the right panel will incur an attempt to guess the entire word.
2. The letters which were already pressed won't take any effects if pressed again, independently if they were guessed wrong or right.

* Guess the entire word by typing it on the input at the right panel and clicking the "Guess Word" button. Note that:
1. The word should be typed EXACTLY like the correct word. Typos will incur on the wrong answer.
2. The input is not case-sensitive.
3. Guessing the word correctly will finish the game with the status of "WIN"
4. Guessing the word wrongly will cause the game to finish and the Hangman to 'die', independent of the number of lives left, so be careful.

* The hangman character will be formed on the image above the word, in the following sequence:
1. The head;
2. The right arm;
3. The left arm;
4. The body;
5. The right leg;
6. The left leg.
* Once the Hangman is fully formed, the lives left number should be 0(zero) and the game is over. The word won't be revealed, so you can give it another try once it appears again ;).

* Once the word is guessed right or all its letters are guessed individually, the game will end, and you are the WINNER :D.