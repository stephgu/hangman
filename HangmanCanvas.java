/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.ArrayList;

public class HangmanCanvas extends GCanvas {
	
	public void reset() {
		removeAll(); 
		bodyCount = 0;
		initializeStartGraphics();
	}
	
	private void initializeStartGraphics() {
		//init scaffold
		addScaffold(); 
		//Init body parts coordinates 
		double y = getHeight()*0.25 + ROPE_LENGTH;
		double x = getWidth()/2.0;
		head = new GOval(HEAD_RADIUS*2, HEAD_RADIUS*2);
		head.setLocation(x - head.getWidth()/2.0, y);
		double startBodyY = y + head.getHeight();
		double bodyEndY = startBodyY + BODY_LENGTH;
		double leftarmEndX = x - UPPER_ARM_LENGTH;
		double armY = startBodyY + ARM_OFFSET_FROM_HEAD;
		double rightarmEndX = x + UPPER_ARM_LENGTH;
		double hipStartX = x - HIP_WIDTH/2.0;
		double hipEndX = hipStartX + HIP_WIDTH;
		double leftlegEndY = bodyEndY + LEG_LENGTH;
		//Init body parts 
		body = new GLine(x, startBodyY, x, bodyEndY);
		hips = new GLine(hipStartX, bodyEndY, hipEndX, bodyEndY);
		upleftarm = new GLine(x, armY, leftarmEndX, armY);
		lowleftarm = new GLine(leftarmEndX, armY, leftarmEndX, armY + LOWER_ARM_LENGTH);
		uprightarm = new GLine(x, armY, rightarmEndX, armY);
		lowrightarm = new GLine(rightarmEndX, armY, rightarmEndX, armY + LOWER_ARM_LENGTH);
		leftleg = new GLine(hipStartX, bodyEndY, hipStartX, leftlegEndY);
		rightleg = new GLine(hipEndX, bodyEndY, hipEndX, leftlegEndY);
		leftfoot = new GLine(hipStartX, leftlegEndY, hipStartX - FOOT_LENGTH, leftlegEndY);
		rightfoot = new GLine(hipEndX, leftlegEndY, hipEndX + FOOT_LENGTH, leftlegEndY);
		//Labels 
		wordlab = new GLabel(word);
		wordlab.setLocation(getWidth()*.25, leftlegEndY + 50);
		wordlab.setFont(new Font("Serif", Font.BOLD, 30));
		guesslab = new GLabel("Letters guessed: ", wordlab.getX(), wordlab.getY() + wordlab.getHeight() + 10);
		guesslab.setFont(new Font("Serif", Font.PLAIN, 20));
		add(guesslab);
		add(wordlab);
		//display word 
		displayWord();
		
	}
	
	/** Resets the display so that only the scaffold appears */
	private void addScaffold() {
		double x = getWidth()/2.0 - BEAM_LENGTH;
		double y = getHeight()*0.25;
		double endBeamX = x + BEAM_LENGTH;
		double endRopeY = y + ROPE_LENGTH;                   
		GLine scaffpole = new GLine(x, y, x, y + SCAFFOLD_HEIGHT);
		GLine scaffbeam = new GLine(x, y, endBeamX, y);
		GLine rope = new GLine(endBeamX, y, endBeamX, endRopeY);
		add(scaffpole);
		add(scaffbeam);
		add(rope);
	}
	
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord() {
		updateWord();
		wordlab.setLabel(word);
		add(wordlab);
	}
	
	private void updateWord() {
		word = "";
		ArrayList<String> temp = Hangman.keyWordCopy;
		for (int i = 0; i < temp.size(); i++) {
			word += temp.get(i);
		}
		System.out.print("Secret word is: " + word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		displayLetter(letter); 
		addBodyPart(bodyCount++);
	}
	
	private void displayLetter(char letter) {
		guessed += letter; 
		guesslab.setLabel("Letters guessed: " + guessed);
		add(guesslab);
	}
	
	private void addBodyPart(int index) {
		switch (index) {
		case 0: 
			add(head);
			break;
		case 1:
			add(body);
			add(hips);
			break;
		case 2: 
			add(upleftarm);
			add(lowleftarm);
			break;
		case 3: 
			add(uprightarm);
			add(lowrightarm);
			break;
		case 4: 
			add(leftleg);
			break;
		case 5: 
			add(rightleg);
			break;
		case 6: 
			add(leftfoot);
			break;
		case 7: 
			add(rightfoot);
			break;
		default: 
			System.out.print("NO MORE BODY PARTS TO ADD MUTHA FUCKAAAAAAA");
		}
	}
	

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	int bodyCount = 0; 
	String guessed = ""; 
	String word = "";
	
	/* Private vars */
	GOval head; 
	GLine body; 
	GLine hips;
	GLine upleftarm;
	GLine lowleftarm;
	GLine uprightarm;
	GLine lowrightarm;
	GLine leftleg;
	GLine rightleg;
	GLine leftfoot;
	GLine rightfoot;
	GLabel guesslab;
	GLabel wordlab;
}
