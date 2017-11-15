package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;

public class Clipping {
	public static int check_x(int player_x, int size_x) {
		int x = player_x;
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
	public static int check_y(int player_y, int size_y) {
		int y = player_y;
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
