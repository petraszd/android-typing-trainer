package com.petraszd.android.typingtrainer;

import com.petraszd.android.typingtrainer.token.Options;

import android.content.pm.ActivityInfo;

public class PortraitActivity extends GameActivity {
	
	@Override
	void updateOrientation(Options options) {
		options.setPortrait();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}
