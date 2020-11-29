package com.petraszd.android.typingtrainer.token;

import com.petraszd.android.typingtrainer.TypingTrainerGame;

public class Token {
	private TypingTrainerGame mGame;
	private TimeMeasurer mTimeMeasurer;
	private KeyPressStatistics mStatistics;
	private PointManager mPointManager;
	private Options mOptions;
	private AudioManager mAudio;

	public Token(TypingTrainerGame game) {
		mGame = game;
		mTimeMeasurer = new TimeMeasurer();
		mStatistics = new KeyPressStatistics();
		mPointManager = new PointManager();
		mOptions = new Options();
		mAudio = new AudioManager(mOptions);
	}

	public void startGame() {
		mTimeMeasurer.start();
		mStatistics.reset();
		mPointManager.reset();
	}

	public void endGame() {
		mTimeMeasurer.end();
	}

	public TimeMeasurer getMeasurer() {
		return mTimeMeasurer;
	}

	public KeyPressStatistics getStatistics() {
		return mStatistics;
	}

	public PointManager getPointManager() {
		return mPointManager;
	}

	public TypingTrainerGame getGame() {
		return mGame;
	}
	
	public Options getOptions() {
		return mOptions;
	}
	
	public AudioManager getAudioManager() {
		return mAudio;
	}
}
