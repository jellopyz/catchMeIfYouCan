package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {
	public Stage stage;
	public Viewport viewport;
	
	public Integer worldTimer;
	public float timeCount;
	public Integer score1, score2;
	
	Label score1Label;
	Label score2Label;
	Label timeLabel;
	Label countdownLabel;
	Label text1Label;
	Label text2Label;
	
	public Hud(SpriteBatch sb) {
		score1 = 0;
		score2 = 0;
		viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		score1Label = new Label(String.format("%06d", score1), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		score2Label = new Label(String.format("%06d", score2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		timeLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		text1Label = new Label("Player1 Score", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		text2Label = new Label("Player2 Score", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(text1Label).expandX().padTop(10);
		table.add(text2Label).expandX().padTop(10);
		table.row();
		table.add(score1Label).expandX();
		table.add(score2Label).expandX();
		
		stage.addActor(table);
	}
}
