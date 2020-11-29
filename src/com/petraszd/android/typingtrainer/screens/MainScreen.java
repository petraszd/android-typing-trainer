package com.petraszd.android.typingtrainer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.petraszd.android.typingtrainer.Rules;
import com.petraszd.android.typingtrainer.TypingTrainerGame;
import com.petraszd.android.typingtrainer.stages.GameStage;
import com.petraszd.android.typingtrainer.stages.StartStage;
import com.petraszd.android.typingtrainer.token.Token;

public class MainScreen implements Screen {

	private TypingTrainerGame mGame;
	private OrthographicCamera mCam;
	private GameStage mCurrentStage;
	private boolean mWaitingForKeyboard = false;
	private boolean mEmulateBack = false;

	public MainScreen(TypingTrainerGame game) {
		mGame = game;

		initCamera();
		initStages();
	}

	public void dispose() {
	}

	public void hide() {
		hideKeyboard();
	}

	public void pause() {
	}

	public void resume() {
		if (mCurrentStage != null) {
			mCurrentStage.onResume();
		}
	}

	public void show() {
		hideKeyboard();
	}

	public void render(float delta) {
		clearScreen();

		if (mCurrentStage.needsKeyboard() && needsSoftKeyboard()) {
			mWaitingForKeyboard = true;
			Gdx.input.setOnscreenKeyboardVisible(true);
			mEmulateBack = true;
		}

		if (mWaitingForKeyboard) {
			Gdx.input.setOnscreenKeyboardVisible(true);
			if (IsKeyboardOpen()) {
				mCurrentStage.setVisibleHeight(mGame.getVisibleHeight());
				mWaitingForKeyboard = false;
				if (mEmulateBack) {
					mCurrentStage.onSoftKeyboardHiddenRaw();
					mEmulateBack = false;
				}
			}
		} else {
			mCurrentStage.act(delta);
			mCurrentStage.draw();
			changeStages();
		}
	}

	public void resize(int w, int h) {
		Rules.init();
		changeCamera(w, h);
	}

	protected void clearScreen() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	protected void initCamera() {
		mCam = new OrthographicCamera(1.0f, 1.0f);
	}

	protected void changeCamera(int w, int h) {
		mCam.viewportWidth = w;
		mCam.viewportHeight = h;
		mCam.update();
		mCam.apply(Gdx.gl10);
	}

	protected void initStages() {
		Token token = new Token(mGame);

		Rules.init(); // Needs to be called before stage construction
		mCurrentStage = new StartStage(token);
	}

	protected boolean needsSoftKeyboard() {
		if (mGame.hasHardwareKeyboard()) {
			return false;
		}
		return !IsKeyboardOpen();
	}
	
	protected boolean IsKeyboardOpen() {
		return mGame.getVisibleHeight() > Rules.MIN_KEYBOARD_SIZE;
	}

	protected void hideKeyboard() {
		mEmulateBack = false;
		if (!mGame.hasHardwareKeyboard()) {
			Gdx.input.setOnscreenKeyboardVisible(false);
		}
	}

	protected void changeStages() {
		mCurrentStage = mCurrentStage.getNextStage();
		if (!mCurrentStage.needsKeyboard()) {
			hideKeyboard();
		}
	}
}
;