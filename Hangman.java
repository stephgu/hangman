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
			if (numGuesses <= 0) break; 
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
    	if (test.equals(keyWord)) return false;
    	else return true; 
    }
    
    public void printWord() {
    	print("The word now looks like this: ");
    	for (int i = 0; i < keyWordCopy.size(); i++) {
    		print(keyWordCopy.get(i));
    	}
    	println();
    }
    
    public void askLetter() {
    	println("You have " + numGuesses + " guesses left.");
    	letter = readLine("Guess a letter: ");
    	letter = letter.toUpperCase();
    	guessed.add(letter);
    }
    
    public void checkWordForLetter() {
    	int dex = keyWord.indexOf(letter);
    	if (dex == -1) {
    		println("There are no " + letter + "'s in the word.");
    		numGuesses--;
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
    
    String keyWord;
    String letter; 
    ArrayList<String> guessed = new ArrayList<String>();
    ArrayList<String> keyWordCopy = new ArrayList<String>();
    
    int numGuesses = 8;
}
