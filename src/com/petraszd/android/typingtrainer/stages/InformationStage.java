package com.petraszd.android.typingtrainer.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.petraszd.android.typingtrainer.AssetsHelper;
import com.petraszd.android.typingtrainer.Rules;
import com.petraszd.android.typingtrainer.textures.RoundPatch;
import com.petraszd.android.typingtrainer.token.Token;

abstract public class InformationStage extends BackgroundBublesStage {

	protected BitmapFont mSlimFont;
	protected BitmapFont mBoldFont;
	protected RoundPatch mTextPlace;

	protected boolean mShowMessage;
	protected float mShowMessageTimer = 0.0f;

	protected int mCurrentRecord;

	public InformationStage(Token token) {
		super(token);
		mShowMessage = false;
		initFonts();
		mTextPlace = new RoundPatch();
	}

	@Override
	public void onBack() {
		toNextStage(StartStage.class);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		mShowMessageTimer += delta;
		if (mShowMessageTimer > 4.0f) { // TODO: to Rules
			mShowMessage = true;
		}
	}

	@Override
	public void draw() {
		super.draw();

		SpriteBatch batch = getSpriteBatch();
		batch.begin();

		beforeDraw(batch);

		float w = Gdx.graphics.getWidth() * 0.9f;
		float h = Gdx.graphics.getHeight() * 0.9f;
		float x = w * (0.05f / 0.9f);
		float y = h * (0.05f / 0.9f);
		mTextPlace.draw(batch, x, y, w, h);

		drawCentered(batch, getHeaderString(), mBoldFont, h - Rules.FONT_SIZE);
		
		customDraw(batch, w, h, x, y);
		if (mShowMessage) {
			drawCentered(batch, getMessageString(), mSlimFont, y
					+ Rules.FONT_SIZE * 1.5f);
		}
		afterDraw(batch);

		batch.end();
	}
	
	protected void drawCentered(SpriteBatch batch, String what, BitmapFont font, float y) {
		TextBounds bounds = font.getBounds(what);
		float x = (Gdx.graphics.getWidth() - bounds.width) / 2.0f;
		font.draw(batch, what, x, y);
	}
	
	protected void drawKeyValue(SpriteBatch batch, String key, String value, BitmapFont font, float y) {
		key += ":";
		value = " " + value;
		
		TextBounds keyB = font.getBounds(key);
		
		float centerX = Gdx.graphics.getWidth() / 2.0f;
		font.draw(batch, key, centerX - keyB.width, y);
		font.draw(batch, value, centerX, y);
	}

	abstract protected void customDraw(SpriteBatch batch, float w, float h, float x, float y);
	abstract protected String getMessageString();
	abstract protected String getHeaderString();

	protected void beforeDraw(SpriteBatch batch) {
	}

	protected void afterDraw(SpriteBatch batch) {
	}

	protected void initFonts() {
		mSlimFont = AssetsHelper.getFont("end-slim");
		mBoldFont = AssetsHelper.getFont("end-bold");
	}
}
