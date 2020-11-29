package com.petraszd.android.typingtrainer.token;

import com.petraszd.android.typingtrainer.Rules;

public class PointManager {
	int mPoints = 0;
	
	public PointManager() {
		reset();
	}
	
	public void reset() {
		mPoints = 0;
	}
	
	public void shooted(int killCount) {
		mPoints += Rules.POINTS_KILL + (killCount - 1) * Rules.POINTS_KILL_INC;
		Rules.onPointsChanged(mPoints);
	}
	
	public void mistake() {
		mPoints += Rules.POINTS_MISTAKE;
		Rules.onPointsChanged(mPoints);
	}
	
	public void correct() {
		mPoints += Rules.POINTS_CORRECT;
		Rules.onPointsChanged(mPoints);
	}
	
	public int getPoints() {
		return mPoints;
	}
	
	@Override
	public String toString() {
		return "" + mPoints;
	}
}
