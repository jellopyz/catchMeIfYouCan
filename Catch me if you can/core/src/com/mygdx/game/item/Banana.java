package com.mygdx.game.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Banana {
	
	public int pos_x = 0, pos_y = 0, stop = 0, size_x = 40, size_y = 40;
	public Rectangle rect;
	public boolean peel = false;
	
	public Banana(int pos_x, int pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		rect = new Rectangle(pos_x, pos_y, size_x, size_y);
	}
}

