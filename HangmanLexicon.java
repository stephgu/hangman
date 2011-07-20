/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon {
	
	public HangmanLexicon() {
		try {
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			try {
				while(true) {
					String word = rd.readLine();
					if (word == null) break;
					dict.add(word);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return dict.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return dict.get(index);
	}
	ArrayList<String> dict = new ArrayList<String>();
}
