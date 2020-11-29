package com.petraszd.android.typingtrainer.words;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.petraszd.android.typingtrainer.Rules;
import com.petraszd.android.typingtrainer.actors.Ship;

public class WordActionDown extends WordAction {
	public WordActionDown(Ship ship, BitmapFont faded,
			BitmapFont normal) {
		super(ship, faded, normal);
	}

	@Override
	protected void onFinish() {
		mShip.onDown();
	}
	
	protected String getImageSrc()
	{
		return "images/arrow-down.png";
	}

	@Override
	protected int getWordLength() {
		return Rules.MOVE_WORD_LENGTH;
	}
}
