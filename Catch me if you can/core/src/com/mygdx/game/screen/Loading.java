package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Main;

public class Loading implements Screen{
	final Main main;
	
	public SpriteBatch batch;
	public TextureAtlas atlas;
	public TextureRegion currentFrame;
	public Animation anime;
	public float elapsedTime = 0;
	
	public Loading(Main main) {
		this.main = main;
		
		this.batch = main.batch;
		
		atlas = new TextureAtlas(Gdx.files.internal("loading1/loading.atlas"));
		anime = new Animation(1/5f, atlas.findRegion("loading1"),atlas.findRegion("loading2"),
				atlas.findRegion("loading3"),atlas.findRegion("loading4"),atlas.findRegion("loading5"));
//		anime = new Animation(1/5f, atlas.findRegion("loading1"));
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        currentFrame = (TextureRegion) anime.getKeyFrame(elapsedTime, true);
        
        elapsedTime += 2*Gdx.graphics.getDeltaTime();
        
        batch.begin();
        batch.draw(currentFrame, 0, 0, 1280, 680);
        batch.end();
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		atlas.dispose();
		
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
