package com.mygdx.game.screen;

import java.util.Random;

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
import com.badlogic.gdx.maps.MapLayer;
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
import com.mygdx.game.item.Pickaxe;
import com.mygdx.game.item.Shoes;
import com.mygdx.game.item.Unji;
import com.mygdx.game.model.Character;
import com.mygdx.game.tools.Clipping;

public class Chapter1 implements Screen{
	final Main main;
	
	//Batch
	public SpriteBatch batch;
	//Image
	public Texture grid, img_shoe, img_banana, img_bananapeel, hunterwin, runnerwin;
	public Texture img_unjisack, img_unji, img_pickaxe;
	//Character
	public Character player1;
	public Character player2;
	//TiledMap
	public TiledMap tiledMap;
	public TiledMapRenderer tiledMapRenderer;
	public OrthographicCamera camera;
	//Objects Array
	public int boxescount = 0, cactuscount = 0, behindcount = 0, breakboxcount = 0;
	public Array<Rectangle> boxes = new Array<Rectangle>();
	public Array<Rectangle> cactus = new Array<Rectangle>();
	public Array<Rectangle> behind = new Array<Rectangle>();
	public Array<Rectangle> breakbox = new Array<Rectangle>();
	//Exit
	public int escapeint = MathUtils.random(3);
	public Rectangle escape;
	public Array<Rectangle> exit = new Array<Rectangle>();
	//Sound
	public Sound wallcrash, music;
	//item
	public Shoes shoes;
	public Banana banana;
	public Unji unji;
	public Pickaxe pickaxe;
	//ViewPort, HUD
	public Viewport gamePort;
	public Hud hud;
	public float w, h;
	//WinTime
	public float wintime = 0;
	
	//RectBehind
	public Rectangle hideb1;
	public Rectangle hideb2;
	public Rectangle hideb3;
	public Rectangle hideb4;
	public Rectangle hideb5;
	public Rectangle hideb6;
	public Rectangle hideb7;
	public Rectangle hideb8;
	public Rectangle hideb9;
	public Rectangle hideb10;
	public Rectangle hideb11;
	public Rectangle hideb12;
	public Rectangle hideb13;
	public Rectangle hideb14;
	public Rectangle hideb15;
	public Rectangle hideb16;
	public Rectangle hideb17;
	public Rectangle hideb18;
	public Rectangle hideb19;
	public Rectangle hideb20;
	public Rectangle hideb21;
	public Rectangle hideb22;
	public Rectangle hideb23;
	public Rectangle hideb24;
	public Rectangle hideb25;
	public Rectangle hideb26;
	public Rectangle hideb27;
	public Rectangle hideb28;
	public Rectangle hideb29;
	public Rectangle hideb30;
	public Rectangle hideb31;
	public Rectangle hideb32;
	public Rectangle hideb33;
	public Rectangle hideb34;
	public Rectangle hideb35;
	public Rectangle hideb36;
	public Rectangle hideb37;
	public Rectangle hideb38;
	public Rectangle hideb39;
	public Rectangle hideb40;
	public Rectangle hideb41;
	public Rectangle hideb42;
	public Rectangle hideb43;
	public Rectangle hideb44;
	public Rectangle hideb45;
	public Rectangle hideb46;
	
	
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
		img_unjisack = new Texture(Gdx.files.internal("item/Unji-Sack.png"));
		img_unji = new Texture(Gdx.files.internal("item/Unji.png"));
		img_pickaxe = new Texture(Gdx.files.internal("item/Pickaxe.png"));
		//victory
		hunterwin = new Texture(Gdx.files.internal("victory/huntwinsnow.png"));
		runnerwin = new Texture(Gdx.files.internal("victory/runwinsnow.png"));
		
		//TiledMap
		tiledMap = new TmxMapLoader().load("tilemap/desert/desert1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
//		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(3);
		
		//Sound
		wallcrash = Gdx.audio.newSound(Gdx.files.internal("sound/effect/wallcrash.ogg"));
		music = Gdx.audio.newSound(Gdx.files.internal("sound/music/mapdesert.mp3"));
		music.loop();

		//Camera, Viewport
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        gamePort = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, camera);
		
        //Objects
		player1 = new Character(100, 40);
		player2 = new Character(1280-100, 500);
		//item
		shoes = new Shoes(300, 300);
		banana = new Banana(500, 500);
		unji = new Unji(300, 350);
		pickaxe = new Pickaxe(550, 500);
		
		//HUD
        hud = new Hud(batch, player1.score, player2.score);
		
