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
4. Your preffered Text Editor or IDE;
5. It is required that the JAVA_HOME and PATH variables are correctly set;
6. It is important to run and maintain the execution of the backend before running the frontend.


### Backend building and running

- Compile and bundle the project:

```shell
mvn install
```

- Execute the tests:

```shell
mvn test
```

- Run the project:

```shell
mvn spring-boot:run
```
- NOTE: In Windows systems, it is possible that Java requires access to your private and public networks. Check both and allow it.


### Frontend building and running

- Access the ClientApp folder:

```shell
cd ClientApp
```

- Installing the dependencies and building the project:

```shell
npm install
```

- NOTE: If the installation fails with error E401, simply delete the package-lock.json file and run the command above again.

- Run the project:

```shell
npm start
```

- NOTE: In Windows systems, it is possible that Node.js requires access to your private and public networks. Just check both and allow it.

- The application should be automatically presented via your default web browser. If it doesn't happen, you can acess it at

```text
http://localhost:3000
```

## How to play the Hangman Game:
