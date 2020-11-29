package com.petraszd.android.typingtrainer.words;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.petraszd.android.typingtrainer.Rules;
import com.petraszd.android.typingtrainer.actors.Ship;
import com.petraszd.android.typingtrainer.textures.FadedArrowPatch;
import com.petraszd.android.typingtrainer.textures.NormalArrowPatch;

abstract public class WordAction {

	public final static int BAD = 1;
	public final static int GOOD = 2;
	public final static int FINISHED = 3;

	protected String mCurrentWord = "";
	protected int mFilled = 0;
	protected BitmapFont mFadedFont;
	protected BitmapFont mNormalFont;
	protected Ship mShip;
	protected boolean mEnabled = true;

	NormalArrowPatch mNormalPatch;
	FadedArrowPatch mFadedPatch;

	public WordAction(Ship ship, BitmapFont faded, BitmapFont normal) {
		mShip = ship;
		mFadedFont = faded;
		mNormalFont = normal;
		
		mCurrentWord = WordGetterManager.getInstance().getter(getWordLength())
				.getRandom();
		mFilled = 0;

		mNormalPatch = new NormalArrowPatch();
		mFadedPatch = new FadedArrowPatch();
	}

	public char generateCurrentWord(List<Character> notStartingWith) {
		mCurrentWord = WordGetterManager.getInstance().getter(getWordLength())
				.getRandom(notStartingWith);
		mFilled = 0;
		return getFirstNotFilled();
	}

	public char getFirstNotFilled() {
		return mCurrentWord.charAt(mFilled);
	}

	public void backspace() {
		if (isFilled()) {
			mFilled--;
		}
	}

	public boolean isFilled() {
		return mFilled > 0;
	}

	public void draw(SpriteBatch batch, float x, float y) {
		if (!mEnabled) {
			return;
		}
		float realY = y + Rules.FONT_SIZE_HALF;

		String faded = mCurrentWord.substring(mFilled);
		String normal = mCurrentWord.substring(0, mFilled);

		TextBounds fadedBounds = mFadedFont.getBounds(faded);
		TextBounds normalBounds = mNormalFont.getBounds(normal);

		float wordWidth = normalBounds.width + fadedBounds.width;
		float wordHeight = Math.max(normalBounds.height, fadedBounds.height);

		float patchY;
		if (Rules.IS_HD) {
			patchY = y + Rules.FONT_SIZE_HALF * 0.75f;
		} else {
			patchY = y + Rules.FONT_SIZE_HALF * 0.625f;
		}
		drawBackground(batch, x, patchY, wordWidth, wordHeight);

		mNormalFont.draw(batch, normal, x, realY);
		mFadedFont.draw(batch, faded, x + normalBounds.width, realY);
	}

	public int tryCharacter(char character) {
		char currentChar = mCurrentWord.charAt(mFilled);
		if (currentChar != character) {
			return BAD;

		}
		mFilled += 1;
		if (mFilled == mCurrentWord.length()) {
			onFinish();
			return FINISHED;
		}
		return GOOD;
	}

	public void setEnabled(boolean active) {
		mEnabled = active;
	}

	public boolean isEnabled() {
		return mEnabled;
	}

	protected void drawBackground(SpriteBatch batch, float x, float y,
			float wordWidth, float wordHeight) {
		float px = x - Rules.FONT_SIZE_HALF / 2.0f;
		float py = y - wordHeight - Rules.FONT_SIZE_HALF / 2.0f;
		float pw = wordWidth + Rules.FONT_SIZE_HALF;
		float ph = wordHeight + Rules.FONT_SIZE_HALF;
		if (mFilled > 0) {
			mNormalPatch.draw(batch, px, py, pw, ph);
		} else {
			mFadedPatch.draw(batch, px, py, pw, ph);
		}
	}

	protected abstract void onFinish();
	protected abstract int getWordLength();
}
