package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.petraszd.android.typingtrainer.AssetsHelper;
import com.petraszd.android.typingtrainer.Rules;

public class Enemy extends SpriteActorCentered {

	private Sprite mShadowSprite;
	private boolean mIsDying = false;
	private int mBlinkCounter = 0;
	private float mTimer = 0.0f;

	public Enemy(float x, float y, float groundY) {
		mSprite.setPosition(x + getSpriteWidth() / 2.0f, y);

		mShadowSprite = new Sprite(AssetsHelper.getTexture("shadow"));
		mShadowSprite.setSize(Rules.STEP_SIZE * 2, Rules.STEP_SIZE * 2);

		mShadowSprite.setPosition(x + getSpriteWidth() / 2.0f,
				groundY - mShadowSprite.getHeight() / 1.5f + Rules.STEP_SIZE);
	}

	public void startDying() {
		mIsDying = true;
		mTimer = Rules.ENEMY_DYING_TIMER;
		mBlinkCounter = 0;
	}

	@Override
	public void act(float delta) {
		if (mIsDying) {
			actWhileDyint(delta);
		} else {
			actWhileAlive(delta);
		}
	}

	@Override
	protected void drawSprite(SpriteBatch batch) {
		if (mIsDying) {
			mSprite.draw(batch, Math.min(Rules.ENEMY_DYING_MAX_ALPHA, mTimer));
		} else {
			super.drawSprite(batch);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (!mIsDying) {
			mShadowSprite.draw(batch);
		}
		super.draw(batch, parentAlpha);
	}

	public float getX() {
		return mSprite.getX();
	}
	
	public float getBubleX() {
		return mSprite.getX() + getImageDeltaX() * 0.8f;
	}
	
	public float getBubleY() {
		return mSprite.getY() - Rules.STEP_SIZE * 0.27f;
	}

	@Override
	public Actor hit(float x, float y) {
		float selfX = mSprite.getX();
		float selfY = mSprite.getY();

		if ((Math.abs(x - selfX) < 20.0f || x > selfX)
				&& Math.abs(y - selfY) < Rules.STEP_SIZE_HALF) {
			return this;
		}
		return null;
	}

	@Override
	protected String getTextureFilename() {
		return "enemy";
	}

	@Override
	protected float getSpriteWidth() {
		return Rules.STEP_SIZE * 3.0f;
	}

	@Override
	protected float getSpriteHeight() {
		return Rules.STEP_SIZE * 3.0f;
	}

	private void actWhileAlive(float delta) {
		float newX = mSprite.getX() - delta * Rules.ENEMY_SPEED;
		mSprite.setPosition(newX, mSprite.getY());
		mShadowSprite.setPosition(newX - Rules.STEP_SIZE * 1.5f,
				mShadowSprite.getY());
	}

	private void actWhileDyint(float delta) {
		mTimer -= delta * Rules.ENEMY_DYING_ALPHA_SPEED;
		float moveDelta = delta * Rules.ENEMY_DYING_MOVING_SPEED;
		mSprite.setPosition(mSprite.getX() + moveDelta, mSprite.getY()
				+ moveDelta);
		if (mTimer <= 0.0f) {
			mTimer = 1.0f;
			mBlinkCounter++;
			if (mBlinkCounter > Rules.ENEMY_DYING_COUNTER) {
				markToRemove(true);
			}
		}
	}
}
