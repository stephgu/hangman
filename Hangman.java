/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.ArrayList;

public class Hangman extends ConsoleProgram {

    public void run() {
    	intializeWord();
    	println(keyWord);
		while (notGuessed()) { 
			if (numGuesses <= 0) {
				println("You lose. The word was " + keyWord);
				break; 
			}
			printWord(); 
			askLetter();
			checkWordForLetter();
		}
	}
    
    public void intializeWord() {
    	keyWord = lexi.getWord(rgen.nextInt(0, lexi.getWordCount() - 1));
    	for (int i = 0; i < keyWord.length(); i++) {
    		keyWordCopy.add("-");
    	}
    }
    
    public boolean notGuessed() {
    	String test = "";
    	for (int i = 0; i < keyWord.length(); i++) {
    		test += keyWordCopy.get(i);
    	}
    	if (test.equals(keyWord)) {
    		println("You got it!");
    		return false;
    	}
    	else return true; 
    }
    
    public void printWord() {
    	print("The word now looks like this: ");
    	for (int i = 0; i < keyWordCopy.size(); i++) {
    		print(keyWordCopy.get(i));
    	}
    	println();
    	println("You have " + numGuesses + " guesses left.");
    }
    
    public void askLetter() {
    	letter = readLine("Guess a letter: ");
    	letter = letter.toUpperCase();
    	if (letter.length() > 1) {
    		println("That is an illegal guess.");
    		askLetter(); 
    	}
    	guessed.add(letter);
    }
    
    public void checkWordForLetter() {
    	int dex = keyWord.indexOf(letter);
    	if (dex == -1) {
    		println("There are no " + letter + "'s in the word.");
    		numGuesses--;
    	} else {
    		println("That guess is correct.");
    		addLetterToCopy(dex);
    	}
    }
    
    public void addLetterToCopy(int i) {
    	while (keyWordCopy.get(i).equals(letter)) {
    		i = checkForDoubleLetter(i);
    	}
    	keyWordCopy.set(i, letter);
    }
    
    public int checkForDoubleLetter(int tdex) {
    	return (keyWord.indexOf(letter, ++tdex));
    }
    
    /* Private instance variables */
    HangmanLexicon lexi = new HangmanLexicon(); 
    RandomGenerator rgen = RandomGenerator.getInstance();
    
    String keyWord;
    String letter; 
    ArrayList<String> guessed = new ArrayList<String>();
    ArrayList<String> keyWordCopy = new ArrayList<String>();
    
    int numGuesses = 8;
}
