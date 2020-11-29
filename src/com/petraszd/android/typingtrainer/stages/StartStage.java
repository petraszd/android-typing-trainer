package com.petraszd.android.typingtrainer.stages;

import com.badlogic.gdx.Gdx;
import com.petraszd.android.typingtrainer.actors.Menu;
import com.petraszd.android.typingtrainer.token.Token;

public class StartStage extends BackgroundBublesStage {

	protected Menu mMenu;

	public StartStage(Token token) {
		super(token);
		
		mMenu = new Menu(this, mToken.getOptions(), mToken.getAudioManager());
		addActor(mMenu);
	}
	
	public void rotate() {
		if (Gdx.graphics.getWidth() > Gdx.graphics.getHeight()) {
			mToken.getGame().toPortrait();	
		} else {
			mToken.getGame().toLandscape();
		}	
	}
	
	public void play() {
		if (mToken.getOptions().isFirstRun()) {
			mToken.getOptions().markFirstRun();
			toNextStage(TutorialStage.class);
		} else {
			toNextStage(PlayableStage.class);
		}
	}
	
	@Override
	public void onBack() {
		mToken.getGame().quit();
	}
}
