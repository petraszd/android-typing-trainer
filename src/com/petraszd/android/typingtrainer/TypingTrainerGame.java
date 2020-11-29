package com.petraszd.android.typingtrainer;

import android.content.res.Configuration;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.petraszd.android.typingtrainer.screens.MainScreen;

public class TypingTrainerGame extends Game {
	
	GameActivity mActivity;
	MainScreen mScreen;

	public TypingTrainerGame(GameActivity activity) {
		mActivity = activity;
	}

	public void create() {
		setupGL();
		Gdx.input.setCatchBackKey(true);
		mScreen = new MainScreen(this);
		setScreen(mScreen);
	}
	
	public boolean hasHardwareKeyboard() {
		Configuration conf = mActivity.getResources().getConfiguration();
		return conf.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO;
	}
	
	@Override
	public void resume() {
		super.resume();
		setupGL();
	}
	
	public void toPortrait() {
		mActivity.toPortrait();
	}
	
	public void toLandscape() {
		mActivity.toLandscape();
	}
	
	public void quit() {
		mActivity.quit();
	}
	
	public int getVisibleHeight() {
		return mActivity.getVisibleHeight();
	}

	protected void setupGL() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	}
}
