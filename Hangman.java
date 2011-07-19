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

public class Hangman extends ConsoleProgram {

    public void run() {
		while (notGuessed()) { 
			if (N_GUESSES < 0) break; 
			printWord(); 
			char = askLetter();
			boolean letterInWord = checkWordForLetter();
			
		}
	}

    public void printWord() {
    	for (int i = 0; i < keyWord.length(); i++) {
    		
    	}
    }
    /* Private instance variables */
    HangmanLexicon lexi = new HangmanLexicon(); 
    RandomGenerator rgen = RandomGenerator.getInstance();
    String keyWord = lexi.getWord(rgen.nextInt(0, lexi.getWordCount() - 1));
    char letter; 
    private static final int N_GUESSES = 8;
}
