package com.mygdx.game.item;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Bone {
	
	public int pos_x = 0, pos_y = 0, size_x = 40, size_y = 40;
	public Rectangle rect;
	public TextureAtlas atlas;
	public TextureRegion currentFrame;
	public Animation anime;
	public float elapsedTime = 0;
	public boolean pickup = false;
	public boolean slow = false;
	public boolean throwup = false;
	public boolean throwdown = false;
	public boolean throwright = false;
	public boolean throwleft = false;
	
	public Bone(int pos_x, int pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		rect = new Rectangle(pos_x, pos_y, size_x, size_y);
	}
}
