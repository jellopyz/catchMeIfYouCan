package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Jet implements ApplicationListener {
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private Texture texture1;
    private Sprite sprite1;
    
    @Override
    public void create() {        
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("0002.png"));
        texture1 = new Texture(Gdx.files.internal("0001.jpg"));
        sprite = new Sprite(texture);
        sprite1 = new Sprite(texture1);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        texture1.dispose();
    }

    @Override
    public void render() {        
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(texture, 300, 0);
        batch.draw(texture1, 500, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}