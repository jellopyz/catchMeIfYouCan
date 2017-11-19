package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Main;

public class MainMenuScreen implements Screen{
	final Main main;
	
	public SpriteBatch batch;
	public TextureAtlas start, exit, title;
	public TextureRegion startcurrentFrame, exitcurrentFrame, titlecurrentFrame;
	public Animation start_anime, exit_anime, title_anime;
	public float menutime = 0, titletime = 0, delay = 0;
	public Sound music;
	public OrthographicCamera camera;
	public Viewport gamePort;
	public TiledMap tiledMap;
	public TiledMapRenderer tiledMapRenderer;
	
	public MainMenuScreen(Main main) {
		this.main = main;
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        //TiledMap
        tiledMap = new TmxMapLoader().load("menu/allmenu.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        //Camera,Viewport
        gamePort = new StretchViewport(Main.V_WIDTH, Main.V_HEIGHT, camera);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
		//Sprite, Batch, Texture, Atlas
		batch = main.batch;
		start = new TextureAtlas(Gdx.files.internal("button/start/start.atlas"));
		exit = new TextureAtlas(Gdx.files.internal("button/exit/exit.atlas"));
		title = new TextureAtlas(Gdx.files.internal("title/title.atlas"));
		
		//Sound
		music = Gdx.audio.newSound(Gdx.files.internal("sound/music/menumusic.mp3"));
		music.loop();
		//Animation
		start_anime = new Animation(1/2f, start.getRegions());
		exit_anime = new Animation(1/2f, exit.getRegions());
		title_anime = new Animation(0.6f, title.findRegion("title1"),title.findRegion("title1"), title.findRegion("title1")
					,title.findRegion("title1"), title.findRegion("title2"),title.findRegion("title3"),title.findRegion("title4")
					,title.findRegion("title5"),title.findRegion("title6"),title.findRegion("title5"),title.findRegion("title4")
					,title.findRegion("title3"),title.findRegion("title2"));
	}

	@Override
	public void render(float delta) {
		//Clear
		Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //----------------------------------------//
        //Camera
        camera.update();
        if (camera.position.x > 1280*6-640) {
        	camera.position.x = 640;
        }
        camera.position.x += 100*delta;
        
			startcurrentFrame = (TextureRegion) start_anime.getKeyFrame(0, true);
			exitcurrentFrame = (TextureRegion) exit_anime.getKeyFrame(0, true);
			titlecurrentFrame = (TextureRegion) title_anime.getKeyFrame(titletime, true);
			
			titletime += 6*delta;
			menutime += 10*Gdx.graphics.getDeltaTime();
			//Move Mouse//
			if(Gdx.input.getX() >= (1280/2)-100 && Gdx.input.getX() <= (1280/2)+100) {
				if (Gdx.input.getY() >= (680/2)+90 && Gdx.input.getY() <= (680/2)+65+90) {
					startcurrentFrame = (TextureRegion) start_anime.getKeyFrame(0.5f, true);
				}
				if (Gdx.input.getY() >= (680/2)+90+100 && Gdx.input.getY() <= (680/2)+65+100+90) {
					exitcurrentFrame = (TextureRegion) exit_anime.getKeyFrame(0.5f, true);
				}
			}
			
			
			//Control//
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
		        if(Gdx.input.getX() >= (1280/2)-100 && Gdx.input.getX() <= (1280/2)+100) {
		        	//Start
		        	if (Gdx.input.getY() >= (680/2)+90 && Gdx.input.getY() <= (680/2)+65+90) {
		        		startcurrentFrame = (TextureRegion) start_anime.getKeyFrame(0.5f, true);
		        		delay += 0.5;
		        		if (delay > 2) {
		        			main.setScreen(new Chapter1(main));
		        			dispose();
		        		}
		        	}
		        	//Exit
		        	if (Gdx.input.getY() >= (680/2)+90+100 && Gdx.input.getY() <= (680/2)+65+90+100) {
		        		exitcurrentFrame = (TextureRegion) exit_anime.getKeyFrame(0.5f, true);
		        		delay += 0.5;
		        		if (delay > 2) {
		        			Gdx.app.exit();
		        			dispose();
		        		}
		        	}
		        	
		        }
		    }
			//-------------------------------------------------------------------------------//
			//tiledMaprender
			tiledMapRenderer.setView(camera);
			tiledMapRenderer.render();
			//Graphics Rendering//
			batch.begin();
			batch.draw(titlecurrentFrame, 290, 300, 700, 250);
			batch.draw(startcurrentFrame, (1280/2)-140, (680/2)-300, 300, 300);
			batch.draw(exitcurrentFrame, (1280/2)-140, (680/2)-400, 300, 300);
			batch.end();
			//---------------------------------------------------------------------------------//
        
        
	}

	@Override
	public void dispose() {
		start.dispose();
		exit.dispose();
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
