package com.petraszd.android.typingtrainer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.petraszd.android.typingtrainer.token.Options;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;

abstract public class GameActivity extends AndroidApplication {
	private Rect m_rect = new Rect();
	
	TypingTrainerGame mGame;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		updateOrientation(new Options(this));
		mGame = new TypingTrainerGame(this);
		initialize(mGame, false);
	}

	public int getVisibleHeight() {
		getWindow().getDecorView().getWindowVisibleDisplayFrame(m_rect);
		return Gdx.graphics.getHeight() - m_rect.height();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	public void toPortrait() {
		tellParentTo(MainActivity.NEXT_GO_PORTRAIT);
	}

	public void toLandscape() {
		tellParentTo(MainActivity.NEXT_GO_LANDSCAPE);
	}
	
	public void quit() {
		tellParentTo(MainActivity.NEXT_QUIT);
	}
	
	protected void tellParentTo(int action) {
		Intent intent = new Intent();
		intent.putExtra(MainActivity.NEXT_ACTION_NAME, action);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	abstract void updateOrientation(Options options);	
}
