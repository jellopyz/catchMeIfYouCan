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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Hud;
import com.mygdx.game.Main;
import com.mygdx.game.item.Banana;
import com.mygdx.game.item.Bone;
import com.mygdx.game.item.Can;
import com.mygdx.game.item.Pickaxe;
import com.mygdx.game.item.Shoes;
import com.mygdx.game.item.Unji;
import com.mygdx.game.model.Character;
import com.mygdx.game.tools.Clipping;

public class Chapter2 implements Screen{
	final Main main;
	
	//Batch
	public SpriteBatch batch;
	//Image
	public Texture grid, img_shoe, img_banana, img_bananapeel, hunterwin, runnerwin;
	public Texture img_unjisack, img_unji, img_pickaxe, img_stat1, img_stat2;
	//Character
	public Character player1;
	public Character player2;
	//TiledMap
	public TiledMap tiledMap;
	public TiledMapRenderer tiledMapRenderer;
	public OrthographicCamera camera;
	//Objects Array
	public int boxescount = 0, behindcount = 0, breakboxcount = 0, breakboxwalkcount = 0;
	public Array<Rectangle> boxes = new Array<Rectangle>();
	public Array<Rectangle> behind = new Array<Rectangle>();
	public Array<Rectangle> breakbox = new Array<Rectangle>();
	public Array<Rectangle> breakboxwalk = new Array<Rectangle>();
	//Exit
	public int escapeint = MathUtils.random(2);
	public Rectangle escape;
	public Array<Rectangle> exit = new Array<Rectangle>();
	//Sound
	public Sound wallcrash, music, boost, throwcan, throwbone, hitcan, takeitem, hitbone, broken, slow, stun;
	public Sound winning, escapesound;
	//item
	public Shoes shoes;
	public Banana banana;
	public Unji unji;
	public Pickaxe pickaxe;
	public Can can;
	public Bone bone;
	//ViewPort, HUD
	public Viewport gamePort;
	public Hud hud;
	public float w, h;
	//WinTime
	public float wintime = 0;
	public int escapecount = 0;
	
	//RectBehind
	public Rectangle GO1;
	public Rectangle GO2;
	public Rectangle GO3;
	public Rectangle GO4;
	public Rectangle GO5;
	public Rectangle GO6;
	public Rectangle GO7;
	public Rectangle GO8;
	public Rectangle GO9;
	public Rectangle GO10;
	public Rectangle GO11;
	public Rectangle GO12;
	public Rectangle GO13;
	public Rectangle GO14;
	public Rectangle GO15;
	public Rectangle GO16;
	public Rectangle GO17;
	public Rectangle GO18;
	public Rectangle GO19;
	public Rectangle GO20;
	public Rectangle GO21;
	public Rectangle GO22;
	public Rectangle GO23;
	public Rectangle GO24;
	public Rectangle GO25;
	public Rectangle GO26;
	public Rectangle GO27;
	public Rectangle GO28;
	public Rectangle GO29;
	public Rectangle GO30;
	public Rectangle GO31;
	public Rectangle GO32;
	public Rectangle GO33;
	public Rectangle GO34;
	public Rectangle GO35;
	public Rectangle GO36;
	public Rectangle GO37;
	public Rectangle GO38;
	public Rectangle GO39;
	public Rectangle GO40;
	public Rectangle GO41;
	public Rectangle GO42;
	public Rectangle GO43;
	public Rectangle GO44;
	public Rectangle GO45;
	public Rectangle GO46;
	public Rectangle GO47;
	
