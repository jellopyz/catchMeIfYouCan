package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Main;

public class Howtoplay implements Screen{
	final Main main;
	
	public SpriteBatch batch;
	public Texture img, back;
	public Sound click, point;
	public int pointcount = 0, delay = 0;
	
	
	public Howtoplay(Main main) {
		this.main = main;
		
		//Sprite, Batch, Texture, Atlas
		batch = main.batch;
		img = new Texture(Gdx.files.internal("help.png"));
		back = new Texture(Gdx.files.internal("button/play.png"));
		
		click = Gdx.audio.newSound(Gdx.files.internal("sound/effect/click.mp3"));
		point = Gdx.audio.newSound(Gdx.files.internal("sound/effect/point.mp3"));
		
	}
	@Override
	public void render(float delta) {
		//Clear
				Gdx.gl.glClearColor(255, 255, 255, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//----------------------------------------//
			//Move Mouse//
			if(Gdx.input.getX() >= 1000 && Gdx.input.getX() <= 1064) {
				if (Gdx.input.getY() >= 680-80-64 && Gdx.input.getY() <= 680-80) {
					pointcount++;
					if (pointcount == 1)
						point.play();
				}
			
			}
			else {
				pointcount = 0;
			}
			
			//Control//
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
		        if(Gdx.input.getX() >= 1000 && Gdx.input.getX() <= 1064) {
		        	//Back to menu
		        	if (Gdx.input.getY() >= 680-80-64 && Gdx.input.getY() <= 680-80) {
		        		click.play();
		        		main.setScreen(new MainMenuScreen(main));

		        	}
		        }
			}
				
				
		batch.begin();
		batch.draw(img, 0, 0, 1280, 680);
		batch.draw(back, 1000, 80);
		batch.end();
	}

	@Override
	public void dispose() {
		img.dispose();
		back.dispose();
		point.dispose();
		click.dispose();
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
