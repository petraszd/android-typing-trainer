package com.petraszd.android.typingtrainer.token;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class RecordManager {
	
	private static final String BEST_SCORE_KEY = "best-score";
	private static final String VERTICAL_PREF_NAME = "vertical";
	private static final String HORIZONTAL_PREF_NAME = "horizontal";

	public enum Type { HORIZONTAL, VERTICAL };
	
	public RecordManager() {
	}
	
	public boolean containsBestScore() {
		return getPreferences().contains(BEST_SCORE_KEY);
	}
	
	public boolean putBestScore(int bestScoreCandidate) {
		int currentBest = getBestScore();
		if (bestScoreCandidate > currentBest) {
			Preferences pref = getPreferences(); 
			pref.putInteger(BEST_SCORE_KEY, bestScoreCandidate);
			pref.flush();
			return true;
		}
		return false;
	}
	
	public int getBestScore() {
		if (containsBestScore()) {
			return getPreferences().getInteger(BEST_SCORE_KEY);
		}
		return 0;
	}
	
	public Type getType() {
		if (Gdx.graphics.getWidth() > Gdx.graphics.getHeight()) {
			return Type.HORIZONTAL;
		}
		return Type.VERTICAL;
	}
	
	private String getPrefName() {
		if (getType() == Type.HORIZONTAL) {
			return HORIZONTAL_PREF_NAME;
		}
		return VERTICAL_PREF_NAME;
	}
	
	private Preferences getPreferences() {
		return Gdx.app.getPreferences(getPrefName());
	}
}
