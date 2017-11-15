package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.screen.Loading;
import com.mygdx.game.screen.MainMenuScreen;

public class Main extends Game{
	Main main = this;
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 680;
	public SpriteBatch batch;
	private static long SPLASH_MINIMUM_MILLIS = 2000L;
	
	public void create() {
		//Sprite, Batch, Texture, Atlas
		batch = new SpriteBatch();
		
		this.setScreen(new Loading(this));
		final long splash_start_time = System.currentTimeMillis();
		 new Thread(new Runnable() {
             @Override
             public void run() {

                 Gdx.app.postRunnable(new Runnable() {
                     @Override
                     public void run() {
                         // ... carga de datos
                         // ... carga de fuentes tipograficas
                         // ... carga de sonidos
                         // ... carga de imagenes
                         // ... carga de recursos de internacionalizacion
                         // ... otros

                         // Se muestra el menu principal tras la SpashScreen
                         long splash_elapsed_time = System.currentTimeMillis() - splash_start_time;
                         if (splash_elapsed_time < Main.SPLASH_MINIMUM_MILLIS) {
                             Timer.schedule(
                                     new Timer.Task() {
                                         @Override
                                         public void run() {
                                      	   Main.this.setScreen(new MainMenuScreen(main));
                                         }
                                     }, (float)(Main.SPLASH_MINIMUM_MILLIS - splash_elapsed_time) / 1000f);
                         } else {
                      	   Main.this.setScreen(new MainMenuScreen(main));
                         }
                     }
                 });
             }
          }).start();
		//Go to MainMenuScreen
		//this.setScreen(new MainMenuScreen(this));
    }
	
//    public void render() {
//        super.render(); //important!
//    }
	
    public void dispose() {
    	getScreen().dispose();
    	Gdx.app.exit();
    }

}
