package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.petraszd.android.typingtrainer.AssetsHelper;
import com.petraszd.android.typingtrainer.Rules;

public class OnError extends Actor{
	
	private Sprite mSprite;
	private boolean mIsVisible = false;
	private float mTimer = 0.0f;
	
	public OnError() {
		Texture t = AssetsHelper.getTexture("onerror");
		mSprite = new Sprite(t);
		mSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void setheight(float h) {
		mSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - h);
		mSprite.setPosition(0.0f, h);
	}
	
	public void turnOn() {
		mIsVisible = true;
		mTimer = Rules.BACKGROUND_ON_ERROR_TIMEOUT;
	}
	
	@Override
	public void act(float delta) {
		mTimer -= delta;
		if (mTimer < 0.0f) {
			mIsVisible = false;
			mTimer = 0.0f;
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (mIsVisible) {
			mSprite.draw(batch, mTimer);
		}
	}
	
	@Override
	public Actor hit(float x, float ys) {
		return null;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		return false;
	}

	@Override
	public void touchDragged(float x, float y, int pointer) {
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
	}
}
