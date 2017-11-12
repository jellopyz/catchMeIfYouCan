package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Chapter2 implements Screen{
	final Main main;
	
	public SpriteBatch batch;
	public Texture background;
	public Character player1;
	public Character player2;
	
	public Chapter2(Main main) {
		this.main = main;
		
		//Sprite, Batch, Texture, Atlas
		batch = main.batch;
		background = new Texture("map/map2.jpg");
		//Objects
		player1 = new Character();
		player2 = new Character(1280, 680);
		/*-------------Texture,Animation of Objects-----------*/
		//Player1
		player1.atlas = new TextureAtlas(Gdx.files.internal("player/player1.atlas"));
		player1.go_up = new Animation(1/2f, player1.atlas.findRegion("walkback1"), player1.atlas.findRegion("walkback2"));
		player1.go_down = new Animation(1/2f, player1.atlas.findRegion("walk1"), player1.atlas.findRegion("walk2"));
		player1.go_right = new Animation(1/2f, player1.atlas.findRegion("walkR1"), player1.atlas.findRegion("walkR2"), player1.atlas.findRegion("walkR3"));
		player1.go_left = new Animation(1/2f, player1.atlas.findRegion("walkL1"), player1.atlas.findRegion("walkL2"), player1.atlas.findRegion("walkL3"));
		player1.stand = new Animation(1/2f, player1.atlas.findRegion("stand"), player1.atlas.findRegion("standback"), player1.atlas.findRegion("standR"), player1.atlas.findRegion("standL"));
		//Player2
		player2.atlas = new TextureAtlas(Gdx.files.internal("player/player1.atlas"));
		player2.go_up = new Animation(1/2f, player1.atlas.findRegion("walkback1"), player1.atlas.findRegion("walkback2"));
		player2.go_down = new Animation(1/2f, player1.atlas.findRegion("walk1"), player1.atlas.findRegion("walk2"));
		player2.go_right = new Animation(1/2f, player1.atlas.findRegion("walkR1"), player1.atlas.findRegion("walkR2"), player1.atlas.findRegion("walkR3"));
		player2.go_left = new Animation(1/2f, player1.atlas.findRegion("walkL1"), player1.atlas.findRegion("walkL2"), player1.atlas.findRegion("walkL3"));
		player2.stand = new Animation(1/2f, player1.atlas.findRegion("stand"), player1.atlas.findRegion("standback"), player1.atlas.findRegion("standR"), player1.atlas.findRegion("standL"));
	}

	@Override
	public void render(float delta) {
		//Clear
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//----------------------------------------//
		
		//Change map
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
			main.setScreen(new Chapter3(main));
			//dispose();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			main.setScreen(new Chapter1(main));
			//dispose();
		}
		//Check Clipping//
		player1.pos_x = Clipping.check_x(player1.pos_x, player1.size_x);
		player1.pos_y = Clipping.check_y(player1.pos_y, player1.size_y);
		
		player2.pos_x = Clipping.check_x(player2.pos_x, player2.size_x);
		player2.pos_y = Clipping.check_y(player2.pos_y, player2.size_y);
		//-----------------------------------//
		
		
		//Player1 Standing//
		if (player1.stop == 0)
			player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0, true);
		if (player1.stop == 1)
			player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0.5f, true);
		if (player1.stop == 2)
			player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1f, true);
		if (player1.stop == 3)
			player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1.5f, true);
		//-------------------------------------------------------------------------------//
		
		//Player2 Standing//
		if (player2.stop == 0)
			player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0, true);
		if (player2.stop == 1)
			player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0.5f, true);
		if (player2.stop == 2)
			player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1f, true);
		if (player2.stop == 3)
			player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1.5f, true);
		//-------------------------------------------------------------------------------//
		
		
		//Player1 Movement//
		if (true) {
			if(Gdx.input.isKeyPressed(Input.Keys.UP)){
	            player1.currentFrame = (TextureRegion) player1.go_up.getKeyFrame(player1.elapsedTime, true);
	            player1.pos_y += 5;
	            player1.elapsedTime += 2*Gdx.graphics.getDeltaTime();
	            player1.stop = 1;
	        }
			else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				player1.currentFrame = (TextureRegion) player1.go_down.getKeyFrame(player1.elapsedTime, true);
				player1.pos_y -= 5;
				player1.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player1.stop = 0;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				player1.currentFrame = (TextureRegion) player1.go_right.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x += 5;
				player1.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player1.stop = 2;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				player1.currentFrame = (TextureRegion) player1.go_left.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x -= 5;
				player1.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player1.stop = 3;
			}
		}
		//-----------------------------------------------------------------------------------------------------//
		
		//Player2 Movement//
		if (true) {
			if(Gdx.input.isKeyPressed(Input.Keys.W)) {
				player2.currentFrame = (TextureRegion) player2.go_up.getKeyFrame(player2.elapsedTime, true);
			    player2.pos_y += 5;
			    player2.elapsedTime += 2*Gdx.graphics.getDeltaTime();
			    player2.stop = 1;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
				player2.currentFrame = (TextureRegion) player2.go_down.getKeyFrame(player2.elapsedTime, true);
				player2.pos_y -= 5;
				player2.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player2.stop = 0;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
				player2.currentFrame = (TextureRegion) player2.go_right.getKeyFrame(player2.elapsedTime, true);
				player2.pos_x += 5;
				player2.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player2.stop = 2;
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
				player2.currentFrame = (TextureRegion) player2.go_left.getKeyFrame(player2.elapsedTime, true);
				player2.pos_x -= 5;
				player2.elapsedTime += 2*Gdx.graphics.getDeltaTime();
				player2.stop = 3;
			}
		}
		//-----------------------------------------------------------------------------------------------------//
		
		
		//Graphics Rendering//
		batch.begin();
		batch.draw(background, 0, 0, 1280, 680);
		batch.draw(player1.currentFrame, player1.pos_x, player1.pos_y, player1.size_x, player1.size_y);
		batch.draw(player2.currentFrame, player2.pos_x, player2.pos_y, player2.size_x, player2.size_y);
		batch.end();
		//---------------------------------------------------------------------------------//
		
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
		player1.atlas.dispose();
		player2.atlas.dispose();
		
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
