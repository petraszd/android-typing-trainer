package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.petraszd.android.typingtrainer.textures.RoundPatch;

public class PauseLabelActor extends SpriteActorCentered {
	
	private boolean mIsVisible = false;
	private RoundPatch mPatch; 

	public PauseLabelActor() {
		mSprite.setPosition(Gdx.graphics.getWidth() / 2.0f,
				Gdx.graphics.getHeight() / 2.0f);
		mPatch = new RoundPatch();
	}
	
	public void turnOn() {
		mIsVisible = true;
	}
	
	public void turnOff () {
		mIsVisible = false;
	}

	public void setHeight(int h) {
		mSprite.setPosition(Gdx.graphics.getWidth() / 2.0f,
				(Gdx.graphics.getHeight() + h) / 2.0f);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (mIsVisible) {
			float x = mSprite.getX();
			float y = mSprite.getY();
			float w = mSprite.getWidth() * 1.3f;
			float h = w / 3.6f; // TODO: to GAME
			mPatch.draw(batch, x - w / 2.0f, y - h / 2.0f, w, h);
			super.draw(batch, parentAlpha);
		}
	}

	@Override
	protected String getTextureFilename() {
		return "pause";
	}
	
	@Override
	protected float getSpriteWidth() {
		return Gdx.graphics.getWidth() / 2.0f;
	}
	
	@Override
	protected float getSpriteHeight() {
		return getSpriteWidth();
	}
}
