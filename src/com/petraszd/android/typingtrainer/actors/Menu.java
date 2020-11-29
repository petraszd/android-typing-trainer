package com.petraszd.android.typingtrainer.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.petraszd.android.typingtrainer.MenuItem;
import com.petraszd.android.typingtrainer.stages.StartStage;
import com.petraszd.android.typingtrainer.token.AudioManager;
import com.petraszd.android.typingtrainer.token.Options;

public class Menu extends Actor {

	private static final int NONE = -1;
	private StartStage mStage;
	private MenuItem mItems[] = null;
	private MenuItem mActive = null;
	private Options mOptions;
	private AudioManager mAudioMgr;

	public Menu(StartStage stage, Options options, AudioManager audioMgr) {
		mOptions = options;
		mAudioMgr = audioMgr;
		mStage = stage;
		initMenuItems(getSoundName(), getRotateName(), "menu-play");
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		for (MenuItem item: mItems) {
			item.draw(batch);
		}
	}

	@Override
	public Actor hit(float x, float y) {
		if (getMenuItemIndex(x, y) != NONE) {
			return this;
		}
		return null;
	}

	// Actor
	@Override
	public boolean touchDown(float x, float y, int pointer) {
		int index = getMenuItemIndex(x, y);
		if (index == NONE) {
			return false;	
		}
		deactivateActive();
		activate(index);
		return true;
	}

	@Override
	public void touchDragged(float x, float y, int pointer) {
		int index = getMenuItemIndex(x, y); 
		if (index == NONE) {
			deactivateActive();
		} else {
			deactivateActive();
			activate(index);
		}
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		if (mActive == null) {
			return;
		}

		deactivateActive();
		int index = getMenuItemIndex(x, y);
		if (index != NONE) {
			onTouched(index);
		}
	}

	protected String getSoundName() {
		if (mOptions.isSound()) {
			return "menu-sound-on";
		} else {
			return "menu-sound-off";
		}
	}

	protected String getRotateName() {
		if (Gdx.graphics.getWidth() > Gdx.graphics.getHeight()) {
			return "menu-portrait";
		} else {
			return "menu-landscape";
		}
	}

	protected void initMenuItems(String... filenames) {
		mItems = new MenuItem[filenames.length];
		for (int i = 0; i < filenames.length; ++i) {
			mItems[i] = initMenuItem(filenames[i], i);
		}
	}
	
	protected MenuItem initMenuItem(String filename, int index) {
		float x = Gdx.graphics.getWidth() / 2.0f;
		float y = getStepSize() * (index * 2.0f + 1.0f);
		return new MenuItem(filename, x, y + getY0(), getStepSize());
	}
	
	protected void onTouched (int index) {		
		switch (index) {
		case 0:
			if (mOptions.isSound()) {
				mOptions.setSoundOff();
				mAudioMgr.stopBackgroundMusic();
			} else {
				mOptions.setSoundOn();
				mAudioMgr.startBackgroundMusic();
			}
			mItems[0] = initMenuItem(getSoundName(), 0);
			break;
		case 1:
			mStage.rotate();
			break;
		case 2:
			mStage.play();
			break;
		default:
			break;
		}
	}
	
	protected float getStepSize() {
		float result = Gdx.graphics.getHeight() / (float) (mItems.length * 2);
		return Math.min(result, Gdx.graphics.getWidth() / (5.2f * 1.1f)); // TODO: to GAME
	}
	
	protected float getY0() {
		if (Gdx.graphics.getWidth() > Gdx.graphics.getHeight()) {
			return 0.0f;
		}
		return getStepSize() * (mItems.length - 1.0f);
	}
	
	protected int getMenuItemIndex(float x, float y) {
		for (int i = 0; i < mItems.length; ++i) {
			if (mItems[i].isTouched(x, y)) {
				return i;
			}
		}
		return NONE; // Stupid idea...
	}
	
	protected void deactivateActive() {
		if (mActive != null) {
			mActive.scaleDown();
			mActive = null;
		}
	}
	
	protected void activate(int index) {
		mActive = mItems[index];
		mActive.scaleUp();
	}
}
