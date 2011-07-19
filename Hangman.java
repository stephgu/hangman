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
		while (notGuessed()) { 
			if (numGuesses < 0) break; 
			printWord(); 
			askLetter();
			checkWordForLetter();
		}
	}
    
    public boolean notGuessed() {
    	keyWord = lexi.getWord(rgen.nextInt(0, lexi.getWordCount() - 1));
    	for (int i = 0; i < keyWord.length(); i++) {
    		if (keyWordCopy.get(i).equals(keyWord.charAt(i)) == false) return true;
    	}
    	return false; 
    }
    
    public void intializeWord() {
    	for (int i = 0; i < keyWord.length(); i++) {
    		keyWordCopy.add("-");
    	}
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
    	guessed.add(letter);
    	numGuesses--;
    }
    
    public void checkWordForLetter() {
    	if (keyWord.contains(letter)) {
    		println("That guess is correct.");
    		addLetterToCopy();
    	} else {
    		println("There are no " + letter + "'s in the word.");
    	}
    }
    
    public void addLetterToCopy() {
    	int i = keyWord.indexOf(letter);
    	keyWordCopy.set(i, (char) letter);
    }
    
    /* Private instance variables */
    HangmanLexicon lexi = new HangmanLexicon(); 
    RandomGenerator rgen = RandomGenerator.getInstance();
    
    String keyWord;
    String letter; 
    ArrayList<String> guessed = new ArrayList<String>();
    ArrayList<Character> keyWordCopy = new ArrayList<Character>();
    
    int numGuesses = 8;
}
