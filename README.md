# hangman-api

## Overview

### About

This application consists of an REST API, which implements the Hangman Game.
It consists of a Java backend and a JavaScript frontend and will be submitted as coding assessment for Dell. 

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
4. It is important to run and maintain the execution of the backend before running the frontend.

### Backend building and running

* Compile and bundle the project:

```shell
mvn clean install
```

* Execute the tests:

```shell
mvn clean test
```

* Run the project:

```shell
mvn spring-boot:run
```

### Frontend building and running

* Access the ClientApp folder:

```shell
cd ClientApp
```

* Install the dependencies and build the project:

```shell
npm install    
```

* Run the project:

```shell
npm start
```

* The application will be automatically presented via your default web browser. If it doesn't happen, you can acess it at 
```text
http://localhost:3000
```


## How to play the Hangman Game:

