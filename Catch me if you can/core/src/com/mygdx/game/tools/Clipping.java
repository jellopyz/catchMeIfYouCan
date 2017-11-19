package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;

public class Clipping {
	public static float check_x(float player_x, int size_x) {
		float x = player_x;
		if (x > 1280 - size_x)
		{
			x = 1280 - size_x;
		}
		else if (x < 0)
		{
			x = 0;
		}
		return x;
	}
	public static float check_y(float player_y, int size_y) {
		float y = player_y;
		if (y > 680 - size_y)
		{
			y = 680 - size_y;
		}
		else if (y < 0) {
			y = 0;
		}
		return y;
	}
}
