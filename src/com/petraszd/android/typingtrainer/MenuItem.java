package com.petraszd.android.typingtrainer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.petraszd.android.typingtrainer.textures.RoundPatch;

public class MenuItem {

	private float mX = 0.0f;
	private float mY = 0.0f;
	private float mSizeX = 0.0f;
	private float mSizeY = 0.0f;

	private RoundPatch mPatch;
	private Sprite mSprite;

	public MenuItem(String name, float x, float y, float stepSize) {
		mPatch = new RoundPatch();
		mSprite = new Sprite(AssetsHelper.getTexture(name));

		mSizeX = stepSize * 5.2f;
		mSizeY = stepSize;

		mSprite.setSize(mSizeX, mSizeX); // Because img is rectangle with
											// transparent padding

		mX = x - mSizeX / 2.0f;
		mY = y - mSizeY / 2.0f;

		mSprite.setPosition(x - mSprite.getWidth() / 2.0f,
				y - mSprite.getHeight() / 2.0f);
	}

	public void draw(SpriteBatch batch) {
		mPatch.draw(batch, mX, mY, mSizeX, mSizeY);
		mSprite.draw(batch);
	}

	public void scaleUp() {
		float deltaX = mSprite.getWidth() * 0.05f;
		float deltaY = mSprite.getHeight() * 0.05f;

		mX -= deltaX;
		mSizeX += deltaX * 2.0f;

		mY -= deltaY;
		mSizeY += deltaY * 2.0f;
	}

	public void scaleDown() {
		float deltaX = mSprite.getWidth() * 0.05f;
		float deltaY = mSprite.getHeight() * 0.05f;

		mX += deltaX;
		mSizeX -= deltaX * 2.0f;

		mY += deltaY;
		mSizeY -= deltaY * 2.0f;
	}

	public boolean isTouched(float x, float y) {
		float x1 = mX;
		float y1 = mY;
		float x2 = mX + mSizeX;
		float y2 = mY + mSizeY;

		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}
}
