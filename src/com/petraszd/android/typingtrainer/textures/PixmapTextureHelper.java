package com.petraszd.android.typingtrainer.textures;

import com.badlogic.gdx.Gdx;

public class PixmapTextureHelper {
	public static int getMinTextureWidth() {
		return getMinTextureDim(Gdx.graphics.getWidth());
	}
	
	public static int getMinTextureHeight() {
		return getMinTextureDim(Gdx.graphics.getHeight());
	}
	
	private static int getMinTextureDim(int dim) {
		int result = 2;
		while (result < dim) {
			result <<= 1;
		}
		return result;		
	}
}
