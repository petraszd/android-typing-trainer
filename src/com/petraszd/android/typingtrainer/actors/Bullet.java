package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.Gdx;
import com.petraszd.android.typingtrainer.Rules;

public class Bullet extends SpriteActorCentered {
	
	private int mKillCount = 0;

	public Bullet(float x, float y) {
		super();
		mSprite.setPosition(x, y);
	}
	
	@Override
	public void act(float delta) {
		float x = mSprite.getX();
		if (x - getSpriteWidth() / 4.0f > Gdx.graphics.getWidth()) {
			markToRemove(true);
		} else {
			mSprite.setPosition(x + Rules.BULLET_SPEED * delta, mSprite.getY());
		}
	}
	
	public float getX() {
		return mSprite.getX() + Rules.STEP_SIZE_HALF / 2.0f;
	}
	
	public float getY() {
		return mSprite.getY();
	}
	
	public int getKillCount () {
		return mKillCount;
	}
	
	public void onKill () {
		mKillCount++;
	}

	@Override
	protected String getTextureFilename() {
		return "bomb";
	}

	@Override
	protected float getSpriteWidth() {
		return Rules.STEP_SIZE * 1.7f;
	}

	@Override
	protected float getSpriteHeight() {
		return Rules.STEP_SIZE * 1.7f;
	}
}
