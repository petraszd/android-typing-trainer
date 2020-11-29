package com.petraszd.android.typingtrainer.token;

import android.content.Context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidPreferences;
import com.petraszd.android.typingtrainer.LanscapeActivity;
import com.petraszd.android.typingtrainer.PortraitActivity;

public class Options {
	private static final String PREFERENCES_NAME = "game-options";
	
	private static final String ORIENTATION = "orientation";
	private static final int ORIENTATION_LANDSCAPE = 0;
	private static final int ORIENTATION_PORTRAIT = 1;
	
	private static final String SOUND = "sound";
	
	private static final String NOT_FIRST_RUN = "not-first-run";
	
	
	private Preferences mPreferences = null;
	private boolean mIsSound;
	
	public Options(Context context) {
		mPreferences = new AndroidPreferences(
				context.getSharedPreferences(PREFERENCES_NAME,
				Context.MODE_PRIVATE));
		mIsSound = isSoundRaw();
	}

	public Options() {
		mIsSound = isSoundRaw();
	}
	
	public Class<?> getGameActivityClass () {
		int option = getPreference().getInteger(ORIENTATION, ORIENTATION_LANDSCAPE);
		if (option == ORIENTATION_LANDSCAPE) {
			return LanscapeActivity.class;
		} else {
			return PortraitActivity.class;
		}
	}
	
	public void setSoundOn() {
		setSound(true);
	}
	
	public void setSoundOff() {
		setSound(false);
	}
	
	public void setPortrait() {
		setOrientation(ORIENTATION_PORTRAIT);
	}
	
	public void setLandscape() {
		setOrientation(ORIENTATION_LANDSCAPE);
	}
	
	public boolean isSound() {
		return mIsSound;
	}
	
	public void markFirstRun() {
		getPreference().putBoolean(NOT_FIRST_RUN, true);
		getPreference().flush();		
	}
	
	public boolean isFirstRun() {
		return !getPreference().getBoolean(NOT_FIRST_RUN, false);
	}
	
	private boolean isSoundRaw() {
		return getPreference().getBoolean(SOUND, false);
	}
	
	private void setOrientation(int orientation) {
		getPreference().putInteger(ORIENTATION, orientation);
		getPreference().flush();
	}
	
	private void setSound(boolean value) {
		mIsSound = value;
		getPreference().putBoolean(SOUND, value);
		getPreference().flush();
	}
	
	private Preferences getPreference () {
		if (mPreferences != null) {
			return mPreferences;
		}
		return Gdx.app.getPreferences(PREFERENCES_NAME);
	}
}
