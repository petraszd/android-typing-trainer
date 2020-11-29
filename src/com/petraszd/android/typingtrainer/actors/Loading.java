package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.Gdx;
import com.petraszd.android.typingtrainer.Rules;

public class Loading extends SpriteActor {

	public Loading() {
		super();
		mSprite.setPosition(Rules.STEP_SIZE_HALF, Gdx.graphics.getHeight()
				- getSpriteHeight() / 3.0f);
	}

	@Override
	protected String getTextureFilename() {
		return "loading";
	}

	@Override
	protected float getSpriteWidth() {
		return Rules.STEP_SIZE * 3.0f;
	}

	@Override
	protected float getSpriteHeight() {
		return getSpriteWidth();
	}
}
