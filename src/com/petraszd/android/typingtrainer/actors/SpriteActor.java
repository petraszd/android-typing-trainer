package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.petraszd.android.typingtrainer.AssetsHelper;

abstract public class SpriteActor extends Actor {

	protected Sprite mSprite;

	public SpriteActor() {
		Texture t = AssetsHelper.getTexture(getTextureFilename());
		t.setFilter(getMinFilter(), getMagFilter());
		mSprite = new Sprite(t);
		float w = getSpriteWidth();
		float h = getSpriteHeight();
		mSprite.setSize(w, h);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		float x = mSprite.getX();
		float y = mSprite.getY();
		mSprite.translate(getImageDeltaX(), getImageDeltaY());
		drawSprite(batch);
		
		mSprite.setPosition(x, y);
	}

	@Override
	public Actor hit(float x, float y) {
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

	abstract protected String getTextureFilename();

	protected float getSpriteWidth() {
		return mSprite.getWidth();
	}

	protected float getSpriteHeight() {
		return mSprite.getHeight();
	}

	protected float getImageDeltaX() {
		return 0.0f;
	}

	protected float getImageDeltaY() {
		return -mSprite.getHeight() / 2.0f;
	}
	
	protected void drawSprite(SpriteBatch batch) {
		mSprite.draw(batch);
	}
	
	protected TextureFilter getMinFilter() {
		return TextureFilter.Linear;
	}
	
	protected TextureFilter getMagFilter() {
		return TextureFilter.Nearest;
	}
}
