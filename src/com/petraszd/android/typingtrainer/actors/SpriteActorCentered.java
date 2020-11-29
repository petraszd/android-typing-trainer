package com.petraszd.android.typingtrainer.actors;

abstract public class SpriteActorCentered extends SpriteActor {
	@Override
	protected float getImageDeltaX() {
		return -mSprite.getWidth() / 2.0f;
	}
	
	@Override
	protected float getImageDeltaY() {
		return -mSprite.getHeight() / 2.0f;
	}
}
