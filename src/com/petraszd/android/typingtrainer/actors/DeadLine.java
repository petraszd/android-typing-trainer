package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.petraszd.android.typingtrainer.Rules;

public class DeadLine extends SpriteActor {
	
	protected float mAlpha = 0.0f;
	
	public void setAlpha(float alpha) {
		mAlpha = alpha;
	}
	
	@Override
	protected float getSpriteHeight() {
		return Gdx.graphics.getHeight();
	}

	@Override
	protected String getTextureFilename() {
		return "dead-line";
	}
	
	@Override
	protected float getImageDeltaX() {
		return Rules.STEP_SIZE * 3.1f - 3.0f;
	}
	
	@Override
	protected float getImageDeltaY() {
		return 0.0f;
	}
	
	@Override
	protected void drawSprite(SpriteBatch batch) {
		mSprite.draw(batch, mAlpha);
	}
}
