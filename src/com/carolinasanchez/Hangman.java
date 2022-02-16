package com.carolinasanchez;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException { // if the file below isn't found, a file not found exception is thrown.

        Scanner keyboard = new Scanner(System.in);

        System.out.println("1 or 2 players?");
        String players = keyboard.nextLine();
        String word; // the word variable must be declared outside/above the if statement so that the rest of the main method (outside the if statement) can access it. it is later initialized with a value inside the if statement.

        if (players.equals("1")) {
            Scanner scanner = new Scanner(new File("C:\\Users\\Empre\\Downloads\\words_alpha.txt")); // Scanner input is this file full of words.

            List<String> words = new ArrayList<>(); // List of Strings declared.

            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            } // while there are more words (lines) in the scanner (file), add them to the List words.

            Random rand = new Random(); // Random class that randomizes declared.

            word = words.get(rand.nextInt(words.size())); // gets a word from the words List and initializes the String variable word with it. nextInt will be a random number between 0 and the size of the words List and will be the index of the randomly selected word.
        } else {
            System.out.println("Player 1, please enter your word:");
            word = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n");
            System.out.println("Ready for Player 2! Good luck!");
        } // SOLUTION FOR PLAYER 2 BEING ABLE TO READ PLAYER 1'S WORD IN THE CONSOLE: HAVE PLAYER 1 INPUT PLAYER 1'S GUESSES. AFTER ALL, THE PLAYER WHO COMES UP WITH THE WORD IS USUALLY THE ONE WHO WRITES THE LETTERS/DRAWS THE HANGMAN ON PAPER.

        // System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>(); // List of Characters declared.

        int wrongCount = 0;
        while(true) {

            printHangman(wrongCount); // calls our printHangman method. The wrongCount variable above is passed into it as an argument.

            if (wrongCount >= 6) {
                System.out.println("");
                System.out.println("Sorry, you lose!");
                System.out.println("The word was: " + word + ".");
                break;
            } // if the wrongCount variable is >= to 6 (and thus the hangman is fully printed) the player loses and these messages print to the console.

            checkWord(word, playerGuesses); // calls our checkWord method. The word and playerGuesses variables above are used as the arguments.

            if (!guessLetter(keyboard, word, playerGuesses)) {
               wrongCount++;
            }

            if (checkWord(word, playerGuesses)) {
                System.out.println("");
                System.out.println("");
                System.out.println("You win!");
                break;
            } // if checkWord returns true, which will only happen if the correctCount variable = the length of the word variable (meaning the player has guessed all the letters in the word and won the game), then stop the program.

            System.out.println("");
            System.out.println("Please enter your guess for the word:");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("");
                System.out.println("You win!");
                break;
            } else {
                System.out.println("Incorrect, try again.");
            } // if they guess the entire word correctly, they win. otherwise, they can enter a letter again.
        }
    }

    public static boolean checkWord(String word, List<Character> playerGuesses) { // checkWord method with word and playerGuesses parameters (not to be confused with the variables of the same name in the main method, which must be passed into this method as arguments when we call it in the main method).
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i)); // print instead of println since we want all Characters on the same line.
                correctCount++;
            } else {
                System.out.print("_"); // again, print instead of println!
            }
        } // if the playerGuesses List contains the character at index i of the word variable, print out that character. Otherwise, print out an underscore.

        return word.length() == correctCount; // if the correctCount equals the length of the word, returns true. otherwise, returns false.
    }

    public static boolean guessLetter(Scanner keyboard, String word, List<Character> playerGuesses) { // guessLetter method with keyboard, word, and playerGuesses parameters (not to be confused with the variables of the same name in the main method, which must be passed into this method as arguments when we call it in the main method).
        System.out.println("");
        System.out.println("Please enter a letter!");
        String letterGuess = keyboard.nextLine(); // initializes the letterGuess variable to the next line inputted by the user.
        playerGuesses.add(letterGuess.charAt(0)); // adds the very first character from the letterGuess variable to the playerGuesses array, since they only get to guess one letter at a time.

        return word.contains(letterGuess); // if the word contains the letter guessed, returns true. otherwise, returns false.
    }

    public static void printHangman(int wrongCount) { // printHangman method with wrongCount parameter (not to be confused with the variable of the same name in the main method, which must be passed into this method as arguments when we call it in the main method).
        System.out.println(" -------");
        System.out.println(" |     |");
        if(wrongCount >= 1) {
            System.out.println(" O");
        }
        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.print("/");
                System.out.println("");
            } else {
                System.out.println("");
            }
        } // print instead of println for 2 and 3 since there may be two arms on the same line.
        if (wrongCount >= 4) {
            System.out.println(" |");
        }
        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount == 6) {
                System.out.print("\\");
                System.out.println("");
            } else {
                System.out.println("");
            }
        } // print instead of println for 5 and 6 since there may be two legs on the same line.
    }
}
