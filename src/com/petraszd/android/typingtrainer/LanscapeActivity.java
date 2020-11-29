package com.petraszd.android.typingtrainer;

import com.petraszd.android.typingtrainer.token.Options;

import android.content.pm.ActivityInfo;

public class LanscapeActivity extends GameActivity {

	@Override
	void updateOrientation(Options options) {
		options.setLandscape();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}
}
