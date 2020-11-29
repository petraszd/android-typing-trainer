package com.petraszd.android.typingtrainer.actors;

import java.util.Random;

import com.petraszd.android.typingtrainer.Rules;

public class RecordBuble extends Buble {
	
	Random mRandom = new Random();
	float mChangeDirTimer = 0.0f;
	float mDir = 1.0f;
	
	public RecordBuble(float x, float y) {
		super(x, y);
		if (!Rules.IS_HD) {
			mSprite.setScale(1.0f);
		}
		mChangeDirTimer = 2.0f + mRandom.nextFloat() * 0.4f; // TODO: to Game
	}
	
	@Override
	public void act(float delta) {
		float deltaY = Rules.BUBLE_SPEED * delta;
		float deltaX = delta * mDir * 5.0f; // TODO: to GAME
		mSprite.setPosition(mSprite.getX() + deltaX, mSprite.getY() + deltaY);
		
		mChangeDirTimer -= delta;
		if (mChangeDirTimer < 0.0f) {
			mChangeDirTimer = 2.0f; // TODO: to GAME
			mDir *= -1.0f;
		}
	}
}
