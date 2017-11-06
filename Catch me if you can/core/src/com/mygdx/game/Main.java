package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game{
	
	public SpriteBatch batch;
	
	public void create() {
		//Sprite, Batch, Texture, Atlas
		batch = new SpriteBatch();
		//Go to MainMenuScreen
		this.setScreen(new MainMenuScreen(this));
    }
	
    public void render() {
        super.render(); //important!
    }
	
    public void dispose() {
    	batch.dispose();
    }

}
