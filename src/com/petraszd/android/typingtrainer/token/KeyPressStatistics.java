package com.petraszd.android.typingtrainer.token;

public class KeyPressStatistics {
	private long mGood;
	private long mBad;

	public KeyPressStatistics() {
		reset();
	}

	public void reset() {
		mGood = mBad = 0;
	}

	public void incGood() {
		mGood++;
	}

	public void incBad() {
		mBad++;
	}

	public String getAccuracyStr() {
		if (getTotal() == 0) {
			return "NaN";
		}
		double accuracy = mGood / (double) getTotal() * 100.0;
		return String.format("%.02f%%", accuracy);
	}

	public long getGood() {
		return mGood;
	}

	public long getBad() {
		return mBad;
	}

	public long getTotal() {
		return mGood + mBad;
	}

	@Override
	public String toString() {
		String formatStr = "Good: %d; Bad: %d; Accuracy: %s";
		return String.format(formatStr, mGood, mBad, getAccuracyStr());
	}
}
