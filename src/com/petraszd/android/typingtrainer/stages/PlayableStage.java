package com.petraszd.android.typingtrainer.stages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.petraszd.android.typingtrainer.Rules;
import com.petraszd.android.typingtrainer.actors.Background;
import com.petraszd.android.typingtrainer.actors.Buble;
import com.petraszd.android.typingtrainer.actors.Bullet;
import com.petraszd.android.typingtrainer.actors.DeadLine;
import com.petraszd.android.typingtrainer.actors.Enemy;
import com.petraszd.android.typingtrainer.actors.OnError;
import com.petraszd.android.typingtrainer.actors.PauseLabelActor;
import com.petraszd.android.typingtrainer.actors.PointViewer;
import com.petraszd.android.typingtrainer.actors.Ship;
import com.petraszd.android.typingtrainer.actors.ShipAim;
import com.petraszd.android.typingtrainer.token.Token;
import com.petraszd.android.typingtrainer.words.WordActionsListener;


public class PlayableStage extends GameStage implements WordActionsListener {
	private Ship mShip;
	private ShipAim mShipAim;
	private DeadLine mDeadLine;
	private Background mBackground;
	private OnError mOnError;
	private Group mBullets;
	private Group mEnemies;
	private Group mDyingEnemies;
	private Group mBubles;
	private PointViewer mPointViewer;
	private List<Float> mEnemyY;
	private Random mRandom;
	private float mRandomEnemyBubleTimer = 0.0f;
	private float mEnemyTimer = 0.0f;
	private float mHeight = 0.0f;
	private boolean mHeightSetted = false;
	
	private boolean mIsDying = false;
	private float mEndTimer = 0.0f;
	
	private boolean mIsPause = false;
	private PauseLabelActor mPauseLabel;
	
	public PlayableStage(Token token) {
		super(token);
		Rules.init();
		token.startGame();

		mRandom = new Random();
		
		mBackground = new Background(); 
		addActor(mBackground);
		
		mDeadLine = new DeadLine();
		addActor(mDeadLine);
		
		mShipAim = new ShipAim();
		addActor(mShipAim);

		mBullets = new Group("bullets");
		addActor(mBullets);
		
		mEnemies = new Group("enemies");
		addActor(mEnemies);
		
		mDyingEnemies = new Group("dying-enemies");
		addActor(mDyingEnemies);
		
		mShip = new Ship(this, mShipAim);
		addActor(mShip);
		
		mBubles = new Group("bubles");
		addActor(mBubles);
		
		mOnError = new OnError();
		addActor(mOnError);

		mPointViewer = new PointViewer();
		addActor(mPointViewer);
		
		mPauseLabel = new PauseLabelActor();
		addActor(mPauseLabel);
		
		setVisibleHeight(0);
		mHeightSetted = false;
		mEnemyTimer = Rules.ENEMY_SPAWN_TIMEOUT; // Generate enemy at start;
	}
	
	@Override
	public void act(float delta) {
		if (mNextClass != null) {
			super.act(delta);
		} else if (!mIsDying && !mIsPause) {
			super.act(delta);
			
			checkForEnemySpawn(delta);
			checkForKills();
			checkForCrossedLine();
			checkForRandomBuble(delta);
		} else if (mIsDying){
			mEndTimer += delta;
			if (mEndTimer > Rules.GAME_OVER_TIMER) {
				toNextStage(EndStage.class);
			}
		}
	}
	
	@Override
	public boolean needsKeyboard() {
		return true;
	}
	
	@Override
	public void onBack() {
		if (!mIsPause) {
			pause();
		} else {
			toNextStage(StartStage.class);
		}
	}
	
	@Override
	public void onSoftKeyboardHidden() {
		onBack();
	}
	
	public void setVisibleHeight(int h) {
		if (!mHeightSetted) {
			mEnemyY = getPossibleEnemyYValues(h);
			mShip.setHeight(h);
			mBackground.setHeight(h);
			mOnError.setheight(h);
			mPauseLabel.setHeight(h);
			mHeight = h;
			mHeightSetted = true;
			Rules.setSpeeds(mEnemyY.size());
		}
	}

	public void createBullet(float x, float y) {
		mToken.getAudioManager().shoot();
		mBullets.addActor(new Bullet(x, y));
	}
	
	public void spawnEnemy() {
		float x = Gdx.graphics.getWidth();
		float y = mEnemyY.get(mRandom.nextInt(mEnemyY.size()));
		
		Enemy enemy = new Enemy(x, y, mHeight);
		mEnemies.addActor(enemy);
	}
	
	@Override
	public void onResume() {
		mShipAim.onResume();
	}

	public void onValidPress() {
		mToken.getStatistics().incGood();
		mToken.getPointManager().correct();
		updatePointViewer();
	}

	public void onInvalidPress() {
		mToken.getStatistics().incBad();
		mToken.getPointManager().mistake();
		updatePointViewer();
		Gdx.input.vibrate(Rules.INVALID_KEY_VIBR_TIME);
		mOnError.turnOn();
	}
	
