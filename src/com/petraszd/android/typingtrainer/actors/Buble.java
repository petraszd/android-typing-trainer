package com.petraszd.android.typingtrainer.actors;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.petraszd.android.typingtrainer.Rules;

public class Buble extends SpriteActorCentered {
	
	protected float mSpeed = 0.0f;
	
	public Buble(float x, float y) {
		super();
		mSprite.setPosition(x, y);
		mSprite.setScale(0.3f);
		
		Random rand = new Random();
		mSpeed = Rules.BUBLE_SPEED + rand.nextFloat() * Rules.BUBLE_SPEED_RANDOM; 
	}

	@Override
	protected String getTextureFilename() {
		return "buble";
	}
	
	@Override
	public void act(float delta) {
		float newScale = mSprite.getScaleX() + delta * Rules.BUBLE_SCALE_SPEED;
		if (newScale <= 1.0f) {
			mSprite.setScale(newScale);
		}
		mSprite.setPosition(mSprite.getX(), mSprite.getY() + delta * mSpeed);
		if (mSprite.getY() > Gdx.graphics.getHeight()) {
			markToRemove(true);
		}
	}
	
	@Override
	protected float getSpriteWidth() {
		if (Rules.IS_HD) {
			return Rules.STEP_SIZE / 1.5f;	
		}
		return Rules.STEP_SIZE / 3.0f;
	}
	
	protected float getSpriteHeight() {
		if (Rules.IS_HD) {
			return Rules.STEP_SIZE / 1.5f;	
		}
		return Rules.STEP_SIZE / 3.0f;
	}
}
