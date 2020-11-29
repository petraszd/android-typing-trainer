package com.petraszd.android.typingtrainer.words;

import java.util.ArrayList;
import java.util.List;


public class WordActions {
	private List<WordAction> mActions;
	private WordAction mActive;
	private WordActionsListener mListener;
	
	public WordActions(WordAction... actions) {
		mActive = null;
		mListener = null;
		mActions = new ArrayList<WordAction>(actions.length);
		for (WordAction wordAction : actions) {
			mActions.add(wordAction);
		}
	}
	
	public void regenerateAll() {
		List<Character> firsts = new ArrayList<Character>(mActions.size() - 1);
		
		for (WordAction action : mActions) {
			firsts.add(action.generateCurrentWord(firsts));
		}
	}
	
	public void setListener(WordActionsListener listener) {
		mListener = listener;
	}
	
	public void regenerateFor (WordAction mainAction) {
		List<Character> firsts = new ArrayList<Character>(mActions.size() - 1);
		for (WordAction action : mActions) {
			if (mainAction != action) {
				firsts.add(action.getFirstNotFilled());
			}
		}
		mainAction.generateCurrentWord(firsts);
	}
	
	public void tryCharacter(char character) {
		if (mActive == null) {
			tryCharacterOnAll(character);
		} else {
			tryCharacterOnActive(character);
		}
	}
	
	public void tryCharacterOnActive(char character) {
		int result = mActive.tryCharacter(character);
		if (result == WordAction.BAD) {
			onInvalidPress();
		} else if (result == WordAction.GOOD) {
			onValidPress();
		} else { // Finished
			regenerateFor(mActive);
			mActive = null;
			onValidPress();
		}
	}
	
	public void tryCharacterOnAll(char character) {
		for (WordAction action : mActions) {
			if (action.isEnabled()) {
				int result = action.tryCharacter(character);
				if (result == WordAction.GOOD) {
					mActive = action;
					onValidPress();
					return;
				} else if (result == WordAction.FINISHED) { // FINISHED
					regenerateFor(action);
					mActive = null;
					onValidPress();
					return;
				}
			}
		}
		onInvalidPress();
	}
	
	public void backspace() {
		if (mActive != null) {
			mActive.backspace();
			if (!mActive.isFilled()) {
				mActive = null;
			}
		}
	}
	
	private void onInvalidPress() {
		if (mListener != null) {
			mListener.onInvalidPress();
		}
	}
	
	private void onValidPress() {
		if (mListener != null) {
			mListener.onValidPress();
		}
	}
}

