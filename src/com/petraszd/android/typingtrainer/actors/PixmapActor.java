package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

abstract public class PixmapActor extends Actor {
	protected Texture mTexture;

	public PixmapActor() {
		mTexture = getTexture();
	}

	public void onResume() {
		mTexture = getTexture();
	}
	
	abstract protected Texture getTexture();	
	abstract protected float getTextureX();
	abstract protected float getTextureY();

	@Override
	public void act(float delta) {
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(mTexture, getTextureX(), getTextureY());
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
