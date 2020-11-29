package com.petraszd.android.typingtrainer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Allows to reach different images and fonts on bigger/lower screens
 */
public class AssetsHelper {
	public static Texture getTexture(String name) {
		Texture t = new Texture(Gdx.files.internal(toTexFilename(name)));
		t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return t;
	}
	
	public static BitmapFont getFont(String name) {
		return getFont(name, name);
	}

	public static BitmapFont getFont(String description, String image) {
		FileHandle hDescp = Gdx.files.internal(toFontFilename(description, "fnt"));
		FileHandle hImage = Gdx.files.internal(toFontFilename(image, "png"));
		return new BitmapFont(hDescp, hImage, false);
	}
	
	private static String toTexFilename(String name) {
		if (Rules.IS_HD) {
			return String.format("images/hd/%s.png", name);
		} else if (Rules.IS_MD){
			return String.format("images/md/%s.png", name);
		} else {
			String result = String.format("images/ld/%s.png", name);
			FileHandle handle = Gdx.files.internal(result);
			if (handle.exists()) {
				return result;
			}
			return String.format("images/md/%s.png", name);
		}
	}
	
	private static String toFontFilename(String name, String postfix) {
		if (Rules.IS_HD) {
			return String.format("fonts/hd/%s.%s", name, postfix);
		} else if (Rules.IS_MD) {
			return String.format("fonts/md/%s.%s", name, postfix);
		} else { // IS_LD
			return String.format("fonts/ld/%s.%s", name, postfix);
		}
	}
}
