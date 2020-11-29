package com.petraszd.android.typingtrainer.stages;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.petraszd.android.typingtrainer.actors.Loading;
import com.petraszd.android.typingtrainer.token.Token;

public class GameStage extends Stage {

	protected Token mToken = null;
	protected GameStage mNextStage = null;
	protected boolean mSoftKeyboardWasHiddenBefore = false;
	protected Class<? extends GameStage> mNextClass = null;
	protected float mNextClassTimer = 0.0f;

	public GameStage(Token token) {
		super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		mToken = token;
		Gdx.input.setInputProcessor(this);
		mNextStage = this;
	}

	public GameStage getNextStage() {
		return mNextStage;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (mNextClass != null) {
			mNextClassTimer += delta;
			if (mNextClassTimer > 0.05f) { // TODO: to rules
				try {
					Constructor<? extends GameStage> ctor = mNextClass
							.getConstructor(Token.class);
					mNextStage = ctor.newInstance(mToken);
				} catch (Exception e) {
					// It is really stupid, but I need to show that
					// game is not in infinitive loop
					mNextStage = new StartStage(mToken);
				}
				mNextClass = null;
				mNextClassTimer = 0.0f;
				Gdx.input.setInputProcessor(mNextStage);
			}
		}
	}

	public void setVisibleHeight(int h) {
		// Do nothing
	}

	public boolean needsKeyboard() {
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			onBack();
			return true;
		}
		return super.keyDown(keycode);
	}

	protected void toNextStage(Class<? extends GameStage> stageClass) {
		mNextClass = stageClass;
		addActor(new Loading());
	}

	public void onResume() {
	}

	public void onBack() {
	}

	public void onSoftKeyboardHiddenRaw() {
		if (mSoftKeyboardWasHiddenBefore) {
			onSoftKeyboardHidden();
		}
		mSoftKeyboardWasHiddenBefore = true;
	}

	public void onSoftKeyboardHidden() {
	}
}
