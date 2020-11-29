package com.petraszd.android.typingtrainer.stages;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.petraszd.android.typingtrainer.Rules;
import com.petraszd.android.typingtrainer.actors.Background;
import com.petraszd.android.typingtrainer.actors.Buble;
import com.petraszd.android.typingtrainer.token.Token;

public class BackgroundBublesStage extends GameStage {

	protected float mNextGenerationTimer = 0.0f;
	protected Group mBubles;
	protected Random mRand;

	public BackgroundBublesStage(Token token) {
		super(token);

		mRand = new Random();

		addActor(new Background());

		mBubles = new Group("bubles");
		addActor(mBubles);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		mNextGenerationTimer += delta;
		if (mNextGenerationTimer > Rules.BACKGROUND_BUBLES_TIMER) {
			mNextGenerationTimer = 0.0f;
			initBubles();
		}
	}

	protected void initBubles() {
		for (float x = Rules.STEP_SIZE_HALF; x < Gdx.graphics.getWidth(); x += Rules.STEP_SIZE) {
			if (mRand.nextFloat() > Rules.BACKGROUND_BUBLES_P_BIRTH) {
				continue;
			}
			float realX = x + mRand.nextFloat()
					* Rules.BACKGROUND_BUBLES_RANDOM_X - 0.5f
					* Rules.BACKGROUND_BUBLES_RANDOM_X;
			mBubles.addActor(new Buble(realX, -10.0f));
		}
	}
}
