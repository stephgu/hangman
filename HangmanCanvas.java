/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import java.awt.*;

public class HangmanCanvas extends GCanvas {
	
	public void reset() {
		removeAll(); 
		addScaffold(); 
	}
	
	private double[] coor() {
		double x = getWidth()/2.0 - BEAM_LENGTH;
		double y = getHeight()*0.25;
		double endBeamX = x + BEAM_LENGTH;
		double endRopeY = y + ROPE_LENGTH;
		double[] result = {x, y, endBeamX, endRopeY};
		return result;
	}
	/** Resets the display so that only the scaffold appears */
	public void addScaffold() {
		double x = coor()[0];
		double y = coor()[1]; 
		double endBeamX = coor()[2];
		double endRopeY = coor()[3];                   
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
	public void displayWord(String word) {
		/* You fill this in */
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
		double x = getWidth()/.25;
		double y = getHeight()*0.25;
		guessed += letter; 
		GLabel guesslab = new GLabel(guessed);
		guesslab.setLocation(x, y + SCAFFOLD_HEIGHT + 200);
		guesslab.setFont(new Font("Serif", Font.BOLD, 20));
	}
	
	private void addBodyPart(int index) {
		switch (index) {
		case 0: addHead();
		case 1: addBody();
		case 2: addLeftArm();
		case 3: addRightArm();
		case 4: addLeftLeg();
		case 5: addRightLeg();
		case 6: addLeftFoot();
		case 7: addRightFoot(); 
		}
	}
	
	private void addHead() {
		double y = coor()[3]; 
		double x = getWidth()/2.0;
		GOval head = new GOval(HEAD_RADIUS*2, HEAD_RADIUS*2);
		head.setLocation(x - head.getWidth()/2.0, y);
		add(head);
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


}
