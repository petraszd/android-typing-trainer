package com.petraszd.android.typingtrainer;

import com.petraszd.android.typingtrainer.token.Options;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	public static final int START_GAME = 1;
	public static final String NEXT_ACTION_NAME = "next";
	public static final int NEXT_GO_LANDSCAPE = 2;
	public static final int NEXT_GO_PORTRAIT = 3;
	public static final int NEXT_QUIT = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startGame(new Options(this).getGameActivityClass());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			int nextAction = data.getExtras().getInt(NEXT_ACTION_NAME);
			if (nextAction == NEXT_GO_LANDSCAPE) {
				startGame(LanscapeActivity.class);
			} else if (nextAction == NEXT_GO_PORTRAIT) {
				startGame(PortraitActivity.class);
			} else if (nextAction == NEXT_QUIT) {
				finish();
			}
		}
	}
	
	protected void startGame(Class<?> gameMode) {
		startActivityForResult(new Intent(this, gameMode), START_GAME);
	}
}