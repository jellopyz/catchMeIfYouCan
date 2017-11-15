package test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Character;
import com.mygdx.game.tools.Clipping;

public class Oldmain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	TextureAtlas menu, start, exit;
	TextureRegion menucurrentFrame, startcurrentFrame, exitcurrentFrame;
	Animation menu_anime, start_anime, exit_anime;
	Character player1 = new Character();
	Character player2 = new Character(700, 600);
	int stage = 0;
	float menutime = 0, delay = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("map.png");
		menu = new TextureAtlas(Gdx.files.internal("menu.atlas"));
		start = new TextureAtlas(Gdx.files.internal("start.atlas"));
		exit = new TextureAtlas(Gdx.files.internal("exit.atlas"));
		
		menu_anime = new Animation(1.5f, menu.getRegions());
		start_anime = new Animation(1/2f, start.getRegions());
		exit_anime = new Animation(1/2f, exit.getRegions());
		player1.atlas = new TextureAtlas(Gdx.files.internal("player/player1.atlas"));
		player1.go_up = new Animation(1/2f, player1.atlas.findRegion("walkback1"), player1.atlas.findRegion("walkback2"));
		player1.go_down = new Animation(1/2f, player1.atlas.findRegion("walk1"), player1.atlas.findRegion("walk2"));
		player1.go_right = new Animation(1/2f, player1.atlas.findRegion("walkR1"), player1.atlas.findRegion("walkR2"), player1.atlas.findRegion("walkR3"));
		player1.go_left = new Animation(1/2f, player1.atlas.findRegion("walkL1"), player1.atlas.findRegion("walkL2"), player1.atlas.findRegion("walkL3"));
		player1.stand = new Animation(1/2f, player1.atlas.findRegion("stand"), player1.atlas.findRegion("standback"), player1.atlas.findRegion("standR"), player1.atlas.findRegion("standL"));
		
		player2.atlas = new TextureAtlas(Gdx.files.internal("player/player1.atlas"));
		player2.go_up = new Animation(1/2f, player1.atlas.findRegion("walkback1"), player1.atlas.findRegion("walkback2"));
		player2.go_down = new Animation(1/2f, player1.atlas.findRegion("walk1"), player1.atlas.findRegion("walk2"));
		player2.go_right = new Animation(1/2f, player1.atlas.findRegion("walkR1"), player1.atlas.findRegion("walkR2"), player1.atlas.findRegion("walkR3"));
		player2.go_left = new Animation(1/2f, player1.atlas.findRegion("walkL1"), player1.atlas.findRegion("walkL2"), player1.atlas.findRegion("walkL3"));
		player2.stand = new Animation(1/2f, player1.atlas.findRegion("stand"), player1.atlas.findRegion("standback"), player1.atlas.findRegion("standR"), player1.atlas.findRegion("standL"));
	}

	@Override
	public void render () {
		
		//Clear//
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//-----------------------------------------//
		
		if (stage == 0) {
			menucurrentFrame = (TextureRegion) menu_anime.getKeyFrame(menutime, true);
			startcurrentFrame = (TextureRegion) start_anime.getKeyFrame(0, true);
			exitcurrentFrame = (TextureRegion) exit_anime.getKeyFrame(0, true);
			
			menutime += 10*Gdx.graphics.getDeltaTime();
			//Control//
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
		        if(Gdx.input.getX() >= (800/2)-100 && Gdx.input.getX() <= (800/2)+100) {
		        	//Start
		        	if (Gdx.input.getY() >= (600/2)+65 && Gdx.input.getY() <= (600/2)+65+65) {
		        		startcurrentFrame = (TextureRegion) start_anime.getKeyFrame(0.5f, true);
		        		delay += 0.5;
		        		if (delay > 2) {
		        			stage = 1;
		        		}
		        	}
		        	//Exit
		        	if (Gdx.input.getY() >= (600/2)+65+100 && Gdx.input.getY() <= (600/2)+65+100+65) {
		        		exitcurrentFrame = (TextureRegion) exit_anime.getKeyFrame(0.5f, true);
		        		delay += 0.5;
		        		if (delay > 2) {
		        			Gdx.app.exit();
		        		}
		        	}
		        	
		        }
		    }
			//-------------------------------------------------------------------------------//
			
			//Graphics Rendering//
			batch.begin();
			batch.draw(menucurrentFrame, 0, 0);
			batch.draw(startcurrentFrame, (800/2)-100, (600/2)-200, 200, 200);
			batch.draw(exitcurrentFrame, (800/2)-100, (600/2)-300, 200, 200);
			batch.end();
			//---------------------------------------------------------------------------------//
		}
		if (stage == 1) {
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
			batch.draw(background, 0, 0);
			batch.draw(player1.currentFrame, player1.pos_x, player1.pos_y, player1.size_x, player1.size_y);
			batch.draw(player2.currentFrame, player2.pos_x, player2.pos_y, player2.size_x, player2.size_y);
			batch.end();
			//---------------------------------------------------------------------------------//
		}
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		player1.atlas.dispose();
		player2.atlas.dispose();
		menu.dispose();
		start.dispose();
		exit.dispose();
	}
}
