package com.petraszd.android.typingtrainer.token;

public class TimeMeasurer {
	private long mBegin = 0;
	private long mEnd = 0;
	
	public TimeMeasurer() {
	}
	
	public void start() {
		mEnd = mBegin = System.nanoTime();
	}
	
	public void end () {
		mEnd = System.nanoTime();
	}
	
	public long getNanoDiff() {
		return mEnd - mBegin;
	}
	
	@Override
	public String toString() {
		return String.format("%.02f s.", getNanoDiff() * 1e-9);
	}
}
