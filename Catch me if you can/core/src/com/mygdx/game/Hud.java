package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud implements Disposable{
	public Stage stage;
	public Viewport viewport;
	
	public Integer min, sec;
	public float timeCount;
	public static Integer score1, score2;
	public BitmapFont font;
	public boolean stop;
	
	public Label score1Label;
	public Label score2Label;
	public Label timeLabel;
	public Label countdownLabel;
	public Label text1Label;
	public Label text2Label;
	
	public Hud(SpriteBatch sb, int score1, int score2) {
		font = new BitmapFont();
		font.getData().setScale(2, 2);
		this.score1 = score1;
		this.score2 = score2;
		stop = false;
		min = 1;
		sec = 30;
		viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		score1Label = new Label(String.format("%02d", score1), new Label.LabelStyle(font, Color.WHITE));
		score2Label = new Label(String.format("%02d", score2), new Label.LabelStyle(font, Color.WHITE));
		
		timeLabel = new Label("Time", new Label.LabelStyle(font, Color.WHITE));
		text1Label = new Label("Hunter Score", new Label.LabelStyle(font, Color.WHITE));
		text2Label = new Label("Runner Score", new Label.LabelStyle(font, Color.WHITE));
		countdownLabel = new Label(String.format("%02d : %02d", min, sec), new Label.LabelStyle(font, Color.WHITE));
		
		table.add(text1Label).expandX();
		table.add(timeLabel).expandX();
		table.add(text2Label).expandX();
		table.row();
		table.add(score1Label).expandX();
		table.add(countdownLabel).expandX();
		table.add(score2Label).expandX();
		
		stage.addActor(table);
	}

	@Override
	public void dispose() {
		font.dispose();
		stage.dispose();
	}
}
