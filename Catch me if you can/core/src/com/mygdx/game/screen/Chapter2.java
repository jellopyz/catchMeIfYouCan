package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.model.Character;
import com.mygdx.game.tools.Clipping;

public class Chapter2 implements Screen{
	final Main main;
	
	public SpriteBatch batch;
	public Texture grid;
	public Character player1;
	public Character player2;
	public TiledMap tiledMap;
	public TiledMapRenderer tiledMapRenderer;
	public OrthographicCamera camera;
	public int boxescount = 0;
	public Array<Rectangle> boxes = new Array<Rectangle>();
	public Sound wallcrash, music;
	
	public Chapter2(Main main) {
		this.main = main;
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
		
		//Sprite, Batch, Texture, Atlas
		batch = main.batch;
		grid = new Texture("test/0001.jpg");
		
		//TiledMap
		tiledMap = new TmxMapLoader().load("tilemap/desert/desert.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		//TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		
		//Sound
		wallcrash = Gdx.audio.newSound(Gdx.files.internal("sound/effect/wallcrash.ogg"));
		music = Gdx.audio.newSound(Gdx.files.internal("sound/music/mapforest.mp3"));
		music.loop();

		//Camera
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
		//Objects
        
		player1 = new Character(100, 40);
		player2 = new Character(1280-100, 500);
		
		/*-------------Texture,Animation of Objects-----------*/
		for (MapObject object : tiledMap.getLayers().get("ObjectCrash").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			boxes.add(rect);
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("Crash").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			boxes.add(rect);;
			++boxescount;
		}
		
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
	
	public void handle(float delta) {
		
		//Check Collision
		for (int i=0 ; i < boxes.size ; ++i) {
			if (player1.rect.overlaps(boxes.get(i))) {
				player1.checkoverlaps = true;
				break;
			}
		}
		
		for (int i=0 ; i < boxes.size ; ++i) {
			if (player2.rect.overlaps(boxes.get(i))) {
				player2.checkoverlaps = true;
				break;
			}
		}
		
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
		
		//Player1 Movement//
		if (true) {
			if(Gdx.input.isKeyPressed(Input.Keys.UP) && player1.checkoverlaps == false){
	            player1.currentFrame = (TextureRegion) player1.go_up.getKeyFrame(player1.elapsedTime, true);
	            player1.pos_y += 5;
	            player1.elapsedTime += 2*delta;
	            player1.stop = 1;
	            player1.rect.y += 5;
	            player1.prevkey = "UP";
	        }
			else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player1.checkoverlaps == false) {
				player1.currentFrame = (TextureRegion) player1.go_down.getKeyFrame(player1.elapsedTime, true);
				player1.pos_y -= 5;
				player1.elapsedTime += 2*delta;
				player1.stop = 0;
				player1.rect.y -= 5;
				player1.prevkey = "DOWN";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player1.checkoverlaps == false) {
				player1.currentFrame = (TextureRegion) player1.go_right.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x += 5;
				player1.elapsedTime += 2*delta;
				player1.stop = 2;
				player1.rect.x += 5;
				player1.prevkey = "RIGHT";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player1.checkoverlaps == false) {
				player1.currentFrame = (TextureRegion) player1.go_left.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x -= 5;
				player1.elapsedTime += 2*delta;
				player1.stop = 3;
				player1.rect.x -= 5;
				player1.prevkey = "LEFT";
			}
			else if (player1.checkoverlaps == true && player1.prevkey == "UP") {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0.5f, true);
				player1.pos_y -= 0.5;
				player1.rect.y -= 0.5;
				player1.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
			else if (player1.checkoverlaps == true && player1.prevkey == "DOWN") {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0f, true);
				player1.pos_y += 0.5;
				player1.rect.y += 0.5;
				player1.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
			else if (player1.checkoverlaps == true && player1.prevkey == "RIGHT") {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1, true);
				player1.pos_x -= 0.5;
				player1.rect.x -= 0.5;
				player1.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
			else if (player1.checkoverlaps == true && player1.prevkey == "LEFT") {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1.5f, true);
				player1.pos_x += 0.5;
				player1.rect.x += 0.5;
				player1.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
		}
		//-----------------------------------------------------------------------------------------------------//
		
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
				
				//Player2 Movement//
				if (true) {
					if(Gdx.input.isKeyPressed(Input.Keys.W) && player2.checkoverlaps == false) {
						player2.currentFrame = (TextureRegion) player2.go_up.getKeyFrame(player2.elapsedTime, true);
					    player2.pos_y += 5;
					    player2.rect.y += 5;
					    player2.elapsedTime += 2*delta;
					    player2.stop = 1;
					    player2.prevkey = "UP";
					}
					else if(Gdx.input.isKeyPressed(Input.Keys.S) && player2.checkoverlaps == false) {
						player2.currentFrame = (TextureRegion) player2.go_down.getKeyFrame(player2.elapsedTime, true);
						player2.pos_y -= 5;
						player2.rect.y -= 5;
						player2.elapsedTime += 2*delta;
						player2.stop = 0;
						player2.prevkey = "DOWN";
					}
					else if(Gdx.input.isKeyPressed(Input.Keys.D) && player2.checkoverlaps == false) {
						player2.currentFrame = (TextureRegion) player2.go_right.getKeyFrame(player2.elapsedTime, true);
						player2.pos_x += 5;
						player2.rect.x += 5;
						player2.elapsedTime += 2*delta;
						player2.stop = 2;
						player2.prevkey = "RIGHT";
					}
					else if(Gdx.input.isKeyPressed(Input.Keys.A) && player2.checkoverlaps == false) {
						player2.currentFrame = (TextureRegion) player2.go_left.getKeyFrame(player2.elapsedTime, true);
						player2.pos_x -= 5;
						player2.rect.x -= 5;
						player2.elapsedTime += 2*delta;
						player2.stop = 3;
						player2.prevkey = "LEFT";
					}
					else if (player2.checkoverlaps == true && player2.prevkey == "UP") {
						player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0.5f, true);
						player2.pos_y -= 0.5;
						player2.rect.y -= 0.5;
						player2.checkoverlaps = false;
						wallcrash.play(0.1f);
					}
					else if (player2.checkoverlaps == true && player2.prevkey == "DOWN") {
						player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0f, true);
						player2.pos_y += 0.5;
						player2.rect.y += 0.5;
						player2.checkoverlaps = false;
						wallcrash.play(0.1f);
					}
					else if (player2.checkoverlaps == true && player2.prevkey == "RIGHT") {
						player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1, true);
						player2.pos_x -= 0.5;
						player2.rect.x -= 0.5;
						player2.checkoverlaps = false;
						wallcrash.play(0.1f);
					}
					else if (player2.checkoverlaps == true && player2.prevkey == "LEFT") {
						player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1.5f, true);
						player2.pos_x += 0.5;
						player2.rect.x += 0.5;
						player2.checkoverlaps = false;
						wallcrash.play(0.1f);
					}
				}
				//-----------------------------------------------------------------------------------------------------//
	}
	@Override
	public void render(float delta) {
		
		//Clear
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//----------------------------------------//
		
		//Change map
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			main.setScreen(new Chapter1(main));
			music.stop();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
			main.setScreen(new Chapter3(main));
			music.stop();
		}
		//---------------------------------------------------//
		
		//Check Clipping//
		player1.pos_x = Clipping.check_x(player1.pos_x, player1.size_x);
		player1.pos_y = Clipping.check_y(player1.pos_y, player1.size_y);
		
		player2.pos_x = Clipping.check_x(player2.pos_x, player2.size_x);
		player2.pos_y = Clipping.check_y(player2.pos_y, player2.size_y);
		//-----------------------------------//
		
		//Handle//
		handle(delta);
		//----------------//
		
		//Map//
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        //--------------------------------//
        
		//Graphics Rendering//
		batch.begin();
		batch.draw(player1.currentFrame, player1.rect.x, player1.rect.y, player1.size_x, player1.size_y);
		batch.draw(player2.currentFrame, player2.rect.x, player2.rect.y, player2.size_x, player2.size_y);
		batch.draw(grid, player1.rect.x, player1.rect.y, player1.rect.width, player1.rect.height);
		batch.draw(grid, player2.rect.x, player2.rect.y, player2.rect.width, player2.rect.height);
		batch.end();
		//---------------------------------------------------------------------------------//
		
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		grid.dispose();
		player1.atlas.dispose();
		player2.atlas.dispose();
		tiledMap.dispose();
		wallcrash.dispose();
		music.dispose();
		
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
