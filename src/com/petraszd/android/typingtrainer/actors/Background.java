package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.petraszd.android.typingtrainer.AssetsHelper;
import com.petraszd.android.typingtrainer.Rules;

public class Background extends Actor {

	private Sprite mBlueSprite;
	private Sprite mGroundBottom;
	private Sprite mGroundTop;
	private float mHeight;
	private float mGroundAnimFactor;
	private int mGroundDir;

	public Background() {
		mBlueSprite = makeSprite("blue");
		mBlueSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		mGroundBottom = makeGroundSprite("bottom");
		mGroundTop = makeGroundSprite("top");

		mGroundAnimFactor = -1.0f;
		mGroundDir = 1;
	}

	public void setHeight(int h) {
		mHeight = h;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		mGroundAnimFactor += mGroundDir * delta
				* Rules.BACKGROUND_GROUND_ANIM_SPEED;
		if (mGroundAnimFactor > 1.0f) {
			mGroundAnimFactor = 1.0f;
			mGroundDir *= -1;
		} else if (mGroundAnimFactor < -1.0f) {
			mGroundAnimFactor = -1.0f;
			mGroundDir *= -1;
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		mBlueSprite.draw(batch);

		mGroundBottom.setPosition(-Rules.STEP_SIZE_HALF + Rules.STEP_SIZE_HALF
				* mGroundAnimFactor, mHeight - mGroundBottom.getHeight()
				+ Rules.STEP_SIZE * 2);
		mGroundBottom.draw(batch);

		mGroundTop.setPosition(-Rules.STEP_SIZE_HALF - Rules.STEP_SIZE_HALF
				* mGroundAnimFactor, mHeight - mGroundTop.getHeight()
				+ Rules.STEP_SIZE * 2.0f);
		mGroundTop.draw(batch);
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

	private Sprite makeSprite(String name) {
		String fullname = String.format("background-%s", name);
		Texture t = AssetsHelper.getTexture(fullname);
		return new Sprite(t);
	}
	
	private Sprite makeGroundSprite(String name) {
		Sprite sprite = makeSprite(String.format("ground-%s", name));
		float size = Gdx.graphics.getWidth() + Rules.STEP_SIZE * 2;
		sprite.setSize(size, size);
		return sprite;
	}
}
