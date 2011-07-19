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
		while (notGuessed()) { 
			if (numGuesses < 0) break; 
			intializeWordCopy();
			printWord(); 
			askLetter();
			checkWordForLetter();
			
		}
	}
    
    public boolean notGuessed() {
    	return false; 
    }
    
    public void intializeWordCopy() {
    	for (int i = 0; i < keyWord.length(); i++) {
    		keyWordCopy.add("-");
    	}
    }
    
    public void printWord() {
    	println("The word now looks like this: " + keyWordCopy);
    }
    
    public void askLetter() {
    	println("You have " + numGuesses + " guesses left.");
    	letter = readLine("Guess a letter: ");
    	guessed.add(letter);
    	numGuesses--;
    }
    
    public void checkWordForLetter() {
    	int dex = keyWord.indexOf(letter);
    	if (dex == -1) {
    		println("There are no " + letter + "'s in the word.");
    	} else {
    		println("That guess is correct.");
    		addLetterToCopy();
    	}
    }
    
    public void addLetterToCopy() {
    	int i = keyWord.indexOf(letter);
    	keyWordCopy.set(i, letter);
    }
    
    /* Private instance variables */
    HangmanLexicon lexi = new HangmanLexicon(); 
    RandomGenerator rgen = RandomGenerator.getInstance();
    
    String keyWord = lexi.getWord(rgen.nextInt(0, lexi.getWordCount() - 1));
    String letter; 
    ArrayList<String> guessed = new ArrayList<String>();
    ArrayList<String> keyWordCopy = new ArrayList<String>();
    
    int numGuesses = 8;
}
