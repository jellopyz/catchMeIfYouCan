package com.mygdx.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 680;
		config.width = 1280;
		config.title = "Catch Me If You Can";
		config.forceExit = false;
		config.addIcon("icon/icon.png", FileType.Internal);
		new LwjglApplication(new Main(), config);
	}
}
