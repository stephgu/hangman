/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.AudioClip;
import java.awt.*;
import java.util.ArrayList;

public class Hangman extends ConsoleProgram {
	
	/**
	 * Initializes canvas where man will be hanged. Adds canvas alongside the console program.
	 */
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}
	/**
	 * Initializes the word, then while it has not yet been guessed and if the number of guesses is not zero, 
	 * prints the word in the state of what the user has guessed, asks user to guess a letter, then checks to 
	 * see if that letter is in the word and updates the letter accordingly. Also adds starts the canvas where 
	 * the hangman will be drawn. TL;DR it's hangman. 
	 */
    public void run() {
    	bgmusic.loop();
    	println("It's time to play Hangman!");
    	intializeWord();
    	canvas.reset();
		while (notGuessed()) { 
			if (numGuesses <= 0) {
				println("You lose. The word was " + keyWord);
				break; 
			}
			printWord(); 
			askLetter();
			checkWordForLetter(keyWord.indexOf(letter));
		}
		restart();
	}
    
    /**
     * Gets the secret word and makes a copy of it that consists of only dashes 
     */
    private void intializeWord() {
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
    private boolean notGuessed() {
    	String test = "";
    	for (int i = 0; i < keyWord.length(); i++) {
    		test += keyWordCopy.get(i);
    	}
    	if (test.equals(keyWord)) {
    		printWord();
    		canvas.displayWord();
    		println("You got it!");
    		return false;
    	}
    	else return true; 
    }
    
    /**
     * Prints what the key word copy looks like by going through each box of the array and print that. 
     * Prints the word on the canvas side of the screen. Also prints number of guesses left. 
     */
    private void printWord() {
    	print("The word now looks like this: ");
    	for (int i = 0; i < keyWordCopy.size(); i++) {
    		print(keyWordCopy.get(i));
    	}
    	println();
    	println("You have " + numGuesses + " guesses left.");
    	canvas.displayWord();
    }
    
    /**
     * Prompts user for a letter. Turns letter to uppercase regardless of what user inputed. Changes multiLetter 
     * variable to false (details inside), and asks user to input again if their initial input was illegal. 
     */
    private void askLetter() {
    	letter = readLine("Guess a letter: ");
    	letter = letter.toUpperCase();
    	letterch = charLetter();
    	//every time a letter is asked, reset the variable that tells whether there are multiple letters or not
    	multiLetters = false; 
    	//what to do with illegal inputs (multiple letters or nothing entered)
    	if (letter.length() > 1 || letter.equals("")) {
    		println("That is an illegal guess.");
    		askLetter(); 
    	} else if (guessed.contains(letter)) {
    		println("You already guessed that letter.");
    		askLetter();
    	}
    	guessed.add(letter);
    }
    
    /**
     * Creates a character version of the letter so it is compatible with the noteIncorrectGuess() method of
     * the HangmanCanvas class. 
     * @return Character version of the most recent letter guessed. 
     */
    private char charLetter() {
    	return letter.charAt(0);
    }
    
    /**
     * Checks whether the key word contains the letter user entered starting from the index specified. 
     * If there is not, takes one guess away and notifies user that their letter does not appear in the key word
     * and notes the incorrect guess on the canvas by adding the correct body part. 
     * If there is, checks whether there are multiple appearances of this letter in the key word by adding 1 to the 
     * index where the previous instance of the letter was found and calling itself with that new index
     * to check whether there are any letters beyond that index. 
     * @param dex The index (or lack thereof in which case it is -1) of the letter
     */
    private void checkWordForLetter(int dex) {
    	if (dex == -1) {
    		//makes sure following commands aren't being run if just checking for multiple letters 
    		if (multiLetters == false) {
    		println("There are no " + letter + "'s in the word.");
    		canvas.noteIncorrectGuess(letterch);
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
    private void addLetterToCopy(int i) {
    	keyWordCopy.set(i, letter);
    }
    
    /**
     * Sets multiLetters to be true, indicating that the key word does indeed have multiple letters. 
     * Returns the index where the next multiple letter appears.  
     * @param tdex The index where the previous multiple letter appeared.
     * @return The index where the next multiple letter appears. 
     */
    private int dexOfMultipleLetters(int tdex) {
    	multiLetters = true; 
    	return (keyWord.indexOf(letter, ++tdex));
    }
    
    /**
     * Asks user if they want to restart. If yes, resets number of guesses, multiple letters, and clears keyWordCopy.
     * If no, print bye. Else, say it was an illegal response and ask to restart again. 
     */
    private void restart() {
    	String restart = readLine("Would you like to restart? ");
    	restart = restart.toUpperCase();
    	if (restart.charAt(0) == 'Y') {
    		numGuesses = 8;
    		multiLetters = false; 
    		guessed.clear();
    		keyWordCopy.clear();
    		run();
    	} else if (restart.charAt(0) == 'N') {
    		println("Thanks for playing Hangman!");
    	} else {
    		println("Illegal response.");
    		restart();
    	}
    }
    
    AudioClip bgmusic = MediaTools.loadAudioClip("lume.au");
    
    /* Private instance variables */
    private HangmanCanvas canvas; 
    HangmanLexicon lexi = new HangmanLexicon(); 
    RandomGenerator rgen = RandomGenerator.getInstance();
    
    static String keyWord;
    String letter; 
    char letterch;
    ArrayList<String> guessed = new ArrayList<String>();
    static ArrayList<String> keyWordCopy = new ArrayList<String>();
    
    int numGuesses = 8;
    boolean multiLetters = false; 
}
