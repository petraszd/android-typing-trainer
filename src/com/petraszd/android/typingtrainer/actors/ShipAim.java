package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.petraszd.android.typingtrainer.textures.ShipAimTexture;

public class ShipAim extends PixmapActor {

	protected float mShipY = 0.0f;

	public void setPosition(float shipY) {
		mShipY = shipY;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(mTexture, 0.0f, mShipY - 1.0f);
	}

	@Override
	protected Texture getTexture() {
		return new ShipAimTexture();
	}

	@Override
	protected float getTextureX() {
		return 0.0f;
	}

	@Override
	protected float getTextureY() {
		// -1.0f because Texture is 2 pixels
		return mShipY - 1.0f;
	}
}
