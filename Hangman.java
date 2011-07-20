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
			checkWordForLetter(keyWord.indexOf(letter));
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
    		printWord();
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
    	//every time a letter is asked, reset the variable that tells whether there are multiple letters or not
    	multiLetters = false; 
    	//what to do with illegal inputs (multiple letters or nothing entered)
    	if (letter.length() > 1 || letter.equals("")) {
    		println("That is an illegal guess.");
    		askLetter(); 
    	}
    	guessed.add(letter);
    }
    
    public void checkWordForLetter(int dex) {
    	if (dex == -1) {
    		if (multiLetters == false) {
    		println("There are no " + letter + "'s in the word.");
    		numGuesses--;
    		}
    	} else {
    		println("That guess is correct.");
    		addLetterToCopy(dex);
    		int newdex = dexOfMultipleLetters(dex);
    		checkWordForLetter(newdex);
    	}
    }
    
    public void addLetterToCopy(int i) {
    	keyWordCopy.set(i, letter);
    }
    
    public int dexOfMultipleLetters(int tdex) {
    	multiLetters = true; 
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
    boolean multiLetters = false; 
}
