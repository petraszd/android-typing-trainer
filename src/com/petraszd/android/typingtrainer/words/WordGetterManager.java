package com.petraszd.android.typingtrainer.words;

import com.petraszd.android.typingtrainer.Rules;

public class WordGetterManager {
	
	public static WordGetterManager instance = null;
	 
	public static WordGetterManager getInstance() {
		if (instance == null) {
			instance = new WordGetterManager();
		}
		return instance;
	}
	
	private WordGetter[] mGetters;
	
	private WordGetterManager() {
		int max = Rules.WORD_MAX_LENGTH;
		int min = Rules.WORD_MIN_LENGTH;
		mGetters = new WordGetter[max - min + 1];
		for (int i = min; i <= max; ++i) {
			mGetters[i - min] = new WordGetter(i);
		}
	}
	
	public WordGetter getter(int wordLength) {
		return mGetters[wordLength - Rules.WORD_MIN_LENGTH];
	}
}










