package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Character {
	int pos_x = 0, pos_y = 0, stop = 0, size_x = 48, size_y = 48;
	Rectangle rect = new Rectangle(pos_x, pos_y, size_x, size_y);
	float elapsedTime = 0;
	TextureAtlas atlas;
	TextureRegion currentFrame;
	Animation go_up, go_down, go_right, go_left, stand;
	
	public Character() {
		
	}
	public Character(int pos_x, int pos_y){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		rect.x = pos_x;
		rect.y = pos_y;
	}
	public Character(int pos_x, int pos_y, int size_x, int size_y){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.size_x = size_x;
		this.size_y = size_y;
		rect.x = pos_x;
		rect.y = pos_y;
	}
}
