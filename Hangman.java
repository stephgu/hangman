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
    	//println(keyWord);
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
    
    /**
     * Gets the secret word and makes a copy of it that consists of only dashes 
     */
    public void intializeWord() {
    	keyWord = lexi.getWord(rgen.nextInt(0, lexi.getWordCount() - 1));
    	for (int i = 0; i < keyWord.length(); i++) {
    		keyWordCopy.add("-");
    	}
    }
    
    /**
     * Takes every letter (String) in the key word copy and appends it to an empty string 
     * to create a string of the copy word. Compares this string to the actual key word string.  
     * @return true if the word has been guessed, false otherwise
     */
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
    
    /**
     * Prints what the key word copy looks like by going through each box of the array and print that. 
     * Also prints number of guesses left. 
     */
    public void printWord() {
    	print("The word now looks like this: ");
    	for (int i = 0; i < keyWordCopy.size(); i++) {
    		print(keyWordCopy.get(i));
    	}
    	println();
    	println("You have " + numGuesses + " guesses left.");
    }
    
    /**
     * Prompts user for a letter. Turns letter to uppercase regardless of what user inputed. Changes multiLetter 
     * variable to false (details inside), and asks user to input again if their initial input was illegal. 
     */
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
    
    /**
     * Checks whether the key word contains the letter user entered starting from the index specified. 
     * If there is not, takes one guess away and notifies user that their letter does not appear in the key word.
     * If there is, checks whether there are multiple appearances of this letter in the key word by adding 1 to the 
     * index where the previous instance of the letter was found and calling itself with that new index
     * to check whether there are any letters beyond that index. 
     * @param dex The index (or lack thereof in which case it is -1) of the letter
     */
    public void checkWordForLetter(int dex) {
    	if (dex == -1) {
    		if (multiLetters == false) {
    		println("There are no " + letter + "'s in the word.");
    		numGuesses--;
    		}
    	} else {
    		if (multiLetters == false) println("That guess is correct.");
    		addLetterToCopy(dex);
    		int newdex = dexOfMultipleLetters(dex);
    		checkWordForLetter(newdex);
    	}
    }
    
    /**
     * Sets the most recent letter entered by user to the specified index
     * @param i The specified index of where the letter should go in the key word copy 
     */
    public void addLetterToCopy(int i) {
    	keyWordCopy.set(i, letter);
    }
    
    /**
     * Sets multiLetters to be true, indicating that the key word does indeed have multiple letters. 
     * Returns the index where the next multiple letter appears.  
     * @param tdex The index where the previous multiple letter appeared.
     * @return The index where the next multiple letter appears. 
     */
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