		/*-------------Texture,Animation of Objects-----------*/
        //behind
        for (MapObject object : tiledMap.getLayers().get("behind").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			behind.add(rect);;
			++behindcount;
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
        for (MapObject object : tiledMap.getLayers().get("oexit4").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	exit.add(rect);
        	break;
		}
        
        
        //BreakBox
        for (MapObject object : tiledMap.getLayers().get("obreak1").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak2").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak3").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak4").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak5").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak6").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak7").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak8").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak9").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak10").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak11").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        for (MapObject object : tiledMap.getLayers().get("obreak12").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	breakbox.add(rect);
			++breakboxcount;
			break;
		}
        
        
        
        //every 1 box
        for (MapObject object : tiledMap.getLayers().get("hideb1").getObjects().getByType(RectangleMapObject.class)) {
        	hideb1 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb2").getObjects().getByType(RectangleMapObject.class)) {
        	hideb2 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb3").getObjects().getByType(RectangleMapObject.class)) {
        	hideb3 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb4").getObjects().getByType(RectangleMapObject.class)) {
        	hideb4 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb5").getObjects().getByType(RectangleMapObject.class)) {
        	hideb5 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb6").getObjects().getByType(RectangleMapObject.class)) {
        	hideb6 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb7").getObjects().getByType(RectangleMapObject.class)) {
        	hideb7 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb8").getObjects().getByType(RectangleMapObject.class)) {
        	hideb8 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb9").getObjects().getByType(RectangleMapObject.class)) {
        	hideb9 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb10").getObjects().getByType(RectangleMapObject.class)) {
        	hideb10 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb11").getObjects().getByType(RectangleMapObject.class)) {
        	hideb11 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb12").getObjects().getByType(RectangleMapObject.class)) {
        	hideb12 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb13").getObjects().getByType(RectangleMapObject.class)) {
        	hideb13 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb14").getObjects().getByType(RectangleMapObject.class)) {
        	hideb14 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb15").getObjects().getByType(RectangleMapObject.class)) {
        	hideb15 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb16").getObjects().getByType(RectangleMapObject.class)) {
        	hideb16 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb17").getObjects().getByType(RectangleMapObject.class)) {
        	hideb17 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb18").getObjects().getByType(RectangleMapObject.class)) {
        	hideb18 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb19").getObjects().getByType(RectangleMapObject.class)) {
        	hideb19 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb20").getObjects().getByType(RectangleMapObject.class)) {
        	hideb20 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb21").getObjects().getByType(RectangleMapObject.class)) {
        	hideb21 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb22").getObjects().getByType(RectangleMapObject.class)) {
        	hideb22 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb23").getObjects().getByType(RectangleMapObject.class)) {
        	hideb23 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb24").getObjects().getByType(RectangleMapObject.class)) {
        	hideb24 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb25").getObjects().getByType(RectangleMapObject.class)) {
        	hideb25 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb26").getObjects().getByType(RectangleMapObject.class)) {
        	hideb26 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb27").getObjects().getByType(RectangleMapObject.class)) {
        	hideb27 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb28").getObjects().getByType(RectangleMapObject.class)) {
        	hideb28 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb29").getObjects().getByType(RectangleMapObject.class)) {
        	hideb29 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb30").getObjects().getByType(RectangleMapObject.class)) {
        	hideb30 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb31").getObjects().getByType(RectangleMapObject.class)) {
        	hideb31 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb32").getObjects().getByType(RectangleMapObject.class)) {
        	hideb32 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb33").getObjects().getByType(RectangleMapObject.class)) {
        	hideb33 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb34").getObjects().getByType(RectangleMapObject.class)) {
        	hideb34 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb35").getObjects().getByType(RectangleMapObject.class)) {
        	hideb35 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb36").getObjects().getByType(RectangleMapObject.class)) {
        	hideb36 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb37").getObjects().getByType(RectangleMapObject.class)) {
        	hideb37 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb38").getObjects().getByType(RectangleMapObject.class)) {
        	hideb38 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb39").getObjects().getByType(RectangleMapObject.class)) {
        	hideb39 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb40").getObjects().getByType(RectangleMapObject.class)) {
        	hideb40 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb41").getObjects().getByType(RectangleMapObject.class)) {
        	hideb41 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb42").getObjects().getByType(RectangleMapObject.class)) {
        	hideb42 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb43").getObjects().getByType(RectangleMapObject.class)) {
        	hideb43 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb44").getObjects().getByType(RectangleMapObject.class)) {
        	hideb44 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb45").getObjects().getByType(RectangleMapObject.class)) {
        	hideb45 = ((RectangleMapObject) object).getRectangle();
		}
        for (MapObject object : tiledMap.getLayers().get("hideb46").getObjects().getByType(RectangleMapObject.class)) {
        	hideb46 = ((RectangleMapObject) object).getRectangle();
		}
        
        
        
        //cactus
        for (MapObject object : tiledMap.getLayers().get("Crash").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			cactus.add(rect);
			++cactuscount;
		}
        
		//box
		for (MapObject object : tiledMap.getLayers().get("ObjectCrash").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			boxes.add(rect);;
			++boxescount;
		}
		
		for (MapObject object : tiledMap.getLayers().get("Boxframe").getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			boxes.add(rect);;
			++boxescount;
		}
		
		for (MapObject object : tiledMap.getLayers().get("checkb1").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb2").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb3").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb4").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb5").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb6").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb7").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb8").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb9").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb10").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb11").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb12").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb13").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb14").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb15").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb16").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb17").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb18").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb19").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb20").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb21").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb22").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb23").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb24").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb25").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb26").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb27").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb28").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb29").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb30").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb31").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb32").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb33").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb34").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb35").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb36").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb37").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb38").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb39").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb40").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb41").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb42").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb43").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb44").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb45").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		for (MapObject object : tiledMap.getLayers().get("checkb46").getObjects().getByType(RectangleMapObject.class)) {
        	Rectangle rect = ((RectangleMapObject) object).getRectangle();
        	boxes.add(rect);;
			++boxescount;
		}
		
		//EscapeWay
		escape = exit.get(escapeint);
		//Player1
		player1.atlas = new TextureAtlas(Gdx.files.internal("player/desert/hunter/mummy.atlas"));
		player1.go_up = new Animation(1/2f, player1.atlas.findRegion("md1"), player1.atlas.findRegion("md2"));
		player1.go_down = new Animation(1/2f, player1.atlas.findRegion("mu1"), player1.atlas.findRegion("mu2"));
		player1.go_right = new Animation(1/2f, player1.atlas.findRegion("ml1"), player1.atlas.findRegion("ml2"), player1.atlas.findRegion("ml3"), player1.atlas.findRegion("ml4"), player1.atlas.findRegion("ml5"));
		player1.go_left = new Animation(1/2f, player1.atlas.findRegion("mr1"), player1.atlas.findRegion("mr2"), player1.atlas.findRegion("mr3"), player1.atlas.findRegion("mr4"), player1.atlas.findRegion("mr5"));
		player1.stand = new Animation(1/2f, player1.atlas.findRegion("mfu"), player1.atlas.findRegion("mbd"), player1.atlas.findRegion("fl"), player1.atlas.findRegion("fr"));
		player1.confused = new Animation(1/2f, player1.atlas.findRegion("mc1"), player1.atlas.findRegion("mc2"), player1.atlas.findRegion("mc3"));
		
		//Player2
		player2.atlas = new TextureAtlas(Gdx.files.internal("player/desert/runner/indian.atlas"));
		player2.go_up = new Animation(1/2f, player2.atlas.findRegion("iu1"), player2.atlas.findRegion("iu2"));
		player2.go_down = new Animation(1/2f, player2.atlas.findRegion("id1"), player2.atlas.findRegion("id2"));
		player2.go_right = new Animation(1/2f, player2.atlas.findRegion("il1"), player2.atlas.findRegion("il2"), player2.atlas.findRegion("il3"), player2.atlas.findRegion("il4"), player2.atlas.findRegion("il5"));
		player2.go_left = new Animation(1/2f, player2.atlas.findRegion("ir1"), player2.atlas.findRegion("ir2"), player2.atlas.findRegion("inr3"), player2.atlas.findRegion("ir4"), player2.atlas.findRegion("inr5"));
		player2.stand = new Animation(1/2f, player2.atlas.findRegion("ifd"), player2.atlas.findRegion("ibu"), player2.atlas.findRegion("fl-"), player2.atlas.findRegion("fr"));
		player2.confused = new Animation(1/2f, player2.atlas.findRegion("ic1"), player2.atlas.findRegion("ic2"), player2.atlas.findRegion("ic3"));
		
	}
	
	
	public void check_unji(float delta) {
		//Unji
		if (player1.slow == true) {
			if (TimeUtils.nanoTime()- player1.unjidelay < 3000000000f) {
				player1.elapsedTime += 0.7*delta;
				player1.slow = true;
				player1.speedup = -3;
			}
			else {
				player1.unjidelay = 0;
				player1.slow = false;
				player1.speedup = 0;
			}
		}
		
		if (player2.slow == true) {
			if (TimeUtils.nanoTime()- player2.unjidelay < 3000000000f) {
				player2.elapsedTime += 0.7*delta;
				player2.slow = true;
				player2.speedup = -3;
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
			
		}
		if (player2.rect.overlaps(unji.rect) && unji.unsack == false) {
			unji.rect.x += 100000;
			player2.holding = "Unji";
			unji.unsack = true;
		}
		
		//unji unsack overlaps
		if (player1.rect.overlaps(unji.rect) && unji.unsack == true) {
			unji.rect.x += 100000;
			player1.slow = true;
			player1.unjidelay = TimeUtils.nanoTime();
		}
		if (player2.rect.overlaps(unji.rect) && unji.unsack == true) {
			unji.rect.x += 100000;
			player2.slow = true;
			player2.unjidelay = TimeUtils.nanoTime();
		}
		
		//unji holding
		if (player1.holding == "Unji") {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "UP"){
				unji.rect.x = player1.topbox.x;
				unji.rect.y = player1.topbox.y;
				player1.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "DOWN"){
				unji.rect.x = player1.downbox.x;
				unji.rect.y = player1.downbox.y;
				player1.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "RIGHT"){
				unji.rect.x = player1.rightbox.x;
				unji.rect.y = player1.rightbox.y;
				player1.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "LEFT"){
				unji.rect.x = player1.leftbox.x;
				unji.rect.y = player1.leftbox.y;
				player1.holding = "";
			}
		}
		
		if (player2.holding == "Unji") {
			if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "UP"){
				unji.rect.x = player2.topbox.x;
				unji.rect.y = player2.topbox.y;
				player2.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "DOWN"){
				unji.rect.x = player2.downbox.x;
				unji.rect.y = player2.downbox.y;
				player2.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "RIGHT"){
				unji.rect.x = player2.rightbox.x;
				unji.rect.y = player2.rightbox.y;
				player2.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "LEFT"){
				unji.rect.x = player2.leftbox.x;
				unji.rect.y = player2.leftbox.y;
				player2.holding = "";
			}
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
	
	public void check_cactus(float delta) {
		//Check Collision
		for (int i=0 ; i < cactus.size ; ++i) {
			if (player1.rect.overlaps(cactus.get(i))) {
				player1.checkcactus = true;
				player1.cactusdelay = TimeUtils.nanoTime();
				break;
			}
		}
				
		for (int i=0 ; i < cactus.size ; ++i) {
			if (player2.rect.overlaps(cactus.get(i))) {
				player2.checkcactus = true;
				player2.cactusdelay = TimeUtils.nanoTime();
				break;
			}
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
		}
		if (player2.rect.overlaps(shoes.rect)) {
			shoes.rect.x += 10000;
			player2.checkshoe = true;
			player2.shoedelay = TimeUtils.nanoTime();
			player2.speed = true;
		}
		
		//Check Effect
		if (player1.checkshoe == true) {
			if (TimeUtils.nanoTime()- player1.shoedelay < 3000000000f) {
				player1.speedup = 3;
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
				player2.speedup = 3;
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
			if (TimeUtils.nanoTime()- player1.bananadelay < 3000000000f) {
				player1.currentFrame = (TextureRegion) player1.confused.getKeyFrame(player1.elapsedTime, true);
				player1.elapsedTime += 2*delta;
				player1.stuck = true;
			}
			else {
				player1.bananadelay = 0;
				player1.stuck = false;
			}
		}
		
		if (player2.stuck == true) {
			if (TimeUtils.nanoTime()- player2.bananadelay < 3000000000f) {
				player2.currentFrame = (TextureRegion) player2.confused.getKeyFrame(player2.elapsedTime, true);
				player2.elapsedTime += 2*delta;
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
			player1.stuck = true;
			player1.bananadelay = TimeUtils.nanoTime();
		}
		if (player2.rect.overlaps(banana.rect) && banana.peel == true) {
			banana.rect.x += 100000;
			player2.stuck = true;
			player2.bananadelay = TimeUtils.nanoTime();
		}
		
		if (player1.holding == "Banana") {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "UP"){
				banana.rect.x = player1.topbox.x;
				banana.rect.y = player1.topbox.y;
				player1.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "DOWN"){
				banana.rect.x = player1.downbox.x;
				banana.rect.y = player1.downbox.y;
				player1.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "RIGHT"){
				banana.rect.x = player1.rightbox.x;
				banana.rect.y = player1.rightbox.y;
				player1.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player1.prevkey == "LEFT"){
				banana.rect.x = player1.leftbox.x;
				banana.rect.y = player1.leftbox.y;
				player1.holding = "";
			}
		}
		
		if (player2.holding == "Banana") {
			if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "UP"){
				banana.rect.x = player2.topbox.x;
				banana.rect.y = player2.topbox.y;
				player2.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "DOWN"){
				banana.rect.x = player2.downbox.x;
				banana.rect.y = player2.downbox.y;
				player2.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "RIGHT"){
				banana.rect.x = player2.rightbox.x;
				banana.rect.y = player2.rightbox.y;
				player2.holding = "";
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_0) && player2.prevkey == "LEFT"){
				banana.rect.x = player2.leftbox.x;
				banana.rect.y = player2.leftbox.y;
				player2.holding = "";
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
			if(Gdx.input.isKeyPressed(Input.Keys.W) && player1.checkoverlaps == false && player1.stuck == false && player1.checkcactus == false){
	            player1.currentFrame = (TextureRegion) player1.go_up.getKeyFrame(player1.elapsedTime, true);
	            player1.pos_y += 5+player1.speedup;
	            if (player1.slow == false && player1.speed == false)
	            	player1.elapsedTime += 2*delta;
	            player1.stop = 1;
	            player1.rect.y += 5+player1.speedup;
	            player1.body.y += 5+player1.speedup;
	            player1.topbox.y += 5+player1.speedup;
				player1.downbox.y += 5+player1.speedup;
				player1.rightbox.y += 5+player1.speedup;
				player1.leftbox.y += 5+player1.speedup;
	            player1.prevkey = "UP";
	        }
			else if(Gdx.input.isKeyPressed(Input.Keys.S) && player1.checkoverlaps == false && player1.stuck == false && player1.checkcactus == false) {
				player1.currentFrame = (TextureRegion) player1.go_down.getKeyFrame(player1.elapsedTime, true);
				player1.pos_y -= 5-player1.speedup;
				if (player1.slow == false && player1.speed == false)
					player1.elapsedTime += 2*delta;
				player1.stop = 0;
				player1.rect.y -= 5+player1.speedup;
				player1.body.y -= 5+player1.speedup;
				player1.topbox.y -= 5+player1.speedup;
				player1.downbox.y -= 5+player1.speedup;
				player1.rightbox.y -= 5+player1.speedup;
				player1.leftbox.y -= 5+player1.speedup;
				player1.prevkey = "DOWN";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.D) && player1.checkoverlaps == false && player1.stuck == false && player1.checkcactus == false) {
				player1.currentFrame = (TextureRegion) player1.go_right.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x += 5+player1.speedup;
				if (player1.slow == false && player1.speed == false)
					player1.elapsedTime += 2*delta;
				player1.stop = 2;
				player1.rect.x += 5+player1.speedup;
				player1.body.x += 5+player1.speedup;
				player1.topbox.x += 5+player1.speedup;
				player1.downbox.x += 5+player1.speedup;
				player1.rightbox.x += 5+player1.speedup;
				player1.leftbox.x += 5+player1.speedup;
				player1.prevkey = "RIGHT";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.A) && player1.checkoverlaps == false && player1.stuck == false && player1.checkcactus == false) {
				player1.currentFrame = (TextureRegion) player1.go_left.getKeyFrame(player1.elapsedTime, true);
				player1.pos_x -= 5-player1.speedup;
				if (player1.slow == false && player1.speed == false)
					player1.elapsedTime += 2*delta;
				player1.stop = 3;
				player1.rect.x -= 5+player1.speedup;
				player1.body.x -= 5+player1.speedup;
				player1.topbox.x -= 5+player1.speedup;
				player1.downbox.x -= 5+player1.speedup;
				player1.rightbox.x -= 5+player1.speedup;
				player1.leftbox.x -= 5+player1.speedup;
				player1.prevkey = "LEFT";
			}
			else if (player1.checkoverlaps == true && player1.prevkey == "UP" && player1.checkcactus == false) {
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
			else if (player1.checkoverlaps == true && player1.prevkey == "DOWN" && player1.checkcactus == false) {
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
			else if (player1.checkoverlaps == true && player1.prevkey == "RIGHT" && player1.checkcactus == false) {
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
			else if (player1.checkoverlaps == true && player1.prevkey == "LEFT" && player1.checkcactus == false) {
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
			//Cactus
			else if (player1.checkcactus == true && player1.prevkey == "UP" && player1.checkoverlaps == false) {
				if (TimeUtils.nanoTime()- player1.cactusdelay < 1000000000f/2 && player1.checkoverlaps == false) {
					player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0.5f, true);
					player1.pos_y -= 2;
					player1.rect.y -= 2;
					player1.body.y -= 2;
					player1.topbox.y -= 2;
					player1.downbox.y -= 2;
					player1.rightbox.y -= 2;
					player1.leftbox.y -= 2;
					wallcrash.play(0.1f);
				}
				else {
					player1.checkcactus = false;
					player1.cactusdelay = 0;
				}
			}
			else if (player1.checkcactus == true && player1.prevkey == "DOWN" && player1.checkoverlaps == false) {
				if (TimeUtils.nanoTime()- player1.cactusdelay < 1000000000f/2 && player1.checkoverlaps == false) {
					player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0f, true);
					player1.pos_y += 2;
					player1.rect.y += 2;
					player1.body.y += 2;
					player1.topbox.y += 2;
					player1.downbox.y += 2;
					player1.rightbox.y += 2;
					player1.leftbox.y += 2;
					wallcrash.play(0.1f);
				}
				else {
					player1.checkcactus = false;
					player1.cactusdelay = 0;
				}
			}
			else if (player1.checkcactus == true && player1.prevkey == "RIGHT" && player1.checkoverlaps == false) {
				if (TimeUtils.nanoTime()- player1.cactusdelay < 1000000000f/2 && player1.checkoverlaps == false) {
					player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1, true);
					player1.pos_x -= 2;
					player1.rect.x -= 2;
					player1.body.x -= 2;
					player1.topbox.x -= 2;
					player1.downbox.x -= 2;
					player1.rightbox.x -= 2;
					player1.leftbox.x -= 2;
					wallcrash.play(0.1f);
				}
				else {
					player1.checkcactus = false;
					player1.cactusdelay = 0;
				}
			}
			else if (player1.checkcactus == true && player1.prevkey == "LEFT" && player1.checkoverlaps == false) {
				if (TimeUtils.nanoTime()- player1.cactusdelay < 1000000000f/2 && player1.checkoverlaps == false) {
					player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1.5f, true);
					player1.pos_x += 2;
					player1.rect.x += 2;
					player1.body.x += 2;
					player1.topbox.x += 2;
					player1.downbox.x += 2;
					player1.rightbox.x += 2;
					player1.leftbox.x += 2;
					wallcrash.play(0.1f);
				}
				else {
					player1.checkcactus = false;
					player1.cactusdelay = 0;
				}
			}
			//when touched box
			else if (player1.checkoverlaps == true && player1.prevkey == "UP" && player1.checkcactus == true) {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0.5f, true);
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
			else if (player1.checkoverlaps == true && player1.prevkey == "DOWN" && player1.checkcactus == true) {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(0f, true);
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
			else if (player1.checkoverlaps == true && player1.prevkey == "RIGHT" && player1.checkcactus == true) {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1, true);
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
			else if (player1.checkoverlaps == true && player1.prevkey == "LEFT" && player1.checkcactus == true) {
				player1.currentFrame = (TextureRegion) player1.stand.getKeyFrame(1.5f, true);
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
			if(Gdx.input.isKeyPressed(Input.Keys.UP) && player2.checkoverlaps == false && player2.stuck == false && player2.checkcactus == false) {
				player2.currentFrame = (TextureRegion) player2.go_up.getKeyFrame(player2.elapsedTime, true);
			    player2.pos_y += 5+player2.speedup;
			    player2.body.y += 5+player2.speedup;
			    player2.rect.y += 5+player2.speedup;
			    player2.topbox.y += 5+player2.speedup;
				player2.downbox.y += 5+player2.speedup;
				player2.rightbox.y += 5+player2.speedup;
				player2.leftbox.y += 5+player2.speedup;
				if (player2.slow == false && player2.speed == false)
					player2.elapsedTime += 2*delta;
			    player2.stop = 1;
			    player2.prevkey = "UP";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player2.checkoverlaps == false && player2.stuck == false && player2.checkcactus == false) {
				player2.currentFrame = (TextureRegion) player2.go_down.getKeyFrame(player2.elapsedTime, true);
				player2.pos_y -= 5+player2.speedup;
				player2.rect.y -= 5+player2.speedup;
				player2.body.y -= 5+player2.speedup;
				player2.topbox.y -= 5+player2.speedup;
				player2.downbox.y -= 5+player2.speedup;
				player2.rightbox.y -= 5+player2.speedup;
				player2.leftbox.y -= 5+player2.speedup;
				if (player2.slow == false && player2.speed == false)
					player2.elapsedTime += 2*delta;
				player2.stop = 0;
				player2.prevkey = "DOWN";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player2.checkoverlaps == false && player2.stuck == false && player2.checkcactus == false) {
				player2.currentFrame = (TextureRegion) player2.go_right.getKeyFrame(player2.elapsedTime, true);
				player2.pos_x += 5+player2.speedup;
				player2.rect.x += 5+player2.speedup;
				player2.body.x += 5+player2.speedup;
				player2.topbox.x += 5+player2.speedup;
				player2.downbox.x += 5+player2.speedup;
				player2.rightbox.x += 5+player2.speedup;
				player2.leftbox.x += 5+player2.speedup;
				if (player2.slow == false && player2.speed == false)
					player2.elapsedTime += 2*delta;
				player2.stop = 2;
				player2.prevkey = "RIGHT";
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player2.checkoverlaps == false && player2.stuck == false && player2.checkcactus == false) {
				player2.currentFrame = (TextureRegion) player2.go_left.getKeyFrame(player2.elapsedTime, true);
				player2.pos_x -= 5+player2.speedup;
				player2.rect.x -= 5+player2.speedup;
				player2.body.x -= 5+player2.speedup;
				player2.topbox.x -= 5+player2.speedup;
				player2.downbox.x -= 5+player2.speedup;
				player2.rightbox.x -= 5+player2.speedup;
				player2.leftbox.x -= 5+player2.speedup;
				if (player2.slow == false && player2.speed == false)
					player2.elapsedTime += 2*delta;
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
			//Cactus
			else if (player2.checkcactus == true && player2.prevkey == "UP" && player2.checkoverlaps == false) {
				if (TimeUtils.nanoTime()- player2.cactusdelay < 1000000000f/2 && player2.checkoverlaps == false) {
					player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0.5f, true);
					player2.pos_y -= 2;
					player2.rect.y -= 2;
					player2.body.y -= 2;
					player2.topbox.y -= 2;
					player2.downbox.y -= 2;
					player2.rightbox.y -= 2;
					player2.leftbox.y -= 2;
					wallcrash.play(0.1f);
				}
				else {
					player2.checkcactus = false;
					player2.cactusdelay = 0;
				}
			}
			else if (player2.checkcactus == true && player2.prevkey == "DOWN" && player2.checkoverlaps == false) {
				if (TimeUtils.nanoTime()- player2.cactusdelay < 1000000000f/2 && player2.checkoverlaps == false) {
					player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0f, true);
					player2.pos_y += 2;
					player2.rect.y += 2;
					player2.body.y += 2;
					player2.topbox.y += 2;
					player2.downbox.y += 2;
					player2.rightbox.y += 2;
					player2.leftbox.y += 2;
					wallcrash.play(0.1f);
				}
				else {
					player2.checkcactus = false;
					player2.cactusdelay = 0;
				}
			}
			else if (player2.checkcactus == true && player2.prevkey == "RIGHT" && player2.checkoverlaps == false) {
				if (TimeUtils.nanoTime()- player2.cactusdelay < 1000000000f/2 && player2.checkoverlaps == false) {
					player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1, true);
					player2.pos_x -= 2;
					player2.rect.x -= 2;
					player2.body.x -= 2;
					player2.topbox.x -= 2;
					player2.downbox.x -= 2;
					player2.rightbox.x -= 2;
					player2.leftbox.x -= 2;
					wallcrash.play(0.1f);
				}
				else {
					player2.checkcactus = false;
					player2.cactusdelay = 0;
				}
			}
			else if (player2.checkcactus == true && player2.prevkey == "LEFT" && player2.checkoverlaps == false) {
				if (TimeUtils.nanoTime()- player2.cactusdelay < 1000000000f/2 && player2.checkoverlaps == false) {
					player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1.5f, true);
					player2.pos_x += 2;
					player2.rect.x += 2;
					player2.body.x += 2;
					player2.topbox.x += 2;
					player2.downbox.x += 2;
					player2.rightbox.x += 2;
					player2.leftbox.x += 2;
					wallcrash.play(0.1f);
				}
				else {
					player2.checkcactus = false;
					player2.cactusdelay = 0;
				}
			}
			//when touched box
			else if (player2.checkoverlaps == true && player2.prevkey == "UP" && player2.checkcactus == true) {
				player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0.5f, true);
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
			else if (player2.checkoverlaps == true && player2.prevkey == "DOWN" && player2.checkcactus == true) {
				player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(0f, true);
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
			else if (player2.checkoverlaps == true && player2.prevkey == "RIGHT" && player2.checkcactus == true) {
				player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1, true);
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
			else if (player2.checkoverlaps == true && player2.prevkey == "LEFT" && player2.checkcactus == true) {
				player2.currentFrame = (TextureRegion) player2.stand.getKeyFrame(1.5f, true);
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
		}
		//-----------------------------------------------------------------------------------------------------//
	}
	
		
	public void check_win(float delta) {
		wintime += delta;
		//Check Win
		if (player1.rect.overlaps(player2.rect) && player1.win == false && player1.stuck == false) {
			player1.score++;
			hud.score1 = player1.score;
			hud.score1Label.setText(String.format("%02d", hud.score1));
			player1.win = true;
			wintime = 0;
		}

		else if (hud.sec == 0 && hud.min == 0 && player2.win == false) {
			
			if (escape == exit.get(0) && player2.win == false) {
				System.out.println(0);
				tiledMap.getLayers().get("exit1").setVisible(false);
				if (player2.rect.overlaps(exit.get(0))) {
					player2.score++;
					player2.win = true;
					wintime = 0;
				}
			}
			else if (escape == exit.get(1) && player2.win == false) {
				System.out.println(1);
				tiledMap.getLayers().get("exit2").setVisible(false);
				if (player2.rect.overlaps(exit.get(1))) {
					player2.score++;
					player2.win = true;
					wintime = 0;
				}
			}
			else if (escape == exit.get(2) && player2.win == false) {
				System.out.println(2);
				tiledMap.getLayers().get("exit3").setVisible(false);
				if (player2.rect.overlaps(exit.get(2))) {
					player2.score++;
					player2.win = true;
					wintime = 0;
				}
			}
			else if (escape == exit.get(3) && player2.win == false) {
				System.out.println(3);
				tiledMap.getLayers().get("exit4").setVisible(false);
				if (player2.rect.overlaps(exit.get(3))) {
					player2.score++;
					player2.win = true;
					wintime = 0;
				}
			}
		}
		if (wintime >= 5 && (player1.win == true || player2.win == true)) {
			if (player1.win == true) {
				music.stop();
				main.setScreen(new Chapter2(main, player1.score, player2.score));
			}
			if (player2.win == true) {
				music.stop();
				main.setScreen(new Chapter2(main, player1.score, player2.score));
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
		check_unji(delta);
		check_exit(delta);
		check_breakbox(delta);
		check_behind(delta);
		check_cactus(delta);
		check_wall(delta);
		check_win(delta);
		check_shoes(delta);
		countdown(delta);
				
		//Change map
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			main.setScreen(new Chapter2(main, player1.score, player2.score));
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
				
		
		//victory
		if (player1.win == true) {
			batch.draw(hunterwin, (w/2)-600, (h/2)-100, 1200, 400);
		}
		
		if (player2.win == true) {
			batch.draw(runnerwin, (w/2)-600, (h/2)-100, 1200, 400);
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
		int[] layer = {3};
		if (player1.checkbehind == true || player2.checkbehind == true) {
			tiledMapRenderer.render(layer);
		}
		
		//Every 1 box
        int[] boxint1 = {4};
		if (player1.rect.overlaps(hideb1) || player2.rect.overlaps(hideb1)) {
			tiledMapRenderer.render(boxint1);
		}
		int[] boxint2 = {5};
		if (player1.rect.overlaps(hideb2) || player2.rect.overlaps(hideb2)) {
			tiledMapRenderer.render(boxint2);
		}
		int[] boxint3 = {6};
		if (player1.rect.overlaps(hideb3) || player2.rect.overlaps(hideb3)) {
			tiledMapRenderer.render(boxint3);
		}
		int[] boxint4 = {7};
		if (player1.rect.overlaps(hideb4) || player2.rect.overlaps(hideb4)) {
			tiledMapRenderer.render(boxint4);
		}
		int[] boxint5 = {8};
		if (player1.rect.overlaps(hideb5) || player2.rect.overlaps(hideb5)) {
			tiledMapRenderer.render(boxint5);
		}
		int[] boxint6 = {9};
		if (player1.rect.overlaps(hideb6) || player2.rect.overlaps(hideb6)) {
			tiledMapRenderer.render(boxint6);
		}
		int[] boxint7 = {10};
		if (player1.rect.overlaps(hideb7) || player2.rect.overlaps(hideb7)) {
			tiledMapRenderer.render(boxint7);
		}
		int[] boxint8 = {11};
		if (player1.rect.overlaps(hideb8) || player2.rect.overlaps(hideb8)) {
			tiledMapRenderer.render(boxint8);
		}
		int[] boxint9 = {12};
		if (player1.rect.overlaps(hideb9) || player2.rect.overlaps(hideb9)) {
			tiledMapRenderer.render(boxint9);
		}
		int[] boxint10 = {13};
		if (player1.rect.overlaps(hideb10) || player2.rect.overlaps(hideb10)) {
			tiledMapRenderer.render(boxint10);
		}
		int[] boxint11 = {14};
		if (player1.rect.overlaps(hideb11) || player2.rect.overlaps(hideb11)) {
			tiledMapRenderer.render(boxint11);
		}
		int[] boxint12 = {15};
		if (player1.rect.overlaps(hideb12) || player2.rect.overlaps(hideb12)) {
			tiledMapRenderer.render(boxint12);
		}
		int[] boxint13 = {16};
		if (player1.rect.overlaps(hideb13) || player2.rect.overlaps(hideb13)) {
			tiledMapRenderer.render(boxint13);
		}
		int[] boxint14 = {17};
		if (player1.rect.overlaps(hideb14) || player2.rect.overlaps(hideb14)) {
			tiledMapRenderer.render(boxint14);
		}
		int[] boxint15 = {18};
		if (player1.rect.overlaps(hideb15) || player2.rect.overlaps(hideb15)) {
			tiledMapRenderer.render(boxint15);
		}
		int[] boxint16 = {19};
		if (player1.rect.overlaps(hideb16) || player2.rect.overlaps(hideb16)) {
			tiledMapRenderer.render(boxint16);
		}
		int[] boxint17 = {20};
		if (player1.rect.overlaps(hideb17) || player2.rect.overlaps(hideb17)) {
			tiledMapRenderer.render(boxint17);
		}
		int[] boxint18 = {21};
		if (player1.rect.overlaps(hideb18) || player2.rect.overlaps(hideb18)) {
			tiledMapRenderer.render(boxint18);
		}
		int[] boxint19 = {22};
		if (player1.rect.overlaps(hideb19) || player2.rect.overlaps(hideb19)) {
			tiledMapRenderer.render(boxint19);
		}
		int[] boxint20 = {23};
		if (player1.rect.overlaps(hideb20) || player2.rect.overlaps(hideb20)) {
			tiledMapRenderer.render(boxint20);
		}
		int[] boxint21 = {24};
		if (player1.rect.overlaps(hideb21) || player2.rect.overlaps(hideb21)) {
			tiledMapRenderer.render(boxint21);
		}
		int[] boxint22 = {25};
		if (player1.rect.overlaps(hideb22) || player2.rect.overlaps(hideb22)) {
			tiledMapRenderer.render(boxint22);
		}
		int[] boxint23 = {26};
		if (player1.rect.overlaps(hideb23) || player2.rect.overlaps(hideb23)) {
			tiledMapRenderer.render(boxint23);
		}
		int[] boxint24 = {27};
		if (player1.rect.overlaps(hideb24) || player2.rect.overlaps(hideb24)) {
			tiledMapRenderer.render(boxint24);
		}
		int[] boxint25 = {28};
		if (player1.rect.overlaps(hideb25) || player2.rect.overlaps(hideb25)) {
			tiledMapRenderer.render(boxint25);
		}
		int[] boxint26 = {29};
		if (player1.rect.overlaps(hideb26) || player2.rect.overlaps(hideb26)) {
			tiledMapRenderer.render(boxint26);
		}
		int[] boxint27 = {30};
		if (player1.rect.overlaps(hideb27) || player2.rect.overlaps(hideb27)) {
			tiledMapRenderer.render(boxint27);
		}
		int[] boxint28 = {31};
		if (player1.rect.overlaps(hideb28) || player2.rect.overlaps(hideb28)) {
			tiledMapRenderer.render(boxint28);
		}
		int[] boxint29 = {32};
		if (player1.rect.overlaps(hideb29) || player2.rect.overlaps(hideb29)) {
			tiledMapRenderer.render(boxint29);
		}
		int[] boxint30 = {33};
		if (player1.rect.overlaps(hideb30) || player2.rect.overlaps(hideb30)) {
			tiledMapRenderer.render(boxint30);
		}
		int[] boxint31 = {34};
		if (player1.rect.overlaps(hideb31) || player2.rect.overlaps(hideb31)) {
			tiledMapRenderer.render(boxint31);
		}
		int[] boxint32 = {35};
		if (player1.rect.overlaps(hideb32) || player2.rect.overlaps(hideb32)) {
			tiledMapRenderer.render(boxint32);
		}
		int[] boxint33 = {36};
		if (player1.rect.overlaps(hideb33) || player2.rect.overlaps(hideb33)) {
			tiledMapRenderer.render(boxint33);
		}
		int[] boxint34 = {37};
		if (player1.rect.overlaps(hideb34) || player2.rect.overlaps(hideb34)) {
			tiledMapRenderer.render(boxint34);
		}
		int[] boxint35 = {38};
		if (player1.rect.overlaps(hideb35) || player2.rect.overlaps(hideb35)) {
			tiledMapRenderer.render(boxint35);
		}
		int[] boxint36 = {39};
		if (player1.rect.overlaps(hideb36) || player2.rect.overlaps(hideb36)) {
			tiledMapRenderer.render(boxint36);
		}
		int[] boxint37 = {40};
		if (player1.rect.overlaps(hideb37) || player2.rect.overlaps(hideb37)) {
			tiledMapRenderer.render(boxint37);
		}
		int[] boxint38 = {41};
		if (player1.rect.overlaps(hideb38) || player2.rect.overlaps(hideb38)) {
			tiledMapRenderer.render(boxint38);
		}
		int[] boxint39 = {42};
		if (player1.rect.overlaps(hideb39) || player2.rect.overlaps(hideb39)) {
			tiledMapRenderer.render(boxint39);
		}
		int[] boxint40 = {43};
		if (player1.rect.overlaps(hideb40) || player2.rect.overlaps(hideb40)) {
			tiledMapRenderer.render(boxint40);
		}
		int[] boxint41 = {44};
		if (player1.rect.overlaps(hideb41) || player2.rect.overlaps(hideb41)) {
			tiledMapRenderer.render(boxint41);
		}
		int[] boxint42 = {45};
		if (player1.rect.overlaps(hideb42) || player2.rect.overlaps(hideb42)) {
			tiledMapRenderer.render(boxint42);
		}
		int[] boxint43 = {46};
		if (player1.rect.overlaps(hideb43) || player2.rect.overlaps(hideb43)) {
			tiledMapRenderer.render(boxint43);
		}
		int[] boxint44 = {47};
		if (player1.rect.overlaps(hideb44) || player2.rect.overlaps(hideb44)) {
			tiledMapRenderer.render(boxint44);
		}
		int[] boxint45 = {48};
		if (player1.rect.overlaps(hideb45) || player2.rect.overlaps(hideb45)){
			tiledMapRenderer.render(boxint45);
		}
		int[] boxint46 = {49};
		if (player1.rect.overlaps(hideb46) || player2.rect.overlaps(hideb46)) {
			tiledMapRenderer.render(boxint46);
		}
		
		
		
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
