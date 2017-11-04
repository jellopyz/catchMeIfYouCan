package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character {
	int pos_x = 0, pos_y = 0, stop = 0, size_x = 100, size_y = 100;
	float elapsedTime = 0;
	TextureAtlas atlas;
	TextureRegion currentFrame;
	Animation go_up, go_down, go_right, go_left, stand;
	
	public Character() {
		
	}
	public Character(int pos_x, int pos_y){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}
	public Character(int pos_x, int pos_y, int size_x, int size_y){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.size_x = size_x;
		this.size_y = size_y;
	}
}
