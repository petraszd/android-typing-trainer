package com.petraszd.android.typingtrainer.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class AbstractPatch extends NinePatch {
	public AbstractPatch(Texture t) {
		super(t, getDim(t), getDim(t), getDim(t), getDim(t));
	}
	
	public static int getDim(Texture texture) {
		return texture.getWidth() / 4;
	}
}
