package com.petraszd.android.typingtrainer;

import com.badlogic.gdx.Gdx;

final public class Rules {

	public static void setSpeeds(int lines) {
		float steps = Gdx.graphics.getWidth() / STEP_SIZE;

		// TODO: spend more time with this formula
		ENEMY_SPEED = 2.0f + Math.abs(1.5f * steps - lines * 1.0f);
		setEnemySpawnSpeed();
	}

	public static void init() {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		int size = Math.max(w, h); 
		if (size >= Rules.RESOLUTION_HD) {
			initHd();
		} else if (size >= Rules.RESOLUTION_MD){
			initMd();
		} else {
			initLd();
		}
		
		if (w > h) {
			STEP_SIZE = h / (3.0f * 3.0f);
		} else {
			STEP_SIZE = h / (6.0f * 3.0f);
		}

		STEP_SIZE_HALF = STEP_SIZE / 2;
		FONT_SIZE_HALF = FONT_SIZE / 2;
		ENEMY_DYING_ALPHA_SPEED = STEP_SIZE / 10.0f;
		ENEMY_DYING_MOVING_SPEED = STEP_SIZE * 3.0f / 8.0f;

		BACKGROUND_GROUND_ANIM_SPEED = STEP_SIZE / 200.0f;
		BUBLE_SCALE_SPEED = STEP_SIZE / 20.0f;
		BUBLE_SPEED = STEP_SIZE / 2.0f;
		BUBLE_SPEED_RANDOM = STEP_SIZE / 4.0f;
		BACKGROUND_BUBLES_RANDOM_X = STEP_SIZE_HALF;

		LEVEL = 1;
		LEVEL_POINTS = 0;
		MOVE_WORD_LENGTH = 2;
		SHOOT_WORD_LENGTH = 4;
	}

	public static void onPointsChanged(int points) {
		// If mistakes are made on level changing than
		// level would be increased almost step
		if (points > LEVEL_POINTS + 100) { // TODO: to const
			LEVEL_POINTS += 100;
			LEVEL++;
			onLevelChange(LEVEL);
		}
	}
	
	private static void setEnemySpawnSpeed() {
		ENEMY_SPAWN_TIMEOUT = STEP_SIZE * 3.0f / ENEMY_SPEED * 0.9f;
	}

	private static void onLevelChange(int level) {
		switch (level) {
		// hardcoded levels
		case 2:
			SHOOT_WORD_LENGTH++;
			break;
		case 3:
			SHOOT_WORD_LENGTH++;
			ENEMY_SPEED *= 1.2f;
			break;
		case 4:
			SHOOT_WORD_LENGTH++;
			break;
		case 5:
			MOVE_WORD_LENGTH++;
			break;

		// Allows to play forever
		default:
			ENEMY_SPEED *= 1.2f;
			break;
		}
		setEnemySpawnSpeed();
	}

	private static void initHd() {
		IS_HD = true;
		IS_MD = false;
		IS_LD = false;

		AIM_LENGTH = 30;
		FONT_SIZE = 64;
		FONT_SIZE_POINTS = 48;
		FONT_SIZE_BIG = 96;
	}
	
	private static void initMd() {
		IS_MD = true;
		IS_HD = false;
		IS_LD = false;
		
		AIM_LENGTH = 15;
		FONT_SIZE = 32;
		FONT_SIZE_POINTS = 24;
		FONT_SIZE_BIG = 64;
	}

	private static void initLd() {
		IS_LD = true;
		IS_MD = false;
		IS_HD = false;

		AIM_LENGTH = 8;
		FONT_SIZE = 12;
		FONT_SIZE_POINTS = 10;
		FONT_SIZE_BIG = 18;
	}

	// Level
	public static int LEVEL_POINTS = 0;
	public static int LEVEL = 1;
	public static int MOVE_WORD_LENGTH = 2;
	public static int SHOOT_WORD_LENGTH = 4;

	public static float ENEMY_SPEED = 25.0f;
	public static float ENEMY_SPAWN_TIMEOUT = 5.0f;

	// Resolution
	public static boolean IS_HD = false;
	public static boolean IS_MD = false;
	public static boolean IS_LD = false;
	
	public static float STEP_SIZE = 40;
	public static float STEP_SIZE_HALF = 20;
	public static float AIM_LENGTH = 15;
	public static int FONT_SIZE = 32;
	public static int FONT_SIZE_HALF = 16;
	public static int FONT_SIZE_POINTS = 24;
	public static int FONT_SIZE_BIG = 64;
	public static float ENEMY_DYING_ALPHA_SPEED = 4.0f;
	public static float ENEMY_DYING_MOVING_SPEED = 15.0f;
	public static float BACKGROUND_GROUND_ANIM_SPEED = 0.2f;
	public static float BUBLE_SCALE_SPEED = 2.0f;
	public static float BUBLE_SPEED = 20.0f;
	public static float BUBLE_SPEED_RANDOM = 5.0f;
	public static float BACKGROUND_BUBLES_RANDOM_X = STEP_SIZE_HALF;

	// Constants
	public final static float SHIP_ANIM_SPEED = 8.0f;
	public final static int WORD_MAX_LENGTH = 10;
	public final static int WORD_MIN_LENGTH = 2;
	public final static float BULLET_SPEED = 500.0f;
	public final static int ENEMY_DYING_COUNTER = 3;
	public final static float ENEMY_DYING_TIMER = 1.0f;
	public final static float ENEMY_DYING_MAX_ALPHA = 0.8f;
	public final static float ENEMY_BUBLE_TIMEOUT = 5.0f;
	public final static float BACKGROUND_ON_ERROR_TIMEOUT = 1.0f;
	public final static float BACKGROUND_BUBLES_TIMER = 1.0f;
	public final static float BACKGROUND_BUBLES_P_BIRTH = 0.05f;

	public final static int INVALID_KEY_VIBR_TIME = 100;
	public final static int GAME_OVER_VIBR_TIME = 400;
	public final static float GAME_OVER_TIMER = 2.0f;

	public final static int POINTS_MISTAKE = -5;
	public final static int POINTS_CORRECT = 1;
	public final static int POINTS_KILL = 10;
	public final static int POINTS_KILL_INC = 5;

	public final static int RESOLUTION_HD = 1024;
	public final static int RESOLUTION_MD = 600;

	// Unsorted
	public final static int MIN_KEYBOARD_SIZE = 100;
}
