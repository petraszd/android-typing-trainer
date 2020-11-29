package com.petraszd.android.typingtrainer.words;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.petraszd.android.typingtrainer.Rules;
import com.petraszd.android.typingtrainer.actors.Ship;

public class WordActionShoot extends WordAction {
	public WordActionShoot(Ship ship, BitmapFont faded, BitmapFont normal) {
		super(ship, faded, normal);
	}

	@Override
	protected void onFinish() {
		mShip.onShoot();
	}
	
	protected String getImageSrc()
	{
		return "images/arrow-shoot.png";
	}

	@Override
	protected int getWordLength() {
		return Rules.SHOOT_WORD_LENGTH;
	}
}