	public Chapter2(Main main, int p1oldscore, int p2oldscore) {
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
		img_unjisack = new Texture(Gdx.files.internal("item/Unji-Sack.png"));
		img_unji = new Texture(Gdx.files.internal("item/Unji.png"));
		img_pickaxe = new Texture(Gdx.files.internal("item/Pickaxe.png"));
		img_stat1 = new Texture(Gdx.files.internal("hud/item.png"));
		img_stat2 = new Texture(Gdx.files.internal("hud/status.png"));
		//victory
		hunterwin = new Texture(Gdx.files.internal("victory/huntwinsnow.png"));
		runnerwin = new Texture(Gdx.files.internal("victory/runwinsnow.png"));
		
		//TiledMap
		tiledMap = new TmxMapLoader().load("tilemap/forest/Forest.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		//TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		
		//Sound
		wallcrash = Gdx.audio.newSound(Gdx.files.internal("sound/effect/wallcrash.ogg"));
		boost = Gdx.audio.newSound(Gdx.files.internal("sound/effect/boost.mp3"));
		throwcan = Gdx.audio.newSound(Gdx.files.internal("sound/effect/throwcan.mp3"));
		throwbone = Gdx.audio.newSound(Gdx.files.internal("sound/effect/throwbone.mp3"));
		hitcan = Gdx.audio.newSound(Gdx.files.internal("sound/effect/hitcan.mp3"));
		hitbone = Gdx.audio.newSound(Gdx.files.internal("sound/effect/hitbone.mp3"));
		takeitem = Gdx.audio.newSound(Gdx.files.internal("sound/effect/takeitem.mp3"));
		broken = Gdx.audio.newSound(Gdx.files.internal("sound/effect/breakgrass.mp3"));
		slow = Gdx.audio.newSound(Gdx.files.internal("sound/effect/slow.mp3"));
		stun = Gdx.audio.newSound(Gdx.files.internal("sound/effect/Stun.mp3"));
		winning = Gdx.audio.newSound(Gdx.files.internal("sound/effect/winning.mp3"));
		escapesound = Gdx.audio.newSound(Gdx.files.internal("sound/effect/escapesound.mp3"));
		music = Gdx.audio.newSound(Gdx.files.internal("sound/music/mapforest.mp3"));
		music.loop();

		//Camera, Viewport
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        gamePort = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, camera);
		
        //Objects
		player1 = new Character(250, 570);
		player2 = new Character(1280-520, 60);
		//Stack score
		player1.score += p1oldscore;
		player2.score += p2oldscore;
		//item
		shoes = new Shoes(450, 40);
		banana = new Banana(1000, 580);
		unji = new Unji(280, 370);
		pickaxe = new Pickaxe(1100, 400);
		can = new Can(670, 430);
		bone = new Bone(900, 70);
		
		//HUD
        hud = new Hud(batch, player1.score, player2.score);
		
		
		/*-------------Texture,Animation of Objects-----------*/
        
        //BreakBoxWalk
        for (MapObject object : tiledMap.getLayers().get("grassbrokenwalk1").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakboxwalk.add(rect);
			++breakboxwalkcount;
		}
        for (MapObject object : tiledMap.getLayers().get("grassbrokenwalk2").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakboxwalk.add(rect);
			++breakboxwalkcount;
		}
        for (MapObject object : tiledMap.getLayers().get("grassbrokenwalk3").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakboxwalk.add(rect);
			++breakboxwalkcount;
		}
        for (MapObject object : tiledMap.getLayers().get("grassbrokenwalk4").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakboxwalk.add(rect);
			++breakboxwalkcount;
		}
        for (MapObject object : tiledMap.getLayers().get("grassbrokenwalk5").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakboxwalk.add(rect);
			++breakboxwalkcount;
		}
        for (MapObject object : tiledMap.getLayers().get("grassbrokenwalk6").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakboxwalk.add(rect);
			++breakboxwalkcount;
		}
        for (MapObject object : tiledMap.getLayers().get("grassbrokenwalk7").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakboxwalk.add(rect);
			++breakboxwalkcount;
		}
        for (MapObject object : tiledMap.getLayers().get("grassbrokenwalk8").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakboxwalk.add(rect);
			++breakboxwalkcount;
		}
        
        
        //BreakBox
        for (MapObject object : tiledMap.getLayers().get("ograssbroken1").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("ograssbroken2").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("ograssbroken3").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("ograssbroken4").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("ograssbroken5").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("ograssbroken6").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("ograssbroken7").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("ograssbroken8").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        
        
        //exit
        for (MapObject object : tiledMap.getLayers().get("oexit1").getObjects().getByType(RectangleMapObject.class)) {
    	   Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	exit.add(rect);
        	break;
		}
        for (MapObject object : tiledMap.getLayers().get("oexit2").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	exit.add(rect);
        	break;
		}
        for (MapObject object : tiledMap.getLayers().get("oexit3").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	exit.add(rect);
        	break;
		}
        
        
        //every 1 box
        for (MapObject object : tiledMap.getLayers().get("GO1").getObjects().getByType(RectangleMapObject.class)) {
        	GO1 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO2").getObjects().getByType(RectangleMapObject.class)) {
        	GO2 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO3").getObjects().getByType(RectangleMapObject.class)) {
        	GO3 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO4").getObjects().getByType(RectangleMapObject.class)) {
        	GO4 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO5").getObjects().getByType(RectangleMapObject.class)) {
        	GO5 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO5").getObjects().getByType(RectangleMapObject.class)) {
        	GO5 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO6").getObjects().getByType(RectangleMapObject.class)) {
        	GO6 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO7").getObjects().getByType(RectangleMapObject.class)) {
        	GO7 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO8").getObjects().getByType(RectangleMapObject.class)) {
        	GO8 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO9").getObjects().getByType(RectangleMapObject.class)) {
        	GO9 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO10").getObjects().getByType(RectangleMapObject.class)) {
        	GO10 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO11").getObjects().getByType(RectangleMapObject.class)) {
        	GO11 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO12").getObjects().getByType(RectangleMapObject.class)) {
        	GO12 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO13").getObjects().getByType(RectangleMapObject.class)) {
        	GO13 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO14").getObjects().getByType(RectangleMapObject.class)) {
        	GO14 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO15").getObjects().getByType(RectangleMapObject.class)) {
        	GO15 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO16").getObjects().getByType(RectangleMapObject.class)) {
        	GO16 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO17").getObjects().getByType(RectangleMapObject.class)) {
        	GO17 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO18").getObjects().getByType(RectangleMapObject.class)) {
        	GO18 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO19").getObjects().getByType(RectangleMapObject.class)) {
        	GO19 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO20").getObjects().getByType(RectangleMapObject.class)) {
        	GO20 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO21").getObjects().getByType(RectangleMapObject.class)) {
        	GO21 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO22").getObjects().getByType(RectangleMapObject.class)) {
        	GO22 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO23").getObjects().getByType(RectangleMapObject.class)) {
        	GO23 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO24").getObjects().getByType(RectangleMapObject.class)) {
        	GO24 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO25").getObjects().getByType(RectangleMapObject.class)) {
        	GO25 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO26").getObjects().getByType(RectangleMapObject.class)) {
        	GO26 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO27").getObjects().getByType(RectangleMapObject.class)) {
        	GO27 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO28").getObjects().getByType(RectangleMapObject.class)) {
        	GO28 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO29").getObjects().getByType(RectangleMapObject.class)) {
        	GO29 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO30").getObjects().getByType(RectangleMapObject.class)) {
        	GO30 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO31").getObjects().getByType(RectangleMapObject.class)) {
        	GO31 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO32").getObjects().getByType(RectangleMapObject.class)) {
        	GO32 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO33").getObjects().getByType(RectangleMapObject.class)) {
        	GO33 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO34").getObjects().getByType(RectangleMapObject.class)) {
        	GO34 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO35").getObjects().getByType(RectangleMapObject.class)) {
        	GO35 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO36").getObjects().getByType(RectangleMapObject.class)) {
        	GO36 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO37").getObjects().getByType(RectangleMapObject.class)) {
        	GO37 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO38").getObjects().getByType(RectangleMapObject.class)) {
        	GO38 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO39").getObjects().getByType(RectangleMapObject.class)) {
        	GO39 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO40").getObjects().getByType(RectangleMapObject.class)) {
        	GO40 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO41").getObjects().getByType(RectangleMapObject.class)) {
        	GO41 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO42").getObjects().getByType(RectangleMapObject.class)) {
        	GO42 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO43").getObjects().getByType(RectangleMapObject.class)) {
        	GO43 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO44").getObjects().getByType(RectangleMapObject.class)) {
        	GO44 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO45").getObjects().getByType(RectangleMapObject.class)) {
        	GO45 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO46").getObjects().getByType(RectangleMapObject.class)) {
        	GO46 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("GO47").getObjects().getByType(RectangleMapObject.class)) {
        	GO47 = ((RectangleMapObject) object).getRectangle();
		}
        
        
        //box
		for (MapObject object : tiledMap.getLayers().get("allblock").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			boxes.add(rect);
			++boxescount;
		}
		
		for (MapObject object : tiledMap.getLayers().get("frameOb").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			boxes.add(rect);
			++boxescount;
		}
		
		
		//behind
        for (MapObject object : tiledMap.getLayers().get("treewalk").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			behind.add(rect);
			++behindcount;
		}
        for (MapObject object : tiledMap.getLayers().get("rockwalk").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			behind.add(rect);
			++behindcount;
		}
        for (MapObject object : tiledMap.getLayers().get("logwalk").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			behind.add(rect);
			++behindcount;
		}
        for (MapObject object : tiledMap.getLayers().get("axewalk").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			behind.add(rect);
			++behindcount;
		}
        
		
        //EscapeWay
      	escape = exit.get(escapeint);
		//Player1
		player1.atlas = new TextureAtlas(Gdx.files.internal("player/forest/hunter/tribe.atlas"));
		player1.go_up = new Animation(1/2f, player1.atlas.findRegion("tu1"), player1.atlas.findRegion("tu2"));
		player1.go_down = new Animation(1/2f, player1.atlas.findRegion("td1"), player1.atlas.findRegion("td2"));
		player1.go_right = new Animation(1/2f, player1.atlas.findRegion("tl1"), player1.atlas.findRegion("tl2"), player1.atlas.findRegion("tl3"), player1.atlas.findRegion("tl4"), player1.atlas.findRegion("tl5"));
		player1.go_left = new Animation(1/2f, player1.atlas.findRegion("tr1"), player1.atlas.findRegion("tr2"), player1.atlas.findRegion("tr3"), player1.atlas.findRegion("tr4"), player1.atlas.findRegion("tr5"));
		player1.stand = new Animation(1/2f, player1.atlas.findRegion("tfd"), player1.atlas.findRegion("tbu"), player1.atlas.findRegion("tfl"), player1.atlas.findRegion("tfr"));
		player1.confused = new Animation(1/2f, player1.atlas.findRegion("tc1"), player1.atlas.findRegion("tc2"), player1.atlas.findRegion("tc3"));
		
		//Player2
		player2.atlas = new TextureAtlas(Gdx.files.internal("player/forest/runner/bull.atlas"));
		player2.go_up = new Animation(1/2f, player2.atlas.findRegion("bu1"), player2.atlas.findRegion("bu2"));
		player2.go_down = new Animation(1/2f, player2.atlas.findRegion("bd1"), player2.atlas.findRegion("bd2"));
		player2.go_right = new Animation(1/2f, player2.atlas.findRegion("bl1"), player2.atlas.findRegion("bl2"), player2.atlas.findRegion("bl3"), player2.atlas.findRegion("bl4"), player2.atlas.findRegion("bl5"));
		player2.go_left = new Animation(1/2f, player2.atlas.findRegion("br1"), player2.atlas.findRegion("br2"), player2.atlas.findRegion("br3"), player2.atlas.findRegion("br4"), player2.atlas.findRegion("br5"));
		player2.stand = new Animation(1/2f, player2.atlas.findRegion("bfd"), player2.atlas.findRegion("bbu"), player2.atlas.findRegion("blr"), player2.atlas.findRegion("bfr"));
		player2.confused = new Animation(1/2f, player2.atlas.findRegion("bc1"), player2.atlas.findRegion("bc2"), player2.atlas.findRegion("bc3"));
		
		//can
		can.atlas = new TextureAtlas(Gdx.files.internal("item/can/Can Action/canact.atlas"));
		can.anime = new Animation(1/5f, can.atlas.findRegion("1"), can.atlas.findRegion("2"),can.atlas.findRegion("3")
				,can.atlas.findRegion("4"),can.atlas.findRegion("5"),can.atlas.findRegion("6"),can.atlas.findRegion("7")
				,can.atlas.findRegion("8"));
				
		//bone
		bone.atlas = new TextureAtlas(Gdx.files.internal("item/bone/Bone Action/boneact.atlas"));
		bone.anime = new Animation(1/5f, bone.atlas.findRegion("1"), bone.atlas.findRegion("2"),bone.atlas.findRegion("3")
				,bone.atlas.findRegion("4"),bone.atlas.findRegion("5"),bone.atlas.findRegion("6"),bone.atlas.findRegion("7"));
				
	}

	
	public void check_bone(float delta) {
		//Can overlaps and Holding
		if (player1.rect.overlaps(bone.rect) && bone.slow == false) {
			player1.holding = "Bone";
			bone.pickup = true;
			bone.slow = true;
			bone.rect.x += 100000;
			takeitem.play();
		}
		if (player2.rect.overlaps(bone.rect) && bone.slow == false) {
			player2.holding = "Bone";
			bone.pickup = true;
			bone.slow = true;
			bone.rect.x += 100000;
			takeitem.play();
		}
		// When touch bone
		if (player1.rect.overlaps(bone.rect) && bone.slow == true) {
			bone.rect.x += 100000;
			player1.boneslow= true;
			player1.bonedelay = TimeUtils.nanoTime();
			hitbone.play();
			slow.play();
		}
		if (player2.rect.overlaps(bone.rect) && bone.slow == true) {
			bone.rect.x += 100000;
			player2.boneslow = true;
			player2.bonedelay = TimeUtils.nanoTime();
			hitbone.play();
			slow.play();
		}
		
		//Slow
		if (player1.boneslow == true) {
			if (TimeUtils.nanoTime()- player1.bonedelay < 2000000000f) {
				player1.elapsedTime += 0.7*delta;
				player1.speedup = -2;
				player1.boneslow = true;
			}
			else {
				player1.bonedelay = 0;
				player1.boneslow = false;
				player1.speedup = 0;
			}
		}
		if (player2.boneslow == true) {
			if (TimeUtils.nanoTime()- player2.bonedelay < 2000000000f) {
				player2.elapsedTime += 0.7*delta;
				player2.boneslow = true;
				player2.speedup = -2;
			}
			else {
				player2.bonedelay = 0;
				player2.boneslow = false;
				player2.speedup = 0;
			}
		}
		
		//Player1 throw
		if (player1.holding == "Bone") {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "UP"){
				bone.rect.x = player1.topbox.x;
				bone.rect.y = player1.topbox.y;
				player1.holding = "";
				bone.throwup = true;
				throwbone.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "DOWN"){
				bone.rect.x = player1.downbox.x;
				bone.rect.y = player1.downbox.y;
				player1.holding = "";
				bone.throwdown = true;
				throwbone.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "RIGHT"){
				bone.rect.x = player1.rightbox.x;
				bone.rect.y = player1.rightbox.y;
				player1.holding = "";
				bone.throwright = true;
				throwbone.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "LEFT"){
				bone.rect.x = player1.leftbox.x;
				bone.rect.y = player1.leftbox.y;
				player1.holding = "";
				bone.throwleft = true;
				throwbone.play();
			}
		}
		
		//Player2 throw
		if (player2.holding == "Bone") {
			if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "UP"){
				bone.rect.x = player2.topbox.x;
				bone.rect.y = player2.topbox.y;
				player2.holding = "";
				bone.throwup = true;
				throwbone.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "DOWN"){
				bone.rect.x = player2.downbox.x;
				bone.rect.y = player2.downbox.y;
				player2.holding = "";
				bone.throwdown = true;
				throwbone.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "RIGHT"){
				bone.rect.x = player2.rightbox.x;
				bone.rect.y = player2.rightbox.y;
				player2.holding = "";
				bone.throwright = true;
				throwbone.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "LEFT"){
				bone.rect.x = player2.leftbox.x;
				bone.rect.y = player2.leftbox.y;
				player2.holding = "";
				bone.throwleft = true;
				throwbone.play();
			}
		}
		
}
	
	public void check_can(float delta) {
		//Can overlaps and Holding
		if (player1.rect.overlaps(can.rect) && can.stunt == false) {
			player1.holding = "Can";
			can.pickup = true;
			can.stunt = true;
			can.rect.x += 100000;
			takeitem.play();
		}
		if (player2.rect.overlaps(can.rect) && can.stunt == false) {
			player2.holding = "Can";
			can.pickup = true;
			can.stunt = true;
			can.rect.x += 100000;
			takeitem.play();
		}
		// When touch can
		if (player1.rect.overlaps(can.rect) && can.stunt == true) {
			can.rect.x += 100000;
			player1.canstuck = true;
			player1.candelay = TimeUtils.nanoTime();
			hitcan.play();
			stun.play();
		}
		if (player2.rect.overlaps(can.rect) && can.stunt == true) {
			can.rect.x += 100000;
			player2.canstuck = true;
			player2.candelay = TimeUtils.nanoTime();
			hitcan.play();
			stun.play();
		}
		
		//Stunt
		if (player1.canstuck == true) {
			if (TimeUtils.nanoTime()- player1.candelay < 1500000000f) {
				player1.currentFrame = (TextureRegion) player1.confused.getKeyFrame(player1.elapsedTime, true);
				player1.elapsedTime += 3*delta;
				player1.canstuck = true;
			}
			else {
				player1.candelay = 0;
				player1.canstuck = false;
			}
		}
		if (player2.canstuck == true) {
			if (TimeUtils.nanoTime()- player2.candelay < 1500000000f) {
				player2.currentFrame = (TextureRegion) player2.confused.getKeyFrame(player2.elapsedTime, true);
				player2.elapsedTime += 3*delta;
				player2.canstuck = true;
			}
			else {
				player2.candelay = 0;
				player2.canstuck = false;
			}
		}
		
		//Player1 throw
		if (player1.holding == "Can") {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "UP"){
				can.rect.x = player1.topbox.x;
				can.rect.y = player1.topbox.y;
				player1.holding = "";
				can.throwup = true;
				throwcan.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "DOWN"){
				can.rect.x = player1.downbox.x;
				can.rect.y = player1.downbox.y;
				player1.holding = "";
				can.throwdown = true;
				throwcan.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "RIGHT"){
				can.rect.x = player1.rightbox.x;
				can.rect.y = player1.rightbox.y;
				player1.holding = "";
				can.throwright = true;
				throwcan.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "LEFT"){
				can.rect.x = player1.leftbox.x;
				can.rect.y = player1.leftbox.y;
				player1.holding = "";
				can.throwleft = true;
				throwcan.play();
			}
		}
		
		//Player2 throw
		if (player2.holding == "Can") {
			if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "UP"){
				can.rect.x = player2.topbox.x;
				can.rect.y = player2.topbox.y;
				player2.holding = "";
				can.throwup = true;
				throwcan.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "DOWN"){
				can.rect.x = player2.downbox.x;
				can.rect.y = player2.downbox.y;
				player2.holding = "";
				can.throwdown = true;
				throwcan.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "RIGHT"){
				can.rect.x = player2.rightbox.x;
				can.rect.y = player2.rightbox.y;
				player2.holding = "";
				can.throwright = true;
				throwcan.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "LEFT"){
				can.rect.x = player2.leftbox.x;
				can.rect.y = player2.leftbox.y;
				player2.holding = "";
				can.throwleft = true;
				throwcan.play();
			}
		}
		
		
	}
	
	public void check_pickaxe(float delta) {
		//pickaxe overlaps
		if (player1.rect.overlaps(pickaxe.rect)) {
			pickaxe.rect.x += 100000;
			player1.holding = "Pickaxe";
			takeitem.play();
					
		}
		if (player2.rect.overlaps(pickaxe.rect)) {
			pickaxe.rect.x += 100000;
			player2.holding = "Pickaxe";
			takeitem.play();
		}
		
		//player1 destroy
		for (int i=0 ; i < breakbox.size ; ++i) {
			Rectangle rect = breakbox.get(i);
			if (player1.holding == "Pickaxe") {
				if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "UP"
						&& player1.topbox.overlaps(rect)){
					broken.play();
					breakbox.get(i).y += 100000;
					if (i == 0)
						tiledMap.getLayers().get("grassbroken1").setVisible(false);
					else if (i == 1)
						tiledMap.getLayers().get("grassbroken2").setVisible(false);
					else if (i == 2)
						tiledMap.getLayers().get("grassbroken3").setVisible(false);
					else if (i == 3)
						tiledMap.getLayers().get("grassbroken4").setVisible(false);
					else if (i == 4)
						tiledMap.getLayers().get("grassbroken5").setVisible(false);
					else if (i == 5)
						tiledMap.getLayers().get("grassbroken6").setVisible(false);
					else if (i == 6)
						tiledMap.getLayers().get("grassbroken7").setVisible(false);
					else if (i == 7)
						tiledMap.getLayers().get("grassbroken8").setVisible(false);
					
					player1.holding = "";
				}
				else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "DOWN"
						&& player1.downbox.overlaps(rect)){
					broken.play();
					breakbox.get(i).y += 100000;
					if (i == 0)
						tiledMap.getLayers().get("grassbroken1").setVisible(false);
					else if (i == 1)
						tiledMap.getLayers().get("grassbroken2").setVisible(false);
					else if (i == 2)
						tiledMap.getLayers().get("grassbroken3").setVisible(false);
					else if (i == 3)
						tiledMap.getLayers().get("grassbroken4").setVisible(false);
					else if (i == 4)
						tiledMap.getLayers().get("grassbroken5").setVisible(false);
					else if (i == 5)
						tiledMap.getLayers().get("grassbroken6").setVisible(false);
					else if (i == 6)
						tiledMap.getLayers().get("grassbroken7").setVisible(false);
					else if (i == 7)
						tiledMap.getLayers().get("grassbroken8").setVisible(false);
					
					player1.holding = "";
					
				}
				else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "RIGHT"
						&& player1.rightbox.overlaps(rect)){
					broken.play();
					breakbox.get(i).y += 100000;
					if (i == 0)
						tiledMap.getLayers().get("grassbroken1").setVisible(false);
					else if (i == 1)
						tiledMap.getLayers().get("grassbroken2").setVisible(false);
					else if (i == 2)
						tiledMap.getLayers().get("grassbroken3").setVisible(false);
					else if (i == 3)
						tiledMap.getLayers().get("grassbroken4").setVisible(false);
					else if (i == 4)
						tiledMap.getLayers().get("grassbroken5").setVisible(false);
					else if (i == 5)
						tiledMap.getLayers().get("grassbroken6").setVisible(false);
					else if (i == 6)
						tiledMap.getLayers().get("grassbroken7").setVisible(false);
					else if (i == 7)
						tiledMap.getLayers().get("grassbroken8").setVisible(false);
					
					player1.holding = "";
					
				}
				else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "LEFT"
						&& player1.leftbox.overlaps(rect)){
					broken.play();
					breakbox.get(i).y += 100000;
					if (i == 0)
						tiledMap.getLayers().get("grassbroken1").setVisible(false);
					else if (i == 1)
						tiledMap.getLayers().get("grassbroken2").setVisible(false);
					else if (i == 2)
						tiledMap.getLayers().get("grassbroken3").setVisible(false);
					else if (i == 3)
						tiledMap.getLayers().get("grassbroken4").setVisible(false);
					else if (i == 4)
						tiledMap.getLayers().get("grassbroken5").setVisible(false);
					else if (i == 5)
						tiledMap.getLayers().get("grassbroken6").setVisible(false);
					else if (i == 6)
						tiledMap.getLayers().get("grassbroken7").setVisible(false);
					else if (i == 7)
						tiledMap.getLayers().get("grassbroken8").setVisible(false);
					
					player1.holding = "";
					
				}
			}
		}
		
		//player2 destroy
		for (int i=0 ; i < breakbox.size ; ++i) {
			Rectangle rect = breakbox.get(i);
			if (player2.holding == "Pickaxe") {
				if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "UP"
						&& player2.topbox.overlaps(rect)){
					broken.play();
					breakbox.get(i).y += 100000;
					if (i == 0)
						tiledMap.getLayers().get("grassbroken1").setVisible(false);
					else if (i == 1)
						tiledMap.getLayers().get("grassbroken2").setVisible(false);
					else if (i == 2)
						tiledMap.getLayers().get("grassbroken3").setVisible(false);
					else if (i == 3)
						tiledMap.getLayers().get("grassbroken4").setVisible(false);
					else if (i == 4)
						tiledMap.getLayers().get("grassbroken5").setVisible(false);
					else if (i == 5)
						tiledMap.getLayers().get("grassbroken6").setVisible(false);
					else if (i == 6)
						tiledMap.getLayers().get("grassbroken7").setVisible(false);
					else if (i == 7)
						tiledMap.getLayers().get("grassbroken8").setVisible(false);
					
					player2.holding = "";
				}
				else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "DOWN"
						&& player2.downbox.overlaps(rect)){
					broken.play();
					breakbox.get(i).y += 100000;
					if (i == 0)
						tiledMap.getLayers().get("grassbroken1").setVisible(false);
					else if (i == 1)
						tiledMap.getLayers().get("grassbroken2").setVisible(false);
					else if (i == 2)
						tiledMap.getLayers().get("grassbroken3").setVisible(false);
					else if (i == 3)
						tiledMap.getLayers().get("grassbroken4").setVisible(false);
					else if (i == 4)
						tiledMap.getLayers().get("grassbroken5").setVisible(false);
					else if (i == 5)
						tiledMap.getLayers().get("grassbroken6").setVisible(false);
					else if (i == 6)
						tiledMap.getLayers().get("grassbroken7").setVisible(false);
					else if (i == 7)
						tiledMap.getLayers().get("grassbroken8").setVisible(false);
					
					player2.holding = "";
					
				}
				else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "RIGHT"
						&& player2.rightbox.overlaps(rect)){
					broken.play();
					breakbox.get(i).y += 100000;
					if (i == 0)
						tiledMap.getLayers().get("grassbroken1").setVisible(false);
					else if (i == 1)
						tiledMap.getLayers().get("grassbroken2").setVisible(false);
					else if (i == 2)
						tiledMap.getLayers().get("grassbroken3").setVisible(false);
					else if (i == 3)
						tiledMap.getLayers().get("grassbroken4").setVisible(false);
					else if (i == 4)
						tiledMap.getLayers().get("grassbroken5").setVisible(false);
					else if (i == 5)
						tiledMap.getLayers().get("grassbroken6").setVisible(false);
					else if (i == 6)
						tiledMap.getLayers().get("grassbroken7").setVisible(false);
					else if (i == 7)
						tiledMap.getLayers().get("grassbroken8").setVisible(false);
					
					player2.holding = "";
					
				}
				else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "LEFT"
						&& player2.leftbox.overlaps(rect)){
					broken.play();
					breakbox.get(i).y += 100000;
					if (i == 0)
						tiledMap.getLayers().get("grassbroken1").setVisible(false);
					else if (i == 1)
						tiledMap.getLayers().get("grassbroken2").setVisible(false);
					else if (i == 2)
						tiledMap.getLayers().get("grassbroken3").setVisible(false);
					else if (i == 3)
						tiledMap.getLayers().get("grassbroken4").setVisible(false);
					else if (i == 4)
						tiledMap.getLayers().get("grassbroken5").setVisible(false);
					else if (i == 5)
						tiledMap.getLayers().get("grassbroken6").setVisible(false);
					else if (i == 6)
						tiledMap.getLayers().get("grassbroken7").setVisible(false);
					else if (i == 7)
						tiledMap.getLayers().get("grassbroken8").setVisible(false);
					
					player2.holding = "";
					
				}
			}
		}
		
	}
	
	public void check_unji(float delta) {
		//Unji
		if (player1.slow == true) {
			if (TimeUtils.nanoTime()- player1.unjidelay < 2000000000f) {
				player1.elapsedTime += 0.7*delta;
				player1.slow = true;
				player1.speedup = -2;
			}
			else {
				player1.unjidelay = 0;
				player1.slow = false;
				player1.speedup = 0;
			}
		}
		
		if (player2.slow == true) {
			if (TimeUtils.nanoTime()- player2.unjidelay < 2000000000f) {
				player2.elapsedTime += 0.7*delta;
				player2.slow = true;
				player2.speedup = -2;
			}
			else {
				player2.unjidelay = 0;
				player2.slow = false;
				player2.speedup = 0;
			}
		}
		//unji sack overlaps
		if (player1.rect.overlaps(unji.rect) && unji.unsack == false) {
			unji.rect.x += 100000;
			player1.holding = "Unji";
			unji.unsack = true;
			takeitem.play();
			
		}
		if (player2.rect.overlaps(unji.rect) && unji.unsack == false) {
			unji.rect.x += 100000;
			player2.holding = "Unji";
			unji.unsack = true;
			takeitem.play();
		}
		
		//unji unsack overlaps
		if (player1.rect.overlaps(unji.rect) && unji.unsack == true) {
			unji.rect.x += 100000;
			player1.slow = true;
			player1.unjidelay = TimeUtils.nanoTime();
			slow.play();
		}
		if (player2.rect.overlaps(unji.rect) && unji.unsack == true) {
			unji.rect.x += 100000;
			player2.slow = true;
			player2.unjidelay = TimeUtils.nanoTime();
			slow.play();
		}
		
		//unji holding
		if (player1.holding == "Unji") {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "UP"){
				unji.rect.x = player1.topbox.x;
				unji.rect.y = player1.topbox.y;
				player1.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "DOWN"){
				unji.rect.x = player1.downbox.x;
				unji.rect.y = player1.downbox.y;
				player1.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "RIGHT"){
				unji.rect.x = player1.rightbox.x;
				unji.rect.y = player1.rightbox.y;
				player1.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "LEFT"){
				unji.rect.x = player1.leftbox.x;
				unji.rect.y = player1.leftbox.y;
				player1.holding = "";
				takeitem.play();
			}
		}
		
		if (player2.holding == "Unji") {
			if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "UP"){
				unji.rect.x = player2.topbox.x;
				unji.rect.y = player2.topbox.y;
				player2.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "DOWN"){
				unji.rect.x = player2.downbox.x;
				unji.rect.y = player2.downbox.y;
				player2.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "RIGHT"){
				unji.rect.x = player2.rightbox.x;
				unji.rect.y = player2.rightbox.y;
				player2.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "LEFT"){
				unji.rect.x = player2.leftbox.x;
				unji.rect.y = player2.leftbox.y;
				player2.holding = "";
				takeitem.play();
			}
		}
	}
	
	public void check_breakboxwalk(float delta) {
		//Check Collision
		for (int i=0 ; i < breakboxwalk.size ; ++i) {
			if (player1.rect.overlaps(breakboxwalk.get(i))) {
				player1.checkbreakboxwalk = true;
				break;
			}
			player1.checkbreakboxwalk = false;
		}
				
		for (int i=0 ; i < breakboxwalk.size ; ++i) {
			if (player2.rect.overlaps(breakboxwalk.get(i))) {
				player2.checkbreakboxwalk = true;
				break;
			}
			player2.checkbreakboxwalk = false;
		}
	}
	
	public void check_breakbox(float delta) {
		//Check Collision
		for (int i=0 ; i < breakbox.size ; ++i) {
			if (player1.rect.overlaps(breakbox.get(i))) {
				player1.checkoverlaps = true;
				break;
			}
		}
				
		for (int i=0 ; i < breakbox.size ; ++i) {
			if (player2.rect.overlaps(breakbox.get(i))) {
				player2.checkoverlaps = true;
				break;
			}
		}
	}
	
	public void check_exit(float delta) {
		
		for (int i=0; i < exit.size ; ++i) {
			if (player1.rect.overlaps(exit.get(i))) {
				player1.checkoverlaps = true;
				break;
			}
		}
		
		for (int i=0; i < exit.size ; ++i) {
			if (player2.rect.overlaps(exit.get(i))) {
				player2.checkoverlaps = true;
				break;
			}
		}
		
	}
	
	public void check_behind(float delta) {
		//Check Collision
				for (int i=0 ; i < behind.size ; ++i) {
					if (player1.rect.overlaps(behind.get(i))) {
						player1.checkbehind = true;
						break;
					}
					player1.checkbehind = false;
				}
						
				for (int i=0 ; i < behind.size ; ++i) {
					if (player2.rect.overlaps(behind.get(i))) {
						player2.checkbehind = true;
						break;
					}
					player2.checkbehind = false;
				}
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
			player1.speed = true;
			boost.play();
			
		}
		if (player2.rect.overlaps(shoes.rect)) {
			shoes.rect.x += 10000;
			player2.checkshoe = true;
			player2.shoedelay = TimeUtils.nanoTime();
			player2.speed = true;
			boost.play();
		}
		
		//Check Effect
		if (player1.checkshoe == true) {
			if (TimeUtils.nanoTime()- player1.shoedelay < 3000000000f) {
				player1.speedup = 2;
				player1.elapsedTime += 10*delta;
			}
			else {
				player1.speedup = 0;
				player1.shoedelay = 0;
				player1.checkshoe = false;
				player1.speed = false;
			}
		}
		
		if (player2.checkshoe == true) {
			if (TimeUtils.nanoTime()- player2.shoedelay < 3000000000f) {
				player2.speedup = 2;
				player2.elapsedTime += 10*delta;
			}
			else {
				player2.speedup = 0;
				player2.shoedelay = 0;
				player2.checkshoe = false;
				player2.speed = false;
			}
		}
	}
	
	public void check_banana(float delta) {
		//Banana
		if (player1.stuck == true) {
			if (TimeUtils.nanoTime()- player1.bananadelay < 1500000000f) {
				player1.currentFrame = (TextureRegion) player1.confused.getKeyFrame(player1.elapsedTime, true);
				player1.elapsedTime += 3*delta;
				player1.stuck = true;
			}
			else {
				player1.bananadelay = 0;
				player1.stuck = false;
			}
		}
		
		if (player2.stuck == true) {
			if (TimeUtils.nanoTime()- player2.bananadelay < 1500000000f) {
				player2.currentFrame = (TextureRegion) player2.confused.getKeyFrame(player2.elapsedTime, true);
				player2.elapsedTime += 3*delta;
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
			takeitem.play();
			
		}
		if (player2.rect.overlaps(banana.rect) && banana.peel == false) {
			banana.rect.x += 100000;
			player2.holding = "Banana";
			banana.peel = true;
			takeitem.play();
		}
		
		if (player1.rect.overlaps(banana.rect) && banana.peel == true) {
			banana.rect.x += 100000;
			player1.stuck = true;
			player1.bananadelay = TimeUtils.nanoTime();
			stun.play();
		}
		if (player2.rect.overlaps(banana.rect) && banana.peel == true) {
			banana.rect.x += 100000;
			player2.stuck = true;
			player2.bananadelay = TimeUtils.nanoTime();
			stun.play();
		}
		
		if (player1.holding == "Banana") {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "UP"){
				banana.rect.x = player1.topbox.x;
				banana.rect.y = player1.topbox.y;
				player1.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "DOWN"){
				banana.rect.x = player1.downbox.x;
				banana.rect.y = player1.downbox.y;
				player1.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "RIGHT"){
				banana.rect.x = player1.rightbox.x;
				banana.rect.y = player1.rightbox.y;
				player1.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "LEFT"){
				banana.rect.x = player1.leftbox.x;
				banana.rect.y = player1.leftbox.y;
				player1.holding = "";
				takeitem.play();
			}
		}
		
		if (player2.holding == "Banana") {
			if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "UP"){
				banana.rect.x = player2.topbox.x;
				banana.rect.y = player2.topbox.y;
				player2.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "DOWN"){
				banana.rect.x = player2.downbox.x;
				banana.rect.y = player2.downbox.y;
				player2.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "RIGHT"){
				banana.rect.x = player2.rightbox.x;
				banana.rect.y = player2.rightbox.y;
				player2.holding = "";
				takeitem.play();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "LEFT"){
				banana.rect.x = player2.leftbox.x;
				banana.rect.y = player2.leftbox.y;
				player2.holding = "";
				takeitem.play();
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
			if(Gdx.input.isKeyPressed(Input.Keys.W) && player1.checkoverlaps == false && player1.stuck == false
					&& player1.canstuck == false){
	            player1.currentFrame = (TextureRegion) player1.go_up.getKeyFrame(player1.elapsedTime, true);
	            player1.pos_y += 3+player1.speedup;
	            if (player1.slow == false && player1.speed == false && player1.boneslow == false)
	            	player1.elapsedTime += 3*delta;
	            player1.stop = 1;
	            player1.rect.y += 3+player1.speedup;
	            player1.body.y += 3+player1.speedup;
	            player1.topbox.y += 3+player1.speedup;
				player1.downbox.y += 3+player1.speedup;
				player1.rightbox.y += 3+player1.speedup;
				player1.leftbox.y += 3+player1.speedup;
	            player1.prevkey = "UP";
	        }
			else if(Gdx.input.isKeyPressed(Input.Keys.S) && player1.checkoverlaps == false && player1.stuck == false
					&& player1.canstuck == false) {
				player1.currentFrame = (TextureRegion) player1.go_down.getKeyFrame(player1.elapsedTime, true);
				player1.pos_y -= 3+player1.speedup;
				if (player1.slow == false && player1.speed == false && player1.boneslow == false)
					player1.elapsedTime += 3*delta;
				player1.stop = 0;
				player1.rect.y -= 3+player1.speedup;
				player1.body.y -= 3+player1.speedup;
				player1.topbox.y -= 3+player1.speedup;
				player1.downbox.y -= 3+player1.speedup;
				player1.rightbox.y -= 3+player1.speedup;
				player1.leftbox.y -= 3+player1.speedup;
				player1.prevkey = "DOWN";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.D) && player1.checkoverlaps == false && player1.stuck == false
					&& player1.canstuck == false) {
				player1.currentFrame = (TextureRegion) player1.go_right.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x += 3+player1.speedup;
				if (player1.slow == false && player1.speed == false && player1.boneslow == false)
					player1.elapsedTime += 3*delta;
				player1.stop = 2;
				player1.rect.x += 3+player1.speedup;
				player1.body.x += 3+player1.speedup;
				player1.topbox.x += 3+player1.speedup;
				player1.downbox.x += 3+player1.speedup;
				player1.rightbox.x += 3+player1.speedup;
				player1.leftbox.x += 3+player1.speedup;
				player1.prevkey = "RIGHT";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.A) && player1.checkoverlaps == false && player1.stuck == false
					&& player1.canstuck == false) {
				player1.currentFrame = (TextureRegion) player1.go_left.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x -= 3+player1.speedup;
				if (player1.slow == false && player1.speed == false && player1.boneslow == false)
					player1.elapsedTime += 3*delta;
				player1.stop = 3;
				player1.rect.x -= 3+player1.speedup;
				player1.body.x -= 3+player1.speedup;
				player1.topbox.x -= 3+player1.speedup;
				player1.downbox.x -= 3+player1.speedup;
				player1.rightbox.x -= 3+player1.speedup;
				player1.leftbox.x -= 3+player1.speedup;
				player1.prevkey = "LEFT";
			}
			else if (player1.checkoverlaps == true && player1.prevkey == "UP") {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0.5f, true);
				player1.pos_y -= 0.5;
				player1.rect.y -= 0.5;
				player1.body.y -= 0.5;
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
				player1.body.y += 0.5;
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
				player1.body.x -= 0.5;
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
				player1.body.x += 0.5;
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
			if(Gdx.input.isKeyPressed(Input.Keys.UP) && player2.checkoverlaps == false && player2.stuck == false
					&& player2.canstuck == false) {
				player2.currentFrame = (TextureRegion) player2.go_up.getKeyFrame(player2.elapsedTime, true);
			    player2.pos_y += 3+player2.speedup;
			    player2.rect.y += 3+player2.speedup;
			    player2.body.y += 3+player2.speedup;
			    player2.topbox.y += 3+player2.speedup;
				player2.downbox.y += 3+player2.speedup;
				player2.rightbox.y += 3+player2.speedup;
				player2.leftbox.y += 3+player2.speedup;
				if (player2.slow == false && player2.speed == false && player2.boneslow == false)
					player2.elapsedTime += 3*delta;
			    player2.stop = 1;
			    player2.prevkey = "UP";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player2.checkoverlaps == false && player2.stuck == false
					&& player2.canstuck == false) {
				player2.currentFrame = (TextureRegion) player2.go_down.getKeyFrame(player2.elapsedTime, true);
				player2.pos_y -= 3+player2.speedup;
				player2.rect.y -= 3+player2.speedup;
				player2.body.y -= 3+player2.speedup;
				player2.topbox.y -= 3+player2.speedup;
				player2.downbox.y -= 3+player2.speedup;
				player2.rightbox.y -= 3+player2.speedup;
				player2.leftbox.y -= 3+player2.speedup;
				if (player2.slow == false && player2.speed == false && player2.boneslow == false)
					player2.elapsedTime += 3*delta;
				player2.stop = 0;
				player2.prevkey = "DOWN";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player2.checkoverlaps == false && player2.stuck == false
					&& player2.canstuck == false) {
				player2.currentFrame = (TextureRegion) player2.go_right.getKeyFrame(player2.elapsedTime, true);
				player2.pos_x += 3+player2.speedup;
				player2.rect.x += 3+player2.speedup;
				player2.body.x += 3+player2.speedup;
				player2.topbox.x += 3+player2.speedup;
				player2.downbox.x += 3+player2.speedup;
				player2.rightbox.x += 3+player2.speedup;
				player2.leftbox.x += 3+player2.speedup;
				if (player2.slow == false && player2.speed == false && player2.boneslow == false)
					player2.elapsedTime += 3*delta;
				player2.stop = 2;
				player2.prevkey = "RIGHT";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player2.checkoverlaps == false && player2.stuck == false
					&& player2.canstuck == false) {
				player2.currentFrame = (TextureRegion) player2.go_left.getKeyFrame(player2.elapsedTime, true);
				player2.pos_x -= 3+player2.speedup;
				player2.rect.x -= 3+player2.speedup;
				player2.body.x -= 3+player2.speedup;
				player2.topbox.x -= 3+player2.speedup;
				player2.downbox.x -= 3+player2.speedup;
				player2.rightbox.x -= 3+player2.speedup;
				player2.leftbox.x -= 3+player2.speedup;
				if (player2.slow == false && player2.speed == false && player2.boneslow == false)
					player2.elapsedTime += 3*delta;
				player2.stop = 3;
				player2.prevkey = "LEFT";
			}
			else if (player2.checkoverlaps == true && player2.prevkey == "UP") {
				player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0.5f, true);
				player2.pos_y -= 0.5;
				player2.rect.y -= 0.5;
				player2.body.y -= 0.5;
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
				player2.body.y += 0.5;
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
				player2.body.x -= 0.5;
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
				player2.body.x += 0.5;
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
		if (player1.rect.overlaps(player2.rect) && player1.win == false && player1.stuck == false) {
			player1.score ++;
			hud.score1 = player1.score;
			hud.score1Label.setText(String.format("%02d", hud.score1));
			player1.win = true;
			wintime = 0;
			winning.play();
		}
		else if (hud.sec == 0 && hud.min == 0 && player2.win == false) {
			if (escapecount == 0)
				escapesound.play();
			escapecount++;
			if (escape == exit.get(0) && player2.win == false) {
				System.out.println(0);
				tiledMap.getLayers().get("exit1").setVisible(false);
				if (player2.rect.overlaps(exit.get(0))) {
					player2.score++;
					player2.win = true;
					wintime = 0;
					winning.play();
				}
			}
			else if (escape == exit.get(1) && player2.win == false) {
				System.out.println(1);
				tiledMap.getLayers().get("exit2").setVisible(false);
				if (player2.rect.overlaps(exit.get(1))) {
					player2.score++;
					player2.win = true;
					wintime = 0;
					winning.play();
				}
			}
			else if (escape == exit.get(2) && player2.win == false) {
				System.out.println(2);
				tiledMap.getLayers().get("exit3").setVisible(false);
				if (player2.rect.overlaps(exit.get(2))) {
					player2.score++;
					player2.win = true;
					wintime = 0;
					winning.play();
				}
			}
			
		}
		if (wintime >= 3 && (player1.win == true || player2.win == true)) {
			if (player1.win == true) {
				music.stop();
				main.setScreen(new Chapter3(main, player1.score, player2.score));
			}
			if (player2.win == true) {
				music.stop();
				main.setScreen(new Chapter3(main, player1.score, player2.score));
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
		check_bone(delta);
		check_can(delta);
		check_pickaxe(delta);
		check_unji(delta);
		check_breakboxwalk(delta);
		check_exit(delta);
		check_breakbox(delta);
		check_behind(delta);
		check_wall(delta);
		check_win(delta);
		check_shoes(delta);
		countdown(delta);
				
		//Change map
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			main.setScreen(new Chapter1(main));
			music.stop();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
			main.setScreen(new Chapter3(main, player1.score, player2.score));
			music.stop();
		}
		//---------------------------------------------------//
		
		//Check Clipping//
//		player1.rect.x = Clipping.check_x(player1.rect.x, player1.size_x);
//		player1.rect.y = Clipping.check_y(player1.rect.y, player1.size_y);
//		
//		player2.rect.x = Clipping.check_x(player2.rect.x, player2.size_x);
//		player2.rect.y = Clipping.check_y(player2.rect.y, player2.size_y);
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
		//shoes
		batch.draw(img_shoe, shoes.rect.x, shoes.rect.y);
		
		//banana
		if (banana.peel == false) {
			batch.draw(img_banana, banana.rect.x, banana.rect.y);
		}
		else {
			batch.draw(img_bananapeel, banana.rect.x, banana.rect.y);
		}
		
		//unji
		if (unji.unsack == false) {
			batch.draw(img_unjisack, unji.rect.x, unji.rect.y);
		}
		else {
			batch.draw(img_unji, unji.rect.x, unji.rect.y);
		}
				
		//Pickaxe
		batch.draw(img_pickaxe, pickaxe.rect.x, pickaxe.rect.y);
		
		//Can
		if (can.pickup == false) {
			can.currentFrame = (TextureRegion) can.anime.getKeyFrame(0, true);
			batch.draw(can.currentFrame, can.rect.x, can.rect.y);
		}
				
		//Can is throwed
		if (can.throwup == true && !(can.rect.overlaps(player1.rect)) && !(can.rect.overlaps(player2.rect))) {
			can.currentFrame = (TextureRegion) can.anime.getKeyFrame(can.elapsedTime, true);
			can.elapsedTime += 3*delta;
			can.rect.y += 4;
			batch.draw(can.currentFrame, can.rect.x, can.rect.y);
		}
		if (can.throwdown == true && !(can.rect.overlaps(player1.rect)) && !(can.rect.overlaps(player2.rect))) {
			can.currentFrame = (TextureRegion) can.anime.getKeyFrame(can.elapsedTime, true);
			can.elapsedTime += 3*delta;
			can.rect.y -= 4;
			batch.draw(can.currentFrame, can.rect.x, can.rect.y);
		}
		if (can.throwright == true && !(can.rect.overlaps(player1.rect)) && !(can.rect.overlaps(player2.rect))) {
			can.currentFrame = (TextureRegion) can.anime.getKeyFrame(can.elapsedTime, true);
			can.elapsedTime += 3*delta;
			can.rect.x += 4;
			batch.draw(can.currentFrame, can.rect.x, can.rect.y);
		}
		if (can.throwleft == true && !(can.rect.overlaps(player1.rect)) && !(can.rect.overlaps(player2.rect))) {
			can.currentFrame = (TextureRegion) can.anime.getKeyFrame(can.elapsedTime, true);
			can.elapsedTime += 3*delta;
			can.rect.x -= 4;
			batch.draw(can.currentFrame, can.rect.x, can.rect.y);
		}
				
		//Bone
		if (bone.pickup == false) {
			bone.currentFrame = (TextureRegion) bone.anime.getKeyFrame(0, true);
			batch.draw(bone.currentFrame, bone.rect.x, bone.rect.y);
		}
		
		//Bone is throwed
		if (bone.throwup == true && !(bone.rect.overlaps(player1.rect)) && !(bone.rect.overlaps(player2.rect))) {
			bone.currentFrame = (TextureRegion) bone.anime.getKeyFrame(bone.elapsedTime, true);
			bone.elapsedTime += 3*delta;
			bone.rect.y += 4;
			batch.draw(bone.currentFrame, bone.rect.x, bone.rect.y);
		}
		if (bone.throwdown == true && !(bone.rect.overlaps(player1.rect)) && !(bone.rect.overlaps(player2.rect))) {
			bone.currentFrame = (TextureRegion) bone.anime.getKeyFrame(bone.elapsedTime, true);
			bone.elapsedTime += 3*delta;
			bone.rect.y -= 4;
			batch.draw(bone.currentFrame, bone.rect.x, bone.rect.y);
		}
		if (bone.throwright == true && !(bone.rect.overlaps(player1.rect)) && !(bone.rect.overlaps(player2.rect))) {
			bone.currentFrame = (TextureRegion) bone.anime.getKeyFrame(bone.elapsedTime, true);
			bone.elapsedTime += 3*delta;
			bone.rect.x += 4;
			batch.draw(bone.currentFrame, bone.rect.x, bone.rect.y);
		}
		if (bone.throwleft == true && !(bone.rect.overlaps(player1.rect)) && !(bone.rect.overlaps(player2.rect))) {
			bone.currentFrame = (TextureRegion) bone.anime.getKeyFrame(bone.elapsedTime, true);
			bone.elapsedTime += 3*delta;
			bone.rect.x -= 4;
			batch.draw(bone.currentFrame, bone.rect.x, bone.rect.y);
		}
		
		
		//Player
		batch.draw(player1.currentFrame, player1.body.x, player1.body.y, player1.size_x, player1.size_y);
		batch.draw(player2.currentFrame, player2.body.x, player2.body.y, player2.size_x, player2.size_y);
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
		
		//Behind Things
		int[] layer = {7, 8, 9, 10};
		if (player1.checkbehind == true || player2.checkbehind == true) {
			tiledMapRenderer.render(layer);
		}
		
		
		//BreakBoxWalk
		int[] layer1 = {11, 12, 13, 14, 15, 16, 17, 18};
		if (player1.checkbreakboxwalk == true || player2.checkbreakboxwalk == true) {
			tiledMapRenderer.render(layer1);
		}
		
		
		//Every 1 box
        int[] GT1 = {44};
		if (player1.rect.overlaps(GO1) || player2.rect.overlaps(GO1)) {
			tiledMapRenderer.render(GT1);
		}
		int[] GT2 = {45};
		if (player1.rect.overlaps(GO2) || player2.rect.overlaps(GO2)) {
			tiledMapRenderer.render(GT2);
		}
		int[] GT3 = {46};
		if (player1.rect.overlaps(GO3) || player2.rect.overlaps(GO3)) {
			tiledMapRenderer.render(GT3);
		}
		int[] GT4 = {47};
		if (player1.rect.overlaps(GO4) || player2.rect.overlaps(GO4)) {
			tiledMapRenderer.render(GT4);
		}
		int[] GT5 = {48};
		if (player1.rect.overlaps(GO5) || player2.rect.overlaps(GO5)) {
			tiledMapRenderer.render(GT5);
		}
		int[] GT6 = {49};
		if (player1.rect.overlaps(GO6) || player2.rect.overlaps(GO6)) {
			tiledMapRenderer.render(GT6);
		}
		int[] GT7 = {50};
		if (player1.rect.overlaps(GO7) || player2.rect.overlaps(GO7)) {
			tiledMapRenderer.render(GT7);
		}
		int[] GT8 = {51};
		if (player1.rect.overlaps(GO8) || player2.rect.overlaps(GO8)) {
			tiledMapRenderer.render(GT8);
		}
		int[] GT9 = {52};
		if (player1.rect.overlaps(GO9) || player2.rect.overlaps(GO9)) {
			tiledMapRenderer.render(GT9);
		}
		int[] GT10 = {53};
		if (player1.rect.overlaps(GO10) || player2.rect.overlaps(GO10)) {
			tiledMapRenderer.render(GT10);
		}
		int[] GT11 = {54};
		if (player1.rect.overlaps(GO11) || player2.rect.overlaps(GO11)) {
			tiledMapRenderer.render(GT11);
		}
		int[] GT12 = {55};
		if (player1.rect.overlaps(GO12) || player2.rect.overlaps(GO12)) {
			tiledMapRenderer.render(GT12);
		}
		int[] GT13 = {56};
		if (player1.rect.overlaps(GO13) || player2.rect.overlaps(GO13)) {
			tiledMapRenderer.render(GT13);
		}
		int[] GT14 = {57};
		if (player1.rect.overlaps(GO14) || player2.rect.overlaps(GO14)) {
			tiledMapRenderer.render(GT14);
		}
		int[] GT15 = {58};
		if (player1.rect.overlaps(GO15) || player2.rect.overlaps(GO15)) {
			tiledMapRenderer.render(GT15);
		}
		int[] GT16 = {59};
		if (player1.rect.overlaps(GO16) || player2.rect.overlaps(GO16)) {
			tiledMapRenderer.render(GT16);
		}
		int[] GT17 = {60};
		if (player1.rect.overlaps(GO17) || player2.rect.overlaps(GO17)) {
			tiledMapRenderer.render(GT17);
		}
		int[] GT18 = {61};
		if (player1.rect.overlaps(GO18) || player2.rect.overlaps(GO18)) {
			tiledMapRenderer.render(GT18);
		}
		int[] GT19 = {62};
		if (player1.rect.overlaps(GO19) || player2.rect.overlaps(GO19)) {
			tiledMapRenderer.render(GT19);
		}
		int[] GT20 = {63};
		if (player1.rect.overlaps(GO20) || player2.rect.overlaps(GO20)) {
			tiledMapRenderer.render(GT20);
		}
		int[] GT21 = {64};
		if (player1.rect.overlaps(GO21) || player2.rect.overlaps(GO21)) {
			tiledMapRenderer.render(GT21);
		}
		int[] GT22 = {65};
		if (player1.rect.overlaps(GO22) || player2.rect.overlaps(GO22)) {
			tiledMapRenderer.render(GT22);
		}
		int[] GT23 = {66};
		if (player1.rect.overlaps(GO23) || player2.rect.overlaps(GO23)) {
			tiledMapRenderer.render(GT23);
		}
		int[] GT24 = {67};
		if (player1.rect.overlaps(GO24) || player2.rect.overlaps(GO24)) {
			tiledMapRenderer.render(GT24);
		}
		int[] GT25 = {68};
		if (player1.rect.overlaps(GO25) || player2.rect.overlaps(GO25)) {
			tiledMapRenderer.render(GT25);
		}
		int[] GT26 = {69};
		if (player1.rect.overlaps(GO26) || player2.rect.overlaps(GO26)) {
			tiledMapRenderer.render(GT26);
		}
		int[] GT27 = {70};
		if (player1.rect.overlaps(GO27) || player2.rect.overlaps(GO27)) {
			tiledMapRenderer.render(GT27);
		}
		int[] GT28 = {71};
		if (player1.rect.overlaps(GO28) || player2.rect.overlaps(GO28)) {
			tiledMapRenderer.render(GT28);
		}
		int[] GT29 = {72};
		if (player1.rect.overlaps(GO29) || player2.rect.overlaps(GO29)) {
			tiledMapRenderer.render(GT29);
		}
		int[] GT30 = {73};
		if (player1.rect.overlaps(GO30) || player2.rect.overlaps(GO30)) {
			tiledMapRenderer.render(GT30);
		}
		int[] GT31 = {74};
		if (player1.rect.overlaps(GO31) || player2.rect.overlaps(GO31)) {
			tiledMapRenderer.render(GT31);
		}
		int[] GT32 = {75};
		if (player1.rect.overlaps(GO32) || player2.rect.overlaps(GO32)) {
			tiledMapRenderer.render(GT32);
		}
		int[] GT33 = {76};
		if (player1.rect.overlaps(GO33) || player2.rect.overlaps(GO33)) {
			tiledMapRenderer.render(GT33);
		}
		int[] GT34 = {77};
		if (player1.rect.overlaps(GO34) || player2.rect.overlaps(GO34)) {
			tiledMapRenderer.render(GT34);
		}
		int[] GT35 = {78};
		if (player1.rect.overlaps(GO35) || player2.rect.overlaps(GO35)) {
			tiledMapRenderer.render(GT35);
		}
		int[] GT36 = {79};
		if (player1.rect.overlaps(GO36) || player2.rect.overlaps(GO36)) {
			tiledMapRenderer.render(GT36);
		}
		int[] GT37 = {80};
		if (player1.rect.overlaps(GO37) || player2.rect.overlaps(GO37)) {
			tiledMapRenderer.render(GT37);
		}
		int[] GT38 = {81};
		if (player1.rect.overlaps(GO38) || player2.rect.overlaps(GO38)) {
			tiledMapRenderer.render(GT38);
		}
		int[] GT39 = {82};
		if (player1.rect.overlaps(GO39) || player2.rect.overlaps(GO39)) {
			tiledMapRenderer.render(GT39);
		}
		int[] GT40 = {83};
		if (player1.rect.overlaps(GO40) || player2.rect.overlaps(GO40)) {
			tiledMapRenderer.render(GT40);
		}
		int[] GT41 = {84};
		if (player1.rect.overlaps(GO41) || player2.rect.overlaps(GO41)) {
			tiledMapRenderer.render(GT41);
		}
		int[] GT42 = {85};
		if (player1.rect.overlaps(GO42) || player2.rect.overlaps(GO42)) {
			tiledMapRenderer.render(GT42);
		}
		int[] GT43 = {86};
		if (player1.rect.overlaps(GO43) || player2.rect.overlaps(GO43)) {
			tiledMapRenderer.render(GT43);
		}
		int[] GT44 = {87};
		if (player1.rect.overlaps(GO44) || player2.rect.overlaps(GO44)) {
			tiledMapRenderer.render(GT44);
		}
		int[] GT45 = {88};
		if (player1.rect.overlaps(GO45) || player2.rect.overlaps(GO45)) {
			tiledMapRenderer.render(GT45);
		}
		int[] GT46 = {89};
		if (player1.rect.overlaps(GO46) || player2.rect.overlaps(GO46)) {
			tiledMapRenderer.render(GT46);
		}
		int[] GT47 = {90};
		if (player1.rect.overlaps(GO47) || player2.rect.overlaps(GO47)) {
			tiledMapRenderer.render(GT47);
		}
		
		
		//Statusbar
		batch.begin();
		batch.draw(img_stat1, 320, 630);
		batch.draw(img_stat2, 360, 630);
		batch.draw(img_stat1, 1150, 630);
		batch.draw(img_stat2, 1190, 630);
		//item
		if (player1.holding == "Banana")
			batch.draw(img_banana, 327, 637, 25, 25);
		if (player1.holding == "Can")
			batch.draw(can.atlas.findRegion("1"), 327, 637, 25, 25);
		if (player1.holding == "Bone")
			batch.draw(bone.atlas.findRegion("1"), 327, 637, 25, 25);
		if (player1.holding == "Unji")
			batch.draw(img_unjisack, 327, 637, 25, 25);
		if (player1.holding == "Pickaxe")
			batch.draw(img_pickaxe, 327, 637, 25, 25);
		if (player2.holding == "Banana")
			batch.draw(img_banana, 1157, 637, 25, 25);
		if (player2.holding == "Can")
			batch.draw(can.atlas.findRegion("1"), 1157, 637, 25, 25);
		if (player2.holding == "Bone")
			batch.draw(bone.atlas.findRegion("1"), 1157, 637, 25, 25);
		if (player2.holding == "Unji")
			batch.draw(img_unjisack, 1157, 637, 25, 25);
		if (player2.holding == "Pickaxe")
			batch.draw(img_pickaxe, 1157, 637, 25, 25);
		//effect
		if (player1.slow == true)
			batch.draw(img_unji, 367, 637, 25, 25);
		if (player1.speedup > 0)
			batch.draw(img_shoe, 367, 637, 25, 25);
		if (player1.stuck == true)
			batch.draw(img_bananapeel, 367, 637, 25, 25);
		if (player2.slow == true)
			batch.draw(img_unji, 1197, 637, 25, 25);
		if (player2.speedup > 0)
			batch.draw(img_shoe, 1197, 637, 25, 25);
		if (player2.stuck == true)
			batch.draw(img_bananapeel, 1197, 637, 25, 25);
		batch.end();
				
		//victory
		batch.begin();
		if (player1.win == true) {
			batch.draw(hunterwin, (w/2)-600, (h/2)-100, 1200, 400);
		}
						
		if (player2.win == true) {
			batch.draw(runnerwin, (w/2)-600, (h/2)-100, 1200, 400);
		}
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
		img_unjisack.dispose();
		img_unji.dispose();
		img_pickaxe.dispose();
		can.atlas.dispose();
		bone.atlas.dispose();
		boost.dispose();
		throwcan.dispose();
		throwbone.dispose();
		hitcan.dispose();
		hitbone.dispose();
		takeitem.dispose();
		broken.dispose();
		slow.dispose();
		stun.dispose();
		img_stat1.dispose();
		img_stat2.dispose();
		winning.dispose();
		escapesound.dispose();
		
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
