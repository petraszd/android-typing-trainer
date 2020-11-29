package com.petraszd.android.typingtrainer.token;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {

	private static final float MOVE_VOLUME = 0.4f;
	private static final float MUSIC_VOLUME = 0.4f;

	private Options mOptions;
	
	private Random mRandom;

	private Sound mShoot;
	private Sound mKill;
	private Sound[] mMoves;
	
	private Music mMusic;

	public AudioManager(Options options) {
		mRandom = new Random();
		
		mOptions = options;

		mShoot = Gdx.audio.newSound(Gdx.files.internal("audio/shoot.ogg"));
		mKill = Gdx.audio.newSound(Gdx.files.internal("audio/kill.ogg"));
		
		initMoveSounds();
		
		mMoves[1] = Gdx.audio.newSound(Gdx.files.internal("audio/move-2.ogg"));
		mMoves[2] = Gdx.audio.newSound(Gdx.files.internal("audio/move-3.ogg"));
		
		mMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/music.ogg"));
		mMusic.setLooping(true);
		mMusic.setVolume(MUSIC_VOLUME);

		startBackgroundMusic();
	}


	public void shoot() {
		play(mShoot);
	}

	public void kill() {
		play(mKill);
	}

	public void move() {
		Sound move = mMoves[mRandom.nextInt(mMoves.length)];
		long id = play(move);
		if (id != -1) {
			move.setVolume(id, MOVE_VOLUME);
		}
	}

	public void startBackgroundMusic() {
		if (mOptions.isSound()) {
			mMusic.play(); 
		}
	}

	public void stopBackgroundMusic() {
		mMusic.stop();
	}

	private long play(Sound sound) {
		if (mOptions.isSound()) {
			return sound.play();
		}
		return -1;
	}

	private void initMoveSounds() {
		mMoves = new Sound[3];
		for (int i = 0; i < mMoves.length; ++i) {
			String name = String.format("audio/move-%d.ogg", i + 1);
			mMoves[i] = Gdx.audio.newSound(Gdx.files.internal(name));
		}
	}
}
