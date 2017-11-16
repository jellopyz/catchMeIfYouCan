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
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Hud;
import com.mygdx.game.Main;
import com.mygdx.game.item.Banana;
import com.mygdx.game.item.Shoes;
import com.mygdx.game.model.Character;
import com.mygdx.game.tools.Clipping;

public class Chapter1 implements Screen{
	final Main main;
	
	public SpriteBatch batch;
	public Texture grid, img_shoe, img_banana, img_bananapeel, hunterwin, runnerwin;
	public Character player1;
	public Character player2;
	public TiledMap tiledMap;
	public TiledMapRenderer tiledMapRenderer;
	public OrthographicCamera camera;
	public int boxescount = 0;
	public Array<Rectangle> boxes = new Array<Rectangle>();
	public Sound wallcrash, music;
	public Shoes shoes;
	public Banana banana;
	public Viewport gamePort;
	public Hud hud;
	public float wintime = 0;
	public float w, h;
	
	public Chapter1(Main main) {
		this.main = main;
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        this.w = w;
        this.h = h;
		
		//Sprite, Batch, Texture, Atlas
		batch = main.batch;
		grid = new Texture("test/0001.jpg");
		//item
		img_shoe = new Texture(Gdx.files.internal("item/Shoe1.png"));
		img_banana = new Texture(Gdx.files.internal("item/Banana.png"));
		img_bananapeel = new Texture(Gdx.files.internal("item/Banana-Drop.png"));
		//victory
		hunterwin = new Texture(Gdx.files.internal("victory/huntwinsnow.png"));
		runnerwin = new Texture(Gdx.files.internal("victory/runwinsnow.png"));
		
		//TiledMap
		tiledMap = new TmxMapLoader().load("tilemap/desert/desert.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		//TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		
		//Sound
		wallcrash = Gdx.audio.newSound(Gdx.files.internal("sound/effect/wallcrash.ogg"));
		music = Gdx.audio.newSound(Gdx.files.internal("sound/music/mapsnow.mp3"));
		music.loop();

		//Camera, Viewport
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        gamePort = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, camera);
		
        //Objects
		player1 = new Character(100, 40);
		player2 = new Character(1280-100, 500);
		shoes = new Shoes(300, 300);
		banana = new Banana(500, 500);
		
		//HUD
        hud = new Hud(batch, player1.score, player2.score);
		
		
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
	
	public void check_wall(float delta) {
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
	}
	
	public void check_shoes(float delta) {
		//shoes overlaps
		if (player1.rect.overlaps(shoes.rect)) {
			shoes.rect.x += 10000;
			player1.checkshoe = true;
			player1.shoedelay = TimeUtils.nanoTime();
			
		}
		if (player2.rect.overlaps(shoes.rect)) {
			shoes.rect.x += 10000;
			player2.checkshoe = true;
			player2.shoedelay = TimeUtils.nanoTime();
		}
		
		//Check Effect
		if (player1.checkshoe == true) {
			if (TimeUtils.nanoTime()- player1.shoedelay < 3000000000f) {
				player1.speedup = 3;
			}
			else {
				player1.speedup = 0;
				player1.shoedelay = 0;
				player1.checkshoe = false;
			}
		}
		
		if (player2.checkshoe == true) {
			if (TimeUtils.nanoTime()- player2.shoedelay < 3000000000f) {
				player2.speedup = 3;
			}
			else {
				player2.speedup = 0;
				player2.shoedelay = 0;
				player2.checkshoe = false;
			}
		}
	}
	
	public void check_banana(float delta) {
		//Banana
		if (player1.stuck == true) {
			if (TimeUtils.nanoTime()- player1.bananadelay < 3000000000f) {
				player1.stuck = true;
			}
			else {
				player1.bananadelay = 0;
				player1.stuck = false;
			}
		}
		
		if (player2.stuck == true) {
			if (TimeUtils.nanoTime()- player2.bananadelay < 3000000000f) {
				player2.stuck = true;
			}
			else {
				player2.bananadelay = 0;
				player2.stuck = false;
			}
		}
		//banana overlaps
		if (player1.rect.overlaps(banana.rect) && banana.peel == false) {
			banana.rect.x += 100000;
			player1.holding = "Banana";
			banana.peel = true;
			
		}
		if (player2.rect.overlaps(banana.rect) && banana.peel == false) {
			banana.rect.x += 100000;
			player2.holding = "Banana";
			banana.peel = true;
		}
		
		if (player1.rect.overlaps(banana.rect) && banana.peel == true) {
			banana.rect.x += 100000;
			player1.holding = "";
			player1.stuck = true;
			player1.bananadelay = TimeUtils.nanoTime();
		}
		if (player2.rect.overlaps(banana.rect) && banana.peel == true) {
			banana.rect.x += 100000;
			player2.holding = "";
			player2.stuck = true;
			player2.bananadelay = TimeUtils.nanoTime();
		}
		
		if (player1.holding == "Banana") {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "UP"){
				banana.rect.x = player1.topbox.x;
				banana.rect.y = player1.topbox.y;
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "DOWN"){
				banana.rect.x = player1.downbox.x;
				banana.rect.y = player1.downbox.y;
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "RIGHT"){
				banana.rect.x = player1.rightbox.x;
				banana.rect.y = player1.rightbox.y;
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "LEFT"){
				banana.rect.x = player1.leftbox.x;
				banana.rect.y = player1.leftbox.y;
			}
		}
		
		if (player2.holding == "Banana") {
			if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "UP"){
				banana.rect.x = player2.topbox.x;
				banana.rect.y = player2.topbox.y;
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "DOWN"){
				banana.rect.x = player2.downbox.x;
				banana.rect.y = player2.downbox.y;
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "RIGHT"){
				banana.rect.x = player2.rightbox.x;
				banana.rect.y = player2.rightbox.y;
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "LEFT"){
				banana.rect.x = player2.leftbox.x;
				banana.rect.y = player2.leftbox.y;
			}
		}
	}
	
	public void handle(float delta) {
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
			if(Gdx.input.isKeyPressed(Input.Keys.W) && player1.checkoverlaps == false && player1.stuck == false){
	            player1.currentFrame = (TextureRegion) player1.go_up.getKeyFrame(player1.elapsedTime, true);
	            player1.pos_y += 5+player1.speedup;
	            player1.elapsedTime += 2*delta;
	            player1.stop = 1;
	            player1.rect.y += 5+player1.speedup;
	            player1.topbox.y += 5+player1.speedup;
				player1.downbox.y += 5+player1.speedup;
				player1.rightbox.y += 5+player1.speedup;
				player1.leftbox.y += 5+player1.speedup;
	            player1.prevkey = "UP";
	        }
			else if(Gdx.input.isKeyPressed(Input.Keys.S) && player1.checkoverlaps == false && player1.stuck == false) {
				player1.currentFrame = (TextureRegion) player1.go_down.getKeyFrame(player1.elapsedTime, true);
				player1.pos_y -= 5-player1.speedup;
				player1.elapsedTime += 2*delta;
				player1.stop = 0;
				player1.rect.y -= 5+player1.speedup;
				player1.topbox.y -= 5+player1.speedup;
				player1.downbox.y -= 5+player1.speedup;
				player1.rightbox.y -= 5+player1.speedup;
				player1.leftbox.y -= 5+player1.speedup;
				player1.prevkey = "DOWN";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.D) && player1.checkoverlaps == false && player1.stuck == false) {
				player1.currentFrame = (TextureRegion) player1.go_right.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x += 5+player1.speedup;
				player1.elapsedTime += 2*delta;
				player1.stop = 2;
				player1.rect.x += 5+player1.speedup;
				player1.topbox.x += 5+player1.speedup;
				player1.downbox.x += 5+player1.speedup;
				player1.rightbox.x += 5+player1.speedup;
				player1.leftbox.x += 5+player1.speedup;
				player1.prevkey = "RIGHT";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.A) && player1.checkoverlaps == false && player1.stuck == false) {
				player1.currentFrame = (TextureRegion) player1.go_left.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x -= 5-player1.speedup;
				player1.elapsedTime += 2*delta;
				player1.stop = 3;
				player1.rect.x -= 5+player1.speedup;
				player1.topbox.x -= 5+player1.speedup;
				player1.downbox.x -= 5+player1.speedup;
				player1.rightbox.x -= 5+player1.speedup;
				player1.leftbox.x -= 5+player1.speedup;
				player1.prevkey = "LEFT";
			}
			else if (player1.checkoverlaps == true && player1.prevkey == "UP") {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0.5f, true);
				player1.pos_y -= 0.5;
				player1.rect.y -= 0.5;
				player1.topbox.y -= 0.5;
				player1.downbox.y -= 0.5;
				player1.rightbox.y -= 0.5;
				player1.leftbox.y -= 0.5;
				player1.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
			else if (player1.checkoverlaps == true && player1.prevkey == "DOWN") {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0f, true);
				player1.pos_y += 0.5;
				player1.rect.y += 0.5;
				player1.topbox.y += 0.5;
				player1.downbox.y += 0.5;
				player1.rightbox.y += 0.5;
				player1.leftbox.y += 0.5;
				player1.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
			else if (player1.checkoverlaps == true && player1.prevkey == "RIGHT") {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1, true);
				player1.pos_x -= 0.5;
				player1.rect.x -= 0.5;
				player1.topbox.x -= 0.5;
				player1.downbox.x -= 0.5;
				player1.rightbox.x -= 0.5;
				player1.leftbox.x -= 0.5;
				player1.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
			else if (player1.checkoverlaps == true && player1.prevkey == "LEFT") {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1.5f, true);
				player1.pos_x += 0.5;
				player1.rect.x += 0.5;
				player1.topbox.x += 0.5;
				player1.downbox.x += 0.5;
				player1.rightbox.x += 0.5;
				player1.leftbox.x += 0.5;
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
			if(Gdx.input.isKeyPressed(Input.Keys.UP) && player2.checkoverlaps == false && player2.stuck == false) {
				player2.currentFrame = (TextureRegion) player2.go_up.getKeyFrame(player2.elapsedTime, true);
			    player2.pos_y += 5;
			    player2.rect.y += 5;
			    player2.topbox.y += 5;
				player2.downbox.y += 5;
				player2.rightbox.y += 5;
				player2.leftbox.y += 5;
			    player2.elapsedTime += 2*delta;
			    player2.stop = 1;
			    player2.prevkey = "UP";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player2.checkoverlaps == false && player2.stuck == false) {
				player2.currentFrame = (TextureRegion) player2.go_down.getKeyFrame(player2.elapsedTime, true);
				player2.pos_y -= 5;
				player2.rect.y -= 5;
				player2.topbox.y -= 5;
				player2.downbox.y -= 5;
				player2.rightbox.y -= 5;
				player2.leftbox.y -= 5;	
				player2.elapsedTime += 2*delta;
				player2.stop = 0;
				player2.prevkey = "DOWN";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player2.checkoverlaps == false && player2.stuck == false) {
				player2.currentFrame = (TextureRegion) player2.go_right.getKeyFrame(player2.elapsedTime, true);
				player2.pos_x += 5;
				player2.rect.x += 5;
				player2.topbox.x += 5;
				player2.downbox.x += 5;
				player2.rightbox.x += 5;
				player2.leftbox.x += 5;
				player2.elapsedTime += 2*delta;
				player2.stop = 2;
				player2.prevkey = "RIGHT";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player2.checkoverlaps == false && player2.stuck == false) {
				player2.currentFrame = (TextureRegion) player2.go_left.getKeyFrame(player2.elapsedTime, true);
				player2.pos_x -= 5;
				player2.rect.x -= 5;
				player2.topbox.x -= 5;
				player2.downbox.x -= 5;
				player2.rightbox.x -= 5;
				player2.leftbox.x -= 5;
				player2.elapsedTime += 2*delta;
				player2.stop = 3;
				player2.prevkey = "LEFT";
			}
			else if (player2.checkoverlaps == true && player2.prevkey == "UP") {
				player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0.5f, true);
				player2.pos_y -= 0.5;
				player2.rect.y -= 0.5;
				player2.topbox.y -= 0.5;
				player2.downbox.y -= 0.5;
				player2.rightbox.y -= 0.5;
				player2.leftbox.y -= 0.5;
				player2.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
			else if (player2.checkoverlaps == true && player2.prevkey == "DOWN") {
				player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0f, true);
				player2.pos_y += 0.5;
				player2.rect.y += 0.5;
				player2.topbox.y += 0.5;
				player2.downbox.y += 0.5;
				player2.rightbox.y += 0.5;
				player2.leftbox.y += 0.5;
				player2.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
			else if (player2.checkoverlaps == true && player2.prevkey == "RIGHT") {
				player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1, true);
				player2.pos_x -= 0.5;
				player2.rect.x -= 0.5;
				player2.topbox.x -= 0.5;
				player2.downbox.x -= 0.5;
				player2.rightbox.x -= 0.5;
				player2.leftbox.x -= 0.5;
				player2.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
			else if (player2.checkoverlaps == true && player2.prevkey == "LEFT") {
				player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1.5f, true);
				player2.pos_x += 0.5;
				player2.rect.x += 0.5;
				player2.topbox.x += 0.5;
				player2.downbox.x += 0.5;
				player2.rightbox.x += 0.5;
				player2.leftbox.x += 0.5;
				player2.checkoverlaps = false;
				wallcrash.play(0.1f);
			}
		}
		//-----------------------------------------------------------------------------------------------------//
	}
		
	public void check_win(float delta) {
		wintime += delta;
		//Check Win
		if (player1.rect.overlaps(player2.rect) && player1.win == false) {
			player1.score ++;
			hud.score1 = player1.score;
			hud.score1Label.setText(String.format("%02d", hud.score1));
			player1.win = true;
			wintime = 0;
		}
		if (hud.sec == 0 && hud.min == 0 && player2.win == false) {
			player2.score ++;
			hud.score2 = player2.score;
			hud.score2Label.setText(String.format("%02d", hud.score2));
			player2.win = true;
			wintime = 0;
		}
		if (wintime >= 5 && (player1.win == true || player2.win == true)) {
			if (player1.win == true) {
				main.setScreen(new Chapter2(main));
			}
			if (player2.win == true) {
				main.setScreen(new Chapter2(main));
			}
			wintime = 0;
		}
	}
	
	public void countdown(float delta) {
		hud.timeCount += delta;
		if (hud.timeCount >= 1) {
			if (hud.min == 0 && hud.sec == 0) {
				hud.stop = true;
			}
			if (player1.win == true || player2.win == true) {
				hud.stop = true;
			}
			if (hud.stop == false) {
				hud.sec --;
				if (hud.sec < 0) {
					hud.min = 0;
					hud.sec = 59;
				}
				hud.countdownLabel.setText(String.format("%02d : %02d", hud.min, hud.sec));
				hud.timeCount = 0;
			}
		}
	}
	
	@Override
	public void render(float delta) {
		
		//Clear
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//----------------------------------------//
		
		//Checking
		if (player1.win == false && player2.win == false) {
			handle(delta);
			check_banana(delta);
		}
		check_wall(delta);
		check_win(delta);
		check_shoes(delta);
		countdown(delta);
				
		//Change map
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			main.setScreen(new Chapter2(main));
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

		
		//Map//
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
       
        
        //HUD
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        
		//Graphics Rendering//
		batch.begin();
		
		//item
		batch.draw(img_shoe, shoes.rect.x, shoes.rect.y);
		if (banana.peel == false) {
			batch.draw(img_banana, banana.rect.x, banana.rect.y);
		}
		else {
			batch.draw(img_bananapeel, banana.rect.x, banana.rect.y);
		}
		
		//victory
		if (player1.win == true) {
			batch.draw(hunterwin, (w/2)-600, (h/2)-100, 1200, 400);
		}
		
		if (player2.win == true) {
			batch.draw(runnerwin, (w/2)-600, (h/2)-100, 1200, 400);
		}
		
		//Player
		batch.draw(player1.currentFrame, player1.rect.x, player1.rect.y, player1.size_x, player1.size_y);
		batch.draw(player2.currentFrame, player2.rect.x, player2.rect.y, player2.size_x, player2.size_y);
//		batch.draw(grid, player1.rect.x, player1.rect.y, player1.rect.width, player1.rect.height);
//		batch.draw(grid, player1.topbox.x, player1.topbox.y, player1.topbox.width, player1.topbox.height);
//		batch.draw(grid, player1.downbox.x, player1.downbox.y, player1.downbox.width, player1.downbox.height);
//		batch.draw(grid, player1.rightbox.x, player1.rightbox.y, player1.rightbox.width, player1.rightbox.height);
//		batch.draw(grid, player1.leftbox.x, player1.leftbox.y, player1.leftbox.width, player1.leftbox.height);
//		batch.draw(grid, player2.rect.x, player2.rect.y, player2.rect.width, player2.rect.height);
//		batch.draw(grid, player2.topbox.x, player2.topbox.y, player2.topbox.width, player2.topbox.height);
//		batch.draw(grid, player2.downbox.x, player2.downbox.y, player2.downbox.width, player2.downbox.height);
//		batch.draw(grid, player2.rightbox.x, player2.rightbox.y, player2.rightbox.width, player2.rightbox.height);
//		batch.draw(grid, player2.leftbox.x, player2.leftbox.y, player2.leftbox.width, player2.leftbox.height);
		
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
		img_shoe.dispose();
		img_banana.dispose();
		img_bananapeel.dispose();
		hunterwin.dispose();
		runnerwin.dispose();
		hud.dispose();
		
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
