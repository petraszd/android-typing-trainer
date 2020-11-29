package com.petraszd.android.typingtrainer.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.petraszd.android.typingtrainer.AssetsHelper;
import com.petraszd.android.typingtrainer.token.Token;

public class TutorialStage extends InformationStage {

	Sprite mTutorial;

	public TutorialStage(Token token) {
		super(token);

		mTutorial = new Sprite(AssetsHelper.getTexture("tutorial"));
	}

	@Override
	public void onBack() {
		toNextStage(StartStage.class);
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		toNextStage(PlayableStage.class);
		return true;
	}

	@Override
	protected void customDraw(SpriteBatch batch, float w, float h, float x,
			float y) {
		float size;
		if (w > h) {
			size = w * 0.5f;
		} else {
			size = w * 0.9f;
		}
		mTutorial.setSize(size, size);
		mTutorial.setPosition((Gdx.graphics.getWidth() - size) / 2.0f,
				(Gdx.graphics.getHeight() - size) / 2.0f);
		mTutorial.draw(batch);
	}

	@Override
	protected String getHeaderString() {
		return "How to play";
	}

	@Override
	protected String getMessageString() {
		return "Tap anywhere to begin";
	}
}
