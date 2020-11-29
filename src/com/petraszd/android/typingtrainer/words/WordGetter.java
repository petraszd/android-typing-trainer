package com.petraszd.android.typingtrainer.words;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;


public class WordGetter {
	
	private int mLength = 0;
	private String[] mWords;
	private Random mRandom;
	
	public WordGetter(int length) {
		mLength = length;
		mWords = readWords();
		mRandom = new Random();
	}
	
	public String getRandom(List<Character> notStartingWith) {
		while (true) {
			String word = getRandom();
			if (startsWithAny(word, notStartingWith)) {
				continue;
			}
			return word;
		}
	}
	
	protected boolean startsWithAny(String word, List<Character> letters) {
		for (char c : letters) {
			if (word.startsWith("" + c)) {
				return true;
			}
		}
		return false;
	}
	
	public String getRandom() {
		int index = mRandom.nextInt(mWords.length);
		return mWords[index];
	}
	
	public int getLength() {
		return mLength;
	}

	private String[] readWords () {
		String filename = String.format("words/%d.txt", mLength);
		FileHandle hand = Gdx.files.internal(filename);
		return hand.readString().split("\n");
	}
}