	public void makeShipBubles(float shipX, float shipY) {
		if (mRandom.nextInt(2) == 1) {
			makeShipBuble(shipX + Rules.STEP_SIZE * 0.4f, shipY + Rules.STEP_SIZE * 0.5f);
		}
		if (mRandom.nextInt(2) == 1) {
			makeShipBuble(shipX, shipY + Rules.STEP_SIZE * 0.4f);
		}
		if (mRandom.nextInt(2) == 1) {
			makeShipBuble(shipX - Rules.STEP_SIZE * 0.7f, shipY + Rules.STEP_SIZE * 0.8f);
		}
		mToken.getAudioManager().move();
	}

	@Override
	public boolean keyTyped(char character) {
		return mShip.keyTyped(character);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (mIsPause) {
			resumeAfterPause();
			return true;
		}
		return super.touchDown(x, y, pointer, button);
	}
	
	private void makeShipBuble(float bubleX, float bubleY) {
		mBubles.addActor(new Buble(bubleX, bubleY));
	}
	
	private void resumeAfterPause() {
		mPauseLabel.turnOff();
		mIsPause = false;
	}
	
	private void pause() {
		mPauseLabel.turnOn();
		mIsPause = true;
	}
	
	private List<Float> getPossibleEnemyYValues(int h) {
		List<Float> result = new ArrayList<Float>();
		float y0 = (Gdx.graphics.getHeight() + h) / 2.0f;
		
		float y = y0;
		while (y < Gdx.graphics.getHeight() - Rules.STEP_SIZE) {
			result.add(y);
			y += Rules.STEP_SIZE;
		}
		
		y = y0 - Rules.STEP_SIZE;
		while (y > h + Rules.STEP_SIZE) {
			result.add(y);
			y -= Rules.STEP_SIZE;
		}
		return result;
	}
	
	private void checkForKills() {
		List<Actor> toBeRemoved = new ArrayList<Actor>();
		for (Actor bulletActor : mBullets.getActors()) {
			Bullet bullet = (Bullet)bulletActor;
			for(Actor enemy : mEnemies.getActors()) {
				if (enemy.hit(bullet.getX(), bullet.getY()) != null) {
					toBeRemoved.add(enemy);
					bullet.onKill();
					mToken.getAudioManager().kill();
					mToken.getPointManager().shooted(bullet.getKillCount());
					updatePointViewer();
				}
			}
		}
		
		for (Actor enemy: toBeRemoved) {
			mEnemies.removeActor(enemy);
			mDyingEnemies.addActor(enemy);
			((Enemy) enemy).startDying();
		}
		
		if (mEnemies.getActors().size() == 0) {
			mEnemyTimer = Rules.ENEMY_SPAWN_TIMEOUT;
		}
	}
	
	private void checkForEnemySpawn(float delta) {
		mEnemyTimer += delta;
		if (mEnemyTimer > Rules.ENEMY_SPAWN_TIMEOUT) {
			mEnemyTimer = 0.0f;
			spawnEnemy();
		}
	}
	
	private void checkForRandomBuble(float delta) {
		mRandomEnemyBubleTimer -= delta;
		if (mRandomEnemyBubleTimer < 0.0f) {
			makeEnemyBuble();
			mRandomEnemyBubleTimer = mRandom.nextFloat() * Rules.ENEMY_BUBLE_TIMEOUT;
		}
	}
	
	private void makeEnemyBuble() {
		int size = mEnemies.getActors().size();
		if (size <= 0) {
			return;
		}
		int index = mRandom.nextInt(size);
		Enemy enemy = (Enemy) mEnemies.getActors().get(index);
		mBubles.addActor(new Buble(enemy.getBubleX(), enemy.getBubleY()));
	}
	
	private void checkForCrossedLine() {
		float nearestX = getNearestEnemyX();
		if (nearestX < Rules.STEP_SIZE * 4.2f) {
			mToken.endGame();
			mIsDying = true;
			mOnError.turnOn();
			Gdx.input.vibrate(Rules.GAME_OVER_VIBR_TIME);
			mEndTimer = 0.0f;
		} else if (nearestX < Rules.STEP_SIZE * 8.2f) { // TODO: refactor
			float alpha = 1.0f - Math.abs(Rules.STEP_SIZE * 4.2f - nearestX) / (Rules.STEP_SIZE * 4.0f);
			mDeadLine.setAlpha(alpha);
		} else {
			mDeadLine.setAlpha(0.0f);
		}
	}
	
	private float getNearestEnemyX() {
		float min = Float.MAX_VALUE;
		for(Actor actor : mEnemies.getActors()) {
			Enemy enemy = (Enemy) actor;
			if (enemy.getX() < min) {
				min = enemy.getX();
			}
		}
		return min;
	}
	
	private void updatePointViewer() {
		mPointViewer.setPoints(mToken.getPointManager().getPoints());
	}
}