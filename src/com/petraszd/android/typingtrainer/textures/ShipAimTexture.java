package com.petraszd.android.typingtrainer.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.petraszd.android.typingtrainer.Rules;

public class ShipAimTexture extends Texture {

	public ShipAimTexture() {
		super(getMinTextureWidth(), 2, Pixmap.Format.RGBA8888);
		draw(getPixmap(), 0, 0);
	}

	private Pixmap getPixmap() {
		Pixmap p = new Pixmap(getMinTextureWidth(), 2, Pixmap.Format.RGBA8888);
		int sCount = getSegmentCount();
		for (int i = 0; i < sCount; ++i) {
			int x0 = (int) (2 * i * Rules.AIM_LENGTH);
			int x1 = (int) ((2 * i + 1) * Rules.AIM_LENGTH);
			if (x0 > Rules.STEP_SIZE) {
				p.setColor(1, 1, 1, 0.9f);
				
				p.drawLine(x0, 0, x1, 0);
				p.drawLine(x0, 1, x1, 1);
			}
		}
		return p;
	}

	private int getSegmentCount() {
		int result = (int) (Gdx.graphics.getWidth() / Rules.AIM_LENGTH);
		if (result % 2 == 0) {
			return result + 1;
		}
		return result;
	}

	private static int getMinTextureWidth() {
		return PixmapTextureHelper.getMinTextureWidth();
	}
}
