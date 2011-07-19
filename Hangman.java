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
			if (N_GUESSES < 0) break; 
			printWord(); 
			askLetter();
			checkWordForLetter();
			
		}
	}
    
    public boolean notGuessed() {
    	return false; 
    }
    
    public void printWord() {
    	print("The word now looks like this: ");
    	for (int i = 0; i < keyWord.length(); i++) {
    		print('-');
    	}
    	println();
    }
    
    public void askLetter() {
    	letter = readLine("Guess a letter: ");
    	guessed.add(letter);
    }
    
    public boolean checkWordForLetter() {
    	return false; 
    }
    /* Private instance variables */
    HangmanLexicon lexi = new HangmanLexicon(); 
    RandomGenerator rgen = RandomGenerator.getInstance();
    String keyWord = lexi.getWord(rgen.nextInt(0, lexi.getWordCount() - 1));
    ArrayList<String> guessed = new ArrayList<String>();
    String letter; 
    private static final int N_GUESSES = 8;
}
