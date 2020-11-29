package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.petraszd.android.typingtrainer.AssetsHelper;
import com.petraszd.android.typingtrainer.Rules;
import com.petraszd.android.typingtrainer.stages.PlayableStage;
import com.petraszd.android.typingtrainer.words.WordAction;
import com.petraszd.android.typingtrainer.words.WordActionDown;
import com.petraszd.android.typingtrainer.words.WordActionShoot;
import com.petraszd.android.typingtrainer.words.WordActionUp;
import com.petraszd.android.typingtrainer.words.WordActions;

public class Ship extends Actor {

	protected int mHeight;

	protected PlayableStage mStage;
	protected ShipAim mShipAim;

	protected Sprite mShipSprite;
	protected Sprite mShadowSprite;

	protected float mY;
	protected float mDeltaY;

	protected WordAction mUpAction;
	protected WordAction mDownAction;
	protected WordAction mShootAction;
	protected WordActions mWordActions;

	public Ship(PlayableStage stage, ShipAim shipAim) {
		mHeight = Gdx.graphics.getHeight();
		mStage = stage;

		mShipAim = shipAim;

		initActions();
		mWordActions.setListener(stage);

		initSprites();
	}

	public void setHeight(int h) {
		mHeight = h;
		mY = mHeight + (Gdx.graphics.getHeight() - mHeight) / 2.0f;

		mShadowSprite.setPosition(Rules.STEP_SIZE_HALF,
				mHeight - mShadowSprite.getHeight() / 1.5f + Rules.STEP_SIZE);
	}

	public void onUp() {
		if (canMoveUp()) {
			float newY = mY + Rules.STEP_SIZE;
			mStage.makeShipBubles(Rules.STEP_SIZE * 2.0f, mY);
			mDeltaY = mY - newY;
			mY = newY;
		}
		updateUpDownActions();
	}

	public void onDown() {
		if (canMoveDown()) {
			float newY = mY - Rules.STEP_SIZE;
			mStage.makeShipBubles(Rules.STEP_SIZE * 2.0f, mY);
			mDeltaY = mY - newY;
			mY = newY;
		}
		updateUpDownActions();
	}

	public void onShoot() {
		mStage.createBullet(Rules.STEP_SIZE * 2.5f, mY);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		float realY = mY + mDeltaY;
		float shipY = realY - mShipSprite.getHeight() / 2.0f;

		mShipSprite.setPosition(0.0f, shipY);
		mShipSprite.draw(batch);

		mShadowSprite.draw(batch);

		float moveDelta = Rules.FONT_SIZE_HALF + Rules.STEP_SIZE;
		mUpAction.draw(batch, Rules.STEP_SIZE * 2.0f, realY + moveDelta);
		mDownAction.draw(batch, Rules.STEP_SIZE * 2.0f, realY - moveDelta);

		mShootAction.draw(batch, Rules.STEP_SIZE * 3.5f, realY);
	}

	@Override
	public void act(float delta) {
		animateMovement(delta);
		mShipAim.setPosition(mY + mDeltaY);
	}

	@Override
	public boolean keyTyped(char character) {
		if (character != 0) {
			if (character >= 97 && character <= 122) {
				mWordActions.tryCharacter(character);
			} else if (character == 8) { // Backspace
				mWordActions.backspace();
			}
			return true;
		}
		return false;
	}

	protected void initActions() {
		BitmapFont normal = AssetsHelper.getFont("words", "normal");
		BitmapFont faded = AssetsHelper.getFont("words", "faded");

		mUpAction = new WordActionUp(this, faded, normal);
		mDownAction = new WordActionDown(this, faded, normal);
		mShootAction = new WordActionShoot(this, faded, normal);

		mWordActions = new WordActions(mUpAction, mDownAction, mShootAction);
		mWordActions.regenerateAll();
	}

	protected void initSprites() {
		mShipSprite = new Sprite(AssetsHelper.getTexture("player"));
		mShipSprite.setSize(Rules.STEP_SIZE * 3.0f, Rules.STEP_SIZE * 3.0f);

		mShadowSprite = new Sprite(AssetsHelper.getTexture("shadow"));
		mShadowSprite.setSize(Rules.STEP_SIZE * 2.0f, Rules.STEP_SIZE * 2.0f);

		mY = 0.0f;
	}

	protected void updateUpDownActions() {
		mDownAction.setEnabled(canMoveDown());
		mUpAction.setEnabled(canMoveUp());
	}

	protected boolean canMoveUp() {
		return mY + Rules.STEP_SIZE < Gdx.graphics.getHeight() - Rules.STEP_SIZE;
	}

	protected boolean canMoveDown() {
		return mY - Rules.STEP_SIZE > mHeight + Rules.STEP_SIZE;
	}

	protected void animateMovement(float delta) {
		float amount = Math.abs(mDeltaY) * Rules.SHIP_ANIM_SPEED;
		float newDelta = 0.0f;
		if (Math.abs(mDeltaY) < 0.2f) {
			newDelta = 0.0f;
		} else if (mDeltaY > 0.0f) {
			newDelta = mDeltaY - amount * delta;
		} else {
			newDelta = mDeltaY + amount * delta;
		}
		
		// Safety -- used only with really low framerate
		if (Math.abs(newDelta) > Math.abs(mDeltaY)) {
			mDeltaY = 0.0f;
		} else {
			mDeltaY = newDelta;
		}
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

	@Override
	public Actor hit(float x, float ys) {
		return null;
	}

}
