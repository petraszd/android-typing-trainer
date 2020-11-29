package com.petraszd.android.typingtrainer.words;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.petraszd.android.typingtrainer.Rules;
import com.petraszd.android.typingtrainer.actors.Ship;

public class WordActionUp extends WordAction {
	
	public WordActionUp(Ship ship, BitmapFont faded, BitmapFont normal) {
		super(ship, faded, normal);
	}

	@Override
	protected void onFinish() {
		mShip.onUp();
	}
	
	protected String getImageSrc()
	{
		return "images/arrow-up.png";
	}

	@Override
	protected int getWordLength() {
		return Rules.MOVE_WORD_LENGTH;
	}
}
