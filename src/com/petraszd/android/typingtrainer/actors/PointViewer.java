package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.petraszd.android.typingtrainer.AssetsHelper;
import com.petraszd.android.typingtrainer.Rules;

public class PointViewer extends Actor {

	private BitmapFont mFont;
	private int mPoints = 0;

	public PointViewer() {
		mFont = AssetsHelper.getFont("points");
	}
	
	public void setPoints(int points) {
		mPoints = points;
	}

	@Override
	public void draw(SpriteBatch batch, float parentApha) {
		String pointsStr = String.format("Your Points: %05d", mPoints);
		TextBounds tb = mFont.getBounds(pointsStr); 
		float x = Gdx.graphics.getWidth() - tb.width - Rules.FONT_SIZE_HALF;
		float y = Gdx.graphics.getHeight() - tb.height / 2.0f;
		mFont.draw(batch, pointsStr, x, y);
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
